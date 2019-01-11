package com.example.zanimos.tpmemory;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private ArrayList<CardFragment> _cards=null;
    private CardFragment _cardSelected = null;
    private GridView _cardsGridView = null;
    private ImageAdapter gridAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
        //tuto : https://stacktips.com/tutorials/android/android-gridview-example-building-image-gallery-in-android sauf image item
        initCardList(R.drawable.img_virgin_daiquiri);
        gridAdapter = new ImageAdapter(this, R.layout.fragment_card, _cards);
        _cardsGridView.setAdapter(gridAdapter);

        _cardsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        Integer.toString(position), Toast.LENGTH_SHORT).show();

            }
        });
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
        _cards = new ArrayList<CardFragment>();
        switch(cocktailId)
        {
            case R.drawable.img_virgin_daiquiri :
                //load virgin daiquiri images
                for(int i=0; i<12; i++){
                    CardFragment cf = new CardFragment();
                    _cards.add(cf);
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
