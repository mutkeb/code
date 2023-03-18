import java.util.LinkedList;
import java.util.Queue;

public class LevelTraversal {

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        Node(int val) {
            this.val = val;
        }
    }

    //  层序遍历
    public static void level(Node head){
        if (head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.val + "");
            if (cur.left != null){
                queue.add(cur.left);
            }
            if (cur.right != null){
                queue.add(cur.right);
            }
        }
    }
}
