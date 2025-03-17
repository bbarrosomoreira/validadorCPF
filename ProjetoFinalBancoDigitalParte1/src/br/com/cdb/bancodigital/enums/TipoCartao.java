package br.com.cdb.bancodigital.enums;

public enum TipoCartao {
	CREDITO ("CCR", "Cartão de Crédito"),
	DEBITO ("CDB", "Cartão de Débito");
	
	private final String codigo;
	private final String descricao;
	
	TipoCartao(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
