package menu.elimcare.elimmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by TIm C. on 3/3/2018.
 */

public class menu extends AppCompatActivity {

    public CheckBox[] chkArray = new CheckBox[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);



        chkArray[0] = findViewById(R.id.checkBox0);
        chkArray[0].setOnClickListener(mListener);
        chkArray[1] = findViewById(R.id.checkBox1);
        chkArray[1].setOnClickListener(mListener);
        chkArray[2] = findViewById(R.id.checkBox2);
        chkArray[2].setOnClickListener(mListener);
        chkArray[3] = findViewById(R.id.checkBox3);
        chkArray[3].setOnClickListener(mListener);
        chkArray[4] = findViewById(R.id.checkBox4);
        chkArray[4].setOnClickListener(mListener);
        chkArray[5] = findViewById(R.id.checkBox5);
        chkArray[5].setOnClickListener(mListener);
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int checkedId = v.getId();
            for (int i = 0; i < chkArray.length; i++) {
                final CheckBox current = chkArray[i];
                if (current.getId() == checkedId) {
                    current.setChecked(true);
                } else {
                    current.setChecked(false);
                }
            }
        }
    };
}
