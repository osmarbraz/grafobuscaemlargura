/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplina: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 *
 * Baseado nos slides 51 da aula do dia 06/10/2017 
 *
 * Página 433 Thomas H. Cormen 3a Ed 
 *
 * Grafo Busca em Largura/Amplitude ou Breadth-first Search(BFS).
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
import java.util.LinkedList;
import java.util.Queue;

public class Principal {

    //Busca em largura atribui cores a cada vértice. 
    final static int BRANCO = 0;//Vértice não visitado. Inicialmente todos os vértices são brancos
    final static int CINZA = 1; //Vértice visitado pela primeira vez
    final static int PRETO = 2; //Vértice teve seus visinhos visitados

    //Vetor da situação vértice, armazena uma das cores
    static int cor[];
    //Armazena a distâncai de s a v
    static int d[];
    //Vetor dos pais de um vértice
    static int pi[];

    /**
     * Troca um número que representa a posição pela vértice do grafo.
     *
     * @param i Posição da letra
     * @return Uma String com a letra da posição
     */
    public static String trocar(int i) {               
        String letras = "rstuvwxy";        
        if ((i >=0) && (i<=letras.length())) {
            return letras.charAt(i) + "";
        } else {
            return "-";
        }
    }

    /**
     * Troca a letra pela posição na matriz de adjacência.
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
     * Mostra o caminho de s até v no grafo G.
     *
     * @param G Matriz de adjacência do grafo
     * @param s Origem no grafo
     * @param v Destino no grafo
     */
    public static void mostrarCaminho(int[][] G, int s, int v) {
        if (v == s) {
            System.out.println("Cheguei em:" + trocar(s));
        } else {
            if (pi[v] == -1) {
                System.out.println("Não existe caminho de " + trocar(s) + " a " + trocar(v));
            } else {
                mostrarCaminho(G, s, pi[v]);
                System.out.println("Visitando:" + trocar(v));
            }
        }
    }

    /**
     * Busca em Largura/Amplitude (Breadth-first Search).
     * 
     * Constrói uma Árvore de Busca em Largura com raiz s. 
     * Cada caminho de s a um vértice v nesta árvore
     * corresponde a um caminho mais curto de s a v.
     * 
     * Complexidade de tempo é O(V+E)
     *
     * @param G Grafo na forma de uma matriz de adjacência
     * @param s (Source) Fonte ou raiz da árvore de busca em largura
     */
    public static void buscaEmLargura(int[][] G, int s) {
        //Quantidade vértices do grafo
        int n = G.length;

        //Inicializa os vetores
        cor = new int[n];
        d = new int[n];
        pi = new int[n];

        //Inicialização dos vetores
        //Consome Theta(V)
        for (int i = 0; i < n; i++) {
            //Vértice i não foi visitado
            cor[i] = BRANCO;
            d[i] = -1;
            pi[i] = -1;
        }
        //Inicializa a raiz do grafo
        //O vértice s foi visitado pela primeira vez
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
                //Somente com os adjancentes ao vértice u
                if (G[u][v] != 0) {
                    //Verifica se v não foi visitado
                    if (cor[v] == BRANCO) {
                        System.out.println("Adjacente de " + trocar(u) + " = " + trocar(v));
                        //V foi visitado pela primeira vez
                        cor[v] = CINZA;
                        d[v] = d[u] + 1;
                        pi[v] = u;
                        q.add(v);
                        System.out.println("Emfileirando:" + trocar(v));
                    }
                }
            }
            //O vértice u já teve seus vizinhos visitados
            cor[u] = PRETO;
        }
    }

    public static void main(String args[]) {

        //Matriz de adjacência para um grafo não direcionado     
        //Grafo do slide 40
        int G[][] =
               //r  s  t  u  v  w  x  y    
               {{0, 1, 0, 0, 1, 0, 0, 0}, //r
                {1, 0, 0, 0, 0, 1, 0, 0}, //s
                {0, 0, 0, 1, 0, 1, 1, 0}, //t
                {0, 0, 1, 0, 0, 0, 1, 1}, //u
                {1, 0, 0, 0, 0, 0, 0, 0}, //v
                {0, 1, 1, 0, 0, 0, 1, 0}, //w
                {0, 0, 1, 1, 0, 1, 0, 1}, //x
                {0, 0, 0, 1, 0, 0, 1, 0}};//y

        System.out.println(">>> Grafo Busca em Largura/Amplitude ou Breadth-first Search(BFS) <<<");

        //int inicio = 1
        int inicio = destrocar('s');
        buscaEmLargura(G, inicio);
       
        //Mostra o caminho de s até y
        // inicio s=1
        inicio = destrocar('s');
        // destino y = y
        int destino = destrocar('y');
        System.out.println();
        System.out.println("Mostrando o caminho de " + trocar(inicio) + " até " + trocar(destino));
        mostrarCaminho(G, inicio, destino);

        System.out.println();
        System.out.println("Distância armarzenada d[x]");
        for (int i = 0; i < G.length; i++) {
            System.out.println(trocar(pi[i]) + "->" + trocar(i) + "=" + d[i]);
        }
    }
}