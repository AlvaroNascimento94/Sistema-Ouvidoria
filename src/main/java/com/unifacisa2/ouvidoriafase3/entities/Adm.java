package com.unifacisa2.ouvidoriafase3.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADM")
public class Adm extends Pessoa{

    public Adm() {
    }

    public Adm(String nome, String nomeUsuario, String senha) {
        super(nome, nomeUsuario, senha);
        
    }

    
}