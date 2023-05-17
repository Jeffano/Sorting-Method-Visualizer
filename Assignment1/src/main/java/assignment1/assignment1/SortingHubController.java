package assignment1.assignment1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class SortingHubController {

    int arraySizeValue;
    int[]  intArray = new int [arraySizeValue];

    private Thread thread;

    private SortingStrategy sortingMethod;

    @FXML
    private Label lblArraySize;

    @FXML
    private Pane lblGraph;

    @FXML
    private Slider sliderArraySize;

    @FXML
    private ComboBox<String> sortingMethods;

    @FXML
    public void initialize() {
        //Sets the default slider value and display that value while setting the new array value
        sliderArraySize.setValue(64);
        lblArraySize.setText("64");

        generateArray(64);
        updateGraph(intArray);
        arraySizeValue = 64;

        //Loads all the sorting methods into the combo box
        sortingMethods.getItems().setAll("Merge Sort", "Insertion Sort");
        sortingMethods.setValue("Merge Sort");
    }

    //Generates the array of random values
    @FXML
    void generateArray(int arraySize){
        intArray = new int[arraySize];

        Random rand = new Random();
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;

        while (i < intArray.length) {
            int num = rand.nextInt(arraySize) + 1;
            if (!map.containsKey(num)) {
                intArray[i] = num;
                map.put(num, 1);
                i++;
            }
        }
        updateGraph(intArray);
    }

    //Method to update the graph pane with the rectangles
    @FXML
    void updateGraph(int[] arr) {
        lblGraph.getChildren().clear();

        double paneWidth = lblGraph.getPrefWidth();
        double paneHeight = lblGraph.getPrefHeight();
        double rectangleWidth = (paneWidth/ arr.length) - 2;

        int maxValue = arr[0];
        for (int j = 1; j < arr.length; j++) {
            if (arr[j] > maxValue) {
                maxValue = arr[j];
            }
        }

        for (int i = 0; i < arr.length; i++){
            double rectangleHeight = ((arr[i] * paneHeight) / maxValue);
            double xCord = (i * (rectangleWidth + 2));
            double yCord = paneHeight - rectangleHeight;
            Rectangle rec = new Rectangle (xCord, yCord, rectangleWidth, rectangleHeight);
            rec.setFill(Color.RED);
            lblGraph.getChildren().add(rec);
        }
    }

    @FXML
    void resetArray(ActionEvent event) {
        initialize();
    }

    @FXML
    void setArraySize(MouseEvent event) {
        int newSize = (int) sliderArraySize.getValue();

        if(arraySizeValue != newSize){
            arraySizeValue = (int) sliderArraySize.getValue();
            lblArraySize.setText(String.valueOf(arraySizeValue));
            generateArray(arraySizeValue);
        }
    }

    void setSortStrategy() {
        String selectedSort = sortingMethods.getValue();

        if(selectedSort.equals("Merge Sort")){
            sortingMethod = new MergeSort(intArray,this);
        } else{
            sortingMethod = new InsertionSort(intArray,this);
        }
        thread = new Thread(sortingMethod);
    }

    @FXML
    void sortArray() {
        setSortStrategy();
        thread.start();
    }
}