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
public class LobbyActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private Toolbar mTopToolbar;
    private SharedPreferencesManager _preferencesManager;
    private Menu _menu;
    private boolean[] _soundIsOn = new boolean[2];
    private ArrayList<Integer> mSelectedItems;

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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                AlertDialog dialog;
                mSelectedItems = new ArrayList();
                builder.setTitle("Modifier Volume")
                        .setMultiChoiceItems(R.array.volumes, _soundIsOn,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which,
                                                        boolean isChecked) {
                                        if (isChecked) {
                                            // If the user checked the item, add it to the selected items
                                            mSelectedItems.add(which);
                                        } else if (mSelectedItems.contains(which)) {
                                            // Else, if the item is already in the array, remove it
                                            mSelectedItems.remove(Integer.valueOf(which));
                                        }
                                    }
                                })
                        // Set the action buttons
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                setVolumes(mSelectedItems);
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
            default :
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        overridePendingTransition(R.anim.slide_forward,R.anim.slide_back);
        super.onResume();
        if(_soundIsOn[0]) {
            Intent intent = new Intent(this, BackgroundSoundService.class);
            intent.putExtra("sound", "lobby");
            startService(intent);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        stopService(new Intent(this, BackgroundSoundService.class));
    }

    /***
     * Activity components init
     */
    private void initComponents(){
        layout = (ConstraintLayout) findViewById(R.id.Layout);
        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        Arrays.fill(_soundIsOn, true);
    }

    private void setVolumes(ArrayList<Integer> volumes){
        if(volumes.contains(0)){
            _soundIsOn[0] = true;
            Intent intent = new Intent(this, BackgroundSoundService.class);
            intent.putExtra("sound", "lobby");
            startService(intent);
        } else if(!volumes.contains(0)){
            _soundIsOn[0] = false;
            stopService(new Intent(this, BackgroundSoundService.class));
        }
        if(volumes.contains(1)){
            _soundIsOn[1] = true;
        } else if(!volumes.contains(1)) {
            _soundIsOn[1] = false;
        }
    }
}