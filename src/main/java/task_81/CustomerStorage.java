package task_81;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Неверный формат ввода. \n"
                    + "для справки наберите help");
        }

        Pattern patternEmail = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}$");
        Matcher matcherEmail = patternEmail.matcher(components[2]);
        if (!matcherEmail.find()) {
            throw new IllegalArgumentException("Неверный формат eMail");
        }

        Pattern patternPhoneNumber = Pattern.compile("\\+7[0-9]{10}$");
        Matcher matcherPhoneNumber = patternPhoneNumber.matcher(components[3]);
        if (!matcherPhoneNumber.find()) {
            throw new IllegalArgumentException("Неверный формат номера телефона");
        }

        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        if (storage.values().isEmpty()) {
            throw new IllegalArgumentException("Выводить пока нечего");
        }

        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {

        if (name.split("\\s+").length != 2) {
            throw new IllegalArgumentException("Неверный формат имени для удаления - смотри help");
        }

        if (storage.containsKey(name)) {
            storage.remove(name);
        } else {
            throw new IllegalArgumentException("Такого человека нет в списке");
        }
    }

    public int getCount() {
        return storage.size();
    }
}