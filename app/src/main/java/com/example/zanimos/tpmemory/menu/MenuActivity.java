package com.example.zanimos.tpmemory.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zanimos.tpmemory.services.BackgroundSound;
import com.example.zanimos.tpmemory.game.GameSettingsActivity;
import com.example.zanimos.tpmemory.R;
import com.example.zanimos.tpmemory.historic.HistoricActivity;

/***
 * Menu activity
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class MenuActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button buttonGame;
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
        buttonGame.setOnClickListener(this);
        buttonHisto.setOnClickListener(this);
    }

    /***
     * Activity components init
     */
    private void initComponents(){
        buttonGame = (Button) findViewById(R.id.button_game);
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
