package com.testdome;
import java.util.*;

public class QuadraticEquation {
    public static Roots findRoots(double a, double b, double c) {
        final double a1 = StrictMath.pow(b, 2) - 4 * a * c;
        double firstRoot = (-b + StrictMath.sqrt(a1))/(2*a);
        double secondRoot = (-b - StrictMath.sqrt(a1))/(2*a);
        return new Roots(firstRoot, secondRoot);
    }

    public static void main(String[] args) {
        Roots roots = QuadraticEquation.findRoots(2, 10, 8);
        System.out.println("Roots: " + roots.x1 + ", " + roots.x2);
    }
}

class Roots {
    public final double x1, x2;

    public Roots(double x1, double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }
}