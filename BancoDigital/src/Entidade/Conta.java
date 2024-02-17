package Entidade;

public class Conta {
	private static int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;
	private static int SEQUENCIAL2 = 1;
	
	protected int agencia;
	protected int numero;
	protected double saldo;
	private Cliente cliente;
	private String nomeBanco;
	private String tipo;
	private int id;
	
	public Conta() {
		this.agencia = AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.id = SEQUENCIAL2++;
	}

	public void sacar(double valor) {
		this.saldo = this.saldo - valor;
		System.out.println("Saque feito! Saldo novo: "+this.saldo);
	}
	
	public void depositar(double valor) {
		this.saldo = this.saldo + valor;
		System.out.println("Deposito feito! Saldo novo: "+this.saldo);
	}
	
	public void transferir(double valor, Conta contaDestino) {
		/*contaDestino.saldo = contaDestino.saldo + valor;
		this.saldo = this.saldo - valor;*/
		this.sacar(valor);
		contaDestino.depositar(valor);
		System.out.println("Transferencia feito! Saldo novo: "+this.saldo);
	}
	
	public void imprimirExtrato() {
		System.out.println("Extrato do Cliente: " + cliente.getNome()+" Banco "+this.nomeBanco);
		System.out.println("Agência: " + this.agencia);
		System.out.println("Número: " + this.numero);
		System.out.println("Tipo: " + this.tipo);
		System.out.println("Saldo: " + this.saldo);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return    "\nConta: \n"
				+ "\nBanco: " + this.nomeBanco 
				+ "\nAgência: " + this.agencia 
				+ "\nNúmero: " + this.numero 
				+ "\nSaldo: " + this.saldo 
				+ "\nTipo: " + this.tipo 
				+ "\nId: " + this.id 
				+ cliente.toString()
				+ "\n______________________________________";
	}
	
}
