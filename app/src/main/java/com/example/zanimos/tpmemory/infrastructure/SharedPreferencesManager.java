package com.example.zanimos.tpmemory.infrastructure;

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

    /***
     * Constructor
     * @param context : current context
     */
    private SharedPreferencesManager(Context context) {
        _prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /***
     * Singleton instance method
     * @param context
     * @return SharedPreferencesManager : SharedPreferencesManager unique instance
     */
    public static SharedPreferencesManager Instance(Context context){
        if(_preferencesManager == null){
            _preferencesManager = new SharedPreferencesManager(context);
        }

        return _preferencesManager;
    }

    /***
     * Token value incrementing
     * @param tokenType : "played" for nb game played / "victory" for nb game win
     * @param difficulty : game difficulty
     * @param cocktail : cocktail to play
     * @param gameMode : game mode
     */
    public void incrementTokenValue(String tokenType,
                                  String difficulty,
                                  String cocktail,
                                  String gameMode)
    {
        SharedPreferences.Editor editor = _prefs.edit();
        // Get token needed
        String token = getPeferencesToken(tokenType, difficulty, cocktail, gameMode);
        // Get token value
        int score = _prefs.getInt(token, 0);
        ++score;
        // Set new token value
        editor.putInt(token, score);
        editor.apply();
    }

    /***
     * Token value reading
     * @param tokenType "played" for nb game played / "victory" for nb game win
     * @param difficulty : game difficulty
     * @param cocktail : cocktail to play
     * @param gameMode : game mode
     * @return String : token value
     */
    public String readTokenValue(String tokenType,
                                  String difficulty,
                                  String cocktail,
                                  String gameMode)
    {
        SharedPreferences.Editor editor = _prefs.edit();
        String token = getPeferencesToken(tokenType, difficulty, cocktail, gameMode);

        return  String.valueOf(_prefs.getInt(token, 0));
    }

    /***
     * Token getter method
     * @param tokenType "played" for nb game played / "victory" for nb game win
     * @param difficulty : game difficulty
     * @param cocktail : cocktail to play
     * @param gameMode : game mode
     * @return String : token
     */
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