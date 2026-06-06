package poo.codebank;

public class Banco {
    private String nome;
    private Cliente[][] agencias;
    private int[] totalClientesPorAgencia;
    private static final int MAX_AGENCIAS = 3;
    private static final int MAX_CLIENTES = 10;

    public Banco(String nome) {
        this.nome = nome;
        this.agencias = new Cliente[MAX_AGENCIAS][MAX_CLIENTES];
        this.totalClientesPorAgencia = new int[MAX_AGENCIAS];
    }

    public boolean adicionarCliente(int agencia, Cliente cliente) {
        if (agencia < 0 || agencia >= MAX_AGENCIAS) {
            System.out.println("  Agência inválida: " + agencia);
            return false;
        }
        if (totalClientesPorAgencia[agencia] >= MAX_CLIENTES) {
            System.out.println("  Agência " + (agencia + 1) + " está lotada.");
            return false;
        }
        agencias[agencia][totalClientesPorAgencia[agencia]++] = cliente;
        System.out.println("  Cliente " + cliente.getNome() + " cadastrado na agência " + (agencia + 1) + ".");
        return true;
    }

    public void relatorioClientesPorAgencia(int agencia) {
        System.out.println("\n=== Clientes — Agência " + (agencia + 1) + " ===");
        if (totalClientesPorAgencia[agencia] == 0) {
            System.out.println("  Nenhum cliente cadastrado.");
            return;
        }
        for (int j = 0; j < totalClientesPorAgencia[agencia]; j++) {
            Cliente c = agencias[agencia][j];
            System.out.println("  " + (j + 1) + ". " + c.getNome()
                    + " | CPF: " + c.getCpf()
                    + " | Contas: " + c.getTotalContas());
        }
        System.out.println("=".repeat(60));
    }

    public void relatorioGeralBanco() {
        System.out.println("\n=== RELATÓRIO GERAL — " + nome + " ===");
        double saldoTotal = 0;
        for (int i = 0; i < MAX_AGENCIAS; i++) {
            double saldoAgencia = 0;
            System.out.println("\n  Agência " + (i + 1) + " — Clientes: " + totalClientesPorAgencia[i]);
            for (int j = 0; j < totalClientesPorAgencia[i]; j++) {
                Cliente c = agencias[i][j];
                saldoAgencia += c.getSaldoTotal();
                System.out.println("    - " + c.getNome()
                        + " | CPF: " + c.getCpf()
                        + " | Saldo: R$ " + String.format("%.2f", c.getSaldoTotal()));
            }
            System.out.println("  Saldo agência " + (i + 1) + ": R$ " + String.format("%.2f", saldoAgencia));
            saldoTotal += saldoAgencia;
        }
        System.out.println("\n  SALDO TOTAL DO BANCO: R$ " + String.format("%.2f", saldoTotal));
        System.out.println("=".repeat(60));
    }

    public Cliente[][] getAgencias() { return agencias; }
    public int[] getTotalClientesPorAgencia() { return totalClientesPorAgencia; }
    public String getNome() { return nome; }
}