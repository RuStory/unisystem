package entities;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 09.07.15
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public class Room implements Serializable {
    private Integer id;
    private Integer number;

    public Room(Integer number) {
        this.number = number;
    }

    public Room(Integer id, Integer number) {
        this.id = id;
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
