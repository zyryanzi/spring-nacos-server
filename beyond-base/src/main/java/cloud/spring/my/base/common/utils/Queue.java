package cloud.spring.my.base.common.utils;

import java.util.Stack;

/**
 * @ClassName: Queue
 * @Author: uray
 * @Date: 2022-03-03 16:00
 **/
public class Queue {

    private Integer peek;
    private Stack<Integer> fStack;
    private Stack<Integer> rStack;

    public Queue(Stack fStack, Stack rStack, Integer peek) {
        this.fStack = fStack;
        this.rStack = rStack;
        this.peek = peek;
    }

    public void push(Integer num) {
        if (fStack.empty()) {
            this.peek = num;
        }
        while (!rStack.empty()){
            fStack.push(rStack.pop());
        }
        fStack.push(num);
    }

    public Integer pop() {
        while (!fStack.empty()){
            rStack.push(fStack.pop());
        }
        Integer p = rStack.pop();
        this.peek = rStack.peek();
        while (!rStack.empty()){
            fStack.push(rStack.pop());
        }
        return p;
    }

    public Integer peek() {
        return this.peek;
    }

    public static void main(String[] args) {
        Queue queue = new Queue(new Stack(), new Stack(), null);
        queue.push(1);
        queue.push(3);
        queue.push(2);
        queue.push(4);
        System.out.println(queue.peek);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.push(9);
        System.out.println(queue.peek);
    }

}
