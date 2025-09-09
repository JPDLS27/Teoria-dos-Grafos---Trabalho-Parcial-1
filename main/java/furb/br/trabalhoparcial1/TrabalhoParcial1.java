/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package furb.br.trabalhoparcial1;

import java.util.Scanner;

/**
 * @author João Paulo de Lima
 * @author Rembrandt R. Rohde
 */
public class TrabalhoParcial1 {

    public TrabalhoParcial1() {
        /*Grafo<String> grafo = new Grafo<>();
        Scanner entrada = new Scanner(System.in);
        char decisao;
        String vi = null;
        String vj = null;
        int peso = 0;

        System.out.print("\nQual a ordem n da matriz de adjacência? \nOrdem n: ");
        int ordemAdj = entrada.nextInt();
        int[][] matrizAdj = new int[ordemAdj][ordemAdj];

        for (int i = 0; i < ordemAdj; i++) {
            grafo.adicionarVertice("v" + (i + 1));
        }

        grafo.imprimirMatrizAdj(matrizAdj, vi, vj, peso);

        // >>>>>>>>>>>>>> ADICIONANDO ARESTAS AQUI <<<<<<<<<<<<
        do {
            System.out.println("\n Você deseja adicionar uma aresta? [S] ou [N]");
            decisao = entrada.next().toUpperCase().charAt(0);

            switch (decisao) {
                case 'S':
                    System.out.println("Quais vertices sao conectados pela aresta?");
                    vi = entrada.next().toLowerCase();
                    vj = entrada.next().toLowerCase();
                    System.out.println("Qual o peso da aresta?");
                    peso = entrada.nextInt();
                    grafo.adicionarAresta(peso, vi, vj);
                    grafo.imprimirMatrizAdj(matrizAdj, vi, vj, peso);
                    break;
                case 'N':
                    break;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }

        } while ('N' != decisao);

        grafo.imprimirMatrizAdj(matrizAdj, vi, vj, peso);

        System.out.println(grafo.tipoDoGrafo(matrizAdj));
        System.out.println(grafo.arestasDoGrafo(matrizAdj));*/

        Grafo g = new Grafo();

        // Dirigido, Multigrafo, Irregular, N?o completo, N?o nulo -> Correto
        int[][] matrizTeste2 = {
                { 0, 0, 1, 0 },
                { 1, 0, 2, 0 },
                { 0, 0, 0, 1 },
                { 1, 0, 1, 1 }
        };
        for (int i = 0; i < matrizTeste2.length; i++) {
            g.adicionarVertice("v" + (i + 1));
        }
        for (int i = 0; i < matrizTeste2.length; i++) {
            for (int j = 0; j < matrizTeste2[i].length; j++) {
                if (matrizTeste2[i][j] != 0) {
                    g.adicionarAresta("v"+(i+1), "v"+(j+1));
                }
            }
        }
        System.out.println(g.tipoDoGrafo(matrizTeste2));
        //System.out.println(g.arestasDoGrafo(matrizTeste2));
        System.out.println(g.buscaEmProfundidade(matrizTeste2));//Nao Sei

        /*int[][] matrizTeste3 = {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        System.out.println(tipoDoGrafo(matrizTeste3));
        // Tipo do grafo: N?o-dirigido, Simples, Regular, N?o completo, Nulo -> correto
        System.out.println(g.arestasDoGrafo(matrizTeste3));
        System.out.println(g.buscaEmProfundidade(matrizTeste3));//Nao Sei

        int[][] matrizTeste4 = {
                { 0, 0 },
                { 0, 0 }
        };
        System.out.println(tipoDoGrafo(matrizTeste4));
        // Tipo do grafo: N?o-dirigido, Simples, Regular, N?o completo, Nulo -> correto
        System.out.println(g.arestasDoGrafo(matrizTeste4)); // Colocar para representação um -
        System.out.println(g.buscaEmProfundidade(matrizTeste4));//Nao Sei

        int[][] matrizTeste5 = {
                { 1, 1 },
                { 1, 1 }
        };
        System.out.println(tipoDoGrafo(matrizTeste5));
        // Tipo do grafo: N?o-dirigido, Multigrafo, Regular, Completo, N?o nulo ->
        // correto
        System.out.println(g.arestasDoGrafo(matrizTeste5));
        System.out.println(g.buscaEmProfundidade(matrizTeste5));//Nao Sei


        int[][] matrizTeste6 = {
        {0, 1, 0, 0, 0}, // Vértice 1 conectado a 2
        {1, 0, 1, 1, 0}, // Vértice 2 conectado a 1, 3, 4
        {0, 1, 0, 0, 1}, // Vértice 3 conectado a 2, 5
        {0, 1, 0, 0, 1}, // Vértice 4 conectado a 2, 5
        {0, 0, 1, 1, 0}  // Vértice 5 conectado a 3, 4
        };

        Grafo g1 = new Grafo();

        // Testando DFS
        String ordemDFS = g1.buscaEmProfundidade(matrizTeste6);
        System.out.println(ordemDFS);

        // Testando arestas para conferência
        System.out.println(g.arestasDoGrafo(matrizTeste6));

        // Testando tipo do grafo
        System.out.println(g1.tipoDoGrafo(matrizTeste6));*/

    }

    public static void main(String[] args) throws Exception {
        new TrabalhoParcial1();
    }
}
