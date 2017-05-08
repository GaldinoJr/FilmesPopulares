package com.example.galdino.filmespopulares.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

    public AdapterListFilmes(List<Filme> mListFilmes) {
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
}
