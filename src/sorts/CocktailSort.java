package sorts;

import java.util.ArrayList;

public class CocktailSort {

    public long cocktailSort(ArrayList<String> arr) {

        long tempoInicial = System.currentTimeMillis();
        cocktailsortSorting(arr, arr.size());
        long tempoFinal = System.currentTimeMillis();

        return (tempoFinal - tempoInicial);
    }

    public static ArrayList<String> cocktailsortSorting(ArrayList<String> arr, int tamanho) {
        int inicio, fim, troca;
        String aux;
        inicio = 0;
        fim = tamanho - 1;
        troca = 0;
        while (troca == 0 && inicio < fim) {
            troca = 1;
            for (int i = inicio; i < fim; i = i + 1) {
                if (arr.get(i).compareTo(arr.get(i+1)) > 0){
                    aux = arr.get(i);
                    arr.set(i, arr.get(i+1));
                    arr.set(i+1, aux);
                    troca = 0;

                }
            }
            fim = fim - 1;
            for (int i = fim; i > inicio; i = i - 1) {
                if (arr.get(i).compareTo(arr.get(i-1)) < 0){
                    aux = arr.get(i);
                    arr.set(i, arr.get(i-1));
                    arr.set(i-1, aux);
                    troca = 0;
                }

            }
            inicio = inicio + 1;
        }
        return arr;
    }
}