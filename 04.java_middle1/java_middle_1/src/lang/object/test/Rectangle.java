package lang.object.test;

public class Rectangle {
    int width, height;
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public String toString() {
        return String.format("Rectangle{width=%d, height=%d}", width, height);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rectangle) {
            Rectangle r = (Rectangle) obj;
            return width == r.width && height == r.height;
        }
        return false;
    }
}
