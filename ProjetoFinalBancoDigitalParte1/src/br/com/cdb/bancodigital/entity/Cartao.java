package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;

import br.com.cdb.bancodigital.enums.StatusCartao;
import br.com.cdb.bancodigital.enums.TipoCartao;

public abstract class Cartao {
	
	protected ContaBancaria conta;
	protected StatusCartao status;
	protected double txUtilizacao;
	protected int senha;
	protected TipoCartao tipoCartao;
	protected long numCartao;
	protected LocalDate dataCriacao;
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao() {
		this.dataCriacao = LocalDate.now();
	}	
	public TipoCartao getTipoCartao() {
		return tipoCartao;
	}
	public void setTipoCartao(TipoCartao tipoCartao) {
		this.tipoCartao = tipoCartao;
	}
	public long getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(long numCartao) {
		this.numCartao = numCartao;
	}
	public ContaBancaria getConta() {
		return conta;
	}
	public void setConta(ContaBancaria conta) {
		this.conta = conta;
	}
	public StatusCartao getStatus() {
		return status;
	}
	public void setStatus(StatusCartao status) {
		this.status = status;
	}
	public double getTxUtilizacao() {
		return txUtilizacao;
	}
	public void setTxUtilizacao(double txUtilizacao) {
		this.txUtilizacao = txUtilizacao;
	}
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	
	public abstract String realizarPagamento(double valor);


}
