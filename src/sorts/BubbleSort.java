package sorts;

import java.util.ArrayList;

public class BubbleSort {

        public long bubbleSort(ArrayList<String> arr){
        
        long tempoInicial = System.currentTimeMillis();
        bubbleSorting(arr);
        long tempoFinal = System.currentTimeMillis();
        return (tempoFinal - tempoInicial);
    }

    public ArrayList<String> bubbleSorting(ArrayList<String> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j).compareTo(arr.get(j + 1)) > 0) {
                    String temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
        return arr;
    }

}
