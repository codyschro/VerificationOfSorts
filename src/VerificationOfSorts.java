import java.util.*;

public class VerificationOfSorts {

    //Define constants
    static long MAXVALUE = 10000;
    static long MINVALUE = -10000;
    static int LISTSIZESMALL = 20;
    static int LISTSIZEBIG = 10000;

    public static void main(String[] args) {

        checkSortCorrectness();
    }

    public static boolean verifySorted(long arr[]){

        //set N as length of list
        int N = arr.length;

        //run thru entire list to verify it is sorted, if not return false
        for(int i = 0; i < N - 1; i++){
            if(arr[i] > arr[i + 1])
                return false;
        }
        return true;
    }

    //using https://www.javatpoint.com/insertion-sort-in-java as a guide
    public static void insertionSort(long arr[]){

        //define variable, length of array
        int N = arr.length;

        //start loop at index 1, index(i) + 1, j < N, j++
        for(int j = 1; j < N; j++){
            //set key and original index variable, i = 0
            long key = arr[j];
            int i = j - 1;
            //test whether index is less than zero and arr[i] is greater than the key
            while( i > -1 && arr[i] > key){
                //swap values
                arr[i + 1] = arr[i];
                //decrease i
                i--;
            }
            //set next index to key
            arr[i + 1] = key;
        }
    }

    // return index of searched number if found, or -1 if not found
    public static void bubbleSort(long[] list){
        // make N passes through the list (N is the length of the list)
        for(int i = 0; i < list.length-1; i++) {
            // for index from 0 to N-1, compare item[index] to next it, swap if needed
            for(int j = 0; j < list.length-1-i; j++){
                if(list[j] > list[j+1]){
                    long tmp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = tmp;
                }
            }
        }
    }

    public static long[] mergeSort(long[] list){

        //check list isn't empty
        if(list.length <= 1)
            return list;

        //create 2 lists to hold each half of original list
        long[] first = new long[list.length/2];
        long[] second = new long[list.length - first.length];

        //split array in half and populate lists
        System.arraycopy(list, 0, first, 0, first.length);
        System.arraycopy(list, first.length, second, 0, second.length);

        //recurse
        mergeSort(first);
        mergeSort(second);

        //merge halves together
        merge(first, second, list);

        return list;
    }

    public static void merge(long[] first, long[] second, long[] result){

        //set variables
        int iFirst = 0;
        int iSecond = 0;
        int iMerged = 0;

        //Compare elements, move smaller to iMerged
        while(iFirst <  first.length && iSecond < second.length){
            if(first[iFirst] < second[iSecond]){
                result[iMerged] = first[iFirst];
                iFirst++;
            }
            else{
                result[iMerged] = second[iSecond];
                iSecond++;
            }
            iMerged++;
        }
        //copy remaining elements from both halves
        System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
    }

    //wrapper, using as guide: http://www.java2novice.com/java-sorting-algorithms/quick-sort/
    public static void quickSort(long list[]){

        //check list, return if null or zero
        if (list == null || list.length == 0){
            return;
        }
        //call worker
        quickSortWorker(list, 0, list.length - 1);
    }

    public static void quickSortWorker(long list[], int lo, int hi){

        //set variables
        int i = lo;
        int j = hi;
        //choose pivot in middle
        int pivot = (int) list[lo + (hi - lo)/2];

        //divide into two arrays
        while (i <= j){
            while(list[i] < pivot){
                i++;
            }
            while(list[j] > pivot){
                j--;
            }
            if(i <= j){
                exchangeNumbers(list, i, j);
                //move index to next on both sides
                i++;
                j--;
            }
        }
        //call quickSort recursively
        if (lo < j){
            quickSortWorker(list, lo, j);
        }
        if(i < hi){
            quickSortWorker(list, i, hi);
        }

    }

    public static void naiveQuickSort(long list[]){

        //check list, return if null or zero
        if (list == null || list.length == 0){
            return;
        }
        //call worker
        naiveQuickSortWorker(list, 0, list.length - 1);
    }

    public static void naiveQuickSortWorker(long list[], int lo, int hi){

        //set variables
        int i = lo;
        int j = hi;
        int pivot = (int) list[lo];

        //divide into two arrays
        while (i <= j){
            while(list[i] < pivot){
                i++;
            }
            while(list[j] > pivot){
                j--;
            }
            if(i <= j){
                exchangeNumbers(list, i, j);
                //move index to next on both sides
                i++;
                j--;
            }
        }
        //call naiveQuickSort recursively
        if (lo < j){
            naiveQuickSortWorker(list, lo, j);
        }
        if(i < hi){
            naiveQuickSortWorker(list, i, hi);
        }

    }

