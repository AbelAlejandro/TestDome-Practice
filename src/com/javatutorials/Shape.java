package com.javatutorials;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

class Shape {

    private Color color;
    private String name;

    public Shape() {
    }

    public Shape(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "color=" + color.toString() +
                ", name='" + name + '\'' +
                '}';
    }

    enum Color {
        RED,
        GREEN,
        BLUE
    }


    public static Collection<Shape> getShapes() {
        Collection<Shape> myShapesCollection = new ArrayList<>();
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        generateShapes(Shape.Color.GREEN, myShapesCollection, leftLimit, rightLimit, targetStringLength, random);
        generateShapes(Shape.Color.RED, myShapesCollection, leftLimit, rightLimit, targetStringLength, random);
        generateShapes(Shape.Color.BLUE, myShapesCollection, leftLimit, rightLimit, targetStringLength, random);
        return myShapesCollection;
    }

    public static void generateShapes(Shape.Color color, Collection<Shape> myShapesCollection, int leftLimit, int rightLimit, int targetStringLength, Random random) {
        for (int i = 0; i < 1000; i++) {
            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .filter(j -> (j <= 57 || j >= 65) && (j <= 90 || j >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            myShapesCollection.add(new Shape(color, generatedString));
        }
    }

}
