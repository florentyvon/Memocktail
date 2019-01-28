package com.example.zanimos.tpmemory.historic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zanimos.tpmemory.R;
import com.example.zanimos.tpmemory.infrastructure.SharedPreferencesManager;

/***
 * Historic score fragment
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class CocktailScoreFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private final String _fragmentResulatTag = "android:switcher:"+R.id.viewerpager+":0";
    private int _idCocktail = 0;
    private View _currentView;
    private Spinner _spinnerDifficulty, _spinnerGameMode;
    private TextView _textViewScorePlayedGames;
    private TextView _textViewScoreVictories;
    private SharedPreferencesManager _preferencesManager;

    /***
     * setArguments fragment method
     * @param args
     */
    @Override
    public void setArguments(Bundle args) {
        _idCocktail = args.getInt("ID");
    }

    /***
     * onCreateView fragment event
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _currentView = inflater.inflate(R.layout.fragment_cocktail_score, container, false);
        initComponents();
        return _currentView;
    }

    /***
     * onStart fragment event
     */
    @Override
    public void onStart() {
        super.onStart();

        // Get SharedPreferencesManager Instance
        _preferencesManager = SharedPreferencesManager.Instance(
                this.getActivity().getApplicationContext());
        bindEvents();
        setSpinners();
        setScore();
    }

    /**
     * Fragment components init
     */
    private void initComponents()
    {
        _spinnerDifficulty = (Spinner) _currentView.findViewById(R.id.spinnerDifficulty);
        _spinnerGameMode = (Spinner) _currentView.findViewById(R.id.spinnerGameMode);
        _textViewScorePlayedGames = _currentView.findViewById(R.id.textViewScorePlayedGames);
        _textViewScoreVictories = _currentView.findViewById(R.id.textViewScoreVictories);
    }

    /***
     * Event binding method
     */
    private void bindEvents()
    {
        _spinnerDifficulty.setOnItemSelectedListener(this);
        _spinnerGameMode.setOnItemSelectedListener(this);
    }

    /***
     * Spinners setting
     */
    private void setSpinners()
    {
        String[] difficulties = new String[] {getString(R.string.easy), getString(R.string.hard)};
        String[] modes = new String[] {getString(R.string.classic), getString(R.string.atc)};

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<>(this.getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, difficulties);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerDifficulty.setAdapter(adapterDifficulty);

        ArrayAdapter<String> adapterGameMode = new ArrayAdapter<>(this.getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, modes);
        adapterGameMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerGameMode.setAdapter(adapterGameMode);
    }

    /***
     * Score display
     */
    private void setScore()
    {
        ImageView image = _currentView.findViewById(R.id.imageViewCocktail);
        String tempNbPlayed;
        String tempScore;
        switch(_idCocktail)
        {
            case R.drawable.img_virgin_daiquiri :
                image.setImageResource(R.drawable.img_virgin_daiquiri);

                tempNbPlayed = _preferencesManager.readTokenValue("played",
                        _spinnerDifficulty.getSelectedItem().toString(),
                        "virgin_daiquiri",
                        _spinnerGameMode.getSelectedItem().toString());
                tempScore = _preferencesManager.readTokenValue("victory",
                        _spinnerDifficulty.getSelectedItem().toString(),
                        "virgin_daiquiri",
                        _spinnerGameMode.getSelectedItem().toString());
                break;

            case R.drawable.img_virgin_mojito :
                image.setImageResource(R.drawable.img_virgin_mojito);

                tempNbPlayed = _preferencesManager.readTokenValue("played",
                        _spinnerDifficulty.getSelectedItem().toString(),
                        "virgin_mojito",
                        _spinnerGameMode.getSelectedItem().toString());
                tempScore = _preferencesManager.readTokenValue("victory",
                        _spinnerDifficulty.getSelectedItem().toString(),
                        "virgin_mojito",
                        _spinnerGameMode.getSelectedItem().toString());
                break;

            case R.drawable.img_virgin_pina_colada :
                image.setImageResource(R.drawable.img_virgin_pina_colada);

                tempNbPlayed = _preferencesManager.readTokenValue("played",
                        _spinnerDifficulty.getSelectedItem().toString(),
                        "virgin_pina_colada",
                        _spinnerGameMode.getSelectedItem().toString());
                tempScore = _preferencesManager.readTokenValue("victory",
                        _spinnerDifficulty.getSelectedItem().toString(),
                        "virgin_pina_colada",
                        _spinnerGameMode.getSelectedItem().toString());
                break;

            default:
                tempNbPlayed = "";
                tempScore = "";
                break;
        }

        _textViewScorePlayedGames.setText(tempNbPlayed);
        _textViewScoreVictories.setText(tempScore);
    }

    /***
     * onItemSelected spinner event
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setScore();
    }

    /***
     * onNothingSelected spinner event (not used)
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}