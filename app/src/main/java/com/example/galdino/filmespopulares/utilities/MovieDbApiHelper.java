package com.example.galdino.filmespopulares.utilities;

import android.content.Context;

import com.example.galdino.filmespopulares.dominio.ObjetoListaFilmes;
import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.dominio.Filme;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Galdino on 23/07/2017.
 */

public class MovieDbApiHelper implements FilmeApiMvpHelper
{
    private static final String METODO_POPULAR = "popular";
    private static final String METODO_MELHOR_AVALIADO = "top_rated";
    private static final String URL_API = "https://api.themoviedb.org/3/movie/";
    private static final String PARAMETER_FILME_DETALHE_CHAVE_API = "api_key";
    private static final String PARAMETER_FILME_DETALHE_TRAILERS = "append_to_response";
    private static final String VALUE_PARAMETER_FILME_DETALHE_TRAILERS = "videos";

    private static final String FILME_DETALHE_PATH_FILME_ID = "movieId";
    private static final String FILME_DETALHE_PATH = "{" + FILME_DETALHE_PATH_FILME_ID + "}";

    //private static final String GET_MOVIE_PATH = "3/movie/{" + MOVIE_ID_PATH + "}";

    private final MovieDbApi mMovieDbApi;
    private String mApiKey;

    public MovieDbApiHelper(Context context) {
        Retrofit retrofit = getRetrofit();
        mMovieDbApi = retrofit.create(MovieDbApi.class);

        if (context != null) {
            mApiKey = context.getString(R.string.chave_api);
        }
    }

    @Override
    public Observable<List<Filme>> getPopular() {
        return mMovieDbApi.getPopular(mApiKey)
                .flatMap(getMovieResponseMapper());
    }

    @Override
    public Observable<List<Filme>> getMelhorAvaliado() {
        return mMovieDbApi.getMelhorAvaliado(mApiKey)
                .flatMap(getMovieResponseMapper());
    }

    @Override
    public Observable<Filme> getFilmeDetalhe(int movieId) {
        return mMovieDbApi.getFilmeDetalhe(movieId, mApiKey,VALUE_PARAMETER_FILME_DETALHE_TRAILERS)
                .cast(Filme.class);
    }

    @Override
    public Observable<ObjetoListaFilmes> getMovieSummary(String movieId) {
        return null;
    }

    // métodos do retrofit
    @android.support.annotation.NonNull
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @android.support.annotation.NonNull
    private Function<ObjetoListaFilmes, Observable<List<Filme>>> getMovieResponseMapper() {
        return new Function<ObjetoListaFilmes, Observable<List<Filme>>>() {
            @Override
            public Observable<List<Filme>> apply(@NonNull ObjetoListaFilmes moviesResponseBody) throws Exception {

                return Observable.just(moviesResponseBody.getResults());
            }
        };
    }


    interface MovieDbApi {

        @GET(METODO_POPULAR)
        Observable<ObjetoListaFilmes> getPopular(@Query(PARAMETER_FILME_DETALHE_CHAVE_API) String apiKey);

        @GET(METODO_MELHOR_AVALIADO)
        Observable<ObjetoListaFilmes> getMelhorAvaliado(@Query(PARAMETER_FILME_DETALHE_CHAVE_API) String apiKey);

        @GET(FILME_DETALHE_PATH)
        Observable<Filme> getFilmeDetalhe(@Path(FILME_DETALHE_PATH_FILME_ID) int movieId,
                                          @Query(PARAMETER_FILME_DETALHE_CHAVE_API) String apiKey,
                                          @Query(PARAMETER_FILME_DETALHE_TRAILERS) String trailer);
    }
}
