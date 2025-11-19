
/**
 * Trabalho de Teoria dos Grafos - Análise de Grafos usando Matriz de Adjacência
 * Alunos João Paulo e Rembrandt
 * Data: 19/11/2025
 * Professora: Patrícia Kayser Vargas Mangan
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Classe que representa um grafo através de matriz de adjacência
 * e fornece métodos para análise de suas propriedades
 */
public class Grafo {
    private int[][] matrizAdjacencia;
    private int numeroVertices;
    private boolean ehDirigido;
    private Map<String, Object> propriedadesCache;

    private int[] cores;

    /**
     * Construtor do grafo
     * 
     * @param matriz Matriz de adjacência
     */
    public Grafo(int[][] matriz) {
        if (matriz == null || matriz.length == 0) {
            throw new IllegalArgumentException("Matriz não pode ser nula ou vazia");
        }

        // Verificar se a matriz é quadrada
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i].length != matriz.length) {
                throw new IllegalArgumentException("Matriz deve ser quadrada");
            }
        }

        this.numeroVertices = matriz.length;
        this.matrizAdjacencia = new int[numeroVertices][numeroVertices];
        this.propriedadesCache = new HashMap<>();

        // Copiar matriz
        for (int i = 0; i < numeroVertices; i++) {
            System.arraycopy(matriz[i], 0, this.matrizAdjacencia[i], 0, numeroVertices);
        }

        // Determinar se é dirigido
        this.ehDirigido = verificarSeDirigido();
        this.cores = new int[numeroVertices];
    }

    /**
     * Verifica se o grafo é dirigido
     * 
     * @return true se dirigido, false caso contrário
     */
    private boolean verificarSeDirigido() {
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < numeroVertices; j++) {
                if (matrizAdjacencia[i][j] != matrizAdjacencia[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determina o tipo do grafo baseado na matriz de adjacência
     * 
     * @return String contendo a classificação do grafo
     */
    public String tipoDoGrafo() {
        if (propriedadesCache.containsKey("tipo")) {
            return (String) propriedadesCache.get("tipo");
        }

        StringBuilder resultado = new StringBuilder();
        PropriedadesGrafo props = analisarPropriedades();

        // Tipo básico
        resultado.append(ehDirigido ? "Grafo Dirigido" : "Grafo Não Dirigido");

        // Laços
        if (props.temLacos) {
            resultado.append(" com Laços");
        }

        // Simplicidade
        if (props.ehSimples && !props.temLacos) {
            resultado.append(" - Grafo Simples");
        } else {
            resultado.append(" - Multigrafo");
        }

        // Regularidade
        if (props.ehRegular) {
            resultado.append(" - Regular (grau ").append(props.grauRegular).append(")");
        }

        // Completude
        if (props.ehCompleto) {
            resultado.append(" - Completo");
        }

        // Nulidade
        if (props.ehNulo) {
            resultado.append(" - Grafo Nulo");
        }

        String tipo = resultado.toString();
        propriedadesCache.put("tipo", tipo);
        return tipo;
    }

    /**
     * Classe interna para armazenar propriedades do grafo
     */
    private static class PropriedadesGrafo {
        boolean ehSimples = true;
        boolean temLacos = false;
        boolean ehRegular = true;
        boolean ehCompleto = true;
        boolean ehNulo = true;
        int grauRegular = 0;
        int totalArestas = 0;
    }

    /**
     * Analisa as propriedades estruturais do grafo
     * 
     * @return Objeto contendo as propriedades analisadas
     */
    private PropriedadesGrafo analisarPropriedades() {
        PropriedadesGrafo props = new PropriedadesGrafo();
        List<Integer> graus = calcularGrausVertices();

        // Verificar propriedades básicas
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < numeroVertices; j++) {
                if (matrizAdjacencia[i][j] > 0) {
                    props.ehNulo = false;

                    if (i == j) {
                        props.temLacos = true;
                    }

                    if (matrizAdjacencia[i][j] > 1) {
                        props.ehSimples = false;
                    }
                }
            }
        }

        // Verificar regularidade
        if (!graus.isEmpty()) {
            props.grauRegular = graus.get(0);
            for (int grau : graus) {
                if (grau != props.grauRegular) {
                    props.ehRegular = false;
                    break;
                }
            }
        }

        // Verificar completude
        int arestasEsperadas = ehDirigido ? numeroVertices * (numeroVertices - 1)
                : numeroVertices * (numeroVertices - 1) / 2;
        props.totalArestas = contarArestas();

        props.ehCompleto = (props.totalArestas == arestasEsperadas) &&
                props.ehSimples && !props.temLacos;

        return props;
    }

    /**
     * Calcula os graus de todos os vértices
     * 
     * @return Lista com os graus dos vértices
     */
    private List<Integer> calcularGrausVertices() {
        List<Integer> graus = new ArrayList<>();

        for (int i = 0; i < numeroVertices; i++) {
            int grau = 0;
            for (int j = 0; j < numeroVertices; j++) {
                if (ehDirigido) {
                    grau += matrizAdjacencia[i][j]; // grau de saída
                } else {
                    grau += matrizAdjacencia[i][j];
                    if (i == j)
                        grau += matrizAdjacencia[i][j]; // laços contam dobrado
                }
            }
            graus.add(grau);
        }

        return graus;
    }

    /**
     * Conta o número total de arestas do grafo
     * 
     * @return Número de arestas
     */
    private int contarArestas() {
        int total = 0;
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < numeroVertices; j++) {
                if (matrizAdjacencia[i][j] > 0) {
                    if (i == j) {
                        // Laço
                        total += matrizAdjacencia[i][j];
                    } else if (ehDirigido) {
                        // Grafo dirigido - todas as arestas
                        total += matrizAdjacencia[i][j];
                    } else if (i < j) {
                        // Grafo não dirigido - contar apenas uma vez
                        total += matrizAdjacencia[i][j];
                    }
                }
            }
        }
        return total;
    }
    // --- NOVOS MÉTODOS PARA AS SPRINTS ---

    /**
     * Sprint 1:
     * Implemente a exportação do grafo para o formato DOT (usado pelo Graphviz -
     * https://graphviz.org/).
     * Gere e salve um arquivo .dot com a estrutura do grafo.
     * 
     * Sprint 3:
     * Exporte novamente o grafo para o formato DOT, agora com os vértices
     * coloridos.
     * Inclua os atributos de cor (fillcolor) no arquivo gerado.
     * Exiba o conteúdo gerado ao usuário.
     */
    public void exportarParaDOT(String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo + ".dot"))) {
            if (ehDirigido) {
                writer.println("digraph G {");
            } else {
                writer.println("graph G {");
            }

            writer.println("    node [style=filled, fontname=\"Arial\"];");

            String[] paleta = { "white", "lightblue", "lightgreen", "lightyellow", "lightpink", "cyan", "orange",
                    "violet", "red", "gray" };

            for (int i = 0; i < numeroVertices; i++) {
                String corStr = "";
                if (this.cores[i] > 0) {
                    String corNome = (this.cores[i] < paleta.length) ? paleta[this.cores[i]] : "white";
                    corStr = " [fillcolor=" + corNome + "]";
                }
                writer.println("    " + i + corStr + ";");
            }

            String tipoAresta = ehDirigido ? " -> " : " -- ";

            for (int i = 0; i < numeroVertices; i++) {
                int inicioJ = ehDirigido ? 0 : i;

                for (int j = inicioJ; j < numeroVertices; j++) {
                    if (matrizAdjacencia[i][j] > 0) {
                        for (int k = 0; k < matrizAdjacencia[i][j]; k++) {
                            writer.println("    " + i + tipoAresta + j + ";");
                        }
                    }
                }
            }

            writer.println("}");
            System.out.println("Arquivo gerado: " + nomeArquivo + ".dot");

        } catch (IOException e) {
            System.err.println("Erro ao escrever arquivo: " + e.getMessage());
        }
    }

    /**
     * Sprint 2: Coloração Sequencial
     * Implemente o algoritmo de coloração sequencial.
     * Atribua cores inteiras (1, 2, 3, ...) aos vértices do grafo.
     */
    public void coloracaoSequencial() {
        Arrays.fill(this.cores, 0);

        for (int v = 0; v < numeroVertices; v++) {
            menorCor(v);
        }
        System.out.println("Coloração Sequencial aplicada.");
    }

    /**
     * Sprint 4: Coloração Heurística
     * Implemente o algoritmo de coloração heurística. Exporte e mostre ao usuário a
     * nova versão do grafo com as cores resultantes.
     */
    public void coloracaoHeuristica() {
        Arrays.fill(this.cores, 0);

        List<Integer> listaGraus = calcularGrausVertices();

        Integer[] verticesOrdenados = new Integer[numeroVertices];
        for (int i = 0; i < numeroVertices; i++)
            verticesOrdenados[i] = i;

        Arrays.sort(verticesOrdenados, (v1, v2) -> {
            return Integer.compare(listaGraus.get(v2), listaGraus.get(v1));
        });

        for (int v : verticesOrdenados) {
            menorCor(v);
        }
        System.out.println("Coloração Heurística aplicada.");
    }

    private void menorCor(int vertice) {
        Set<Integer> coresVizinhas = new HashSet<>();

        List<Integer> vizinhos = obterVizinhos(vertice);

        for (int vizinho : vizinhos) {
            if (this.cores[vizinho] != 0) {
                coresVizinhas.add(this.cores[vizinho]);
            }
        }

        if (ehDirigido) {

            for (int i = 0; i < numeroVertices; i++) {
                if (matrizAdjacencia[i][vertice] > 0 && this.cores[i] != 0) {
                    coresVizinhas.add(this.cores[i]);
                }
            }
        }

        int cor = 1;
        while (coresVizinhas.contains(cor)) {
            cor++;
        }
        this.cores[vertice] = cor;
    }

    /**
     * Sprint 5: Surpreenda-me...
     * Implemente alguma funcionalidade nova a sua escolha :-)
     * Gera um relatório da coloração (Número Cromático e validação).
     */
    public void verificaColaracao() {
        if (this.cores[0] == 0) {
            System.out.println("O grafo ainda não foi colorido.");
            return;
        }

        int maxCor = 0;
        boolean coloracaoValida = true;

        for (int i = 0; i < numeroVertices; i++) {
            if (this.cores[i] > maxCor)
                maxCor = this.cores[i];

            // Validação: vizinhos não podem ter a mesma cor
            for (int vizinho : obterVizinhos(i)) {
                if (this.cores[i] == this.cores[vizinho]) {
                    coloracaoValida = false;
                    System.out
                            .println("ERRO: Vértices " + i + " e " + vizinho + " têm a mesma cor (" + cores[i] + ")!");
                }
            }
        }

        System.out.println("\n=== [Sprint 5] Relatório de Coloração do Grafo ===");
        System.out.println(
                "Coloração " + (coloracaoValida ? "VÁLIDA (Nenhum vizinho com mesma cor)" : "INVÁLIDA"));
        System.out.println("Número Cromático (k): " + maxCor);
        System.out
                .println("Isso significa que o grafo pode ser dividido em " + maxCor + " conjuntos.");
        System.out.println("=====================================================\n");
    }

    /**
     * Conta as arestas do grafo e lista o conjunto de arestas
     * 
     * @return String com a quantidade e o conjunto de arestas
     */
    public String arestasDoGrafo() {
        if (propriedadesCache.containsKey("arestas")) {
            return (String) propriedadesCache.get("arestas");
        }

        StringBuilder resultado = new StringBuilder();
        Set<String> conjuntoArestas = new LinkedHashSet<>(); // LinkedHashSet mantém ordem
        int totalArestas = 0;

        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < numeroVertices; j++) {
                if (matrizAdjacencia[i][j] > 0) {
                    if (i == j) {
                        // Laço
                        for (int k = 0; k < matrizAdjacencia[i][j]; k++) {
                            conjuntoArestas.add("(" + i + "," + j + ")");
                            totalArestas++;
                        }
                    } else if (ehDirigido) {
                        // Grafo dirigido
                        for (int k = 0; k < matrizAdjacencia[i][j]; k++) {
                            conjuntoArestas.add("(" + i + "," + j + ")");
                            totalArestas++;
                        }
                    } else if (i < j) {
                        // Grafo não dirigido
                        for (int k = 0; k < matrizAdjacencia[i][j]; k++) {
                            conjuntoArestas.add("(" + i + "," + j + ")");
                            totalArestas++;
                        }
                    }
                }
            }
        }

        resultado.append("Quantidade de arestas: ").append(totalArestas).append("\n");
        resultado.append("Conjunto de arestas: {");

        String[] arestas = conjuntoArestas.toArray(new String[0]);
        for (int i = 0; i < arestas.length; i++) {
            resultado.append(arestas[i]);
            if (i < arestas.length - 1) {
                resultado.append(", ");
            }
        }
        resultado.append("}");

        String resultadoFinal = resultado.toString();
        propriedadesCache.put("arestas", resultadoFinal);
        return resultadoFinal;
    }

    /**
     * Calcula o grau do grafo e de cada vértice
     * 
     * @return String identificando o grau do grafo, de cada vértice e a sequência
     *         de graus
     */
    public String grausDoVertice() {
        if (propriedadesCache.containsKey("graus")) {
            return (String) propriedadesCache.get("graus");
        }

        StringBuilder resultado = new StringBuilder();
        List<Integer> graus = new ArrayList<>();
        int grauMaximo = 0;
        int grauMinimo = Integer.MAX_VALUE;

        if (ehDirigido) {
            resultado.append("Grafo Dirigido - Graus de Entrada e Saída:\n");

            for (int i = 0; i < numeroVertices; i++) {
                GrauVertice grauVertice = calcularGrauVertice(i);

                graus.add(grauVertice.total);
                grauMaximo = Math.max(grauMaximo, grauVertice.total);
                grauMinimo = Math.min(grauMinimo, grauVertice.total);

                resultado.append("Vértice ").append(i)
                        .append(" - Grau de entrada: ").append(grauVertice.entrada)
                        .append(", Grau de saída: ").append(grauVertice.saida)
                        .append(", Grau total: ").append(grauVertice.total).append("\n");
            }
        } else {
            resultado.append("Grafo Não Dirigido - Graus:\n");

            for (int i = 0; i < numeroVertices; i++) {
                int grau = 0;
                for (int j = 0; j < numeroVertices; j++) {
                    grau += matrizAdjacencia[i][j];
                    if (i == j)
                        grau += matrizAdjacencia[i][j]; // laços contam dobrado
                }

                graus.add(grau);
                grauMaximo = Math.max(grauMaximo, grau);
                grauMinimo = Math.min(grauMinimo, grau);

                resultado.append("Vértice ").append(i).append(" - Grau: ").append(grau).append("\n");
            }
        }

        if (grauMinimo == Integer.MAX_VALUE)
            grauMinimo = 0;

        resultado.append("\nGrau do grafo: ").append(grauMaximo).append(" (grau máximo)\n");
        resultado.append("Grau mínimo: ").append(grauMinimo).append("\n");
        resultado.append("Sequência de graus: ").append(graus);

        String resultadoFinal = resultado.toString();
        propriedadesCache.put("graus", resultadoFinal);
        return resultadoFinal;
    }

    /**
     * Classe interna para representar graus de entrada e saída de um vértice
     */
    private static class GrauVertice {
        int entrada;
        int saida;
        int total;

        GrauVertice(int entrada, int saida) {
            this.entrada = entrada;
            this.saida = saida;
            this.total = entrada + saida;
        }
    }

    /**
     * Calcula o grau de um vértice específico
     * 
     * @param vertice Índice do vértice
     * @return Objeto GrauVertice com informações de grau
     */
    private GrauVertice calcularGrauVertice(int vertice) {
        int grauEntrada = 0;
        int grauSaida = 0;

        for (int j = 0; j < numeroVertices; j++) {
            grauSaida += matrizAdjacencia[vertice][j];
            grauEntrada += matrizAdjacencia[j][vertice];
        }

        return new GrauVertice(grauEntrada, grauSaida);
    }

    /**
     * Realiza busca em profundidade no grafo
     * 
     * @return String identificando a ordem em que os vértices são explorados
     */
    public String buscaEmProfundidade() {
        if (propriedadesCache.containsKey("dfs")) {
            return (String) propriedadesCache.get("dfs");
        }

        boolean[] visitado = new boolean[numeroVertices];
        List<Integer> ordemVisitaCompleta = new ArrayList<>();
        StringBuilder resultado = new StringBuilder();

        resultado.append("Busca em Profundidade (DFS):\n");

        int numeroComponentes = 0;

        for (int i = 0; i < numeroVertices; i++) {
            if (!visitado[i]) {
                numeroComponentes++;
                List<Integer> componenteAtual = new ArrayList<>();

                if (numeroComponentes > 1) {
                    resultado.append("\nComponente ").append(numeroComponentes).append(": ");
                } else {
                    resultado.append("Componente ").append(numeroComponentes).append(": ");
                }

                dfsRecursivo(i, visitado, componenteAtual);

                // Formatar saída do componente
                for (int j = 0; j < componenteAtual.size(); j++) {
                    resultado.append(componenteAtual.get(j));
                    if (j < componenteAtual.size() - 1) {
                        resultado.append(" -> ");
                    }
                }

                ordemVisitaCompleta.addAll(componenteAtual);
            }
        }

        resultado.append("\n\nOrdem completa de visitação: ").append(ordemVisitaCompleta);

        // Análise de conectividade
        if (numeroComponentes == 1) {
            resultado.append("\nO grafo é conexo (1 componente).");
        } else {
            resultado.append("\nO grafo possui ").append(numeroComponentes).append(" componentes conexos.");
        }

        String resultadoFinal = resultado.toString();
        propriedadesCache.put("dfs", resultadoFinal);
        return resultadoFinal;
    }

    /**
     * Método auxiliar para DFS recursivo
     * 
     * @param vertice  Vértice atual
     * @param visitado Array de controle de visitação
     * @param ordem    Lista para armazenar ordem de visitação
     */
    private void dfsRecursivo(int vertice, boolean[] visitado, List<Integer> ordem) {
        visitado[vertice] = true;
        ordem.add(vertice);

        // Obter vizinhos e ordenar para garantir ordem consistente
        List<Integer> vizinhos = obterVizinhos(vertice);
        Collections.sort(vizinhos);

        for (int vizinho : vizinhos) {
            if (!visitado[vizinho]) {
                dfsRecursivo(vizinho, visitado, ordem);
            }
        }
    }

    /**
     * Obtém a lista de vizinhos de um vértice
     * 
     * @param vertice Índice do vértice
     * @return Lista de vizinhos
     */
    private List<Integer> obterVizinhos(int vertice) {
        List<Integer> vizinhos = new ArrayList<>();
        for (int i = 0; i < numeroVertices; i++) {
            if (matrizAdjacencia[vertice][i] > 0) {
                vizinhos.add(i);
            }
        }
        return vizinhos;
    }

    /**
     * Obtém a matriz de adjacência (cópia defensiva)
     * 
     * @return Cópia da matriz de adjacência
     */
    public int[][] getMatrizAdjacencia() {
        int[][] copia = new int[numeroVertices][numeroVertices];
        for (int i = 0; i < numeroVertices; i++) {
            System.arraycopy(matrizAdjacencia[i], 0, copia[i], 0, numeroVertices);
        }
        return copia;
    }

    /**
     * Obtém o número de vértices
     * 
     * @return Número de vértices
     */
    public int getNumeroVertices() {
        return numeroVertices;
    }

    /**
     * Verifica se o grafo é dirigido
     * 
     * @return true se dirigido, false caso contrário
     */
    public boolean isDirigido() {
        return ehDirigido;
    }

    /**
     * Imprime a matriz de adjacência de forma organizada
     * 
     * @return String representando a matriz
     */
    public String imprimirMatriz() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numeroVertices; i++) {
            for (int j = 0; j < numeroVertices; j++) {
                sb.append(matrizAdjacencia[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Limpa o cache de propriedades calculadas
     */
    public void limparCache() {
        propriedadesCache.clear();
    }
}
