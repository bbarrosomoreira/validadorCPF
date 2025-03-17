package br.com.cdb.bancodigital.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.EnderecoCliente;
import br.com.cdb.bancodigital.enums.Categoria;

public class ClienteDAO {
	
	private ArrayList<Cliente> listaDeClientes = new ArrayList<>();
	
	public void addCliente(Cliente cliente) //PRECISO CRIAR OBJETO DE CLIENTE AQUI?
	{
		cliente.setClienteId(listaDeClientes.size()+1);
		listaDeClientes.add(cliente);
	} 
	
	public void addCliente(String cpf, String nome, LocalDate dataNascimento, EnderecoCliente endereco, Categoria categoria)
	{
		Cliente cliente = new Cliente();
		cliente.setClienteId(listaDeClientes.size()+1);
		listaDeClientes.add(cliente);
	} 
	
	public void addCliente(String cpf, String nome, LocalDate dataNascimento, String rua, int numero, String complemento, String cidade, String estado, String cep, Categoria categoria)
	{
		EnderecoCliente endereco = new EnderecoCliente(rua, numero, complemento, cidade, estado, cep);
		Cliente cliente = new Cliente(cpf, nome, dataNascimento, endereco, categoria);
		cliente.setClienteId(listaDeClientes.size()+1);
		System.out.println(cliente.getClienteId());

		listaDeClientes.add(cliente);
	} 
	
	public void removeCliente(Cliente cliente)
	{
		listaDeClientes.remove(cliente);
	}
	
	public Cliente buscarCliente(int id)
	{
		for (Cliente c : listaDeClientes)
		{
			if(c.getClienteId() == id)
				return c;
		}
		return null;
	}
	
	public ArrayList<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}
	

}
