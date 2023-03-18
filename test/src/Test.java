import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Test {
    static {
        System.out.println("1");
    }
    public Test(){
        System.out.println("2");

    }
    {
        System.out.println("3");
    }
}
class B extends Test{
    static {
        System.out.println("A");
    }
    public B(){
        System.out.println("B");

    }
    {
        System.out.println("C");
    }
}

class Test3{
    static {
        System.out.println("D");
    }

    public static String[] getMatcherStrPattern(String pattern,String str){
        String[] result = new String[0];
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        while (m.find()){
            result = Arrays.copyOf(result,result.length+1);
            result[result.length-1] = m.group();
        }
        return result;
    }


    public static void main(String[] args) {
        new B();
//
//        System.out.println(IntStream.range(7,9).map(a-> a*5).boxed().filter(a->a.compareTo(8)>0).reduce(0,Integer::sum));

        String pattern = "^[A-Za-z]$.";
        String str = "cb>?@wer,ht";
        String[] matcherStrPattern = getMatcherStrPattern(pattern, "cb.");
        for (String s : matcherStrPattern) {
            System.out.println(s);
        }

    }
}