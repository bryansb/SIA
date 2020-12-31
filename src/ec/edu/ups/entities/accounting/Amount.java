package ec.edu.ups.entities.accounting;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Amount
 *
 */
@Entity
@Table(name = "AMOUNTS")
public class Amount implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "amo_id")
	private int id;
	
	@Column(name = "amo_date")
	private GregorianCalendar date;
	
	@Column(name = "amo_description")
	private String description;
	
	@Column(name = "amo_unit_price")
	private double unitPrice;
	
	@Column(name = "amo_total")
	private double total;
	
	// i: income |  e: egress
	@Column(name = "amo_type")
	private char type;
	
	@JoinColumn
	@ManyToOne
	private Account account;
	
	public Amount() {
		super();
	}
   
}
