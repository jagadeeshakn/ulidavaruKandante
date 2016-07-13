package com.conflux.finflux.infrastructure.operations;

/**
 * Created by praveen on 6/23/2016.
 */
public interface SplashScreenCheckList {
    void initalizeProgressBar();
    boolean isApplicationActivated();
    boolean isFirstLogin();
}
