package ec.edu.ups.entities.accounting;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.entities.registration.Enrollment;

/**
 * Entity implementation class for Entity: BillHead
 *
 */
@Entity
@Table(name = "BILL_HEADS")
public class BillHead implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hea_id")
	private int id;
	
	@Column(name = "hea_subtotal")
	private double subtotal;
	
	@Column(name = "hea_taxes")
	private double taxes;
	
	@Column(name = "hea_vat")
	private double vat;
	
	@Column(name = "hea_total")
	private double total;
	
	@Column(name = "hea_date")
	private Date date;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "billHead")
	private List<BillDetail> billDetailList;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "billHead")
	private Enrollment enrollment;
	
	public BillHead() {
		super();
	}
   
}
