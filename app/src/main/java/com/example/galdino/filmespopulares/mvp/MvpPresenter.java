package com.example.galdino.filmespopulares.mvp;

import com.example.galdino.filmespopulares.mvp.schedulerprovider.SchedulerProvider;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface MvpPresenter<T extends MvpView> {
    void attach(T t);

    void detachView();

    SchedulerProvider getSchedulerProvider();
}
