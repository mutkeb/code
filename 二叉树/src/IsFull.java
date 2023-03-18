import sun.reflect.generics.tree.Tree;

public class IsFull {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info{
        public int height;
        public int nodes;

        Info(int height,int nodes){
            this.height = height;
            this.nodes = nodes;
        }
    }

    //  满二叉树
    public static boolean isFull2(TreeNode head){
        if (head == null){
            return true;
        }
        Info info = isFull(head);
        return (1 << info.height) - 1 == info.nodes;
    }

    public static Info isFull(TreeNode head){
        if (head == null){
            return new Info(0,0);
        }
        Info leftInfo = isFull(head);
        Info rightInfo = isFull(head);

        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;

        return new Info(height,nodes);
    }


}
