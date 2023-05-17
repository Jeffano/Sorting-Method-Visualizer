package assignment1.assignment1;


import javafx.application.Platform;

public class InsertionSort implements SortingStrategy{
    private int[] intArray;
    private SortingHubController controller;

    public InsertionSort(int[] array, SortingHubController sHC){
        intArray = array;
        controller = sHC;
    }

    public void sort(int[] inputArray) {

            int n = inputArray.length;

            //Looping through array starting at index 1
            for (int i = 1; i < n; ++i) {
                Integer key = inputArray[i];
                int j = i - 1;

                //Move elements of arr[0..i-1], that are greater than key, to one position ahead
                //of their current position
                while (j >= 0 && inputArray[j] > key) {
                    inputArray[j + 1] = inputArray[j];
                    j = j - 1;
                }
                inputArray[j + 1] = key;

                Platform.runLater(()->controller.updateGraph(intArray));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
    }

    @Override
    public void run() {
        sort(intArray);
    }
}


