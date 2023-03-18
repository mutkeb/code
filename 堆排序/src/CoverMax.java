import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CoverMax {

    //  线段类
    static class Line{
        public int start;
        public int end;

        public Line(int start,int end){
            this.start = start;
            this.end = end;
        }
    }

    //  遍历方法
    public static int maxCover1(int [][] lines){
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min,lines[i][0]);
            max = Math.max(max,lines[i][1]);
        }
        int cover = 0;
        for (double i = min + 0.5; i < max ; i++) {
            int cur = 0;
            for (int j = 0; j < lines.length; j++) {
                if (i > lines[j][0] && i < lines[j][1]){
                    cur++;
                }
            }
            cover = Math.max(cover,cur);
        }
        return cover;
    }

    //  堆方法
    public static int maxCover2(int[][] m){
        //  排序
        Line[] lines = new Line[m.length];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line(m[i][0],m[i][1]);
        }
        Arrays.sort(lines, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.start - o2.start;
            }
        });
        //  建一个小根堆
        int max = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < lines.length; i++) {
            //  淘汰
            while (!heap.isEmpty()){
                if (heap.peek() <= lines[i].start){
                    heap.poll();
                }
            }
            //  加入
            heap.add(lines[i].end);
            //  获得最大值
            max = Math.max(max,heap.size());
        }
        return max;
    }

}
