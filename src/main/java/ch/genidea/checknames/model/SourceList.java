/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.genidea.checknames.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marcomolteni
 */
@Entity
@Table(name = "SourceList")
@NamedQueries({@NamedQuery(name = "SourceList.findAll", query = "SELECT s FROM SourceList s"), @NamedQuery(name = "SourceList.findById", query = "SELECT s FROM SourceList s WHERE s.id = :id"), @NamedQuery(name = "SourceList.findByName", query = "SELECT s FROM SourceList s WHERE s.name = :name"), @NamedQuery(name = "SourceList.findByLastImported", query = "SELECT s FROM SourceList s WHERE s.lastImported = :lastImported"), @NamedQuery(name = "SourceList.findByNoOfRecordsImported", query = "SELECT s FROM SourceList s WHERE s.noOfRecordsImported = :noOfRecordsImported"), @NamedQuery(name = "SourceList.findByWebSource", query = "SELECT s FROM SourceList s WHERE s.webSource = :webSource")})
public class SourceList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "lastImported")
    @Temporal(TemporalType.DATE)
    private Date lastImported;
    @Column(name = "noOfRecordsImported")
    private Integer noOfRecordsImported;
    @Column(name = "webSource")
    private String webSource;
    public SourceList() {
    }

    public SourceList(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastImported() {
        return lastImported;
    }

    public void setLastImported(Date lastImported) {
        this.lastImported = lastImported;
    }

    public Integer getNoOfRecordsImported() {
        return noOfRecordsImported;
    }

    public void setNoOfRecordsImported(Integer noOfRecordsImported) {
        this.noOfRecordsImported = noOfRecordsImported;
    }

    public String getWebSource() {
        return webSource;
    }

    public void setWebSource(String webSource) {
        this.webSource = webSource;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SourceList)) {
            return false;
        }
        SourceList other = (SourceList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.genidea.checknames.model.SourceList[id=" + id + "]";
    }

}
