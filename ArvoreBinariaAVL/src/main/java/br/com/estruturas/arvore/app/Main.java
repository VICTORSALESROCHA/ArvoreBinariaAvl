package br.com.estruturas.arvore.app;

import br.com.estruturas.arvore.service.MenuService;
import br.com.estruturas.arvore.tree.ArvoreAVL;

import java.util.Scanner;

/**
 * Ponto de entrada do programa.
 */
public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

        try (Scanner scanner = new Scanner(System.in)) {
            MenuService menu = new MenuService(arvore, scanner);
            menu.iniciar();
        }
    }
}
