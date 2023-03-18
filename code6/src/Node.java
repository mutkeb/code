
public class Node {

    public static void mergerSort(int[]arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process(arr,0,arr.length-1);
    }

    public static void process(int[] arr,int left,int right){
        if (left == right){
            return;
        }
        int mid = (left + right) / 2;
        process(arr,left,mid);
        process(arr,mid + 1,right);
        merge(arr,left,mid,right);
        print(arr);
    }

    public static void merge(int[] arr,int left,int mid,int right){
        int[] help = new int[right - left + 1];
        int p = left;
        int q = mid + 1;
        int i = 0;
        while (p <= mid && q <= right){
            help[i++] = arr[p] <= arr[q] ? arr[p++] : arr[q++];
        }
        while (p <= mid){
            help[i++] = arr[p++];
        }
        while (q <= right){
            help[i++] = arr[q++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[left+j] = help[j];
        }
    }
    public static void print(int[] arr){
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void mergeSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N){
            int L = 0;
            while (L < N){
                if (mergeSize >= N - L){
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize,N - M - 1);
                merge(arr,L,M,R);
                if (mergeSize > N / 2){
                    break;
                }
                mergeSize <<= 1;
            }
        }
    }
    public static void swap(int[]arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void splitNum(int[] arr){
        int lessEqualR = -1;
        int index = 0;
        int mostR = arr.length - 1;
        while (index < arr.length){
            if (arr[index] <= arr[mostR]){
                swap(arr,++lessEqualR,index++);
            }else {
                index++;
            }
        }
        print(arr);
    }

    public static void splitNum2(int[] arr){
        int N = arr.length;
        int lessR = -1;
        int moreL = N - 1;
        int index = 0;
        while (index < moreL){
            if (arr[index] < arr[N-1]){
                swap(arr,index++,++lessR);
            }else if (arr[index] > arr[N-1]){
                swap(arr,index,--moreL);
            }else{
                index++;
            }
        }
        swap(arr,N-1,moreL);
        print(arr);
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,5,1,4};
        splitNum(arr);
    }
}
