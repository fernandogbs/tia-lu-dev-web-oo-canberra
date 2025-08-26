import java.util.ArrayList;
import java.util.List;

public class CentralDeDados {
    private static CentralDeDados instancia;

    private List<Cliente> clientes;
    private List<ItemCardapio> cardapio;
    private List<Pedido> pedidos;

    private int proximoCodigoCliente;
    private int proximoCodigoItem;
    private int proximoNumeroPedido;

    private CentralDeDados() {
        this.clientes = new ArrayList<>();
        this.cardapio = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.proximoCodigoCliente = 1;
        this.proximoCodigoItem = 1;
        this.proximoNumeroPedido = 1;

        popularDados();
    }

    public static CentralDeDados getInstancia() {
        if (instancia == null) {
            instancia = new CentralDeDados();
        }
        return instancia;
    }

    private void popularDados() {
        adicionarItemCardapio("Pizza Margherita", 35.90);
        adicionarItemCardapio("Hambúrguer Clássico", 28.50);
        adicionarItemCardapio("Batata Frita", 12.00);
        adicionarItemCardapio("Refrigerante 350ml", 5.50);
        adicionarItemCardapio("Salada Caesar", 22.90);

        adicionarCliente("João Silva", "(11) 99999-1234");
        adicionarCliente("Maria Santos", "(11) 98888-5678");
    }

    public Cliente adicionarCliente(String nome, String telefone) {
        Cliente cliente = new Cliente(proximoCodigoCliente++, nome, telefone);
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscarClientePorCodigo(int codigo) {
        for (Cliente cliente : clientes) {
            if (cliente.getCodigo() == codigo) {
                return cliente;
            }
        }
        return null;
    }

    public ItemCardapio adicionarItemCardapio(String nome, double preco) {
        ItemCardapio item = new ItemCardapio(proximoCodigoItem++, nome, preco);
        cardapio.add(item);
        return item;
    }

    public List<ItemCardapio> getCardapio() {
        return new ArrayList<>(cardapio);
    }

    public ItemCardapio buscarItemPorCodigo(int codigo) {
        for (ItemCardapio item : cardapio) {
            if (item.getCodigo() == codigo) {
                return item;
            }
        }
        return null;
    }

    public Pedido criarPedido(Cliente cliente) {
        Pedido pedido = new Pedido(proximoNumeroPedido++, cliente);
        pedidos.add(pedido);
        return pedido;
    }

    public List<Pedido> getPedidos() {
        return new ArrayList<>(pedidos);
    }

    public List<Pedido> getPedidosPorStatus(StatusPedido status) {
        List<Pedido> pedidosFiltrados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getStatus() == status) {
                pedidosFiltrados.add(pedido);
            }
        }
        return pedidosFiltrados;
    }

    public Pedido buscarPedidoPorNumero(int numero) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumero() == numero) {
                return pedido;
            }
        }
        return null;
    }

    public double getValorTotalArrecadado() {
        double total = 0.0;
        for (Pedido pedido : pedidos) {
            total += pedido.getValorTotal();
        }
        return total;
    }

    public int getTotalPedidos() {
        return pedidos.size();
    }
}
