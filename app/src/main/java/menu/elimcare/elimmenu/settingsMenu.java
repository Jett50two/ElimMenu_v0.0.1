package menu.elimcare.elimmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim C. on 3/4/2018.
 */

public class settingsMenu extends AppCompatActivity implements View.OnClickListener {
    public static Log log;
    private static final String TAGarray = "Array Input";

    int numOfWeeks = 0, loop = 0, loopBack = 0;
    String iMenu;
    Spinner spin, spinSide;
    Button btnSubmit,btnBack, btnNext, btnSave;
    EditText etPrimaryFood, etSide1, etSide2, etSide3,etDessert;
    String[] userInput = new String[28];
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

    /**
     * Layout visibility control and settings buttons for the code
     * For layout 1
     */
    public void displayLay1(){
        findViewById(R.id.menuNew).setVisibility(View.VISIBLE);
        findViewById(R.id.menuDis).setVisibility(View.GONE);

        addEditText();
        addButtons();
        addItemsToSpin();
    }

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

    /**
     * Layout visibility control and settings buttons for the code
     * for layout 2
     */
    public void displayLay2(){
        findViewById(R.id.menuNew).setVisibility(View.GONE);
        findViewById(R.id.menuDis).setVisibility(View.VISIBLE);
    }

    public void displayLay3(){
        findViewById(R.id.menuNew).setVisibility(View.GONE);
        findViewById(R.id.menuDis).setVisibility(View.GONE);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSubmit: {
                findViewById(R.id.layWeeks).setVisibility(View.GONE);
                findViewById(R.id.btnSubmit).setVisibility(View.GONE);
                findViewById(R.id.layInput).setVisibility(view.VISIBLE);
                numOfWeeks = (int) spin.getSelectedItem();
                break;
            }
            case R.id.btnBack: {
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

                break;
            }
            case R.id.btnNext: {
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

                break;
            }
            case R.id.btnSave: {
                log.d("btnSave", "Number of weeks = " + numOfWeeks
                        + "\nloop = " + loop + "\nloopBack = " + loopBack
                        + "\nTotal [" + (loop - loopBack) + "]");
                break;
            }
        }
    }

    /**
     * Read the EditText views and replace all text with nothing.
     * @return
     */
    public String readAndReplace(){
        log.d("readAndReplace", "firstLoop = " + loop
                + "\nTotal [" + (loop - loopBack) + "]");
        String str = "";
        str = etPrimaryFood.getText().toString().replace("/", ",").trim() + "/" + etSide1.getText().toString().replace("/", ",").trim();
        etPrimaryFood.setText("");
        etSide1.setText("");
        if(etSide2.getVisibility() == View.VISIBLE){
            str = str + "/" + etSide2.getText().toString().replace("/", ",").trim();
            etSide2.setText("");
        }
        if (etSide3.getVisibility() == View.VISIBLE){
            str = str + "/" + etSide3.getText().toString().replace("/", ",").trim();
            etSide3.setText("");
        }
        str = str + "/" + etDessert.getText().toString().replace("/",",").trim();
        etDessert.setText("");
        log.d("readAndReplace", "firstLoop = " + loop
                + "\nTotal [" + (loop - loopBack) + "]"
                + "str = " + str);
        return str;
    }

    public void backDisplay(int loop1, int loop1Back){
        String[] str = userInput[loop1 - loop1Back].split("/");
        log.d("backDisplay", "firstLoopBack = " + loop1Back
                + "\nfirstLoop = " + loop1
                + "\nTotal [" + (loop1 - loop1Back) + "]"
                + "input" + userInput[loop1 - loop1Back]);
        if (str.length == 5){
            etPrimaryFood.setText(str[0]);
            etSide1.setText(str[1]);
            etSide2.setText(str[2]);
            etSide3.setText(str[3]);
            etDessert.setText(str[4]);
            log.d("length == 5",
                    "str[0] = " + str[0] +
                            "str[1] = " + str[1] +
                            "str[2] = " + str[2] +
                            "str[3] = " + str[3] +
                            "str[4] = " + str[4]);
        }
        else if (str.length == 4){
            etPrimaryFood.setText(str[0]);
            etSide1.setText(str[1]);
            etSide2.setText(str[2]);
            etDessert.setText(str[3]);
            log.d("length == 5",
                    "str[0] = " + str[0] +
                            "str[1] = " + str[1] +
                            "str[2] = " + str[2] +
                            "str[3] = " + str[3]);
        }
        else if (str.length == 3){
            etPrimaryFood.setText(str[0]);
            etSide1.setText(str[1]);
            etDessert.setText(str[2]);
            log.d("length == 5",
                    "str[0] = " + str[0] +
                            "str[1] = " + str[1] +
                            "str[2] = " + str[2]);
        }
    }

    public void nextDisplay(int loop1, int loop1Back) {
        log.d("nextDisplay", "firstLoopBack = " + loop1Back
                + "\nfirstLoop = " + loop1
                + "\nTotal [" + (loop1 - loop1Back) + "]");

        if (loop1Back == 0) {
            etPrimaryFood.setText("");
            etSide1.setText("");
            findViewById(R.id.laySide2).setVisibility(View.VISIBLE);
            etSide2.setText("");
            findViewById(R.id.laySide3).setVisibility(View.VISIBLE);
            etSide3.setText("");
            etDessert.setText("");
        } else {
            String[] str = userInput[loop1 - loop1Back].split("/");
            if (str.length == 5) {
                etPrimaryFood.setText(str[0]);
                etSide1.setText(str[1]);
                findViewById(R.id.laySide2).setVisibility(View.VISIBLE);
                etSide2.setText(str[2]);
                findViewById(R.id.laySide3).setVisibility(View.VISIBLE);
                etSide3.setText(str[3]);
                etDessert.setText(str[4]);
                log.d("length == 5",
                        str[0] +
                        str[1] +
                        str[2] +
                        str[3] +
                        str[4]);
            }
            else if (str.length == 4) {
                etPrimaryFood.setText(str[0]);
                etSide1.setText(str[1]);
                findViewById(R.id.laySide2).setVisibility(View.VISIBLE);
                etSide2.setText(str[2]);
                findViewById(R.id.laySide2).setVisibility(View.GONE);
                etSide3.setText("");
                etDessert.setText(str[3]);
                log.d("length == 4",
                        str[0] +
                                str[1] +
                                str[2] +
                                str[3]);
            }
            else if (str.length == 3) {
                etPrimaryFood.setText(str[0]);
                etSide1.setText(str[1]);
                findViewById(R.id.laySide2).setVisibility(View.GONE);
                etSide2.setText("");
                findViewById(R.id.laySide3).setVisibility(View.GONE);
                etSide3.setText("");
                etDessert.setText(str[2]);
                log.d("length == 3",
                        str[0] +
                                str[1] +
                                str[2]);
            }
        }
    }

}

