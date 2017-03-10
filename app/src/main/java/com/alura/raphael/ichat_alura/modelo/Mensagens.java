package com.alura.raphael.ichat_alura.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by raphael on 08/03/2017.
 */

public class Mensagens implements Serializable{
    @SerializedName("text")
    private String texto;
    private int id;

    public Mensagens(int id,String texto) {
        this.texto = texto;
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }
}
