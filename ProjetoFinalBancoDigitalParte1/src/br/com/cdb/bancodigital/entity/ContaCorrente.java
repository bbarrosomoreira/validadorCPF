package br.com.cdb.bancodigital.entity;

public class ContaCorrente extends ContaBancaria {
	
	private double taxaManutencaoMensal;
	private final String tipo = "Conta Corrente";
	
	public String getTipo() {
		return tipo;
	}
	public double getTaxaManutencaoMensal() {
		return taxaManutencaoMensal;
	}
	public void setTaxaManutencaoMensal(double taxaManutencaoMensal) {
		this.taxaManutencaoMensal = taxaManutencaoMensal;
	}

	public String sacar(double valor) {
		if(saldo < valor)
			return "Saldo insuficiente.";
		else
		{
			saldo -= valor;
			return "Saque realizado com sucesso.";
		}
	}
	
	public ContaCorrente() {
		super();
	}

	
	

}
