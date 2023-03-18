public class MaxDistance {

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
        public int maxDistance;

        Info(int height,int maxDistance){
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    //  最长距离
    public static Info process(TreeNode head){
        if (head == null){
            return new Info(0,0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;

        //  最大距离的三种形式
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + rightInfo.height + 1;

        int maxDistance = Math.max(Math.max(p1,p2),p3);
        return new Info(height,maxDistance);
    }
}
