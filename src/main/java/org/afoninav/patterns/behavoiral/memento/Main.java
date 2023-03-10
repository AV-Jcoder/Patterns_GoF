package com.afoninav.patterns.behavoiral.memento;

import java.util.*;

/**
 * Пример шаблона Memento(Snapshot)
 */
public class Main {
    public static void main(String[] args) {

        StateHolder repository = new StateHolder();
        AddressBook book = new AddressBook();

        book.addContact("Ivanov Ivan");
        System.out.println(book);

        System.out.println("Do snapshot\n");
        repository.push(book.getSnapshot());

        book.addContact("Petrov Petr");
        book.addContact("Sergeev Sergej");
        System.out.println(book);

        System.out.println("Rollback to last snapshot\n");
        book.setSnapshot(repository.pop());

        System.out.println(book);
    }
}

class AddressBook {
    private List<String> contacts = new ArrayList<>();
    private Date lastUpdate;
    private int snapshotVersion;

    public void addContact(String contact) {
        if (!contacts.contains(contact)) {
            contacts.add(contact);
        }
        this.lastUpdate = new Date();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Last update: " + this.lastUpdate + "\n");
        sb.append("Snapshot: ver." + this.snapshotVersion + "\n");
        for (String s : contacts) {
            sb.append('[').append(s).append("]\n");
        }
        return sb.toString();
    }

    public Object getSnapshot() {
        return new AddressBookSnapshot(new LinkedList<>(contacts), lastUpdate, snapshotVersion++);
    }

    public void setSnapshot(Object param) {
        if (param instanceof AddressBookSnapshot) {
            AddressBookSnapshot memento = (AddressBookSnapshot) param;
            contacts = memento.contacts;
            lastUpdate = memento.lastUpdate;
            snapshotVersion = memento.snapshotVersion;
        }
    }

    private class AddressBookSnapshot {
        private final List<String> contacts;
        private final Date lastUpdate;
        private final int snapshotVersion;

        private AddressBookSnapshot(List<String> contacts, Date lastUpdate, int snapshotVersion) {
            this.contacts = contacts;
            this.lastUpdate = lastUpdate;
            this.snapshotVersion = snapshotVersion;
        }
    }
}

class StateHolder {
    private Deque<Object> snapshots = new LinkedList<>();

    public void push(Object snapshot) {
        this.snapshots.push(snapshot);
    }

    public Object pop() {
        return snapshots.pop();
    }
}

