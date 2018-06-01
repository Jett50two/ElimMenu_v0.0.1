package menu.elimcare.elimmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by TIm C. on 3/3/2018.
 */

public class menu extends AppCompatActivity implements View.OnClickListener {
    Log log;
    CheckBox cBox0, cBox1, cBox2, cBox3, cBox4, cBox5, cBox6,
            cBox7, cBox8, cBox9, cBox10, cBox11, cBox12, cBox13;
    EditText textInput;
    String menuControl, checkedBoxes;
    String[] oldData, iLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Button btnApply = findViewById(R.id.btnApply);
        Button btnBack = findViewById(R.id.btnBack);

        btnApply.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        readBundles();
    }

    private void readBundles(){
        Bundle extras = getIntent().getExtras();
        if (extras == null){
            menuControl = null;
            changeDisplay();
        } else {
            menuControl = extras.getString("menuType");
            oldData = extras.getStringArray("oldData");
            iLocation = extras.getStringArray("roomInfo");
            changeDisplay();
        }
    }

    private void changeDisplay() {
        if(menuControl == null){
            nullDisplay();
        } else if (menuControl.equals("primary")){
            changeDisplay1();
        } else if (menuControl.equals("sides")){
            changeDisplay2();
        } else if (menuControl.equals("drink")){
            changeDisplay3();
        } else if (menuControl.equals("dessert")){
            changeDisplay4();
        } else if (menuControl.equals("menu")){
            displayMenu();
        }
        else {
            otherDisplay();
        }
    }

    private void nullDisplay() {
        findViewById(R.id.layPrimary).setVisibility(View.GONE);
        findViewById(R.id.laySides).setVisibility(View.GONE);
        findViewById(R.id.layDrink).setVisibility(View.GONE);
        findViewById(R.id.layDesserts).setVisibility(View.GONE);
    }
    private void changeDisplay1() {
        findViewById(R.id.layPrimary).setVisibility(View.VISIBLE);
        findViewById(R.id.laySides).setVisibility(View.GONE);
        findViewById(R.id.layDrink).setVisibility(View.GONE);
        findViewById(R.id.layDesserts).setVisibility(View.GONE);
        // meats
        cBox0 = findViewById(R.id.spagh);
        cBox1 = findViewById(R.id.meatloaf);
        cBox2 = findViewById(R.id.fFish);
        cBox3 = findViewById(R.id.rBeef);
        cBox4 = findViewById(R.id.fChicken);
        cBox5 = findViewById(R.id.gChicken);
        // sand/burgers
        cBox6 = findViewById(R.id.burger);
        cBox7 = findViewById(R.id.blt);
        cBox8 = findViewById(R.id.cbrw);
        // Salads
        cBox9 = findViewById(R.id.coSalad);
        cBox10 = findViewById(R.id.dinnerSalad);
        // soups
        cBox11 = findViewById(R.id.tomatoSoup);
        cBox12 = findViewById(R.id.chickenSoup);
        cBox13 = findViewById(R.id.sod);
    }
    private void changeDisplay2() {
        findViewById(R.id.layPrimary).setVisibility(View.GONE);
        findViewById(R.id.laySides).setVisibility(View.VISIBLE);
        findViewById(R.id.layDrink).setVisibility(View.GONE);
        findViewById(R.id.layDesserts).setVisibility(View.GONE);

        cBox0 = findViewById(R.id.mashedPotatos);
        cBox1 = findViewById(R.id.potatoChips);
        cBox2 = findViewById(R.id.carrots);
        cBox3 = findViewById(R.id.peas);
        cBox4 = findViewById(R.id.checkOther);
        textInput = findViewById(R.id.etOther);
    }
    private void changeDisplay3() {
        findViewById(R.id.layPrimary).setVisibility(View.GONE);
        findViewById(R.id.laySides).setVisibility(View.GONE);
        findViewById(R.id.layDrink).setVisibility(View.VISIBLE);
        findViewById(R.id.layDesserts).setVisibility(View.GONE);

        cBox0 = findViewById(R.id.milk);
        cBox1 = findViewById(R.id.chocoMilk);
        cBox2 = findViewById(R.id.aj);
        cBox3 = findViewById(R.id.cran);
        cBox4 = findViewById(R.id.prune);
        cBox5 = findViewById(R.id.oj);
    }
    private void changeDisplay4() {
        findViewById(R.id.layPrimary).setVisibility(View.GONE);
        findViewById(R.id.laySides).setVisibility(View.GONE);
        findViewById(R.id.layDrink).setVisibility(View.GONE);
        findViewById(R.id.layDesserts).setVisibility(View.VISIBLE);

        cBox0 = findViewById(R.id.fDessert);
        cBox1 = findViewById(R.id.iceCream);
        cBox2 = findViewById(R.id.jello);
        cBox3 = findViewById(R.id.yogurt);
        cBox4 = findViewById(R.id.peaches);
        cBox5 = findViewById(R.id.pears);
        cBox6 = findViewById(R.id.appleSauce);
    }
    private void displayMenu(){
        findViewById(R.id.layPrimary).setVisibility(View.VISIBLE);
        findViewById(R.id.laySides).setVisibility(View.VISIBLE);
        findViewById(R.id.layDrink).setVisibility(View.VISIBLE);
        findViewById(R.id.layDesserts).setVisibility(View.VISIBLE);
        findViewById(R.id.layButtons).setVisibility(View.GONE);
    }
    private void otherDisplay(){
        findViewById(R.id.layPrimary).setVisibility(View.GONE);
        findViewById(R.id.laySides).setVisibility(View.GONE);
        findViewById(R.id.layDrink).setVisibility(View.GONE);
        findViewById(R.id.layDesserts).setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        Intent iBackToMenu = new Intent(this, orderField.class);;
        iBackToMenu.putExtra("roomInfo", iLocation);
        iBackToMenu.putExtra("oldData", oldData);
        startActivity(iBackToMenu);
    }

    @Override
    public void onClick(View view) {
        Intent iBackToMenu = new Intent(this, orderField.class);;
        switch (view.getId()){
            case R.id.btnApply: {
                if (menuControl.equals("primary")){
                    primaryCheckBox();
                    iBackToMenu.putExtra("lay1Boxes", checkedBoxes);
                    iBackToMenu.putExtra("roomInfo", iLocation);
                    iBackToMenu.putExtra("oldData", oldData);
                    startActivity(iBackToMenu);
                } else if (menuControl.equals("sides")){
                    sidesCheckBox();
                    iBackToMenu.putExtra("lay2Boxes", checkedBoxes);
                    iBackToMenu.putExtra("roomInfo", iLocation);
                    iBackToMenu.putExtra("oldData", oldData);
                    startActivity(iBackToMenu);
                } else if (menuControl.equals("drink")){
                    drinkCheckBox();
                    iBackToMenu.putExtra("lay3Boxes", checkedBoxes);
                    iBackToMenu.putExtra("roomInfo", iLocation);
                    iBackToMenu.putExtra("oldData", oldData);
                    startActivity(iBackToMenu);
                } else if (menuControl.equals("dessert")){
                    dessertCheckBox();
                    iBackToMenu.putExtra("lay4Boxes", checkedBoxes);
                    iBackToMenu.putExtra("roomInfo", iLocation);
                    iBackToMenu.putExtra("oldData", oldData);
                    startActivity(iBackToMenu);
                }
                break;
            }
            case R.id.btnBack: {
                iBackToMenu.putExtra("roomInfo", iLocation);
                iBackToMenu.putExtra("oldData", oldData);
                startActivity(iBackToMenu);
                break;
            }
        }
    }

    /**
     *
     */
    private void primaryCheckBox(){
        if (cBox0.isChecked()){
            checkedBoxes(cBox0);
        }
        if (cBox1.isChecked()){
            checkedBoxes(cBox1);
        }
        if (cBox2.isChecked()){
            checkedBoxes(cBox2);
        }
        if (cBox3.isChecked()){
            checkedBoxes(cBox3);
        }
        if (cBox4.isChecked()){
            checkedBoxes(cBox4);
        }
        if (cBox5.isChecked()){
            checkedBoxes(cBox5);
        }
        if (cBox6.isChecked()){
            checkedBoxes(cBox6);
        }
        if (cBox7.isChecked()){
            checkedBoxes(cBox7);
        }
        if (cBox8.isChecked()){
            checkedBoxes(cBox8);
        }
        if (cBox9.isChecked()){
            checkedBoxes(cBox9);
        }
        if (cBox10.isChecked()){
            checkedBoxes(cBox10);
        }
        if (cBox11.isChecked()){
            checkedBoxes(cBox11);
        }
        if (cBox12.isChecked()){
            checkedBoxes(cBox12);
        }
        if (cBox13.isChecked()){
            checkedBoxes(cBox13);
        }
    }

    private void sidesCheckBox() {if (cBox0.isChecked()){
        checkedBoxes(cBox0);
    }
        if (cBox1.isChecked()){
            checkedBoxes(cBox1);
        }
        if (cBox2.isChecked()){
            checkedBoxes(cBox2);
        }
        if (cBox3.isChecked()){
            checkedBoxes(cBox3);
        }
        if (cBox4.isChecked()){
            userInput(textInput);
        }
    }
    private void drinkCheckBox() {
        if (cBox0.isChecked()){
            checkedBoxes(cBox0);
        }
        if (cBox1.isChecked()){
            checkedBoxes(cBox1);
        }
        if (cBox2.isChecked()){
            checkedBoxes(cBox2);
        }
        if (cBox3.isChecked()){
            checkedBoxes(cBox3);
        }
        if (cBox4.isChecked()){
            checkedBoxes(cBox4);
        }
        if (cBox5.isChecked()){
            checkedBoxes(cBox5);
        }
    }
    private void dessertCheckBox() {
        if (cBox0.isChecked()){
            checkedBoxes(cBox0);
        }
        if (cBox1.isChecked()){
            checkedBoxes(cBox1);
        }
        if (cBox2.isChecked()){
            checkedBoxes(cBox2);
        }
        if (cBox3.isChecked()){
            checkedBoxes(cBox3);
        }
        if (cBox4.isChecked()){
            checkedBoxes(cBox4);
        }
        if (cBox5.isChecked()){
            checkedBoxes(cBox5);
        }
        if (cBox6.isChecked()){
            checkedBoxes(cBox6);
        }
    }

    /**
     *
     * @param box
     */
    private void checkedBoxes(CheckBox box){
        if(checkedBoxes == null){
            checkedBoxes = box.getText().toString();
        } else {
            checkedBoxes = checkedBoxes + ",\n" + box.getText().toString();
        }
    }

    /**
     *
     * @param userInput
     */
    private void userInput(EditText userInput){
        if(checkedBoxes == null){
            checkedBoxes = userInput.getText().toString();
        } else {
            checkedBoxes = checkedBoxes + ",\n" + userInput.getText().toString();
        }
    }
}
