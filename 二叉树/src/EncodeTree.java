import java.util.ArrayList;
import java.util.List;

public class EncodeTree {

    public static class Node{
        public int val;
        public List<Node> children;

        public Node(int val,List<Node> children){
            this.val = val;
            this.children = children;
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    //  多叉树转二叉树
    public static TreeNode encode(Node root){
        if (root == null){
            return null;
        }
        TreeNode head = new TreeNode(root.val);
        head.left = en(root.children);
        return head;
    }

    private static TreeNode en(List<Node> nodeList){
        if (nodeList == null || nodeList.size() < 0){
            return null;
        }
        TreeNode head = null;
        TreeNode cur = null;
        for (Node node : nodeList) {
            TreeNode temp = new TreeNode(node.val);
            if (head == null){
                head = temp;
            }
            if (cur != null){
                cur.right = temp;
            }
            cur = temp;
            temp.left = en(node.children);
        }
        return head;
    }

    //  二叉树转多叉树
    public static Node decode(TreeNode root){
        if (root == null){
            return null;
        }
        Node head = new Node(root.val,de(root.left));
        return head;
    }


    private static List<Node> de(TreeNode head){
        if (head == null){
            return null;
        }
        List<Node> children = new ArrayList<>();
        while (head != null){
            children.add(new Node(head.val,de(head.left)));
            head = head.right;
        }
        return children;
    }

}
