package com.javatutorials;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.javatutorials.EmpSort.SALARY_ORDER;

public class CollectionTutorial {
    public static final Department RESEARCH = new Department(2, "Research");
    public static final Person PERSON_1 = new Person("Denver", "Jeremy");
    public static final Person PERSON_2 = new Person("Geneva", "Pierre");
    public static final Person PERSON_3 = new Person("Geneva", "Julien");
    private static final Department SALES = new Department(99, "Sales");
    public static final Department ACCOUNTING = new Department(1, "Accounting");
    private static final Department ENGINEERING = new Department(123, "Engineering");
    public static final Employee EMPLOYEE_1 = new Employee(48000, "David", ACCOUNTING);
    public static final Employee EMPLOYEE_2 = new Employee(120000, "Carolina", RESEARCH);
    public static final Employee EMPLOYEE_3 = new Employee(2000000, "Sigulda", SALES);
    public static final Employee EMPLOYEE_4 = new Employee(49000, "Abdul", ENGINEERING);
    public static final Employee EMPLOYEE_5 = new Employee(900000, "Turaida", SALES);
    public static final Employee EMPLOYEE_6 = new Employee(800000, "Jurmala", SALES);
    public static final Employee EMPLOYEE_7 = new Employee(800000, "Maskavas", SALES);
    public static final Employee EMPLOYEE_8 = new Employee(700000, "Artilerijas", SALES);
    public static final Employee EMPLOYEE_9 = new Employee(700000, "Riga", SALES);
    private static final int PASS_THRESHOLD = 6;
    public static final Student STUDENT_1 = new Student(7, "Abelardo");
    public static final Student STUDENT_2 = new Student(5, "Gerard");
    public static final Student STUDENT_3 = new Student(8, "Belen");
    private static Collection<Shape> myShapesCollection;
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        /* The COLLECTION interface */ System.out.println();
        System.out.println("/// The COLLECTION interface");

        myShapesCollection = Shape.getShapes();
        /* Stream */ System.out.println();
        long startTime = System.nanoTime();
        myShapesCollection.stream()
                .filter(e -> e.getColor() == Shape.Color.RED)
                .forEach(e -> System.out.println(e.getName()));
        long stopTime = System.nanoTime();
        long duration1 = stopTime - startTime;

        /* Parallel Stream */ System.out.println();
        startTime = System.nanoTime();
        myShapesCollection.parallelStream()
                .filter(e -> e.getColor() == Shape.Color.BLUE)
                .forEach(e -> System.out.println(e.getName()));
        stopTime = System.nanoTime();
        System.out.println("Single stream time in milliseconds: " + TimeUnit.NANOSECONDS.toMillis(duration1));
        long duration2 = stopTime - startTime;
        System.out.println("Parallel stream time in milliseconds: " + TimeUnit.NANOSECONDS.toMillis(duration2));
        System.out.println("Difference in milliseconds: " + TimeUnit.NANOSECONDS.toMillis(duration1 - duration2));

        /*Collecting values by joining them */ System.out.println();
        String joined = myShapesCollection.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        System.out.println(joined);

        /* Collecting values by summing them */ System.out.println();
        Collection<Employee> employees = new ArrayList<>();
        employees.add(EMPLOYEE_1);
        employees.add(EMPLOYEE_2);
        employees.add(EMPLOYEE_3);
        employees.add(EMPLOYEE_4);
        employees.add(EMPLOYEE_5);
        int totalSalaries = employees.stream()
                .collect(Collectors.summingInt(Employee::getSalary));
        System.out.println(totalSalaries);


        List<Person> people = new ArrayList<>();
        people.addAll(employees);
        List<Student> students = new ArrayList<>();
        students.add(STUDENT_1);
        students.add(STUDENT_2);
        students.add(STUDENT_3);

        /* for - each */ System.out.println();
        for(Employee employee : employees) System.out.println(employee.getName());

        /* Accumulate names into a List */ System.out.println();
        List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println(list);

