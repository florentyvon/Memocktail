package com.example.zanimos.tpmemory.infrastructure;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.zanimos.tpmemory.AProposActivity;
import com.example.zanimos.tpmemory.R;
import com.example.zanimos.tpmemory.services.BackgroundSoundService;
import com.example.zanimos.tpmemory.services.SoundEffectsService;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class BaseActivity extends AppCompatActivity {

    private Menu _menu;
    //private ArrayList<Integer> mSelectedItems;
    /*
     _soundIsOn[0] => background sound
     _soundIsOn[0] => sound effect
      */
    protected boolean[] _soundIsOn = new boolean[2];
    protected SharedPreferencesManager preferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferencesManager = SharedPreferencesManager.Instance(getApplicationContext());
        _soundIsOn[0] = preferencesManager.readSettingTokenValue("volume-background");
        _soundIsOn[1] = preferencesManager.readSettingTokenValue("volume-sound_effects");
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
        switch (item.getItemId()) {
            case R.id.volumeON:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                AlertDialog dialog;
                // Set up sound set up stored values
                _soundIsOn[0] = preferencesManager.readSettingTokenValue("volume-background");
                _soundIsOn[1] = preferencesManager.readSettingTokenValue("volume-sound_effects");

                builder.setTitle("Modifier Volume")
                        .setMultiChoiceItems(R.array.volumes, _soundIsOn,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which,
                                                        boolean isChecked) {
                                        _soundIsOn[which] = isChecked;
                                    }
                                })
                        // Set the action buttons
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                setVolumes();
                            }
                        })
                        .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                return;
                            }
                        });
                dialog = builder.create();
                dialog.show();
                break;
            case R.id.aPropos:
                Intent i = new Intent(this, AProposActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, BackgroundSoundService.class));
    }

    private void setVolumes() {
        // Set background sound
        if (_soundIsOn[0]) {
            preferencesManager.writeSettingTokenValue("volume-background", true);
            Intent intent = new Intent(this, BackgroundSoundService.class);
            intent.putExtra("sound", "lobby");
            startService(intent);
        } else {
            preferencesManager.writeSettingTokenValue("volume-background", false);
            stopService(new Intent(this, BackgroundSoundService.class));
        }

        // Set sound effects
        if (_soundIsOn[1]) {
            preferencesManager.writeSettingTokenValue("volume-sound_effects", true);
        } else {
            preferencesManager.writeSettingTokenValue("volume-background", false);
            stopService(new Intent(this, BackgroundSoundService.class));
        }
    }
}