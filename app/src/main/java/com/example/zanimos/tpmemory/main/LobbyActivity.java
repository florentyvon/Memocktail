package com.example.zanimos.tpmemory.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
<<<<<<< refs/remotes/origin/master
        Intent intent = new Intent(this, BackgroundSoundService.class);
        intent.putExtra("sound","lobby");
        startService(intent);
=======
        startService(new Intent(this, BackgroundSoundService.class));
>>>>>>> rename sound service
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
        return true;
    }

    /***
     * onOptionsItemSelected toolbar event
     * @param item
     * @return boolean : success
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.volumeON) {
            ActionMenuItemView vOff = (ActionMenuItemView) findViewById(R.id.volumeOFF);
            item.setVisible(false);
            vOff.setVisibility(View.VISIBLE);
            Toast.makeText(LobbyActivity.this, "volume off", Toast.LENGTH_LONG).show();
        } else {
            MenuItem vOn = (MenuItem) findViewById(R.id.volumeON);
            item.setVisible(false);
            vOn.setVisible(true);
            Toast.makeText(LobbyActivity.this, "volume off", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
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
    }
}