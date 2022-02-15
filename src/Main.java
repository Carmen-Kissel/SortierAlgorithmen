import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main{
    private static Zeichenfenster z;
    private static JButton bubbleSort;
    private static JButton quicksort;
    private static BubbleSort b;
    private static QuickSort q;

    public static void main(String[] args) {
        z = new Zeichenfenster("SortierAlgorithmen");

        bubbleSort = new JButton("BubbleSort");
        z.komponenteHinzufuegen(bubbleSort, "unten");
        bubbleSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                b = new BubbleSort();
            }
        });

        quicksort = new JButton("QuickSort");
        z.komponenteHinzufuegen(quicksort, "unten");
        quicksort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                q = new QuickSort();
            }
        });
    }
}
