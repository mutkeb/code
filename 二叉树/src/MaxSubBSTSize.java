public class MaxSubBSTSize {

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val){
            this.val = val;
        }
    }

    public static class Info{
        public int max;
        public int min;
        public int allSize;
        public int maxSize;

        public Info(int max, int min, int allSize, int maxSize) {
            this.max = max;
            this.min = min;
            this.allSize = allSize;
            this.maxSize = maxSize;
        }
    }

    //  最大搜索二叉树子树
    public static Info maxSubBSTSize(TreeNode head){
        if (head == null){
            return null;
        }
        Info leftInfo = maxSubBSTSize(head.left);
        Info rightINfo = maxSubBSTSize(head.right);

        int max = head.val;
        int min = head.val;
        int allSize = 1;
        int maxSize = 1;
        if (leftInfo != null){
            max = Math.max(max,leftInfo.max);
            min = Math.min(min,leftInfo.min);
            allSize += leftInfo.allSize;
        }
        if (rightINfo != null){
            max = Math.max(max,rightINfo.max);
            min = Math.min(min,rightINfo.min);
            allSize += rightINfo.allSize;
        }

        int p1 = -1;
        //  先将前面的数据同步转过来
        if (leftInfo != null){
            p1 = leftInfo.maxSize;
        }
        int p2 = -1;
        if (rightINfo != null){
            p2 = rightINfo.maxSize;
        }
        //  看当前数据是否要加工，也就是当前是否符合平衡二叉树
        int p3 = -1;
        boolean isLeftBST = leftInfo == null ? true : (leftInfo.maxSize == leftInfo.allSize);
        boolean isRightBST = rightINfo == null ? true : (rightINfo.maxSize >= rightINfo.allSize);
        if (isLeftBST && isRightBST){
            boolean isLessX = leftInfo == null ? true : (leftInfo.max <= head.val);
            boolean isMoreX = rightINfo == null ? true : (rightINfo.min >= head.val);
            if (isLessX && isMoreX){
                //  这里leftInfo很有可能为空，所以要单独拿出来判断
                int leftSize =  leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightINfo == null ? 0 : rightINfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        return new Info(Math.max(Math.max(p1,p2),p3),allSize,max,min);
    }
}
