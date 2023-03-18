public class Test2 {
    static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }

    public static void delete(Node head){
        Node firstNode = null;
        Node lastNode = null;
        while (head != null){
            if (firstNode == null || head.val != firstNode.val){
                lastNode = head;
                if (firstNode != null){
                    deleteFromFirstToLast(firstNode,lastNode);
                }
                firstNode = head;
            }else{
                lastNode = head;
            }
            head = head.next;
        }
    }

    public static void deleteFromFirstToLast(Node first, Node last){
        //  如果头尾相同就不用删了
        if (first == last){
            return;
        }
        //  头尾不同删除头尾之间的
        first.next = last;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        Node node3 = new Node(2);
        node1.next = node2;
        node2.next = node3;
        delete(node1);
        System.out.println(node1.next.val);


    }
}