    public static void exchangeNumbers(long list[], int i, int j){

        //swap numbers function
        long temp = list[i];
        list[i] = list[j];
        list[j] = temp;

    }

    //create random integer list containing negative and positive numbers
    public static long[] createRandomListOfIntegers(int size){
        long[] newList = new long[size];
        for(int j = 0; j < size; j++) {
            newList[j] = (long) (MINVALUE + Math.random() * (MAXVALUE - MINVALUE));
        }
        return newList;
    }

    public static void checkSortCorrectness(){

        //would have made this better (less repeated code) but didn't have time... it works tho
        //create test lists one for each of 5 sorts, one small and one big
        long[] testList0 = createRandomListOfIntegers(LISTSIZESMALL);
        long[] testList1 = createRandomListOfIntegers(LISTSIZESMALL);
        long[] testList2 = createRandomListOfIntegers(LISTSIZESMALL);
        long[] testList3 = createRandomListOfIntegers(LISTSIZESMALL);
        long[] testList4 = createRandomListOfIntegers(LISTSIZESMALL);
        long[] testList5 = createRandomListOfIntegers(LISTSIZEBIG);
        long[] testList6 = createRandomListOfIntegers(LISTSIZEBIG);
        long[] testList7 = createRandomListOfIntegers(LISTSIZEBIG);
        long[] testList8 = createRandomListOfIntegers(LISTSIZEBIG);
        long[] testList9 = createRandomListOfIntegers(LISTSIZEBIG);

        //run tests printing lists of 20 for each and check its sorted
        System.out.println("Before Bubble Sort:");
        System.out.println(Arrays.toString(testList0));
        bubbleSort(testList0);
        System.out.println("After Bubble Sort");
        System.out.println(Arrays.toString(testList0));
        boolean result = false;
        result = verifySorted(testList0);
        System.out.println("Bubble sort sorted list correctly? " + result);

        System.out.println("Before Insertion Sort:");
        System.out.println(Arrays.toString(testList1));
        insertionSort(testList1);
        System.out.println("After Insertion Sort");
        System.out.println(Arrays.toString(testList1));
        result = false;
        result = verifySorted(testList1);
        System.out.println("Insertion sort sorted list correctly? " + result);

        System.out.println("Before Merge Sort:");
        System.out.println(Arrays.toString(testList2));
        mergeSort(testList2);
        System.out.println("After Merge Sort");
        System.out.println(Arrays.toString(testList2));
        result = false;
        result = verifySorted(testList2);
        System.out.println("Merge sort sorted list correctly? " + result);

        System.out.println("Before Quick Sort:");
        System.out.println(Arrays.toString(testList3));
        quickSort(testList3);
        System.out.println("After Quick Sort");
        System.out.println(Arrays.toString(testList3));
        result = false;
        result = verifySorted(testList3);
        System.out.println("Quick sort sorted list correctly? " + result);

        System.out.println("Before Naive Quick Sort:");
        System.out.println(Arrays.toString(testList4));
        naiveQuickSort(testList4);
        System.out.println("After Naive Quick Sort");
        System.out.println(Arrays.toString(testList4));
        result = false;
        result = verifySorted(testList4);
        System.out.println("Naive quick sort sorted list correctly? " + result +"\n");

        //test lists of 10000 on each sort
        result = false;
        bubbleSort(testList5);
        result = verifySorted(testList5);
        System.out.println("Bubble sort sorted large list correctly? " + result);

        result = false;
        insertionSort(testList6);
        result = verifySorted(testList6);
        System.out.println("Insertion sort sorted large list correctly? " + result);

        result = false;
        mergeSort(testList7);
        result = verifySorted(testList7);
        System.out.println("Merge sort sorted large list correctly? " + result);

        result = false;
        quickSort(testList8);
        result = verifySorted(testList8);
        System.out.println("Quick sort sorted large list correctly? " + result);

        result = false;
        naiveQuickSort(testList9);
        result = verifySorted(testList9);
        System.out.println("Naive quick sort sorted large list correctly? " + result);

    }
}
