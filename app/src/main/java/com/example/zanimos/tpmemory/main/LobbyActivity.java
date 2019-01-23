package com.example.zanimos.tpmemory.main;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.zanimos.tpmemory.AProposActivity;
import com.example.zanimos.tpmemory.R;
import com.example.zanimos.tpmemory.services.BackgroundSoundService;
import com.example.zanimos.tpmemory.infrastructure.SharedPreferencesManager;
import com.example.zanimos.tpmemory.menu.MenuActivity;

/***
 * Lobby activity (main activity)
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class LobbyActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private Toolbar mTopToolbar;
    private SharedPreferencesManager _preferencesManager;
    private Menu _menu;
    private boolean _soundIsOn = true;

    /***
     * onCreate activity event
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        initComponents();
        setSupportActionBar(mTopToolbar);
    }

    /***
     * onStart activity event
     */
    @Override
    protected void onStart() {
        super.onStart();

        // Init SharedPreferencesManager for all app
        SharedPreferencesManager.Instance(getApplicationContext());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
        if(_soundIsOn) {
            Intent intent = new Intent(this, BackgroundSoundService.class);
            intent.putExtra("sound", "lobby");
            startService(intent);
        }
    }

    /***
     * onCreateOptionsMenu toolbar event
     * @param menu
     * @return boolean : success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        _menu = menu;
        return true;
    }

    /***
     * onOptionsItemSelected toolbar event
     * @param item
     * @return boolean : success
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.volumeON:
                stopService(new Intent(this, BackgroundSoundService.class));

                item.setVisible(false);
                _menu.getItem(1).setVisible(true);

                _soundIsOn = false;
                break;
            case R.id.volumeOFF:
                Intent intent = new Intent(this, BackgroundSoundService.class);
                intent.putExtra("sound","lobby");
                startService(intent);

                item.setVisible(false);
                _menu.getItem(0).setVisible(true);

                _soundIsOn = true;
                break;
            case R.id.aPropos:
                Intent i = new Intent(this, AProposActivity.class);
                startActivity(i);
                break;
            default :
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop(){
        super.onStop();
        stopService(new Intent(this, BackgroundSoundService.class));
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        stopService(new Intent(this, BackgroundSoundService.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(this, BackgroundSoundService.class));
    }

    /***
     * Activity components init
     */
    private void initComponents(){
        layout = (ConstraintLayout) findViewById(R.id.Layout);
        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
    }
}