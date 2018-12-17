package com.example.zanimos.tpmemory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    private List<com.example.zanimos.tpmemory.CardFragment> _cards = null;
    private CardFragment _cardSelected = null;

    private GridView _cardsGridView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void compareCardSelected(CardFragment selectedCocktail)
    {
        if(selectedCocktail == null)
        {
            //no card already selected
            _cardSelected = selectedCocktail;
        }
        else
        {
            if(_cardSelected == selectedCocktail)
            {
                _cards.remove(_cardSelected);
                _cards.remove(selectedCocktail);
            }else
            {
                _cardSelected.setImageVisibility(false);
                selectedCocktail.setImageVisibility(false);
                _cardSelected = null;
            }
        }
    }

    private void finishGame()
    {
        //Pop up dialog
    }

    private void initComponents()
    {
        _cardsGridView = (GridView) findViewById(R.id.cardsGridView);
    }

    private void initCardList(int cocktailId)
    {
        switch(cocktailId)
        {
            case R.drawable.img_virgin_daiquiri :
                //load virgin daiquiri images
                break;
            case R.drawable.img_virgin_mojito :
                //load virgin mojito images
                break;
            case R.drawable.img_virgin_pina_colada :
                //load virgin pina colada images
                break;
            default:
                break;
        }
    }
}
