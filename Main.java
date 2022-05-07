package Lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(new Student("Timur", Arrays.asList(new Course("Mechanics"),
                new Course("Geology"), new Course("Chemistry"))));
        studentsList.add(new Student("Polina", Arrays.asList(new Course("Mathematics"),
                new Course("Psychology"), new Course("Chemistry"), new Course("Biology"))));
        studentsList.add(new Student("Lubov", Arrays.asList(new Course("History"),
                new Course("Geology"), new Course("Psychology"), new Course("Management"), new Course("Jurisprudence"))));
        studentsList.add(new Student("Denis", Arrays.asList(new Course("Geology"),
                new Course("Mathematics"))));
        studentsList.add(new Student("Vladislav", Arrays.asList(new Course("History"),
                new Course("Management"))));

        //функция принимает список Студентов и возвращает список уникальных курсов, на кот. подписаны Студенты.
        System.out.println(studentsList.stream()
                .map(s -> s.getCourseList())
                .flatMap(f -> f.stream())
                .map(n -> n.getName())
                .collect(Collectors.toSet())

        );



        //ф-ция принимает список Студентов и
        //возвращает список из 3-х самых любознательных (у кот. больше всего кол-во курсов)
        System.out.println(
                studentsList.stream()
                        .sorted((s1, s2) -> s2.getCourseList().size() - s1.getCourseList().size())
                        .limit(3)
                        .collect(Collectors.toList())
        );

        //ф-ция приимает список Студентов и экземпляр Курсов,
        //а возвращает - список студентов, кот. посещяют этот курс
        Course course = new Course("Mathematics");
        System.out.println(studentsList.stream()
                .filter(s -> s.getCourseList().contains(course))
                .collect(Collectors.toList())
        );
    }
}
