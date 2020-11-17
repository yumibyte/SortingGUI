import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.Buffer;
import java.util.Arrays;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GUI implements ActionListener {

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
    public static int numberSwaps;
    public static int numberComparisons;
    public static int[] outputArrayInt;
    public static int[] inputArrayInt;
//    public static CellArray ca;
//    public static Graphics gg;
//    public static Buffer buffer;
    public static SortingMethods sortingMethods = new SortingMethods();


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

//        ca = new CellArray(16, 23, 70, 15, 50);
//        gui.buffer = gui.createImage(gui.getSize().width, gui.getSize().height);
//        this.gg = this.buffer.getGraphics();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortButton) {

            switch (algorithmComboBox.getSelectedIndex()) {
                case 0:
                    numberSwaps = 0;
                    numberComparisons = 0;

                    try {
                        outputArrayInt = sortingMethods.selectionSort(inputArrayInt);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 1:
                    numberSwaps = 0;
                    numberComparisons = 0;
                    outputArrayInt = sortingMethods.insertionSort(inputArrayInt);
                    break;
                case 2:
                    numberSwaps = 0;
                    numberComparisons = 0;
                    outputArrayInt = sortingMethods.quickSort(inputArrayInt, 0, inputArrayInt.length - 1);
                    break;
                case 3:
                    numberSwaps = 0;
                    numberComparisons = 0;
                    outputArrayInt = sortingMethods.bubbleSort(inputArrayInt);
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
