package com.example.galdino.filmespopulares.mvp;

import com.example.galdino.filmespopulares.mvp.schedulerprovider.SchedulerProvider;

/**
 * Created by Galdino on 20/07/2017.
 */

public abstract class BasePresenter<T extends MvpView> implements MvpPresenter<T>{

    private SchedulerProvider mSchedulerProvider;

    public BasePresenter(SchedulerProvider schedulerProvider) {
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }
}