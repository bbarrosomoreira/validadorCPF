package br.com.cdb.bancodigital.service;

import java.util.ArrayList;
import java.util.Random;

import br.com.cdb.bancodigital.dao.CartaoDAO;
import br.com.cdb.bancodigital.entity.Cartao;
import br.com.cdb.bancodigital.entity.CartaoCredito;
import br.com.cdb.bancodigital.entity.CartaoDebito;
import br.com.cdb.bancodigital.entity.ContaBancaria;
import br.com.cdb.bancodigital.enums.Categoria;
//import br.com.cdb.bancodigital.enums.StatusCartao;

public class CartaoService {

	private CartaoDAO cartaoDao = new CartaoDAO();
	private int contadorGlobal = 1;

	public String addCartaoCredito(ContaBancariaService contaService, int contaId) {

		ContaBancaria conta = contaService.buscarContaPorId(contaId);
		if (conta == null) {
			return "Erro: Cliente não encontrado.";
		}

		CartaoCredito ccr = new CartaoCredito(); // POSSO CRIAR OBJETO AQUI?
		ccr.setConta(conta);
		ccr.setDataCriacao();

		// criar uma forma de senha segura de 4 digitos
		ccr.setSenha(1234);

		// CRIANDO NUMERO DO CARTAO
		Random random = new Random();
		int n1 = random.nextInt(2);
		int codMasterCard = 50;
		if (n1 == 0) {
			codMasterCard = 51;
		} else {
			codMasterCard = 55;
		}
//		int soma = 0;
//		for (int i =0; i<15; i++) {
//			int n2 = random.nextInt(10);
//			soma += n2 * (10 ^ i);
//		}

//		long numeroCartao = ((codMasterCard * 100000000000000)+soma); //PENSAR SOBRE STRING OU LONG
		long numeroCartao = ((codMasterCard * 1000) + contadorGlobal);
		contadorGlobal++;
		ccr.setNumCartao(numeroCartao);

		// SETANDO TX. UTILIZAÇÃO

		// SETANDO LIMITE DE CREDITO
		if (contaService.buscarContaPorId(contaId).getCliente().getCategoria() == Categoria.COMUM)
			ccr.setLimiteCredito(1000.00);
		else if (contaService.buscarContaPorId(contaId).getCliente().getCategoria() == Categoria.SUPER)
			ccr.setLimiteCredito(5000.00);
		else if (contaService.buscarContaPorId(contaId).getCliente().getCategoria() == Categoria.PREMIUM)
			ccr.setLimiteCredito(10000.00);

		cartaoDao.addCartaoCredito(ccr);
		return "Cartão de Crédito criado com sucesso!";
	}

	public String addCartaoDebito(ContaBancariaService contaService, int contaId) {

		ContaBancaria conta = contaService.buscarContaPorId(contaId);
		if (conta == null) {
			return "Erro: Cliente não encontrado.";
		}

		CartaoDebito cdb = new CartaoDebito(); // POSSO CRIAR OBJETO AQUI?
		cdb.setConta(conta);
		cdb.setDataCriacao();

		// criar uma forma de senha segura de 4 digitos
		cdb.setSenha(1234);

		// CRIANDO NUMERO DO CARTAO
		Random random = new Random();
		int n1 = random.nextInt(2);
		int codMasterCard = 50;
		if (n1 == 0) {
			codMasterCard = 51;
		} else {
			codMasterCard = 55;
		}
//		int soma = 0;
//		for (int i =0; i<15; i++) {
//			int n2 = random.nextInt(10);
//			soma += n2 * (10 ^ i);
//		}

//		long numeroCartao = ((codMasterCard * 100000000000000)+soma); //PENSAR SOBRE STRING OU LONG
		long numeroCartao = ((codMasterCard * 1000) + contadorGlobal);
		contadorGlobal++;
		cdb.setNumCartao(numeroCartao);

		// SETANDO LIMITE DIARIO
		if (contaService.buscarContaPorId(contaId).getCliente().getCategoria() == Categoria.COMUM)
			cdb.setLimiteDiario(500);
		else if (contaService.buscarContaPorId(contaId).getCliente().getCategoria() == Categoria.SUPER)
			cdb.setLimiteDiario(2500.00);
		else if (contaService.buscarContaPorId(contaId).getCliente().getCategoria() == Categoria.PREMIUM)
			cdb.setLimiteDiario(5000.00);

		cartaoDao.addCartaoDebito(cdb);
		return "Cartão de Crédito criado com sucesso!";
	}

	public ArrayList<Cartao> getListaDeCartoes() {
		return cartaoDao.getListaCartoes();
	}

//	public boolean ativarCartao(int codigoStatus, int cartaoId) {
//		// add validacao caso nao encontre id
//		status = StatusCartao.valueOf(codigoStatus);
//		cartaoDao.buscarCartao(cartaoId).setStatus(status);
//		return true;
//	}

	public Cartao buscarCartao(int id) {
		return cartaoDao.buscarCartao(id);
	}

	public String ressetarLimiteDeCreditoMensal(CartaoCredito ccr) {
		ccr.setLimiteCreditoAtual(ccr.getLimiteCredito());
		return "Limite do cartão de crédito " + ccr.getNumCartao()
				+ " foi renovado com sucesso. \n Já pode continuar usando seu cartão para suas compras do dia a dia.";
	}

	public String ressetarLimiteDeDebitoDiario(CartaoDebito cdb) {
		cdb.setLimiteDiarioAtual(cdb.getLimiteDiario());
		return "Limite do cartão de débito " + cdb.getNumCartao()
				+ " foi renovado com sucesso. \n Já pode continuar usando seu cartão para suas compras do dia a dia.";
	}
}
