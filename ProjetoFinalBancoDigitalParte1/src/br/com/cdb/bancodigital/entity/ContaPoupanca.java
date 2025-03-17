package br.com.cdb.bancodigital.entity;

public class ContaPoupanca extends ContaBancaria {
	
	private double rendimentoAnual;
	private final String tipo = "Conta Poupança";
	
	public String getTipo() {
		return tipo;
	}
	public double getRendimentoAnual() {
		return rendimentoAnual;
	}
	public void setRendimentoAnual(double rendimentoAnual) {
		this.rendimentoAnual = rendimentoAnual;
	}


}