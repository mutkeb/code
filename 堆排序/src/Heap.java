import java.util.PriorityQueue;

public class Heap {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //  向上移

    public static void heapInsert(int[] arr,int index){
        while (arr[index] > arr[(index-1) /2]){
            swap(arr,index,(index-1)/2);
            index = (index - 1) / 2;
        } 
    }

    //  向下移
    public static void heapify(int[] arr,int index,int heapSize){
        int left = 2 * index + 1;

        while (left < heapSize){
            int largest = left + 1 < heapSize && arr[left] < arr[left+1]? left + 1: left;

            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index){
                break;
            }
            swap(arr,largest,index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    //  堆排序
    public static void heapSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        //  先一个个插入构造堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,arr[i]);
        }

        //  再一个个换位置
        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        while (heapSize > 0){
            //  因为只有0一个位置可能是不符合根最大的，只需要动0位置就行
            heapify(arr,0,heapSize);
            swap(arr,0,--heapSize);

        }
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(3);
        heap.add(5);
        System.out.println("堆顶:" + heap.peek());
        
    }
}
