package menu.elimcare.elimmenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addRooms extends AppCompatActivity implements View.OnClickListener {
    public static Log log;
    private static final String TAGpointer = "Check Null Pointer";
    public saveAndLoad sAndL;
    // global ints
    int lowNum, highNum;
    // global Strings
    String iLayout, iRoomNumber, iFilename, strHallName, strLowNum, strHighNum;
    // Buttons
    Button cancel, save;
    // EditText
    EditText etRoomID, etFirstname, etLastName, etFoodDiet, etFluidRestric, etOther;

    /**
     * On create
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_rooms);

        // Get extras from the intent
        Bundle extras = getIntent().getExtras();
        // make sure there is an extra in the intent
        if(extras == null) {
            iLayout= null;
            Toast.makeText(getApplicationContext(),"Error: Extra is 'null'", Toast.LENGTH_LONG).show();
        } else {
            iLayout = extras.getString("layout");
            iRoomNumber = extras.getString("roomNumber");
            iFilename = extras.getString("filename");
            changeDisplay();
        }
    }

    /**
     * this method will change the display of the
     */
    private void changeDisplay(){
        if(iLayout.equals("hall")){
            displayLay1();
        }
        else if (iLayout.equals("room")){
            displayLay2();
        }
    }

    /**
     * Layout visibility control and settings buttons for the code
     * For layout 1
     */
    public void displayLay1(){
        findViewById(R.id.lay1).setVisibility(View.VISIBLE);
        findViewById(R.id.lay2).setVisibility(View.GONE);

        // buttons
        cancel = findViewById(R.id.btnCancel);
        cancel.setOnClickListener(this);
        save = findViewById(R.id.btnSave);
        save.setOnClickListener(this);
    }

    /**
     * Layout visibility control and settings buttons for the code
     * for layout 2
     */
    public void displayLay2(){
        findViewById(R.id.lay1).setVisibility(View.GONE);
        findViewById(R.id.lay2).setVisibility(View.VISIBLE);

        // EditText
        etRoomID = findViewById(R.id.textRoomID);
        etFirstname = findViewById(R.id.textFirstName);
        etLastName = findViewById(R.id.textLastName);
        etFoodDiet = findViewById(R.id.textFoodDeit);
        etFluidRestric = findViewById(R.id.textFluidRestric);
        etOther = findViewById(R.id.textOther);


        // buttons
        cancel = findViewById(R.id.btn2Cancel);
        cancel.setOnClickListener(this);
        save = findViewById(R.id.btn2Update);
        save.setOnClickListener(this);

        // Call setInput for displaying current information.
        setInput();
    }

    /**
     * Update the display in the "edit room" layout (lay2)
     */
    private void setInput(){
        try {
            int num = Integer.parseInt(iRoomNumber);
            log.d(TAGpointer, "number: " + num + "\nOriginal: " + iRoomNumber);
            etRoomID.setText(String.valueOf(num));
        } catch (NumberFormatException e) {
            etRoomID.setText(String.valueOf(""));
            // Toast.makeText(getApplicationContext(), "Unknown Error: " + e, Toast.LENGTH_LONG).show();
        }
        String[] loadedData = sAndL.loadData(iFilename + iRoomNumber + ".txt", this);
        for (int i = 0; i< loadedData.length; i++){
            if(loadedData[i]. equals(iRoomNumber)){
                // Do nothing
            } else if(i == 1){
                // set text for first name
                etFirstname.setText(String.valueOf(loadedData[i]));
            } else if(i == 2){
                // set text for last name
                etLastName.setText(String.valueOf(loadedData[i]));
            } else if (i == 3){
                // set text for food diet
                etFoodDiet.setText(String.valueOf(loadedData[i]));
            } else if (i == 4){
                // set text for fluid restriction
                etFluidRestric.setText(String.valueOf(loadedData[i]));
            } else if (i == 5){
                // set text for other notes that have been entered.
                etOther.setText(String.valueOf(loadedData[i]));
            } else {
                // debug possible errors.
                log.d(TAGpointer, "Line" + i + ": " + loadedData[i] + "\n");
            }
        }
    }

    /**
     * Check the button input and updated the layout according to what was pressed.
     */
    private void checkInput(){
        // if creating a hallway
        if(iLayout.equals("hall")) {
            // edittext
            EditText etHallName = findViewById(R.id.textHallName);
            strHallName = etHallName.getText().toString();
            EditText etLowNum = findViewById(R.id.textLowNum);
            strLowNum = etLowNum.getText().toString();
            try {
                lowNum = Integer.parseInt(strLowNum);
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_LONG).show();
            }
            EditText etHighNum = findViewById(R.id.textHighNum);
            strHighNum = etHighNum.getText().toString();
            try {
                highNum = Integer.parseInt(strHighNum);
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_LONG).show();
            }
        }
        else if(iLayout.equals("")){

        }

    }

    /**
     * On click for buttons to save/update hall/room information.
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // Save Hallway input
            case R.id.btnSave: {
                String totalNum = "";
                checkInput();
                boolean resultNum = checkNum (lowNum, highNum);
                boolean resultName = checkHallName(strHallName);
                if(resultNum == true && resultName == true){
                    totalNum = intToStr();
                    String filename = getApplicationContext().getFilesDir().getPath().toString() + "rooms.txt";
                    String[] data = {strHallName, totalNum};
                    sAndL.Save( filename,data,this);
                    finish();
                } else {
                    break;
                }
            }
            case R.id.btnCancel: {
                finish();
                break;
            }
            // save/update room input
            case R.id.btn2Update: {
                String roomID, firstName, lastName, foodDiet, fluidRestric, other;
                roomID = checkName(etRoomID.getText().toString());
                firstName = checkName(etFirstname.getText().toString());
                lastName = checkName(etLastName.getText().toString());
                foodDiet = checkName(etFoodDiet.getText().toString());
                fluidRestric = checkName(etFluidRestric.getText().toString());
                other = checkName(etOther.getText().toString());

                String[] data = {roomID, firstName, lastName, foodDiet, fluidRestric, other};
                sAndL.saveRoom(iFilename + iRoomNumber + ".txt", data, this);
                finish();
                break;
            }
            case R.id.btn2Cancel: {
                finish();
                break;
            }
        }
    }

    /**
     * make sure that the input is not null or empty. If the input is null or empty, have it update return "empty
     * @param input = room information
     * @return
     */
    private String checkName(String input) {
        if (input.equals(null)) {
            Toast.makeText(getApplicationContext(), "Error: Name is NULL", Toast.LENGTH_LONG).show();
            return "empty";
        } else if (input.equals("")) {
            Toast.makeText(getApplicationContext(), "Error: Name is blank", Toast.LENGTH_LONG).show();
            return "empty";
        } else {
            return input;
        }
    }

    /**
     * make sure that the hallway name is not null or empty.
     * Yes, it is the same as checkName(), but this is meant to be boolean instead of returning a String.
     * @param name = hakk way bane
     * @return
     */
    private boolean checkHallName (String name){
        name.trim();
        if (name.equals(null)) {
            Toast.makeText(getApplicationContext(), "Error: Name is NULL", Toast.LENGTH_LONG).show();
            return false;
        } else if (name.equals("")){
            Toast.makeText(getApplicationContext(), "Error: Name is blank", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check to make sure that the rooms in the hallway are saved in the correct order. (low can not be greater then or equal to high)
     * @param low = low number
     * @param high = high number
     * @return
     */
    private boolean checkNum (int low, int high){
        if (low < high){
            return true;
        } else if (low == high){
            Toast.makeText(getApplicationContext(), "Error: A hallway with 1 room? are you sure?", Toast.LENGTH_LONG).show();
            return false;
        } else if (low > high){
            Toast.makeText(getApplicationContext(), "Error: Low number is greater than high number", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            Toast.makeText(getApplicationContext(), "Oops! Something went wrong.", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * Create the list of numbers for rooms within the hallway.
     * @return
     */
    private String intToStr (){
        String totalNum = "";
        boolean control = true;
        while(lowNum <= highNum){
            if (control == true){
                totalNum = String.valueOf(lowNum);
                control = false;
            } else {
                totalNum = totalNum + "," + String.valueOf(lowNum);
            }
            totalNum = totalNum + ",empty";
            lowNum++;
        }
        return totalNum;
    }

    /* dialog to save time when creating rooms. Not work like it should, might come back to it.
    private void dialog(){
        // Creating alert Dialog with one Button
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(addRooms.this);

        // Setting Dialog Title
        alertDialog.setTitle("ENTER RESIDENT NAME");

        // Setting Dialog Message
        alertDialog.setMessage("(if you leave the name blank, the room will be assumed empty)");
        final EditText input = new EditText(addRooms.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        rName = input.getText().toString();
                        if(rName.equals("")){
                            rName = "empty";
                        }
                    }
                });
        /*
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });
        // closed
        // Showing Alert Message
        alertDialog.show();
    }
    */

}
