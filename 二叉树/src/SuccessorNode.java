public class SuccessorNode {

    public static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int val){
            this.val = val;
        }
    }

    //  这里是随便给一个节点找父节点
    //  找后继节点
    public static Node getSuccessorNode(Node node){
        if (node == null){
            return null;
        }
        if (node.right != null){
            //  往右树找左节点
            return getLeftMost(node.right);
        }else {
            //  往左树找最左的
            Node parent = node.parent;
            //  考虑到了找不到的情况
            while (parent != null){
                if (node == parent.left){
                    break;
                }
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    private static Node getLeftMost(Node node) {
        if (node == null){
            return null;
        }
        while (node.left != null){
            node = node.left;
        }
        return node;

    }
}
