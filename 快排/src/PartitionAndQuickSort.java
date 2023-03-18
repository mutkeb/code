import com.sun.org.apache.regexp.internal.RE;

public class PartitionAndQuickSort {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // arr[L..R]上，以arr[R]位置的数做划分值
    // <= X > X
    // <= X X
    public static int partition(int[] arr,int L,int R){
        if (L < R){
            return -1;
        }
        if (L == R){
            return L;
        }
        int lessEqual = L-1;
        int index = L;
        while (index < R){
            if (arr[index] <= arr[R]){
                swap(arr,++lessEqual,index++ );
            }
        }
        swap(arr,R,++lessEqual);
        return lessEqual;
    }

    //  第一种快排方法，只有小于等于和大于区域
    public void quickSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process1(arr,0,arr.length-1);
    }

    public void process1(int[] arr,int L,int R){
        if (L >= R) {
            return;
        }

        int M = partition(arr,L,R);
        process1(arr,L,M);
        process1(arr,M+1,R);
    }

    //  第二种快排方法，分为小于等于大于区域
    public void quickSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process2(arr,0,arr.length-1);
    }

    //  返回的数组为等于区域的左边界和有边界
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L < R){
            return new int[] { -1, -1 };
        }
        if (L == R){
            return new int[]{L,L};
        }

        int less = L-1;
        int more = R;
        int index = L;
        while (index <= R){
            if (arr[index] < arr[R]){
                //  小于情况需要与左边界交换
                swap(arr,++less,index++);
            }else if (arr[index] == arr[R]){
                index++;
            }else {
                //  大于情况需要与右边界交换
                swap(arr,--more,index++);
            }
        }
        swap(arr,R,more);
        return new int[]{less+1,more};
    }

    public void process2(int[] arr,int L,int R){
        if (L >= R) {
            return;
        }
        int[] area = netherlandsFlag(arr,L,R);
        process2(arr,0,area[0]-1);
        process2(arr,area[1]+1,R);
    }

    //  第三种快排方法
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }

}
