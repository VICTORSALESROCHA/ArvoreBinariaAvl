# Roteiro rápido para apresentação em sala

## 1. O que o programa faz
O programa implementa uma Árvore Binária de Busca balanceada por AVL. Cada nó possui um valor inteiro usado como chave e um texto associado. Valores menores são inseridos à esquerda e valores maiores à direita.

## 2. Por que AVL
A AVL mantém a propriedade de uma Árvore Binária de Busca, mas corrige desequilíbrios por rotações depois das inserções. Isso evita que uma sequência como 10, 20, 30 forme uma árvore parecida com uma lista. Nesse exemplo, a AVL faz uma rotação e deixa 20 como raiz, 10 à esquerda e 30 à direita.

## 3. Organização do código
- `Main`: inicia o programa.
- `No`: representa cada nó.
- `ArvoreAVL`: contém inserção, busca, percursos, altura, estrutura e rotações.
- `MenuService`: controla o menu e a interação com o usuário.
- `EntradaUtils`: valida entradas de teclado.

## 4. Percursos para explicar
- Pré-ordem: Raiz -> Esquerda -> Direita.
- Em ordem: Esquerda -> Raiz -> Direita. Em uma ABB, mostra as chaves em ordem crescente.
- Pós-ordem: Esquerda -> Direita -> Raiz.
- BFS: usa fila e percorre nível por nível.
- DFS: usa pilha e avança em profundidade antes de voltar; o programa também mostra a profundidade de cada nó.

## 5. Altura
A altura exigida pela atividade é calculada por recursão. A convenção usada é árvore vazia = 0 e folha = 1.

## 6. Recursos modernos de Java
- Lambda para formatar resultados.
- Streams para transformar e numerar sequências.
- Method reference com `No::resumo`.
- Classe anônima para padronizar cabeçalhos.
- `Optional` na busca para evitar retorno nulo.
- `switch` com sintaxe moderna.

## 7. Demonstração sugerida
Insira: 30, 20, 40, 10, 25, 35, 50.
Depois mostre:
1. Estrutura da árvore.
2. Em ordem (deve sair 10, 20, 25, 30, 35, 40, 50).
3. BFS.
4. DFS.
5. Busca por 25 e por 99.
6. Altura.

Para provar o balanceamento AVL, reinicie e insira 10, 20, 30. A estrutura final terá 20 como raiz.

## 8. Perguntas que podem aparecer
**AVL ainda é uma Árvore Binária de Busca?** Sim. Ela mantém a regra esquerda < raiz < direita e adiciona balanceamento.

**Qual a diferença entre BFS e DFS?** BFS usa fila e visita por níveis; DFS usa pilha/recursão e aprofunda um caminho antes de voltar.

**Por que não aceitar valores duplicados?** Para manter uma regra simples de chave única e evitar ambiguidade na busca.

**Qual a complexidade da busca?** Em uma AVL balanceada, busca e inserção são O(log n) no pior caso. Percursos visitam todos os nós e são O(n).

**Por que não usar DSW?** DSW é muito bom para rebalancear uma árvore inteira em fases, mas AVL é mais adequada para um programa interativo que recebe inserções continuamente, pois rebalanceia após cada inserção.
