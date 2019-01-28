package com.example.zanimos.tpmemory.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.zanimos.tpmemory.R;

/***
 * Sound effects service
 * @author Florent Yvon, Julien Raillard, Mickael Meneux
 */
public class SoundEffectsService extends Service {

    private MediaPlayer mMediaPlayer;

    /***
     * Constructor
     */
    public SoundEffectsService() {}

    /***
     * onBind activity event
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /***
     * onStartCommand activity event
     */
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

        return START_STICKY;
    }

    /***
     * onDestroy activity event
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) mMediaPlayer.release();
    }
}