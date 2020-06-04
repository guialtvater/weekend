package weekend.teste;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;
import weekend.model.Cliente;
import weekend.model.Produto;
import weekend.model.Pedido;
import weekend.model.Evento;

public class testesUnitariosModels {

	@Test
	public void getSetProduto() {
		Produto teste = new Produto();
		teste.setNome("Limonada");
		teste.setDescricao("Bebida a base de limão");
		teste.setValor(15.00);
		assertEquals(teste.nome,"Limonada");
		assertEquals(teste.descricao,"Bebida a base de limão");
		assertEquals(teste.valor, Double.valueOf("15.00"));
	}
	

	@Test
	public void clienteMenor() {
	     LocalDate dataNascimento = LocalDate.of(2005, 1, 1);
	     String mensagemCliente = Cliente.calcularIdade(dataNascimento, LocalDate.of(2020, 6, 1));
	     assertEquals(mensagemCliente,"Usuário menor de idade");  
	}
		
	@Test
	public void clienteMaior() {
	     LocalDate dataNascimento = LocalDate.of(1992, 1, 1);
	     String mensagemCliente = Cliente.calcularIdade(dataNascimento, LocalDate.of(2020, 6, 1));
	     assertEquals(mensagemCliente,"Usuário pode consumir bebida alcóolica");
	}
	
	@Test
	public void montarPedido() {
		Pedido pedido1 = new Pedido();
		Produto produto1 = new Produto();
		Produto produto2 = new Produto();
		Produto produto3 = new Produto();
		Produto produto4 = new Produto();
		pedido1.adicionarProduto(pedido1, produto1);
		pedido1.adicionarProduto(pedido1, produto2);
		pedido1.adicionarProduto(pedido1, produto3);
		pedido1.adicionarProduto(pedido1, produto4);
		assertEquals(pedido1.getProdutos().size(),4);
	}
	
	@Test
	public void valorPedido() {
		Pedido pedido1 = new Pedido();
		pedido1.setValor(0.0);
		Produto produto1 = new Produto();
		Produto produto2 = new Produto();
		produto1.setValor(15.00);
		produto2.setValor(25.00);
		pedido1.valorPedido(pedido1, produto1);
		pedido1.valorPedido(pedido1, produto2);
		assertEquals(pedido1.valor,Double.valueOf("40.00"));
	}
	
	@Test
	public void pagamentoClienteSaldoPositivo() {
		Cliente cliente1 = new Cliente();
		cliente1.setSaldo(40.00);
		Pedido pedido1 = new Pedido();
		pedido1.setValor(0.0);
		Produto produto1 = new Produto();
		Produto produto2 = new Produto();
		produto1.setValor(15.00);
		produto2.setValor(25.00);
		pedido1.valorPedido(pedido1, produto1);
		pedido1.valorPedido(pedido1, produto2);
		String mensagemCliente = Pedido.pagamentoCliente(cliente1,pedido1);
		assertEquals(mensagemCliente,"Pedido realizado com sucesso!");
	}
	
	@Test
	public void pagamentoClienteSaldoNegativo() {
		Cliente cliente1 = new Cliente();
		cliente1.setSaldo(39.00);
		Pedido pedido1 = new Pedido();
		pedido1.setValor(0.0);
		Produto produto1 = new Produto();
		Produto produto2 = new Produto();
		produto1.setValor(15.00);
		produto2.setValor(25.00);
		pedido1.valorPedido(pedido1, produto1);
		pedido1.valorPedido(pedido1, produto2);
		String mensagemCliente = Pedido.pagamentoCliente(cliente1,pedido1);
		assertEquals(mensagemCliente,"Saldo insuficiente!");
	}
	
	@Test
	public void novoSaldoClientePositivo() {
		Cliente cliente1 = new Cliente();
		cliente1.setSaldo(40.00);
		Pedido pedido1 = new Pedido();
		pedido1.setValor(0.0);
		Produto produto1 = new Produto();
		Produto produto2 = new Produto();
		produto1.setValor(15.00);
		produto2.setValor(25.00);
		pedido1.valorPedido(pedido1, produto1);
		pedido1.valorPedido(pedido1, produto2);
		String mensagemCliente = Pedido.pagamentoCliente(cliente1,pedido1);
		assertEquals(cliente1.getSaldo(),Double.valueOf("0.00"));
	}
	
	@Test
	public void manterSaldoClienteNegativo() {
		Cliente cliente1 = new Cliente();
		cliente1.setSaldo(39.00);
		Pedido pedido1 = new Pedido();
		pedido1.setValor(0.0);
		Produto produto1 = new Produto();
		Produto produto2 = new Produto();
		produto1.setValor(15.00);
		produto2.setValor(25.00);
		pedido1.valorPedido(pedido1, produto1);
		pedido1.valorPedido(pedido1, produto2);
		String mensagemCliente = Pedido.pagamentoCliente(cliente1,pedido1);
		assertEquals(cliente1.getSaldo(),Double.valueOf("39.00"));
	}
	
	@Test
	public void pagamentoEntradaEvento() {
		Cliente cliente1 = new Cliente();
		cliente1.setSaldo(50.00);
		Evento evento1 = new Evento();
		evento1.setValorEntrada(50.00);
		String mensagemCliente = Evento.pagamentoEntrada(cliente1,evento1);
		assertEquals(mensagemCliente,"Pedido realizado com sucesso!");
	}
	

}
