package com.example.galdino.filmespopulares.Dominio;

/**
 * Created by Galdino on 07/05/2017.
 */

public class Filme
{
    private Integer idFotoCapaFilme;
    private String dsNomeFilme;

    // Gets
    public Integer getIdFotoCapaFilme() {
        return idFotoCapaFilme;
    }
    public String getDsNomeFilme() {
        return dsNomeFilme;
    }

    // Sets
    public void setIdFotoCapaFilme(Integer idFotoCapaFilme) {
        this.idFotoCapaFilme = idFotoCapaFilme;
    }
    public void setDsNomeFilme(String dsNomeFilme) {
        this.dsNomeFilme = dsNomeFilme;
    }
}
