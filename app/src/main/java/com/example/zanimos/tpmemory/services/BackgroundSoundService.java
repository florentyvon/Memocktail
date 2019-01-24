package com.example.zanimos.tpmemory.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.zanimos.tpmemory.R;

public class BackgroundSoundService extends Service {

    private BackgroundSoundServiceBinder binder = new BackgroundSoundServiceBinder();
    private MediaPlayer mMediaPlayer;


    public class BackgroundSoundServiceBinder extends Binder {
        public BackgroundSoundService getService() {
            return BackgroundSoundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extras = intent.getExtras();
        if(extras == null) return START_REDELIVER_INTENT;
        else {
            String _soundS;
            _soundS = extras.getString("sound");
            mMediaPlayer = MediaPlayer.create(this, getSound(_soundS));
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer != null) mMediaPlayer.stop();
    }

    private int getSound(String soundString){
        switch(soundString){
            case "lobby":
                return R.raw.background_sound;
            case "game":
                return R.raw.game;
            case "countdown":
                return R.raw.countdown;
            default:
                return R.raw.background_sound;
        }
    }
}
