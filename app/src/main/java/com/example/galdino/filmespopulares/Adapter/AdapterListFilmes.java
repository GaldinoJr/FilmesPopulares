package com.example.galdino.filmespopulares.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galdino.filmespopulares.Dominio.Result;
import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.databinding.AdapterListFilmesBinding;
import com.example.galdino.filmespopulares.databinding.IncludeCapaFilmeBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Galdino on 07/05/2017.
 */

public class AdapterListFilmes extends RecyclerView.Adapter<AdapterListFilmes.AdapteListFilmesViewHolder>
{
    private List<Result> mListFilmes;
    private ListenerAdapter mListener;


    public AdapterListFilmes(List<Result> mListFilmes) {
        this.mListFilmes = mListFilmes;
    }



    // 1 - Associa os objetos da tela com a variável
    public class AdapteListFilmesViewHolder extends RecyclerView.ViewHolder
    {
        private AdapterListFilmesBinding mBinding;
        public AdapteListFilmesViewHolder(AdapterListFilmesBinding binding)
        {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(mListener != null && position != RecyclerView.NO_POSITION)
                    {
                        Result filme = mListFilmes.get(position);
                        mListener.onClickList(filme);
                    }
                }
            });
        }
    }

    // 2 - Infla o XML do adapter
    @Override
    public AdapteListFilmesViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdapterListFilmesBinding adapterListFilmesBinding =
                AdapterListFilmesBinding.inflate(layoutInflater,parent,false);

        return new AdapteListFilmesViewHolder(adapterListFilmesBinding);
    }

    // 3 - Passa o conteudo para os objetos de tela
    @Override
    public void onBindViewHolder(AdapteListFilmesViewHolder holder, int position)
    {
        if(holder.mBinding!= null && holder.mBinding.ivCapaFilme != null)
        {
            Context context = holder.mBinding.ivCapaFilme.getContext();
            if(mListFilmes.get(position) != null && mListFilmes.get(position).getPosterPath()!= null)
            {
                String urlCapa = context.getResources().getString(R.string.url_images_185) + mListFilmes.get(position).getPosterPath();
                Picasso.with(context).load(urlCapa).into(holder.mBinding.ivCapaFilme);
            }
        }
    }

    @Override
    public int getItemCount()
    {
        if(mListFilmes != null)
            return mListFilmes.size();
        else
            return 0;
    }

    // Click
    public void setListFilmesClickListener(ListenerAdapter listenerAdapter)
    {
        this.mListener = listenerAdapter;
    }
    public interface ListenerAdapter
    {
        void onClickList(Result filme);
    }
}
