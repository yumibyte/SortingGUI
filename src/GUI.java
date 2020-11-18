import javax.swing.*;
import java.awt.*;
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
    public static int numberSwaps;
    public static int numberComparisons;
    public static int[] outputArrayInt;
    public static int[] inputArrayInt;
    public static DocumentListener documentListener;
    public static JLabel imagingLabel;
    public static SortingMethods sortingMethods = new SortingMethods();


    public static void main(String[] args) {

        JFrame frame = new JFrame("GUI Sorter!!!");
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 700);
        panel.setLayout(null);
        frame.add(panel);


        // realtime listener for inputting nums
        inputArrayField = new JTextArea("");
        inputArrayField.setBounds(230, 50, 300, 180);
        inputArrayField.getDocument().addDocumentListener(documentListener);
        panel.add(inputArrayField);

        JLabel algorithmsLabel = new JLabel("Choose an algorithm");
        algorithmsLabel.setBounds(230, 230, 300, 50);
        panel.add(algorithmsLabel);

        algorithmComboBox = new JComboBox(algorithmsList);
        algorithmComboBox.setBounds(230, 260, 300, 50);
        algorithmComboBox.addActionListener(new GUI());
        panel.add(algorithmComboBox);

        JLabel comparisonsLabel = new JLabel("Output information");
        comparisonsLabel.setBounds(230, 280, 300, 100);
        panel.add(comparisonsLabel);

        outputResultsLabel = new JTextArea("");
        outputResultsLabel.setBounds(230, 350, 300, 90);
        panel.add(outputResultsLabel);

        sortButton = new JButton("Sort");
        sortButton.addActionListener((ActionListener) new GUI());
        sortButton.setBounds(320, 460, 150, 35);

        imagingLabel = new JLabel("<html>Visualization of sorting<br/>will be displayed here...<br/><b>Bold<b/> = # to be swapped");
        imagingLabel.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        imagingLabel.setBounds(10, 0, 500, 450);
        panel.add(imagingLabel);

        panel.add(sortButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortButton) {

            String inputText = GUI.inputArrayField.getText();
            GUI.inputArrayInt = Arrays.stream(inputText.split("\\r?\\n")).mapToInt(Integer::parseInt).toArray();

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
