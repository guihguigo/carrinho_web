package br.com.k19.sessionBeans;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
@Remote(Carrinho.class)
public class CarrinhoBean implements Carrinho{
	private Set<String> produtos = new HashSet<String>();
	private static int contadorTotal;
	private static int contadorAtivos;
	private int id;
	
	public void adiciona(String produto) {
		this.produtos.add(produto);
	}
	
	public void remove(String produto) {
		this.produtos.remove(produto);
	}
	
	public Set<String> getProdutos() {
		return this.produtos;
	}

	@Remove
	public void finalizaCompra() {
		System.out.println("Finalizando compras.");
	}
	
	@PostConstruct
	public void postConstruct() {
		synchronized(CarrinhoBean.class) {
			CarrinhoBean.contadorTotal++;
			CarrinhoBean.contadorAtivos++;
			this.id = CarrinhoBean.contadorTotal;
			
			System.out.println("PostConstruct");
			System.out.println("Id: " + this.id);
			System.out.println("Contador total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos" + CarrinhoBean.contadorAtivos);
		}
	}
	
	@PrePassivate
	public void prePassivate() {
		synchronized(CarrinhoBean.class) {
			CarrinhoBean.contadorAtivos--;
			
			System.out.println("PrePassivate");
			System.out.println("Id: " + this.id);
			System.out.println("Contador total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos" + CarrinhoBean.contadorAtivos);
		}
	}
	
	@PostActivate
	public void posActivate() {
		synchronized(CarrinhoBean.class) {
			CarrinhoBean.contadorAtivos++;

			System.out.println("PosActivate");
			System.out.println("Id: " + this.id);
			System.out.println("Contador total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos" + CarrinhoBean.contadorAtivos);
		}
	}
	
	@PreDestroy
	public void preDestroy() {
		synchronized (CarrinhoBean.class) {
			CarrinhoBean.contadorAtivos--;

			System.out.println("PreDestroy");
			System.out.println("Id: " + this.id);
			System.out.println("Contador total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos" + CarrinhoBean.contadorAtivos);
		}
	}
}
