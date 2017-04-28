/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hierarchical;

import data.clustering.Cluster;
import data.clustering.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akhmad Fariiqun
 */
public class Linkage {

    protected int K;
    protected int n;
    protected int pointDimension;
    protected List<Point> points;
    protected List<Cluster> clusters;

    public Linkage(double[][] data, int K) {
        this.K = K;
        n = data.length;
        points = new ArrayList(n);
        clusters = new ArrayList(n);
        pointDimension = data[0].length;
        for (int i = 0; i < n; i++) {
            points.add(new Point(data[i]));
            Cluster cluster = new Cluster(i);
            List<Point> clusterPoints = new ArrayList();
            clusterPoints.add(new Point(data[i]));
            cluster.setPoints(clusterPoints);
            cluster.setCentroid(new Point(data[i]));
            clusters.add(cluster);
        }
        plotClusters();
    }

    protected void plotClusters() {
        for (int i = 0; i < n; i++) {
            Cluster c = clusters.get(i);
            c.plotCluster();
        }
    }

    protected void clearClusters() {
        for (Cluster cluster : clusters) {
            cluster.clear();
        }
    }

    protected void assignCluster() {



    }

    protected void calculateCentroids() {
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

    protected List getCentroids() {
        List centroids = new ArrayList(K);
        for (Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getPosition());
//            System.out.println("Centroid "+cluster.getId()+": "+point.toString());
            centroids.add(point);
        }
        return centroids;
    }
}
