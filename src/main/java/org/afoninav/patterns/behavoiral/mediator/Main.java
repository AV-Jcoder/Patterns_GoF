package com.afoninav.patterns.behavoiral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример Шаблона Mediator(Посредник)
 */
class Main {
    public static void main(String[] args) {
        TextChat chat = new TextChat();
        Client admin = new Admin(chat, "Admin");
        Client client1 = new User(chat,"User_1");
        Client client2 = new User(chat,"User_2");
        Client client3 = new User(chat,"User_3");
        Client client4 = new User(chat,"User_4");
        Client client5 = new User(chat,"User_5");

        chat.setAdmin(admin);
        chat.addUserToChat(client1);
        chat.addUserToChat(client2);
        chat.addUserToChat(client3);
        chat.addUserToChat(client4);

        client1.sendMessage("Hello everyone !");
        admin.sendMessage("Welcome to my chat!");

    }
}

/**
 * Чат
 */
interface Chat {
    void sendMessage(String text, Client sender);
}


/**
 * Клиент
 */
interface Client {
    void sendMessage(String text);
    void getMessage(String text);

}

/**
 * Чат Импл.
 */
class TextChat implements Chat {
    private Client admin;
    private List<Client> users = new ArrayList<>();

    public void setAdmin(Client admin) {
        this.admin = admin;
    }

    void addUserToChat(Client client) {
        users.add(client);
    }

    @Override
    public void sendMessage(String text, Client sender) {
        for (Client user : users) {
            if (user != sender)
            user.getMessage(text);
        }
        admin.getMessage(text);
    }
}

/**
 * Клиент Импл №1.
 */
class Admin implements Client {
    private Chat chat;
    private String name;

    public Admin(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    @Override
    public void sendMessage(String text) {
        this.chat.sendMessage(text, this);
    }

    @Override
    public void getMessage(String text) {
        System.out.printf("%s, получено соообщение: %s\n", this.name, text);
    }
}

/**
 * Клиент Импл №2.
 */
class User implements Client {
    private Chat chat;
    private String name;

    public User(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    @Override
    public void sendMessage(String text) {
        chat.sendMessage(text, this);
    }

    @Override
    public void getMessage(String text) {
        System.out.printf("%s, получено соообщение: %s\n", this.name, text);
    }
}
