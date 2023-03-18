public class isBalanced {

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
        public Boolean isBalanced;

        public Info(int height,Boolean isBalanced){
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    //  是否是平衡二叉树
    public static Info isBalanced(TreeNode head){
        if (head == null){
            return new Info(0,true);
        }
        Info leftInfo = isBalanced(head.left);
        Info rightInfo = isBalanced(head.right);

        int height = Math.max(leftInfo.height,rightInfo.height);
        Boolean isBalanced = true;

        if (!leftInfo.isBalanced || !rightInfo.isBalanced){
            isBalanced = false;
        }
        if (Math.abs(leftInfo.height-rightInfo.height) > 1){
            isBalanced = false;
        }
        return new Info(height,isBalanced);
    }
}
