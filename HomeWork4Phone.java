
/**
 * Java Core. Homework #4
 *
 * @author Bakeshko Daria
 * @version 11.04.22
 *
 */
/*
2. Написать простой класс «Телефонный Справочник», который хранит в себе список фамилий и телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие с пользователем через консоль и т.д). Консоль использовать только для вывода результатов проверки телефонного справочника.
 */

import java.util.ArrayList;
import java.util.HashMap;

class HomeWork4Phone {
    public static void main(String[] args) {

        HomeWork4Phone phonebookHashMap = new HomeWork4Phone();
        phonebookHashMap.add("Popov", "55-140");
        phonebookHashMap.add("Zorin", "10-741");
        phonebookHashMap.add("Gorin", "33-505");
        phonebookHashMap.add("Antipov", "77-841");
        phonebookHashMap.add("Zorin", "4-30-02");
        phonebookHashMap.add("Popov", "2-10-70");

        System.out.println(phonebookHashMap.get("Popov"));
        System.out.println(phonebookHashMap.get("Gorin"));
        System.out.println(phonebookHashMap.get("Zorin"));
    }

    private HashMap<String, ArrayList<String>> phonebookHashMap = new HashMap<>();

    void add(String surname, String phoneNumber) {
        ArrayList<String> phoneNumbersList = phonebookHashMap.getOrDefault(surname, new ArrayList<>());
        phoneNumbersList.add(phoneNumber);
        phonebookHashMap.put(surname, phoneNumbersList);
    }

    ArrayList<String> get(String surname) {
        return phonebookHashMap.get(surname);
    }

}
