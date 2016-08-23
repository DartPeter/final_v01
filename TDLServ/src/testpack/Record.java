package testpack;

public class Record {
	
	public int id;
	public String name;
	public boolean status;
	public int pr_id;
	
	public Record(int id, String name, boolean status, int pr_id) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.pr_id = pr_id;
	}

	public String toString() {
		return "Record [id=" + id + ", name=" + name + ", status=" + status + ", pr_id=" + pr_id + "]";
	}

}
