import java.util.*;

public class TwoQueuesStack {

    public Queue<Integer> queue;
    public Queue<Integer> help;

    public TwoQueuesStack(){
        queue = new LinkedList<>();
        help = new LinkedList<>();
    }
    public void push(int pushInt){
        queue.offer(pushInt);
    }

    public int poll(){
        while (queue.size() > 1){
            help.offer(queue.poll());
        }
        Integer poll = queue.poll();
        Queue temp = new LinkedList();
        temp = queue;
        queue = help;
        help = temp;
        return poll;
    }

    public int peek(){
        while (queue.size() > 1){
            help.offer(queue.poll());
        }
        Integer poll = queue.poll();
        help.offer(poll);
        Queue temp = new LinkedList();
        temp = queue;
        queue = help;
        help = temp;
        return poll;
    }



}
