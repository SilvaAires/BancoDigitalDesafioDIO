package BancoDeDados;

import java.util.Optional;

import Entidade.Cliente;

public class ClienteBD {
	private BD bancoDados;
	public ClienteBD(BD banco) {
		this.bancoDados = banco;
	}
	
	public void salvarCliente(Cliente novoCliente) {
		boolean clienteRepetido = false;
		for(Cliente cliente : this.bancoDados.getCliente()) {
			if(cliente.getId() == novoCliente.getId()) {
				clienteRepetido = true;
				System.out.println("Cliente já cadastrado, "+cliente.getNome()+'!');
				break;
			}
		}
		if(!clienteRepetido) {
			this.bancoDados.adicionarCliente(novoCliente);
			System.out.println("Cliente cadastrado com sucesso, "+novoCliente.getNome()+"!");
		}
	}
	
	public void excluirCliente(String cpf) {
		int clienteExclusao = -1;
		for (int i = 0; i < bancoDados.getCliente().length; i++) {
			Cliente cliente = bancoDados.getCliente()[i];
			if(cliente.getCpf().equals(cpf)) {
				clienteExclusao = i;
				break;
			}
		}
		if(clienteExclusao != -1) {
			bancoDados.removerCliente(clienteExclusao);
			System.out.println("Cliente excluido com sucesso!");
		}else {
			System.out.println("Cliente inexistente!");
		}
	}
	
	public Optional<Cliente> consultarCliente(int id){
		for(Cliente cliente : bancoDados.getCliente()) {
			if(cliente.getId() == id) {
				return Optional.of(cliente);
			}
		}
		return Optional.empty();
	}
	
	public Optional<Cliente> consultarClienteCpf(String cpf){
		for(Cliente cliente : bancoDados.getCliente()) {
			if(cliente.getCpf().equals(cpf)) {
				return Optional.of(cliente);
			}
		}
		return Optional.empty();
	}
	
	public void listarTodosClientes() {
		if(bancoDados.getCliente().length == 0) {
			System.out.println("Não existem clientes cadastrados !");
		}else {
			for(Cliente cliente : bancoDados.getCliente()) {
				System.out.println(cliente.toString());
			}
		}
	}
}
