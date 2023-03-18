import java.util.Arrays;

public class Code {

    public static void swap(int[] arr,int position1,int position2 ){
        int temp = arr[position1];
        arr[position1] = arr[position2];
        arr[position2] = temp;
    }

    public static void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }

    //  冒泡排序
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        int N = arr.length;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N -1 - i ; j++){
                if (arr[j] > arr[j + 1]){
                    swap(arr,j,j+ 1);
                }
            }
        }

//        int N = arr.length;
//        for (int i = 0; i < N; i++) {
//            for (int j = i+1; j < N; j++){
//                if (arr[j] > arr[j-1]){
//                    swap(arr,j,j-1);
//                }
//            }
//        }
    }


    //  选择排序
    public static void selectSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minIndex = i;
            for (int j = i + 1; j < N;j++){
                minIndex  = arr[minIndex] > arr[j] ? j : minIndex;
            }
            swap(arr,i,minIndex);
        }
    }

    //  插入排序
    public static void insertSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        int N = arr.length;

        for (int i = 0; i < N; i++) {
            for(int j = i - 1; j >= 0 && arr[j] > arr[j+1];j--){
                swap(arr,j + 1,j);
            }
        }

    }

    public static int[] generateRandomArray(int maxSize,int maxValue){
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[]  arr){
        int[] arr1 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = arr[i];
        }
        return arr1;
    }

    public static boolean isEqual(int[] arr,int[] arr1){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr1[i]){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[] arr = {1,3,5,2,1,3,7,8,3};
        bubbleSort(arr);
        print(arr);

        //  编写对数器
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2= copyArray(arr1);
            bubbleSort(arr1);
            //  系统实现的排序来排序arr2
            Arrays.sort(arr2);

            //  比较两个数组，若有不一致，就break
            if (!isEqual(arr1,arr2)){
                succeed = false;
                print(arr1);
                print(arr2);
                break;
            }
        }
        System.out.println(succeed ? "success" : "fail");


//        bubbleSort(arr);
//        selectSort(arr);
//        insertSort(arr);
//        print(arr);

    }
}
