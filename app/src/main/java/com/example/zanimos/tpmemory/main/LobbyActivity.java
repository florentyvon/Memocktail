package com.example.zanimos.tpmemory.main;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.zanimos.tpmemory.infrastructure.BaseActivity;
import com.example.zanimos.tpmemory.services.BackgroundSoundService;
import com.example.zanimos.tpmemory.infrastructure.SharedPreferencesManager;
import com.example.zanimos.tpmemory.menu.MenuActivity;
import com.example.zanimos.tpmemory.services.SoundEffectsService;

import java.util.ArrayList;
import java.util.Arrays;

/***
 * Lobby activity (main activity)
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class LobbyActivity extends BaseActivity {

    private ConstraintLayout layout;

    /***
     * onCreate activity event
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        if(super._soundIsOn[0]) {
            Intent intent = new Intent(this, BackgroundSoundService.class);
            intent.putExtra("sound", "lobby");
            startService(intent);
        }

        initComponents();
    }

    @Override
    protected void onResume(){
        overridePendingTransition(R.anim.slide_forward,R.anim.slide_back);
        super.onResume();
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
    }

    /***
     * Activity components init
     */
    private void initComponents(){
        layout = (ConstraintLayout) findViewById(R.id.Layout);
    }
}