package com.unifacisa2.ouvidoriafase3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.unifacisa2.ouvidoriafase3.entities.Adm;
import com.unifacisa2.ouvidoriafase3.entities.Ocorrencia;
import com.unifacisa2.ouvidoriafase3.entities.Usuario;
import com.unifacisa2.ouvidoriafase3.services.AdmService;
import com.unifacisa2.ouvidoriafase3.services.UsuarioService;
import com.unifacisa2.ouvidoriafase3.util.Teclado;

@SpringBootApplication
public class Ouvidoriafase3Application implements CommandLineRunner {

	@Autowired
	AdmService admService;

	@Autowired
	UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(Ouvidoriafase3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		boolean sair = true;

		while (sair) {
			System.out.println("\n====== Menu Login ======\n1. Cadastro\n2. Login\n3. Sair");
			int opc = Teclado.tInt();
			switch (opc) {
				case 1:
					System.out.println("\n====== Menu Cadastro ======\n1. Administrador\n2. Usuario\n3. Sair");
					int opc2 = Teclado.tInt();
					System.out.print("Digite seu Nome: ");
					String name = Teclado.tString();
					System.out.print("Digite seu UserName: ");
					String userName = Teclado.tString();
					System.out.print("Digite sua Senha: ");
					String senha = Teclado.tString();
					if (opc2 == 1) {
						admService.addPessoa(new Adm(name, userName, senha));
					} else if (opc2 == 2) {
						usuarioService.addPessoa(new Usuario(name, userName, senha));

					} else {
						System.err.println("DIgite uma opcao valida");
					}
					break;
				case 2:
					System.out.println("\n====== Login ======");
					System.out.print("Digite seu UserName: ");
					String userName2 = Teclado.tString();
					System.out.print("Digite sua Senha: ");
					String senha2 = Teclado.tString();
					if (admService.checkLogin(userName2, senha2)) {
						boolean sair2 = true;
						while (sair2) {
							System.out.println(
									"\n====== Menu Adm ======\n1. Vizualizar ocorrencias\n2. Deletar ocorrencia\n3. Sair");
							int opc3 = Teclado.tInt();
							switch (opc3) {
								case 1:
									admService.vizualizarOcorrencia();
									break;
								case 2:
									System.out
											.println("\nQual o metodo de apagar:\n1. Especifico\n2. Geral\n3. Voltar");
									int opt3 = Teclado.tInt();
									switch (opt3) {
										case 1:
											admService.vizualizarOcorrencia();
											int id = Teclado.tInt();
											admService.apagar((long) id);
											break;
										case 2:
											admService.removeAll();
											break;
										case 3:
											break;
										default:
											System.out.println("Digite uma opcao valida do menu");
											break;
									}
									break;
								case 3:
									System.out.println("\nLogin Adm encerrado\n");
									sair2 = false;
									break;
								default:
									System.out.println("\nDigite uma opção do menu\n");
									break;
							}
						}
					} else if (usuarioService.checkLogin(userName2, senha2)) {
						boolean sair2 = true;
						while (sair2) {
							System.out.println(
									"\n====== Menu Usuario ======\n1. Vizualizar ocorrencias\n2. Cadastrar Ocorrencia\n3. Sair");
							int opc3 = Teclado.tInt();
							switch (opc3) {
								case 1:
									usuarioService.visualizarMinhasOcorrencias(userName2);
									break;
								case 2:
									System.out.println(
											"\nQual o tipo de ocorrencia:\n1. Reclamação\n2. Elogio\n3. Sugestao\n4. Voltar");
									int opc4 = Teclado.tInt();
									switch (opc4) {
										case 1:
											System.out.println("\nDigite a reclamacao: ");
											String recla = Teclado.tString();
											usuarioService.addOcorrenica(new Ocorrencia(recla, "Reclamacao",
													usuarioService.findUser(userName2)));
											break;
										case 2:
											System.out.println("\nDigite o elogio: ");
											String elo = Teclado.tString();
											usuarioService.addOcorrenica(new Ocorrencia(elo, "Elogio",
													usuarioService.findUser(userName2)));
											break;
										case 3:
											System.out.println("\nDigite a sugestao: ");
											String suges = Teclado.tString();
											usuarioService.addOcorrenica(new Ocorrencia(suges, "Sugestao",
													usuarioService.findUser(userName2)));
											break;
										case 4:
											break;
										default:
											System.out.println("\nDigite uma opçao do menu valida\n");
											break;
									}
									break;
								case 3:
									sair2 = false;
									System.out.println("\nLogin de usuario encerrado\n");
									break;
								default:
									System.out.println("\nDigite uma opcao do menu valida\n");
									break;
							}
						}
					} else {
						System.out.println("\nNenhum adm ou usario foi encontrado\n");
					}
					break;
				case 3:
					sair = false;
					System.out.println("\nSistema encerrado\n");
					break;
				default:
					System.out.println("\nDigite uma opcao valida\n");
					break;
			}
		}

	}

}
