package com.example.zanimos.tpmemory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/***
 * A propos activity
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class AProposActivity extends AppCompatActivity {

    /***
     * onCreate activity event
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apropos);
    }

    /***
     * onStop activity event
     */
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}