/*Old 2d array stuff.
///////////////////////////////////////////////////////         btnBack
if (firstLoop - firstLoopBack == 0 && 6 - secondLoopBack == 0) {
                    log.d("skip", "skip");
                    break;
                }
                else if (firstLoopBack == 0 && firstLoop > 0 && secondLoop == secondLoopBack){
                    firstLoopBack = firstLoopBack + 1;
                    secondLoopBack = 0;
                    log.d("2nd = 2ndBack", "add to first loop");
                    backDisplay(firstLoop, firstLoopBack, 6, secondLoopBack);
                }
                else if (firstLoopBack != 0 && secondLoopBack >= 6){
                    firstLoopBack = firstLoopBack + 1;
                    secondLoopBack = 0;
                    log.d("if second > 6", "add to first loop");
                    backDisplay(firstLoop, firstLoopBack, 6, secondLoopBack);
                }
                else  if (firstLoopBack == 0 && secondLoop > secondLoopBack){
                    secondLoopBack = secondLoopBack + 1;
                    log.d("2ndLoop > 2ndBack", "add to second loop");
                    backDisplay(firstLoop, firstLoopBack, secondLoop, secondLoopBack);
                }
                else  if (firstLoopBack > 0 && secondLoopBack < 6){
                    secondLoopBack = secondLoopBack + 1;
                    log.d("6 > 2ndBack", "add to second loop");
                    backDisplay(firstLoop, firstLoopBack, 6, secondLoopBack);
                } else {
                    log.d("ERROR", "firstLoop = " + firstLoop + "\nfirstLoopBack = " + firstLoopBack + "\nsecondLoop = " + secondLoop + "\nsecondLoopBack = " + secondLoopBack);
                }

///////////////////////////////////////////////////////         btnNext
if (firstLoopBack != 0 || secondLoopBack != 0){
                    if (secondLoop == 6){
                        userInput[firstLoop - firstLoopBack][secondLoop - secondLoopBack] = readAndReplace();
                    }
                    else  if (firstLoop - firstLoopBack == firstLoop){
                        userInput[firstLoop - firstLoopBack][secondLoop - secondLoopBack] = readAndReplace();
                    } else {
                        userInput[firstLoop - firstLoopBack][6 - secondLoopBack] = readAndReplace();
                    }
                    if (firstLoopBack != 0 && secondLoopBack == 0){
                        firstLoopBack = firstLoopBack - 1;
                        if (firstLoopBack == 1){
                            firstLoopBack = firstLoopBack - 1;
                            secondLoopBack = secondLoop;
                            log.d("if 1stLoopBack == 1", "add to second loop");
                            nextDisplay(firstLoop, firstLoopBack, secondLoop, secondLoopBack);
                        }
                        else {
                            secondLoopBack = 6;
                            log.d("else", "sub to first loop");
                            nextDisplay(firstLoop, firstLoopBack, 6, secondLoopBack);
                        }
                    }
                    else if (firstLoopBack == 0){
                        secondLoopBack = secondLoopBack - 1;
                        log.d("if 1stLoopBack == 0", "sub second loop");
                         nextDisplay(firstLoop, firstLoopBack, secondLoop, secondLoopBack);
                    } else {
                        secondLoopBack = secondLoopBack - 1;
                        log.d("else", "add to second loop");
                        nextDisplay(firstLoop, firstLoopBack, 6, secondLoopBack);
                    }
                    break;
                    /* old crap
                    if (secondLoopBack == 0){
                        firstLoopBack = firstLoopBack - 1;
                        secondLoopBack = 0;
                    }
                    else {
                        secondLoopBack = secondLoopBack - 1;
                    }
                    log.d("counting back up", "firstLoopBack = " + firstLoopBack + "\nsecondLoopBack = " + secondLoopBack
                            + "\nTotal [" + (firstLoop - firstLoopBack) + "] [" + (secondLoop-secondLoopBack) + "]");
                    nextDisplay();
                } else {
                        for (int i = firstLoop; i < numOfWeeks; ) {
        userInput[firstLoop][secondLoop] = readAndReplace();
        log.d("Loops", "firstLoop = " + firstLoop + "\nsecondLoop = " + secondLoop);
        log.d(TAGarray, "\nUserInput: " + userInput[firstLoop][secondLoop]
        + "\nTotal [" + (firstLoop - firstLoopBack) + "] [" + (secondLoop-secondLoopBack) + "]");
        secondLoop++;
        if (secondLoop > 6) {
        secondLoop = 0;
        firstLoop++;
        }
        break;
        }
        if (firstLoop >= numOfWeeks){
        findViewById(R.id.btnNext).setVisibility(View.GONE);
        findViewById(R.id.btnSave).setVisibility(View.VISIBLE);
        // save information
        }
        }
 */
