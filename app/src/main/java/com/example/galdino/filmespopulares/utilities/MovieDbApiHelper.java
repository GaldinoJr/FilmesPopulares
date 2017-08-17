package com.example.galdino.filmespopulares.utilities;

import android.content.Context;

import com.example.galdino.filmespopulares.dataBase.AppDataBase;
import com.example.galdino.filmespopulares.dominio.ObjetoListaFilmes;
import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Comentarios;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Result;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Videos;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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
    private static final String METODO_COMENTARIOS_FILME = "reviews";
    private static final String METODO_POPULAR = "popular";
    private static final String METODO_MELHOR_AVALIADO = "top_rated";
    private static final String URL_API = "https://api.themoviedb.org/3/movie/";
    private static final String PARAMETER_FILME_DETALHE_CHAVE_API = "api_key";
    private static final String PARAMETER_FILME_DETALHE_TRAILERS = "append_to_response";
    private static final String VALUE_PARAMETER_FILME_DETALHE_TRAILERS = "videos";

    private static final String FILME_DETALHE_PATH_FILME_ID = "movieId";
    private static final String FILME_DETALHE_PATH_FILME_REVIEWS = "reviews";
    private static final String FILME_DETALHE_PATH = "{" + FILME_DETALHE_PATH_FILME_ID + "}";

    //private static final String GET_MOVIE_PATH = "3/movie/{" + MOVIE_ID_PATH + "}";

    private final MovieDbApi mMovieDbApi;
    private String mApiKey;
    private Context mContext;

    public MovieDbApiHelper(Context context) {
        Retrofit retrofit = getRetrofit();
        mMovieDbApi = retrofit.create(MovieDbApi.class);
        mContext = context;
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
    public Observable<List<Result>> getComentario(int idFilme) {
        return mMovieDbApi.getComentarios(idFilme, mApiKey)
                .flatMap(getComentariosResponseMapper());
    }

    // Para não travar a UI
    //    public Observable<List<Filme>> getFavorito(final Context context) {
//        return Observable.create(new ObservableOnSubscribe<List<Filme>>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<List<Filme>> e) throws Exception {
//                AppDataBase db = AppDataBase.getInstance(context);
//                List<Filme> filmes = db.filmeDAO().getAll();
////                for (Filme filme : filmes) {
////                    e.onNext(filme);
////                }
//                e.onNext(filmes);
//                e.onComplete();
//            }
//        });

    @Override
    public Observable<List<Filme>> getFavorito() {
        AppDataBase db = AppDataBase.getInstance(mContext);

        return Observable.just(db.filmeDAO().getAll());
    }

    @Override
    public Observable<Filme> getFilmeDetalhe(int idFilme)
    {
        AppDataBase db = AppDataBase.getInstance(mContext);
        Filme filme = db.filmeDAO().selectById(idFilme);
        Observable<Filme> filmeObservable;
        if(filme == null) {
            filmeObservable =  mMovieDbApi.getFilmeDetalhe(idFilme, mApiKey, VALUE_PARAMETER_FILME_DETALHE_TRAILERS)
                    .cast(Filme.class);
        }
        else
        {
            List<Result> list = db.resultDAO().getAllTrailers();
            Videos videos = new Videos();
            videos.setResults(list);
            filme.setVideos(videos);
            filmeObservable = Observable.just(filme);
        }
        return filmeObservable;
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

    @android.support.annotation.NonNull
    private Function<Comentarios, Observable<List<Result>>> getComentariosResponseMapper() {
        return new Function<Comentarios, Observable<List<Result>>>() {
            @Override
            public Observable<List<Result>> apply(@NonNull Comentarios moviesResponseBody) throws Exception {

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
        @GET(FILME_DETALHE_PATH + "/" + FILME_DETALHE_PATH_FILME_REVIEWS)
        Observable<Comentarios> getComentarios(@Path(FILME_DETALHE_PATH_FILME_ID) int movieId,
                                               @Query(PARAMETER_FILME_DETALHE_CHAVE_API) String apiKey);
    }
}
