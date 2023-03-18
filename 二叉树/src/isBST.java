public class isBST {

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val){
            this.val = val;
        }
    }

    public static class Info{
        public boolean isBSt;
        public int max;
        public int min;

        public Info(boolean isBST,int max,int min){
            this.isBSt = isBST;
            this.max = max;
            this.min = min;
        }
    }

    //  搜索二叉树
    public static Info isBST(TreeNode head){
        if (head == null){
            return null;
        }
        Info leftInfo = isBST(head.left);
        Info rightInfo = isBST(head.right);

        int max = head.val;
        int min = head.val;
        boolean isBST = true;

        if (leftInfo != null){
            max = Math.max(max,leftInfo.max);
        }

        if (rightInfo != null){
            max = Math.max(max,rightInfo.max);
        }

        if (leftInfo != null){
            min = Math.min(min,leftInfo.min);
        }

        if (rightInfo != null){
            max = Math.min(min,rightInfo.min);
        }

        if (leftInfo != null && !leftInfo.isBSt){
            isBST = false;
        }
        if(rightInfo != null && !rightInfo.isBSt){
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max >= head.val){
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= head.val){
            isBST = false;
        }
        return new Info(isBST,max,min);
    }

    public static Info isBST2(TreeNode head){
        if (head == null){
            return new Info(true,Integer.MIN_VALUE,Integer.MAX_VALUE);
        }
        Info leftInfo = isBST2(head.left);
        Info rightInfo = isBST2(head.right);

        int min = head.val;
        int max= head.val;
        boolean isBST = true;

        max = Math.max(max,leftInfo.max);
        min = Math.min(min,rightInfo.min);

        if (!leftInfo.isBSt || !rightInfo.isBSt){
            isBST = false;
            return new Info(isBST,max,min);
        }


        if (head.val <= leftInfo.max){
            isBST = false;
            return new Info(isBST,max,min);
        }
        if (head.val >= rightInfo.min){
            isBST = false;
            return new Info(isBST,max,min);
        }
        return new Info(isBST,max,min);
    }

    //  写个对数器
    //  生成随机树
    public static TreeNode generateRandomBST(int maxLevel,int maxValue){
        return generate(1,maxLevel,maxValue);
    }

    private static TreeNode generate(int level, int maxLevel, int maxValue) {
//        if (Math.random() < 0.5 || level > maxLevel){
//            return null;
//        }
        if (level > maxLevel){
            return null;
        }
        TreeNode treeNode = new TreeNode((int)(Math.random() * maxValue));
        treeNode.left = generate(level + 1,maxLevel,maxValue);
        treeNode.right = generate(level + 1,maxLevel,maxValue);
        return treeNode;
    }

    //  两棵树是否相同
    private static boolean isSameValueStructure(TreeNode head1,TreeNode head2){
        if (head1 == null && head2 == null){
            return true;
        }
        if (head1 != null && head2 == null){
            return false;
        }
        if (head1 == null && head2 != null){
            return false;
        }
        if (head1.val != head2.val){
            return false;
        }
        return isSameValueStructure(head1.left,head2.left) && isSameValueStructure(head2.right,head2.right);
    }

//      打印二叉树
    public static void printTree(TreeNode head){
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    private static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null){
            return;
        }
        //  打印右树
        printInOrder(head.right,height + 1,"v",len);
        String val = to + head.val + to;
        //  中树
        int lenM = val.length();
        //  左树，这边的Len都是指的长度
        int lenL = (len - lenM) / 2;
        //  右树
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        //  ^是左树的标志
        printInOrder(head.left, height + 1, "^", len);

    }
//
    //  获得对应长度的空字符串
    //  注意这里的space是有一个大小的字符串
    public static String getSpace(int num){
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }


    public static void main(String[] args) {
        int maxLevel = 3;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (isBST2(head).isBSt){
                printTree(head);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
