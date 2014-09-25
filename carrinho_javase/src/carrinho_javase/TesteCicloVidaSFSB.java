package carrinho_javase;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.k19.sessionBeans.Carrinho;

public class TesteCicloVidaSFSB {
	public static void main(String[] args) throws NamingException, InterruptedException {
		InitialContext intial = new InitialContext();
		
		Carrinho[] carrinhos = new Carrinho[6];
		
		for (int i = 0; i < carrinhos.length; i++) {
			carrinhos[i] = (Carrinho) intial.lookup("java:global/carrinhoWeb/CarrinhoBean");
			carrinhos[i].adiciona("Chaveiro k19");
			carrinhos[i].adiciona("Caneta k19");
			Thread.sleep(1000);
		}
		
		carrinhos[0].adiciona("Borracha k19");
		
		Thread.sleep(5000);
		
		carrinhos[0].finalizaCompra();
		
	}
}
