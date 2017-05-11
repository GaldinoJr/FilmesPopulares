package com.example.galdino.filmespopulares.Dominio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Galdino on 07/05/2017.
 */

public class Filme
{

//    private Integer idFotoCapaFilme;
//    private String dsNomeFilme;
//
//    // Gets
//    public Integer getIdFotoCapaFilme() {
//        return idFotoCapaFilme;
//    }
//    public String getDsNomeFilme() {
//        return dsNomeFilme;
//    }
//
//    // Sets
//    public void setIdFotoCapaFilme(Integer idFotoCapaFilme) {
//        this.idFotoCapaFilme = idFotoCapaFilme;
//    }
//    public void setDsNomeFilme(String dsNomeFilme) {
//        this.dsNomeFilme = dsNomeFilme;
//    }

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
