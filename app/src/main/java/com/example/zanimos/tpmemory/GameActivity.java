package com.example.zanimos.tpmemory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private List<com.example.zanimos.tpmemory.CardFragment> _cards=null;
    //utilisé pour adapter custom
    private List<ImageView> _cardsi = null;
    private CardFragment _cardSelected = null;

    //utilisé pour adapter text
    private GridView _cardsGridView = null;
    //utilisé pour adapter custom
    private GridView _cardGridView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //tuto : https://www.mkyong.com/android/android-gridview-example/ 2ème partie, sur l'adapter custom, la première fonctionne bien avec du texte si vous voulez vous faire la main
        _cardGridView = (GridView) findViewById(R.id.cardsGridView);
        initCardList(R.drawable.img_virgin_mojito);
        ImageAdapter iA = new ImageAdapter(this);
        _cardGridView.setAdapter(iA);

        _cardGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        _cardsi = new ArrayList<>();
        switch(cocktailId)
        {
            case R.drawable.img_virgin_daiquiri :
                //load virgin daiquiri images
                for(int i=0; i<12; i++){
                    CardFragment cf = new CardFragment();
                    cf.setImageVisibility(true);
                    _cards.add(cf);
            }
                break;
            case R.drawable.img_virgin_mojito :
                //load virgin mojito images
                for(int i=0; i<12; i++){
                    ImageView iV = new ImageView(getBaseContext());
                    iV.setImageResource(R.drawable.img_virgin_mojito);
                    _cardsi.add(iV);
                }
                break;
            case R.drawable.img_virgin_pina_colada :
                //load virgin pina colada images
                break;
            default:
                break;
        }
    }
}
