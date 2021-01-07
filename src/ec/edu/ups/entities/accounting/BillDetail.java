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
	
	@Column(name = "det_unit_price", columnDefinition = "DECIMAL(6, 2)")
	private double unitPrice;
	
	@Column(name = "det_total", columnDefinition = "DECIMAL(6, 2)")
	private double total;
	
	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private BillHead billHead;
	
	public BillDetail() {
		super();
	}

	public BillDetail(String description, int quantity, double unitPrice) {
		super();
		this.description = description;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		calculateTotal();
	}

	public void calculateTotal() {
		this.total = this.quantity * this.unitPrice;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public BillHead getBillHead() {
		return billHead;
	}

	public void setBillHead(BillHead billHead) {
		this.billHead = billHead;
	}

	@Override
	public String toString() {
		return "BillDetail [id=" + id + ", description=" + description + ", quantity=" + quantity + ", unitPrice="
				+ unitPrice + ", total=" + total + ", billHead=" + billHead + "]";
	}
}
