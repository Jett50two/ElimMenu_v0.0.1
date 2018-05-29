package menu.elimcare.elimmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Omega on 3/2/2018.
 */

public class orderField extends AppCompatActivity implements View.OnClickListener{

    Log log;
    String[] iLocation, iOldData;
    boolean iExtra;
    TextView tvName, tvRoomNum, tvDiet, tvPrimaryFood, tvSide, tvDrink, tvDesserts;
    LinearLayout layDiet, layPrimaryFood, laySide, layDrink, layDessert, layNameNum;

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
        iOldData = extras.getStringArray("getOld");
        log.d("extras has info","extras: " + iLocation + "\n\n");
    }

    private void setValues(){
        tvName = findViewById(R.id.tvName);
        tvRoomNum = findViewById(R.id.tvRoomNum);
        layNameNum = findViewById(R.id.layNameNum);
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
                iMenuControl.putExtra("roomInfo", "location");
                startActivity(iMenuControl);
                Toast.makeText(getApplicationContext(),"name and number textview",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.layPrimary: {
                iMenuControl = new Intent(this, menu.class);
                iMenuControl.putExtra("menuType", "primary");
                startActivity(iMenuControl);
                Toast.makeText(getApplicationContext(),"primary foods Layout",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.laySides: {
                iMenuControl = new Intent(this, menu.class);
                iMenuControl.putExtra("menuType", "sides");
                startActivity(iMenuControl);
                Toast.makeText(getApplicationContext(),"sides Layout",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.layDrink: {
                iMenuControl = new Intent(this, menu.class);
                iMenuControl.putExtra("menuType", "drink");
                startActivity(iMenuControl);
                Toast.makeText(getApplicationContext(),"drink Layout",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.layDesserts: {
                iMenuControl = new Intent(this, menu.class);
                iMenuControl.putExtra("menuType", "dessert");
                startActivity(iMenuControl);
                Toast.makeText(getApplicationContext(),"dessert Layout",Toast.LENGTH_LONG).show();
                break;
            }

        }
    }
}
