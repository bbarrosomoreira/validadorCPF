package br.com.cdb.bancodigital.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import br.com.cdb.bancodigital.entity.Cartao;
import br.com.cdb.bancodigital.entity.CartaoCredito;
import br.com.cdb.bancodigital.entity.CartaoDebito;
import br.com.cdb.bancodigital.entity.ContaBancaria;
import br.com.cdb.bancodigital.entity.ContaCorrente;
import br.com.cdb.bancodigital.entity.ContaPoupanca;
import br.com.cdb.bancodigital.enums.Categoria;
import br.com.cdb.bancodigital.service.CartaoService;
import br.com.cdb.bancodigital.service.ClienteService;
import br.com.cdb.bancodigital.service.ContaBancariaService;

public class Main {

	// Cadastro de cliente
	// Interação
	//
	//
	//
	//
	//

	public static void main(String[] args) {

		ClienteService clienteService = new ClienteService();
		ContaBancariaService contaService = new ContaBancariaService();
		CartaoService cartaoService = new CartaoService();
		Scanner input = new Scanner(System.in);
		Categoria categoriaCliente;
		int mesAdicional = 0;
		int opcao1 = 100;

		while (opcao1 != 0) {
			displayMenuPrincipal();
			opcao1 = input.nextInt();
			input.nextLine();

			switch (opcao1) {
			case 1:
				System.out.println("Insira o CPF do novo cliente - apenas números:");
				String cpf = input.nextLine();
				System.out.println("Insira o nome completo do novo cliente:");
				String nome = input.nextLine();
				System.out.println("Insira a data de nascimento do novo cliente - DD/MM/AAAA");
				LocalDate dataNascimento = LocalDate.parse(input.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

				System.out.println("Insira o endereço do novo cliente:");
				System.out.println("Rua:");
				String rua = input.nextLine();
				System.out.println("Número - para endereço sem número, digite (0):");
				int numero = input.nextInt();
				input.nextLine();
				System.out.println("Complemento:");
				String complemento = input.nextLine();
				System.out.println("Cidade:");
				String cidade = input.nextLine();
				System.out.println("Estado - sigla de 2 letras:");
				String estado = input.nextLine();
				System.out.println("CEP - apenas números:");
				String cep = input.nextLine();

				System.out.println("Insira a categoria do novo cliente - Comum, Super, Premium");
				String categoria = input.nextLine();
				categoriaCliente = Categoria.valueOf(categoria.toUpperCase());

				String resultadoCliente = clienteService.addCliente(cpf, nome, dataNascimento, rua, numero, complemento,
						cidade, estado, cep, categoriaCliente);
				System.out.println(resultadoCliente);

//				System.out.println("Id do cliente: "+clienteService);
				break;

			case 2:
				System.out.println("Digite o id do cliente:");
				int clienteId = input.nextInt();
				int opcao2 = 100;
				while (opcao2 != 0) {
					displayMenuCliente();
					opcao2 = input.nextInt();
					input.nextLine();

					switch (opcao2) {

					case 1:
						String resultadoCC = contaService.addContaCorrente(clienteService, clienteId);
						System.out.println(resultadoCC);
						break;
					case 2:
						String resultadoCP = contaService.addContaPoupanca(clienteService, clienteId);
						System.out.println(resultadoCP);
						break;
					case 3:
						for (ContaBancaria c : contaService.getListaDeContas()) {
							System.out.println(c.getContaId() + " - " + c.getTipo());
						}
						int opcao3 = 100;
						while (opcao3 != 0) {
							displayMenuConta();
							opcao3 = input.nextInt();
							input.nextLine();

							switch (opcao3) {

							case 1:
								System.out.println("Digite o id da conta que deseja ver o saldo.");
								int contaId = input.nextInt();
								input.nextLine();
								System.out.println(contaService.buscarContaPorId(contaId).getSaldo());
								break;
							case 2:
								System.out.println("Digite o id da conta que deseja fazer o deposito.");
								contaId = input.nextInt();
								input.nextLine();
								System.out.println("Digite o o valor a ser depositado.");
								int valorDeposito = input.nextInt();
								input.nextLine();
								System.out.println(contaService.buscarContaPorId(contaId).depositar(valorDeposito));
								break;
							case 3:
								System.out.println("Digite o id da conta da qual irá realizar o PIX.");
								contaId = input.nextInt();
								input.nextLine();
								System.out.println("Digite o id da conta de destido do PIX.");
								int destinoID = input.nextInt();
								input.nextLine();
								ContaBancaria destino = contaService.buscarContaPorId(destinoID);
								System.out.println("Digite o o valor do PIX.");
								int valorPIX = input.nextInt();
								input.nextLine();
								System.out.println(
										contaService.buscarContaPorId(contaId).transferirPIX(valorPIX, destino));
								break;
							case 4:
								System.out.println("Digite o id da conta da qual deseja sacar.");
								contaId = input.nextInt();
								input.nextLine();
								System.out.println("Digite o o valor do saque.");
								int valorSaque = input.nextInt();
								input.nextLine();
								System.out.println(
										((ContaCorrente) contaService.buscarContaPorId(contaId)).sacar(valorSaque));
								break;
							case 5:
								System.out.println("Digite o id da conta que deseja ver a taxa ou rendimento.");
								contaId = input.nextInt();
								input.nextLine();

								if (contaService.buscarContaPorId(contaId) instanceof ContaCorrente) {
									System.out.println(((ContaCorrente) contaService.buscarContaPorId(contaId))
											.getTaxaManutencaoMensal());
								} else if (contaService.buscarContaPorId(contaId) instanceof ContaPoupanca) {
									System.out.println(((ContaPoupanca) contaService.buscarContaPorId(contaId))
											.getRendimentoAnual());
								}

								break;
							case 6:
								int opcao4 = 100;
								while (opcao4 != 0) {
									displayMenuCartao();
									opcao4 = input.nextInt();
									input.nextLine();

									switch (opcao4) {

									case 1:
										System.out.println(
												"Digite o id da conta que deseja adicionar o cartão de crédito.");
										contaId = input.nextInt();
										input.nextLine();
										System.out.println(cartaoService.addCartaoCredito(contaService, contaId));
										break;
									case 2:
										System.out.println(
												"Digite o id da conta que deseja adicionar o cartão de débito.");
										contaId = input.nextInt();
										input.nextLine();
										System.out.println(cartaoService.addCartaoDebito(contaService, contaId));
										break;
									case 3:
										for (Cartao c : cartaoService.getListaDeCartoes()) {

											System.out.println(c.getNumCartao() + " - " + c.getTipoCartao() + " - "
													+ c.getStatus());
										}
										break;
									case 4:
										// menu seguro
										break;

									case 0:
										System.out.println("*** Voltando ao menu anterior ***");
										break;

									default:
										System.out.println("Opção inválida!\n");
									}
								}
								break;

							case 7:
								// rotinaMensal();
								LocalDate dataAtual = LocalDate.now();
								mesAdicional = mesAdicional + 1;
								LocalDate mesCorrente = dataAtual.plusMonths(mesAdicional);
								
								for (ContaBancaria cb : contaService.getListaDeContas()) {
									if(ChronoUnit.MONTHS.between(cb.getDataCriacao(), mesCorrente) > 0) {
										if (cb instanceof ContaCorrente) {
											System.out.println(contaService.debitarTaxaManutencao((ContaCorrente) cb));
										}
										else if (cb instanceof ContaPoupanca) {
											System.out.println(contaService.renderMensalPoupanca((ContaPoupanca) cb));
										}
									else {
										System.out.println("Ainda não se passou 1 mês desde a criação da conta "+ cb.getContaId());
										}
									}
								}
								
								for (Cartao c: cartaoService.getListaDeCartoes()) {
									if(ChronoUnit.MONTHS.between(c.getDataCriacao(), mesCorrente) > 0) {
										if (c instanceof CartaoCredito) {
											System.out.println(cartaoService.ressetarLimiteDeCreditoMensal((CartaoCredito) c));
										}
										else if (c instanceof CartaoDebito) {
											System.out.println(cartaoService.ressetarLimiteDeDebitoDiario((CartaoDebito) c));
										}
									else {
										System.out.println("Ainda não se passou 1 mês desde a criação do cartão.");
										}
									}
								}
								
								break;

							case 0:
								System.out.println("*** Voltando ao menu anterior ***");
								break;

							default:
								System.out.println("Opção inválida!\n");
							}
						}

						break;
					case 0:
						System.out.println("*** Voltando ao Menu Principal ***");
						break;

					default:
						System.out.println("Opção inválida!\n");
					}
				}
				break;

			case 3:
				// nome
				break;

			case 0:
				System.out.println("*** Menu encerrado ***");
				break;

			default:
				System.out.println("Opção inválida!\n");

			}

		}
		input.close();
	}
	
	public static void cadastrarUsuario() {
		
	}

	public static void displayMenuPrincipal() {
		System.out.println("*** Menu Principal ***");
		System.out.println("1. Adicionar novo cliente");
		System.out.println("2. Acessar cliente pela id");
		System.out.println("3. Acessar cliente pelo nome");
		System.out.println("0. Encerrar");
	}

	public static void displayMenuCliente() {
		System.out.println("*** Menu Cliente ***");
		System.out.println("1. Adicionar nova conta corrente");
		System.out.println("2. Adicinar nova conta poupança");
		System.out.println("3. Acessar contas do cliente");
		System.out.println("0. Retornar ao menu principal");
	}

	public static void displayMenuConta() {
		System.out.println("*** Menu da Conta ***");
		System.out.println("1. Mostrar o saldo");
		System.out.println("2. Fazer um depósito em conta");
		System.out.println("3. Fazer uma transferência PIX");
		System.out.println("4. Fazer um saque");
		System.out.println("5. Mostrar a taxa ou rendimento da conta");
		System.out.println("6. Acessar o menu de cartões");
		System.out.println("7. Rodar rotina de 1 mês"); // AVALIAR ONDE DEIXAR ISSO
		System.out.println("0. Retornar ao menu anterior");
	}

	public static void displayMenuCartao() {
		System.out.println("*** Menu de Cartão ***");
		System.out.println("1. Adicionar novo cartão de crédito");
		System.out.println("2. Adicinar novo cartão de débito");
		System.out.println("3. Acessar cartões do cliente");
		System.out.println("4. Acessar o menu de seguros");
		System.out.println("0. Retornar ao menu anterior");
	}

	public static void displayMenuSeguros() {
		System.out.println("*** Menu de Seguros ***");
		System.out.println("1. Adicionar Seguro Viagem");
		System.out.println("2. Adicinar Seguro de Fraude");
		System.out.println("3. Acessar seguros do cliente");
		System.out.println("0. Retornar ao menu anterior");
	}

	public static void displayMenuCartao2() {
		System.out.println("1. Ativar/Desativar cartão");
		System.out.println("2. Alterar senha do cartão");
		System.out.println("3. Ajustar limite do cartão");
		System.out.println("4. Realizar pagamento");
		System.out.println("0. Retornar ao menu anterior");
	}
}
