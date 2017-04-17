/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hierarchical;

import java.util.HashMap;
import javafx.util.Pair;

/**
 *
 * @author Afa
 */
public class AllDataDistance {
    double[][] allData;
    HashMap<Pair<Integer, Integer>, Double> allDistance;

    public AllDataDistance(double[][] allData) {
        this.allData = allData;
        allDistance= new HashMap<>();
        for (int i = 0; i < allData.length-1; i++) {
            allDistance.put(new P, null);
            for (int j = i+1; j < allData.length; j++) {
                allDistance.put(i, allDistance.get(j).p)
            }
            
        }
    }
    
}
