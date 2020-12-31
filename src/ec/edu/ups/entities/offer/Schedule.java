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
   
}
