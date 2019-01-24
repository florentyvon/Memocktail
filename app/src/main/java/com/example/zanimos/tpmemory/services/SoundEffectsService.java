package com.example.zanimos.tpmemory.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

import com.example.zanimos.tpmemory.R;

public class SoundEffectsService extends Service {

    private MediaPlayer mMediaPlayer;
    private boolean isOn;

    public SoundEffectsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.card);
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopSelf();
                }
            });
            isOn = true;
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
    }

    /*public boolean toggleVolume()
    {
        isOn = !isOn;
        if(isOn) mMediaPlayer.setVolume(20f, 20f);
        else mMediaPlayer.setVolume(20f, 20f);

        return isOn;
    }*/
}
