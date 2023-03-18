import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandom {

    public static class Node{
        public int val;
        public Node next;
        public Node random;

        public Node(int val){
            this.val = val;
        }
    }

    public static Node copyRandomList1(Node head){
        Map<Node,Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null){
            map.put(cur,new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null){

            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyRandomList2(Node head){
        if (head == null){
            return null;
        }
        Node cur = head;
        Node next = null;
        //  构建特殊链表
        while (cur != null){
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }

        //  对random节点进行复制
        cur = head;
        Node copy = null;
        while (cur != null){
            next = cur.next.next;
            copy = cur.next;
            copy.random = next != null ? next.next : null;
            cur = next;
        }

        //  将复制链表进行链接
        Node res = head.next;
        cur = head;
        while (cur != null){
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }

        return res;
    }
}
