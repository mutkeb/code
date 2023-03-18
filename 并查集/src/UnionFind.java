import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind<V> {

    public static class Node<V>{
        public V val;
        public Node(V val){
            this.val = val;
        }
    }

    public HashMap<V,Node<V>> nodes;
    public HashMap<Node<V>,Node<V>> parents;
    public HashMap<Node<V>,Integer> sizeMap;

    public UnionFind(List<V> values){
        nodes = new HashMap<>();
        parents = new HashMap<>();
        sizeMap = new HashMap<>();
        for (V value : values) {
            Node<V> node = new Node<>(value);
            nodes.put(value,node);
            parents.put(node,node);
            sizeMap.put(node,1);
        }
    }


    //  找到代表节点，要将其连起来，因为一开始的节点都是直接指向祖先节点，我们要将其一层一层连起来
    //  例子：4移到2，2又移到6，此时4要找到6
    public Node<V> findFather(Node<V> cur){
        //  如果父节点是本身说明没动过，父节点不是本身说明已经进入并查集了
        Stack<Node<V>> stack = new Stack<>();
        while (parents.get(cur) != cur){
            stack.add(cur);
            cur = parents.get(cur);
        }
        while (!stack.isEmpty()){
            parents.put(stack.pop(),cur);
        }
        return cur;
    }

    //  是否相同
    public boolean isSameSet(V a,V b){
        return findFather(nodes.get(a)) != findFather(nodes.get(b));
    }

    //  合并操作
    public void union(V a,V b){
        Node<V> aHead = findFather(nodes.get(a));
        Node<V> bHead = findFather(nodes.get(b));
        if (aHead != bHead){
            int aSetSize = sizeMap.get(aHead);
            int bSetSize = sizeMap.get(bHead);
            Node<V> bigNode = aSetSize > bSetSize ? aHead : bHead;
            Node<V> smallNode = bigNode == aHead ? bHead : aHead;
            parents.put(smallNode,bigNode);
            sizeMap.put(bigNode,aSetSize + bSetSize);
            nodes.remove(smallNode);
        }
    }

    //  返回目前还有几个并查集
    public int sets(){
        return sizeMap.size();
    }


}
