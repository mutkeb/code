import java.util.ArrayList;
import java.util.List;

public class Test4 {

    static class Tree{
        String val;
        List<Tree> sons;

        public Tree(String val){
            this.val = val;
        }
    }


    //  构建树
    public static List<Tree> buildTree(List<String[]> list){
        List<Tree> root = new ArrayList<>();
        List<Tree> parent = new ArrayList<>();
        for (String[] strings : list) {
            List<Tree> sons = new ArrayList<>();
            for (String string : strings) {
                Tree tree = new Tree(string);
                sons.add(tree);
            }

            if (root.isEmpty()){
                root = sons;
            }

            if (!parent.isEmpty()){
                for (Tree tree : parent) {
                    tree.sons = sons;
                }
            }

            parent = sons;

        }
        return root;
    }

    //  深度遍历树
    public static List<String> deepSearch(List<Tree> root){
        if (root == null){
            return null;
        }
        List<String> result = new ArrayList<>();
        for (Tree tree : root) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(tree.val);
            if (tree.sons != null){
                List<String> list = deepSearch(tree.sons);
                for (String string : list) {
                    StringBuilder last = new StringBuilder();
                    last.append(stringBuilder);
                    last.append(string);
                    result.add(last.toString());
                }
            }else {
                result.add(stringBuilder.toString());
            }
        }
        return result;
    }


    public static void main(String[] args) {
        List<String[]> list = new ArrayList<>();
        String[] str = {"a","b","cd"};
        String[] str1 = {"de"};
        String[] str2 = {"e","f"};

        list.add(str);
        list.add(str1);
        list.add(str2);

        List<Tree> trees = buildTree(list);
//        for (Tree tree : trees) {
//            System.out.println(tree.val);
//            for (Tree son : tree.sons) {
//                System.out.println(son.val);
//            }
//        }
        List<String> list1 = deepSearch(trees);
        String[] result = new String[list1.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list1.get(i);
        }
        for (String s : result) {
            System.out.println(s);
        }
    }
}
