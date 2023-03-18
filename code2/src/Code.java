public class Code {

    public static int getLeft(int[] arr,int num){
        if (arr == null || arr.length == 0){
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R){
            int mid = (L + R) / 2;
            if (num <= arr[mid]){
                R = mid - 1;
                index = mid;
            }else{
                L = mid + 1;
            }
        }
        return index;
    }

    public static int getRight(int[] arr,int num){
        if (arr == null || arr.length == 0){
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R){
            int mid = (L + R) / 2;
            if (num >= arr[mid]){
                L = mid + 1;
                index = mid;
            }else{
                L = mid + 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,2,2,2,4};
        System.out.println(getLeft(arr,2));
        System.out.println(getRight(arr,2));
    }
}
