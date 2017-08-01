package com.example.galdino.filmespopulares.utilities;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.Result;
import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.filmeDetalhe.FilmeDetalhe;
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
    private static final String APPEND_TO_RESPONSE = "append_to_response";

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

    private static Uri.Builder buildUrl(Context context, String metodo)
    {
        String urlLink = context.getResources().getString(R.string.url_api);

        Uri.Builder builder = Uri.parse(urlLink)
                .buildUpon()
                .appendPath(metodo)
                .appendQueryParameter(API_KEY, context.getResources().getString(R.string.chave_api));

        return builder;
    }

    public static URL buildUrlVideos(Context context, String metodo)
    {
        Uri.Builder builder = buildUrl(context,metodo);
        Uri builtUri = builder.build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v("Teste", "Built URI " + url);

        return url;
    }

    public static URL buildUrlVideoDetalhe(Context context, String metodo)
    {
        Uri.Builder builder = buildUrl(context,metodo);
        builder.appendQueryParameter(APPEND_TO_RESPONSE, "videos");
        Uri builtUri = builder.build();

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

    public FilmeDetalhe getFilmeDetalhe(String retorno) {
        Gson gson = new Gson();
        FilmeDetalhe filmeDetalhe = gson.fromJson(retorno, FilmeDetalhe.class);
        return filmeDetalhe;
    }
}
