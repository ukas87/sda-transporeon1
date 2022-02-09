import model.Person;
import model.Student;
import model.Teacher;

import java.util.*;

public class MainDataClassesOverview {
    public static void main(String[] args) {

        Student student1 = new Student();  //inicjalizowanie studenta1
        Student student2 = new Student();  //studenta2
        Student student3 = new Student();  // oraz studenta3
        Student student4 = new Student();  //4
        Student student5 = new Student();  //czyli mamy 5 studentów

        student1.setFirstName("Cycylia");
        student1.setLastName("Cycek");

        student2.setFirstName("Wacław");
        student2.setLastName("Białek");

        student3.setFirstName("Bartosz");
        student3.setLastName("Białek");


        student4.setFirstName("Zenon");
        student4.setLastName("Zakała");

        student5.setFirstName("Edward");
        student5.setLastName("Eklerk");

        ////////////////////ARRAY, TABLICA//////////////
        System.out.println("\n ARRAY, czyli Tablica do iteracji");

        Student[] studentsArray = new Student[]{student1, student2, student3, student4, student5};

        for (Student student : studentsArray) {
            System.out.println(student);
        }

        //////////////////////LISTA//////////////
        System.out.println("\n Lista, to samo na Liście");
        ///od kluczowego słowa List<nazwa> nazwaListy = List.of(s1,s2,s3,s4,s5);
        List<Student> studentList = List.of(student1, student2, student3, student4, student5);

        for (Student student : studentList) {
            System.out.println(student);
        }
        ////////tu wersja z FOR moim ulubienym/////////////
        System.out.println("\n wywołne na klasycznym forze, też działa ale mniej efektywne");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i));
        }
        //tylko po pierwsze: lista na .size a nie lenght
        //po drugie wypisując odwołay się nie do i ale studentList.get(i)


        ///////////////////////FOR EACH///////////////////////

        System.out.println("\nTo samo przy użyciu FOR EACH'a");

        studentList.forEach(System.out::println);

        //bieremy studentLis czyli       List<Student> studentList = List.of(s1,s2,s3,s4,s5);

        ///////STREAM NA ARRAY/// czyi strumien na tablicy////??///////
        //Student[] studentsArray = new Student[]{student1, student2, student3, student4, student5};
        System.out.println("\nStream NA ARRAYu");
        Arrays.stream(studentsArray).forEach(System.out::println);

        ///////////////////////SORTOWANIE LISTY///////////////////////
        System.out.println("\nLet's sort the list");
        //we need to create the ArrayList because result of List.of(...) is NOT mutable!
        //List.of jest niemutowalne, nie można dodawać, oejmować anie przesuwać ni chuja

        List<Student> studentArrayList = new ArrayList<>(studentList);// ta jest mutowalna, w ten drugi parametr listastudentów
        Collections.sort(studentArrayList);
        studentArrayList.forEach(System.out::println);

        //////////////////////DODAjemy nauczycieli//////////////
        //inicjalizacja nauczycieli
        Teacher teacher1 = new Teacher();
        Teacher teacher2 = new Teacher();
        //imiona i nazwiska usatwiamy
        teacher1.setFirstName("Wacław");
        teacher1.setLastName("Hitler");
        teacher2.setFirstName("Irena");
        teacher2.setLastName("Stalin");

        //oraz przedmioty których uczą na dwa sposoby
        //dla pierwszego za pomocą pomocą metody setClassType(Set.of(" ", " "));
        teacher1.setClassTypes(Set.of("MAT", "INF"));
        //dla drugiego
        Set<String> classSet = new HashSet<>();
        classSet.add("POL");
        classSet.add("HIS");
        classSet.add("POL"); // intencjonalnie jest to zdooblowane, set nie wyświetli tego drugi raz;

        teacher2.setClassTypes(classSet);

        //////////DODAJEMY NAUCZYICLEI DO LISTY  LINKED LIST!!!//////
        System.out.println("\n wyświetlamy nauczycieli");
        List<Teacher> teacherList = new LinkedList<>();
        teacherList.add(teacher1);
        teacherList.add(teacher2);
        teacherList.forEach(System.out::println);

        //////////////////////Wyświalam WSZYTSKICH///////////
        System.out.println("\n liczta wszystkich personów");
        List<Person> personList = new LinkedList<>();
        personList.addAll(teacherList);
        personList.addAll(studentList);
        personList.forEach(System.out::println);

        ////Teraz posotujemy i wyswietlamy////
        System.out.println("\n teraz posortowanie i wyswiwtlanie:");
        Collections.sort(personList);
        personList.forEach(System.out::println);


        /////////////////////////przykład że List.of jesyt nimutowalna/////

        System.out.println("\nto zadannie zę list of jest niemutowalne");
        var strings = new ArrayList<String>(List.of("1", "2", "20", "3"));
        Collections.sort(strings);
        strings.forEach(System.out::println);

        ////////////////////COMPARATOR////////////
        System.out.println("\nLet's use comparator");


        //        Collections.sort(personList, PersonFirstNameLastNameComparator.INSTANCE);

//        Collections.sort(personList, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());
//
//                if (firstNameResult == 0) {
//                    return o1.getLastName().compareTo(o2.getLastName());
//                }
//                return firstNameResult;
//            }
//        });

//        Collections.sort(personList, new NestedPersonFirstNameLastNameComparator());

        Collections.sort(personList, ((o1, o2) -> {
            int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());

            if (firstNameResult == 0) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
            return firstNameResult;
        }));

// Consumer as a internal class:
//        personList.forEach(new Consumer<Person>() {
//            @Override
//            public void accept(Person person) {
//                System.out.println(person);
//            }
//        });

// Consumer as a lambda
        personList.forEach(person -> System.out.println(person));

    }

    //Internal class (bound to object)
    public class PersonFirstNameLastNameComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());

            if (firstNameResult == 0) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
            return firstNameResult;
        }
    }

    //Nested (static internal) class, available from static context
    public static class NestedPersonFirstNameLastNameComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            int firstNameResult = o1.getFirstName().compareTo(o2.getFirstName());

            if (firstNameResult == 0) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
            return firstNameResult;


        }

    }
}
