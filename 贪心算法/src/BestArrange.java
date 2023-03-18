import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {

    public static class Program{
        public int start;
        public int end;
        public Program(int start,int end){
            this.start = start;
            this.end = end;
        }
    }
    public static class MyComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    //  贪心算法
    public static int bestArrange(Program[] programs){
        if (programs == null || programs.length < 0){
            return 0;
        }
        Arrays.sort(programs,new MyComparator());
        int deadLine = 0;
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= deadLine){
                deadLine = programs[i].end;
                result++;
            }
        }
        return result;
    }
    
    //  暴力解法
    public static int bestArrange2(Program[] programs,int result,int deadLine){
        if (programs == null || programs.length < 0){
            return result;
        }
        int max = result;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= deadLine){
                Program[] next = remove(i, programs);
                max = Math.max(max,bestArrange2(next,result + 1,programs[i].end));
            }
        }
        return max;
    }

    //  剔除某一个会议
    public static Program[] remove(int index,Program[] programs){
        if (programs == null || programs.length < 0){
            return null;
        }
        int j = 0;
        Program[] copyPrograms = new Program[programs.length - 1];
        for (int i = 0; i < programs.length; i++) {
            copyPrograms[j++] = programs[i];
        }
        return copyPrograms;
    }
}
