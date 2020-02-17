package sorts;

import java.util.ArrayList;

public class SelectSort {

    public static ArrayList<String> selectSorting(ArrayList<String> arr) {
        int tamanho = arr.size();
        for (int i = 0; i < tamanho - 1; i++) {
            int min = i;
            for (int j = i + 1; j < tamanho; j++) {
                if (arr.get(j).compareTo(arr.get(min)) > 0) {
                    min = j;
                }
            }
            String temp = arr.get(min);
            arr.set(min, arr.get(i));
            arr.set(i, temp);
        }
        return arr;
    }
    
    public long selectSort(ArrayList<String> arr){
        
        long tempoInicial = System.currentTimeMillis();
        selectSorting(arr);
        long tempoFinal = System.currentTimeMillis();
        return (tempoFinal - tempoInicial);
    }
}
