package com.javatutorials;

class Student extends Person {
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }

    private int grade;

    public Student(int grade, String name) {
        this.grade = grade;
        super.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
