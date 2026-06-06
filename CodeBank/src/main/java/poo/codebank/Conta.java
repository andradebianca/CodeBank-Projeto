package poo.codebank;

public abstract class Conta {
    protected int numero;
    protected Cliente titular;
    protected double saldo;
    protected Extrato extrato;

    public Conta(int numero, Cliente titular, double saldoInicial) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.extrato = new Extrato();
        extrato.registrar("Conta aberta | Depósito inicial: R$ " + String.format("%.2f", saldoInicial)
                + " | Saldo: R$ " + String.format("%.2f", saldo));
    }

    public void creditar(double valor) throws ValorInvalidoException {
        try {
            if (valor <= 0) throw new ValorInvalidoException(valor);
            saldo += valor;
            extrato.registrar("Crédito: R$ " + String.format("%.2f", valor)
                    + " | Saldo: R$ " + String.format("%.2f", saldo));
            System.out.println("  Crédito de R$ " + String.format("%.2f", valor) + " realizado.");
        } catch (ValorInvalidoException e) {
            System.out.println("  Erro ao creditar: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("  Saldo atual da conta " + numero + ": R$ " + String.format("%.2f", saldo));
        }
    }

    public abstract void debitar(double valor) throws Exception;

    public void transferir(double valor, Conta destino) throws Exception {
        try {
            this.debitar(valor);
            destino.creditar(valor);
            extrato.registrar("Transferência enviada p/ conta " + destino.getNumero()
                    + ": R$ " + String.format("%.2f", valor)
                    + " | Saldo: R$ " + String.format("%.2f", saldo));
            destino.getExtrato().registrar("Transferência recebida da conta " + this.numero
                    + ": R$ " + String.format("%.2f", valor)
                    + " | Saldo: R$ " + String.format("%.2f", destino.getSaldo()));
        } catch (Exception e) {
            System.out.println("  Erro na transferência: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("  Saldo atual da conta " + numero + ": R$ " + String.format("%.2f", saldo));
        }
    }

    public void exibirExtrato() {
        System.out.println("=== Extrato - Conta " + numero + " | Titular: " + titular.getNome() + " ===");
        extrato.exibir();
        System.out.println("=".repeat(60));
    }

    public int getNumero() { 
        return numero; }
    public Cliente getTitular() { 
        return titular; }
    public double getSaldo() { 
        return saldo; }
    public Extrato getExtrato() { 
        return extrato; }
}