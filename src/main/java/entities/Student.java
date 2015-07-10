package entities;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 09.07.15
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class Student implements Serializable{
    private Integer id;
    private String name;
    private String surname;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Student(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
