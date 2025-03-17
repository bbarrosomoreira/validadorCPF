package br.com.cdb.bancodigital.service;

import java.util.ArrayList;

import br.com.cdb.bancodigital.dao.ContaBancariaDAO;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.ContaBancaria;
import br.com.cdb.bancodigital.entity.ContaCorrente;
import br.com.cdb.bancodigital.entity.ContaPoupanca;
import br.com.cdb.bancodigital.enums.Categoria;

public class ContaBancariaService {
	
	private ContaBancariaDAO contaBancariaDao = new ContaBancariaDAO();
//	private int contadorGlobal = 1;
	
	public String addContaCorrente(ClienteService clienteService, int clienteId) //VER COMO VAI FICAR AJUSTES
	{
		Cliente cliente = clienteService.buscarClientePorId(clienteId);
		if (cliente == null) {
			return "Erro: Cliente não encontrado.";
		}
		
		ContaCorrente cc = new ContaCorrente(); //POSSO CRIAR OBJETO AQUI?
//		String cpfInicio = cliente.getCpf().substring(0, 3);
//		String idComoString = cpfInicio + "00" + contadorGlobal;
//		int idCorrente = Integer.parseInt(idComoString);
		
//		try {
//			int idCorrente = Integer.parseInt(idComoString);
//		} catch (NumberFormatException e) {
//			throw new IllegalArgumentException ("Erro ao gerar o ID da conta corrente.");
//		}
//		contadorGlobal++;
		cc.setContaId(111);
		cc.setSaldo(100);
		cc.setCliente(cliente);
		cc.setDataCriacao();
		contaBancariaDao.addContaCorrente(cc);
		
		if(cc.getCliente().getCategoria() == Categoria.COMUM)
			cc.setTaxaManutencaoMensal(12.00);
		else if (cc.getCliente().getCategoria() == Categoria.SUPER)
			cc.setTaxaManutencaoMensal(8.00);
		else if (cc.getCliente().getCategoria() == Categoria.PREMIUM)
			cc.setTaxaManutencaoMensal(0.00);
		return "Conta Corrente "+cc.getContaId()+" adicionada com sucesso.";
	}
	
	public String addContaPoupanca(ClienteService clienteService, int clienteId)
	{
		Cliente cliente = clienteService.buscarClientePorId(clienteId);
		if (cliente == null) {
			return "Erro: Cliente não encontrado.";
		}
		
		ContaPoupanca cp = new ContaPoupanca();
//		int idPoupanca = Integer.parseInt(cliente.getCpf().substring(0,3)+"00"+contadorGlobal+""+500);
//		contadorGlobal++;
		cp.setContaId(111500);
		cp.setSaldo(100);
		cp.setCliente(cliente);
		cp.setDataCriacao();
		contaBancariaDao.addContaPoupanca(cp);
		if(cp.getCliente().getCategoria() == Categoria.COMUM)
			cp.setRendimentoAnual(0.005);
		else if (cp.getCliente().getCategoria() == Categoria.SUPER)
			cp.setRendimentoAnual(0.007);
		else if (cp.getCliente().getCategoria() == Categoria.PREMIUM)
			cp.setRendimentoAnual(0.009);
		return "Conta Poupança "+cp.getContaId()+" adicionada com sucesso!";
	}
	
	public ArrayList<ContaBancaria> getListaDeContas() {
		return contaBancariaDao.getListaDeContas();
	}
	
	public ContaBancaria buscarContaPorId(int id) {
		return contaBancariaDao.buscarConta(id);
	}
	
	public String debitarTaxaManutencao(ContaCorrente c) {		
		double taxaMensal = c.getTaxaManutencaoMensal();
		double saldoAtual = c.getSaldo();
		double saldoNovo = saldoAtual - taxaMensal;
		c.setSaldo(saldoNovo);
		return "Taxa de manutenção da conta "+c.getContaId() + " debitada com sucesso!";
	}
	
	public String renderMensalPoupanca(ContaPoupanca c) {
		double taxaAnual = c.getRendimentoAnual();
		double taxaMensal = Math.pow(1 + taxaAnual, 1.0/12) - 1;
		double saldoAtual = c.getSaldo();
		double rendimento = saldoAtual*taxaMensal;
		double saldoNovo = saldoAtual + rendimento;
		c.setSaldo(saldoNovo);
		return "Rendimento da poupança "+c.getContaId()+ " concluído com sucesso!";
	}

}
