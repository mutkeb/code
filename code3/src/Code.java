public class Code {
    public static class Node{
        int value;
        Node next;
    }

    public static Node getLastNode(Node start,int k){
        if (--k < 0 || start == null){
            return start;
        }
        while (k != 0 && start != null){
            start = start.next;
            k--;
        }
        return start;
    }

    //  对指定的某一组进行反转
    public static void reverse(Node start,Node end){
        Node next;
        Node pre = null;
        end = end.next;
        while (start != end){
            next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
    }

    public static Node reverseGroup(Node start,int k){
        Node end = getLastNode(start,k);
        if (end == null){
            //  说明第一组都不够
            return start;
        }

        Node head = end;
        Node temp = end.next;
        reverse(start,end);

        Node lastEnd = start;
        start = temp;

        while (start != null){
            end = getLastNode(start,k);
            if (end == null){
                return head;
            }
            reverse(start,end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    public static void print(Node head){
        while (head != null){
            System.out.print(head.value + ",");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        Node node6 = new Node();
        Node node7 = new Node();
        node1.value = 1;
        node1.next = node2;
        node2.next = node3;
        node2.value = 2;
        node3.next = node4;
        node3.value = 3;
        node4.value = 4;
        node4.next = node5;
        node5.next = node6;
        node5.value = 5;
        node6.next = node7;
        node6.value = 6;
        node7.next = null;
        node7.value = 7;
        print(reverseGroup(node1,3));
    }
}
