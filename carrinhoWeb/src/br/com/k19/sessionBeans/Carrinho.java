package br.com.k19.sessionBeans;

import java.util.Set;

public interface Carrinho {
	void adiciona(String produto);
	void remove(String produto);
	Set<String> getProdutos();
	void finalizaCompra();
}
