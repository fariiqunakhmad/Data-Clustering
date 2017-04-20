/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitioning;

import java.util.ArrayList;
import java.util.List;
import data.clustering.Cluster;
import data.clustering.Point;

/**
 *
 * @author Afa
 */
public class KMeans {

    private int K;
    private double minCoordinate;
    private double maxCoordinate;
    private int pointDimension;
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
//        points = Point.createRandomPoints(minCoordinate, maxCoordinate, NUM_POINTS);
//
//        //Create Clusters
//        //Set Random Centroids
//        for (int i = 0; i < K; i++) {
//            Cluster cluster = new Cluster(i);
//            Point centroid = Point.createRandomPoint(minCoordinate, maxCoordinate);
//            cluster.setCentroid(centroid);
//            clusters.add(cluster);
//        }
//
//        //Print Initial state
//        plotClusters();
//    }
    public void init(double[][] data) {
        maxCoordinate = getMaxValue(data);
        minCoordinate = getMinValue(data);
        pointDimension = data[0].length;
        points = new ArrayList(data.length);
        for (int i = 0; i < data.length; i++) {
            points.add(new Point(data[i]));
        }

        //membuat K centroid acak
        for (int i = 0; i < K; i++) {
            Cluster cluster = new Cluster(i);
            Point centroid = Point.createRandomPoint(pointDimension, minCoordinate, maxCoordinate);
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

        //menampilkan status awal cluster
        System.out.println("Centroid mula-mula");
        plotClusters();
    }

    public void setK(int K) {
        this.K = K;
    }

    public static double getMaxValue(double[][] numbers) {
        double maxValue = numbers[0][0];
        for (int j = 0; j < numbers.length; j++) {
            for (int i = 0; i < numbers[j].length; i++) {
                if (numbers[j][i] > maxValue) {
                    maxValue = numbers[j][i];
                }
            }
        }
        return maxValue;
    }

    public static double getMinValue(double[][] numbers) {
        double minValue = numbers[0][0];
        for (int j = 0; j < numbers.length; j++) {
            for (int i = 0; i < numbers[j].length; i++) {
                if (numbers[j][i] < minValue) {
                    minValue = numbers[j][i];
                }
            }
        }
        return minValue;
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

    private void clearClusters() {
        for (Cluster cluster : clusters) {
            cluster.clear();
        }
    }

    private List getCentroids() {
        List centroids = new ArrayList(K);
        for (Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getPosition());
//            System.out.println("Centroid "+cluster.getId()+": "+point.toString());
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
            List<Point> points = cluster.getPoints();
            int n = points.size();
            double[] sumP = new double[pointDimension];
            for (Point point : points) {
                for (int i = 0; i < sumP.length; i++) {
                    sumP[i] += point.getPosition()[i];
                }
            }
            Point centroid = cluster.getCentroid();
            if (n > 0) {
                double[] newP = new double[pointDimension];
                for (int i = 0; i < sumP.length; i++) {
                    newP[i] = sumP[i] / n;
                }
                centroid.setPosition(newP);
            }
        }
    }

}
