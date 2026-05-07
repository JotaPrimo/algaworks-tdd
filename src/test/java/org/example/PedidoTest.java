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

    private void assertResumoPedido(double valorTotal, double desconto, double valorFinal) {
        assertEquals(valorTotal, pedido.getValorTotal(), DELTA);
        assertEquals(desconto, pedido.getValorDesconto(), DELTA);
        assertEquals(valorFinal, pedido.getValorFinal(), DELTA);
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
        assertResumoPedido(0.0, 0.0, 0.0);
    }

    @Test
    public void deveCalcularResumoParaUmItemSemDesconto() {
        item.setPreco(5.0);
        item.setDescricao("Sabonete");
        item.setQuantidade(5);

        pedido.adicionarItem(item);

        assertResumoPedido(25, 0.0, 25.0);
    }

    @Test
    public void devePermitirAdicionarVariosItens() {
        pedido.adicionarItens(itens);
        assertEquals("Deve ser igual a quantidade de iten. Agora é zero", itens.size(), pedido.getItens().size());
    }

    @Test
    public void aoAdicionarListaItensQuantidadeDeveSerIgual() {
        preencheListaDeItens();

        pedido.adicionarItens(itens);
        assertEquals("Deve ser igual a quantidade de iten. Agora é 6", itens.size(), pedido.getItens().size());
    }

    @Test
    public void deveCalcularValorTotalDoPedidoComMuitosItensSemDesconto() {
        preencheListaDeItens();

        pedido.adicionarItens(itens);
        assertResumoPedido(71.00, 0.0, 71.00);
    }

    @Test
    public void deveCalcularValorDescontoDoPedidoComMuitosItens() {
        preencheListaDeItens();

        pedido.adicionarItens(itens);
        pedido.setValorDesconto(31.0);

        assertResumoPedido(71.0, 31.0, 40.00);
    }

    @Test
    public void deveCalcularValorTotalDoPedidoComDesconto() {
        preencheListaDeItens();
        pedido.setValorDesconto(31.00);
        pedido.adicionarItens(itens);


        pedido.fecharPedido();
        assertResumoPedido(71.00, 31.00, 40.00);
    }

    private void preencheListaDeItens() {
        itens.add(new Item("Arroz", 1, 25.0));
        itens.add(new Item("Feijão", 1, 10.0));
        itens.add(new Item("Caixa de ovos", 1, 20.0));
        itens.add(new Item("Lata de óleo", 1, 7.0));
        itens.add(new Item("Caixa de leite", 1, 5.0));
        itens.add(new Item("1 kg batata", 1, 4.0));
    }
}
