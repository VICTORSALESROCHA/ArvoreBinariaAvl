package br.com.estruturas.arvore.util;

import java.util.Scanner;

/**
 * Centraliza a leitura e validação de dados do teclado.
 */
public final class EntradaUtils {

    private EntradaUtils() {
        // Impede instanciação de classe utilitária.
    }

    public static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    public static int lerInteiroNaoNegativo(Scanner scanner, String mensagem) {
        while (true) {
            int valor = lerInteiro(scanner, mensagem);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("Digite um valor maior ou igual a zero.");
        }
    }

    public static String lerTexto(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        String texto = scanner.nextLine().trim();
        return texto.isEmpty() ? "sem descrição" : texto;
    }
}
