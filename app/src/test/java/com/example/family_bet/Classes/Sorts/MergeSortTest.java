package com.example.family_bet.Classes.Sorts;

import junit.framework.TestCase;

import org.junit.Assert;

public class MergeSortTest extends TestCase {
    private MergeSort mergeSort;
     public MergeSortTest(){
         mergeSort=new MergeSort();
     }
    public void testSort() {
        int arr[] = { 12, 11, 13, 5, 6, 7,25,78,33,32,31,45,46,45 };

        System.out.println("Given Array");


        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array");
        MergeSort.printArray(arr);
    }
}