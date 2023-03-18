public class RadixSort {

    public static void radixSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        radixSort(arr,0, arr.length-1,maxbits(arr));
    }

    //  获得最大位数
    public static int maxbits(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int res = 0;
        while (max > 0){
            res++;
            max /= 10;
        }
        return res;
    }

    public static void radixSort(int[] arr,int L,int R,int digit){
        //  辅助数组——要排出来的数组
        int[] help = new int[R-L+1];
        //  得到循环次数
        int maxbits = maxbits(arr);
        for (int i = 1; i <= maxbits; i++) {
            //  直接i就代表每次要比较的位数
            int[] count = new int[10];
            for (int j = L; j <= R; j++){
                int digit1 = getDigit(arr[j], i);
                count[digit1]++;
            }
            //  构建前缀和数组
            for (int j = 0; j < 10; j++){
                count[j] += count[j-1];
            }

            //  从桶中取出
            for (int j = L; j <= R ; j++) {
                //  取出的数
                int num = arr[j];
                //  拿到count*数组对比是哪一个桶
                int digit1 = getDigit(arr[j], i);
                int index = --count[digit1];
                help[index] = arr[j];
            }
            //  从help中取出来，因为原数组不能直接换
            for (int j = 0; j < help.length; j++) {
                arr[L + j] = help[j];
            }
        }
    }

    //  获得对应位置的数
    public static int getDigit(int x, int d){
        return (int)((x / Math.pow(10,d-1)) % 10);
    }
}
