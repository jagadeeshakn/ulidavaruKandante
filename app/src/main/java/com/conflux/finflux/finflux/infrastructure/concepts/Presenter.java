package com.conflux.finflux.finflux.infrastructure.concepts;

/**
 * Created by Praveen J U on 6/29/2016.
 */
public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);
    void detachView();
}
