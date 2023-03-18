import javax.xml.soap.Node;

public class TrieTree {

    public static class Node1{
        int p ;
        int q;
        Node1[] next;

        Node1(){
            p = 0;
            q = 0;
            next = new Node1[26];
        }
    }

    public static class  Trie1{
        private Node1 root;

        public Trie1(){
            root = new Node1();
        }

        public void insert(String word){
            if (word == null){
                return;
            }
            char[] chars = word.toCharArray();
            Node1 node = root;
            node.p++;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.next[index] == null){
                    node.next[index] = new Node1();
                }
                node.p++;
                node = node.next[index];
            }
            node.q++;
        }

        public void delete(String word){
            if (search(word) > 0){
                char[] chars = word.toCharArray();
                Node1 node = new Node1();
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i] - 'a';
                    if (--node.next[index].p == 0){
                        node.next[index] = null;
                        return;
                    }
                    node = node.next[index];
                }
                node.q--;
            }
        }

        public int search(String word){
            if (word == null){
                return 0;
            }

            char[] chars = word.toCharArray();
            Node1 node = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.next[index] == null){
                    return 0;
                }
                node = node.next[index];
            }
            return node.q;
        }

        public int prefixNumber(String pre){
            if (pre == null){
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node1 node = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.next[index] == null){
                    return 0;
                }
                node = node.next[index];
            }
            return node.q;
        }
    }
}
