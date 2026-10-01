package br.com.estruturas.arvore.service;

import br.com.estruturas.arvore.model.No;
import br.com.estruturas.arvore.tree.ArvoreAVL;
import br.com.estruturas.arvore.util.EntradaUtils;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Responsável apenas pela interação com o usuário.
 * A lógica da árvore permanece na classe ArvoreAVL.
 */
public class MenuService {
    private final ArvoreAVL arvore;
    private final Scanner scanner;

    // Lambda + Stream: transforma a lista de nós em uma sequência legível.
    private final Function<List<No>, String> formatarPercurso = nos -> nos.stream()
            .map(No::resumo)
            .collect(Collectors.joining(" -> "));

    // Classe anônima: exemplo de função/objeto anônimo para padronizar cabeçalhos.
    private final Consumer<String> mostrarCabecalho = new Consumer<>() {
        @Override
        public void accept(String titulo) {
            System.out.println();
            System.out.println("============================================================");
            System.out.println(titulo);
            System.out.println("============================================================");
        }
    };

    public MenuService(ArvoreAVL arvore, Scanner scanner) {
        this.arvore = arvore;
        this.scanner = scanner;
    }

    public void iniciar() {
        mostrarCabecalho.accept("ÁRVORE BINÁRIA DE BUSCA BALANCEADA - AVL");
        System.out.println("Valores menores ficam à esquerda e maiores à direita.");
        System.out.println("A AVL realiza rotações automaticamente para manter o balanceamento.");

        carregarValoresIniciais();

        int opcao;
        do {
            exibirMenu();
            opcao = EntradaUtils.lerInteiro(scanner, "Escolha uma opção: ");
            executarOpcao(opcao);
        } while (opcao != 10);
    }

    private void carregarValoresIniciais() {
        int quantidade = EntradaUtils.lerInteiroNaoNegativo(
                scanner,
                "Quantos valores deseja inserir inicialmente? "
        );

        int inseridos = 0;
        while (inseridos < quantidade) {
            System.out.println("\nNó inicial " + (inseridos + 1) + " de " + quantidade);
            if (inserirValor()) {
                inseridos++;
            }
        }
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("------------------------- MENU -------------------------");
        System.out.println("1  - Inserir valor");
        System.out.println("2  - Buscar valor");
        System.out.println("3  - Mostrar Pré-ordem (Raiz -> Esquerda -> Direita)");
        System.out.println("4  - Mostrar Em ordem (Esquerda -> Raiz -> Direita)");
        System.out.println("5  - Mostrar Pós-ordem (Esquerda -> Direita -> Raiz)");
        System.out.println("6  - Mostrar BFS - Busca em Largura");
        System.out.println("7  - Mostrar DFS - Busca em Profundidade");
        System.out.println("8  - Mostrar altura da árvore");
        System.out.println("9  - Mostrar estrutura da árvore");
        System.out.println("10 - Sair");
        System.out.println("--------------------------------------------------------");
    }

    private void executarOpcao(int opcao) {
        // Switch com arrow labels mantém o menu limpo e legível.
        switch (opcao) {
            case 1 -> inserirValor();
            case 2 -> buscarValor();
            case 3 -> mostrarPercurso("PRÉ-ORDEM", arvore.preOrdem());
            case 4 -> mostrarPercurso("EM ORDEM", arvore.emOrdem());
            case 5 -> mostrarPercurso("PÓS-ORDEM", arvore.posOrdem());
            case 6 -> mostrarPercurso("BFS - BUSCA EM LARGURA", arvore.bfs());
            case 7 -> mostrarDfs();
            case 8 -> mostrarAltura();
            case 9 -> mostrarEstrutura();
            case 10 -> System.out.println("Programa encerrado. Até a próxima!");
            default -> System.out.println("Opção inválida. Escolha uma opção de 1 a 10.");
        }
    }

    private boolean inserirValor() {
        int valor = EntradaUtils.lerInteiro(scanner, "Digite o valor inteiro do nó: ");
        String dado = EntradaUtils.lerTexto(scanner, "Digite o dado/descrição do nó: ");

        if (arvore.inserir(valor, dado)) {
            System.out.println("Valor " + valor + " inserido com sucesso.");
            return true;
        }

        System.out.println("O valor " + valor + " já existe. Valores duplicados não são inseridos.");
        return false;
    }

    private void buscarValor() {
        mostrarCabecalho.accept("BUSCA DE VALOR");
        int valor = EntradaUtils.lerInteiro(scanner, "Digite o valor que deseja buscar: ");

        arvore.buscar(valor)
                .ifPresentOrElse(
                        no -> System.out.println("Valor encontrado: " + no.resumo()),
                        () -> System.out.println("Valor " + valor + " não encontrado na árvore.")
                );
    }

    private void mostrarPercurso(String titulo, List<No> percurso) {
        mostrarCabecalho.accept(titulo);
        if (percurso.isEmpty()) {
            System.out.println("A árvore está vazia.");
            return;
        }
        System.out.println("Sequência visitada:");
        System.out.println(formatarPercurso.apply(percurso));
    }

    private void mostrarDfs() {
        mostrarCabecalho.accept("DFS - BUSCA EM PROFUNDIDADE");
        List<String> percurso = arvore.dfsComProfundidade();

        if (percurso.isEmpty()) {
            System.out.println("A árvore está vazia.");
            return;
        }

        // Stream usado para numerar as visitas sem alterar os dados da árvore.
        IntStream.range(0, percurso.size())
                .forEach(i -> System.out.printf(
                        "%dº visitado: %s%n",
                        i + 1,
                        percurso.get(i)
                ));
    }

    private void mostrarAltura() {
        mostrarCabecalho.accept("ALTURA DA ÁRVORE");
        System.out.println("Altura calculada recursivamente: " + arvore.alturaRecursiva());
        System.out.println("Convenção usada: árvore vazia = 0 e nó folha = 1.");
    }

    private void mostrarEstrutura() {
        mostrarCabecalho.accept("ESTRUTURA HIERÁRQUICA DA ÁRVORE");
        System.out.print(arvore.estrutura());
        System.out.println("Legenda: E = filho esquerdo | D = filho direito");
    }
}
