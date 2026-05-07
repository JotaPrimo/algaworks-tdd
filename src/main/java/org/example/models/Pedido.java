package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private List<Item> itens = new ArrayList<>();
    private Double valorTotal = 0.0;
    private Double valorFinal = 0.0;
    private Double valorDesconto = 0.0;

    public Pedido() {
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Double getValorTotal() {
        if (Objects.isNull(valorTotal)) {
            return 0.0;
        }

        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorDesconto() {
        if (Objects.isNull(valorDesconto)) {
            return 0.0;
        }

        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
        setItens(itens);
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void calcularValorFinalDoPedido() {
        double valorTotal = itens.stream()
                .map(item -> item.getPreco() * item.getQuantidade())
                .reduce(0.0, Double::sum);

        setValorFinal(valorTotal - valorDesconto);
    }

    public void adicionarItens(List<Item> novosItens) {
        itens.addAll(novosItens);

        setItens(itens);
    }

    public void calcularValorTotalDoPedido() {
        double valorTotal = itens.stream()
                .map(item -> item.getPreco() * item.getQuantidade())
                .reduce(0.0, Double::sum);

        setValorTotal(valorTotal);
    }

    public void fecharPedido() {
        calcularValorTotalDoPedido();
        calcularValorFinalDoPedido();

        itens.forEach(Item::toString);
    }
}
