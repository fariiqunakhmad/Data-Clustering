/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitioning;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akhmad Fariiqun
 */

public class Cluster {
	
	public List<Point> points;
	public Point centroid;
	public int id;
	
	//Membuat sebuah cluster baru
	public Cluster(int id) {
		this.id = id;
		this.points = new ArrayList();
		this.centroid = null;
	}

	public List getPoints() {
		return points;
	}
	
	public void addPoint(Point point) {
		points.add(point);
	}

	public void setPoints(List points) {
		this.points = points;
	}

	public Point getCentroid() {
		return centroid;
	}

	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}

	public int getId() {
		return id;
	}
	
	public void clear() {
		points.clear();
	}
	
	public void plotCluster() {
                System.out.println("");
		System.out.println("Cluster " + id+"");
		System.out.println("Centroid: " + centroid + "");
//		System.out.println("[Points: \n");
		for(Point p : points) {
			System.out.println(p);
		}
//		System.out.println("]");
	}

}

