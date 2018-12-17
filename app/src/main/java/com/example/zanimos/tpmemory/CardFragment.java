package com.example.zanimos.tpmemory;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class CardFragment extends Fragment {

    private View _currentView = null;
    private int _id = R.drawable.img_virgin_daiquiri;

    private ImageView _cocktailImage = null;

    @Override
    public void setArguments(Bundle args) {
        _id = args.getInt("COCKTAIL_ID");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _currentView = inflater.inflate(R.layout.fragment_card, container, false);
        initComponents();
        return _currentView;
    }

    @Override
    public void onStart() {
        super.onStart();

        _cocktailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCardSelected();
            }
        });
    }

    public void setImageVisibility(boolean b)
    {
        if(b) _cocktailImage.setImageResource(_id);
        else _cocktailImage.setImageResource(R.drawable.ic_launcher_background);
    }

    private CardFragment sendCardSelected()
    {
        // TODO send to GameActivity
        return this;
    }

    private void initComponents()
    {
        _cocktailImage = (ImageView) _currentView.findViewById(R.id.cocktailImageView);
    }
}
