package br.com.cdb.bancodigital.entity;

import br.com.cdb.bancodigital.enums.TipoCartao;

public class CartaoDebito extends Cartao{

	private double limiteDiario; //RESSETAR AO VIRAR O DIA + possibilidade de alteração pelo usuario
	private double limiteDiarioAtual;
//	TipoCartao tipoCartao = TipoCartao.DEBITO;
	
	public double getLimiteDiarioAtual() {
		return limiteDiarioAtual;
	}

	public void setLimiteDiarioAtual() {
		this.limiteDiarioAtual = this.limiteDiario;
	}
	
	public void setLimiteDiarioAtual(double limiteDiarioAtual) {
		this.limiteDiarioAtual = limiteDiarioAtual;
	}

	@Override
	public void setTipoCartao(TipoCartao tipoCartao) {
		this.tipoCartao = TipoCartao.DEBITO;
	}
	
	public double getLimiteDiario() {
		return limiteDiario;
	}
	public void setLimiteDiario(double limiteDiario) {
		this.limiteDiario = limiteDiario;
	}

	@Override
	public String realizarPagamento(double valor) {
		if(this.getLimiteDiario() < valor)
			return "Limite insuficiente.";
		else
		{
			double novoLimite = this.limiteDiarioAtual - valor;
			this.setLimiteDiarioAtual(novoLimite);
			return "Pagamento realizado com sucesso! Limite diário disponível:" + novoLimite;
		}
	}
	
}
