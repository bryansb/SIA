package ec.edu.ups.entities.accounting;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: BillDetail
 *
 */
@Entity
@Table(name = "BILL_DETAILS")
public class BillDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "det_id")
	private int id;
	
	@Column(name = "det_description")
	private String description;
	
	@Column(name = "det_quantity")
	private int quantity;
	
	@Column(name = "det_unit_price")
	private double unitPrice;
	
	@Column(name = "det_total")
	private double total;
	
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private BillHead billHead;
	
	public BillDetail() {
		super();
	}
   
}
