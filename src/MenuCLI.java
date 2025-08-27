import java.util.Scanner;
import java.util.List;

public class MenuCLI {
    private SistemaRestaurante sistema;
    private Scanner scanner;

    public MenuCLI() {
        this.sistema = new SistemaRestaurante();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("SISTEMA DE GERENCIAMENTO DE RESTAURANTE");
        System.out.println("=======================================");

        boolean continuar = true;
        while (continuar) {
            mostrarMenuPrincipal();
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    menuCardapio();
                    break;
                case 2:
                    menuClientes();
                    break;
                case 3:
                    menuPedidos();
                    break;
                case 4:
                    menuRelatorios();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Sistema encerrado!");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Gerenciar Cardápio");
        System.out.println("2. Gerenciar Clientes");
        System.out.println("3. Gerenciar Pedidos");
        System.out.println("4. Relatórios");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void menuCardapio() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n=== GERENCIAR CARDAPIO ===");
            System.out.println("1. Cadastrar item");
            System.out.println("2. Listar itens");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarItem();
                    break;
                case 2:
                    listarItens();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void cadastrarItem() {
        System.out.println("\n--- Cadastrar Item ---");

        System.out.print("Nome do item: ");
        scanner.nextLine();
        String nome = scanner.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("Nome nao pode ser vazio!");
            return;
        }

        System.out.print("Preco: R$ ");
        double preco = scanner.nextDouble();

        if (preco <= 0) {
            System.out.println("Preco deve ser maior que zero!");
            return;
        }

        ItemCardapio item = sistema.cadastrarItemCardapio(nome, preco);
        System.out.println("Item cadastrado com sucesso! Codigo: " + item.getCodigo());
    }

    private void listarItens() {
        System.out.println("\n--- CARDAPIO ---");
        List<ItemCardapio> itens = sistema.listarCardapio();

        if (itens.isEmpty()) {
            System.out.println("Nenhum item cadastrado.");
        } else {
            for (ItemCardapio item : itens) {
                System.out.printf("%d - %s - R$ %.2f%n",
                        item.getCodigo(), item.getNome(), item.getPreco());
            }
        }
    }

    private void menuClientes() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n=== GERENCIAR CLIENTES ===");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarCliente() {
        System.out.println("\n--- Cadastrar Cliente ---");

        System.out.print("Nome: ");
        scanner.nextLine(); // Limpar buffer
        String nome = scanner.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("Nome nao pode ser vazio!");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        if (telefone.trim().isEmpty()) {
            System.out.println("Telefone nao pode ser vazio!");
            return;
        }

