# Árvore Binária de Busca AVL - Estruturas de Dados

Projeto Java 17 que implementa uma Árvore Binária de Busca balanceada por AVL.

## Funcionalidades

- Inserção de valor e dado associado
- Busca iterativa
- Pré-ordem
- Em ordem
- Pós-ordem
- BFS (largura)
- DFS (profundidade, com nível)
- Altura recursiva
- Impressão hierárquica da árvore
- Balanceamento AVL com rotações simples e duplas
- Validação de entradas
- Exemplos de Lambda, Streams, method reference e classe anônima

## Estrutura

```text
src/main/java/br/com/estruturas/arvore/
├── app/Main.java
├── model/No.java
├── service/MenuService.java
├── tree/ArvoreAVL.java
└── util/EntradaUtils.java
```

## Executar no IntelliJ IDEA

1. Abra a pasta `ArvoreBinariaAVL`.
2. Configure o Project SDK como JDK 17 ou superior.
3. Aguarde o IntelliJ reconhecer o `pom.xml`.
4. Abra `Main.java`.
5. Clique no botão Run ao lado de `main`.

## Observação

A AVL é uma Árvore Binária de Busca. A diferença é que, após as inserções, ela realiza rotações para manter a diferença de altura entre as subárvores dentro do limite permitido.
