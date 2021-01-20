package com.javatutorials;

class Department {
    private int internalCode;
    private String name;

    public Department(int internalCode, String name) {
        this.internalCode = internalCode;
        this.name = name;
    }

    public int getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(int internalCode) {
        this.internalCode = internalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}
