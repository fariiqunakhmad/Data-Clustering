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
        calculate();
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


}
