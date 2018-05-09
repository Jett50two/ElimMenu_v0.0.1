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

/**
 * Created by Tim C. on 3/3/2018.
 */

public class saveAndLoad {
    public static Log log;
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

    public static void save2dMenu(String filename, String[][] array2D, Context context){
        FileOutputStream fos = null;
        File file = new File(filename);
        try {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context.getApplicationContext(), "Error with save data: " + e, Toast.LENGTH_LONG).show();
        }
        try {
            try {
                for (int i = 0; i < 4; i++){
                    for (int j = 0; j < 7; j++){
                        fos.write(array2D[i][j].getBytes());
                        log.d(TAGsave, "Line " + i + ":" + array2D[i][j] + "\n");
                        if (i < 4 && j < 6) {
                            fos.write("\n".getBytes());
                        }
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
    public static void saveRoom(String filename, String[] data, Context context){
        FileOutputStream fos = null;
        File file = new File(filename);
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
    public static String[] clearBlankOnSave(String[] loadData, String[] data){
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
    public static String[] clearBlankOnLoad(String[] loadData){
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
    public static void saveRoom(String filename, String roomNum, Context context){
        try {
            filename = filename + "-" + roomNum + ".txt";
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
