import java.util.ArrayList;
import java.util.List;

public class MaxHappy {

    public static class Employee{
        public int val;
        public List<Employee> nexts;
        Employee(int val){
            this.val = val;
            nexts = new ArrayList<>();
        }
    }

    public static class Info{
        //  头结点不来
        public int no;
        //  头结点来
        public int yes;
        public Info(int no,int yes){
            this.no = no;
            this.yes = yes;
        }
    }

    //  最大开心值
    public static Info process(Employee x){
        if (x == null){
            return new Info(0,0);
        }
        int no = 0;
        int yes = x.val;
        for (Employee next : x.nexts) {
            Info nextInfo = process(next);
            yes += nextInfo.no;
            no += Math.max(nextInfo.no,nextInfo.yes);
        }
        return new Info(no,yes);
    }
}
