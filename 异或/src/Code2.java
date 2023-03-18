import java.util.HashMap;
import java.util.HashSet;

public class Code2 {
    public static int onlyKTimes(int[] arr,int k,int m){
        int[] t = new int[32];
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0;j <= 31;j++){
                t[j] += (arr[i] >> j) & 1;
            }
        }
        int answer = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0){
                answer |= (1 << i);
            }
        }
        return answer;
    }
    public static int test(int[] arr,int k,int m){
        HashMap<Integer,Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])){
                map.put(arr[i],map.get(arr[i]) + 1);
            }else{
                map.put(arr[i],1);
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == k){
                return key;
            }
        }
        return -1;
    }

    public static int[] generateArray(int maxKinds,int range,int k ,int m){
        //  先生成k
        int kNum =  random(range);
        int numKinds = (int)(Math.random() * maxKinds) + 2;
        int[] array = new int[k + (numKinds - 1) * m];
        int index = 0;
        for (; index < k; index++) {
            array[index] = kNum;
        }
        numKinds--;
        HashSet hashSet = new HashSet();
        hashSet.add(kNum);
        while (numKinds != 0){
            int num = 0;
            do {
                num = random(range);
            }while (hashSet.contains(num));
            hashSet.add(num);
            numKinds--;
            for (int i = 0; i < m; i++){
                array[index++] = num;
            }
        }
        return array;
    }

    //  生成[-range,range]的数
    public static int random(int range){
        return (int)(Math.random() * (2 * range + 1) - range);
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+ ",");
        }
    }


    public static void main(String[] args) {
        int maxKinds = 5;
        int range = 200;
        int maxTimes = 10;
        int testTimes = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int a = (int)(Math.random() * maxTimes + 2);
            int b = (int)(Math.random() * maxTimes + 2);
            int k = a == b ? Math.min(a,b)-1 : Math.min(a,b);
            int m = Math.max(a,b);
            int[]arr = generateArray(maxKinds,range,k,m);
            int test1 = test(arr, k, m);
            int test2 = onlyKTimes(arr, k, m);
            if(test1 != test2){
                System.out.println("测试失败");
                printArray(arr);
                System.out.println("k=" + k + ",m=" + m);
                break;
            }
        }
    }
}
