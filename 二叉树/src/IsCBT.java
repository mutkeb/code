import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class IsCBT {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info{
        public boolean isFull;
        public boolean isCBT;
        public int height;
        public Info(boolean isFull,boolean isCBT,int height){
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    //  完全二叉树，层序遍历解法
    public static boolean isCBT(TreeNode head){
        if (head == null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        TreeNode left = null;
        TreeNode right = null;
        //  代表遇到过左右孩子不全满的节点，此时遇到一个非叶子节点就报错
        Boolean isLeaf = false;
        while (!queue.isEmpty()){
            head = queue.poll();
            left = head.left;
            right = head.right;
            if ((left == null && right != null) || (isLeaf &&(left != null || right != null))){
                return false;
            }
            if (left != null){
                queue.add(left);
            }
            if (right != null){
                queue.add(right);
            }
            if (left == null || right == null){
                isLeaf = true;
            }
        }
        return true;
    }

    //  递归解法
    public static Info isCBT2(TreeNode head){
        if (head == null){
            return new Info(true,true,0);
        }
        Info leftInfo = isCBT2(head.left);
        Info rightInfo = isCBT2(head.right);


        int height = Math.max(leftInfo.height,rightInfo.height) + 1;

        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        if (leftInfo.isFull && rightInfo.isFull && (leftInfo.height == rightInfo.height || leftInfo.height == rightInfo.height + 1)){
            isCBT = true;
        }
        if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
            isCBT = true;
        }
        if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
            isCBT = true;
        }


        return new Info(isFull,isCBT,height);
    }
}
