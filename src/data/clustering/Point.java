/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.clustering;

/**
 *
 * @author Akhmad Fariiqun
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Point {
    private int dimension;
    private double[] position;
    private int cluster = 0;

    public Point(double[] p) {
        position = p.clone();
        dimension=position.length;
    }

    public int getDimension() {
        return dimension;
    }
    

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }
    

    public void setCluster(int n) {
        this.cluster = n;
    }

    public int getCluster() {
        return this.cluster;
    }

    //Menghitung jarak antara 2 titik
    public static double distance(Point p, Point centroid) {
        double sum = 0.0;
        for (int i = 0; i < p.getPosition().length; i++) {
            sum = sum + Math.pow((p.getPosition()[i] - centroid.getPosition()[i]), 2.0);
        }
        return Math.sqrt(sum);
    }

    //Membuat titik acak
    public static Point createRandomPoint(int lenght, double min, double max) {
        Random r = new Random();
        double[] p= new double[lenght];
        for (int i = 0; i < p.length; i++) {
            p[i]=min + (max - min) * r.nextDouble();
        }
        return new Point(p);
    }

    protected static List createRandomPoints(int lenght, double min, double max, int K) {
        List points = new ArrayList(K);
        for (int i = 0; i < K; i++) {
            points.add(createRandomPoint(lenght, min, max));
        }
        return points;
    }

    public String toString() {
        String p="(";
        for (int i = 0; i < position.length; i++) {
             p+=position[i];
        }
        return p+=")";
    }
}
