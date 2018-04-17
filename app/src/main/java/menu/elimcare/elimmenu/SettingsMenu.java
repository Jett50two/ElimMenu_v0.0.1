package menu.elimcare.elimmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim C. on 3/4/2018.
 */

public class SettingsMenu extends AppCompatActivity {

    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);

        addItemsToSpin();
    }

    public void addItemsToSpin() {
        spin = (Spinner) findViewById(R.id.spinner);
        List<Integer> list = new ArrayList<Integer>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);
    }
}
