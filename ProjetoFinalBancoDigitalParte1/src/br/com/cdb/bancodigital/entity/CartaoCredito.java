package br.com.cdb.bancodigital.entity;

import br.com.cdb.bancodigital.enums.TipoCartao;

public class CartaoCredito extends Cartao {
	
	private double limiteCredito; //RESSETAR AO VIRAR O MÊS + QD LIMITE = 0, AVISAR!
	private double limiteCreditoAtual;
//	private TipoCartao tipoCartao = TipoCartao.CREDITO; 

	@Override
	public void setTipoCartao(TipoCartao tipoCartao) {
		this.tipoCartao = TipoCartao.CREDITO;
	}
	
	@Override
	public String realizarPagamento(double valor) {
		if(this.getLimiteCredito() < valor)
			return "Limite insuficiente.";
		else
		{
			double novoLimite = this.limiteCreditoAtual - valor;
			this.setLimiteCreditoAtual(novoLimite);
			return "Pagamento realizado com sucesso! Limite de crédito disponível:" + novoLimite;
		}
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public double getLimiteCreditoAtual() {
		return limiteCreditoAtual;
	}

	public void setLimiteCreditoAtual() {
		this.limiteCreditoAtual = this.limiteCredito;
	}
	
	public void setLimiteCreditoAtual(double limiteCreditoAtual) {
		this.limiteCreditoAtual = limiteCreditoAtual;
	}
	
	
	
	
	//seguros
}
