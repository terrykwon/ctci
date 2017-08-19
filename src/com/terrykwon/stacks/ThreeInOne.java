package com.terrykwon.stacks;

/**
 * Use a single array to implement three stacks.
 */
public class ThreeInOne {

    /**
     * Partitions an array into three equal parts,
     * and uses a private class that stores the start and end of its allocation.
     *
     * Resizing is not implemented; attempting to push beyond a stack's allocated space will throw an error.
     */
    private static class TripleStack<E> {
        private E[] data;
        private Stack stack1, stack2, stack3;

        TripleStack(int size) {
            data = (E[]) new Object[size];

            stack1 = new Stack(0, size / 3);
            stack2 = new Stack(size / 3, size / 3 * 2);
            stack3 = new Stack(size / 3 * 2, size);
        }

        void printStacks() {
            System.out.print("Stack 1: ");
            stack1.print();
            System.out.print("Stack 2: ");
            stack2.print();
            System.out.print("Stack 3: ");
            stack3.print();
        }

        Stack get(int stackNum) {
            switch (stackNum) {
                case 1:
                    return stack1;
                case 2:
                    return stack2;
                case 3:
                    return stack3;
                default:
                    return null;
            }
        }

        private class Stack {
            int top;
            int start;
            int end;
            int size = 0;

            Stack(int start, int end) {
                this.start = start;
                this.top = start;
                this.end = end;
                size = 0;
            }

            E pop() {
                if (size == 0) {
                    return null;
                }

                E temp = data[top];
                data[top] = null;
                top -= 1;
                size -= 1;
                return temp;
            }

            void push(E element) throws IllegalStateException {

                if (top + 1 == end) {
                    throw new IllegalStateException("Stack is full");
                }

                top += 1;

                data[top] = element;
                size += 1;
            }

            void print() {
                for (int i = start + 1; i <= top; i++) {
                    System.out.print(data[i] + " ");
                }
                System.out.println();
            }

        }

    }

    public static void main(String[] args) {
        TripleStack<Integer> tStack = new TripleStack<>(12);
        tStack.get(1).push(1);
        tStack.get(2).push(2);
        tStack.get(3).push(3);

        tStack.printStacks();
    }
}
