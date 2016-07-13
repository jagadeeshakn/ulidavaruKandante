package com.conflux.finflux.core;

/**
 * Created by jagadeeshakn on 7/2/2016.
 */
public interface BaseActivityCallback {

    void showProgress(String message);

    void setToolbarTitle(String title);

    void hideProgress();

    void logout();
}
