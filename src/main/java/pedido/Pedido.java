package pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final ArrayList<ItemPedido> itens;
    private final Cliente cliente;

    public Pedido(int id, ArrayList<ItemPedido> itens,Cliente cliente){
        this.id = id;
        this.itens=itens;
        this.cliente=cliente;
    }

    public double calcularTotal(Cardapio cardapio){
        double total= 0;

        for (ItemPedido item: itens) {
            var shake = item.getShake();
            var qtdShake = item.getQuantidade();
            var adicionais = shake.getAdicionais();

            var precoBase = cardapio.getPrecos().get(shake.getBase());
            var precoBaseComTamanho = precoBase + (precoBase * shake.getTipoTamanho().multiplicador);
            var totalAdicionais = adicionais.stream().map(adicional -> cardapio.getPrecos().get(adicional))
                    .reduce(Double::sum).orElse(0.0);

            total += (precoBaseComTamanho + totalAdicionais) *  qtdShake;
        }

        return total;
    }

    public void adicionarItemPedido(ItemPedido itemPedidoAdicionado){

        if(itens.stream().anyMatch(
                        itemPedido -> itemPedido.getShake().equals(itemPedidoAdicionado.getShake()))
        ){
            ItemPedido pedidoExistente = itens.stream()
                    .filter(itemPedido -> itemPedido.getShake().equals(itemPedidoAdicionado.getShake()))
                    .findAny()
                    .orElseThrow();

            int quantidadeAtualizada = itemPedidoAdicionado.getQuantidade() + pedidoExistente.getQuantidade();
            pedidoExistente.setQuantidade(quantidadeAtualizada);

            this.itens.remove(itemPedidoAdicionado);
            this.itens.add(pedidoExistente);
        }else{
            this.itens.add(itemPedidoAdicionado);
        }
    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {

        if (this.itens.contains(itemPedidoRemovido)) {

            ItemPedido pedidoAtualizado = itens.stream()
                    .filter(itemPedido -> itemPedido.equals(itemPedidoRemovido))
                    .findAny()
                    .orElseThrow();

            pedidoAtualizado.setQuantidade(pedidoAtualizado.getQuantidade() - 1);

            if(pedidoAtualizado.getQuantidade() == 0){
                this.itens.remove(itemPedidoRemovido);
                return true;
            }

            this.itens.remove(itemPedidoRemovido);
            this.itens.add(pedidoAtualizado);

        } else {
            throw new IllegalArgumentException("Item nao existe no pedido.");
        }

        return true;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public int getId(){
        return this.id;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id == pedido.id && itens.equals(pedido.itens) && cliente.equals(pedido.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itens, cliente);
    }

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
