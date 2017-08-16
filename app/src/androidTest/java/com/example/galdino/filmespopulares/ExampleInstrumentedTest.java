package com.example.galdino.filmespopulares;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.galdino.filmespopulares.dataBase.AppDataBase;
import com.example.galdino.filmespopulares.entity.Filme;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private AppDataBase mAppDataBase;
    private Filme mFilme;

    @Before
    public void setUp()
    {
        Context context = InstrumentationRegistry.getContext();

        mAppDataBase = Room
                .inMemoryDatabaseBuilder(context,AppDataBase.class)
//                .databaseBuilder(context,AppDataBase.class,"teste")
                .allowMainThreadQueries()
                .build();
        popularFilme();
    }

    private void popularFilme()
    {
        mFilme = new Filme();
        mFilme.setFgFavorito(1);
        mFilme.setId(2);
    }

    @Test
    public void insertFilme()
    {
        long[] longs = mAppDataBase.filmeDAO().insertAll(mFilme);
        Assert.assertTrue(longs[0] > 0);
    }

    @Test
    public void countFilmes()
    {
        int countFilme = mAppDataBase.filmeDAO().countUsers();
        Assert.assertEquals(1,countFilme);
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.galdino.filmespopulares", appContext.getPackageName());
    }
}
