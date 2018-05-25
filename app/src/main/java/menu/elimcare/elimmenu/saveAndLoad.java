package menu.elimcare.elimmenu;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Tim C. on 3/3/2018.
 */

public class saveAndLoad {
    private static Log log;
    private static final String TAGsave = "saveAndLoad, FILE SAVE";

    /**
     * This is only called at the start of the program. Once the original files have been made it will check and skip.
     * @param context = used for toasts
     * @param fileNum = the number of files to be created.
     * @param fileNames = the file names (file.txt).
     * @param fileContents = the contents of the file.
     */
    public saveAndLoad(Context context, int fileNum, String[] fileNames, String[] fileContents) {
        // read to see if files are there, if they aren't. Create files.
        for (int i = 0; i < fileNum; i++) {
            try {
                String filename = context.getApplicationContext().getFilesDir().getPath().toString() + fileNames[i] + ".txt";
                File file = new File(filename);
                if (!file.exists()) {
                    file.createNewFile();
                    Toast.makeText(context, "Creating files for " + fileNames[i] + "", Toast.LENGTH_LONG).show();
                    for(int j = 0; j < fileContents.length; j++){
                       //fileContents[j].replace(" ", "");
                    }
                    // Save(filename, fileContents, context);
                }
            } catch (IOException e) {
                Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    /**
     * These are the methods for saving file information
     * @param filename = file name being saved.
     * @param data = data to be saved.
     * @param context = toast
     * Save takes the file name, the array information, and the context of the application.
     */
    public static void Save(String filename, String[] data, Context context){
        FileOutputStream fos = null;
        File file = new File(filename);
        // load data
        String[] loadData = loadData(filename, context);
        // clear out any possible blank lines in the array
        String[] saveData = clearBlankOnSave(loadData, data);
        try {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
        }
        try {
            try {
                for (int i = 0; i<saveData.length; i++){
                    fos.write(saveData[i].getBytes());
                    log.d(TAGsave, "Line " + i + ":" + saveData[i] + "\n");
                    if (i < saveData.length - 1) {
                        fos.write("\n".getBytes());
                    }
                }
                Toast.makeText(context.getApplicationContext(), "Creating save data...", Toast.LENGTH_LONG).show();
            }
            catch (IOException e){
                e.printStackTrace();
                Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
            }
        }
        finally {
            try {
                fos.close();
            }
            catch (IOException e){
                e.printStackTrace();
                Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * This is for updating room information
     * @param filename = file name/path
     * @param data = data being saved
     * @param context = toasts
     */
    public static void saveRoom(String filepath, String filename, String hallname, String[] data, Context context){
        updateHallInfo(filepath, hallname, data, context);
        FileOutputStream fos = null;
        File file = new File(filepath + filename);
        // clear out any possible blank lines in the array
        String str = "";
        for(int i = 0; i < data.length; i++){
            if(data[i].equals(null) || data[i].equals("")){
                // do nothing
            }else {
                str = str + data[i] + "/";
            }
        }

        for (int i = 0; i<data.length; i++){
            str = str + data[i] + "/";
        }
        String[] saveData = str.split("/");

        try {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
        }
        try {
            try {
                for (int i = 0; i<saveData.length; i++){
                    fos.write(saveData[i].getBytes());
                    log.d("saveRoom", "Line " + i + ":" + saveData[i] + "\n");
                    if (i < saveData.length - 1) {
                        fos.write("\n".getBytes());
                    }
                }
                Toast.makeText(context.getApplicationContext(), "Creating save data...", Toast.LENGTH_LONG).show();
            }
            catch (IOException e){
                e.printStackTrace();
                Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
            }
        }
        finally {
            try {
                fos.close();
            }
            catch (IOException e){
                e.printStackTrace();
                Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
            }
        }
    }

    private static void updateHallInfo(String filepath, String hallName, String[] data, Context context){
        log.d("updateHall", "filepath = " + filepath
                + "\nhallName = " + hallName
                + "\ndata = " + Arrays.toString(data)
                + "\ncontext = " + context);
        FileOutputStream fos = null;
        File file = new File(filepath + "rooms.txt");
        String[] loadData = loadData(filepath + "rooms.txt", context);
        String[] contentsOfFile;
        String str = "";
        for (int i = 0; i<loadData.length; i++){
            log.d("updateHall", "loadData [" + i + "] = " + loadData[i]);
            if(loadData[i].equals(hallName)){
                log.d("updateHall", loadData[i] + " = " + hallName);
                contentsOfFile = loadData[i+1].split(",");
                for (int j = 0; j < contentsOfFile.length; j++){
                    if(j == 0){
                        if(contentsOfFile[j].equals(data [0])){
                            str = contentsOfFile[j];
                            str = str + "," + data[1] + " " + data[2];
                            j++;
                        } else {
                            str = contentsOfFile[j];
                        }
                    } else {
                        if(contentsOfFile[j].equals(data [0])){
                            str = str + "," + contentsOfFile[j];
                            str = str + "," + data[1] + " " + data[2];
                            j++;
                        } else {
                            str = str + "," + contentsOfFile[j];
                        }
                    }
                    log.d("updateHall", "str = " + str
                            +"\nj = " + j);
                }
            }
        }
        log.d("updateHall", "str = " + str);
        for (int i = 0; i<loadData.length; i++){
            if(loadData[i].equals(hallName)){
                loadData[i+1] = str;
            }

            log.d("updateHall", "loadData[" + i + "] = " + str);
        }
        try {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
        }
        try {
            try {
                for (int i = 0; i<loadData.length; i++){
                    fos.write(loadData[i].getBytes());
                    log.d("updateHall", "Line " + i + ":" + loadData[i] + "\n");
                    if (i < loadData.length - 1) {
                        fos.write("\n".getBytes());
                    }
                }
                Toast.makeText(context.getApplicationContext(), "Creating save data...", Toast.LENGTH_LONG).show();
            }
            catch (IOException e){
                e.printStackTrace();
                Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
            }
        }
        finally {
            try {
                fos.close();
            }
            catch (IOException e){
                e.printStackTrace();
                Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Just a basic save option. nothing special to it. just save information.
     * @param filepath = path to the file
     * @param data = information for saving
     * @param context = context
     */
    public static void basicSave(String filepath, String[] data, Context context){
        FileOutputStream fos = null;
        File file = new File(filepath);
        // clear out any possible blank lines in the array
        String str = "";
        for(int i = 0; i < data.length; i++){
            if(data[i].equals(null) || data[i].equals("")){
                // do nothing
            }else {
                str = str + data[i] + "/";
            }
        }

        for (int i = 0; i<data.length; i++){
            str = str + data[i] + "/";
        }
        String[] saveData = str.split("/");

        try {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
        }
        try {
            try {
                for (int i = 0; i<saveData.length; i++){
                    fos.write(saveData[i].getBytes());
                    log.d(TAGsave, "Line " + i + ":" + saveData[i] + "\n");
                    if (i < saveData.length - 1) {
                        fos.write("\n".getBytes());
                    }
                }
                Toast.makeText(context.getApplicationContext(), "Creating save data...", Toast.LENGTH_LONG).show();
            }
            catch (IOException e){
                e.printStackTrace();
                Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
            }
        }
        finally {
            try {
                fos.close();
            }
            catch (IOException e){
                e.printStackTrace();
                Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Clear blank values in the array.
     * @param loadData = takes loaded data already from the file
     * @param data = takes new data being saved.
     * @return return the combined values
     */
    private static String[] clearBlankOnSave(String[] loadData, String[] data){
        String str = "";
        for(int i = 0; i < loadData.length; i++){
            if(loadData[i].equals(null) || loadData[i].equals("")){
                // do nothing
            }else {
                str = str + loadData[i] + "/";
            }
        }

        for (int i = 0; i<data.length; i++){
            str = str + data[i] + "/";
        }
        String[] saveData = str.split("/");
        return saveData;
    }

    /**
     * These are the methods for loading data from the txt files
     * @param filename = file being loaded
     * @param context = toast (if needed)
     * @return
     */
    public static String[] loadData(String filename, Context context){
        File file = new File(filename);
        String fileInput = "";
        FileInputStream fis = null;

        String line;
        try {
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            int count = 0;
            while((line=br.readLine())!=null) {
                if (count == 0){
                    if(line.equals("") || line.equals(" ") || line.equals(null)){
                        fileInput = "";
                    }
                    else {
                        fileInput = line;
                    }
                    count++;
                }else {
                    fileInput = fileInput + "/" + line;
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        // split the string at the "/" symbols.
        String[] split = fileInput.split("/");
        // clear out any possible blank lines in the array
        split = clearBlankOnLoad(split);
        return split;
    }

    /**
     * Special function
     * @param loadData = only load data from file.
     * @return
     */
    private static String[] clearBlankOnLoad(String[] loadData){
        String str = "";
        for(int i = 0; i < loadData.length; i++){
            if(loadData[i].equals(null) || loadData[i].equals("")){
                // do nothing
            }else {
                str = str + loadData[i] + "/";
            }
        }
        loadData = str.split("/");
        return loadData;
    }

    /**
     *
     * @param filename = file name to save
     * @param roomNum = room number.
     * @param context
     */
    public static void saveHall(String filename, String roomNum, Context context){
        try {
            filename = filename + roomNum;
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
                log.d(TAGsave, "file made \n");
                Toast.makeText(context, "Creating files for " + roomNum + ".txt", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            log.d(TAGsave, filename + "\n" + e);
            Toast.makeText(context.getApplicationContext(), "Error with save data: \n" + e, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
