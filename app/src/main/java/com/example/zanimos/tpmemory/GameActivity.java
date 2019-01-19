package com.example.zanimos.tpmemory;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private ArrayList<CardFragment> _cards;
    private CardFragment _cardSelected;
    private GridLayout _cardsGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //TODO: set row and coll dynamically
        initComponents(4,3);
        //tuto : https://stacktips.com/tutorials/android/android-gridview-example-building-image-gallery-in-android sauf image item
        initCardList(R.drawable.img_virgin_daiquiri);

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

    private void initComponents(int row, int col)

    {
        _cardsGrid = (GridLayout) findViewById(R.id.cardsGrid);
        //_cardsGrid.setColumnCount(col);
        //_cardsGrid.setRowCount(row);
    }

    private void initCardList(int cocktailId)
    {
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        _cards = new ArrayList<CardFragment>();
        switch(cocktailId)
        {
            case R.drawable.img_virgin_daiquiri :
                //load virgin daiquiri images
                for(int i=0; i<12; i++){
                    CardFragment cf = new CardFragment();
                    _cards.add(cf);
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add( _cardsGrid.getId(),cf,null).commit();
                   // _cardsGrid.addView(cf.getView(),i);
                }
                break;
            case R.drawable.img_virgin_mojito :
                //load virgin mojito image
                break;
            case R.drawable.img_virgin_pina_colada :
                //load virgin pina colada images
                break;
            default:
                break;
        }

    }
}
