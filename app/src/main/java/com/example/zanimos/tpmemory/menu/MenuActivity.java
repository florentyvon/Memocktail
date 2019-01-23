package com.example.zanimos.tpmemory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/***
 * Menu activity
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class MenuActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button buttonChrono;
    private Button buttonHisto;

    /***
     * onCreate activity event
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu);
        super.onCreate(savedInstanceState);
        initComponents();
    }

    /***
     * onStart activity event
     */
    @Override
    protected void onStart() {
        super.onStart();
        bindEvents();
    }

    /***
     * Event binding method
     */
    private void bindEvents(){
        buttonChrono.setOnClickListener(this);
        buttonHisto.setOnClickListener(this);
    }

    /***
     * Activity components init
     */
    private void initComponents(){
        buttonChrono = (Button) findViewById(R.id.button_chrono);
        buttonHisto = (Button) findViewById(R.id.button_histo);
    }

    /***
     * OnClick event method
     * @param v : current view
     */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.button_game:
                intent = new Intent(getApplicationContext(), GameSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.button_histo:
                intent = new Intent(getApplicationContext(), HistoricActivity.class);
                startActivity(intent);
                break;
        }
    }
}
