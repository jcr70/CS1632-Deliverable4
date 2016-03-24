import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;

public class ArraySorting {
    Random rand;
    int len;



    @Test
    public void TestArrays(){
        rand = new Random();
        len = 100;

        for(int i = 0; i < len; i++){

            int[] array = arrayGenerator();
            Arrays.sort(array);
            int[] sortedArr = array.clone();

            //Assert that the two sorted arrays both have the same length
            assertEquals(array.length, sortedArr.length);

            //Check if all the elements are either in increasing order or the same as the element next to it
            assertTrue(isIncreasing(array));

            //Check if both arrays are equal
            assertTrue(Arrays.equals(array, sortedArr));

            //Idempotent - Running sort again should not change the output
            Arrays.sort(sortedArr);
            assertTrue(Arrays.equals(array, sortedArr));

            //A reversed array should still contain all the same elements in reverse order
            int[] reversed = reverseArray(array);
            assertTrue(checkIfEqual(reversed, array));

        }
    }

    /*
    Reverses an array in place.
     */
    public int[] reverseArray(int[] a){
        int[] b = a.clone();
        for(int start = 0, end = b.length-1; start < end; start++, end--){
            int tmp = b[start];
            b[start] = b[end];
            b[end] = tmp;
        }

        return b;
    }

    /*
    Checks if two arrays contain the same exact values. Differs from Arrays.equals since they may not necessarily be in the same
    indices. Uses a hashmap to check if they're equal
     */
    public boolean checkIfEqual(int[] a, int[] b){
        HashMap<Integer, Integer> aMap = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> bMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < a.length; i++){
            if(aMap.containsKey(a[i])){
                int val = aMap.get(a[i]);
                aMap.put(a[i], ++val);
            } else {
                aMap.put(a[i], 1);
            }

            if(bMap.containsKey(b[i])){
                int val = bMap.get(b[i]);
                bMap.put(b[i], ++val);
            } else {
                bMap.put(b[i], 1);
            }
        }

        return aMap.equals(bMap);
    }

    /**
     * Checks if every element is either in increasing order or the same as the element next to it
     * @param a
     * @return boolean
     */
    public boolean isIncreasing(int[] a){
        for(int i=1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }

        return true;
    }


    /**
     * Generates an array of random length with random values
     * @return int[] array
     */
    public int[] arrayGenerator(){
        int[] a = new int[rand.nextInt(1000)];  //Random length < 1000
        for(int i = 0; i < a.length; i++){ a[i] = rand.nextInt(); }
        return a;
    }

}

