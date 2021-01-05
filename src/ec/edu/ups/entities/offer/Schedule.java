package ec.edu.ups.entities.offer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Schedule
 *
 */
@Entity
@Table(name = "SCHEDULES")
public class Schedule implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sch_id")
	private int id;
	
	@Column(name = "sch_day")
	private String day;
	
	@Column(name = "sch_start_time")
	private String startTime;
	
	@Column(name = "sch_end_time")
	private String endTime;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Group group;
	
	public Schedule() {
		super();
	}

	public Schedule(String day, String startTime, String endTime, Group group) {
		super();
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.group = group;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", day=" + day + ", startTime=" + startTime + ", endTime=" + endTime + ", group="
				+ group + "]";
	}
   
}
