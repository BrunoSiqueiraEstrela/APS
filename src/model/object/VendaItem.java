/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.object;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author DELTA
 */
@Entity
@Table(name = "venda_item", catalog = "bancoaps", schema = "")
@NamedQueries({
    @NamedQuery(name = "VendaItem.findAll", query = "SELECT v FROM VendaItem v"),
    @NamedQuery(name = "VendaItem.findByIditemvenda", query = "SELECT v FROM VendaItem v WHERE v.iditemvenda = :iditemvenda"),
    @NamedQuery(name = "VendaItem.findByFkvenda", query = "SELECT v FROM VendaItem v WHERE v.fkvenda = :fkvenda"),
    @NamedQuery(name = "VendaItem.findByFkproduto", query = "SELECT v FROM VendaItem v WHERE v.fkproduto = :fkproduto"),
    @NamedQuery(name = "VendaItem.findByValorcusto", query = "SELECT v FROM VendaItem v WHERE v.valorcusto = :valorcusto"),
    @NamedQuery(name = "VendaItem.findByValorvenda", query = "SELECT v FROM VendaItem v WHERE v.valorvenda = :valorvenda"),
    @NamedQuery(name = "VendaItem.findByQuantidade", query = "SELECT v FROM VendaItem v WHERE v.quantidade = :quantidade"),
    @NamedQuery(name = "VendaItem.findByValorfinalitem", query = "SELECT v FROM VendaItem v WHERE v.valorfinalitem = :valorfinalitem"),
    @NamedQuery(name = "VendaItem.findByStatus", query = "SELECT v FROM VendaItem v WHERE v.status = :status")})
public class VendaItem implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iditemvenda")
    private Integer iditemvenda;
    @Column(name = "fkvenda")
    private Integer fkvenda;
    @Column(name = "fkproduto")
    private Integer fkproduto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorcusto")
    private BigDecimal valorcusto;
    @Column(name = "valorvenda")
    private BigDecimal valorvenda;
    @Column(name = "quantidade")
    private BigDecimal quantidade;
    @Column(name = "valorfinalitem")
    private BigDecimal valorfinalitem;
    @Column(name = "status")
    private Boolean status;

    public VendaItem() {
    }

    public VendaItem(Integer iditemvenda) {
        this.iditemvenda = iditemvenda;
    }

    public Integer getIditemvenda() {
        return iditemvenda;
    }

    public void setIditemvenda(Integer iditemvenda) {
        Integer oldIditemvenda = this.iditemvenda;
        this.iditemvenda = iditemvenda;
        changeSupport.firePropertyChange("iditemvenda", oldIditemvenda, iditemvenda);
    }

    public Integer getFkvenda() {
        return fkvenda;
    }

    public void setFkvenda(Integer fkvenda) {
        Integer oldFkvenda = this.fkvenda;
        this.fkvenda = fkvenda;
        changeSupport.firePropertyChange("fkvenda", oldFkvenda, fkvenda);
    }

    public Integer getFkproduto() {
        return fkproduto;
    }

    public void setFkproduto(Integer fkproduto) {
        Integer oldFkproduto = this.fkproduto;
        this.fkproduto = fkproduto;
        changeSupport.firePropertyChange("fkproduto", oldFkproduto, fkproduto);
    }

    public BigDecimal getValorcusto() {
        return valorcusto;
    }

    public void setValorcusto(BigDecimal valorcusto) {
        BigDecimal oldValorcusto = this.valorcusto;
        this.valorcusto = valorcusto;
        changeSupport.firePropertyChange("valorcusto", oldValorcusto, valorcusto);
    }

    public BigDecimal getValorvenda() {
        return valorvenda;
    }

    public void setValorvenda(BigDecimal valorvenda) {
        BigDecimal oldValorvenda = this.valorvenda;
        this.valorvenda = valorvenda;
        changeSupport.firePropertyChange("valorvenda", oldValorvenda, valorvenda);
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        BigDecimal oldQuantidade = this.quantidade;
        this.quantidade = quantidade;
        changeSupport.firePropertyChange("quantidade", oldQuantidade, quantidade);
    }

    public BigDecimal getValorfinalitem() {
        return valorfinalitem;
    }

    public void setValorfinalitem(BigDecimal valorfinalitem) {
        BigDecimal oldValorfinalitem = this.valorfinalitem;
        this.valorfinalitem = valorfinalitem;
        changeSupport.firePropertyChange("valorfinalitem", oldValorfinalitem, valorfinalitem);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        Boolean oldStatus = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldStatus, status);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iditemvenda != null ? iditemvenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendaItem)) {
            return false;
        }
        VendaItem other = (VendaItem) object;
        if ((this.iditemvenda == null && other.iditemvenda != null) || (this.iditemvenda != null && !this.iditemvenda.equals(other.iditemvenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "views.VendaItem[ iditemvenda=" + iditemvenda + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
