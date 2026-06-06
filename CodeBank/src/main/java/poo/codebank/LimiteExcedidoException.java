package poo.codebank;

public class LimiteExcedidoException extends Exception {
    public LimiteExcedidoException(double saldo, double limite, double valorSolicitado) {
        super("Limite excedido. Saldo atual: R$ " + String.format("%.2f", saldo)
                + " | Limite disponível: R$ " + String.format("%.2f", limite)
                + " | Valor solicitado: R$ " + String.format("%.2f", valorSolicitado));
    }
}
