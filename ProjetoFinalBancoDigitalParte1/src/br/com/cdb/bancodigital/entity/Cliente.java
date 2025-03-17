package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;

import br.com.cdb.bancodigital.enums.Categoria;

public class Cliente {
	
	private int clienteId; //tornar imutável
	private String cpf;
	private String nome;
	private LocalDate dataNascimento;
	private EnderecoCliente endereco;
	private Categoria categoria;
	

	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public EnderecoCliente getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoCliente endereco) {
		this.endereco = endereco;
	}
	public void setEndereco(String rua, int numero, String complemento, String cidade, String estado, String cep) {
	} //verificar
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Cliente(String cpf, String nome, LocalDate dataNascimento, EnderecoCliente endereco,
			Categoria categoria) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.categoria = categoria;
	}
	public Cliente() {
		super();
	}
	
	

}
