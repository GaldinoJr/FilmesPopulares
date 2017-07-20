package com.example.galdino.filmespopulares.mvp;

import io.reactivex.Scheduler;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface SchedulerProvider
{
    Scheduler io();

    Scheduler ui();
}
