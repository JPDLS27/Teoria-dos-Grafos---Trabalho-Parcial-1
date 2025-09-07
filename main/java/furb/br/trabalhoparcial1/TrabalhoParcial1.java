/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package furb.br.trabalhoparcial1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author João Paulo de Lima
 * @author Rembrandt R. Rohde
 */
public class TrabalhoParcial1 {

    public static void main(String[] args) throws Exception {
        Grafo<String> grafo = new Grafo<>();
        Scanner entrada = new Scanner(System.in);
        char decisao;
        String vi = null;
        String vj = null;
        int peso = 0;

        System.out.print("\nQual a ordem n da matriz de adjacência? \nOrdem n: ");
        int ordemAdj = entrada.nextInt();
        String[][] matrizAdj = new String[ordemAdj][ordemAdj];

        for (int i = 0; i < ordemAdj; i++) {
            grafo.adicionarVertice("v" + (i + 1));
        }

        grafo.imprimirMatrizAdj(matrizAdj, vi, vj, peso);

        /* >>>>>>>>>>>>>> ADICIONANDO ARESTAS AQUI <<<<<<<<<<<<*/
        do {
            System.out.println("\n Você deseja adicionar uma aresta? [S] ou [N]");
            decisao = entrada.next().toUpperCase().charAt(0);

            switch (decisao) {
                case 'S':
                    System.out.println("Quais vertices sao conectados pela aresta?");
                    vi = entrada.next().toLowerCase();
                    vj = entrada.next().toLowerCase();
                    // System.out.println("Qual o peso da aresta?");
                    peso = 1;
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

        System.out.println(grafo.buscaEmProfundidade(matrizAdj));
    }
}
