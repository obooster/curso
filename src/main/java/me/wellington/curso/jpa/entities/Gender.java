package me.wellington.curso.jpa.entities;

import javax.persistence.*;

//@Entity
//@Table(name = "genders")
@Embeddable
public class Gender {
  //  @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    private String genderName;

    public Gender() {}

    public Gender(String name) {
        this.genderName = name;
    }

    public String getName() {
        return genderName;
    }

    public void setName(String name) {
        this.genderName = name;
    }

    @Override
    public String toString() {
        return "Gender{" +
              //  "id=" + id +
                "name='" + genderName + '\'' +
                '}';
    }
}
