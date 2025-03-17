package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;

public abstract class ContaBancaria {
	
	protected double saldo;
	protected int contaId; //torná-lo imutável
	protected Cliente cliente;
	protected String tipo;
	protected LocalDate dataCriacao;
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao() {
		this.dataCriacao = LocalDate.now();
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public int getContaId() {
		return contaId;
	}
	public void setContaId(int contaId) {
		this.contaId = contaId;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String depositar(double valor) {
		saldo += valor;
		return "Valor "+ valor + " depositado com sucesso!";
	}

	public String transferirPIX(double valor, ContaBancaria destino) {
		if (saldo >= valor)
		{
			destino.depositar(valor);
			saldo -= valor;
		}
		return "PIX realizado com sucesso!";
		
	}
	

}
