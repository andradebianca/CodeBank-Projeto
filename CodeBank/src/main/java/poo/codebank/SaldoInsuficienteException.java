package poo.codebank;


public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(double saldo, double valorSolicitado) {
        super("Saldo insuficiente. Saldo disponível: R$ " + String.format("%.2f", saldo)
                + " | Valor solicitado: R$ " + String.format("%.2f", valorSolicitado));
    }
}
