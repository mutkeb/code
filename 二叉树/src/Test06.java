//  先序反序列化的非递归写法，以及对数器

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test06 {

    // 二叉树节点定义
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.val = data;
        }
    }

    // 先序遍历序列化，非递归版
    public static Queue<String> preSerial(TreeNode head) {
        Queue<String> ans = new LinkedList<>();
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                if (head == null) {
                    ans.add(null);
                } else {
                    ans.add(String.valueOf(head.val));
                }
                if (head != null) {
                    stack.push(head.right);
                    stack.push(head.left);
                }
            }
        }
        return ans;
    }

    // 先序遍历反序列化，非递归版
    public static TreeNode preBuild(Queue<String> preList) {
        if (preList == null || preList.size() < 0){
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(preList.poll()));
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        // 非常关键的表，用来标记一个节点有没有建立过左孩子
        HashSet<TreeNode> finishList = new HashSet<>();
        while (!stack.isEmpty()){
            TreeNode father = stack.pop();
            if (!finishList.contains(father)){
                String left = preList.poll();
                if (left == null){
                    father.left = null;
                }else {
                    TreeNode leftNode = new TreeNode(Integer.valueOf(left));
                    father.left = leftNode;
                    finishList.add(father);
                    stack.add(father);
                    stack.add(father.left);
                }
            }else {
                String right = preList.poll();
                if (right != null) {
                    father.right = new TreeNode(Integer.valueOf(right));
                }
                if (father.right != null) {
                    stack.push(father.right);
                }
            }
        }
        return head;
    }

    // 二叉树按层序列化
    // 为了验证
    public static Queue<String> levelSerial(TreeNode head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.val));
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll(); // head 父 子
                if (head.left != null) {
                    ans.add(String.valueOf(head.left.val));
                    queue.add(head.left);
                } else {
                    ans.add(null);
                }
                if (head.right != null) {
                    ans.add(String.valueOf(head.right.val));
                    queue.add(head.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    // 二叉树按层反序列化
    // 为了验证
    public static TreeNode levelBuild(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        TreeNode head = generateNode(levelList.poll());
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (head != null) {
            queue.add(head);
        }
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    // 生成随机树
    // 为了验证
    public static TreeNode generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new TreeNode(Integer.valueOf(val));
    }

    // 生成随机树
    // 为了验证
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // 生成随机树
    // 为了验证
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    //  自己写一遍
    public static TreeNode generate1(int level,int maxLevel,int maxValue){
        if (level > maxLevel || Math.random() < 0.5){
            return null;
        }
        TreeNode head = new TreeNode((int)(Math.random() * maxValue));
        head.left = generate(level + 1,maxLevel,maxValue);
        head.right = generate(level + 1,maxLevel,maxValue);
        return head;
    }

    // 验证两棵树是不是一样的数值结构
    // 为了验证
    public static boolean isSameValueStructure(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.val != head2.val) {
            return false;
        }
        //  只有父节点对上了，才会看子节点
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // 打印二叉树
    // 为了验证
    public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    // 打印二叉树
    // 为了验证
    public static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        //  v是右树的标志
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.val + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        //  ^是左树的标志
        printInOrder(head.left, height + 1, "^", len);
    }

    // 打印二叉树
    // 为了验证
    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    // 对数器主函数
    // 为了验证
    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        TreeNode head = generateRandomBST(maxLevel, maxValue);
//        Queue<String> levels = levelSerial(head);
//        TreeNode head1 = levelBuild(levels);
        printTree(head);
//        for (int i = 0; i < testTimes; i++) {
//            TreeNode head = generateRandomBST(maxLevel, maxValue);
//            Queue<String> levels = levelSerial(head);
//            TreeNode head1 = levelBuild(levels);
//            Queue<String> pres = preSerial(head);
//            TreeNode head2 = preBuild(pres);
////            printTree(head);
//            if (!isSameValueStructure(head1, head2)) {
//                System.out.println("出错了!");
//                printTree(head);
//                break;
//            }
//        }
        System.out.println("测试结束");

    }

}
