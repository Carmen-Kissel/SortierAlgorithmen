public class Square {
    protected int left;
    private final int up;
    protected int height;
    private final int width;
    private Zeichenfenster zeichenfenster;

    public Square(int leftNew, int heightNew, int widthNew, Zeichenfenster zeichenfenster) {
        left = leftNew;
        height = heightNew;
        width = widthNew;
        up = 500-height;
        this.zeichenfenster = zeichenfenster;
    }

    public void drawSquare() {
        zeichenfenster.zeichneRechteck(left,up,width,height);
    }
}
