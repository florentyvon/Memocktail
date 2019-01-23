package com.example.zanimos.tpmemory.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zanimos.tpmemory.R;
import com.example.zanimos.tpmemory.menu.MenuActivity;

/***
 * Game result activity
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class GameResultActivity extends AppCompatActivity {

    private ImageView _gameResultImage;
    private Button _backToMenuButton;

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

        Bundle bd = getIntent().getExtras();
        setImage(bd);

        _backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MenuActivity.class);
                startActivity(i);
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

        if(win) _gameResultImage.setImageResource(R.drawable.win_game);
        else _gameResultImage.setImageResource(R.drawable.loose_game);
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