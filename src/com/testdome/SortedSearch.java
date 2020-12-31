package com.testdome;

public class SortedSearch {
    public static int countNumbersLinear(int[] sortedArray, int lessThan) {
        int count = 0;
        for (int i = 0; i < sortedArray.length; i++) {
            if(sortedArray[i] >= lessThan) break;
            count++;
        }
        return count;
    }

    private static int findMid(int[] sortedArray, int left, int right, int lessThan) {
        int middle = left + (right - left) / 2;
        if (sortedArray[middle] == lessThan) {
            // If duplicates are present it returns the position of the first element
            while (middle - 1 > 0 && sortedArray[middle - 1] == lessThan)
                --middle;
            return middle;
        }
        if (middle <= left) {
            // It happens if lessThan is not present in the array
            while (middle <= right && sortedArray[middle] < lessThan)
                ++middle;
            return middle;
        }
        if (sortedArray[middle] < lessThan) {
            left = middle;  // ignore left half
        } else {
            right = middle;  // ignore right half
        }
        return findMid(sortedArray, left, right, lessThan);
    }

    public static int countNumbers(int[] sortedArray, int lessThan) {
        int left = 0;
        int right = sortedArray.length - 1;
        return findMid(sortedArray, left, right, lessThan);
    }

    public static void main(String[] args) {
        System.out.println("{ 1, 3, 5, 7 } less than 4 is 2 == " + SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
        System.out.println("{ 1, 1, 1, 1 } less than 4 is 4 == " + SortedSearch.countNumbers(new int[] { 1, 1, 1, 1 }, 4));
        System.out.println("{ 5, 7 } less than 4 is 0 == " + SortedSearch.countNumbers(new int[] { 5, 7 }, 4));
        System.out.println("{ 1, 2, 3 } less than 4 is 3 == " + SortedSearch.countNumbers(new int[] { 1, 2, 3 }, 4));
        System.out.println("{ 4, 4, 4, 4 } less than 4 is 0 == " + SortedSearch.countNumbers(new int[] { 4, 4, 4, 4 }, 4));

    }
}