/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.clustering;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import partitioning.KMeans;

/**
 *
 * @author Afa
 */
public class DataClustering {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new FileReader("src/data.txt"));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        int lineCounter = 0;
        double[][] baris = new double[lineCounter][0];
        Set<Double> kelas = new TreeSet<Double>();

        while (line != null) {
            lineCounter++;
            baris = Arrays.copyOf(baris, lineCounter);
//            String[] v = line.split(",");
            String[] v = line.split("\t");
            double[] data = new double[v.length];
            for (int i = 0; i < v.length; i++) {
                data[i] = Double.parseDouble(v[i]);
            }
            kelas.add(data[v.length - 1]);
            baris[lineCounter - 1] = data;
            line = br.readLine();
        }
        double[][] dataMula = baris;

//        double[][] dataMula = new double[][]{
//            {1, 1, 0},
//            {1, 2, 0},
//            {2, 1, 0},
//            {2, 3, 1},
//            {3, 3, 1}
//        };

//        System.out.println("Labeled Data: ");
//        for (int i = 0; i < dataMula.length; i++) {
//
//            System.out.println("Data " + (i + 1) + ": " + Arrays.toString(dataMula[i]));
//        }
        
        KMeans kmeans = new KMeans();
    	kmeans.init(dataMula);
    	kmeans.calculate();
        
//
//        Scanner scan = new Scanner(System.in);
//        double[] dataTest = new double[dataMula[0].length - 1];
//        System.out.println("");
//        System.out.println("Masukkan Data Baru sebanyak " + (dataMula[0].length - 1) + " element!");
//        for (int i = 0; i < dataMula[0].length - 1; i++) {
//            System.out.print("Elemen " + (i + 1) + ":");
//            dataTest[i] = scan.nextDouble();
//        }
////        double[] dataTest = new double[]{1, 3};
//
//        System.out.println("");
//        System.out.println("Anda telah selesai memasukkan Data Baru.");
//        System.out.println("Data Baru: ");
//        System.out.println(Arrays.toString(dataTest));
//        System.out.println("");
//        System.out.print("Masukkan nilai k:");
//        int k = scan.nextInt();
////        int k = 3;
//        System.out.println("");
//        System.out.println("Anda telah selesai memasukkan nilai k.");
//        System.out.println("Nilai k: " + k);
//        System.out.println("");
    }
    
}
