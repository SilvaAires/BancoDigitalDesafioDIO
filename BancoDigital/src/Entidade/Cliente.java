package Entidade;

public class Cliente {
	private static int SEQUENCIAL = 1;
	
	private int id;
	private String nome;
	private String cpf;
	private String dataNasc;

	public Cliente(int id, String nome, String cpf, String dataNasc) {
		this.id = SEQUENCIAL++;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
	}
	
	public Cliente() {
		this.id = SEQUENCIAL++;
	}

	public int getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return    "\n______________________________________\n"
				+ "\nCliente: \n"
				+ "\nId: " + id 
				+ "\nNome: " + nome 
				+ "\nCPF: " + cpf 
				+ "\nData de Nascimento: " + dataNasc;
	}
}
