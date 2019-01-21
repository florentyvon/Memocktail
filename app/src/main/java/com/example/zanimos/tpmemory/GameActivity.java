package com.example.zanimos.tpmemory;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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

    private CardFragment[] _cardsSelected;
    private GridLayout _cardsGrid;
    private int _nbPairToPlay;
    private String _preferencesVictoryKey;
    private String _preferencesGamePlayedKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initComponents(4,3);
        _nbPairToPlay = 6;
        _cardsSelected = new CardFragment[2];
        //victory/played - cocktail - gamemode - difficulty
        _preferencesVictoryKey = "victory-virgin_daiquiri-classique-normal";
        _preferencesGamePlayedKey = "played-virgin_daiquiri-classique-normal";

        fillGridLayout(initCardList(R.drawable.img_virgin_daiquiri));
    }

    @Override
    protected void onStart(){
        super.onStart();
        storeGameStart();
    }

    private void storeGameStart()
    {
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int gamePlayed = prefs.getInt(_preferencesGamePlayedKey, 0);
        ++gamePlayed;
        editor.putInt(_preferencesGamePlayedKey, gamePlayed);
        editor.apply();
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
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int score;
        if(win)
        {
            score = prefs.getInt(_preferencesVictoryKey, 0);
            ++score;
            editor.putInt(_preferencesVictoryKey, score);
            // TODO : difference with editor.commit() ?
            editor.apply();
        }
    }

    private void initComponents(int row, int col)
    {
        _cardsGrid = (GridLayout) findViewById(R.id.cardsGrid);
        _cardsGrid.setColumnCount(col);
        _cardsGrid.setRowCount(row);
    }

    private ArrayList<CardFragment> initCardList(int cocktailId)
    {
        ArrayList cards = new ArrayList<CardFragment>();
        int[] images = new int[4];
        switch(cocktailId)
        {
            case R.drawable.img_virgin_daiquiri :
                //load virgin daiquiri images
                images[0] = R.drawable.eau_gazeuse;
                images[1] = R.drawable.fraise;
                images[2] = R.drawable.glacon;
                images[3] = R.drawable.sucre_canne;

                break;
            case R.drawable.img_virgin_mojito :
                //load virgin mojito image
                images[0] = R.drawable.citron_vert;
                images[1] = R.drawable.glacon;
                images[2] = R.drawable.lemonade;
                images[3] =  R.drawable.menthe;
                break;
            case R.drawable.img_virgin_pina_colada :
                //load virgin pina colada images
                images[0] = R.drawable.ananas;
                images[1] = R.drawable.noix_coco;
                images[2] = R.drawable.glacon;
                images[3] = R.drawable.sucre_canne;
                break;
            default:
                break;
        }

        for(int i=0; i<_nbPairToPlay; i++){
            GenerateCardPair(images[i%4], cards);
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
}
