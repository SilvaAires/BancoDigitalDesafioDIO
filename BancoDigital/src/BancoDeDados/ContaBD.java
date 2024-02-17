package BancoDeDados;

import java.util.Optional;

import Entidade.Conta;

public class ContaBD {
	private BD bancoDados;
	
	public ContaBD(BD banco) {
		this.bancoDados = banco;
	}
	
	public void salvarContaBD(Conta novaConta) {
		boolean contaRepetido = false;
		for(Conta conta : bancoDados.getConta()) {
			if(conta.getId() == novaConta.getId()) {
				contaRepetido = true;
				System.out.println("Conta já cadastrado!");
				break;
			}
		}
		if(!contaRepetido) {
			this.bancoDados.adicionarConta(novaConta);
			System.out.println("Conta cadastrada com sucesso!");
		}
	}
	
	public void excluirContaBD(String cpf) {
		int contaExclusao = -1;
		for(int i = 0; i < bancoDados.getConta().length; i++) {
			Conta conta = bancoDados.getConta()[i];
			if(conta.getCliente().getCpf().equals(cpf)) {
				contaExclusao = i;
				break;
			}
		}
		if(contaExclusao != -1) {
			bancoDados.removerConta(contaExclusao);
			System.out.println("Conta excluido com sucesso!");
		}else {
			System.out.println("Produto inexistente!");
		}
	}
	
	public Optional<Conta> consultarConta(int id){
		for(Conta conta : bancoDados.getConta()) {
			if(conta.getId() == id) {
				return Optional.of(conta);
			}
		}
		return Optional.empty();
	}
	
	public Conta consultarContaCPF(String id){
		for(Conta conta : bancoDados.getConta()) {
			if(conta.getCliente().getCpf().equals(id)) {
				return conta;
			}
		}
		return null;
	}
	
	public void listarTodasContas() {
		if(bancoDados.getConta().length == 0) {
			System.out.println("Não exite contas cadastradas!");
		}else {
			for(Conta conta : bancoDados.getConta()) {
				System.out.println(conta.toString());
			}
		}
	}
}
