package com.example.zanimos.tpmemory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CocktailScoreFragment extends Fragment {

    private final String _fragmentResulatTag = "android:switcher:"+R.id.viewerpager+":0";
    private int _idCocktail = 0;
    private View _currentView;
    private Spinner spinnerD, spinnerGM;
    private String[] difficulties, modes;
    private TextView _textViewScorePlayedGames;
    private TextView _textViewScoreVictories;

    @Override
    public void setArguments(Bundle args) {
        _idCocktail = args.getInt("ID");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _currentView = inflater.inflate(R.layout.fragment_cocktail_score, container, false);
        initComponents();
        return _currentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayAdapter<String> adapterD = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, difficulties);
        adapterD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerD.setAdapter(adapterD);
        ArrayAdapter<String> adapterGM = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, modes);
        adapterGM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGM.setAdapter(adapterGM);
        bindEvents();
        setScore();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity().getApplicationContext());

        /*Toast.makeText(getContext(), prefs.getInt(
                "victory-virgin_daiquiri-classique-facile", 0), Toast.LENGTH_SHORT).show();*/

        _textViewScoreVictories.setText(
                String.valueOf(prefs.getInt(
                        "victory-virgin_daiquiri-classique-facile", 0)
                ));
    }

    private void initComponents()
    {
        difficulties = new String[] {getString(R.string.easy), getString(R.string.hard)};
        spinnerD = (Spinner) _currentView.findViewById(R.id.spinnerDifficulty);
        modes = new String[] {getString(R.string.classic), getString(R.string.atc)};
        spinnerGM = (Spinner) _currentView.findViewById(R.id.spinnerGameMode);
        _textViewScorePlayedGames = _currentView.findViewById(R.id.textViewScorePlayedGames);
        _textViewScoreVictories = _currentView.findViewById(R.id.textViewScoreVictories);
    }

    // TODO : method for attaching event like onclick
    private void bindEvents()
    {

    }

    // TODO : load scores from own page depending on the id
    private void setScore()
    {
        ImageView image = _currentView.findViewById(R.id.imageViewCocktail);
        switch(_idCocktail)
        {
            case R.drawable.img_virgin_daiquiri :
                // TODO : load virgin daiquiri score
                image.setImageResource(R.drawable.img_virgin_daiquiri);
                break;
            case R.drawable.img_virgin_mojito :
                // TODO : load virgin mojito score
                image.setImageResource(R.drawable.img_virgin_mojito);
                break;
            case R.drawable.img_virgin_pina_colada :
                // TODO : load virgin pina colada score
                image.setImageResource(R.drawable.img_virgin_pina_colada);
                break;
            default:
                // TODO : load nothing => popup dialog error ?
                break;
        }
    }
}
