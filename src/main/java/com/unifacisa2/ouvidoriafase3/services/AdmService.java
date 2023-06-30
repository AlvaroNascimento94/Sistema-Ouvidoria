package com.unifacisa2.ouvidoriafase3.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.unifacisa2.ouvidoriafase3.entities.Adm;
import com.unifacisa2.ouvidoriafase3.entities.Ocorrencia;
import com.unifacisa2.ouvidoriafase3.entities.Usuario;
import com.unifacisa2.ouvidoriafase3.exception.DomainExcepiton;
import com.unifacisa2.ouvidoriafase3.repositories.AdmRepository;
import com.unifacisa2.ouvidoriafase3.repositories.OcorrenciaRepository;
import com.unifacisa2.ouvidoriafase3.repositories.UsuarioRepository;

@Service
public class AdmService {

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AdmRepository admRepository;

    public void addPessoa(Adm adm) {
        try {
            if (checkUser(adm.getNomeUsuario())) {
                System.out.println("\nuserName ja cadastrado");
            } else {
                admRepository.save(adm);
                System.out.println("Cadastrado");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean checkUser(String nameUser) {
        if (admRepository.existsById(nameUser)) {
            return true;
        }
        return false;
    }

    public Boolean checkLogin(String nameUser, String senha) {
        try {
            Adm adm = admRepository.findById(nameUser).get();
            if (adm == null) {
                return false;
            }
            if (adm.getSenha().equals(senha)) {
                return true;
            } else {
                throw new DomainExcepiton("Senha incorreta");
            }
        } catch (NoSuchElementException e) {
            // System.err.println("Senha ou NameUser errada");
        } catch (InvalidDataAccessApiUsageException e) {
            System.err.println("Nao foi possivel verificar userName ou senha");
        } catch (DomainExcepiton e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Digite algum caracter");
        }
        return false;
    }

    // teste 2
    public void vizualizarOcorrencia() {
        // fazer um loop percorrendo a lista de usuario
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        List<Ocorrencia> list = (List<Ocorrencia>) ocorrenciaRepository.findAll();
        try {
            System.out.println("\n===== Lista de todas Ocorrencias =====\n");

            for (Usuario usuario : usuarios) {
                System.out.println(String.format("Usuario: %s\n", usuario.getNome()));
                if (!usuario.getOcorrencias().isEmpty()) {
                    for (Ocorrencia ocorrencia : list) {
                        if (ocorrencia.getUsuario().getNomeUsuario().equals(usuario.getNomeUsuario())) {
                            System.out.println(
                                    String.format("ID: %d - Tipo: %s\nOcorrencia: %s\n", ocorrencia.getId(),
                                            ocorrencia.getTipo(), ocorrencia.getDescricao()));
                        }
                    }
                } else {
                    System.out.println("Lista vazia\n");
                }

            }

        } catch (DomainExcepiton e) {
            System.out.println(e.getMessage());
        }

    }

    public void apagar(Long id) {
        try {
            ocorrenciaRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Erro ao deletar");
        }
    }

    public void removeAll() {
        try {
            ocorrenciaRepository.deleteAll();
        } catch (Exception e) {
            System.err.println("Erro ao deletar");
        }
    }

}
