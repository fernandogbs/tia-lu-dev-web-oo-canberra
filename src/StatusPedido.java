public enum StatusPedido {
    ACEITO("Aceito"),
    PREPARANDO("Preparando"),
    FEITO("Feito"),
    AGUARDANDO_ENTREGADOR("Aguardando Entregador"),
    SAIU_PARA_ENTREGA("Saiu para Entrega"),
    ENTREGUE("Entregue");

    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusPedido proximo() {
        switch (this) {
            case ACEITO:
                return PREPARANDO;
            case PREPARANDO:
                return FEITO;
            case FEITO:
                return AGUARDANDO_ENTREGADOR;
            case AGUARDANDO_ENTREGADOR:
                return SAIU_PARA_ENTREGA;
            case SAIU_PARA_ENTREGA:
                return ENTREGUE;
            case ENTREGUE:
                return ENTREGUE;
            default:
                return this;
        }
    }

    @Override
    public String toString() {
        return descricao;
    }
}
