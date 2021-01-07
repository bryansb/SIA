package ec.edu.ups.entities.accounting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.utils.ParameterDAO;
import ec.edu.ups.entities.registration.Enrollment;
import ec.edu.ups.entities.utils.Parameter;

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

	@Transient
	private ParameterDAO parameterDAO;
	
	public BillHead() {
		super();
		parameterDAO = DAOFactory.getFactory().getParameterDAO();
	}

	public void createBillDetail(String description, int quantity, double unitPrice) {
		if (this.billDetailList == null) {
			this.billDetailList = new ArrayList<BillDetail>();
		}
		BillDetail billDetail = new BillDetail(description, quantity, unitPrice);
		this.billDetailList.add(billDetail);
	}
	
	public boolean calculateTotal() {
		Parameter iva;
		Parameter basicTax;
		if (this.billDetailList == null) {
			return false;
		}
		try {
			iva = parameterDAO.findByKey("IVA");
			basicTax = parameterDAO.findByKey("BASIC_TAX");
			for (BillDetail billDetail : this.billDetailList) {
				billDetail.calculateTotal();
				this.subtotal += billDetail.getTotal();
			}
			this.taxes = this.subtotal * iva.getDoubleValue();
			this.vat = this.subtotal * basicTax.getDoubleValue();
			this.total = this.subtotal + this.taxes + this.vat;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<BillDetail> getBillDetailList() {
		return billDetailList;
	}

	public void setBillDetailList(List<BillDetail> billDetailList) {
		this.billDetailList = billDetailList;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	@Override
	public String toString() {
		return "BillHead [id=" + id + ", subtotal=" + subtotal + ", taxes=" + taxes + ", vat=" + vat + ", total="
				+ total + ", date=" + date + ", billDetailList=" + billDetailList + ", enrollment=" + enrollment + "]";
	}
}
