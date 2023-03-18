import java.util.PriorityQueue;

public class LessMoreSplitGold {


    public static int lessMoney2(int[] arr){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int sum = 0;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        while (queue.size() > 1){
            cur = queue.poll() + queue.poll();
            sum += cur;
            queue.add(cur);
        }
        return sum;
    }

    //  暴力解法
    public static int lessMoney1(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }
        return process(arr,0);
    }

    //  开始尝试 0-1,0-2,0-3,0-4,1-2,1-3,1-4,2-3,2-4,3-4
    public static int process(int[] arr,int sum){
        if (arr.length == 1){
            return sum;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length ; j++) {
                ans = Math.min(ans,process(copyAndMergeTwo(arr, i, j),sum + arr[i] + arr[j]));
            }
        }
        return sum;
    }


    //  把i跟j的数合并，生成一个新的数返回
    public static int[] copyAndMergeTwo(int[] arr,int i ,int j){
        int[] copy = new int[arr.length - 1];
        int index = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j){
                copy[index++] = arr[k];
            }
        }
        copy[index] =arr[i] + arr[j];
        return copy;
    }
}
