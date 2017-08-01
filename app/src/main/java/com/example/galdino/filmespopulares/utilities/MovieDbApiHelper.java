package com.example.galdino.filmespopulares.utilities;

import android.content.Context;

import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.Result;
import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.filmeDetalhe.FilmeDetalhe;

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
    private static final String PARAMETER_CHAVE_API = "api_key";

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
    public Observable<List<Result>> getPopular() {
        return mMovieDbApi.getPopular(mApiKey)
                .flatMap(getMovieResponseMapper());
    }

    @Override
    public Observable<List<Result>> getMelhorAvaliado() {
        return mMovieDbApi.getMelhorAvaliado(mApiKey)
                .flatMap(getMovieResponseMapper());
    }

    @Override
    public Observable<FilmeDetalhe> getFilmeDetalhe(int movieId) {
        return mMovieDbApi.getFilmeDetalhe(movieId, mApiKey)
                .cast(FilmeDetalhe.class);
    }

    @Override
    public Observable<Filme> getMovieSummary(String movieId) {
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
    private Function<Filme, Observable<List<Result>>> getMovieResponseMapper() {
        return new Function<Filme, Observable<List<Result>>>() {
            @Override
            public Observable<List<Result>> apply(@NonNull Filme moviesResponseBody) throws Exception {

                return Observable.just(moviesResponseBody.getResults());
            }
        };
    }


    interface MovieDbApi {

        @GET(METODO_POPULAR)
        Observable<Filme> getPopular(@Query(PARAMETER_CHAVE_API) String apiKey);

        @GET(METODO_MELHOR_AVALIADO)
        Observable<Filme> getMelhorAvaliado(@Query(PARAMETER_CHAVE_API) String apiKey);

        @GET(FILME_DETALHE_PATH)
        Observable<FilmeDetalhe> getFilmeDetalhe(@Path(FILME_DETALHE_PATH_FILME_ID) int movieId,
                                                 @Query(PARAMETER_CHAVE_API) String apiKey);
    }
}
