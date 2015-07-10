package entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 09.07.15
 * Time: 19:54
 * To change this template use File | Settings | File Templates.
 */
public class Lesson implements Serializable {
    private Integer id;
    private String name;
    private Teacher teacher;
    private Room room;
    private Time time;
    private List<Student> students = new ArrayList<>();

    public Lesson(String name, Teacher teacher, Room room, Time time) {
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
    }

    public Lesson(String name, Teacher teacher, Room room, Time time, List<Student> students) {

        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;

        Lesson lesson = (Lesson) o;

        if (id != null ? !id.equals(lesson.id) : lesson.id != null) return false;
        if (name != null ? !name.equals(lesson.name) : lesson.name != null) return false;
        if (room != null ? !room.equals(lesson.room) : lesson.room != null) return false;
        if (students != null ? !students.equals(lesson.students) : lesson.students != null) return false;
        if (teacher != null ? !teacher.equals(lesson.teacher) : lesson.teacher != null) return false;
        if (time != null ? !time.equals(lesson.time) : lesson.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (students != null ? students.hashCode() : 0);
        return result;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Lesson(Integer id, String name, Teacher teacher, Room room, Time time) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Room getRoom() {
        return room;
    }

    public Time getTime() {
        return time;
    }
}
