package menu.elimcare.elimmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Omega on 5/10/2018.
 */

public class orderField extends AppCompatActivity implements View.OnClickListener{

    Log log;
    String[] iLocation, iOldData, sendOldData;
    String lay1Boxes, lay2Boxes, lay3Boxes, lay4Boxes;
    boolean iExtra;
    TextView tvName, tvRoomNum, tvDiet, tvPrimaryFood, tvSide, tvDrink, tvDesserts;
    LinearLayout layDiet, layPrimaryFood, laySide, layDrink, layDessert, layNameNum, layScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_field);

        setValues();
        // Get extras from the intent
        Bundle extras = getIntent().getExtras();
        // make sure there is an extra in the intent
        if(extras == null) {
            log.d("extras is null","There are no extras"  + "\n\n");
        } else {
            checkBundles(extras);
        }
    }

    private void checkBundles(Bundle extras) {
        iLocation = extras.getStringArray("roomInfo");
        if(iLocation != null) {
            tvRoomNum.setText(String.valueOf("Room # " + iLocation[0]));
            tvName.setText(String.valueOf(iLocation[1] + ", " + iLocation[2]));
            tvDiet.setText(String.valueOf("Food Restriction:\n\t" + iLocation[3]
            + "\nFluid Restriction:\n\t" + iLocation[4]
            + "\nOther Notes:\n\t" + iLocation[5]));
        }
        updateValues(extras);
        log.d("extras has info","extras: " + iLocation + "\n\n");
    }

    private void updateValues(Bundle extras){
        iOldData = extras.getStringArray("oldData");
        if(iOldData != null){
            tvPrimaryFood.setText(iOldData[0]);
            tvSide.setText(iOldData[1]);
            tvDrink.setText(iOldData[2]);
            tvDesserts.setText(iOldData[3]);
        }
        lay1Boxes = extras.getString("lay1Boxes");
        if(lay1Boxes != null){
            tvPrimaryFood.setText(lay1Boxes);
        }
        lay2Boxes = extras.getString("lay2Boxes");
        if(lay2Boxes != null){
            tvSide.setText(lay2Boxes);
        }
        lay3Boxes = extras.getString("lay3Boxes");
        if(lay3Boxes != null){
            tvDrink.setText(lay3Boxes);
        }
        lay4Boxes = extras.getString("lay4Boxes");
        if(lay4Boxes != null){
            tvDesserts.setText(lay4Boxes);
        }
    }

    private void setValues(){
        tvName = findViewById(R.id.tvName);
        tvRoomNum = findViewById(R.id.tvRoomNum);
        layNameNum = findViewById(R.id.layNameNum);
        layScroll = findViewById(R.id.layScroll);
        tvDiet = findViewById(R.id.tvDiet);
        tvPrimaryFood = findViewById(R.id.tvPrimary);
        layPrimaryFood = findViewById(R.id.layPrimary);
        tvSide = findViewById(R.id.tvSides);
        laySide = findViewById(R.id.laySides);
        tvDrink = findViewById(R.id.tvDrink);
        layDrink = findViewById(R.id.layDrink);
        tvDesserts = findViewById(R.id.tvDesserts);
        layDessert = findViewById(R.id.layDesserts);


        layNameNum.setOnClickListener(this);
        layScroll.setOnClickListener(this);
        layPrimaryFood.setOnClickListener(this);
        laySide.setOnClickListener(this);
        layDrink.setOnClickListener(this);
        layDessert.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent iMenuControl;
        switch (view.getId()){
            case R.id.layNameNum:{
                iMenuControl = new Intent(this, location.class);
                iMenuControl.putExtra("location", "location");
                iMenuControl.putExtra("oldRoomInfo", iLocation);
                sendOldData = new String[]{tvPrimaryFood.getText().toString(), tvSide.getText().toString(),
                                            tvDrink.getText().toString(), tvDesserts.getText().toString()};
                iMenuControl.putExtra("oldData", sendOldData);
                startActivity(iMenuControl);
                // Toast.makeText(getApplicationContext(),"name and number textview",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.layScroll:{
                iMenuControl = new Intent(this, location.class);
                iMenuControl.putExtra("location", "location");
                iMenuControl.putExtra("oldRoomInfo", iLocation);
                sendOldData = new String[]{tvPrimaryFood.getText().toString(), tvSide.getText().toString(),
                        tvDrink.getText().toString(), tvDesserts.getText().toString()};
                iMenuControl.putExtra("oldData", sendOldData);
                startActivity(iMenuControl);
                // Toast.makeText(getApplicationContext(),"name and number textview",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.layPrimary: {
                iMenuControl = new Intent(this, menu.class);
                iMenuControl.putExtra("menuType", "primary");
                iMenuControl.putExtra("roomInfo", iLocation);
                sendOldData = new String[]{tvPrimaryFood.getText().toString(), tvSide.getText().toString(),
                        tvDrink.getText().toString(), tvDesserts.getText().toString()};
                iMenuControl.putExtra("oldData", sendOldData);
                startActivity(iMenuControl);
                // Toast.makeText(getApplicationContext(),"primary foods Layout",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.laySides: {
                iMenuControl = new Intent(this, menu.class);
                iMenuControl.putExtra("menuType", "sides");
                iMenuControl.putExtra("roomInfo", iLocation);
                sendOldData = new String[]{tvPrimaryFood.getText().toString(), tvSide.getText().toString(),
                        tvDrink.getText().toString(), tvDesserts.getText().toString()};
                iMenuControl.putExtra("oldData", sendOldData);
                startActivity(iMenuControl);
                // Toast.makeText(getApplicationContext(),"sides Layout",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.layDrink: {
                iMenuControl = new Intent(this, menu.class);
                iMenuControl.putExtra("menuType", "drink");
                iMenuControl.putExtra("roomInfo", iLocation);
                sendOldData = new String[]{tvPrimaryFood.getText().toString(), tvSide.getText().toString(),
                        tvDrink.getText().toString(), tvDesserts.getText().toString()};
                iMenuControl.putExtra("oldData", sendOldData);
                startActivity(iMenuControl);
                // Toast.makeText(getApplicationContext(),"drink Layout",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.layDesserts: {
                iMenuControl = new Intent(this, menu.class);
                iMenuControl.putExtra("menuType", "dessert");
                iMenuControl.putExtra("roomInfo", iLocation);
                sendOldData = new String[]{tvPrimaryFood.getText().toString(), tvSide.getText().toString(),
                        tvDrink.getText().toString(), tvDesserts.getText().toString()};
                iMenuControl.putExtra("oldData", sendOldData);
                startActivity(iMenuControl);
                // Toast.makeText(getApplicationContext(),"dessert Layout",Toast.LENGTH_LONG).show();
                break;
            }

        }
    }
}