        Cliente cliente = sistema.cadastrarCliente(nome, telefone);
        System.out.println("Cliente cadastrado com sucesso! Codigo: " + cliente.getCodigo());
    }

    private void listarClientes() {
        System.out.println("\n--- CLIENTES ---");
        List<Cliente> clientes = sistema.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.printf("%d - %s - %s%n",
                        cliente.getCodigo(), cliente.getNome(), cliente.getTelefone());
            }
        }
    }

    private void menuPedidos() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n=== GERENCIAR PEDIDOS ===");
            System.out.println("1. Criar pedido");
            System.out.println("2. Atualizar status");
            System.out.println("3. Consultar por status");
            System.out.println("4. Listar todos os pedidos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    criarPedido();
                    break;
                case 2:
                    atualizarStatus();
                    break;
                case 3:
                    consultarPorStatus();
                    break;
                case 4:
                    listarTodosPedidos();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void criarPedido() {
        System.out.println("\n--- Criar Pedido ---");

        listarClientes();
        if (sistema.listarClientes().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }

        System.out.print("Código do cliente: ");
        int codigoCliente = scanner.nextInt();

        Cliente cliente = sistema.buscarCliente(codigoCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado!");
            return;
        }

        Pedido pedido = sistema.criarPedido(cliente.getCodigo());
        System.out.println("Pedido criado! Numero: " + pedido.getNumero());

        boolean adicionandoItens = true;
        while (adicionandoItens) {
            listarItens();
            if (sistema.listarCardapio().isEmpty()) {
                System.out.println("Nenhum item no cardapio!");
                break;
            }

            System.out.print("Codigo do item (0 para finalizar): ");
            int codigoItem = scanner.nextInt();

            if (codigoItem == 0) {
                adicionandoItens = false;
            } else {
                ItemCardapio item = sistema.buscarItemCardapio(codigoItem);
                if (item == null) {
                    System.out.println("Item nao encontrado!");
                    continue;
                }

                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();
                if (quantidade <= 0) {
                    System.out.println("Quantidade deve ser maior que zero!");
                    continue;
                }

                sistema.adicionarItemAoPedido(pedido.getNumero(), item.getCodigo(), quantidade);
                System.out.println("Item adicionado ao pedido!");
            }
        }

        System.out.printf("Pedido finalizado! Total: R$ %.2f%n", pedido.getValorTotal());
    }

    private void atualizarStatus() {
        System.out.println("\n--- Atualizar Status ---");

        listarTodosPedidos();
        if (sistema.listarTodosPedidos().isEmpty()) {
            System.out.println("Nenhum pedido encontrado!");
            return;
        }

        System.out.print("Numero do pedido: ");
        int numero = scanner.nextInt();

        Pedido pedido = sistema.buscarPedido(numero);
        if (pedido == null) {
            System.out.println("Pedido não encontrado!");
            return;
        }

        StatusPedido novoStatus = sistema.atualizarStatusPedido(pedido.getNumero());
        if (novoStatus != null) {
            System.out.println("Status atualizado para: " + novoStatus);
        } else {
            System.out.println("Não foi possível atualizar o status (pedido já pode estar finalizado).");
        }
    }

    private void consultarPorStatus() {
        System.out.println("\n--- Consultar por Status ---");
        System.out.println("Status disponiveis:");
        StatusPedido[] status = StatusPedido.values();
        for (int i = 0; i < status.length; i++) {
            System.out.println((i + 1) + ". " + status[i]);
        }

        System.out.print("Escolha o status: ");
        int opcao = scanner.nextInt();

        if (opcao < 1 || opcao > status.length) {
            System.out.println("Opcao invalida!");
            return;
        }

        StatusPedido statusEscolhido = status[opcao - 1];
        List<Pedido> pedidos = sistema.consultarPedidosPorStatus(statusEscolhido);

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido com status: " + statusEscolhido);
        } else {
            System.out.println("\nPedidos com status " + statusEscolhido + ":");
            for (Pedido pedido : pedidos) {
                System.out.printf("Pedido %d - Cliente: %s - Total: R$ %.2f%n",
                        pedido.getNumero(), pedido.getCliente().getNome(), pedido.getValorTotal());
            }
        }
    }

    private void listarTodosPedidos() {
        System.out.println("\n--- TODOS OS PEDIDOS ---");
        List<Pedido> pedidos = sistema.listarTodosPedidos();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.printf("Pedido %d - Cliente: %s - Status: %s - Total: R$ %.2f%n",
                        pedido.getNumero(), pedido.getCliente().getNome(),
                        pedido.getStatus(), pedido.getValorTotal());
            }
        }
    }

    private void menuRelatorios() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n=== RELATORIOS ===");
            System.out.println("1. Relatorio simplificado");
            System.out.println("2. Relatorio detalhado");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    relatorioSimplificado();
                    break;
                case 2:
                    relatorioDetalhado();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void relatorioSimplificado() {
        System.out.println("\n--- RELATORIO SIMPLIFICADO ---");
        String relatorioSimplificado = sistema.gerarRelatorioSimplificado();
        System.out.println(relatorioSimplificado);
    }

    private void relatorioDetalhado() {
        System.out.println("\n--- RELATORIO DETALHADO ---");
        List<Pedido> pedidos = sistema.listarTodosPedidos();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }

        double totalGeral = 0;
        for (Pedido pedido : pedidos) {
            System.out.printf("\nPedido %d - Cliente: %s (%s)%n",
                    pedido.getNumero(), pedido.getCliente().getNome(),
                    pedido.getCliente().getTelefone());
            System.out.println("Status: " + pedido.getStatus());
            System.out.println("Itens:");

            for (PedidoItem item : pedido.getItens()) {
                System.out.printf("  - %s x%d = R$ %.2f%n",
                        item.getItem().getNome(), item.getQuantidade(), item.getSubtotal());
            }

            System.out.printf("Total do pedido: R$ %.2f%n", pedido.getValorTotal());
            totalGeral += pedido.getValorTotal();
            System.out.println("--------------------");
        }

        System.out.printf("\nTOTAL GERAL: R$ %.2f%n", totalGeral);
    }
}
