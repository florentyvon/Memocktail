package com.example.zanimos.tpmemory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zanimos.tpmemory.LobbyActivity;

public class MenuActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button buttonClassique;
    private Button buttonChrono;
    private Button buttonHisto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu);
        super.onCreate(savedInstanceState);

        buttonChrono = (Button) findViewById(R.id.button_chrono);
        buttonClassique = (Button) findViewById(R.id.button_classique);
        buttonHisto = (Button) findViewById(R.id.button_histo);

        buttonChrono.setOnClickListener(this);
        buttonClassique.setOnClickListener(this);
        buttonHisto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.button_classique:
                intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
                break;
            case R.id.button_chrono:
                intent = new Intent(getApplicationContext(), ATCActivity.class);
                startActivity(intent);
                break;
            case R.id.button_histo:
                intent = new Intent(getApplicationContext(), HistoricActivity.class);
                startActivity(intent);
                break;
        }
    }
}
