import java.util.HashMap;

public class FindFirstIntersecNode {

    public static class Node {
        public int val;
        public Node next;

        Node(int val) {
            this.val = val;
        }
    }

    //  利用哈希表
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        HashMap<Node, Integer> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            if (map.containsKey(cur)) {
                //  说明有环
                return cur;
            }
            map.put(cur, 1);
            cur = cur.next;
        }
        return null;
    }

    //  利用快慢指针
    public static Node getLoopNode2(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //  两个非环相交
    public static Node noLoop(Node head1,Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1 != null){
            n++;
            cur1 = cur1.next;
        }

        while (cur2 != null){
            n--;
            cur2 = cur2.next;
        }
        //  不相交
        if (cur1 == cur2){
            return null;
        }
        //  长的链表
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0){
            cur1 = cur1.next;
            n--;
        }
        //  开始共同搜索
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public static Node bothLoop(Node head1,Node head2,Node loop1,Node loop2){
        //  分为三种情况
        if (loop1 == loop2){
            Node cur1 = head1;
            Node cur2 = head2;
            int n = 0;
            while (cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }

            while (cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else{
            Node cur1 = loop1.next;
            while (cur1 != loop1){
                if (cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }


    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;

        System.out.println(getLoopNode(node1).val);
    }
}
