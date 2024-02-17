package Main;

import java.util.Optional;
import java.util.Scanner;

import BancoDeDados.BD;
import BancoDeDados.ClienteBD;
import BancoDeDados.ContaBD;
import Entidade.Cliente;
import Entidade.Conta;

public class Main {
	private static Cliente clienteLogado = null;
	private static BD banco = new BD();
	private static ClienteBD cliBD = new ClienteBD(banco);
	private static ContaBD conBD = new ContaBD(banco);
	private static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Bem vindo ao Controle do Banco Digital!");
		
		boolean sair = true;
		while(sair) {
			if(clienteLogado == null) {
				System.out.println("\nDigite seu CPF! Somente números!");
				entrada = new Scanner(System.in);
				String cpf = entrada.nextLine();
				identificarUser(cpf);
			}else {
				System.out.println("\nSelecione uma opção "+clienteLogado.getNome()+":\n");
				
				System.out.println("Operações De Dinheiro!\n");
	            System.out.println("1 - Sacar dinheiro");
	            System.out.println("2 - Depositar dinheiro");
	            System.out.println("3 - Transferir dinheiro");
	            System.out.println("4 - Imprimir Extrato");
	            
	            System.out.println("\nOperações De Controle!\n");
	            System.out.println("5 - Cadastrar Conta e Cliente");
	            System.out.println("6 - Excluir Conta e Cliente");
	            
	            System.out.println("\nOperações De Checagem!\n");
	            System.out.println("7 - Listar Cliente e Conta");
	            
	            System.out.println("\n8 - Sair");
	            
	            System.out.println("---------------------------------------");
	            entrada = new Scanner(System.in);
	            int op = entrada.nextInt();
	            
	            switch (op) {
	            case 1:
	            	Conta ress = conBD.consultarContaCPF(clienteLogado.getCpf());
	            	if(ress != null) {
	            		System.out.println("Digite um valor para sacar: ");
	            		entrada = new Scanner(System.in);
	    	            double saque = entrada.nextDouble();
	            		ress.sacar(saque);
	            	}else {
	            		System.err.println("ERRO!");
	            	}
	            	break;
	            
	            case 2: 
	            	Conta res1 = conBD.consultarContaCPF(clienteLogado.getCpf());
	            	if(res1 != null) {
	            		System.out.println("Digite um valor para depositar: ");
	            		entrada = new Scanner(System.in);
	    	            double deposito = entrada.nextDouble();
	            		res1.depositar(deposito);;
	            	}else {
	            		System.err.println("ERRO!");
	            	}
	            	break;
	            
	            case 3:
	            	System.out.println("informe o CPF da pessoa que queira transferir: ");
	            	String cp = digitar();
	            	Conta pessoaDaConta = conBD.consultarContaCPF(cp);
	            	Conta res2 = conBD.consultarContaCPF(clienteLogado.getCpf());
	            	
	            	if((res2 != null) && (pessoaDaConta != null)) {
	            		System.out.println("Digite um valor para depositar: ");
	            		entrada = new Scanner(System.in);
	            		double trans = entrada.nextDouble();
	            		res2.transferir(trans , pessoaDaConta);
	            	}else {
	            		System.err.println("ERRO!");
	            	}
	            	
	            	break;
	            
	            case 4: 
	            	Conta res = conBD.consultarContaCPF(clienteLogado.getCpf());
	            	if(res != null) {
	            		res.imprimirExtrato();
	            	}else {
	            		System.err.println("ERRO!");
	            	}
	            	break;
	            
	            case 5: 
	            	System.out.println("Cadastramento de Conta e Cliente!\n");
	            	Cliente cadCli = new Cliente();
	            	
	            	System.out.println("\nDigite seu nome:");
	    			cadCli.setNome(digitar());
	    			
	    			System.out.println("\nDigite seu CPF:");
	    			cadCli.setCpf(digitar());
	    			
	    			System.out.println("\nDigite a data de nascimento: \nSomente número separados por espaço!");
	    			cadCli.setDataNasc(digitar());
	            	
	    			Conta cadCon = new Conta();
	    			
	            	System.out.println("\nDigite o nome do Banco:\n");
	    			cadCon.setNomeBanco(digitar());
	    			
	            	System.out.println("\nDigite o tipo de conta:\n");
	            	cadCon.setTipo(digitar());
	            	
	            	cadCon.setCliente(cadCli);
	            	cliBD.salvarCliente(cadCli);
	            	conBD.salvarContaBD(cadCon);
	            	break;
	            
	            case 6: 
	            	System.out.println("Exclusão de Conta e cliente!");
	            	System.out.println("\nDigite seu CPF: \n");
	            	String cpf = digitar();
	            	cliBD.excluirCliente(cpf);
	            	conBD.excluirContaBD(cpf);	            	
	            	break;
	            
	            case 7: 
	            	System.out.println("Lista de conta e cliente");
	            	conBD.listarTodasContas();
	            	break;
	            
	            case 8:
	            	System.out.println("Saindo da aplicação!");
	            	sair = false;
	            	break;
	            }
			}
		}
	}
	
	public static String digitar() {
		entrada = new Scanner(System.in);
		String digi = entrada.nextLine(); 
		return digi;
	}
	
	public static void identificarUser(String cpf) {
		Optional<Cliente> resultado = cliBD.consultarClienteCpf(cpf);
		if(resultado.isPresent()) {
			Cliente cliente = resultado.get();
			System.out.println("Olá! Você está logado "+cliente.getNome());
			clienteLogado = cliente;
		}else {
			Cliente cadastramento = new Cliente();
			
			System.out.println("Usuário não cadastrado!");
			
			System.out.println("\nCadastramento!");
			
			System.out.println("\nDigite seu nome:");
			cadastramento.setNome(digitar());
			
			System.out.println("\nDigite seu CPF:");
			cadastramento.setCpf(digitar());
			
			System.out.println("\nDigite a data de nascimento: \nSomente número separados por espaço!");
			cadastramento.setDataNasc(digitar());
			
			Conta cadCon = new Conta();
			
        	System.out.println("\nDigite o nome do Banco:\n");
			cadCon.setNomeBanco(digitar());
			
        	System.out.println("\nDigite o tipo de conta:\n");
        	cadCon.setTipo(digitar());
        	cadCon.setCliente(cadastramento);
			
			cliBD.salvarCliente(cadastramento);
			conBD.salvarContaBD(cadCon);
			clienteLogado = cadastramento;
		}
	}
}