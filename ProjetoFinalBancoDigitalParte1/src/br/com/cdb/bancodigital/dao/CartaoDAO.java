package br.com.cdb.bancodigital.dao;

import java.util.ArrayList;

import br.com.cdb.bancodigital.entity.Cartao;
import br.com.cdb.bancodigital.entity.CartaoCredito;
import br.com.cdb.bancodigital.entity.CartaoDebito;

public class CartaoDAO {
	
	private ArrayList<Cartao> listaDeCartoes = new ArrayList<>();
	
	public void addCartaoCredito(CartaoCredito ccr) {
		listaDeCartoes.add(ccr);
	}
	
	public void addCartaoDebito(CartaoDebito cdb) {
		listaDeCartoes.add(cdb);
	}
	
	public void removeCartao(Cartao c) {
		listaDeCartoes.remove(c);
	}
	
	public ArrayList<Cartao> getListaCartoes(){
		return listaDeCartoes;
	}
	
	public Cartao buscarCartao(int id)
	{
		for (Cartao c : listaDeCartoes)
		{
			if(c.getNumCartao() == id)
				return c;
		}
		return null;
	}

}
