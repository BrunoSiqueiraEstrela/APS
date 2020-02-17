package sorts;

import java.util.ArrayList;

public class QuickSort {

    public long quickSort(ArrayList<String> arr) {

        int low = 0;                   // valor inicial da checagem do array
        int high = arr.size() - 1;     // último valor do arraylist

        long tempoInicial = System.currentTimeMillis();
        quicksortSorting(arr, low, high);
        long tempoFinal = System.currentTimeMillis();

        return (tempoFinal - tempoInicial);
    }

    public static ArrayList<String> quicksortSorting(ArrayList<String> arr, int low, int high) {

        int middle = low + (high - low) / 2;   // pegando o meio do array
        String pivot = arr.get(middle);        // gerando o pivot do array

        int i = low, j = high;                 // separando o array em dois
        while (i <= j) {
            while (arr.get(i).compareTo(pivot) < 0) {
                i++;
            }

            while (arr.get(j).compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {                      // caso o valor do segundo array é maior ou igual o primeiro,
                String temp = arr.get(i);      // troca o lugar dos dois
                arr.set(i, arr.get(j));
                arr.set(j, temp);
                i++;
                j--;
            }
        }
        if (low < j) {
            quicksortSorting(arr, low, j);            // repete o processo
        }
        if (high > i) {
            quicksortSorting(arr, i, high);           // repete o processo
        }
        return arr;
    }
}
