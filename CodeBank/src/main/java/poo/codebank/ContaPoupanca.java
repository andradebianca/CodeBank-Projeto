package poo.codebank;

public class ContaPoupanca extends Conta {
    private double taxaRendimento;

    public ContaPoupanca(int numero, Cliente titular, double saldoInicial, double taxaRendimento) {
        super(numero, titular, saldoInicial);
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public void debitar(double valor) throws ValorInvalidoException, SaldoInsuficienteException {
        try {
            if (valor <= 0) throw new ValorInvalidoException(valor);
            if (valor > saldo) throw new SaldoInsuficienteException(saldo, valor);
            saldo -= valor;
            extrato.registrar("Débito: R$ " + String.format("%.2f", valor)
                    + " | Saldo: R$ " + String.format("%.2f", saldo));
            System.out.println("  Débito de R$ " + String.format("%.2f", valor) + " realizado.");
        } catch (ValorInvalidoException | SaldoInsuficienteException e) {
            System.out.println("  Erro ao debitar: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("  Saldo atual da conta " + numero + ": R$ " + String.format("%.2f", saldo));
        }
    }

    public void aplicarRendimento() throws SaldoNegativoException {
        try {
            if (saldo <= 0) throw new SaldoNegativoException(saldo);
            double rendimento = saldo * taxaRendimento;
            saldo += rendimento;
            extrato.registrar("Rendimento (" + String.format("%.2f", taxaRendimento * 100) + "%): +"
                    + "R$ " + String.format("%.2f", rendimento)
                    + " | Saldo: R$ " + String.format("%.2f", saldo));
            System.out.println("  Rendimento de R$ " + String.format("%.2f", rendimento) + " aplicado.");
        } catch (SaldoNegativoException e) {
            System.out.println("  Erro ao aplicar rendimento: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("  Saldo atual da conta " + numero + ": R$ " + String.format("%.2f", saldo));
        }
    }

    public double getTaxaRendimento() { 
        return taxaRendimento; 
    }
}