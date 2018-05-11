package menu.elimcare.elimmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tim C. on 3/4/2018.
 */

public class settingsMenu extends AppCompatActivity implements View.OnClickListener {
    public static Log log;
    saveAndLoad sAndL;

    /////////////////////////// These variables are for the layout 1 ///////////////////////////////
    int numOfWeeks = 0, loop = 0, loopBack = 0;
    String iMenu;
    Spinner spin, spinSide;
    Button btnSubmit,btnBack, btnNext, btnSave;
    EditText etPrimaryFood, etSide1, etSide2, etSide3,etDessert;
    String[] userInput;

    /////////////////////////// These variables are for the layout 2 ///////////////////////////////
    expandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    List<String> week0 = new ArrayList<>(), week1 = new ArrayList<>(),
                 week2 = new ArrayList<>(), week3 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);
        // Get extras from the intent
        Bundle extras = getIntent().getExtras();
        // make sure there is an extra in the intent
        if(extras == null) {
            iMenu= null;
            Toast.makeText(getApplicationContext(),"Error: Extra is 'null'", Toast.LENGTH_LONG).show();
        } else {
            iMenu = extras.getString("menu");
            changeDisplay();
        }
    }

    /**
     * this method will change the display of the
     */
    private void changeDisplay(){
        if(iMenu.equals("new")){
            displayLay1();
        }
        else if (iMenu.equals("edit")){
            displayLay2();
        } else if (iMenu.equals("display")){
            displayLay3();
        }
    }

    /**                                                             FIRST DISPLAY //////////////////
     * Layout visibility control and settings buttons for the code
     * For layout 1
     * All functions that follow below are only used by displayLay1
     */
    public void displayLay1(){
        findViewById(R.id.menuNew).setVisibility(View.VISIBLE);
        findViewById(R.id.menuDis).setVisibility(View.GONE);

        addEditText();
        addButtons();
        addItemsToSpin();
    }
    /**
     * addEditText, addButtons, addItemsToSpin
     * Are functions for setting variables to IDs.
     */
    public void addEditText() {
        etPrimaryFood = findViewById(R.id.etPrimaryFood);
        etSide1 = findViewById(R.id.etSide1);
        etSide2 = findViewById(R.id.etSide2);
        etSide3 = findViewById(R.id.etSide3);
        etDessert = findViewById(R.id.etDessert);
    }
    public void addButtons(){
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }
    public void addItemsToSpin() {
        spin = findViewById(R.id.spinner);
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);

        spinSide= findViewById(R.id.spinSide);
        List<Integer> side = new ArrayList<>();
        side.add(3);
        side.add(2);
        side.add(1);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, side);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSide.setAdapter(adapter);
        spinSide.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                sideLimit();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // Buttons
    /**
     * backButton, nextButton
     * Are only used for displayLay1. So For better orgonization, I put them into functions below
     * the displayLay1 function.
     */
    public void submitButton(){
        findViewById(R.id.layWeeks).setVisibility(View.GONE);
        findViewById(R.id.btnSubmit).setVisibility(View.GONE);
        findViewById(R.id.layInput).setVisibility(View.VISIBLE);
        numOfWeeks = (int) spin.getSelectedItem();
        userInput  = new String[7 * numOfWeeks];
        log.d("arraySize", "SIZE == " + userInput.length);
    }
    public void backButton() {
        // if the next button has been replaced, bring it back.
        if(btnNext.getVisibility() == View.GONE){
            findViewById(R.id.btnNext).setVisibility(View.VISIBLE);
            findViewById(R.id.btnSave).setVisibility(View.GONE);
        }
        if(loop - loopBack <= 0){

        } else {
            loopBack++;
            backDisplay (loop, loopBack);
        }
    }
    public void nextButton(){
        // If the user has gone back through their input,
        if(loopBack > 0){
            userInput[loop - loopBack] = readAndReplace();
            loopBack--;
            if (loopBack == 0 && loop == numOfWeeks * 7){
                nextDisplay(loop, loopBack + 1);
                findViewById(R.id.btnNext).setVisibility(View.GONE);
                findViewById(R.id.btnSave).setVisibility(View.VISIBLE);
            } else {
                nextDisplay(loop, loopBack);
            }
        } else {
            if(loop < numOfWeeks * 7){
                userInput[loop] = readAndReplace();
                loop++;
                if (loop >= numOfWeeks * 7){
                    nextDisplay(loop, loopBack+1);
                    log.d("if", "Number of weeks = " + numOfWeeks
                            + "\nloop = " + loop + "\nloopBack = " + loopBack
                            + "\nTotal [" + (loop - loopBack) + "]");
                    findViewById(R.id.btnNext).setVisibility(View.GONE);
                    findViewById(R.id.btnSave).setVisibility(View.VISIBLE);
                }
            } else {
                nextDisplay(loop, loopBack+1);
                log.d("else", "Number of weeks = " + numOfWeeks
                        + "\nloop = " + loop + "\nloopBack = " + loopBack
                        + "\nTotal [" + (loop - loopBack) + "]");
                findViewById(R.id.btnNext).setVisibility(View.GONE);
                findViewById(R.id.btnSave).setVisibility(View.VISIBLE);
            }
        }
    }
    public void saveButton(){
        log.d("btnSave", getApplicationContext().getFilesDir().getPath().toString() + "menu.txt" +
                "\nNumber of weeks = " + numOfWeeks
                + "\nloop = " + loop + "\nloopBack = " + loopBack
                + "\nTotal [" + (loop - loopBack) + "]");
        // saveRoom is used because it overwrites the save file.
        sAndL.saveRoom(getApplicationContext().getFilesDir().getPath().toString() + "menu.txt", userInput, this);
        finish();
    }

    // displays
    /**
     * Read the EditText views and replace all text with nothing.
     * @return
     */
    public String readAndReplace(){
        log.d("readAndReplace", "firstLoop = " + loop
                + "\nTotal [" + (loop - loopBack) + "]");
        String str = "";
        str = etPrimaryFood.getText().toString().replace("-", " ").trim() + "-" + etSide1.getText().toString().replace("-", " ").trim();
        etPrimaryFood.setText("");
        etSide1.setText("");
        if(etSide2.getVisibility() == View.VISIBLE){
            str = str + "-" + etSide2.getText().toString().replace("-", " ").trim();
            etSide2.setText("");
        }
        if (etSide3.getVisibility() == View.VISIBLE){
            str = str + "-" + etSide3.getText().toString().replace("-", " ").trim();
            etSide3.setText("");
        }
        str = str + "-" + etDessert.getText().toString().replace("-"," ").trim();
        etDessert.setText("");
        log.d("readAndReplace", "firstLoop = " + loop
                + "\nTotal [" + (loop - loopBack) + "]"
                + "str = " + str);
        return str;
    }
    public void backDisplay(int loop1, int loop1Back){
        String[] str = userInput[loop1 - loop1Back].split("-");
        displayLog("nextDisplay", loop1, loop1Back);
        log.d("backDisplay", "firstLoopBack = " + loop1Back
                + "\nfirstLoop = " + loop1
                + "\nTotal [" + (loop1 - loop1Back) + "]"
                + "input" + userInput[loop1 - loop1Back]);
        if (str.length == 5){
            displayLength5(str);
        }
        else if (str.length == 4){
            displayLength4(str);
        }
        else if (str.length == 3){
            displayLength3(str);
        }
    }
    public void nextDisplay(int loop1, int loop1Back) {
        displayLog("nextDisplay", loop1, loop1Back);
        if (loop1Back == 0) {
            etPrimaryFood.setText("");
            etSide1.setText("");
            findViewById(R.id.laySide2).setVisibility(View.VISIBLE);
            etSide2.setText("");
            findViewById(R.id.laySide3).setVisibility(View.VISIBLE);
            etSide3.setText("");
            etDessert.setText("");
        } else {
            String[] str = userInput[loop1 - loop1Back].split("-");
            if (str.length == 5){
                displayLength5(str);
            }
            else if (str.length == 4){
                displayLength4(str);
            }
            else if (str.length == 3){
                displayLength3(str);
            }
        }
    }

    /**
     * This is here to display log information. if it isn't needed, then
     * @param functionName
     * @param loop1
     * @param loopBack1
     */
    public void displayLog(String functionName, int loop1, int loopBack1){
        log.d(functionName, "firstLoopBack = " + loopBack1
                + "\nfirstLoop = " + loop1
                + "\nTotal [" + (loop1 - loopBack1) + "]"
                + "input" + userInput[loop1 - loopBack1]);
    }

    /**
     * displayLength5, displayLength4, displayLength3
     * Focus on the different possible layouts from reading the array information
     * @param str
     */
    public void displayLength5(String[] str){
        etPrimaryFood.setText(str[0]);
        etSide1.setText(str[1]);
        etSide2.setText(str[2]);
        etSide3.setText(str[3]);
        etDessert.setText(str[4]);
        log.d("length == 5",
                "\nstr[0] = " + str[0] +
                        "\nstr[1] = " + str[1] +
                        "\nstr[2] = " + str[2] +
                        "\nstr[3] = " + str[3] +
                        "\nstr[4] = " + str[4]);
    }
    public void displayLength4(String[] str){
        etPrimaryFood.setText(str[0]);
        etSide1.setText(str[1]);
        etSide2.setText(str[2]);
        etDessert.setText(str[3]);
        log.d("length == 5",
                "\nstr[0] = " + str[0] +
                        "\nstr[1] = " + str[1] +
                        "\nstr[2] = " + str[2] +
                        "\nstr[3] = " + str[3]);
    }
    public void displayLength3(String[] str){
        etPrimaryFood.setText(str[0]);
        etSide1.setText(str[1]);
        etDessert.setText(str[2]);
        log.d("length == 5",
                "\nstr[0] = " + str[0] +
                        "\nstr[1] = " + str[1] +
                        "\nstr[2] = " + str[2]);
    }

    /**
     * This limits the side dishes in the menu layout. For example,
     * if there is only 1 side, laySide2, laySide3 will be removed.
     */
    public void sideLimit(){
        int sideLimit = (int)spinSide.getSelectedItem();
        if(sideLimit == 1){
            findViewById(R.id.laySide2).setVisibility(View.GONE);
            findViewById(R.id.laySide3).setVisibility(View.GONE);
        } else if(sideLimit == 2){
            findViewById(R.id.laySide2).setVisibility(View.VISIBLE);
            findViewById(R.id.laySide3).setVisibility(View.GONE);
        } else {
            findViewById(R.id.laySide2).setVisibility(View.VISIBLE);
            findViewById(R.id.laySide3).setVisibility(View.VISIBLE);
        }
    }


    /**                                                             SECOND DISPLAY /////////////////
     * Layout visibility control and settings buttons for the code
     * for layout 2
     */
    public void displayLay2(){
        findViewById(R.id.menuNew).setVisibility(View.GONE);
        findViewById(R.id.menuDis).setVisibility(View.VISIBLE);
        listView();
    }

    public void listView(){
        // get the listview
        expListView = findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new expandableListAdapter(this, listDataHeader, listDataChild);

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
        // load information from saveAndLoad
        String[] split = sAndL.loadData(getApplicationContext().getFilesDir().getPath().toString() + "menu.txt", this);

        // call to expListLoader.class to handle the information that was read.
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding header data
        int totalWeeks = split.length;
        if(totalWeeks / 7 == 1){
            listDataHeader.add("Week 1");
        } else if(totalWeeks / 7 == 2){
            listDataHeader.add("Week 1");
            listDataHeader.add("Week 2");
        } else if(totalWeeks / 7 == 3){
            listDataHeader.add("Week 1");
            listDataHeader.add("Week 2");
            listDataHeader.add("Week 3");
        } else {
            listDataHeader.add("Week 1");
            listDataHeader.add("Week 2");
            listDataHeader.add("Week 3");
            listDataHeader.add("Week 4");
        }
        // Adding child data
        for(int i = 0; i <split.length / 7;){
            for(int j = 1; j < split.length + 1; j++){
                String[] array = split[j - 1].split("-");
                arrayCreater(array, i);
                log.d("Loading 4 Loop Data", "array.length = " + array.length
                        + "\ni = " + i + ", j = " + j + ", j-1 = " + (j-1));
                if (j%7 == 0){
                    log.d("if j%7", "true (i think" + j % 7);
                    i++;
                }
            }
        }
        for(int i = 0; i <split.length / 7; i++) {
            if (i == 0) {
                listDataChild.put(listDataHeader.get(0), week0); // Header, Child data
            } else if (i == 1) {
                listDataChild.put(listDataHeader.get(1), week1); // Header, Child data
            } else if (i == 2) {
                listDataChild.put(listDataHeader.get(2), week2); // Header, Child data
            } else if (i == 3) {
                listDataChild.put(listDataHeader.get(3), week3); // Header, Child data
            }
        }
    }

    /**
     * Fill children fields with values.
     * @param array
     * @param i
     */
    private void arrayCreater(String[] array, int i){
        String strMenuDay = "";
        if(i == 0){
            for(int j =0; j < array.length; j++){
                log.d("Loading 4 Loop Data", "Array  = " + array[j]
                        + "strMenuDay = " + strMenuDay);
                if (j < array.length - 1){
                    strMenuDay = strMenuDay + array[j].trim() + "\n";
                } else {
                    strMenuDay = strMenuDay + array[j].trim();
                }
            }
            week0.add(strMenuDay);
        } else if (i == 1){
            for(int j =0; j < array.length; j++){
                log.d("Loading 4 Loop Data", "Array  = " + array[j]
                        + "strMenuDay = " + strMenuDay);
                if (j < array.length - 1){
                    strMenuDay = strMenuDay + array[j].trim() + "\n";
                } else {
                    strMenuDay = strMenuDay + array[j].trim();
                }
            }
            week1.add(strMenuDay);
        } else if (i == 2){
            for(int j =0; j < array.length; j++){
                log.d("Loading 4 Loop Data", "Array  = " + array[j]
                        + "strMenuDay = " + strMenuDay);
                if (j < array.length - 1){
                    strMenuDay = strMenuDay + array[j].trim() + "\n";
                } else {
                    strMenuDay = strMenuDay + array[j].trim();
                }
            }
            week2.add(strMenuDay);
        } else if (i == 3){
            for(int j =0; j < array.length; j++){
                log.d("Loading 4 Loop Data", "Array  = " + array[j]
                        + "strMenuDay = " + strMenuDay);
                if (j < array.length - 1){
                    strMenuDay = strMenuDay + array[j].trim() + "\n";
                } else {
                    strMenuDay = strMenuDay + array[j].trim();
                }
            }
            week3.add(strMenuDay);
        }
    }

    /**                                                             THIRD DISPLAY //////////////////
     * Layout visibility control and settings buttons for the code
     * for layout 3
     */
    public void displayLay3(){
        findViewById(R.id.menuNew).setVisibility(View.GONE);
        findViewById(R.id.menuDis).setVisibility(View.GONE);
    }

    /**
     * //////////////////////////////////////////// ON CLICK ///////////////////////////////////////
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*
            *   btnSubmit, btnBack, btnNext, btnSave
            *   are all used by layout 1.
             */
            case R.id.btnSubmit: {
                submitButton();
                break;
            }
            case R.id.btnBack: {
                backButton();
                break;
            }
            case R.id.btnNext: {
                nextButton();
                break;
            }
            case R.id.btnSave: {
                saveButton();
                break;
            }
        }
    }
}