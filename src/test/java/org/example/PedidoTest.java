package org.example;

import org.example.models.Item;
import org.example.models.Pedido;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PedidoTest {
    public static final double DELTA = 0.00001;

    private Pedido pedido;
    private Item item;
    private List<Item> itens;
    @Before
    public void setUp() throws Exception {
        pedido = new Pedido();
        item = new Item();
        itens = new ArrayList<Item>();
    }

    private void assertResumoPedido(double valorTotal, double desconto) {
        assertEquals(valorTotal, pedido.getValorTotal(), DELTA);
        assertEquals(desconto, pedido.getValorDesconto(), DELTA);
    }

    @Test
    public void deveAdicionarUmItemNoPedido() {
        List<Item> itens = new ArrayList<Item>();
        Item item = new Item();
        itens.add(item);

        pedido.setItens(itens);
        assertNotNull(pedido.getItens());
        assertEquals(itens.size(), pedido.getItens().size());
        assertFalse("Não deve ser vazio", itens.isEmpty());
    }

    @Test
    public void deveCalcularValorTotalEDescontoParaPedidoVazio() {
        // nesse caso deve retornar zero para valor total e desconto
        assertResumoPedido(0.0, 0.0);
    }

    @Test
    public void deveCalcularResumoParaUmItemSemDesconto() {
        item.setPreco(5.0);
        item.setDescricao("Sabonete");
        item.setQuantidade(5);

        pedido.adicionarItem(item);

        assertResumoPedido(25, 0.0);
    }
}
