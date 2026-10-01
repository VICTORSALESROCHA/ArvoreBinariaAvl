package br.com.estruturas.arvore.model;

/**
 * Representa um nó da árvore.
 * Cada nó possui uma chave inteira, um dado associado,
 * referências para os filhos e a altura usada pelo balanceamento AVL.
 */
public class No {
    private final int valor;
    private final String dado;
    private No esquerda;
    private No direita;
    private int altura;

    public No(int valor, String dado) {
        this.valor = valor;
        this.dado = dado;
        this.altura = 1;
    }

    public int getValor() {
        return valor;
    }

    public String getDado() {
        return dado;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String resumo() {
        return valor + " [" + dado + "]";
    }

    @Override
    public String toString() {
        return resumo();
    }
}
