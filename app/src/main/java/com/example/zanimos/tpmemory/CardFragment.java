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
import android.widget.Toast;


public class CardFragment extends Fragment implements View.OnClickListener {

    private ImageView _image;
    private int _imageId;
    private boolean _isFound;

    public CardFragment() {}

    public static CardFragment Instantiate(int imageId)
    {
        CardFragment cf = new CardFragment();
        Bundle bd = new Bundle();

        bd.putInt("image_id", imageId);
        cf.setArguments(bd);

        return cf;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card, null, false);

        initComponents(v);
        setParameters();
        setImageVisibility(false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        _image.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ((GameActivity) getActivity()).compareCardSelected(this);
    }

    public void setImageVisibility(boolean b) {
        // avoid change if card already found
        if(_isFound) return;

        if (b) _image.setImageResource(_imageId);
        else _image.setImageResource(R.drawable.dos_carte);
    }

    /**
     * Make card unclickable and hide for ergonomics
     */
    public void setCardFound() {
        if(!_isFound)
        {
            _isFound = true;
            _image.setImageAlpha(100);
            _image.setClickable(false);
        }
    }

    private void initComponents(View view) {
        _image = view.findViewById(R.id.cocktailImageView);
    }

    private void setParameters() {
        _imageId = getArguments().getInt("image_id");
        _isFound = false;
    }

    public int get_imageId() { return _imageId; }
}
