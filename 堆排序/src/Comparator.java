import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Comparator {
    static class Com implements java.util.Comparator{

        @Override
        public int compare(Object o1, Object o2) {
            return -1;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[5];
        int[] arr2 = {1,2,3};
        Arrays.sort(arr2);
        System.out.println(arr2[0]);
        arr[0] = 2;
        Arrays.sort(arr,new Com());
        System.out.println(arr[0]);

        List list = new ArrayList();
        list.sort(new Com());
    }
}
