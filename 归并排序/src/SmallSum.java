
public class SmallSum {


    public static int merge(int[] arr,int L ,int M,int R){
        int[] help = new int[R - L + 1];
        int p = L;
        int q = M + 1;
        int i = 0;
        int res = 0;
        while (p <= M && q <= R){
            res += arr[p] < arr[q] ? arr[p] * (R - q + 1) : 0;
            help[i++] = arr[p] < arr[q] ? arr[p++] : arr[q++];
        }

        while (p <= M){
            help[i++] = arr[p++];
        }
        while (q <= R){
            help[i++] = arr[q++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L+j] = help[j];
        }
        return res;
    }

    public static int process(int[] arr,int L ,int R){
        if (L == R){
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return process(arr,L,M)
                + process(arr,M+1,R)
                + merge(arr,L,M,R);
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2};
        System.out.println("小和为:" + process(arr,0,arr.length-1));
    }

}
