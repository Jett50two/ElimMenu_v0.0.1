package menu.elimcare.elimmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    // boolean control = false;
    int numOfWeeks = 0, firstLoop = 0, secondLoop = 0;
    String iMenu;
    Spinner spin;
    Button btnSubmit,btnBack, btnNext, btnSave;
    EditText etPrimaryFood, etSide1, etSide2, etSide3,etDessert;
    String[][] userInput = new String[3][6];
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
            Toast.makeText(getApplicationContext(),iMenu, Toast.LENGTH_LONG).show();
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

                break;
            }
            case R.id.btnNext: {
                for(int i = firstLoop; i <numOfWeeks;){
                    userInput[firstLoop][secondLoop] = getText();
                    secondLoop++;
                    if (secondLoop == 6 ){
                        secondLoop = 0;
                        firstLoop++;
                    }
                    break;
                }
                break;
            }
            case R.id.btnSave: {

                break;
            }
        }
    }

    public String getText(){
        String str = "";
        str = etPrimaryFood.getText().toString() + etSide1.getText().toString();
        if(etSide2.getVisibility() == View.VISIBLE){

        }
        if (etSide3.getVisibility() == View.VISIBLE){

        }

        return str;
    }
}
