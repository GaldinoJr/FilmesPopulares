package com.example.galdino.filmespopulares.Utilities;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.galdino.filmespopulares.Dominio.Filme;
import com.example.galdino.filmespopulares.Dominio.Result;
import com.example.galdino.filmespopulares.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Galdino on 10/05/2017.
 */

public class NetworkUtils
{
    private static final String API_KEY = "api_key";

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static URL buildUrl(Context context, String metodo)
    {
        String urlLink = context.getResources().getString(R.string.url_api);// + metodo;

        Uri builtUri = Uri.parse(urlLink)
                .buildUpon()
                .appendPath(metodo)
                .appendQueryParameter(API_KEY, context.getResources().getString(R.string.chave_api))
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v("Teste", "Built URI " + url);

        return url;
    }

    public List<Result> getList(String retorno) {
        Gson gson = new Gson();
        Filme filme = gson.fromJson(retorno, Filme.class);
        return filme.getResults();
    }
}
