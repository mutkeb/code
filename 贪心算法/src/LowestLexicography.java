import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LowestLexicography {


    //  贪心做法

    //  比较器
    static class Com implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }

    public static String process(String[] strs){
        if (strs.length < 1 || strs == null){
            return null;
        }

        Arrays.sort(strs,new Com());
        int N = strs.length;
        String res = "";
        for (int i = 0; i < N; i++) {
            res += strs[i];
        }
        return res;
    }


    //  暴力做法

    public static String getAnswer(String[] strs){
        if (strs.length < 1 || strs == null){
            return null;
        }
        List<String> list = process2(strs);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return list.get(0);
    }

    public static List<String> process2(String[] strs){
        List<String> ans = new ArrayList<>();
        if (strs == null || strs.length < 1){
            ans.add("");
            return ans;
        }
        int N = strs.length;
        for (int i = 0; i < N; i++) {
            String first = strs[i];
            String next[] = removeIndex(i,strs);
            List<String> cur = process2(next);
            for (String s : cur) {
                ans.add(first + s);
            }
        }
        return ans;
    }

    public static String[] removeIndex(int index,String[] strs){
        if (strs.length < 1 || strs == null){
            return null;
        }
        int N = strs.length;
        String[] ans = new String[N-1];
        int j = 0;
        for (int i = 0; i < N; i++) {
            if (i != index){
                ans[j++] = strs[i];
            }
        }
        return ans;
    }



    //  对数器
    //  生成这样一个String
    public static String generateString(int maxLen){
        char[] chars = new char[(int)(Math.random() * maxLen + 1)];
        for (int i = 0; i < chars.length; i++) {
            int maxValue = 5;
            char val = Math.random() < 0.5 ? (char)(65 + (int)(Math.random()* maxValue)) : (char)(97 + (int)(Math.random()* maxValue));
            chars[i] = val;
        }
        return String.valueOf(chars);
    }

    //  生成多个String
    public static String[] generateStringArray(int maxLen,int maxSize){
        int size = (int)(Math.random() * maxSize + 1);
        String[] strs = new String[size];
        for (int i = 0; i < size; i++) {
            strs[i] = generateString(maxLen);
        }
        return strs;
    }

    //  复制数组
    public static String[] copy(String[] strs){
        String[] copy = new String[strs.length];
        for (int i = 0; i < strs.length; i++) {
            copy[i] = strs[i];
        }
        return copy;
    }

    public static void main(String[] args) {
        int maxLen = 5;
        int maxSize = 10;
        int testTimes = 10;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] strs = generateStringArray(maxLen, maxSize);
            String[] copy = copy(strs);
            if (!process(strs).equals(getAnswer(copy))){
                for (String str : strs) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
