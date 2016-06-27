package com.conflux.finflux.finflux.infrastructure;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.db.Activation;
import com.conflux.finflux.finflux.infrastructure.operations.SplashScreenCheckList;
import com.conflux.finflux.finflux.util.Logger;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import butterknife.Bind;

import butterknife.ButterKnife;

public class SplashScreen extends Activity implements SplashScreenCheckList{

    private final String TAG = getClass().getSimpleName();

    //bind view components starts here
    @Bind(R.id.fullscreen_content)ImageView imageViewLogo;
    @Bind(R.id.progressBar)ProgressBar progressBar;

    //
    private ConditionVariable conditionVariable;
    private Handler handler = new Handler();
    int progressStatus = 0;

    TranslateAnimation.AnimationListener animationListener = new TranslateAnimation.AnimationListener(){

        @Override
        public void onAnimationStart(Animation animation) {
            Logger.d(TAG,"animation start");
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Logger.d(TAG,"animation end");
            initalizeProgressBar();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        TranslateAnimation contentViewAnimate = new TranslateAnimation(0.0f, 0.0f,
                0.0f, -400.0f);
        contentViewAnimate.setDuration(2000);
        contentViewAnimate.setFillAfter(true);
        contentViewAnimate.setAnimationListener(animationListener);
        imageViewLogo.startAnimation(contentViewAnimate);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }


    @Override
    public void initalizeProgressBar() {
        Logger.e(TAG, "Initialize The Progress Bar");
        progressBar.setVisibility(View.VISIBLE);
        conditionVariable = new ConditionVariable();
        incrementProgress();
    }

    private void incrementProgress(){
        progressBar.setVisibility(View.VISIBLE);
        ProgressAsynchronous progressAsynchronous =new ProgressAsynchronous();
        progressAsynchronous.execute();
    }

    private void startActivationActivity(){
        startActivity(new Intent(this,ApplicationSetup.class));
        finish();
    }

    @Override
    public boolean isApplicationActivated() {
        return false;
    }

    @Override
    public boolean isFirstLogin() {
        return false;
    }

    private void updateProgressBar(final Integer integer){
        handler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(integer);
            }
        });
    }

    private class ProgressAsynchronous extends AsyncTask<Void,Integer,Boolean> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Boolean doInBackground(Void... voids) {
            while (progressStatus < 100)
            {
                progressStatus += 1;
                updateProgressBar(progressStatus);
                try
                {
                    Thread.sleep(20);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                startActivationActivity();
            }
        }
    }

}
