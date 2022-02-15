import java.util. Random;
import javax.swing.*;
import java.awt.event.*;

public class QuickSort
{
    private final Zeichenfenster z;
    private final Square[] bars;
    private final int barsNumber;

    private final Random randomObject;
    private final JButton randomButton;
    private final JButton sortButton;

    public static void main(String[] args) {
        new QuickSort();
    }

    public QuickSort(){
        z = new Zeichenfenster("Quicksort");
        barsNumber = 100; //Number may be changed: valid if (barsNumber%2==0 && barsNumber != 0 && barsNumber <= 500)
        bars = new Square[barsNumber];
        createBars();
        drawBars();
        randomObject = new Random();

        //Buttons
        randomButton = new JButton("Randomize");
        z.komponenteHinzufuegen(randomButton, "unten");
        randomButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                randomize();
            }
        });
        sortButton = new JButton("Sort");
        z.komponenteHinzufuegen(sortButton, "unten");
        sortButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                sort();
            }
        });
    }

    private void createBars() {
        int width = 600/ barsNumber;
        int height = 500/ barsNumber;
        int heightSave = height;
        int left = 0;

        for(int i=0; i <= bars.length-1; i++) {
            bars[i] = new Square(left, height, width, z);
            left = left + width;
            height = height + heightSave;
        }
    }

    private Square getSquare(int slot) {
        return bars[slot];
    }

    public void drawBars() {
        z.loescheAlles();
        for(int i=0; i<=barsNumber-1; i++)
        {
            bars[i].drawSquare();
        }
    }

    //Switches two selected bars.
    public void switchTwoBars(int slot1, int slot2) {
        Square a = bars[slot2];
        bars[slot2] = bars[slot1];
        bars[slot1] = a;
        int temp1 = bars[slot1].left;
        bars[slot1].left = bars[slot2].left;
        bars[slot2].left = temp1;
    }

    //Randomizes all bars.
    public void randomize(){
        int z1;
        int z2;
        for(int i = 0; i < barsNumber *2; i++)
        {
            z1 = randomObject.nextInt(barsNumber);
            z2 = randomObject.nextInt(barsNumber);
            switchTwoBars(z1, z2);
        }
        drawBars();
    }

    //
    public int partition(int start, int end) {
        int lastItem = end - 1; //lastItem = last bar.

        for(int i = end-1; i >= start; i--) {
            if(bars[i].height > bars[lastItem].height) { //lastItem is compared with all bars.
                if(i != lastItem - 1) { //in order to prevent the skipping of smaller bars in between i and the lastItem
                    switchTwoBars(i, lastItem - 1); //i has to be next to lastItem
                }
                switchTwoBars(lastItem - 1, lastItem); //lastItem is switched with the higher bar one spot next to itself
                lastItem--; //the new position of the pivot
            }
        }
        return lastItem; //Result: all bars which are higher than the pivot are on its right, the smaller ones are on the left -> repeat until sorted
    }

    public void sortRecursive(int start, int end) {
        if(end - start <= 0) { //termination condition
            return;
        }
        int pivot = partition(start, end);
        if(pivot + 1 < end) {
            sortRecursive(pivot + 1,end);
        }
        if(pivot >= start) {
            sortRecursive(start, pivot);
        }
    }

    public void sort(){
        sortRecursive(0, barsNumber);
        drawBars();
    }
}
