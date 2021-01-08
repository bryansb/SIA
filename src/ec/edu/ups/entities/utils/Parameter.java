package ec.edu.ups.entities.utils;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Parameter
 *
 */
@Entity
@Table(name = "PARAMETERS")
public class Parameter implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "par_id")
	private int id;
	
	@Column(name = "par_key", nullable = false, unique = true)
	private String key;
	
	@Column(name = "par_value", nullable = false)
	private String value;
	
	@Column(name = "par_description", nullable = true)
	private String description;
	
	public Parameter() {
		super();
	}
	
	public Parameter(String key, String value, String description) {
		super();
		this.key = key;
		this.value = value;
		this.description = description;
	}

	public double getDoubleValue() throws Exception {
		double value = Double.parseDouble(this.value);
		return value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Parameter [id=" + id + ", key=" + key + ", value=" + value + ", description=" 
				+ description + "]";
	}
   
}
