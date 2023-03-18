public class LinkedListMid {

    public static class Node{
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    //  输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    public Node linkedList1(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return head;
        }
        //  至少三个节点
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //  输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public Node linkedList2(Node head){
        if (head == null || head.next == null){
            return head;
        }

        //  至少三个节点
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //  输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public Node linkedList3(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        //  至少三个节点才开始能返回
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //  输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public Node linkedList4(Node head){
        if (head == null || head.next == null){
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
