package menu.elimcare.elimmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Tim C. on 3/4/2018.
 */

public class settings extends AppCompatActivity implements View.OnClickListener {
    TextView rooms, menu, numOfTables;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        viewButtons();
    }

    /**
     * Set the buttons
     */
    public void viewButtons(){
        rooms = findViewById(R.id.rooms);
        rooms.setOnClickListener(this);
        menu = findViewById(R.id.menu);
        menu.setOnClickListener(this);
        numOfTables = findViewById(R.id.numOfTables);
        numOfTables.setOnClickListener(this);
    }

    /**
     * On Click
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rooms: {
                Intent iRooms = new Intent(this, SettingRooms.class);
                startActivity(iRooms);
                break;
            }case R.id.menu: {
                Intent iMenu = new Intent(this, SettingsMenu.class);
                startActivity(iMenu);
                break;
            }case R.id.numOfTables: {
                Intent iTables = new Intent(this, SettingsTables.class);
                startActivity(iTables);
                break;
            }
        }
    }
}
