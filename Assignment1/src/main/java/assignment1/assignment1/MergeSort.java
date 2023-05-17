package assignment1.assignment1;

import javafx.application.Platform;

public class MergeSort implements SortingStrategy{

    private int[] intArray;
    private static SortingHubController controller;

    public MergeSort(int[] array, SortingHubController sHC){
        intArray = array;
        controller = sHC;
    }

    @Override
    public void run() {

        sort(intArray);
    }

    public void sort(int[] arr)
    {
            mergeSort(arr,0, intArray.length-1);

    }
    static void mergeSort(int arr[], int l, int r)
    {
        if (l < r) {

            // Same as (l + r) / 2, but avoids overflow
            // for large l and r
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);

        }

    }

    static void merge(int[] arr, int s, int m, int end) {

        int s2 = m + 1;

        // If the direct merge is already sorted
        if (arr[m] <= arr[s2]) {
            return;
        }

        // Two pointers to maintain s
        // of both arrays to merge
        while (s <= m && s2 <= end) {

            // If element 1 is in right place
            if (arr[s] <= arr[s2]) {
                s++;
            }
            else {
                int value = arr[s2];
                int index = s2;

                // Shift all the elements between element 1
                // element 2, right by 1.
                while (index != s) {
                    arr[index] = arr[index - 1];
                    index--;
                }
                arr[s] = value;

                // Update all the pointers
                s++;
                m++;
                s2++;
            }
            Platform.runLater(()->controller.updateGraph(arr));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
