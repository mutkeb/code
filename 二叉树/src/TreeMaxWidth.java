import java.util.LinkedList;
import java.util.Queue;

public class TreeMaxWidth {
    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }
    //  找的是最大宽度
    public static int maxWidthUse(TreeNode head){
        if (head == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curEnd = null;
        TreeNode nextEnd = null;
        int max = 0;
        int levelNode = 0;  //  当前层的节点数
        queue.add(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            if (curEnd == null){
                curEnd = head;
            }
            if (head.left != null){
                queue.add(head.left);
                nextEnd = head.left;
            }
            if (head.right != null){
                queue.add(head.right);
                nextEnd = head.right;
            }
            levelNode++;
            if (head == curEnd){
                curEnd = nextEnd;
                max = Math.max(max,levelNode);
                levelNode = 0;
            }
        }
        return max;
    }
}
