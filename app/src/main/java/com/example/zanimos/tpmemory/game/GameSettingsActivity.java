package com.example.zanimos.tpmemory.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.zanimos.tpmemory.R;
import com.example.zanimos.tpmemory.infrastructure.BaseActivity;
import com.example.zanimos.tpmemory.services.BackgroundSoundService;

/***
 * Game settings activity
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class GameSettingsActivity extends BaseActivity {

    private Spinner _spinnerCocktails;
    private Spinner _spinnerDifficulty;
    private Spinner _spinnerGameMode;
    private ImageView _cocktailImage;
    private ImageButton _playButton;

    /***
     * onCreate activity event
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);
        initComponents();
    }

    /***
     * onStart activity event
     */
    @Override
    protected void onStart() {
        super.onStart();
        setSpinners();
        bindEvents();
    }

    /***
     * Activity components init
     */
    private void initComponents(){
        _spinnerCocktails = findViewById(R.id.spinnerCocktailToPlay);
        _spinnerDifficulty = findViewById(R.id.spinnerDifficultyToPlay);
        _spinnerGameMode = findViewById(R.id.spinnerGameModeToPlay);
        _cocktailImage = findViewById(R.id.imageViewCocktailToPlay);
        _playButton = findViewById(R.id.playButton);
    }

    /***
     * Event binding method
     */
    private void bindEvents(){
        _spinnerCocktails.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(_spinnerCocktails.getSelectedItem().toString())
                {
                    case "Virgin Daiquiri":
                        _cocktailImage.setImageResource(R.drawable.img_virgin_daiquiri);
                        break;
                    case "Virgin Mojito":
                        _cocktailImage.setImageResource(R.drawable.img_virgin_mojito);
                        break;
                    case "Virgin Pina Colada":
                        _cocktailImage.setImageResource(R.drawable.img_virgin_pina_colada);
                        break;
                }

            }

            // Don't need this
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        _playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cocktailSelected = (String) _spinnerCocktails.getSelectedItem();
                String gameModeSelected = (String) _spinnerGameMode.getSelectedItem();
                String difficultySelected = (String) _spinnerDifficulty.getSelectedItem();

                // Set parameters to game
                Bundle bd = new Bundle();
                bd.putString("COCKTAIL", cocktailSelected);
                bd.putString("GAME_MODE", gameModeSelected);
                bd.putString("DIFFICULTY", difficultySelected);

                // check if sound is already on and stop it and then play another one in setGameMode method
                if(_soundIsOn[0])
                    stopService(new Intent(getApplicationContext(), BackgroundSoundService.class));

                // Start Game Activity with parameters
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtras(bd);
                startActivity(i);
            }
        });
    }

    /***
     * Spinners setting
     */
    private void setSpinners(){
        String[] cocktails = new String[] {"Virgin Daiquiri", "Virgin Mojito", "Virgin Pina Colada"};
        String[] difficulties = new String[] {getString(R.string.easy), getString(R.string.hard)};
        String[] modes = new String[] {getString(R.string.classic), getString(R.string.atc)};

        ArrayAdapter<String> adapterCocktails = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, cocktails);
        adapterCocktails.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerCocktails.setAdapter(adapterCocktails);

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, difficulties);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerDifficulty.setAdapter(adapterDifficulty);

        ArrayAdapter<String> adapterGameMode = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, modes);
        adapterGameMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerGameMode.setAdapter(adapterGameMode);
    }
}