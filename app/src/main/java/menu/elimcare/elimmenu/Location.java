package menu.elimcare.elimmenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Omega on 3/1/2018.
 */

public class Location extends AppCompatActivity {
    public saveAndLoad sAndL;
    Context context;
    String filename;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    // This is numbers, hall0 = hall'zero', hall1 = hall'one'.
    List<String> hall0 = new ArrayList<>(), hall1 = new ArrayList<>(),
            hall2 = new ArrayList<>(), hall3 = new ArrayList<>(),
            hall4 = new ArrayList<>(), hall5 = new ArrayList<>(),
            hall6 = new ArrayList<>(), hall7 = new ArrayList<>(),
            hall8 = new ArrayList<>(), hall9 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        listView();
    }

    /**
     * Create and fill the expandable list view.
     */
    public void listView(){
        // get the listview
        expListView = findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    /**
     * Create the parents for the expandable list view
     */
    private void prepareListData() {
        // call to expListLoader.class to load information from the file.
        context = getApplicationContext();
        filename = context.getFilesDir().getPath().toString() + "rooms.txt";
        // load information from saveAndLoad
        String[] split = sAndL.loadData(filename, context);
        // call to expListLoader.class to handle the information that was read.

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding header data
        for(int i = 0; i< split.length; i = i+2){
            listDataHeader.add(split[i].trim());
        }

        /*
        I will add this to read file input later on.
        (that way it can be updated.)
         */
        // Adding child data
        for(int i = 1; i <split.length; i = i+2){
            String[] array = split[i].split(",");
            for(int j = 0; j < array.length; j ++){
                array[j].trim();
            }
            arrayCreater(array, i);
        }
        for(int i = 1; i <split.length; i = i+2) {
            if (i == 1) {
                listDataChild.put(listDataHeader.get(0), hall0); // Header, Child data
            } else if (i == 3) {
                listDataChild.put(listDataHeader.get(1), hall1); // Header, Child data
            } else if (i == 5) {
                listDataChild.put(listDataHeader.get(2), hall2); // Header, Child data
            } else if (i == 7) {
                listDataChild.put(listDataHeader.get(3), hall3); // Header, Child data
            } else if (i == 9) {
                listDataChild.put(listDataHeader.get(4), hall4); // Header, Child data
            } else if (i == 11) {
                listDataChild.put(listDataHeader.get(5), hall5); // Header, Child data
            } else if (i == 13) {
                listDataChild.put(listDataHeader.get(6), hall6); // Header, Child data
            } else if (i == 15) {
                listDataChild.put(listDataHeader.get(7), hall7); // Header, Child data
            } else if (i == 17) {
                listDataChild.put(listDataHeader.get(8), hall8); // Header, Child data
            } else if (i == 19) {
                listDataChild.put(listDataHeader.get(9), hall9); // Header, Child data
            }
        }
    }

    /**
     * Fill children fields with values.
     * @param array
     * @param i
     */
    private void arrayCreater(String[] array, int i){
        if(i == 1){
            for(int j =0; j < array.length; j++){
                if(j%2 == 0) {
                    hall0.add(array[j].trim());
                } else {
                    hall0.add("\t" + array[j].trim());
                }
            }
        } else if(i == 3){
            for(int j =0; j < array.length; j++){
                if(j%2 == 0) {
                    hall1.add(array[j].trim());
                } else {
                    hall1.add("\t" + array[j].trim());
                }
            }
        } else if(i == 5){
            for(int j =0; j < array.length; j++){
                if(j%2 == 0) {
                    hall2.add(array[j].trim());
                } else {
                    hall2.add("\t" + array[j].trim());
                }
            }
        } else if(i == 7){
            for(int j =0; j < array.length; j++){
                if(j%2 == 0) {
                    hall3.add(array[j].trim());
                } else {
                    hall3.add("\t" + array[j].trim());
                }
            }
        } else if(i == 9){
            for(int j =0; j < array.length; j++){
                if(j%2 == 0) {
                    hall4.add(array[j].trim());
                } else {
                    hall4.add("\t" + array[j].trim());
                }
            }
        } else if(i == 11){
            for(int j =0; j < array.length; j++){
                if(j%2 == 0) {
                    hall5.add(array[j].trim());
                } else {
                    hall5.add("\t" + array[j].trim());
                }
            }
        } else if(i == 13){
            for(int j =0; j < array.length; j++){
                if(j%2 == 0) {
                    hall6.add(array[j].trim());
                } else {
                    hall6.add("\t" + array[j].trim());
                }
            }
        } else if(i == 15){
            for(int j =0; j < array.length; j++){
                if(j%2 == 0) {
                    hall7.add(array[j].trim());
                } else {
                    hall7.add("\t" + array[j].trim());
                }
            }
        } else if(i == 17){
            for(int j =0; j < array.length; j++){
                if(j%2 == 0) {
                    hall8.add(array[j].trim());
                } else {
                    hall8.add("\t" + array[j].trim());
                }
            }
        } else if(i == 19){
            for(int j =0; j < array.length; j++){
                if(j%2 == 0) {
                    hall9.add(array[j].trim());
                } else {
                    hall9.add("\t" + array[j].trim());
                }
            }
        }
    }
}
