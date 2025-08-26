import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numero;
    private Cliente cliente;
    private List<PedidoItem> itens;
    private StatusPedido status;
    private LocalDateTime dataHora;

    public Pedido(int numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.ACEITO;
        this.dataHora = LocalDateTime.now();
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void adicionarItem(ItemCardapio item, int quantidade) {
        for (PedidoItem pedidoItem : itens) {
            if (pedidoItem.getItem().getCodigo() == item.getCodigo()) {
                pedidoItem.setQuantidade(pedidoItem.getQuantidade() + quantidade);
                return;
            }
        }
        itens.add(new PedidoItem(item, quantidade));
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void avancarStatus() {
        this.status = this.status.proximo();
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public double getValorTotal() {
        double total = 0.0;
        for (PedidoItem item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(numero)
          .append(" - Cliente: ").append(cliente.getNome())
          .append(" - Status: ").append(status)
          .append(" - Data/Hora: ").append(dataHora.format(formatter))
          .append(" - Total: R$ ").append(String.format("%.2f", getValorTotal()));
        return sb.toString();
    }

    public String toStringDetalhado() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("=== PEDIDO #").append(numero).append(" ===\n");
        sb.append("Cliente: ").append(cliente.getNome()).append(" (").append(cliente.getTelefone()).append(")\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Data/Hora: ").append(dataHora.format(formatter)).append("\n");
        sb.append("Itens:\n");
        for (PedidoItem item : itens) {
            sb.append("  - ").append(item.getItem().getNome())
              .append(" x").append(item.getQuantidade())
              .append(" = R$ ").append(String.format("%.2f", item.getSubtotal())).append("\n");
        }
        sb.append("TOTAL: R$ ").append(String.format("%.2f", getValorTotal()));
        return sb.toString();
    }
}
