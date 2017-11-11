/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 * Baseado nos slides 51 da aula do dia 06/10/2017 
 * Realiza a busca em Largura em um grafo
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
import java.util.LinkedList;
import java.util.Queue;

public class Principal {

    final static int BRANCO = 0;//Não visitado
    final static int CINZA = 1; //Visitado pela primeira vez
    final static int PRETO = 2; //Teve seus visinhos visitados

    static int cor[];
    static int d[];
    static int pi[];

    /**
     * Troca um número que representa a posição pela vértice do grafo.
     *
     * @param i Posição da letra
     * @return Uma String com a letra da posição
     */
    public static String trocar(int i) {
        String letras = "rstuvwxy";
        return letras.charAt(i) + "";
    }

    /**
     * Troca a letra pela posição na matriz de adjacência
     *
     * @param v Letra a ser troca pela posição
     * @return Um inteiro com a posição da letra no grafo
     */
    public static int destrocar(char v) {
        String letras = "rstuvwxy";
        int pos = -1;
        for (int i = 0; i < letras.length(); i++) {
            if (letras.charAt(i) == v) {
                pos = i;
            }
        }
        return pos;
    }

    /**
     * Mostra o caminho de s até v no grafo G
     *
     * @param G Matriz de incidência do grafo
     * @param s Origem no grafo
     * @param v Destino no grafo
     */
    public static void printPath(int[][] G, int s, int v) {
        if (v == s) {
            System.out.println("Cheguei em:" + trocar(s));
        } else {
            if (pi[v] == -1) {
                System.out.println("Não existe caminho de " + trocar(s) + " a " + trocar(v));
            } else {
                printPath(G, s, pi[v]);
                System.out.println("Visitando:" + trocar(v));
            }
        }
    }

    /**
     * Busca em Largura em Grafo.
     *
     * @param G Grafo na forma de uma matriz de adjacência
     * @param s Raiz da árvore de busca em largura
     */
    public static void buscaEmLargura(int[][] G, int s) {
        //Quantidade vértices do grafo
        int n = G.length;

        //Inicializa os vetores
        cor = new int[n];
        d = new int[n];
        pi = new int[n];

        //Inicialização dos vetores
        for (int i = 0; i < n; i++) {
            cor[i] = BRANCO;
            d[i] = -1;
            pi[i] = -1;
        }
        //Inicializa a raiz do grafo
        cor[s] = CINZA;
        d[s] = 0;
        pi[s] = -1;

        //Instancia a Fila
        Queue q = new LinkedList();
        q.add(s);
        System.out.println("Emfileirando:" + trocar(s));
        while (q.isEmpty() == false) {
            int u = (int) q.poll();
            System.out.println("Desemfileirando:" + trocar(u));
            for (int v = 0; v < n; v++) {
                if (G[u][v] != 0) {
                    if (cor[v] == BRANCO) {
                        System.out.println(">> Adjacente de " + trocar(u) + " = " + trocar(v));
                        cor[v] = CINZA;
                        d[v] = d[u] + 1;
                        pi[v] = u;
                        q.add(v);
                        System.out.println("Emfileirando:" + trocar(v));
                    }
                }
            }
            cor[u] = PRETO;
        }
    }

    public static void main(String args[]) {

        //Matriz de incidência para um grafo não direcionado     
        //Grafo do slide 40
        int G[][]
               = //r  s  t  u  v  w  x  y    
                {{0, 1, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1, 1, 0},
                {0, 0, 1, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 1, 0}};

        System.out.println("Busca em Largura / Amplitude");

        // inicio s=1
        int inicio = destrocar('s');
        buscaEmLargura(G, inicio);

        //Mostra o caminho
        // destino v = 4
        int destino = destrocar('y');
        System.out.println();
        System.out.println("Mostrando o caminho de " + trocar(inicio) + " até " + trocar(destino));
        printPath(G, inicio, destino);
    }
}
