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
 
    private double[] position;
    private int cluster = 0;
 
    public Point(double[] p)
    {
        position=p.clone();
    }
    
    public double [] getPosition(){
        return this.position.clone();
    }
    
    public void setCluster(int n) {
        this.cluster = n;
    }
    
    public int getCluster() {
        return this.cluster;
    }
    
    //Menghitung jarak antara 2 titik
    protected static double distance(Point p, Point centroid) {
        return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2));
    }
    
    //Membuat titik acak
    protected static Point createRandomPoint(int min, int max) {
    	Random r = new Random();
        for (int i = 0; i < Point..length; i++) {
            Object arr = arr[i];
            
        }
    	double x = min + (max - min) * r.nextDouble();
    	double y = min + (max - min) * r.nextDouble();
    	return new Point(x,y);
    }
    
    protected static List createRandomPoints(int min, int max, int number) {
    	List points = new ArrayList(number);
    	for(int i = 0; i < number; i++) {
    		points.add(createRandomPoint(min,max));
    	}
    	return points;
    }
    
    public String toString() {
    	return "("+x+","+y+")";
    }
}
