import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Serialize {
    public static class Node {
        public int val;
        public Node left;
        public Node right;

        Node(int val) {
            this.val = val;
        }
    }

    //  序列化，先序
    public static Queue<String> preSerial(Node head){
        Queue<String> queue = new LinkedList<>();
        pres(head,queue);
        return queue;
    }

    public static void pres(Node head,Queue<String> ans){
        if (head == null){
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.val));
            pres(head.left,ans);
            pres(head.right,ans);
        }
    }

    //  反序列化，先序
    public static Node buildByPreQueue(Queue<String> queue){
        if (queue == null || queue.size() == 0){
            return null;
        }
        return preb3(queue);
    }

    public static Node preb(Queue<String> preList){
        String value = preList.poll();
        if (value == null){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }

    //  非递归写法
    //  自己写的
    public static Node preb2(Queue<String> preList){
        String value = preList.poll();
        if (value == null){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        Stack<Node> stack1 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()){
            Node cur = stack1.pop();
            String leftVal = preList.poll();
            if (leftVal == null){
                cur.left = null;
                String rightVal = preList.poll();
                if (rightVal == null){
                    cur.right = null;
                    if (!preList.isEmpty() && stack1.isEmpty()){
                        String poll = preList.poll();
                        Node right = new Node(Integer.valueOf(poll));
                        head.right = right;
                        stack1.add(new Node(Integer.valueOf(poll)));
                    }
                }else {
                    Node rightNode = new Node(Integer.valueOf(rightVal));
                    cur.right = rightNode;
                    stack1.add(rightNode);
                }
            }else {
                Node leftNode = new Node(Integer.valueOf(leftVal));
                cur.left = leftNode;
                stack1.add(leftNode);
            }
        }
        return head;
    }

    public static Node preb3(Queue<String> preList){
        String value = preList.poll();
        if (value == null){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        Stack<Node> stack1 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()){
            head = stack1.pop();
            head.left = generateNode(preList.poll());
            if (head.right != null){
                stack1.add(head.right);
            }
            if (head.left != null){
                stack1.add(head.left);
            }else {
                head.right = stack1.pop();
            }
        }
        return head;
    }

    //  序列化，层序
    public static Queue<String> levelSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        if (head == null){
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.val));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()){
                head = queue.poll();
                if (head.left != null){
                    ans.add(String.valueOf(head.left.val));
                    queue.add(head.left);
                }else {
                    ans.add(null);
                }
                if (head.right != null){
                    ans.add(String.valueOf(head.right.val));
                    queue.add(head.right);
                }else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    //  反序列化，层序
    public static Node buildByLevelQueue(Queue<String> levelList){
        if (levelList == null || levelList.size() < 0){
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head == null){
            queue.add(null);
        }
        queue.add(head);
        Node node = null;
        while (!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }
        }
        return head;
    }

    public static Node generateNode(String val){
        return new Node(Integer.valueOf(val));
    }

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("4");
        queue.add("2");
        queue.add(null);
        queue.add(null);
        queue.add("3");
        queue.add(null);
        queue.add(null);
        Node node = buildByPreQueue(queue);
        System.out.println(node.val);
        System.out.println(node.left.val);
        System.out.println(node.right.val);
    }
}
