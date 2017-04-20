/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitioning;

import java.util.ArrayList;
import java.util.List;

import partitioning.Point;

/**
 *
 * @author Afa
 */
public class KMeans {

    private int K = 3;
    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 160;

    private List<Point> points;
    private List<Cluster> clusters;

    public KMeans() {
        this.points = new ArrayList();
        this.clusters = new ArrayList();
    }

//    public static void main(String[] args) {
//
//        KMeans kmeans = new KMeans();
//        kmeans.init();
//        kmeans.calculate();
//    }
//    public void init() {
//        //Create Points
//        points = Point.createRandomPoints(MIN_COORDINATE, MAX_COORDINATE, NUM_POINTS);
//
//        //Create Clusters
//        //Set Random Centroids
//        for (int i = 0; i < K; i++) {
//            Cluster cluster = new Cluster(i);
//            Point centroid = Point.createRandomPoint(MIN_COORDINATE, MAX_COORDINATE);
//            cluster.setCentroid(centroid);
//            clusters.add(cluster);
//        }
//
//        //Print Initial state
//        plotClusters();
//    }
    public void setK(int K){
        this.K=K;
    }
    public void init(double[][] data) {
        points = new ArrayList(data.length);
        for (int i = 0; i < data.length; i++) {
            points.add(new Point(data[i][0], data[i][1]));
        }

        //membuat K centroid acak
        for (int i = 0; i < K; i++) {
            Cluster cluster = new Cluster(i);
            Point centroid = Point.createRandomPoint(MIN_COORDINATE, MAX_COORDINATE);
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

        //menampilkan status awal cluster
        System.out.println("Centroid mula-mula");
        plotClusters();
    }

    private void plotClusters() {
        for (int i = 0; i < K; i++) {
            Cluster c = clusters.get(i);
            c.plotCluster();
        }
    }

    //memproses K means
    public void calculate() {
        boolean finish = false;
        int iteration = 0;

        while (!finish) {
            System.out.println("========================================");
            System.out.println("Iterasi " + (iteration+1));
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
                double d= Point.distance(lastCentroids.get(i), currentCentroids.get(i));
                distance += d;
                System.out.println("Jarak Centroid "+i+" Lama dengan Centroid "+i+" Baru= " + d);
            }
            System.out.println("");
            System.out.println("Jumlah Jarak Centroid Lama dengan Centroid Baru= " + distance);
            plotClusters();

            if (distance == 0) {
                finish = true;
            }
        }
    }

    private void clearClusters() {
        for (Cluster cluster : clusters) {
            cluster.clear();
        }
    }

    private List getCentroids() {
        List centroids = new ArrayList(K);
        for (Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getX(), aux.getY());
            centroids.add(point);
        }
        return centroids;
    }

    private void assignCluster() {
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

    private void calculateCentroids() {
        for (Cluster cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            List<Point> list = cluster.getPoints();
            int n = list.size();
            for (Point point : list) {
                sumX += point.getX();
                sumY += point.getY();
            }
            Point centroid = cluster.getCentroid();
            if (n > 0) {
                double newX = sumX / n;
                double newY = sumY / n;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }
}
