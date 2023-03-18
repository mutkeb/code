import java.util.Stack;

public class TwoStacksQueue {

    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public TwoStacksQueue(){
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    public void pushToPop(){
        if (stackPop.empty()){
            while (!stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }
    }

    public void add(int pushInt){
        stackPush.add(pushInt);
        pushToPop();
    }

    public int poll(){
        if (stackPush.empty() && stackPop.empty()){
            throw new RuntimeException("Queue is empty");
        }
        pushToPop();
        return stackPop.pop();
    }

    public int peek(){
        if (stackPush.empty() && stackPop.empty()){
            throw new RuntimeException("Queue is empty");
        }
        pushToPop();
        return stackPop.peek();
    }

}
