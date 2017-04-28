/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hierarchical;

import data.clustering.Cluster;
import data.clustering.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Afa
 */
public class SingleLinkage extends Linkage {

    public SingleLinkage(double[][] data, int K) {
        super(data, K);
        
        //jarak antar data
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < data.length; j++) {
                double[] ds = data[j];
                
            }
        }
        
        //calculate();
    }

    //memproses 
    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        while (!finish) {
            System.out.println("========================================");
            System.out.println("Iterasi " + (iteration + 1));
            clearClusters();
            List<Point> lastCentroids = getCentroids();
            //mengelompokkan kedalam cluster
            System.out.println("Menghitung jarak data dengan Centroid");
            assignCluster();
            //Menghitung Centroid baru.
            calculateCentroids();
            iteration++;
            List<Point> currentCentroids = getCentroids();
            System.out.println("");
            //Menghitung Jarak Centroid Lama dengan Centroid Baru
            double distance = 0;
            for (int i = 0; i < lastCentroids.size(); i++) {
                double d = Point.distance(lastCentroids.get(i), currentCentroids.get(i));
                distance += d;
                System.out.println("Jarak Centroid " + i + " Lama dengan Centroid " + i + " Baru= " + d);
            }
            System.out.println("");
            System.out.println("Jumlah Jarak Centroid Lama dengan Centroid Baru= " + distance);
            plotClusters();

            if (distance == 0) {
                finish = true;
            }
        }
    }

    @Override
    protected void assignCluster() {
//        super.assignCluster(); //To change body of generated methods, choose Tools | Templates.
        double max = Double.MAX_VALUE;
        double min = max;
        int cluster = 0;
        double distance = 0.0;
        for (Point point : points) {
            min = max;
            for (int i = 0; i < K; i++) {
                Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                System.out.println("Jarak " + point.toString() + " dengan Centroid " + i + " = " + distance);
                if (distance < min) {
                    min = distance;
                    cluster = i;
                }
            }
            point.setCluster(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }

}
