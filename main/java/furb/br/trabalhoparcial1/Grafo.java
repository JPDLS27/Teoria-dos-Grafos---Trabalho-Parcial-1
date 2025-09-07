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

    public void adicionarAresta(int peso, TIPO vInicio, TIPO vFim) {
        Vertice<TIPO> inicio = this.getVertice(vInicio);
        Vertice<TIPO> fim = this.getVertice(vFim);
        Aresta<TIPO> aresta = new Aresta<TIPO>(peso, inicio, fim);
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
                if (!marcados.contains(proximo)) { //verifica se o vértice foi ou n marcado
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }

    public String[][] imprimirMatrizAdj(String[][] matrizAdj, String vi, String vj, int peso) {

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
                    matrizAdj[linha][coluna] = peso + "  ";
                } else if (matrizAdj[linha][coluna] == null) {
                    matrizAdj[linha][coluna] = "0  ";
                }
                System.out.print(matrizAdj[linha][coluna]);
            }
            System.out.println();
        }
        return matrizAdj;
    }

    public String buscaEmProfundidade(String[][] matrizAdj) {
        String ordem = "";

        for (int u = 0; u < vertices.size(); u++) {
            vertices.get(u).setCor("B");
        }

        for (int u = 1; u < vertices.size(); u++) {
            if (vertices.get(u).getCor().equals("B")) {
                ordem = "visitou " + vertices.get(u).getDado() + "\n";
                ordem = dfsVisit(u, matrizAdj, ordem);
            }
        }
        return ordem;
    }

    public String dfsVisit(int u, String[][] matrizAdj, String ordem) {
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
}

/**/
