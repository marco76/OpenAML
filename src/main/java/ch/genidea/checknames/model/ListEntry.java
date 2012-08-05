/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

/**
 * 
 * @author marcomolteni
 */
@Entity
@Indexed
@Table(name = "ListEntry")
// @NamedQueries({@NamedQuery(name = "ListEntry.findAll", query =
// "SELECT l FROM ListEntry l"), @NamedQuery(name = "ListEntry.findById", query
// = "SELECT l FROM ListEntry l WHERE l.id = :id"), @NamedQuery(name =
// "ListEntry.findByListName", query =
// "SELECT l FROM ListEntry l WHERE l.listName = :listName"), @NamedQuery(name =
// "ListEntry.findByFirstName", query =
// "SELECT l FROM ListEntry l WHERE l.firstName = :firstName"), @NamedQuery(name
// = "ListEntry.findByFamilyName", query =
// "SELECT l FROM ListEntry l WHERE l.familyName = :familyName"),
// @NamedQuery(name = "ListEntry.findByYob", query =
// "SELECT l FROM ListEntry l WHERE l.yob = :yob"), @NamedQuery(name =
// "ListEntry.findByDob", query =
// "SELECT l FROM ListEntry l WHERE l.dob = :dob"), @NamedQuery(name =
// "ListEntry.findByTitle", query =
// "SELECT l FROM ListEntry l WHERE l.title = :title"), @NamedQuery(name =
// "ListEntry.findByCountryOfResidence", query =
// "SELECT l FROM ListEntry l WHERE l.countryOfResidence = :countryOfResidence"),
// @NamedQuery(name = "ListEntry.findByCountryOfOrigin", query =
// "SELECT l FROM ListEntry l WHERE l.countryOfOrigin = :countryOfOrigin"),
// @NamedQuery(name = "ListEntry.findByUid", query =
// "SELECT l FROM ListEntry l WHERE l.uid = :uid"), @NamedQuery(name =
// "ListEntry.findByType", query =
// "SELECT l FROM ListEntry l WHERE l.type = :type")})
public class ListEntry extends SearchList  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@DocumentId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;
	private String listName;
	private String firstName;

	@Fields( { @Field(index = Index.TOKENIZED) })
	private String familyName;

	@Lob
	@Field(index = Index.TOKENIZED)
	private String allNames;
	@Column(name = "YOB")
	private Integer yob;
	@Column(name = "DOB")
	private String dob;
	@Column(name = "title", length = 25)
	private String title;

	@ManyToOne
	@JoinColumn(name = "listCode")
	private SourceList sourceList;

	@Column(name = "countryOfResidence", length = 100)
	private String countryOfResidence;
	@Column(name = "countryOfOrigin")
	private String countryOfOrigin;
	@Column(name = "uid")
	private BigInteger uid;
	@Column(name = "type")
	private String type;
	private String alias;
	private String address;

	private String addressCountry;
	private String addressCity;
	private Long parentIdForAlias;
	
	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	
	public ListEntry() {
	}

	public ListEntry(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getAllNames() {
		return allNames;
	}

	public void setAllNames(String allNames) {
		this.allNames = allNames;
	}

	public Integer getYob() {
		return yob;
	}

	public void setYob(Integer yob) {
		this.yob = yob;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public BigInteger getUid() {
		return uid;
	}

	public void setUid(BigInteger uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
/*
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ListEntry)) {
			return false;
		}
		ListEntry other = (ListEntry) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ch.genidea.checknames.model.ListEntry[id=" + id + "]";
	}
*/
	/**
	 * @return the sourceList
	 */
	public SourceList getSourceList() {
		return sourceList;
	}

	/**
	 * @param sourceList
	 *            the sourceList to set
	 */
	public void setSourceList(SourceList sourceList) {
		this.sourceList = sourceList;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setParentIdForAlias(Long parentIdForAlias) {
		this.parentIdForAlias = parentIdForAlias;
	}

	public Long getParentIdForAlias() {
		return parentIdForAlias;
	}

}
