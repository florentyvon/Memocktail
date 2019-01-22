package com.example.zanimos.tpmemory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    private SharedPreferencesManager _preferencesManager;
    private CardFragment[] _cardsSelected;
    private GridLayout _cardsGrid;

    private int _nbPairToPlay;
    private String _difficulty;
    private String _cocktail;
    private String _gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();

        Bundle bd = getIntent().getExtras();
        if(bd!=null){
            _difficulty = bd.getString("DIFFICULTY");
            _cocktail = bd.getString("COCKTAIL");
            _gameMode = bd.getString("GAME_MODE");

            setDifficulty(_difficulty);
            setCocktail(_cocktail);
            setGameMode(_gameMode);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        _cardsSelected = new CardFragment[2];

        // Get SharedPreferencesManager Instance
        _preferencesManager = SharedPreferencesManager.Instance(getApplicationContext());
        _preferencesManager.incrementTokenValue("played",_difficulty,_cocktail,_gameMode);
    }

    private void setDifficulty(String difficulty)
    {
        switch(difficulty)
        {
            case "Facile":
                // 12 cards
                _cardsGrid.setColumnCount(3);
                _cardsGrid.setRowCount(4);
                _nbPairToPlay = 6;
                break;
            case "Difficile":
                // 16 cards
                _cardsGrid.setColumnCount(4);
                _cardsGrid.setRowCount(4);
                _nbPairToPlay = 8;
                break;
            default:
                // 12 cards
                _cardsGrid.setColumnCount(4);
                _cardsGrid.setRowCount(3);
                _nbPairToPlay = 6;
                break;
        }
    }

    private void setCocktail(String cocktail)
    {
        switch (cocktail)
        {
            case "Virgin Daiquiri":
                fillGridLayout(initCardList(R.drawable.img_virgin_daiquiri));
                break;
            case "Virgin Mojito":
                fillGridLayout(initCardList(R.drawable.img_virgin_mojito));
                break;
            case "Virgin Pina Colada":
                fillGridLayout(initCardList(R.drawable.img_virgin_pina_colada));
                break;
            default :
                fillGridLayout(initCardList(R.drawable.img_virgin_mojito));
                break;
        }
    }

    private void setGameMode(String gameMode)
    {
        if(("Difficile").equals(gameMode))
        {
            // TODO : lancer service chrono
        }
    }

    public void compareCardSelected(CardFragment clickedCard)
    {
        if(_cardsSelected[0] != null && _cardsSelected[0].hashCode() == clickedCard.hashCode()) return;

        if(_cardsSelected[0]!= null && _cardsSelected[1] != null){
            // hide selected cards
            _cardsSelected[0].setImageVisibility(false);
            _cardsSelected[1].setImageVisibility(false);
            //reset selected cards
            _cardsSelected[0] = null;
            _cardsSelected[1] = null;
        }

        clickedCard.setImageVisibility(true);

        if(_cardsSelected[0] == null)
        {
            // no card already selected
            _cardsSelected[0] = clickedCard;
        }
        else
        {
            _cardsSelected[1] = clickedCard;
            if(_cardsSelected[0].get_imageId() == _cardsSelected[1].get_imageId()) {
                --_nbPairToPlay;
                _cardsSelected[0].setCardFound();
                _cardsSelected[1].setCardFound();

                if (_nbPairToPlay == 0) {
                    finishGame(true);
                }
            }
        }
    }

    private void finishGame(boolean win)
    {
        Toast.makeText(this, "End Game!", Toast.LENGTH_SHORT).show();
        if(win)
        {
            _preferencesManager.incrementTokenValue("victory",_difficulty,_cocktail,_gameMode);
        }

        // Back to Menu
        Intent i = new Intent(this.getApplicationContext(), MenuActivity.class);
        startActivity(i);
        finish();
    }

    private void initComponents()
    {
        _cardsGrid = (GridLayout) findViewById(R.id.cardsGrid);
        _cardsGrid.setAlignmentMode(GridLayout.ALIGN_MARGINS);
    }

    private void fillGridLayout(ArrayList<CardFragment> cardsToPlay)
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction;

        _cardsGrid.removeAllViews();

        fragmentTransaction = fragmentManager.beginTransaction();
        for(CardFragment cf : cardsToPlay)
        {
            fragmentTransaction.add(_cardsGrid.getId(),cf,null);
        }
        fragmentTransaction.commit();
    }

    private ArrayList<CardFragment> initCardList(int cocktailId)
    {
        ArrayList cards = new ArrayList<CardFragment>();
        int[] images = new int[6];
        switch(cocktailId)
        {
            case R.drawable.img_virgin_daiquiri :
                //load virgin daiquiri images
                images[0] = R.drawable.eau_gazeuse;
                images[1] = R.drawable.fraise;
                images[2] = R.drawable.glacon;
                images[3] = R.drawable.sucre_canne;
                images[4] = R.drawable.citron_vert;
                images[5] = R.drawable.menthe;

                break;
            case R.drawable.img_virgin_mojito :
                //load virgin mojito images
                images[0] = R.drawable.citron_vert;
                images[1] = R.drawable.glacon;
                images[2] = R.drawable.eau_gazeuse;
                images[3] =  R.drawable.menthe;
                images[4] = R.drawable.sucre_canne;
                images[5] = R.drawable.applejuice;
                break;
            case R.drawable.img_virgin_pina_colada :
                //load virgin pina colada images
                images[0] = R.drawable.ananas;
                images[1] = R.drawable.noix_coco;
                images[2] = R.drawable.glacon;
                images[3] = R.drawable.sucre_canne;
                images[4] = R.drawable.cerise;
                images[5] = R.drawable.lemon;
                break;
            default:
                break;
        }

        for(int i=0; i<_nbPairToPlay; i++){
            GenerateCardPair(images[i%6], cards);
        }

        Collections.shuffle(cards);
        return cards;
    }

    private void GenerateCardPair(int imageId, ArrayList<CardFragment> cards)
    {
        CardFragment cf = CardFragment.Instantiate(imageId);
        cards.add(cf);
        cf = CardFragment.Instantiate(imageId);
        cards.add(cf);
    }
}
