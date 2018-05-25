/**
 * Elim Menu Application
 * Created by:          Tim C.
 * Date Started:        3/1/2018
 * Date Updated:        4/15/2018
 */
package menu.elimcare.elimmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class mainActivity extends AppCompatActivity implements View.OnClickListener {
    // save and load class
    public saveAndLoad sAndL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // call the file check method
        check();
        // Textviews
        TextView orders = findViewById(R.id.orders);
        TextView settings = findViewById(R.id.settings);
        TextView location = findViewById(R.id.location);

        // create on click listeners
        orders.setOnClickListener(this);
        settings.setOnClickListener(this);
        location.setOnClickListener(this);
    }

    /**
     * Check to see if files have been created.
     */
    public void check(){
        // Number of files
        int fileNum = 2;
        // File names
        String[] fileNames = {"rooms", "menu"};
        // Stirng, if the files need to be created load empty data.
        /** vvvvvvvv No longer needed vvvvvv But I will keep it in for now and remove it latter vvvvvvvvvv*/
        String[] fileContents = {"Hallway1", "102    ,empty   ,103, empty,104,    empty a  ", "Hallway2", "202,   empty,203 ,empty,  204,empty"};
        // Check File
        sAndL = new saveAndLoad(this, fileNum, fileNames, fileContents);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            // load orders class
            case R.id.orders: {
                Intent iOrder = new Intent(this, orderField.class);
                startActivity(iOrder);
                // Toast.makeText(getApplicationContext(),"Test press on textview",Toast.LENGTH_LONG).show();
                break;
            }
            // load settings class
            case R.id.settings: {
                Intent iSettings = new Intent(this, settings.class);
                startActivity(iSettings);
                break;
            }
            case R.id.location: {
                Intent iLocation = new Intent(this, location.class);
                startActivity(iLocation);
                // Toast.makeText(getApplicationContext(),"Test press on textview",Toast.LENGTH_LONG).show();
            }
        }
    }
}

/* This is here just incase I need to make a toolbar for this app.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

 /* This is just here for debugging save data problems.
    public void readfile(Context context){
        // read to see if files are there, if they aren't. Create files.
        try {
            String filename = context.getFilesDir().getPath().toString() + "/westrooms.txt";
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
                Toast.makeText(context,"Creating files for use...",Toast.LENGTH_LONG).show();
                // write code for saving data to the file
            }
        } catch (IOException e) {
            Toast.makeText(context.getApplicationContext(),"Error with save data: " + e,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    */