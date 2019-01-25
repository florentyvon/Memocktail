package com.example.zanimos.tpmemory.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.zanimos.tpmemory.R;
import com.example.zanimos.tpmemory.infrastructure.BaseActivity;
import com.example.zanimos.tpmemory.menu.MenuActivity;
import com.example.zanimos.tpmemory.services.BackgroundSoundService;
import com.example.zanimos.tpmemory.services.SoundEffectsService;

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
        setImage(bd);

        // Bind event
        _backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iView = new Intent(v.getContext(), MenuActivity.class);
                // Restart sound in menu if sound on
                if(_soundIsOn[0])
                    iView.putExtra("SOUND_RESTART", true);

                stopService(new Intent(v.getContext(), SoundEffectsService.class));
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
        Intent intent = new Intent(this, SoundEffectsService.class);

        if(win) {
            _gameResultImage.setImageResource(R.drawable.win_game);
            intent.putExtra("sound", "win");
        }
        else {
            _gameResultImage.setImageResource(R.drawable.loose_game);
            intent.putExtra("sound", "loose");
        }

        if(_soundIsOn[1])
            startService(intent);
        else
            intent = null;
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