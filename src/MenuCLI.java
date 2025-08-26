import java.util.List;
import java.util.Scanner;

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

        while (true) {
            mostrarMenuPrincipal();
            int opcao = scanner.nextInt();

            try {
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
                        System.out.println("Obrigado por usar o sistema!");
                        return;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            pausar();
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
        while (true) {
            System.out.println("\n=== GERENCIAR CARDÁPIO ===");
            System.out.println("1. Cadastrar novo item");
            System.out.println("2. Listar cardápio");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarItemCardapio();
                    break;
                case 2:
                    listarCardapio();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void menuClientes() {
        while (true) {
            System.out.println("\n=== GERENCIAR CLIENTES ===");
            System.out.println("1. Cadastrar novo cliente");
            System.out.println("2. Listar clientes");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void menuPedidos() {
        while (true) {
            System.out.println("\n=== GERENCIAR PEDIDOS ===");
            System.out.println("1. Criar novo pedido");
            System.out.println("2. Atualizar status do pedido");
            System.out.println("3. Consultar pedidos por status");
            System.out.println("4. Listar todos os pedidos");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    criarPedido();
                    break;
                case 2:
                    atualizarStatusPedido();
                    break;
                case 3:
                    consultarPedidosPorStatus();
                    break;
                case 4:
                    listarTodosPedidos();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void menuRelatorios() {
        while (true) {
            System.out.println("\n=== RELATÓRIOS ===");
            System.out.println("1. Relatório Simplificado");
            System.out.println("2. Relatório Detalhado");
            System.out.println("3. Relatório de Métricas Avançadas");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("\n" + sistema.gerarRelatorioSimplificado());
                    break;
                case 2:
                    System.out.println("\n" + sistema.gerarRelatorioDetalhado());
                    break;
                case 3:
                    System.out.println("\n" + sistema.gerarRelatorioMetricas());
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }    private void cadastrarItemCardapio() {
        System.out.println("\n--- Cadastrar Item do Cardápio ---");
        System.out.print("Nome do item: ");
        String nome = scanner.nextLine();

        System.out.print("Preço (R$): ");
        double preco = scanner.nextDouble();

        ItemCardapio item = sistema.cadastrarItemCardapio(nome, preco);
        System.out.println("Item cadastrado com sucesso!");
        System.out.println("Codigo: " + item.getCodigo() + " - " + item.getNome() + " - R$ " + String.format("%.2f", item.getPreco()));
    }

    private void listarCardapio() {
        System.out.println("\n--- CARDÁPIO ---");
        List<ItemCardapio> cardapio = sistema.listarCardapio();

        if (cardapio.isEmpty()) {
            System.out.println("Nenhum item cadastrado no cardápio.");
        } else {
            System.out.printf("%-8s %-30s %10s%n", "CÓDIGO", "NOME", "PREÇO");
            System.out.println("--------------------------------------------------");
            for (ItemCardapio item : cardapio) {
                System.out.printf("%-8d %-30s R$ %7.2f%n",
                    item.getCodigo(), item.getNome(), item.getPreco());
            }
        }
    }

    private void cadastrarCliente() {
        System.out.println("\n--- Cadastrar Cliente ---");
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente cliente = sistema.cadastrarCliente(nome, telefone);
        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println("Codigo: " + cliente.getCodigo() + " - " + cliente.getNome() + " - " + cliente.getTelefone());
    }

    private void listarClientes() {
        System.out.println("\n--- CLIENTES CADASTRADOS ---");
        List<Cliente> clientes = sistema.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.printf("%-8s %-25s %15s%n", "CÓDIGO", "NOME", "TELEFONE");
            System.out.println("---------------------------------------------------");
            for (Cliente cliente : clientes) {
                System.out.printf("%-8d %-25s %15s%n",
                    cliente.getCodigo(), cliente.getNome(), cliente.getTelefone());
            }
        }
    }

    private void criarPedido() {
        System.out.println("\n--- Criar Novo Pedido ---");

        listarClientes();

        System.out.print("Código do cliente: ");
        int codigoCliente = scanner.nextInt();

        try {
            Pedido pedido = sistema.criarPedido(codigoCliente);
            System.out.println("Pedido criado com sucesso!");
            System.out.println("Número do pedido: " + pedido.getNumero());
            System.out.println("Cliente: " + pedido.getCliente().getNome());

            while (true) {
                System.out.println("\n--- Adicionar Itens ao Pedido ---");
                listarCardapio();

                System.out.print("Código do item (0 para finalizar): ");
                int codigoItem = scanner.nextInt();

                if (codigoItem == 0) {
                    break;
                }

                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();

                if (sistema.adicionarItemAoPedido(pedido.getNumero(), codigoItem, quantidade)) {
                    ItemCardapio item = sistema.buscarItemCardapio(codigoItem);
                    System.out.println("Item adicionado: " + item.getNome() + " x" + quantidade);
                } else {
                    System.out.println("Erro ao adicionar item. Verifique o código do item.");
                }
            }

            Pedido pedidoAtualizado = sistema.buscarPedido(pedido.getNumero());
            System.out.println("\n" + pedidoAtualizado.toStringDetalhado());

        } catch (Exception e) {
            System.out.println("Erro ao criar pedido: " + e.getMessage());
        }
    }

    private void atualizarStatusPedido() {
        System.out.println("\n--- Atualizar Status do Pedido ---");
        listarTodosPedidos();

        System.out.print("Número do pedido: ");
        int numeroPedido = scanner.nextInt();

        Pedido pedido = sistema.buscarPedido(numeroPedido);
        if (pedido == null) {
            System.out.println("Pedido não encontrado!");
            return;
        }

        System.out.println("Status atual: " + pedido.getStatus());

        if (sistema.atualizarStatusPedido(numeroPedido)) {
            Pedido pedidoAtualizado = sistema.buscarPedido(numeroPedido);
            System.out.println("Status atualizado para: " + pedidoAtualizado.getStatus());
        } else {
            System.out.println("Não foi possível atualizar o status (pedido já pode estar finalizado).");
        }
    }

    private void consultarPedidosPorStatus() {
        System.out.println("\n--- Consultar Pedidos por Status ---");
        System.out.println("Status disponíveis:");
        StatusPedido[] statuses = StatusPedido.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + ". " + statuses[i]);
        }

        System.out.print("Escolha o status (1-" + statuses.length + "): ");
        int opcao = scanner.nextInt();

        if (opcao < 1 || opcao > statuses.length) {
            System.out.println("Opção inválida!");
            return;
        }

        StatusPedido statusEscolhido = statuses[opcao - 1];
        List<Pedido> pedidos = sistema.consultarPedidosPorStatus(statusEscolhido);

        System.out.println("\n--- Pedidos com status: " + statusEscolhido + " ---");
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado com este status.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }

    private void listarTodosPedidos() {
        System.out.println("\n--- TODOS OS PEDIDOS ---");
        List<Pedido> pedidos = sistema.listarTodosPedidos();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }

    private void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}
