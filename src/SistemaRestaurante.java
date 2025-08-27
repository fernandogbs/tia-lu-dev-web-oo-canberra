import java.util.List;

public class SistemaRestaurante {
    private CentralDeDados centralDeDados;

    public SistemaRestaurante() {
        this.centralDeDados = CentralDeDados.getInstancia();
    }

    public Cliente cadastrarCliente(String nome, String telefone) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome do cliente não pode ser vazio");
            return null;
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            System.out.println("Telefone do cliente não pode ser vazio");
            return null;
        }
        return centralDeDados.adicionarCliente(nome.trim(), telefone.trim());
    }

    public List<Cliente> listarClientes() {
        return centralDeDados.getClientes();
    }

    public Cliente buscarCliente(int codigo) {
        return centralDeDados.buscarClientePorCodigo(codigo);
    }

    public ItemCardapio cadastrarItemCardapio(String nome, double preco) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome do item não pode ser vazio");
            return null;
        }
        if (preco <= 0) {
            System.out.println("Preço deve ser maior que zero");
            return null;
        }
        return centralDeDados.adicionarItemCardapio(nome.trim(), preco);
    }

    public List<ItemCardapio> listarCardapio() {
        return centralDeDados.getCardapio();
    }

    public ItemCardapio buscarItemCardapio(int codigo) {
        return centralDeDados.buscarItemPorCodigo(codigo);
    }

    public Pedido criarPedido(int codigoCliente) {
        Cliente cliente = centralDeDados.buscarClientePorCodigo(codigoCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado com código: " + codigoCliente);
            return null;
        }
        return centralDeDados.criarPedido(cliente);
    }

    public boolean adicionarItemAoPedido(int numeroPedido, int codigoItem, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero");
            return false;
        }

        Pedido pedido = centralDeDados.buscarPedidoPorNumero(numeroPedido);
        if (pedido == null) {
            return false;
        }

        ItemCardapio item = centralDeDados.buscarItemPorCodigo(codigoItem);
        if (item == null) {
            return false;
        }

        pedido.adicionarItem(item, quantidade);
        return true;
    }

    public StatusPedido atualizarStatusPedido(int numeroPedido) {
      Pedido pedido = centralDeDados.buscarPedidoPorNumero(numeroPedido);
      if (pedido == null) {
        return null;
      }

      StatusPedido statusAnterior = pedido.getStatus();
      pedido.avancarStatus();

      if (pedido.getStatus() == StatusPedido.SAIU_PARA_ENTREGA) {
        notificarSaidaParaEntrega(pedido);
      }

      return pedido.getStatus() != statusAnterior ? pedido.getStatus() : statusAnterior;
    }

    public List<Pedido> consultarPedidosPorStatus(StatusPedido status) {
        return centralDeDados.getPedidosPorStatus(status);
    }

    public List<Pedido> listarTodosPedidos() {
        return centralDeDados.getPedidos();
    }

    public Pedido buscarPedido(int numero) {
        return centralDeDados.buscarPedidoPorNumero(numero);
    }

    public String gerarRelatorioSimplificado() {
        int totalPedidos = centralDeDados.getTotalPedidos();
        double valorArrecadado = centralDeDados.getValorTotalArrecadado();

        return "=== RELATÓRIO SIMPLIFICADO ===\n" +
               "Total de Pedidos: " + totalPedidos + "\n" +
               "Valor Total Arrecadado: R$ " + String.format("%.2f", valorArrecadado);
    }

    public String gerarRelatorioDetalhado() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RELATÓRIO DETALHADO - TODOS OS PEDIDOS ===\n\n");

        List<Pedido> pedidos = centralDeDados.getPedidos();
        if (pedidos.isEmpty()) {
            sb.append("Nenhum pedido registrado.\n");
        } else {
            for (Pedido pedido : pedidos) {
                sb.append(pedido.toStringDetalhado()).append("\n\n");
            }
            sb.append("RESUMO:\n");
            sb.append("Total de Pedidos: ").append(pedidos.size()).append("\n");
            sb.append("Valor Total Arrecadado: R$ ").append(String.format("%.2f", centralDeDados.getValorTotalArrecadado()));
        }

        return sb.toString();
    }

    private void notificarSaidaParaEntrega(Pedido pedido) {
        System.out.println("NOTIFICAÇÃO: Pedido #" + pedido.getNumero() +
                          " saiu para entrega para " + pedido.getCliente().getNome());
    }
}
