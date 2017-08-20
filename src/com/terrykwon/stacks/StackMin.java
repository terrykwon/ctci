package com.terrykwon.stacks;

/**
 * Design a stack that has a function <code>min()</code> that returns the minimum element.
 */
public class StackMin {

    /**
     * Uses two stacks: one for the data, and one that tracks the min.
     * On every <code>push()</code>, the element is compared to the current min (the top of the min stack),
     * and if it is smaller or equal, it is pushed to the min stack as well.
     *
     * Time complexity (of push, pop, min operations): O(1)
     * Space complexity: O(N)
     */
    private static class CustomStack<E extends Comparable<E>> {

        private LinkedStack<E> data;
        private LinkedStack<E> min;

        CustomStack() {
            data = new LinkedStack<>();
            min = new LinkedStack<>();
        }

        void push(E element) {
            data.push(element);

            if (min.size() == 0) {
                min.push(element);
            } else if (element.compareTo(min.top()) <= 0) {
                // If current element is smaller, push it to min stack.
                min.push(element);
            }

        }

        E pop() {
            if (data.top() == min.top()) {
                min.pop();
            }
            return data.pop();
        }

        E min() {
            return min.top();
        }

        void print() {
            data.print();
        }

    }


    public static void main(String[] args) {

        CustomStack<Integer> stack = new CustomStack<>();

        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(2);
        stack.push(7);

        stack.print();
        System.out.println(stack.min()); // 1

        stack.pop();
        stack.pop();
        stack.pop();
        stack.print();
        System.out.println(stack.min()); // 2

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.print();
        System.out.println(stack.min()); // null

    }
}
