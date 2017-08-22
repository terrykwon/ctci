package com.terrykwon.stacks;

/**
 * Implement a sorted stack where the smallest elements are at the top.
 */
public class SortStack {

    /**
     * Uses a second stack (stack2) to sort.
     * Inserts items from stack1 to stack2 one at a time, and ensures sort order by
     * popping elements from stack2 until the item to insert is at its right place.
     *
     * Time complexity of sort(): O(N^2), specifically (1 + 2 + ... + N)
     * Space complexity of sort(): O(N)
     */
    private static class CustomStack {
        private LinkedStack<Integer> stack1;
        private LinkedStack<Integer> stack2;

        CustomStack() {
            stack1 = new LinkedStack<>();
            stack2 = new LinkedStack<>();
        }

        void push(int i) {
            stack1.push(i);
        }

        Integer pop() {
            return stack1.pop();
        }

        void sort() {
            while (stack1.size() > 0) {
                Integer temp = stack1.pop();

                while (stack2.size() > 0 && temp > stack2.top()) {
                    stack1.push(stack2.pop());
                }

                stack2.push(temp);
            }

            LinkedStack<Integer> tempStack = stack2;
            stack1 = stack2;
            stack2 = tempStack;
        }

        void print() {
            stack1.print();
        }

    }


    public static void main(String[] args) {
        CustomStack stack = new CustomStack();

        stack.push(5);
        stack.push(3);
        stack.push(3);
        stack.push(7);
        stack.push(1);
        stack.push(2);
        stack.push(4);
        stack.push(9);
        stack.push(6);

        stack.print();
        stack.sort();
        stack.print();
    }
}
