package com.javatutorials;

class Person implements Comparable<Person> {
    public Person() {
    }

    public Person(String city, String name) {
        this.city = city;
        this.name = name;
    }

    protected String city;
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.name);
    }
}
