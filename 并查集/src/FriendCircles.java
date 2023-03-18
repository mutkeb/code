public class FriendCircles {

    public static int findCircleNum(int[][] M){
        int N = M.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (M[i][j] == 1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.sets;
    }

    public static class UnionFind{
        //  parent[i] = k, i的父亲是k
        private int[] parent;
        //  size[i] = k,如果i是代表节点，size[i]才有意义
        //  i所在的集合有多大
        private int[] size;
        //  辅助结构
        private int[] help;
        //  一共有多少个集合
        private int sets;

        public UnionFind(int N){
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = 0;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        //  把沿途的都记下来，统一指向同一个代表节点
        //  栈会慢很多
        private int find(int i){
            int h1 = 0;
            if (parent[i] != i){
                help[h1++] = i;
                i = parent[i];
            }
            for (int j = h1; j > 0; j--) {
                parent[help[h1]] = i;
            }
            return i;
        }

        private boolean isSameSet(int a,int b){
            return find(a) == find(b);
        }

        private void union(int a,int b){
            int f1 = find(a);
            int f2 = find(b);
            if (f1 != f2){
                if (size[f1] >= size[f2]){
                    size[f1] += size[f2];
                    parent[f2] = f1;
                }else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int sets(){
            return sets;
        }
    }
}
