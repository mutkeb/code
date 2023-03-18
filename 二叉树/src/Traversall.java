import java.util.Stack;

public class Traversall {

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        Node(int val) {
            this.val = val;
        }
    }
    //  遍历的各种非递归写法

    //  先序遍历
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.println(head.val + "");
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
    }

    //  后序遍历（利用中左右反过来就是后序）
    public static void pos1(Node head){
        if (head != null){
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            stack1.push(head);
            while (!stack1.isEmpty()){
                head = stack1.pop();
                stack2.push(head);
                if (head.left != null) {
                    stack1.push(head.right);
                }
                if (head.right != null) {
                    stack1.push(head.left);
                }
            }
            while (!stack2.isEmpty()){
                System.out.println(stack2.pop().val + "");
            }
        }
        System.out.println();
    }


    //  中序
    public static void in(Node cur) {
        if (cur != null){
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || cur != null){
                //  开始一直往左遍历
                if (cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }else {
                    cur = stack.pop();
                    System.out.println(cur.val +  "");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }


}
