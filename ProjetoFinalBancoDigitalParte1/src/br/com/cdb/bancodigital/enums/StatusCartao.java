package br.com.cdb.bancodigital.enums;

public enum StatusCartao {
	ATIVADO (1),
	DESATIVADO (2);
	
	private final int codigo;
	
	StatusCartao(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

}
