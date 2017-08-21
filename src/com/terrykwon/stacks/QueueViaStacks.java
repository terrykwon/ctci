package com.terrykwon.stacks;

/**
 * Implement a queue using two stacks.
 */
public class QueueViaStacks {

    /**
     * Popping stack1 and pushing each value to stack2 inverts the stack.
     * Therefore, add elements onto stack1, and pop elements in stack2.
     * When stack2 is empty, "flip" stack1 onto stack2.
     *
     * Time complexity of add(): O(1)
     * Time complexity of remove(): O(N), but amortized because the flip from stack1 to stack2 is only needed
     * when stack2 is empty.
     */
    private static class Queue<E> {
        int size;
        private LinkedStack<E> stack1;
        private LinkedStack<E> stack2;

        Queue() {
            size = 0;
            stack1 = new LinkedStack<>();
            stack2 = new LinkedStack<>();
        }

        void add(E element) {
            stack1.push(element);
            size++;
        }

        E remove() {
            if (size == 0) {
                return null;
            }

            if (stack2.size() == 0) {
                while (stack1.size() > 0) {
                    stack2.push(stack1.pop());
                }
            }

            size--;

            return stack2.pop();
        }

    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();

        for (int i = 0; i < 10; i++) {
            q.add(i);
        }

        for (int j = 0; j < 5; j++) {
            System.out.println(q.remove()); // 0, 1, 2, 3, 4
        }

        for (int k = 10; k < 15; k++) {
            q.add(k);
        }

        for (int l = 5; l < 13; l++) {
            System.out.println(q.remove()); // 5, 6, ..., 12
        }
    }
}
