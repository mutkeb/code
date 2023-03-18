
public class Code {

    //  单个数不同
    public static int find(int[] arr){

        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans ^= arr[i];
        }
        return ans;
    }

    //  找最右侧的1
    public static int findRight(int a){
        return a & -a;
    }

    public static void find2(int[] arr){
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans ^= arr[i];
        }

        int right = findRight(ans);
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & right) != 0){
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + "  " + (onlyOne ^ ans));
    }

    public  static void main(String[] args) {
        int[] arr = {1,2,3,3,2,6};
        System.out.println(find(arr));
        find2(arr);
    }
}
