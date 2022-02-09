package pl.sda.java.adv.school;

import pl.sda.java.adv.school.model.Person;
import pl.sda.java.adv.school.model.Student;
import pl.sda.java.adv.school.model.Teacher;
import pl.sda.java.adv.school.util.PersonFirstNameLastNameComparator;

import java.util.*;
import java.util.function.Consumer;

public class MainDataClassesOverview {
    public static void main(String[] args) {


        Student student1 = new Student();
        student1.setFirstName("Michał");
        student1.setLastName("Zielińśki");

        Student student2 = new Student();
        student2.setFirstName("Zenon");
        student2.setLastName("Czarny");

        Student student3 = new Student();
        student3.setFirstName("Bożydar");
        student3.setLastName("Czarny");

        Student student4 = new Student();
        student4.setFirstName("Amadeusz");
        student4.setLastName("Adamski");

        Student student5 = new Student();
        student5.setFirstName("Julia");
        student5.setLastName("Czarny");


        System.out.println("\nLet's have an array we can iterate over");

        //Student[] students = {student1, student2, student3, student4}; //to zadziała ale
        // Student[] students = new Student[] {student1, student2, student3, student4}; //to jest lepsza opcja
        Student[] studentsArray = new Student[]{student1, student2, student3, student4, student5}; //to jest lepsza opcja

//        for (Student student : students){
//            System.out.println(student);
//        }

        for (Student student : studentsArray) {
            System.out.println(student);
        }


        System.out.println("\nLet's do the same with list");
        //lista z studentami,
        List<Student> studentList = List.of(student1, student2, student3, student4, student5);
        for (Student student : studentList) {
            System.out.println(student);
        }


        System.out.println("\nLet's do the same with list old school way, not recommended");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i));
        }


        System.out.println("\nLet's do the same with forEach");
        studentList.forEach(System.out::println);


        System.out.println("\nLet's use stream for on array");
        Arrays.stream(studentsArray).forEach(System.out::println);


        System.out.println("\nLet's sort the list");
        //we need to create the ArrayList becouse result of
        List<Student> studentsArrayList = new ArrayList<>(studentList);//przekazanie
        //sortowanie na liście Adamski idzie na początek; i jeszcz po imieniu dodaliśmy w student Comparable;
        Collections.sort(studentsArrayList);
        studentsArrayList.forEach(System.out::println);


        System.out.println("\nLet's have some teachers");
        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Genowefa");
        teacher1.setLastName("Pompke");
        teacher1.setClassTypes(Set.of("MATH", "INF"));

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Adam");
        teacher2.setLastName("Spadam");
        Set<String> classSet = new HashSet<>();
        classSet.add("POL");
        classSet.add("HIS");
        classSet.add("POL"); //to jest zdublowane, ale
        teacher2.setClassTypes(classSet);


        List<Teacher> teacherList = new LinkedList<>();
        teacherList.add(teacher1);
        teacherList.add(teacher2);
        teacherList.forEach(System.out::println);


        System.out.println("\nLet's print a list of all people");
        List<Person> personList = new LinkedList<>();
        personList.addAll(studentsArrayList);
        personList.addAll(teacherList);
        personList.forEach(System.out::println);


        System.out.println("\n Lets print a list of people, and sort it");
        Collections.sort(personList);
        personList.forEach(System.out::println);


        System.out.println("\n                                        ");
        var strings = new ArrayList<String>(List.of("1", "2", "20", "3"));
        Collections.sort(strings);
        strings.forEach(System.out::println);

        System.out.println("\n Lets uce comparator");


//        //Collections.sort(personList, PersonFirstNameLastNameComparator.INSTANCE);
//        //SINGLETON??
//        Collections.sort(personList, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());
//                if(firstNameResult == 0){
//                    return o1.getLastName().compareTo(o2.getLastName());
//                }
//                return firstNameResult;
//            }
//        });
// klasa ANONIMOWA

       // Collections.sort(personList, new NestedPersonFirstNameLastNameComparator());

        //teraz będzie coć o Lambdach;czyli?

        Collections.sort(personList, ((o1, o2) -> {
            int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());

            if(firstNameResult == 0){
                return o1.getLastName().compareTo(o2.getLastName());
            }
            return firstNameResult;
        }));



//        personList.forEach(new Consumer<Person>() {
//            @Override
//            public void accept(Person person) {
//                System.out.println(person);
//            }
//        });


        //to jest lambda, czyli implementacja interfejsu
        System.out.println("\n to jesT Lambda");
        personList.forEach(person -> System.out.println(person));



    }
    //to jest klasa wewnętrzna, internal class(powiązana z obiektem, bound to object)
    public class PersonFirstNameLastNameComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());
            if(firstNameResult == 0){
                return o1.getLastName().compareTo(o2.getLastName());
            }
            return firstNameResult;
        }
    }
    //to jest klasa wewnętrzna2,zagnierzdzona (nested, static internal) class, available from static context
    public static class NestedPersonFirstNameLastNameComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());

            if(firstNameResult == 0){
                return o1.getLastName().compareTo(o2.getLastName());
            }
            return firstNameResult;
        }
    }

//    public void print (){
//        System.out.println("");
//        new PersonFirstNameLastNameComparator();
//    }

}
