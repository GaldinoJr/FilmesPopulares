package com.example.galdino.filmespopulares.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galdino.filmespopulares.databinding.AdapterListComentariosBinding;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Result;

import java.util.List;

/**
 * Created by Galdino on 17/08/2017.
 */

public class AdapterListComentario extends RecyclerView.Adapter<AdapterListComentario.ViewHolder>
{
    // 1 - Parametros
    private List<Result> mList;
    // 2 - Construtor
    public AdapterListComentario(List<Result> mList) {
        this.mList = mList;
    }
    // 3 - cria o viewHolder
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private AdapterListComentariosBinding mBinding;
        public ViewHolder(AdapterListComentariosBinding binding)
        {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
    // infla o holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        AdapterListComentariosBinding binding =
                AdapterListComentariosBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        if(mList != null && mList.get(position) != null && holder.mBinding != null) {
            Result result = mList.get(position);

            holder.mBinding.tvNomePessoa.setText(result.getAuthor());
            holder.mBinding.tvComentario.setText(result.getContent());
        }
    }

    @Override
    public int getItemCount()
    {
        if(mList == null) {
            return 0;
        }
        else
        {
            return mList.size();
        }
    }
}
