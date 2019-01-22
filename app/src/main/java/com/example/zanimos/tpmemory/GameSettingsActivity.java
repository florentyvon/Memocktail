package com.example.zanimos.tpmemory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

public class GameSettingsActivity extends AppCompatActivity {

    private Spinner _spinnerCocktails;
    private Spinner _spinnerDifficulty;
    private Spinner _spinnerGameMode;
    private ImageView _cocktailImage;
    private ImageButton _playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setSpinners();
        bindEvents();
    }

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

                // Start Game Activity with parameters
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtras(bd);
                startActivity(i);
            }
        });
    }

    private void initComponents(){
        _spinnerCocktails = findViewById(R.id.spinnerCocktailToPlay);
        _spinnerDifficulty = findViewById(R.id.spinnerDifficultyToPlay);
        _spinnerGameMode = findViewById(R.id.spinnerGameModeToPlay);
        _cocktailImage = findViewById(R.id.imageViewCocktailToPlay);
        _playButton = findViewById(R.id.playButton);
    }

    private void setSpinners(){
        String[] cocktails = new String[] {"Virgin Daiquiri", "Virgin Mojito", "Virgin Pina Colada"};
        String[] difficulties = new String[] {getString(R.string.easy), getString(R.string.hard)};
        String[] modes = new String[] {getString(R.string.classic), getString(R.string.atc)};

        ArrayAdapter<String> adapterCocktails = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, cocktails);
        adapterCocktails.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerCocktails.setAdapter(adapterCocktails);

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, difficulties);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerDifficulty.setAdapter(adapterDifficulty);

        ArrayAdapter<String> adapterGameMode = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, modes);
        adapterGameMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerGameMode.setAdapter(adapterGameMode);
    }
}
