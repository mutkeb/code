import com.sun.org.apache.regexp.internal.RE;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;

public class CountOfRangeSum {


    public static int countRangeSum(int[]arr,int upper,int lower){
        if (arr.length == 0 || arr == null){
            return 0;
        }
        int N = arr.length;
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i-1] + arr[i];
        }
        return count(sum,0,N-1,upper,lower);
    }

    public static int count(int[]sum,int L,int R,int upper,int lower){
        if (L == R){
            if (sum[L] >= lower && sum[L] <= upper){
                return 1;
            }else{
                return 0;
            }
        }
        int M = (L + R) / 2;
        int leftPart = count(sum,L,M,upper,lower);
        int rightPart = count(sum,M+1,R,upper,lower);
        int mergePart = merge(sum,L,M,R,upper,lower);
        return leftPart + rightPart + mergePart;
    }

    public static int merge(int[] sum,int L,int M,int R,int upper,int lower){
        int windowL = L;
        int windowR = L;
        int ans = 0;
        for (int i = M + 1; i <= R ; i++) {
            if (sum[windowL] < sum[i] - lower && windowL <= M){
                windowL++;
            }
            if (sum[windowR] >= sum[i] - upper && windowL <= M){
                windowR++;
            }
            ans += Math.max(0,windowR - windowL);
        }
        int[] help = new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = M+1;
        while (p1 <= M && p2 <= R){
            help[i++] = sum[p1] < sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= M){
            help[i++] = sum[p1++];
        }
        while (p2 <= R){
            help[i++] = sum[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            sum[L+j] = help[j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,5};
        System.out.println(countRangeSum(arr,1,1));
    }
}
