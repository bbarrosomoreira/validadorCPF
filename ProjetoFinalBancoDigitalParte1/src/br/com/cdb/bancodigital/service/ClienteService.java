package br.com.cdb.bancodigital.service;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.cdb.bancodigital.dao.ClienteDAO;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.enums.Categoria;

public class ClienteService {

	ClienteDAO clienteDao = new ClienteDAO();

	private boolean cepVazio;
	private boolean cepInvalido;
	private boolean dataVazia;
	private boolean dataInvalida;
	private boolean menorIdade;
	private boolean nomeVazio;
	private boolean nomeInvalido;
	private boolean clienteExistente;
	private boolean cpfVazio;
	private boolean tamanhoErrado;
	private boolean mesmosNumeros;
	private boolean falhaAutentificacao;

	public String addCliente(String cpf, String nome, LocalDate dataNascimento, String rua, int numero,
			String complemento, String cidade, String estado, String cep, Categoria categoria) {
		if (!validarCPF(cpf)) {
			if (clienteExistente)
				return "CPF já cadastrado";
			if (cpfVazio)
				return "O campo do CPF precisa ser preenchido";
			if (tamanhoErrado)
				return "O campo de CPF deve conter 11 dígitos numéricos.";
			if (mesmosNumeros)
				return "O campo de CPF deve conter 11 dígitos numéricos distintos.";
			if (falhaAutentificacao)
				return "CPF inválido!";
		}

		if (!validarNome(nome))
			if (nomeVazio)
				return "O campo do nome precisa ser preenchido";
			if(nomeInvalido)
				return "O valor informado tem caracteres especiais. O nome deve conter apenas letras e espaços.";

		if (!validarDataNascimento(dataNascimento))
			if (dataVazia)
				return "O campo de data precisa ser preenchido";
			if(dataInvalida)
				return "Esta é uma data futura. Ajuste para uma data correta";
			if(menorIdade)
				return "Menor de idade - O cliente deve ser maior de idade (≥ 18 anos) para fazer seu cadastro.";

		if (!validarCep(cep))
			if (cepVazio)
				return "O campo do cep precisa ser preenchido";
			if (tamanhoErrado)
				return "O campo de CEP deve conter 8 dígitos numéricos.";
			if(cepInvalido)
				return "O valor informado tem caracteres inválidos. O cep deve conter apenas números.";

		
		//cpf = formatarCpf(cpf);
		//cep = formatarCep(cep);
		clienteDao.addCliente(cpf, nome, dataNascimento, rua, numero, complemento, cidade, estado, cep, categoria);
		return "Cliente cadastrado com sucesso!";
	}

	private boolean validarCep(String cep) {
		if(cep == null || cep.trim().isEmpty()) {
			cepVazio = true;
			return false;
		}
		cep = cep.trim();
		// VERIFICAR SE CONTEM 8 DIGITOS
		if (cep.length() != 8) {
			tamanhoErrado = true;
			return false;
		}
		
		if(!cep.matches("\\d{8}")) {
			cepInvalido = true;
			return false;
		}
		return true;
	}
	
//	private String formatarCep(String cep) {
//		if(!validarCep(cep))
//		{
//			throw new IllegalArgumentException("CEP inválido! Deve conter exatamente 8 dígitos numéricos.");
//		}
//		return cep.substring(0,5) + "-" + cep.substring(5);
//	}
	
 	private boolean validarDataNascimento(LocalDate dataNascimento) {
 		if(dataNascimento == null) {
 			dataVazia = true;
 			return false;
 		}
 		if(dataNascimento.isAfter(LocalDate.now())) {
 			dataInvalida = true;
 			return false;
 		}
 		//VERIFICAR IDADE MINIMA DE 18 ANOS
 		LocalDate dataMinima = LocalDate.now().minusYears(18);
 		if(dataNascimento.isBefore(dataMinima) || dataNascimento.equals(dataMinima)) {
 			return true;
 		}
 		else {
			menorIdade = true;
			return false;
 		}
	}

	private boolean validarNome(String nome) {
		if(nome == null || nome.trim().isEmpty()) {
			nomeVazio = true;
			return false;
		}
		nome = nome.trim();
		if(!nome.matches("[a-zA-ZÀ-ÿ\\s]{2,100}")) {
			nomeInvalido = true;
			return false;
		}
		
		return true;
	}

	private boolean validarCPF(String cpf) {
		// VERIFICAR SE CPF JÁ FOI CADASTRADO NO SISTEMA
		for (Cliente c : clienteDao.getListaDeClientes()) {
			if (c.getCpf() == cpf) {
				clienteExistente = true; // retornar msg de já existente
				return false;
			}
		}

		// VERIFICAR SE O CAMPO ESTÁ VAZIO
		if (cpf == null || cpf.isBlank()) {
			cpfVazio = true; // retornar msg de campo vazio
			return false;
		}

		// REMOVER CARACTERES NAO NUMERICOS
		cpf = cpf.replaceAll("[^0-9]", "");

		// VERIFICAR SE CONTEM 11 DIGITOS
		if (cpf.length() != 11) {
			tamanhoErrado = true;
			return false;
		}

		// VERIFICAR SE TODOS OS DIGITOS SÃO IGUAIS
		if (cpf.matches("(\\d)\\1{10}")) {
			mesmosNumeros = true;
			return false;
		}

		// AUTENTIFICAÇÃO
		try {
			int[] peso = { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
			int soma = 0;
			for (int i = 0; i < 9; i++) {
				soma += (cpf.charAt(i) - '0') * peso[i];
			}
			int primeiroDigitoVerificador = 11 - (soma % 11);
			if (primeiroDigitoVerificador >= 10) {
				primeiroDigitoVerificador = 0;
			}

			soma = 0;
			int[] peso2 = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
			for (int i = 0; i < 10; i++) {
				soma += (cpf.charAt(i) - '0') * peso2[i];
			}
			int segundoDigitoVerificador = 11 - (soma % 11);
			if (segundoDigitoVerificador >= 10) {
				segundoDigitoVerificador = 0;
			}

			// Verificar se os dígitos calculados são iguais aos dígitos do CPF
			if ((primeiroDigitoVerificador == (cpf.charAt(9) - '0'))
					&& (segundoDigitoVerificador == (cpf.charAt(10) - '0'))) {
				return true;
			}

		} catch (Exception e) {
			falhaAutentificacao = true;
			return false;
		}

		return true;
	}

//	private String formatarCpf(String cpf) {
//		if(!validarCPF(cpf))
//		{
//			throw new IllegalArgumentException("CPF inválido! Deve conter exatamente 11 dígitos numéricos.");
//		}
//		return cpf.substring(0,3) + "." + cpf.substring(3,6) + "." + cpf.substring(6,9) + "-" + cpf.substring(9);
//	}
	
	
	public Cliente buscarClientePorId(int clienteId) {
		return clienteDao.buscarCliente(clienteId);
	}
	
	public ArrayList<Cliente> getListaDeClientes() {
		return clienteDao.getListaDeClientes();
	}
	
	
}