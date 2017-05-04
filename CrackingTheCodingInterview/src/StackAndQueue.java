import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by lanceji on 5/3/17.
 */
public class StackAndQueue {
    public static void main(String[] args){
        StackAndQueue saq = new StackAndQueue();
        Stack temp = new Stack();
        temp.push(8);
        temp.push(5);
        temp.push(1);
        temp.push(9);
        temp.push(12);
        temp.push(5);
        temp.push(4);
        temp.push(7);
        temp.push(2);
        temp.push(18);
        temp = saq.sortStack(temp);
        while(!temp.isEmpty()){
            System.out.println(temp.pop());
        }

    }


    class stackWithMin extends Stack<Integer> {
        Stack<Integer> minStack;
        public stackWithMin(){
            minStack = new Stack<Integer>();
        }

        public void push(int value){
            super.push(value);
            if(value <= min()){
                minStack.push(value);
            }
        }

        public Integer pop(){
            int value = super.pop();
            if(value == minStack.peek()){
                minStack.pop();
            }
            return value;
        }

        public Integer peek(){
            return super.peek();
        }

        public int min(){
            if(minStack.isEmpty())
                return Integer.MAX_VALUE;
            else
                return minStack.peek();
        }
    }

    class StackNode{
        Stack<Integer> stack;
        StackNode next;
        public StackNode(){
            stack = new Stack<Integer>();
            next = null;
        }
    }

    class setOfStacks{
        StackNode head;
        StackNode tail;
        int threshold;
        public void push(int value){
            if(tail.stack != null && !isFull(tail.stack)){
                tail.stack.push(value);
            }else{
                StackNode next = new StackNode();
                next.stack.push(value);
                tail.next = next;
                tail = next;
            }
        }

        public int pop(){
            if(tail.stack == null)
                throw new EmptyStackException();
            int result = tail.stack.pop();
            if(tail.stack.size()==0){
                StackNode cur = head;
                while(cur.next != tail){
                    cur = cur.next;
                }
                cur.next = null;
            }
            return result;
        }

        public setOfStacks(int threshold){
            head = new StackNode();
            tail = head;
            this.threshold = threshold;
        }

        public boolean isFull(Stack<Integer> stack){
            return stack.size() == threshold;
        }
    }

    class setOfStacksUsingArrayList{
        ArrayList<Stack<Integer>> stacks;
        int threshold;
        public setOfStacksUsingArrayList(int threshold){
            stacks = new ArrayList<Stack<Integer>>();
            this.threshold = threshold;
        }

        public boolean isFull(Stack<Integer> stack){
            return stack.size() == this.threshold;
        }

        public Stack<Integer> getLastStack(){
            if(stacks.size()==0)
                return null;
            return stacks.get(stacks.size()-1);
        }

        public void push(int value){
            Stack<Integer> last = getLastStack();
            if(last != null && !isFull(last)){
                last.push(value);
            }else{
                Stack<Integer> newStack = new Stack<Integer>();
                newStack.push(value);
                stacks.add(newStack);
            }
        }

        public int pop(){
            Stack<Integer> last = getLastStack();
            if(last==null)
                throw new EmptyStackException();
            int result = last.pop();
            if(last.size()==0)
                stacks.remove(stacks.size()-1);
            return  result;
        }
    }

    class myQueue<T>{//Queue with two stacks
        Stack<T> stack1;
        Stack<T> stack2;

        public myQueue(){
            stack1 = new Stack<T>();
            stack2 = new Stack<T>();
        }

        public void enqueue(T value){
            stack1.push(value);
        }

        public T dequeue(){
            shiftStack();
            return stack2.pop();
        }

        public T peek(){
            shiftStack();
            return stack2.peek();
        }

        public void shiftStack(){
            if(stack2.isEmpty()){
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
        }

    }

    public Stack<Integer> sortStack(Stack<Integer> input){
        if(input == null || input.isEmpty())
            return null;
        Stack<Integer> tempStack = new Stack();
        while(!input.isEmpty()){
            int temp = input.pop();
            while(!tempStack.isEmpty() && temp > tempStack.peek()){
                input.push(tempStack.pop());
            }
            tempStack.push(temp);
        }

        return tempStack;
    }

    class AnimalShelter{
        abstract class Animal{
            
        }
    }
}
