/**
 * Trabalho de Teoria dos Grafos - Análise de Grafos usando Matriz de Adjacência
 * Alunos João Paulo e Rembrandt
 * Data: 10/09/2025
 * Professora: Patrícia Kayser Vargas Mangan
 * Classe principal para demonstração e testes
 */
class AnalisadorGrafos {
    public static void main(String[] args) {
        // Exemplo 1: Grafo não dirigido simples
        System.out.println("=== EXEMPLO 1: Grafo Não Dirigido Simples ===");
        int[][] matriz1 = {
            {0, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {0, 1, 1, 0}
        };
        
        Grafo grafo1 = new Grafo(matriz1);
        demonstrarGrafo(grafo1);
        
        // Exemplo 2: Grafo dirigido
        System.out.println("\n\n=== EXEMPLO 2: Grafo Dirigido ===");
        int[][] matriz2 = {
            {0, 1, 0, 1},
            {0, 0, 1, 0},
            {1, 0, 0, 1},
            {0, 0, 0, 0}
        };
        
        Grafo grafo2 = new Grafo(matriz2);
        demonstrarGrafo(grafo2);
        
        // Exemplo 3: Grafo completo
        System.out.println("\n\n=== EXEMPLO 3: Grafo Completo K4 ===");
        int[][] matriz3 = {
            {0, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 0}
        };
        
        Grafo grafo3 = new Grafo(matriz3);
        demonstrarGrafo(grafo3);
        
        // Exemplo 4: Multigrafo com laços
        System.out.println("\n\n=== EXEMPLO 4: Multigrafo com Laços ===");
        int[][] matriz4 = {
            {1, 2, 0},
            {2, 0, 1},
            {0, 1, 2}
        };
        
        Grafo grafo4 = new Grafo(matriz4);
        demonstrarGrafo(grafo4);
    }
    
    /**
     * Método auxiliar para demonstrar todas as funcionalidades de um grafo
     * @param grafo Instância do grafo a ser analisado
     */
    private static void demonstrarGrafo(Grafo grafo) {
        System.out.println("Matriz de Adjacência:");
        System.out.print(grafo.imprimirMatriz());
        System.out.println("\n" + grafo.tipoDoGrafo());
        System.out.println("\n" + grafo.arestasDoGrafo());
        System.out.println("\n" + grafo.grausDoVertice());
        System.out.println("\n" + grafo.buscaEmProfundidade());
    }
}