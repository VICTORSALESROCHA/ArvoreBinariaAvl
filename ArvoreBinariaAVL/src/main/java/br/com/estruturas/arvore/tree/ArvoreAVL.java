package br.com.estruturas.arvore.tree;

import br.com.estruturas.arvore.model.No;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * Árvore Binária de Busca balanceada pelo algoritmo AVL.
 *
 * A propriedade de busca é mantida em toda inserção:
 * - valores menores ficam à esquerda;
 * - valores maiores ficam à direita.
 *
 * Após a inserção, rotações AVL corrigem possíveis desequilíbrios.
 */
public class ArvoreAVL {
    private No raiz;

    public boolean inserir(int valor, String dado) {
        if (buscar(valor).isPresent()) {
            return false;
        }
        raiz = inserirRecursivo(raiz, valor, dado);
        return true;
    }

    private No inserirRecursivo(No atual, int valor, String dado) {
        if (atual == null) {
            return new No(valor, dado);
        }

        if (valor < atual.getValor()) {
            atual.setEsquerda(inserirRecursivo(atual.getEsquerda(), valor, dado));
        } else {
            atual.setDireita(inserirRecursivo(atual.getDireita(), valor, dado));
        }

        atualizarAltura(atual);
        return balancear(atual);
    }

    /**
     * Busca iterativa por chave.
     * Complexidade esperada na AVL: O(log n).
     */
    public Optional<No> buscar(int valor) {
        No atual = raiz;

        while (atual != null) {
            if (valor == atual.getValor()) {
                return Optional.of(atual);
            }
            atual = valor < atual.getValor()
                    ? atual.getEsquerda()
                    : atual.getDireita();
        }
        return Optional.empty();
    }

    public List<No> preOrdem() {
        List<No> resultado = new ArrayList<>();
        preOrdemRecursivo(raiz, resultado);
        return resultado;
    }

    private void preOrdemRecursivo(No no, List<No> resultado) {
        if (no == null) {
            return;
        }
        resultado.add(no);
        preOrdemRecursivo(no.getEsquerda(), resultado);
        preOrdemRecursivo(no.getDireita(), resultado);
    }

    public List<No> emOrdem() {
        List<No> resultado = new ArrayList<>();
        emOrdemRecursivo(raiz, resultado);
        return resultado;
    }

    private void emOrdemRecursivo(No no, List<No> resultado) {
        if (no == null) {
            return;
        }
        emOrdemRecursivo(no.getEsquerda(), resultado);
        resultado.add(no);
        emOrdemRecursivo(no.getDireita(), resultado);
    }

    public List<No> posOrdem() {
        List<No> resultado = new ArrayList<>();
        posOrdemRecursivo(raiz, resultado);
        return resultado;
    }

    private void posOrdemRecursivo(No no, List<No> resultado) {
        if (no == null) {
            return;
        }
        posOrdemRecursivo(no.getEsquerda(), resultado);
        posOrdemRecursivo(no.getDireita(), resultado);
        resultado.add(no);
    }

    /**
     * BFS: visita a árvore nível por nível utilizando uma fila.
     */
    public List<No> bfs() {
        List<No> resultado = new ArrayList<>();
        if (raiz == null) {
            return resultado;
        }

        Queue<No> fila = new ArrayDeque<>();
        fila.offer(raiz);

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            resultado.add(atual);

            if (atual.getEsquerda() != null) {
                fila.offer(atual.getEsquerda());
            }
            if (atual.getDireita() != null) {
                fila.offer(atual.getDireita());
            }
        }
        return resultado;
    }

    /**
     * DFS iterativa: usa uma pilha e registra a profundidade de cada nó.
     */
    public List<String> dfsComProfundidade() {
        List<String> resultado = new ArrayList<>();
        if (raiz == null) {
            return resultado;
        }

        Deque<NoProfundidade> pilha = new ArrayDeque<>();
        pilha.push(new NoProfundidade(raiz, 0));

        while (!pilha.isEmpty()) {
            NoProfundidade item = pilha.pop();
            No atual = item.no;
            resultado.add(atual.resumo() + " {profundidade=" + item.profundidade + "}");

            // Empilha primeiro a direita para que a esquerda seja visitada antes.
            if (atual.getDireita() != null) {
                pilha.push(new NoProfundidade(atual.getDireita(), item.profundidade + 1));
            }
            if (atual.getEsquerda() != null) {
                pilha.push(new NoProfundidade(atual.getEsquerda(), item.profundidade + 1));
            }
        }
        return resultado;
    }

    /**
     * Calcula a altura por recursão, como solicitado na atividade.
     * Árvore vazia = 0; folha = 1.
     */
    public int alturaRecursiva() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(No no) {
        if (no == null) {
            return 0;
        }
        return 1 + Math.max(
                alturaRecursiva(no.getEsquerda()),
                alturaRecursiva(no.getDireita())
        );
    }

    /**
     * Gera uma representação textual hierárquica da árvore.
     */
    public String estrutura() {
        if (raiz == null) {
            return "(árvore vazia)";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("RAIZ: ").append(raiz.resumo()).append(System.lineSeparator());
        montarEstrutura(
                raiz.getEsquerda(),
                "",
                raiz.getDireita() == null,
                "E",
                sb
        );
        montarEstrutura(raiz.getDireita(), "", true, "D", sb);
        return sb.toString();
    }

    private void montarEstrutura(No no,
                                 String prefixo,
                                 boolean ultimo,
                                 String lado,
                                 StringBuilder sb) {
        if (no == null) {
            return;
        }

        sb.append(prefixo)
                .append(ultimo ? "└── " : "├── ")
                .append(lado)
                .append(": ")
                .append(no.resumo())
                .append(System.lineSeparator());

        String novoPrefixo = prefixo + (ultimo ? "    " : "│   ");
        boolean temDireita = no.getDireita() != null;

        if (no.getEsquerda() != null) {
            montarEstrutura(no.getEsquerda(), novoPrefixo, !temDireita, "E", sb);
        }
        if (no.getDireita() != null) {
            montarEstrutura(no.getDireita(), novoPrefixo, true, "D", sb);
        }
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    // ---------------- AVL ----------------

    private No balancear(No no) {
        int fator = fatorBalanceamento(no);

        // Caso Esquerda-Esquerda ou Esquerda-Direita
        if (fator > 1) {
            if (fatorBalanceamento(no.getEsquerda()) < 0) {
                no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
            }
            return rotacaoDireita(no);
        }

        // Caso Direita-Direita ou Direita-Esquerda
        if (fator < -1) {
            if (fatorBalanceamento(no.getDireita()) > 0) {
                no.setDireita(rotacaoDireita(no.getDireita()));
            }
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No rotacaoDireita(No y) {
        No x = y.getEsquerda();
        No subArvore = x.getDireita();

        x.setDireita(y);
        y.setEsquerda(subArvore);

        atualizarAltura(y);
        atualizarAltura(x);
        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.getDireita();
        No subArvore = y.getEsquerda();

        y.setEsquerda(x);
        x.setDireita(subArvore);

        atualizarAltura(x);
        atualizarAltura(y);
        return y;
    }

    private void atualizarAltura(No no) {
        no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));
    }

    private int altura(No no) {
        return no == null ? 0 : no.getAltura();
    }

    private int fatorBalanceamento(No no) {
        return no == null ? 0 : altura(no.getEsquerda()) - altura(no.getDireita());
    }

    private static class NoProfundidade {
        private final No no;
        private final int profundidade;

        private NoProfundidade(No no, int profundidade) {
            this.no = no;
            this.profundidade = profundidade;
        }
    }
}
