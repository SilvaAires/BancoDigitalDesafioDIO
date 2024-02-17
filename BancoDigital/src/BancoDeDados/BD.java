package BancoDeDados;

import java.util.ArrayList;
import java.util.List;

import Entidade.Cliente;
import Entidade.Conta;

public class BD {
	private List<Conta> contas;
	private List<Cliente> clientes;
	
	public BD() {
		this.contas = new ArrayList<>();
		this.clientes = new ArrayList<>();
	}
	
	public Conta[] getConta() {
		return contas.toArray(new Conta[contas.size()]);
	}
	
	public void adicionarConta(Conta conta) {
		contas.add(conta);
	}
	
	public void removerConta(int posicao) {
		contas.remove(posicao);
	}
	
	public Cliente[] getCliente() {
		return clientes.toArray(new Cliente[clientes.size()]);
	}
	
	public void adicionarCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public void removerCliente(int posicao) {
		clientes.remove(posicao);
	}
}
