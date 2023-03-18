import javax.swing.tree.TreeNode;

public class Code {

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info{
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(TreeNode x){
        if (x == null){
            return  null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int max = x.val;
        int min = x.val;
        boolean isBST = true;

        if (leftInfo == null){
            max = leftInfo.max > x.val ? leftInfo.max : x.val;
            min = leftInfo.min < x.val ? leftInfo.min : x.val;
        }

        if (rightInfo == null){
            max = rightInfo.max > x.val ? rightInfo.max : x.val;
            min = rightInfo.min < x.val ? rightInfo.min : x.val;
        }

        if (!leftInfo.isBST && leftInfo != null){
            isBST = false;
        }
        if (!rightInfo.isBST && rightInfo != null){
            isBST = false;
        }

        return new Info(isBST,max,min);
    }

    public static void main(String[] args) {

    }
}
