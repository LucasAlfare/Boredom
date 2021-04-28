package samueltm.boredom.math.geometry;

public class Cylinder extends Shape {
    private final double height;
    private final double radius;

    public Cylinder(double height, double diameter) {
        this.height = height;
        this.radius = diameter / 2;
    }

    @Override
    public double getArea() {
        return (2 * Math.PI * Math.pow(radius, 2)) + (2 * Math.PI * radius * height);
    }
}
