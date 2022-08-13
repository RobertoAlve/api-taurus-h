package com.taurusmagister.taurusmagister.entidade;

import java.util.ArrayList;

public class FilaString <T>{
    // Atributos
    private int tamanho;
    private T[] fila;

    // Construtor
    public FilaString() {
        this.tamanho = 0;
        this.fila = (T[]) new Object[50];
    }


    // Métodos

    /* Método isEmpty() - retorna true se a fila está vazia e false caso contrário */
    public boolean isEmpty() {
        return tamanho == 0;
    }

    /* Método isFull() - retorna true se a fila está cheia e false caso contrário */
    public boolean isFull() {
        if (tamanho == fila.length){
            return true;
        }
        return false;
    }

    /* Método insert - recebe um elemento e insere esse elemento na fila
                       no índice tamanho, e incrementa tamanho
                       Retornar IllegalStateException caso a fila esteja cheia
     */
    public void insert(T info) {
        if (isFull()){
            throw new IllegalStateException();
        }
        fila[tamanho++] = info;
    }

    /* Método peek - retorna o primeiro elemento da fila, sem removê-lo */
    public T peek() {
        return fila[0];
    }

    /* Método poll - remove e retorna o primeiro elemento da fila, se a fila não estiver
       vazia. Quando um elemento é removido, a fila "anda", e tamanho é decrementado
     */
    public T poll() {
        if (!isEmpty()) {
            T primeiroElemento = peek();
            for (int i = 0; i + 1 < tamanho; i++) {
                fila[i] = fila[i + 1];
                fila[i + 1] = null;
            }
            tamanho--;
            return primeiroElemento;
        }
        return null;
    }

    /* Método exibe() - exibe o conteúdo da fila */
    public void exibe() {
        for (int i=0; i < tamanho; i++){
            System.out.println(fila[i]);
        }
    }

    public int getTamanho(){
        return tamanho;
    }
}
