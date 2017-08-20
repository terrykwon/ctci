package com.terrykwon.stacks;

/**
 * Implement a data structure that creates a new stack once the previous stack exceeds capacity.
 */
public class StackOfPlates {

    /**
     * A stack of stacks.
     * When the top stack is filled, creates a new stack.
     */
    private static class StackSet<E> {
        public static final int MAX_CAPACITY = 5;

        private LinkedStack<LinkedStack<E>> stackSet = new LinkedStack<>(); // A stack of stacks

        StackSet() {
            LinkedStack<E> stack = new LinkedStack<>();
            stackSet.push(stack);
        }

        void push(E element) {
            LinkedStack<E> topStack;

            if (stackSet.top().size() < 5) {
                topStack = stackSet.top();
            } else {
                topStack = new LinkedStack<>();
                stackSet.push(topStack);
            }

            topStack.push(element);
        }

        E pop() {
            LinkedStack<E> topStack = stackSet.top();
            E element = topStack.pop();

            if (topStack.size() == 0) {
                // Empty top stack, so get rid of it.
                stackSet.pop();
            }

            return element;
        }

        void printStacks() {
            System.out.println(stackSet.size());
            int size = stackSet.size();
            for (int i = 0; i < size; i++) {
                System.out.print("Stack " + i + ": ");
                stackSet.pop().print();
            }
        }

    }



    public static void main(String[] args) {
        StackSet<Integer> stackSet = new StackSet<>();

        for (int i = 0; i < 25; i++) {
            stackSet.push(i);
        }

        for (int j = 0; j < 7; j++) {
            stackSet.pop();
        }

        stackSet.printStacks();

    }
}
