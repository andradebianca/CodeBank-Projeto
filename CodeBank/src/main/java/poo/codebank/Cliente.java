package poo.codebank;

public class Cliente {
    private String nome;
    private String cpf;
    private Conta[] contas;
    private int totalContas;
    private static final int MAX_CONTAS = 5;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.contas = new Conta[MAX_CONTAS];
        this.totalContas = 0;
    }

    public boolean adicionarConta(Conta conta) {
        if (totalContas < MAX_CONTAS) {
            contas[totalContas++] = conta;
            System.out.println("  Conta #" + conta.getNumero() + " adicionada ao cliente " + nome + ".");
            return true;
        }
        System.out.println("  Cliente " + nome + " já possui o máximo de " + MAX_CONTAS + " contas.");
        return false;
    }

    public double getSaldoTotal() {
        double total = 0;
        for (int i = 0; i < totalContas; i++) total += contas[i].getSaldo();
        return total;
    }
    public String getNome() { 
        return nome; 
    }
    public String getCpf() { 
        return cpf; 
    }
    public Conta[] getContas() { 
        return contas; 
    }
    public int getTotalContas() { 
        return totalContas; 
    }
}