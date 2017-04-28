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
public class Distance {
    Point pointA, pointB;
    double distance;

    public Distance(Point pointA, Point pointB, double distance) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.distance = distance;
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
}
