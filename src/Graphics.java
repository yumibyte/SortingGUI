

public class Graphics {
    public static String data = "";

    public void setTextArea(int[] inputArray, int swapIndex1, int swapIndex2) {

        // NO SWAP
        if (swapIndex1 == -1 && swapIndex2 == -1) {
            for (int i = 0; i < inputArray.length; i++ ) {

                data = data + inputArray[i] + " ";
            }
            data = data + "<br/>";

        } else {
            for (int i = 0; i < inputArray.length; i++ ) {

                // if it's a swapped number make it bold
                if (i == swapIndex1 || i == swapIndex2) {
                    data = data + "<b>" + inputArray[i] + "</b> ";
                } else {
                    data = data + inputArray[i] + " ";

                }
            }
            data = data + "<br/>";
        }

    }

    public void clearTextArea() {
        GUI.imagingLabel.setText("");
    }

}
