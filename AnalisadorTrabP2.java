/**
 * Trabalho Parcial 3 de Teoria dos Grafos - Coloração e Exibição de Grafos
 * Alunos João Paulo e Rembrandt
 * Data: 19/11/2025
 * Professora: Patrícia Kayser Vargas Mangan
 * Classe principal - demonstração e testes
 */
import java.util.Scanner;

class AnalisadorTrabP2 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.println(
                "Qual grafo você deseja usar?\n1) Grafo Não Dirigido Simples\n2) Grafo Dirigido\n3) Grafo Completo K4\n4) Multigrafo com Laços\n5) Grafo de Ex. da Aula 13");
        String opcao = ler.next();
        int[][] matriz = {
                { 0 }
        };
        Grafo grafo = new Grafo(matriz);

        switch (opcao) {
            case "1":
                System.out.println("=== EXEMPLO 1: Grafo Não Dirigido Simples ===");
                int[][] matriz1 = {
                        { 0, 1, 1, 0, 0 },
                        { 1, 0, 1, 1, 0 },
                        { 1, 1, 0, 1, 1 },
                        { 0, 1, 1, 0, 1 },
                        { 0, 0, 1, 1, 0 }
                };

                Grafo grafo1 = new Grafo(matriz1);
                grafo = grafo1;
                break;
            case "2":
                System.out.println("\n\n=== EXEMPLO 2: Grafo Dirigido ===");
                int[][] matriz2 = {
                        { 0, 1, 0, 1 },
                        { 0, 0, 1, 0 },
                        { 1, 0, 0, 1 },
                        { 0, 0, 0, 0 }
                };

                Grafo grafo2 = new Grafo(matriz2);
                grafo = grafo2;
                break;
            case "3":
                System.out.println("\n\n=== EXEMPLO 3: Grafo Completo K4 ===");
                int[][] matriz3 = {
                        { 0, 1, 1, 1 },
                        { 1, 0, 1, 1 },
                        { 1, 1, 0, 1 },
                        { 1, 1, 1, 0 }
                };

                Grafo grafo3 = new Grafo(matriz3);
                grafo = grafo3;
                break;
            case "4":
                System.out.println("\n\n=== EXEMPLO 4: Multigrafo com Laços ===");
                int[][] matriz4 = {
                        { 1, 2, 0 },
                        { 2, 0, 1 },
                        { 0, 1, 2 }
                };

                Grafo grafo4 = new Grafo(matriz4);
                grafo = grafo4;
                break;
            case "5":
                System.out.println("=== EXEMPLO 5: Grafo de Exemplo da Aula 13 ===");
                int[][] matriz5 = {
                        { 0, 1, 1, 1, 0, 0, 1 },
                        { 1, 0, 1, 1, 1, 0, 1 },
                        { 1, 1, 0, 1, 0, 1, 0 },
                        { 1, 1, 1, 0, 0, 1, 0 },
                        { 0, 1, 0, 0, 0, 0, 0 },
                        { 0, 0, 1, 1, 0, 0, 1 },
                        { 1, 1, 0, 0, 0, 1, 0 }
                };

                Grafo grafo5 = new Grafo(matriz5);
                grafo = grafo5;
                break;
            default:
                break;
        }

        System.out.println("Matriz Original:");
        System.out.print(grafo.imprimirMatriz());

        // --- SPRINT 1: Exportação Simples ---
        System.out.println("\n>> Executando Sprint 1: Exportar grafo original (.dot)...");
        grafo.exportarParaDOT("grafo_original");

        // --- SPRINT 2: Coloração Sequencial ---
        System.out.println("\n>> Executando Sprint 2: Coloração Sequencial...");
        grafo.coloracaoSequencial();

        // --- SPRINT 3: Exportar Colorido ---
        System.out.println("\n>> Executando Sprint 3: Exportar grafo colorido sequencialmente...");
        grafo.exportarParaDOT("grafo_colorido_sequencial");

        // --- SPRINT 5 (PARTE 1): Relatório da Sequencial ---
        grafo.verificaColaracao();

        // --- SPRINT 4: Coloração Heurística ---
        System.out.println("\n>> Executando Sprint 4: Coloração Heurística (Welsh-Powell)...");
        grafo.coloracaoHeuristica();
        grafo.exportarParaDOT("grafo_colorido_heuristica");

        // --- SPRINT 5 (PARTE 2): Relatório da Heurística ---
        grafo.verificaColaracao();

        System.out.println("\nFIM. Verifique os arquivos .dot gerados na pasta do projeto.");
        System.out.println("Você pode visualizar os arquivos em: https://graphvizonline.net/");

        ler.close();
    }
}