import java.util.Stack;

public class isPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //  利用栈的形式
    public static boolean isPalindromeList1(Node head){
        if (head == null){
            return true;
        }
        Node cur = head;
        Stack<Node> stack = new Stack();
        //  压栈
        while (head != null){
            stack.push(head);
            head = head.next;
        }
        //  出栈
        while (!stack.isEmpty()){
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }

        return true;
    }

    //  不利用栈的形式
    public static boolean isPalindromeList2(Node head){
        if (head == null || head.next == null) {
            return true;
        }

        //  找到中点的位置
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //  找出中点位置slow
        Node temp = slow.next;
        slow.next = null;
        //  开始倒置
        Node temp2 = null;
        while (temp != null){
            temp2 = temp.next;
            temp.next = slow;
            slow = temp;
            temp = temp2;
        }
        //  查看是否一一对应
        Node temp3 = slow;
        boolean res = true;
        while (head != null){
            if (head.value != slow.value){
                res = false;
                break;
            }
            head = head.next;
            slow = slow.next;
        }
        //  再给倒回去
        temp2 = temp3.next;
        temp3.next = null;
        while (temp2 != null){
            temp = temp2.next;
            temp3.next = temp2;
            temp3 = temp2;
            temp2 = temp;
        }
        return res;
    }
}
