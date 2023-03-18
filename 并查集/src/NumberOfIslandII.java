import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NumberOfIslandII {

    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        UnionFind unionFind = new UnionFind(m,n);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            ans.add(unionFind.connect(positions[i][0],positions[i][1]));
        }
        return ans;
    }

    public static List<Integer> numIslands22(int m,int n, int[][] postions){
        UnionFind2 unionFind2 = new UnionFind2();
        List<Integer> ans = new ArrayList<>();
        for (int[] postion : postions) {
            ans.add(unionFind2.connect(postion[0],postion[1]));
        }
        return ans;
    }

    public static class UnionFind{
        public int[] parent;
        public int[] size;
        public int[] help;
        public int row;
        public int col;
        public int sets;

        public UnionFind(int m,int n){
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        public int find(int m){
            int h1 = 0;
            while (m != parent[m]){
                help[h1++] = m;
                m = parent[m];
            }
            for (int i = h1; i > 0; i--) {
                parent[help[i]] = m;
            }
            return m;
        }

        //  组装并返回个数,因为每次加入是分开的，所以要四个方向
        public int connect(int m,int n){
            int index = index(m,n);
            if (size[index] != 1){
                //  开始初始化
                size[index] = 1;
                parent[index] = index;
                sets++;
                union(m-1,n,m,n);
                union(m+1,n,m,n);
                union(m,n+1,m,n);
                union(m,n-1,m,n);
            }
            return sets;
        }

        private void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }


        public int index(int m,int n){
            return m * col + n;
        }
    }

    public static class UnionFind2{
        public HashMap<String,String> parent;
        public HashMap<String,Integer> size;
        public ArrayList<String> help;
        public int sets;
        public UnionFind2(){
            parent = new HashMap<>();
            size = new HashMap<>();
            sets = 0;
        }

        public String find(String x){
            if (!x.equals(parent.get(x))){
                help.add(x);
                x = parent.get(x);
            }
            for (String s : help) {
                parent.put(s,x);
            }
            //  注意要清空
            help.clear();
            return x;
        }


        public int connect(int r,int c){
            String key = String.valueOf(r) + "_" + String.valueOf(c);
            if (!size.containsKey(key)){
                size.put(key,1);
                parent.put(key,key);
                sets++;
                String upKey = String.valueOf(r-1) + "_" + String.valueOf(c);
                String downKey = String.valueOf(r+1) + "_" + String.valueOf(c);
                String leftKey = String.valueOf(r) + "_" + String.valueOf(c-1);
                String rightKey = String.valueOf(r-1) + "_" + String.valueOf(c+1);
                union(upKey,key);
                union(downKey,key);
                union(leftKey,key);
                union(rightKey,key);
            }
            return sets;
        }

        //  不做边界判定要怎么处理，这里不会发生越界，所以没关系
        public void union(String key1,String key2){
            if (size.containsKey(key1) && size.containsKey(key2)){
                String parent1 = find(key1);
                String parent2 = find(key2);
                int size1 = size.get(parent1);
                int size2 = size.get(parent2);
                if (!parent1.equals(parent2)){
                    if (size1 >= size2){
                        parent.put(key2,parent1);
                        size.put(key1,size1 + size2);
                    }else {
                        parent.put(key1,parent2);
                        size.put(key2,size1 + size2);
                    }
                }
                sets--;
            }
        }
    }
}
