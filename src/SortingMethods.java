import java.awt.*;
import java.util.Arrays;

class SortingMethods {

    public static Graphics graphics = new Graphics();
    int[] selectionSort(int[] inputArray) {
        // set textarea functionality
        graphics.clearTextArea();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < inputArray.length - 1; i ++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < inputArray.length; j++) {
                if (inputArray[j] < inputArray[min_idx]) {
                    min_idx = j;
                    graphics.setTextArea(inputArray, i, min_idx);

                }

                GUI.numberComparisons++;
            }
            // Swap the found minimum element with the first
            // element

            int temp = inputArray[min_idx];
            inputArray[min_idx] = inputArray[i];
            inputArray[i] = temp;
            graphics.setTextArea(inputArray, -1, -1);

            GUI.numberSwaps++;
        }

        return inputArray;
    }

    int partition(int inputArray[], int low, int high) {
        graphics.setTextArea(inputArray, -1, -1);

        int pivot = inputArray[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j ++) {
            // If current element is smaller than the pivot
            if (inputArray[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = inputArray[i];
                inputArray[i] = inputArray[j];
                inputArray[j] = temp;
                GUI.numberSwaps++;

            } else {
                GUI.numberComparisons ++;

            }
        }
// swap arr[i+1] and arr[high] (or pivot)
        graphics.setTextArea(inputArray, -1, -1);

        int temp = inputArray[i + 1];
        graphics.setTextArea(inputArray, i + 1, high);
        inputArray[i + 1] = inputArray[high];
        inputArray[high] = temp;
        GUI.numberSwaps++;
        graphics.setTextArea(inputArray, -1, -1);

        return i + 1;
    }
    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    int[] quickSort(int[] inputArray, int low, int high) {

        if (low < high) {
            GUI.numberComparisons ++;
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(inputArray, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(inputArray, low, pi-1);
            quickSort(inputArray, pi+1, high);
            GUI.numberComparisons ++;

        } else {
            GUI.numberComparisons ++;
        }


        return inputArray;
    }

    int[] insertionSort(int[] inputArray) {
        for (int i = 1; i < inputArray.length; ++i) {

            int key = inputArray[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            graphics.setTextArea(inputArray, -1, -1);

            while (j >= 0 && inputArray[j] > key) {
                graphics.setTextArea(inputArray, j + 1, j + 1);         // unsure how to access the value swapped with... so set to same value for now

                inputArray[j + 1] = inputArray[j];
                j = j - 1;
                GUI.numberComparisons++;
                GUI.numberSwaps++;
            }

            GUI.numberComparisons++;

            inputArray[j + 1] = key;

        }
        graphics.setTextArea(inputArray, -1, -1);         // unsure how to access the value swapped with... so set to same value for now

        return inputArray;
    }

    int[] bubbleSort(int inputArray[]) {
        int arrayLength = inputArray.length;
        for (int i = 0; i < arrayLength - 1; i++) {
            for (int j = 0; j < arrayLength - i - 1; j++) {
                if (inputArray[j] > inputArray[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    graphics.setTextArea(inputArray, j, j + 1);
                    int temp = inputArray[j];
                    inputArray[j] = inputArray[j + 1];
                    inputArray[j + 1] = temp;
                    GUI.numberSwaps ++;

                } else {
                    graphics.setTextArea(inputArray, -1, -1);
                }
                GUI.numberComparisons++;
            }
        }

        return inputArray;
    }
}
