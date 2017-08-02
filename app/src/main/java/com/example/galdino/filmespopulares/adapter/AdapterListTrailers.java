package com.example.galdino.filmespopulares.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galdino.filmespopulares.databinding.AdapterListTrailersBinding;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Result;

import java.util.List;

/**
 * Created by Galdino on 01/08/2017.
 */

public class AdapterListTrailers extends RecyclerView.Adapter<AdapterListTrailers.ListTrailersAdapterViewHolder>
{
    // 1 - Declara as variávies
    private List<Result> mList;
    private AdapterListTrailers.ListenerAdapter mListener;

    // 2 - Popula as váriaveis pelo Construtor
    public AdapterListTrailers(List<Result> mList) {
        this.mList = mList;
    }

    // 3 - Cria o Listener
    public interface ListenerAdapter
    {
        void onItenClickListener(Result result);
    }

    // 4 - Seta o Listener
    public void setListener(ListenerAdapter listener) {
        this.mListener = listener;
    }

    // 5 - Infla o layout
    @Override
    public ListTrailersAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdapterListTrailersBinding binding = AdapterListTrailersBinding.inflate(layoutInflater, parent, false);
        return new ListTrailersAdapterViewHolder(binding);
    }

    // 6 - Arruma o retorno do count
    @Override
    public int getItemCount()
    {
        if(mList == null)
        {
            return 0;
        }
        else
        {
            return mList.size();
        }
    }


    // 7 - seta o listener e vincula o binding
    public class ListTrailersAdapterViewHolder extends RecyclerView.ViewHolder {
        private AdapterListTrailersBinding mBinding;
        public ListTrailersAdapterViewHolder(AdapterListTrailersBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(mListener != null && position != RecyclerView.NO_POSITION && mList.get(position) != null)
                    {
                        Result result = mList.get(position);
                        mListener.onItenClickListener(result);
                    }
                }
            });
        }
    }

    // 8 - Passa os valores para a tela
    @Override
    public void onBindViewHolder(ListTrailersAdapterViewHolder holder, int position)
    {
        if (holder.mBinding != null && mList.get(position) != null)
        {
            AdapterListTrailersBinding binding = holder.mBinding;
            Result result =  mList.get(position);
            //
            binding.tvNomeTrailer.setText(result.getName());
            binding.tvTipoTrailer.setText(result.getType());
        }
    }
}
