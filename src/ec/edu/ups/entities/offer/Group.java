package ec.edu.ups.entities.offer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.entities.management.Teacher;
import ec.edu.ups.entities.registration.Grade;

/**
 * Entity implementation class for Entity: Group
 *
 */
@Entity
@Table(name = "GROUPS")
public class Group implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gro_id")
	private int id;
	
	@Column(name = "gro_academic_period")
	private String academicPeriod;
	
	@Column(name = "gro_physical_space")
	private String physicalSpace;
	
	@Column(name = "gro_quota")
	private int quota;
	
	@Column(name = "gro_deleted", nullable = false,  
			columnDefinition = "BOOLEAN DEFAULT 0")
	private boolean deleted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	private List<Schedule> scheduleList;
	
	@JoinColumn
	@ManyToOne(fetch = FetchType.EAGER)
	private Subject subject;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	private List<Grade> gradeList;
	
	@ManyToMany
	@JoinColumn
	private List<Teacher> teacherList;
	
	@Transient
	private boolean editable;
	
	public Group() {
		super();
	}

	public Group(String academicPeriod, String physicalSpace, int quota) {
		super();
		this.academicPeriod = academicPeriod;
		this.physicalSpace = physicalSpace;
		this.quota = quota;
	}
	
	public String getScheduleToString() {
		String schedule = "";
		
		for (Schedule s : scheduleList) {
			schedule += " " + s.getDay() + " " + s.getStartTime() + "-" + s.getEndTime() 
			+ " |";
		}
		schedule = schedule.substring(0,schedule.length()-2);
		return schedule;
	}
	
	public void createSchedule(String day, String startTime, String endTime, Group group) {
		Schedule schedule;
		
		if (this.scheduleList == null) {
			this.scheduleList = new ArrayList<>();
		}
		
		schedule = new Schedule(day, startTime, endTime, group);
		scheduleList.add(schedule);
	}
	
	public void addTeacher(Teacher teacher) {

		if (this.teacherList == null) {
			this.teacherList = new ArrayList<>();
		}
		
		teacherList.add(teacher);
	}
	
	public void removeTeacher(Teacher teacher) {
		if (this.teacherList == null) {
			return;
		}
		
		for (int i = 0; i < teacherList.size(); i++) {
			if (teacherList.get(i).getId() == teacher.getId()) {
				teacherList.remove(i);
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcademicPeriod() {
		return academicPeriod;
	}

	public void setAcademicPeriod(String academicPeriod) {
		this.academicPeriod = academicPeriod;
	}

	public String getPhysicalSpace() {
		return physicalSpace;
	}

	public void setPhysicalSpace(String physicalSpace) {
		this.physicalSpace = physicalSpace;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", academicPeriod=" + academicPeriod + ", physicalSpace=" + physicalSpace
				+ ", quota=" + quota + ", scheduleList=" + scheduleList + ", subject=" + subject + ", gradeList="
				+ gradeList + ", teacherList=" + teacherList + "]";
	}
   
}
