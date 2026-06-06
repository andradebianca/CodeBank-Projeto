package poo.codebank;

public class Extrato {
    private String[] registros;
    private int totalRegistros;
    private static final int CAPACIDADE = 50;

    public Extrato() {
        this.registros = new String[CAPACIDADE];
        this.totalRegistros = 0;
    }

    public void registrar(String descricao) {
        if (totalRegistros < CAPACIDADE) {
            registros[totalRegistros] = descricao;
            totalRegistros++;
        } else {
            System.out.println("[EXTRATO] Capacidade máxima de registros atingida.");
        }
    }

    public void exibir() {
        if (totalRegistros == 0) {
            System.out.println("  Nenhuma operação registrada.");
            return;
        }
        for (int i = 0; i < totalRegistros; i++) {
            System.out.println("  " + (i + 1) + ". " + registros[i]);
        }
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }
}
