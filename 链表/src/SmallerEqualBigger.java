public class SmallerEqualBigger {
    public static class Node{
        public int val;
        public Node next;
        public Node(int val){
            this.val = val;
        }
    }

    public static Node SmallerEqualBigger(Node head,int target){
        if (head == null){
            return null;
        }
        //  设置小于区的头和尾
        Node smallHead = null;
        Node smallTail = null;
        //  设置等于区的头和尾
        Node equalHead = null;
        Node equalTail = null;
        //  设置大于区的头和尾
        Node bigHead = null;
        Node bigTail = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = null;
            if (head.val < target){
                //  小于区域
                if (smallHead == null){
                    //  开始插入第一个节点
                    smallHead = head;
                    smallTail = head;
                }else {
                    smallTail.next = head;
                    smallTail = head;
                }

            }else if (head.val == target){
                //  等于区域
                if (equalHead == null){
                    //  开始插入第一个节点
                    equalHead = head;
                    equalTail = head;
                }else {
                    equalTail.next = head;
                    equalTail = head;
                }
            }else {
                //  大于区域
                if (bigHead == null){
                    //  开始插入第一个节点
                    bigHead = head;
                    bigTail = head;
                }else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }
            head = next;
        }
        //  开始进行链表的连接
        if (smallHead != null){
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }

        if (equalTail != null){
            equalTail.next = bigHead;
        }

        return smallHead != null ? smallHead : (equalHead != null ? equalHead : bigHead);
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        Node head2 = new Node(3);
        Node head3 = new Node(4);
        head.next = head2;
        System.out.println(head.next.val);
        Node temp = head;
        temp.next = head3;
        System.out.println(head.next.val);
    }
}
