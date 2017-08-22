package com.terrykwon.queues;

import com.terrykwon.linkedlists.DoublyLinkedList;

import java.util.Random;

/**
 * Design a queue for cats and dogs.
 */
public class AnimalShelter {

    private static class AnimalQueue {

        private DoublyLinkedList<Dog> dogList;
        private DoublyLinkedList<Cat> catList;

        AnimalQueue() {
            dogList = new DoublyLinkedList<>();
            catList = new DoublyLinkedList<>();
        }

        void enqueue(Dog dog) {
            dogList.addFirst(dog);
        }

        void enqueue(Cat cat) {
            catList.addFirst(cat);
        }

        Dog dequeDog() {
            return dogList.removeLast();
        }

        Cat dequeCat() {
            return catList.removeLast();
        }

        Animal dequeAny() {
            Random random = new Random();

            if (random.nextFloat() < 0.5) {
                return dequeCat();
            }

            return dequeDog();
        }

    }

    static abstract class Animal {
        String name;

        Animal(String name) {
            this.name = name;
        }
    }

    static class Dog extends Animal {

        Dog(String name) {
            super(name);
        }

    }

    static class Cat extends Animal {

        Cat(String name) {
            super(name);
        }

    }


    public static void main(String[] args) {

        AnimalQueue q = new AnimalQueue();

        q.enqueue(new Dog("Rover"));
        q.enqueue(new Dog("Spot"));
        q.enqueue(new Cat("Meowy"));
        q.enqueue(new Dog("Doggy"));
        q.enqueue(new Cat("Kittens"));

        System.out.println(q.dequeCat().name); // Meowy
        System.out.println(q.dequeDog().name); // Rover
        System.out.println(q.dequeAny().name); // Spot or Kittens

    }
}
