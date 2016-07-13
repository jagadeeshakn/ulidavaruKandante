package com.conflux.finflux.base;

/**
 * Created by jagadeeshakn on 7/8/2016.
 */
public interface Presenter<V extends MvpView >{

    void attachView(V mvpView);

    void detachView();

}
