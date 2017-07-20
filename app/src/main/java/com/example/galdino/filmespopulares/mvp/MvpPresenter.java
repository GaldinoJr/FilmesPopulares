package com.example.galdino.filmespopulares.mvp;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface MvpPresenter<T extends MvpView> {
    void attach(T t);

    void detachView();

    SchedulerProvider getSchedulerProvider();
}
