import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Code {

    public static void mergeSort(int[] arr,int L,int R){
        if (L == R){
            return;
        }
        int M = L + ((R - L)  >> 1);
        mergeSort(arr,L,M);
        mergeSort(arr,M+1,R);
        merge(arr,L,M,R);
    }

    public static void merge(int[] arr,int L,int M,int R){
        int p = L;
        int q = M + 1;
        int[] help = new int[R-L+1];
        int i = 0;
        while (p <= M && q <= R){
            help[i++] = arr[p] <= arr[q] ? arr[p++] : arr[q++];
        }
        while (p <= M){
            help[i++] = arr[p++];
        }
        while (q <= R){
            help[i++] = arr[q++];
        }
        for (int j = 0; j < help.length;j++){
            arr[L+j] = help[j];
        }
    }
    //  归并排序非递归方法
    public static void mergeSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N){
            int L = 0;
            while (L < N){
                int M = L + mergeSize - 1;
                if (M >= N){
                    break;
                }
                int R = Math.min(N-1,M+mergeSize);
                merge(arr,L,M,R);
                L = R + 1;
            }
            if (mergeSize > N / 2){
                break;
            }
            mergeSize <<= 1;
        }
    }

    //  对数器
    public static void test(int[] arr){
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N-i-1;j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    public static int[] generateArray(int maxValue,int maxSize){
        int size = (int)(Math.random() * maxSize + 1);
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * maxValue);
        }
        return arr;
    }
    public static int[] copyArray(int[] array){
        int[] copy = new int[array.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }
    public static void print(int []arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]  + ",");
        }
    }


    public static void main(String[] args) {

        int testTimes = 20000;
        int maxValue = 100;
        int maxSize = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray(maxValue,maxSize);
            int[] copy = copyArray(arr);
//            mergeSort(arr,0,arr.length-1);
            mergeSort2(arr);
            test(copy);
            for (int j = 0; j < arr.length;j++){
                if (arr[j] != copy[j]){
                    System.out.println("测试失败");
                    print(arr);
                    return;
                }
            }
        }
        System.out.println("测试完成");
    }
}
