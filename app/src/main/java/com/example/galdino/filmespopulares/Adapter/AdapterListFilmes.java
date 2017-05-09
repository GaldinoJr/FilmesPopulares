package com.example.galdino.filmespopulares.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galdino.filmespopulares.Dominio.Filme;
import com.example.galdino.filmespopulares.databinding.AdapterListFilmesBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Galdino on 07/05/2017.
 */

public class AdapterListFilmes extends RecyclerView.Adapter<AdapterListFilmes.AdapteListFilmesViewHolder>
{
    private List<Filme> mListFilmes;
    private ListenerAdapter mListener;
    private int altura;

    public AdapterListFilmes(List<Filme> mListFilmes, int y) {
        this.mListFilmes = mListFilmes;
        this.altura = y;
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
                        Filme filme = mListFilmes.get(position);
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
        //View view = layoutInflater.inflate(R.layout.adapter_list_filmes,parent, false);
        AdapterListFilmesBinding adapterListFilmesBinding =
                AdapterListFilmesBinding.inflate(layoutInflater,parent,false);
//        int height = parent.getMeasuredHeight() / 4;
//
//        adapterListFilmesBinding.rlContainer2.setMinimumHeight(height);
//
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) adapterListFilmesBinding.getRoot().getLayoutParams();
        params.height = altura;
        adapterListFilmesBinding.getRoot().setLayoutParams(params);

        return new AdapteListFilmesViewHolder(adapterListFilmesBinding);
    }

    // 3 - Passa o conteudo para os objetos de tela
    @Override
    public void onBindViewHolder(AdapteListFilmesViewHolder holder, int position)
    {
        if(holder.mBinding!= null && holder.mBinding.ivCapaFilme != null)
        {
            Context context = holder.mBinding.ivCapaFilme.getContext();
            if(mListFilmes.get(position) != null && mListFilmes.get(position).getIdFotoCapaFilme()!= null)
                Picasso.with(context).load(mListFilmes.get(position).getIdFotoCapaFilme()).into(holder.mBinding.ivCapaFilme);
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
        void onClickList(Filme filme);
    }
}
