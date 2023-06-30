package com.unifacisa2.ouvidoriafase3.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.unifacisa2.ouvidoriafase3.entities.Ocorrencia;
import com.unifacisa2.ouvidoriafase3.entities.Usuario;
import com.unifacisa2.ouvidoriafase3.exception.DomainExcepiton;
import com.unifacisa2.ouvidoriafase3.repositories.OcorrenciaRepository;
import com.unifacisa2.ouvidoriafase3.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public void addPessoa(Usuario usuario) {
        try {
            if (checkUser(usuario.getNomeUsuario())) {
                System.out.println("\nuserName ja cadastrado");
            } else {
                usuarioRepository.save(usuario);
                System.out.println("\nCadastrado");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void addOcorrenica(Ocorrencia ocorrencia) {
        try {
            ocorrenciaRepository.save(ocorrencia);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean checkUser(String nameUser) {
        if (usuarioRepository.existsById(nameUser)) {
            return true;
        }
        return false;
    }

    public Boolean checkLogin(String nameUser, String senha) {
        try {
            Usuario usuario = usuarioRepository.findById(nameUser).get();
            if (usuario == null) {
                return false;
            }
            if (usuario.getSenha().equals(senha)) {
                return true;
            } else {
                throw new DomainExcepiton("Senha incorreta");
            }
        } catch (NoSuchElementException e) {
            // System.err.println("Senha ou NameUser errada");
        } catch(DomainExcepiton e){
            System.out.println(e.getMessage());
        }catch (InvalidDataAccessApiUsageException e) {
            System.err.println("Nao foi possivel verificar userName ou senha");
        } catch (NullPointerException e) {
            System.err.println("Digite algum caracter");
        }
        return false;
    }

    public Usuario getUser(String filter) {
        return usuarioRepository.findById(filter).get();
    }

    public void visualizarMinhasOcorrencias(String nameUser) {
        List<Ocorrencia> list = (List<Ocorrencia>) ocorrenciaRepository.findAll();
        System.out.println(String.format("====== Ocorrencias de %s ======\n", nameUser));
        for (Ocorrencia ocorrencia : list) {
            if (ocorrencia.getUsuario().getNomeUsuario().equals(nameUser)) {
                System.out.println(String.format("%s - %s\n", ocorrencia.getTipo(), ocorrencia.getDescricao()));
            }
        }
        System.out.println();
    }

    public Usuario findUser(String nameUser) {
        try {
            Usuario usuario = usuarioRepository.findById(nameUser).get();
            return usuario;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;

    }

}
