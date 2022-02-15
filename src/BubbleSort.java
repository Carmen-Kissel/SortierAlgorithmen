import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BubbleSort {

    private Zeichenfenster z;
    private final Square[] bars;
    private final int barsNumber;
    private int sortNumber;

    private final Random randomObject;
    private final JButton randomButton;
    private final JButton sortButton;

    public static void main(String[] args) {
        new BubbleSort();
    }

    //constructor
    public BubbleSort() {
        z = new Zeichenfenster("Bubblesort");
        barsNumber = 100; //Number may be changed: valid if (barsNumber%2==0 && barsNumber != 0 && barsNumber <= 500)
        bars = new Square[barsNumber];
        createBars();
        drawBars();
        randomObject = new Random();
        sortNumber = 1;

        //Buttons
        randomButton = new JButton("Randomize");
        z.komponenteHinzufuegen(randomButton, "unten");
        randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                randomize();
            }
        });

        sortButton = new JButton("Sort");
        z.komponenteHinzufuegen(sortButton, "unten");
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortAllSquares();
            }
        });
    }
    
    private void createBars() {
        int width = 600/ bars.length;
        int height = 500/ bars.length;
        int heightSave = height;
        int left = 0;

        for(int i = 0; i < bars.length; i++) {
            bars[i] = new Square(left, height, width, z);
            left = left + width;
            height = height + heightSave;
        }
    }

    public void drawBars() {
        z.loescheAlles();
        for (Square bar : bars) {
            bar.drawSquare();
        }
    }

    private Square getSquare(int slot){
        return bars[slot];
    }

    //Switches two selected bars.
    private void switchTwoBars(int slot1, int slot2){
        Square[] barsCopy = new Square[2];
        barsCopy[0] = getSquare(slot1);
        barsCopy[1] = getSquare(slot2);
        int temp1 = bars[slot1].left;
        int temp2 = bars[slot2].left;
        bars[slot1] = barsCopy[1];
        bars[slot2] = barsCopy[0];
        bars[slot1].left = temp1;
        bars[slot2].left = temp2;
    }

    //Randomizes all bars.
    public void randomize(){
        int z1;
        int z2;
        for(int i = 0; i < bars.length * 2; i++)
        {
            z1 = randomObject.nextInt(bars.length);
            z2 = randomObject.nextInt(bars.length);
            switchTwoBars(z1, z2);
        }
        sortNumber = 1;
        drawBars();
    }

    private void changeIfNecessary(int firstBar, int secondBar) {
        if(bars[firstBar].height < bars[secondBar].height) {
            switchTwoBars(firstBar, secondBar);
        }
    }

    //Moves the highest unsorted bar to the right
    private void sortOnce() {
        for(int i = 0; i < bars.length - sortNumber; i++) {
            changeIfNecessary(i+1, i);
        }
        System.out.println("Sortierte Balken: " + sortNumber++ + " von " + bars.length);
    }

    //Sorts all bars.
    public void sortAllSquares() {
        for(int i = 0; i < bars.length; i++) {
            sortOnce();
        }
        sortNumber = 1;
        drawBars();
    }
}