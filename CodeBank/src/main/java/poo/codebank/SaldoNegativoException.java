package poo.codebank;

public class SaldoNegativoException extends Exception {
    public SaldoNegativoException(double saldo) {
        super("Operação negada. Saldo atual de R$ " + String.format("%.2f", saldo)
                + " impede a aplicação de rendimento (saldo deve ser maior que zero).");
    }
}
