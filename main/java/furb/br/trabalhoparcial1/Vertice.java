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

public class Vertice<TIPO> {
    private TIPO dado;
    private ArrayList<Aresta<TIPO>> arestasEntrada;
    private ArrayList<Aresta<TIPO>> arestasSaida;
    private String cor;
    private int d;
    private int f;

    public Vertice(TIPO valor) {
        this.dado = valor;
        this.arestasEntrada = new ArrayList<Aresta<TIPO>>();
        this.arestasSaida = new ArrayList<Aresta<TIPO>>();
    }

    public TIPO getDado() {
        return dado;
    }

    public void setDado(TIPO dado) {
        this.dado = dado;
    }

    public void adicionarArestaEntrada(Aresta<TIPO> aresta) {
        this.arestasEntrada.add(aresta);
    }

    public void adicionarArestaSaida(Aresta<TIPO> aresta) {
        this.arestasSaida.add(aresta);
    }

    public ArrayList<Aresta<TIPO>> getArestasEntrada() {
        return arestasEntrada;
    }

    public void setArestasEntrada(ArrayList<Aresta<TIPO>> arestasEntrada) {
        this.arestasEntrada = arestasEntrada;
    }

    public ArrayList<Aresta<TIPO>> getArestasSaida() {
        return arestasSaida;
    }

    public void setArestasSaida(ArrayList<Aresta<TIPO>> arestasSaida) {
        this.arestasSaida = arestasSaida;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
    
    


}
