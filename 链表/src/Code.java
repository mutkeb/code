public class Code {
    public static class Node{
        private int value;
        private Node next;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
        Node(){};
    }

    public static Node removeValue(Node head,int num){
        while (head != null){
            if (head.value != num){
                break;
            }
            head = head.next;
        }
        Node cur = head;
        Node pre = head;
        while (cur != null){
            if (cur.value == num){
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node node1 = new Node();
        node1.setValue(1);
        Node node2 = new Node();
        node2.setValue(2);
        Node node3 = new Node();
        node3.setValue(3);
        Node node4 = new Node();
        node4.setValue(1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        Node node = removeValue(node1, 1);
        while (node != null){
            System.out.println(node.value);
            node = node.next;
        }
    }
}
