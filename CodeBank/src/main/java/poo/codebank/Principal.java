package poo.codebank;

public class Principal {
    public static void main(String[] args) {

        // ── BANCO E CLIENTES ───────────────────────────────────────
        Banco banco = new Banco("CodeBank");

        Cliente c1 = new Cliente("Ana Lima",    "111.111.111-11");
        Cliente c2 = new Cliente("Bruno Souza", "222.222.222-22");
        Cliente c3 = new Cliente("Carla Melo",  "333.333.333-33");
        Cliente c4 = new Cliente("Diego Pires", "444.444.444-44");
        Cliente c5 = new Cliente("Elena Ramos", "555.555.555-55");

        // ── CONTAS ─────────────────────────────────────────────────
        ContaCorrente cc1 = new ContaCorrente(1001, c1, 1000.00, 500.00);
        ContaPoupanca cp1 = new ContaPoupanca(1002, c1, 2000.00, 0.005);
        ContaCorrente cc2 = new ContaCorrente(2001, c2,  500.00, 300.00);
        ContaPoupanca cp2 = new ContaPoupanca(2002, c2,    0.00, 0.005); // saldo zero — testa erro rendimento
        ContaCorrente cc3 = new ContaCorrente(3001, c3,  800.00, 200.00);
        ContaPoupanca cp4 = new ContaPoupanca(4001, c4, 3000.00, 0.008);
        ContaCorrente cc5 = new ContaCorrente(5001, c5,  100.00,  50.00);

        // ── ASSOCIAÇÕES ────────────────────────────────────────────
        System.out.println("\n-- Cadastrando contas --");
        c1.adicionarConta(cc1); c1.adicionarConta(cp1);
        c2.adicionarConta(cc2); c2.adicionarConta(cp2);
        c3.adicionarConta(cc3);
        c4.adicionarConta(cp4);
        c5.adicionarConta(cc5);

        System.out.println("\n-- Cadastrando clientes nas agências --");
        banco.adicionarCliente(0, c1); banco.adicionarCliente(0, c2);
        banco.adicionarCliente(1, c3); banco.adicionarCliente(1, c4);
        banco.adicionarCliente(2, c5);

        // ── CRÉDITO ────────────────────────────────────────────────
        System.out.println("\n-- Crédito --");
        try { cc1.creditar(500.00); } catch (Exception e) {}
        try { cp1.creditar(-50.00); } catch (Exception e) {} // valor inválido

        // ── DÉBITO ─────────────────────────────────────────────────
        System.out.println("\n-- Débito --");
        try { cc1.debitar(200.00);   } catch (Exception e) {}
        try { cc5.debitar(500.00);   } catch (Exception e) {} // limite excedido
        try { cp1.debitar(99999.00); } catch (Exception e) {} // saldo insuficiente
        try { cc2.debitar(0);        } catch (Exception e) {} // valor inválido

        // ── TRANSFERÊNCIA ──────────────────────────────────────────
        System.out.println("\n-- Transferência --");
        try { cc1.transferir(300.00, cp4);  } catch (Exception e) {}
        try { cc5.transferir(9999.00, cc3); } catch (Exception e) {} // limite excedido

        // ── RENDIMENTO ─────────────────────────────────────────────
        System.out.println("\n-- Rendimento --");
        try { cp1.aplicarRendimento(); } catch (Exception e) {}
        try { cp4.aplicarRendimento(); } catch (Exception e) {}
        try { cp2.aplicarRendimento(); } catch (Exception e) {} // saldo zero — erro esperado

        // ── TARIFA ─────────────────────────────────────────────────
        System.out.println("\n-- Tarifas mensais --");
        for (ContaCorrente cc : new ContaCorrente[]{ cc1, cc2, cc3, cc5 }) {
            cc.cobrarTarifa();
        }

        // ── RELATÓRIOS ─────────────────────────────────────────────
        System.out.println("\n\n========== RELATÓRIOS ==========");

        System.out.println("\n-- Extratos --");
        cc1.exibirExtrato(); cp1.exibirExtrato();
        cc2.exibirExtrato(); cp2.exibirExtrato();
        cc3.exibirExtrato(); cp4.exibirExtrato();
        cc5.exibirExtrato();

        banco.relatorioClientesPorAgencia(0);
        banco.relatorioClientesPorAgencia(1);
        banco.relatorioClientesPorAgencia(2);

        banco.relatorioGeralBanco();
    }
}