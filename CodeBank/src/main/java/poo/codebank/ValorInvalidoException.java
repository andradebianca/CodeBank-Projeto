package poo.codebank;

public class ValorInvalidoException extends Exception {
    public ValorInvalidoException(double valor) {
        super("Valor inválido: R$ " + String.format("%.2f", valor) + ". O valor informado deve ser maior que zero.");
    }
}
