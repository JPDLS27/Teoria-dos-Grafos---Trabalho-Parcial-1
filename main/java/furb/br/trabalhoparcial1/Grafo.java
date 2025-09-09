/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package furb.br.trabalhoparcial1;

/**
 * @author João Paulo de Lima
 * @author Rembrandt R. Rohde
 */
import java.util.ArrayList;

public class Grafo<TIPO> {

    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;
    private int tempo = 0;

    public Grafo() {
        this.vertices = new ArrayList<Vertice<TIPO>>();
        this.arestas = new ArrayList<Aresta<TIPO>>();
    }

    public void adicionarVertice(TIPO dado) {
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(dado);
        this.vertices.add(novoVertice);
    }

    public void adicionarAresta(TIPO vInicio, TIPO vFim) {
        Vertice<TIPO> inicio = this.getVertice(vInicio);
        Vertice<TIPO> fim = this.getVertice(vFim);
        Aresta<TIPO> aresta = new Aresta<TIPO>(inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<TIPO> getVertice(TIPO dado) {
        Vertice<TIPO> vertice = null;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getDado().equals(dado)) {
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }

    public void buscaEmLargura() {
        ArrayList<Vertice<TIPO>> marcados = new ArrayList<Vertice<TIPO>>();
        ArrayList<Vertice<TIPO>> fila = new ArrayList<Vertice<TIPO>>();
        Vertice<TIPO> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while (fila.size() > 0) {
            Vertice<TIPO> visitado = fila.get(0);
            for (int i = 0; i < visitado.getArestasSaida().size(); i++) {
                Vertice<TIPO> proximo = visitado.getArestasSaida().get(i).getFim();
                if (!marcados.contains(proximo)) { // verifica se o vértice foi ou n marcado
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }

    public int[][] imprimirMatrizAdj(int[][] matrizAdj, String vi, String vj, int peso) {

        System.out.println("Matriz de Adjacencia:");

        System.out.print("   ");
        for (int coluna = 0; coluna < vertices.size(); coluna++) {
            System.out.print(this.vertices.get(coluna).getDado() + " ");
        }
        System.out.println();

        for (int linha = 0; linha < matrizAdj.length; linha++) {

            System.out.print("v" + (linha + 1) + "  ");

            for (int coluna = 0; coluna < matrizAdj[linha].length; coluna++) {
                if (vertices.get(linha).getDado().equals(vi) && vertices.get(coluna).getDado().equals(vj)) {
                    matrizAdj[linha][coluna] = peso;
                }
                System.out.print(matrizAdj[linha][coluna] + "  ");
            }
            System.out.println();
        }
        return matrizAdj;
    }

    public String buscaEmProfundidade(int[][] matrizAdj) {
        String ordem = "";

        for (int u = 0; u < vertices.size(); u++) {
            vertices.get(u).setCor("B");
        }

        for (int u = 0; u < vertices.size(); u++) {
            if (vertices.get(u).getCor().equals("B")) {
                ordem = "visitou " + vertices.get(u).getDado() + "\n";
                ordem = dfsVisit(u, matrizAdj, ordem);
            }
        }
        return ordem;
    }

    public String dfsVisit(int u, int[][] matrizAdj, String ordem) {
        vertices.get(u).setCor("C");
        tempo = tempo + 1;
        vertices.get(u).setD(tempo);

        for (int v = 0; v < vertices.size(); v++) {
            if (matrizAdj[u][v] != "0  " && vertices.get(v).getCor().equals("B")) {
                ordem += "visitou " + vertices.get(v).getDado() + "\n";
                ordem = dfsVisit(v, matrizAdj, ordem);
            }
        }
        vertices.get(u).setCor("P");
        tempo = tempo + 1;
        vertices.get(u).setF(tempo);
        ordem += vertices.get(u).getDado() + " saiu\n";
        return ordem;
    }

    public String arestasDoGrafo(int[][] matrizAdj) {
        String conjuntoE = "E = {";
        int qtdArestas = 0;
        int controle = 0;
        Double quantiaA = 0.0;

        for (int i = 0; i < matrizAdj.length; i++) {
            for (int j = 0; j < matrizAdj[i].length; j++) {
                if (matrizAdj[i][j] != 0) {
                    for (int u = 0; u <= controle; u++) {
                        if (controle <= u) {
                            if (arestas.size() == u + 1) {
                                conjuntoE += "(" + arestas.get(controle).getInicio().getDado() + ", "
                                        + arestas.get(controle).getFim().getDado() + ")";
                            } else {
                                conjuntoE += "(" + arestas.get(controle).getInicio().getDado() + ", "
                                        + arestas.get(controle).getFim().getDado() + "), ";
                            }
                        }
                    }
                    if (matrizAdj[i][j] == matrizAdj[j][i] && controle < arestas.size()) {
                        quantiaA += arestas.get(controle).getPeso() / 2.0;
                        controle++;
                    } else if (matrizAdj[i][j] != matrizAdj[j][i] && controle < arestas.size()) {
                        qtdArestas += arestas.get(controle).getPeso();
                        controle++;
                    }
                }
            }
        }

        qtdArestas += quantiaA;
        return conjuntoE += "}\n Quantidade de Arestas: " + qtdArestas;
    }

    public String tipoDoGrafo(int[][] matrizAdj) {
        String tipo = "";
        String dir = "não dirigido";
        String simples = "simples";
        String nulo = "nulo";
        String full = "completo";

        for (int i = 0; i < matrizAdj.length; i++) {
            for (int j = 0; j < matrizAdj.length; j++) {
            }
        }

        for (int i = 0; i < matrizAdj.length; i++) {
            for (int j = 0; j < matrizAdj.length; j++) {
                if ((i == j && matrizAdj[i][j] != 0) || matrizAdj[i][j] > 1) {
                    simples = "multigrafo";
                }
                if (matrizAdj[i][j] != matrizAdj[j][i]) {
                    dir = "dirigido";
                }
                if (matrizAdj[i][j] != 0) {
                    nulo = "não nulo";
                }
                if (simples != "multigrafo" && matrizAdj[i][j] == 0 && i != j) {
                    full = "não completo";
                }
            }
        }
        tipo = "Grafo " + simples + ", " + dir + ", " + nulo + " e " + full;

        return tipo;
    }

    public static String tipoDoGrafo1(int[][] matriz) {
        int n = matriz.length; // número de vértices
        boolean dirigido = false; // verifica se o grafo é direcionado
        boolean simples = true; // verifica se é simples (sem laços e sem arestas múltiplas)
        boolean regular = true; // verifica se é regular (todos vértices têm o mesmo grau)
        boolean completo = true; // verifica se é completo (todos os vértices conectados entre si)
        boolean nulo = true; // verifica se é nulo (sem arestas)
        int[] graus = new int[n]; // vetor para armazenar o grau de cada vértice
        int grauEsperado = -1; // usado para verificar regularidade
        // percorre todos os vértices
        for (int i = 0; i < n; i++) {
            graus[i] = 0;
            for (int j = 0; j < n; j++) {

                if (i == j && matriz[i][j] != 0) {
                    simples = false; // se tem valor na diagonal, tem laço, logo não é simples
                }
                if (matriz[i][j] > 1) {
                    simples = false; // se maior que 1, há múltiplas arestas, logo não é simples
                }

                if (matriz[i][j] != 0) {
                    nulo = false; // se existe alguma aresta, logo não é nulo
                }
                if (!dirigido && matriz[i][j] == 0 && i != j) {

                    completo = false; // se falta conexão entre vértices diferentes, logo não é completo
                }
                graus[i] += matriz[i][j]; // soma as arestas incidentes no vértice i
            }
        }

        // se não for dirigido, o grau deve ser recalculado (pois cada aresta foi
        // contada duas vezes)
        if (!dirigido) {
            for (int i = 0; i < n; i++) {
                graus[i] = 0;
                for (int j = 0; j < n; j++) {
                    graus[i] += matriz[i][j];
                }
            }
        }
        // verifica se todos os vértices têm o mesmo grau (questão de regularidade)
        grauEsperado = graus[0];
        for (int g : graus) {
            if (g != grauEsperado) {
                regular = false;
                break;
            }
        }
        StringBuilder tipo = new StringBuilder("Tipo do grafo: ");
        tipo.append(dirigido ? "Dirigido" : "Não-dirigido").append(", ");
        /**
         * Tipo esse if funcionar como tipo: Ele avalia a condição de dirigido.
         * Se for true, no caso ele vai colocar .append("Dirigido") Caso for
         * false, Será .append("Não-dirigido') *
         */
        tipo.append(simples ? "Simples" : "Multigrafo").append(", ");
        tipo.append(regular ? "Regular" : "Irregular").append(", ");
        tipo.append(completo ? "Completo" : "Não completo").append(", ");
        tipo.append(nulo ? "Nulo" : "Não nulo");
        return tipo.toString();
    }

}