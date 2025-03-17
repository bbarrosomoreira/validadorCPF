package br.com.cdb.bancodigital.dao;

import java.util.ArrayList;

import br.com.cdb.bancodigital.entity.ContaBancaria;
import br.com.cdb.bancodigital.entity.ContaCorrente;
import br.com.cdb.bancodigital.entity.ContaPoupanca;

public class ContaBancariaDAO {
	
	private ArrayList<ContaBancaria> listaDeContas = new ArrayList<>();
	
	public void addContaCorrente(ContaCorrente cc)
	{
		listaDeContas.add(cc);
	}
	
	public void addContaPoupanca(ContaPoupanca cp)
	{
		listaDeContas.add(cp);
	}
	
	public void removeContaCorrente(ContaCorrente cc)
	{
		listaDeContas.remove(cc);
	}
	
	public void removeContaPoupanca(ContaPoupanca cp)
	{
		listaDeContas.remove(cp);
	}
	
	public ArrayList<ContaBancaria> getListaDeContas() {
		return listaDeContas;
	}

	public ContaBancaria buscarConta(int id)
	{
		for (ContaBancaria c : listaDeContas)
		{
			if(c.getContaId() == id)
				return c;
		}
		return null;
	}
}
