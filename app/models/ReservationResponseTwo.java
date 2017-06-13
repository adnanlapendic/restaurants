package models;

/**
 * Created by lapa on 6/13/17.
 */
public class ReservationResponseTwo {

    private Long tableId;
    private Long userId;
    private Long id;
    private Long dateAndTime;
    private int persons;


    public ReservationResponseTwo(Long id, Long tableId, Long userId, Long dateAndTime, int persons) {
        this.id = id;
        this.tableId = tableId;
        this.userId = userId;
        this. dateAndTime = dateAndTime;
        this.persons = persons;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Long dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }
}
