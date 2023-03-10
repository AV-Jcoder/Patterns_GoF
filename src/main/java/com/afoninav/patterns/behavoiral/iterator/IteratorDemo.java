package com.afoninav.patterns.behavoiral.iterator;

/**
 * Пример Шаблона Iterator.
 */
public class IteratorDemo {
    public static void main(String[] args) {
        MyArrayList list = new MyArrayList(5);
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(8);
        System.out.println(list);


        MyIterator iterator = list.getIterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 6)
                iterator.remove();
        }
        System.out.println(list);

    }
}

interface MyIterator {
    int next();
    boolean hasNext();
    int remove();
}

interface Iterable {
    MyIterator getIterator();
}

class MyArrayList implements Iterable {
    private final int[] array;
    private final int size;
    private int curSize;

    public MyArrayList(int size) {
        this.array = new int[size];
        this.size = size;
        this.curSize = 0;
    }

    public void add(int data) {
        if (curSize >= size) {
            System.out.println("Index out of bounds exception");
            return;
        }
        this.array[curSize++] = data;
    }

    public void remove(int index) {
        if (index < 0 || index >= curSize) {
            System.out.println("Index out of bounds exception");
            return;
        } else if (!isEmpty()) {
            shiftToLeftFromIndex(index);
            curSize--;
        }
    }

    private void shiftToLeftFromIndex(int index) {
        for (int i = index; i < size-1; i++) {
            array[i] = array[i+1];
        }
    }

    @Override
    public String toString() {
        if (!isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < curSize; i++) {
                sb.append('[').append(array[i]).append(']').append(' ');
            }
            return sb.toString();
        } else {
            return "List is empty";
        }
    }

    public boolean isEmpty() {
        return curSize == 0;
    }


    @Override
    public MyIterator getIterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements MyIterator {
        int currentPos = 0;
        int lastReturned = -1;

        @Override
        public int next() {
            lastReturned = currentPos;
            return array[currentPos++];
        }

        @Override
        public boolean hasNext() {
            return currentPos < curSize;
        }

        @Override
        public int remove() {
            int removed = array[lastReturned];
            currentPos = lastReturned;
            shiftToLeftFromIndex(currentPos);
            curSize--;
            return removed;
        }
    }
}


