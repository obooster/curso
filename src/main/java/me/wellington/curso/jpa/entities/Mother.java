package me.wellington.curso.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "family")
public class Mother {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "mother", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Person> persons = new ArrayList<>();

    public Mother() {}

    public Mother(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(Person... persons) {
        Arrays.stream(persons).forEach(person -> person.setMother(this));
        this.persons = Arrays.asList(persons);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mother{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", persons=" + persons.stream().map(Person::getName).toList() +
                '}';
    }

}
