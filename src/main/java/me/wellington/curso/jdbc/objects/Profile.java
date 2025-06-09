package me.wellington.curso.jdbc.objects;

public class Profile {
    private int number;
    private String name;
    private int age;

    public Profile(String name, int age) {
        this.name = name.toUpperCase();
        this.age = age;
    }

    public Profile(int number, String name, int age) {
        this.number = number;
        this.name = name.toUpperCase();
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
