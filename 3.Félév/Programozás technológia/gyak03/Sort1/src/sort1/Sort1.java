/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author bli
 */
public class Sort1 {

    public static List<Integer> getSortedByNRemainder(List<Integer> list, final int n) {
        return getSortedByNRemainder(list, n, true);
    }

    public static List<Integer> getSortedByNRemainder(List<Integer> list, final int n, final boolean ascending) {
        ArrayList<Integer> listCopy = new ArrayList<>(list);
        Collections.sort(listCopy, new RemainderComparator(n, ascending));
        return listCopy;
    }
    
    public static <T extends Comparable> List<T> sortByIndex(List<T> list,final int i, final int j){
        Collections.sort(list.subList(0, i),new ReverseCompare<T>());
        Collections.sort(list.subList(i, j+1));
        Collections.sort(list.subList(j+1, list.size()),new ReverseCompare<T>());
        return list;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 10, 21, 12, 34, 5));
        System.out.println(getSortedByNRemainder(list, 3));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(7, 2, 15, 8, 3, 6, 18, 97));
        System.out.println(sortByIndex(list2, 3, 5));
    }
}
