package com.example.zanimos.tpmemory.game;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zanimos.tpmemory.R;
import com.example.zanimos.tpmemory.infrastructure.SharedPreferencesManager;
import com.example.zanimos.tpmemory.menu.MenuActivity;
import com.example.zanimos.tpmemory.services.BackgroundSoundService;

import java.util.ArrayList;
import java.util.Collections;

/***
 * Main game activity
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class GameActivity extends AppCompatActivity {

    private SharedPreferencesManager _preferencesManager;
    private CardFragment[] _cardsSelected;
    private GridLayout _cardsGrid;
    private TextView _chronoTextView;

    private CountDownTimer _countDownTimer;
    private int _nbPairToPlay;
    private String _difficulty;
    private String _cocktail;
    private String _gameMode;
    private int _timer;


    /***
     * onCreate activity event
     * @param savedInstanceState
     */
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
        }
    }

    /***
     * onStart activity event
     */
    @Override
    protected void onStart(){
        super.onStart();
        _cardsSelected = new CardFragment[2];

        // Get SharedPreferencesManager Instance
        _preferencesManager = SharedPreferencesManager.Instance(getApplicationContext());
        _preferencesManager.incrementTokenValue("played",_difficulty,_cocktail,_gameMode);

        // Set Game parameters
        setDifficulty(_difficulty);
        setCocktail(_cocktail);
        setGameMode(_gameMode);

        Intent intent = new Intent(this, BackgroundSoundService.class);
        intent.putExtra("sound","game");
        startService(intent);
    }

    /***
     * Activity components init
     */
    private void initComponents()
    {
        _chronoTextView = (TextView) findViewById(R.id.chronoTextView);
        _cardsGrid = (GridLayout) findViewById(R.id.cardsGrid);
        _cardsGrid.setAlignmentMode(GridLayout.ALIGN_MARGINS);
    }

    /***
     * Difficulty setting
     * @param difficulty : game difficulty
     */
    private void setDifficulty(String difficulty)
    {
        switch(difficulty)
        {
            case "Facile":
                // 12 cards
                _cardsGrid.setColumnCount(3);
                _cardsGrid.setRowCount(4);
                _nbPairToPlay = 6;
                _timer = 60000; // 1 min
                break;
            case "Difficile":
                // 16 cards
                _cardsGrid.setColumnCount(4);
                _cardsGrid.setRowCount(4);
                _nbPairToPlay = 8;
                _timer = 30000; // 30 scd
                break;
            default:
                // 12 cards
                _cardsGrid.setColumnCount(4);
                _cardsGrid.setRowCount(3);
                _nbPairToPlay = 6;
                _timer = 60000; // 1min
                break;
        }
    }

    /***
     * Cocktail to play setting
     * @param cocktail : cocktail to play
     */
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

    /***
     * Game mode setting
     * @param gameMode : game mode
     */
    private void setGameMode(String gameMode)
    {
        if(getString(R.string.atc).equals(gameMode)) startTimer();
        else _chronoTextView.setEnabled(false);
    }

    /***
     * GridLayout filling with cards
     * @param cardsToPlay : cards
     */
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

    /***
     * Card list creation
     * @param cocktailId
     * @return ArrayList<CardFragment> : cards
     */
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

    /***
     * Create two similar cards (a pair)
     * @param imageId : card image (recto)
     * @param cards : game card list to add new cards
     */
    private void GenerateCardPair(int imageId, ArrayList<CardFragment> cards)
    {
        CardFragment cf = CardFragment.Instantiate(imageId);
        cards.add(cf);
        cf = CardFragment.Instantiate(imageId);
        cards.add(cf);
    }


    private void displayScore(){
        int mins = _timer / 60000;
        int scds = _timer % 60000 / 1000;

        StringBuilder strTimer = new StringBuilder();
        strTimer.append(0);
        strTimer.append(mins);
        strTimer.append(':');
        if(scds < 10) strTimer.append(0);
        strTimer.append(scds);

        _chronoTextView.setText(strTimer.toString());
    }

    private void startTimer()
    {
        _countDownTimer = new CountDownTimer(_timer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                _timer = (int) millisUntilFinished;
                displayScore();
            }

            @Override
            public void onFinish() {
                finishGame(false);
            }
        }.start();
    }

    /***
     * Compare method for selected cards
     * @param clickedCard : selectedCard
     */
    public void compareCardSelected(CardFragment clickedCard)
    {
        // if click two times on the same card
        if(_cardsSelected[0] != null && _cardsSelected[0].hashCode() == clickedCard.hashCode()) return;

        // if click on card after having click on two previous card
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
            // already selected card
            _cardsSelected[1] = clickedCard;
            if(_cardsSelected[0].get_imageId() == _cardsSelected[1].get_imageId()) {
                // same image
                --_nbPairToPlay;
                _cardsSelected[0].setCardFound();
                _cardsSelected[1].setCardFound();

                if (_nbPairToPlay == 0) {
                    finishGame(true);
                }
            }
        }
    }

    /***
     * Method to end the game
     * @param win : Win/Loose ?
     */
    private void finishGame(boolean win)
    {
        Bundle bd = new Bundle();
        if(win)
        {
            _preferencesManager.incrementTokenValue("victory",_difficulty,_cocktail,_gameMode);
            bd.putBoolean("WIN", true);
        }
        else
        {
            bd.putBoolean("WIN", false);
        }

        // Back to Menu
        Intent i = new Intent(this.getApplicationContext(), GameResultActivity.class);
        i.putExtras(bd);
        startActivity(i);
        finish();
    }
}
