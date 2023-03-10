package com.afoninav.patterns.structural.proxy;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Пример шаблона Proxy(заместитель)
 * из книги Применение шаблонов Java.
 * Экземпляр класса AddressBookProxy создает
 * объект класса AddressBooklmpl только в том случае,
 * когда это действительно необходимо — например,
 * когда пользователь вызывает метод getAllddresses, getAddress, save.
 */
public class Main {
    public static void main(String[] args) {
        String pathToFile = "/tmp/1.txt";
        AddressBookProxy proxy = new AddressBookProxy(pathToFile);
        proxy.add("London 123456");
        proxy.save();
    }
}


/**
 * Интерфейс с помощью которого обеспечивается
 * доступ к адресной книге.
 */
interface AddressBook {
    void add(String address);
    ArrayList<String> getAllAddresses();
    String getAddress(String description);
    void open();
    void save();
}

/**
 * Класс реализует адресную книгу.
 * Адреса добавляются в коллекцию,
 * а затем сохраняются на диск.
 */
class AddressBookImpl implements AddressBook {
    private File file;
    private ArrayList<String> addresses = new ArrayList<>();

    AddressBookImpl(String pathToFile) {
        this.file = new File(pathToFile);
    }

    @Override
    public void add(String address) {
        addresses.add(address);
    }

    @Override
    public ArrayList<String> getAllAddresses() {
        return addresses;
    }

    @Override
    public String getAddress(String description) {
        Iterator<String> iterator = addresses.iterator();
        while (iterator.hasNext()) {
            String address = iterator.next();
            if (address.equalsIgnoreCase(description)) {
                return address;
            }
        }
        return null;
    }

    @Override
    public void open() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String address = reader.readLine();
            while (address != null) {
                addresses.add(address);
                address = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            for (String address : addresses) {
                writer.write(address);
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * Получение данных из адресной книги может потребовать весьма длительного
 * времени (именно этим, по-видимому, объясняется повсеместная нелюбовь
 * пользователей к адресным книгам). Поэтому прокси-объект должен оттягивать, насколько это
 * возможно, момент создания реальной адресной книги. Конечно, вся ответственность
 * за создание адресной книги, лежит именно на прокси-объекте AddressBookProxy,
 * но выполнить эту операцию он должен только тогда, когда она действительно необходима.
 */
class AddressBookProxy implements AddressBook {
    String pathToFile;
    AddressBookImpl addressBook;
    ArrayList<String> localAddresses;

    public AddressBookProxy(String pathToFile) {
        this.pathToFile = pathToFile;
        this.localAddresses= new ArrayList<>();
    }

    @Override
    public void add(String address) {
        if (addressBook != null) {
            addressBook.add(address);
        } else if (!localAddresses.contains(address)){
            localAddresses.add(address);
        }
    }

    @Override
    public ArrayList<String> getAllAddresses() {
        if (addressBook == null) {
            open();
        }
        return addressBook.getAllAddresses();
    }

    @Override
    public String getAddress(String description) {
        // find in local memory
        if (!localAddresses.isEmpty()) {
            Iterator<String> iterator = localAddresses.iterator();
            while(iterator.hasNext()) {
                String address = iterator.next();
                if (address.equalsIgnoreCase(description)) {
                    return address;
                }
            }
        }
        // find in addressBook
        if (addressBook == null) {
            open();
        }
        return addressBook.getAddress(description);
    }

    @Override
    public void open() {
        addressBook = new AddressBookImpl(pathToFile);
        Iterator<String> iterator = localAddresses.iterator();
        while (iterator.hasNext()) {
            addressBook.add(iterator.next());
        }
    }

    @Override
    public void save() {
        if (addressBook != null) {
            addressBook.save();
        } else if (!localAddresses.isEmpty()) {
            open();
            //тут баг, если мы добавим в локальную книгу 1 адрес
            // и сохраним его, то перезапишутся все данные на диске.
            addressBook.save();
        }
    }
}

