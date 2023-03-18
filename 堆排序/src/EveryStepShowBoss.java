import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class EveryStepShowBoss {

    public static class Customer {
        private int id;
        private int buy;
        private int enterTime;

        public Customer(int i, int b, int o) {
            this.id = i;
            this.buy = b;
            this.enterTime = o;
        }
    }

    public static class WhosYourDaddy {
        private HashMap<Integer, Customer> customers;
        private HeapGreater<Customer> candHeap;
        private HeapGreater<Customer> daddyHeap;
        private final int daddyLimit;

        public WhosYourDaddy(int limit) {
            customers = new HashMap<Integer, Customer>();
            candHeap = new HeapGreater<>(new CandidateComparator());
            daddyHeap = new HeapGreater<>(new DaddyComparator());
            daddyLimit = limit;
        }
        // 当前处理i号事件，arr[i] -> id,  buyOrRefund
        public void operate(int time, int id, boolean buyOrRefund) {
            if (!buyOrRefund && !customers.containsKey(id)) {
                return;
            }

            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }

            Customer c = customers.get(id);
            if (buyOrRefund) {
                c.buy++;
            } else {
                c.buy--;
            }
            if (c.buy == 0) {
                customers.remove(id);
            }

            //  如果两边都不存在
            if (!candHeap.contains(c) && !daddyHeap.contains(c)){
                if (daddyHeap.size() < daddyLimit) {
                    c.enterTime = time;
                    daddyHeap.push(c);
                } else {
                    c.enterTime = time;
                    candHeap.push(c);
                }
            }else if (candHeap.contains(c)){
                //  候选区有，vip区没有
                if (c.buy == 0) {
                    daddyHeap.remove(c);
                } else {
                    daddyHeap.resign(c);
                }
            }else {
                if (c.buy == 0) {
                    daddyHeap.remove(c);
                } else {
                    daddyHeap.resign(c);
                }
            }
            daddyMove(time);
        }
        public List<Integer> getDaddies() {
            List<Customer> customers = daddyHeap.getAllElements();
            List<Integer> ans = new ArrayList<>();
            for (Customer c : customers) {
                ans.add(c.id);
            }
            return ans;
        }

        private void daddyMove(int time) {
            if (candHeap.isEmpty()) {
                return;
            }
            if (daddyHeap.size() < daddyLimit) {
                Customer p = candHeap.pop();
                p.enterTime = time;
                daddyHeap.push(p);
            } else {
                if (candHeap.peek().buy > daddyHeap.peek().buy) {
                    Customer oldDaddy = daddyHeap.pop();
                    Customer newDaddy = candHeap.pop();
                    oldDaddy.enterTime = time;
                    newDaddy.enterTime = time;
                    daddyHeap.push(newDaddy);
                    candHeap.push(oldDaddy);
                }
            }
        }
    }


    public static class CandidateComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? o2.buy - o1.buy : o1.enterTime - o2.enterTime;
        }
    }

    public static class DaddyComparator implements Comparator<Customer>{

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? o2.buy - o1.buy : o1.enterTime - o2.enterTime;
        }
    }

    //  暴力算法
    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k){
        HashMap<Integer,Customer> map = new HashMap();
        ArrayList<Customer> cands = new ArrayList<>();
        ArrayList<Customer> daddy = new ArrayList<>();
        //  奖励区的id名单，每一步都记录-
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            //  假如原先是0又发生退货就不用管这种情况
            if (!buyOrRefund && !map.containsKey(id)){
                ans.add(getCurAns(daddy));
                continue;
            }
            // 没有发生：用户购买数为0并且又退货了
            // 用户之前购买数是0，此时买货事件
            // 用户之前购买数>0， 此时买货
            // 用户之前购买数>0, 此时退货
            if (!map.containsKey(id)){
                map.put(id,new Customer(id,0,0));
            }
            //  买、卖
            Customer customer = map.get(id);
            if (buyOrRefund){
                customer.buy++;
            }else {
                customer.buy--;
            }

            //  退货的如果等于0后就要清除
            if (customer.buy == 0){
                map.remove(id);
            }
            //  如果从来没进过等待区或者VIP区
            if (!cands.contains(customer) && !daddy.contains(customer)){
                if (daddy.size() < k){
                    customer.enterTime = i;
                    daddy.add(customer);
                }else {
                    customer.enterTime = i;
                    cands.add(customer);
                }
            }

            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            cands.sort(new CandidateComparator());
            daddy.sort(new DaddyComparator());
            move(cands, daddy, k, i);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }

    //  候选区的和VIP区的进行调换
    private static void move(ArrayList<Customer> cands, ArrayList<Customer> daddy, int k, int i) {
        if (cands.isEmpty()) {
            return;
        }

        //  不满，找最大的一个，也就是0位置的
        if (daddy.size() < k){
            Customer c = cands.get(0);
            c.enterTime = i;
            daddy.add(c);
            cands.remove(0);
        }else{
            //  VIP区满了，就需要比较
            if (cands.get(0).buy > daddy.get(0).buy){
                Customer oldDaddy = daddy.get(0);
                Customer newDaddy = cands.get(0);
                cands.remove(newDaddy);
                daddy.remove(oldDaddy);
                newDaddy.enterTime = i;
                oldDaddy.enterTime = i;
                daddy.add(newDaddy);
                cands.add(oldDaddy);
            }
        }

    }

    public static List<Integer> getCurAns(ArrayList<Customer> daddy){
        List<Integer> ans = new ArrayList<>();
        for (Customer customer : daddy) {
            ans.add(customer.id);
        }
        return ans;
    }

    public static void cleanZeroBuy(ArrayList<Customer> arr) {
        List<Customer> noZero = new ArrayList<Customer>();
        for (Customer c : arr) {
            if (c.buy != 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        for (Customer c : noZero) {
            arr.add(c);
        }
    }
}
