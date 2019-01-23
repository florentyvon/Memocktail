package com.example.zanimos.tpmemory;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/***
 * Shared preferences manager
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class SharedPreferencesManager {

    private static SharedPreferencesManager _preferencesManager = null;
    private SharedPreferences _prefs = null;

    private SharedPreferencesManager(Context context) {
        _prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferencesManager Instance(Context context){
        if(_preferencesManager == null){
            _preferencesManager = new SharedPreferencesManager(context);
        }

        return _preferencesManager;
    }

    public void incrementTokenValue(String tokenType,
                                  String difficulty,
                                  String cocktail,
                                  String gameMode)
    {
        SharedPreferences.Editor editor = _prefs.edit();
        String token = getPeferencesToken(tokenType, difficulty, cocktail, gameMode);

        int score = _prefs.getInt(token, 0);
        ++score;

        editor.putInt(token, score);
        editor.apply();
    }

    public String readTokenValue(String tokenType,
                                  String difficulty,
                                  String cocktail,
                                  String gameMode)
    {
        SharedPreferences.Editor editor = _prefs.edit();
        String token = getPeferencesToken(tokenType, difficulty, cocktail, gameMode);

        return  String.valueOf(_prefs.getInt(token, 0));
    }

    private String getPeferencesToken(String tokenType,
                                             String difficulty,
                                             String cocktail,
                                             String gameMode)
    {
        // token pattern : victory/played-cocktail-gamemode-difficulty
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(cocktail.toLowerCase().replace(" ", "_")+"-");
        stringBuilder.append(gameMode.toLowerCase().replace(" ", "_")+"-");
        stringBuilder.append(difficulty.toLowerCase().replace(" ", "_"));

        return stringBuilder.insert(0, tokenType+"-").toString();
    }
}
