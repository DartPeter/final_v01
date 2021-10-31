package entity;

/**
 * 
 * @author peter
 * Mark entity
 *
 */
public class Mark {
    
    private int id;
    private int userId;
    private int subjId;
    private int value;
    private String subjName;
    
    public Mark() {
        // empty
    }
    
    public Mark(int id, int userId, int subjId, int value, String subjName) {
        this.id = id;
        this.userId = userId;
        this.subjId = subjId;
        this.value = value;
        this.subjName = subjName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSubjId() {
        return subjId;
    }

    public void setSubjId(int subjId) {
        this.subjId = subjId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSubjName() {
        return subjName;
    }

    public void setSubjName(String subjName) {
        this.subjName = subjName;
    }

}
