
/**
 * Java Core. Homework #4
 *
 * @author Bakeshko Daria
 * @version 11.04.22
 *
 */
/*
1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class HomeWork4Speach {
    public static void main(String[] args) {

        // srazy vivesti bez povtorov
        HashSet<String> stringHashSet = new HashSet<>(
                Arrays.asList("either", "the", "well", "was", "very", "deep", "or", "she", "fell", "very", "slowly",
                        "for", "she", "had", "plenty", "of", "time", "as", "she", "went", "down"));
        System.out.println(stringHashSet);

        // vivod s podschetom
        String[] aliceSpeech = { "either", "the", "well", "was", "very", "deep", "or", "she", "fell", "very", "slowly",
                "for", "she", "had", "plenty", "of", "time", "as", "she", "went", "down" };

        HashMap<String, Integer> unicStringHashMap = new HashMap<>();

        for (String unic : aliceSpeech) {
            if (unicStringHashMap.containsKey(unic)) {
                unicStringHashMap.put(unic, unicStringHashMap.get(unic) + 1);
            } else {
                unicStringHashMap.put(unic, 1);
            }
        }
        System.out.println(unicStringHashMap);
    }

}
