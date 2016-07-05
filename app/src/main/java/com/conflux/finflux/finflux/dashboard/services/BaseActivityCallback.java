package com.conflux.finflux.finflux.dashboard.services;

/**
 * Created by jagadeeshakn on 7/2/2016.
 */
public interface BaseActivityCallback {

    void showProgress(String message);

    void setToolbarTitle(String title);

    void hideProgress();

    void logout();
}
