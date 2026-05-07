package org.example.models;

public class Item {

    private String descricao;
    private int quantidade;
    private Double preco;

    public Item() {
    }

    public Item(String descricao, int quantidade, double preco) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
