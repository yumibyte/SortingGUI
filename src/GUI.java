import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class GUI implements ActionListener {


    public void colorRow(int rowNumber) {
        // MARK : highlight row functionality
        numTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row == rowNumber ? Color.LIGHT_GRAY : Color.WHITE);
                return c;
            }
        });
    }

    int[] selectionSort(int[] inputArray) throws InterruptedException {
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < inputArray.length - 1; i ++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < inputArray.length; j++) {
                if (inputArray[j] < inputArray[min_idx]) {
                    min_idx = j;

                }
                numberComparisons++;
            }
            // Swap the found minimum element with the first
            // element
            int temp = inputArray[min_idx];
            inputArray[min_idx] = inputArray[i];
            inputArray[i] = temp;
            numberSwaps++;
        }
        gui.colorRow(2);


        return inputArray;
    }

    int partition(int inputArray[], int low, int high) {
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
                numberSwaps++;
            }
            numberComparisons ++;
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = inputArray[i + 1];
        inputArray[i + 1] = inputArray[high];
        inputArray[high] = temp;
        numberSwaps++;
        return i + 1;
    }
    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    int[] quickSort(int[] inputArray, int low, int high) {

        if (low < high) {
            numberComparisons ++;
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(inputArray, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(inputArray, low, pi-1);
            quickSort(inputArray, pi+1, high);
            numberComparisons ++;

        } else {
            numberComparisons ++;
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
            while (j >= 0 && inputArray[j] > key) {
                numberComparisons++;
                inputArray[j + 1] = inputArray[j];
                j = j - 1;
                numberSwaps++;
            }
            numberComparisons++;
            inputArray[j + 1] = key;
        }
        return inputArray;
    }

    int[] bubbleSort(int inputArray[]) {
        int arrayLength = inputArray.length;
        for (int i = 0; i < arrayLength - 1; i++) {
            for (int j = 0; j < arrayLength - i - 1; j++) {
                if (inputArray[j] > inputArray[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = inputArray[j];
                    inputArray[j] = inputArray[j + 1];
                    inputArray[j + 1] = temp;
                    numberSwaps ++;
                }
                numberComparisons++;
            }
        }
        return inputArray;
    }

    public static JPanel panel;
    public static JTextArea inputArrayField = new JTextArea();
    public static JComboBox algorithmComboBox;
    public static String[] algorithmsList = {"Selection Sort", "Insertion Sort", "QuickSort", "Bubble Sort"};
    public static JTextArea outputResultsLabel;
    public static JButton sortButton;
    public static JTable numTable;
    public static JScrollPane tableScrollPane;
    public static String[] columns;
    public static Object[][] data;
    public static GUI gui = new GUI();
    public static int numberSwaps;
    public static int numberComparisons;
    public static int[] outputArrayInt;
    public static int[] inputArrayInt;
    public static CellArray ca;
    public static Graphics gg;
    public static Buffer buffer;

    public void update(Graphics var1) {
        this.paint(var1);
    }

    public void paint(Graphics var1) {
        if (this.gg != null && var1 != null) {
            this.gg.setColor(this.bg);
            this.gg.fillRect(0, 0, this.getSize().width, this.getSize().height);
            this.ca.paint(this.gg);
            var1.drawImage(this.buffer, 0, 0, this);
        }
    }
    public static void main(String[] args) {


        JFrame frame = new JFrame("GUI Sorter!!!");
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        panel.setLayout(null);
        frame.add(panel);

        // realtime listener for inputting nums
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                String inputText = inputArrayField.getText();
                inputArrayInt = Arrays.stream(inputText.split("\\r?\\n")).mapToInt(Integer::parseInt).toArray();
                columns = new String[] {
                        "# Item", "#"
                };

                data = new Object[inputArrayInt.length][2];
                for (int i = 0; i < inputArrayInt.length; i++ ) {
                    data[i][0] = i;
                    data[i][1] = inputArrayInt[i];
                }

//                numTable.setModel(tm);

                numTable = new JTable(data, columns);

                tableScrollPane = new JScrollPane(numTable);
                tableScrollPane.setBounds(10, 50, 110, 450);
                panel.add(tableScrollPane);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // if user deletes num entered
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // fix array

            }
        };

        inputArrayField = new JTextArea("");
        inputArrayField.setBounds(150, 50, 300, 180);
        inputArrayField.getDocument().addDocumentListener(documentListener);
        panel.add(inputArrayField);

        JLabel algorithmsLabel = new JLabel("Choose an algorithm");
        algorithmsLabel.setBounds(150, 230, 300, 50);
        panel.add(algorithmsLabel);

        algorithmComboBox = new JComboBox(algorithmsList);
        algorithmComboBox.setBounds(140, 260, 300, 50);
        algorithmComboBox.addActionListener(new GUI());
        panel.add(algorithmComboBox);

        JLabel comparisonsLabel = new JLabel("Output information");
        comparisonsLabel.setBounds(150, 280, 300, 100);
        panel.add(comparisonsLabel);

        outputResultsLabel = new JTextArea("This sort used _" + "\n sdfsdf");
        outputResultsLabel.setBounds(150, 350, 300, 90);
        panel.add(outputResultsLabel);

        sortButton = new JButton("Sort");
        sortButton.addActionListener((ActionListener) new GUI());
        sortButton.setBounds(220, 460, 150, 35);

        panel.add(sortButton);
        // create table

        tableScrollPane = new JScrollPane(numTable);
        tableScrollPane.setBounds(10, 50, 110, 450);
        panel.add(tableScrollPane);

        frame.setVisible(true);

        ca = new CellArray(16, 23, 70, 15, 50);
        gui.buffer = gui.createImage(gui.getSize().width, gui.getSize().height);
        this.gg = this.buffer.getGraphics();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortButton) {

            switch (algorithmComboBox.getSelectedIndex()) {
                case 0:
                    numberSwaps = 0;
                    numberComparisons = 0;

                    try {
                        outputArrayInt = gui.selectionSort(inputArrayInt);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 1:
                    numberSwaps = 0;
                    numberComparisons = 0;
                    outputArrayInt = gui.insertionSort(inputArrayInt);
                    break;
                case 2:
                    numberSwaps = 0;
                    numberComparisons = 0;
                    outputArrayInt = gui.quickSort(inputArrayInt, 0, inputArrayInt.length - 1);
                    break;
                case 3:
                    numberSwaps = 0;
                    numberComparisons = 0;
                    outputArrayInt = gui.bubbleSort(inputArrayInt);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + algorithmComboBox.getSelectedIndex());
            }
            String outputArrayString = "";

            for (int i = 0; i < outputArrayInt.length; i ++) {
                outputArrayString = outputArrayString + outputArrayInt[i] + ", ";
            }
            outputResultsLabel.setText("Sorted Array: " + outputArrayString + "\nNumber Comparisons: " + numberComparisons + "\nNumber Swaps: " + numberSwaps);
        }
    }
}

//class ModifyTable implements TableModelListener {
//
//    public ModifyTable(JTable numTable) {
//        numTable.getModel().addTableModelListener(this);
//    }
//
//    @Override
//    public void tableChanged(TableModelEvent e) {
//
//    }
//}
