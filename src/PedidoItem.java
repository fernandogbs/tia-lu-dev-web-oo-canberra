public class PedidoItem {
    private ItemCardapio item;
    private int quantidade;

    public PedidoItem(ItemCardapio item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public ItemCardapio getItem() {
        return item;
    }

    public void setItem(ItemCardapio item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return item.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return "PedidoItem{" +
                "item=" + item.getNome() +
                ", quantidade=" + quantidade +
                ", subtotal=" + String.format("%.2f", getSubtotal()) +
                '}';
    }
}
