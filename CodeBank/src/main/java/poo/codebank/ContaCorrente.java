package poo.codebank;

public class ContaCorrente extends Conta implements Tributavel {
    private double limite;
    private static final double TARIFA_MENSAL = 15.00;

    public ContaCorrente(int numero, Cliente titular, double saldoInicial, double limite) {
        super(numero, titular, saldoInicial);
        this.limite = limite;
    }

    @Override
    public void debitar(double valor) throws Exception {
        try {
            if (valor <= 0) throw new ValorInvalidoException(valor);
            if (valor > saldo + limite) throw new LimiteExcedidoException(saldo, limite, valor);
            saldo -= valor;
            extrato.registrar("Débito: R$ " + String.format("%.2f", valor)
                    + " | Saldo: R$ " + String.format("%.2f", saldo));
            System.out.println("  Débito de R$ " + String.format("%.2f", valor) + " realizado.");
        } catch (ValorInvalidoException | LimiteExcedidoException e) {
            System.out.println("  Erro ao debitar: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("  Saldo atual da conta " + numero + ": R$ " + String.format("%.2f", saldo));
        }
    }

    @Override
    public double calcularTarifa() {
        return TARIFA_MENSAL;
    }

    @Override
    public void cobrarTarifa() {
        saldo -= TARIFA_MENSAL;
        extrato.registrar("Tarifa mensal: R$ " + String.format("%.2f", TARIFA_MENSAL)
                + " | Saldo: R$ " + String.format("%.2f", saldo));
        System.out.println("  Tarifa de R$ " + String.format("%.2f", TARIFA_MENSAL)
                + " cobrada da conta " + numero + ".");
        System.out.println("  Saldo atual da conta " + numero + ": R$ " + String.format("%.2f", saldo));
    }

    public double getLimite() { 
        return limite; 
    }
}