        /* Accumulate names into a TreeSet  */ System.out.println();
        Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set);

        /* Compute sum of salaries of employee */ System.out.println();
        int total = employees.stream()
                .collect(Collectors.summingInt(Employee::getSalary));
        System.out.println(total);

        /* Group employees by department */ System.out.println();
        Map<Department, List<Employee>> byDept
                = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(byDept);

        /* Compute sum of salaries by department */ System.out.println();
        Map<Department, Integer> totalByDept
                = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.summingInt(Employee::getSalary)));
        System.out.println(totalByDept);

        /* Partition students into passing and failing  */ System.out.println();
        Map<Boolean, List<Student>> passingFailing =
                students.stream()
                        .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));
        passingFailing.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList())
                .forEach(x -> System.out.println(x.getName() + " " + x.getGrade()));

        /* The SET interface */ System.out.println();
        System.out.println("/// The SET interface");

        /* Make list out of randoms */ System.out.println();
        List<Long> integerArrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            integerArrayList.add(new Random().nextLong());
            if(i%2==0) integerArrayList.add(2L);
        }

        /* Print list  */ System.out.println();
        System.out.println("// Print list");
        for (int i = 0; i < integerArrayList.size(); i++) {
            System.out.println(integerArrayList.get(i));
        }

        /* ... using the Set's constructor  */ System.out.println();
        System.out.println("// ... using the Set's constructor");
        Set<Long> noDuplicates = new HashSet<>(integerArrayList);
        noDuplicates.iterator().forEachRemaining(System.out::println);

        /* ... using Stream API  */ System.out.println();
        System.out.println("// ... using Stream API");
        Set<Long> noDupesWithStream = integerArrayList.stream().collect(Collectors.toSet());
        noDupesWithStream.iterator().forEachRemaining(System.out::println);

        /* ... mapping only names and collecting into a Red Black Tree  */ System.out.println();
        System.out.println("// ... collecting into a Red Black Tree");
        Set<Long> longSet = new TreeSet<>(integerArrayList);
        longSet.iterator().forEachRemaining(System.out::println);

        /* ... using a list that preserves original ordering */ System.out.println();
        System.out.println("// ... using a list that preserves original ordering");
        Set<Long> noDups = new LinkedHashSet<>(integerArrayList);
        noDups.iterator().forEachRemaining(System.out::println);

        /* ... randomly permutes a list  */ System.out.println();
        System.out.println("// ... randomly permutes a list");
        Collections.shuffle(integerArrayList);
        integerArrayList.iterator().forEachRemaining(System.out::println);

        /* The MAP interface  */ System.out.println();
        System.out.println("/// The MAP interface");
        List<Person> personList = new ArrayList<>();
        personList.add(PERSON_1);
        personList.add(PERSON_2);
        personList.add(PERSON_3);

        /* Group employees by department  */ System.out.println();
        System.out.println("// Group employees by department");
        Map<Department, List<Employee>> departmentListMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        departmentListMap.forEach((key, value) -> System.out.println(key + " " + value.toString()));

        /* Compute sum of salaries by department */ System.out.println();
        System.out.println("// Compute sum of salaries by department");
        Map<Department, Integer> integerMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.summingInt(Employee::getSalary)));
        integerMap.forEach((key, value) -> System.out.println(key + " " + value));

        /* Partition students into passing and failing */ System.out.println();
        System.out.println("// Partition students into passing and failing");
        Map<Boolean, List<Student>> booleanListMap = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getGrade()>= PASS_THRESHOLD));
        booleanListMap.forEach((key, value) -> System.out.println(key + " " + value.toString()));


        /* Classify Person objects by city */ System.out.println();
        System.out.println("// Partition students into passing and failing");
        Map<String, List<Person>> peopleByCity
                = personList.stream().collect(Collectors.groupingBy(Person::getCity));
        peopleByCity.forEach((key, value) -> System.out.println(key + " " + value.toString()));

        System.out.println();
        System.out.println("Now suppose you want to know who all the \"individual contributors\" (or non-managers) are.");
        /*Suppose you have a Map, managers, that maps each employee in a company to the employee's manager.
        We'll be deliberately vague about the types of the key and the value objects.
        It doesn't matter, as long as they're the same.
        Now suppose you want to know who all the "individual contributors" (or non-managers) are.
        The following snippet tells you exactly what you want to know.*/
        Map<Employee, Employee> managers = new HashMap<>();
        managers.put(EMPLOYEE_6, EMPLOYEE_5);
        managers.put(EMPLOYEE_7, EMPLOYEE_5);
        managers.put(EMPLOYEE_8, EMPLOYEE_7);
        managers.put(EMPLOYEE_9, EMPLOYEE_7);
        Set<Employee> individualContributors = new HashSet<>(managers.keySet());
        individualContributors.removeAll(managers.values());
        individualContributors.forEach(System.out::println);

        /* Using comparator to sort employees by salary */ System.out.println();
        System.out.println("// Using comparator to sort employees by salary");
        List<Employee> employeeList = new ArrayList<>(employees);
        Collections.sort(employeeList, SALARY_ORDER);
        System.out.println(employeeList);

        /* Using default comparation to sort employees by name */ System.out.println();
        System.out.println("// Using default comparation to sort employees by name");
        Collections.sort(employeeList);
        System.out.println(employeeList);

        List <String> l1 = new ArrayList<>();
        List <Integer> l2 = new ArrayList<>();
        System.out.println(l1.getClass().toString()+ ' ' + l2.getClass().toString());

    }
    public static boolean startsWithUpperCaseLetter(String word) {
        try {
            return Character.isUpperCase(word.charAt(0));
        }
        catch (StringIndexOutOfBoundsException ex) {
            System.out.println("The argument is an empty String");
        }
        return false;
    }
    static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        for (T o : a) {
            c.add(o); // Correct
        }
    }
}
