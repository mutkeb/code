public class LowestAncestor {

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

    public static class Info{
        public boolean findA;
        public boolean findB;
        public TreeNode ans;
        public Info(boolean findA,boolean findB,TreeNode ans){
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    //  最近公共祖先
    public static Info findLowestAncestor(TreeNode head,TreeNode a,TreeNode b){
        if (head == null){
            return new Info(false,false,null);
        }
        Info leftInfo = findLowestAncestor(head.left,a,b);
        Info rightInfo = findLowestAncestor(head.right,a,b);

        boolean findA = head == a || leftInfo.findA || rightInfo.findA;
        boolean findB = head == b || leftInfo.findB || rightInfo.findB;

        TreeNode ans = null;
        if (leftInfo.ans != null){
            ans = leftInfo.ans;
        }else if (rightInfo.ans != null){
            ans = rightInfo.ans;
        }else {
            if (findA && findB){
                ans = head;
            }
        }
        return new Info(findA,findB,ans);
    }
}
