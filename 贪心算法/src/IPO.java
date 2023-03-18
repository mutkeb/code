import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class IPO {

    static class Program{
        public int profit;
        public int capital;
        public Program(int profit,int capital){
            this.profit = profit;
            this.capital = capital;
        }
    }

    static class MinCostComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.capital - o2.capital;
        }
    }

    static class MaxProfitComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

    public static int findMaximizedCapital(int K,int W,int[] Profits,int[] Capital){
        PriorityQueue<Program> minCost = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfit = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < Profits.length; i++) {
            minCost.add(new Program(Profits[i],Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!minCost.isEmpty() && minCost.peek().capital <= W){
                maxProfit.add(minCost.poll());
            }
            if (maxProfit.isEmpty()){
                return W;
            }
            W += maxProfit.poll().profit;
        }
        return W;
    }

    //  暴力解法

    public static int findMaximizedCapital2(int K,int W,int[] Profits,int[] Capital){
        if (Profits == null || Profits.length < 1){
            return W;
        }
        if (Capital == null || Capital.length < 1){
            return W;
        }
        Program[] programs = new Program[Profits.length];
        for (int i = 0; i < Profits.length; i++) {
            programs[i] = new Program(Profits[i],Capital[i]);
        }
        return progress(programs,W,K);
    }

    public static int progress(Program[] programs,int W,int K){
        if (K == 0){
            return W;
        }
        int max = W;
        //  表示能够做的项目的列表
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].capital <= W){
                max = Math.max(max,progress(removeAndGetNewArray(programs,i),W+programs[i].profit,K--));
            }
        }
        return max;
    }

    //  移除指定位置的数生成新的数组
    public static Program[] removeAndGetNewArray(Program[] programs,int index){
        Program[] copy = new Program[programs.length-1];
        int copyIndex = 0;
        for (int i = 0; i < programs.length; i++) {
            if (i != index){
                copy[copyIndex++] = programs[i];
            }
        }
        return copy;
    }

    //  对数器
    public static int[] generateArray(int maxValue,int maxSize){
        int[] array = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            array[i] = (int)(Math.random() * maxValue + 1);
        }
        return array;
     }

    public static int[] copyArray(int[] array){
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    public static void main(String[] args) {
        int maxSize = 2;
        int maxValue = 8;
        int testTimes = 10000;
        int K = 3;
        int W = 2;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] profits = generateArray(maxValue, maxSize);
            int[] capitals = generateArray(maxValue,maxSize);
            int ans1 = findMaximizedCapital(K, W, profits,capitals);
            int[] copyProfits = copyArray(profits);
            int[] copyCapitals = copyArray(capitals);
            int ans2 = findMaximizedCapital2(K, W, copyProfits,copyCapitals);
            if (ans1 != ans2){
                System.out.println("error");
                System.out.println("ans1=" + ans1);
                System.out.println("ans2=" + ans2);
                System.out.println("数组打印");
                for (int j = 0; j < profits.length; j++) {
                    System.out.println("profits=" + profits[j] + ",capital=" + capitals[j]);
                }
                break;
            }
        }
        System.out.println("test end");
    }
}
