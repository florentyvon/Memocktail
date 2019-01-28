package com.example.zanimos.tpmemory.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.zanimos.tpmemory.R;
import com.example.zanimos.tpmemory.infrastructure.BaseActivity;
import com.example.zanimos.tpmemory.menu.MenuActivity;
import com.example.zanimos.tpmemory.services.BackgroundSoundService;

/***
 * Game result activity
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class GameResultActivity extends BaseActivity {

    private ImageView _gameResultImage;
    private ImageButton _backToMenuButton;

    /***
     * onCreate activity event
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);
        initComponents();
    }

    /***
     * onStart activity event
     */
    @Override
    protected void onStart() {
        super.onStart();

        // Set image depending victory
        Bundle bd = getIntent().getExtras();
        if (bd!=null) setImage(bd);

        // Bind event
        _backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iView = new Intent(v.getContext(), MenuActivity.class);
                // Restart sound in menu if sound on
                if(_soundIsOn[0])
                    iView.putExtra("SOUND_RESTART", true);

                stopService(new Intent(v.getContext(), BackgroundSoundService.class));
                startActivity(iView);
                finish();
            }
        });
    }

    /***
     * Display game result image
     * @param bd
     */
    private void setImage(Bundle bd)
    {
        boolean win = bd.getBoolean("WIN");
        Intent intent = new Intent(this, BackgroundSoundService.class);

        if(win) {
            _gameResultImage.setImageResource(R.drawable.win_game);
            intent.putExtra("sound", "win");
        }
        else {
            _gameResultImage.setImageResource(R.drawable.loose_game);
            intent.putExtra("sound", "loose");
        }
        if(_soundIsOn[0])
            startService(intent);
    }

    /***
     * Activity components init
     */
    private void initComponents()
    {
        _gameResultImage = findViewById(R.id.gameResultImageView);
        _backToMenuButton = findViewById(R.id.backToMenuButton);
    }
}