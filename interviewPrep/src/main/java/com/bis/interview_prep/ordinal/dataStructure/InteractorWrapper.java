package com.bis.interview_prep.ordinal.dataStructure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Given an iterator with methods next() and hasNext(), create a wrapper iterator,
 * PeekableInterface, which also implements peek(). peek shows the next element that would be returned on next().
 * <p>
 * Here is the interface:
 * <p>
 * class PeekableInterface(object):
 * def __init__(self, iterator):
 * pass
 * <p>
 * def peek(self):
 * pass
 * <p>
 * def next(self):
 * pass
 * <p>
 * def hasNext(self):
 * pass
 **/
public class InteractorWrapper {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        PeekIterator<Integer> peekIterator = new PeekIterator<>(list.iterator());

        int i = 0;
        while (peekIterator.hasNext()) {
            if (i % 2 == 0) {
                System.out.println(peekIterator.getPeek());
            }
            System.out.println(peekIterator.next());
            i++;
        }
    }


}

class PeekIterator<T> {
    private Iterator<T> iteractor;
    private T peek;

    public PeekIterator(Iterator<T> iteractor) {
        this.iteractor = iteractor;
    }

    //get Peek of an iterator
    public T getPeek() {
        if (peek == null) {
            peek = iteractor.next();
        }

        return peek;
    }

    //get the next
    public T next() {
        T res = null;
        if (peek != null) {
            res = peek;
            peek = null;
        } else {
            res = iteractor.next();
        }
        return res;
    }

    //check if it has next
    public boolean hasNext() {
        return iteractor.hasNext();
    }

}