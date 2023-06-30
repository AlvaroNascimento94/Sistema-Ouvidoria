package com.unifacisa2.ouvidoriafase3.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("USR")
public class Usuario extends Pessoa{


    public Usuario() {
    }

    public Usuario(String nome, String nomeUsuario, String senha) {
        super(nome, nomeUsuario, senha);
    }


}
