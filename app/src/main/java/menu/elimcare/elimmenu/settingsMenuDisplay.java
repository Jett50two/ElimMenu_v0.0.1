package menu.elimcare.elimmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class settingsMenuDisplay extends AppCompatActivity implements View.OnClickListener {
    public static Log log;
    private static final String TAGclick = "Click control";

    Intent iMenu;
    TextView newMenu, editMenu, disMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu_display);

        setButtons();
    }

    public void setButtons(){
        newMenu = findViewById(R.id.newMenu);
        newMenu.setOnClickListener(this);

        editMenu = findViewById(R.id.editMenu);
        editMenu.setOnClickListener(this);

        disMenu = findViewById(R.id.disMenu);
        disMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        iMenu = new Intent(this, settingsMenu.class);
        switch(view.getId()){
            case R.id.newMenu: {
                log.d(TAGclick, "New Menu clicked");
                iMenu.putExtra("menu", "new");
                startActivity(iMenu);
                break;
            }
            case R.id.editMenu: {
                log.d(TAGclick, "Edit Menu clicked");
                iMenu.putExtra("menu", "edit");
                startActivity(iMenu);
                break;
            }
            case R.id.disMenu: {
                log.d(TAGclick, "Display Menu clicked");
                iMenu.putExtra("menu", "display");
                startActivity(iMenu);
                break;
            }
        }
    }
}
