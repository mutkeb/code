public class Code {

    public static void print(int num){
        for (int i = 31; i >= 0 ; i--) {
            System.out.print((num & (1 << i)) == 0? "0":"1");
        }
        System.out.println();
    }

    //  选择排序算法
    public static void SelectSort(int[] arr){
        if (arr == null || arr.length == 1){
            return;
        }

        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minIndex = i;
            for(int j = i + 1; j < N; j++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr,i,minIndex);
        }
    }


    //  冒泡排序算法
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length == 1){
            return;
        }

        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++){
                if (arr[j] > arr[j-1]){
                    swap(arr,j,j-1);
                }
            }
        }
    }

    //  插入排序算法
    public static void insertSort(int[] arr){
        if (arr == null || arr.length == 1){
            return;
        }

        int N = arr.length;
        for (int end = 1; end < N; end++){
            int newNumIndex = end;
            while (newNumIndex - 1 >=0 && arr[newNumIndex-1] > arr[newNumIndex]){
                swap(arr,newNumIndex-1,newNumIndex);
                newNumIndex--;
            }

            //  改进版
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--){
                swap(arr,pre,pre + 1);
            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int num = 3;
        print(num);

        int[] arr = {1,4,2,3,6};
        printArray(arr);
        printArray(arr);
    }
}
