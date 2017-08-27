package com.terrykwon.trees;


import com.terrykwon.Position;
import java.util.Iterator;

/**
 * A tree based on positions.
 */
public interface Tree<E> extends Iterable<E> {

    Position<E> root();

    Position<E> parent() throws IllegalArgumentException;

    Iterable<Position<E>> children(Position<E> position) throws IllegalArgumentException;

    int numChildren(Position<E> position) throws IllegalArgumentException;

    boolean isInternal(Position<E> position) throws IllegalArgumentException;

    boolean isExternal(Position<E> position) throws IllegalArgumentException;

    boolean isRoot(Position<E> position) throws IllegalArgumentException;

    int size();

    boolean isEmpty();

    Iterator<E> iterator();

    Iterable<Position<E>> positions();

}
