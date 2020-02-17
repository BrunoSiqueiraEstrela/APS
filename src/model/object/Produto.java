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
@Table(name = "produto", catalog = "bancoaps", schema = "")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findByIdproduto", query = "SELECT p FROM Produto p WHERE p.idproduto = :idproduto"),
    @NamedQuery(name = "Produto.findByDescricaoproduto", query = "SELECT p FROM Produto p WHERE p.descricaoproduto = :descricaoproduto"),
    @NamedQuery(name = "Produto.findByValorvenda", query = "SELECT p FROM Produto p WHERE p.valorvenda = :valorvenda"),
    @NamedQuery(name = "Produto.findByValorcustounitario", query = "SELECT p FROM Produto p WHERE p.valorcustounitario = :valorcustounitario"),
    @NamedQuery(name = "Produto.findByFksetor", query = "SELECT p FROM Produto p WHERE p.fksetor = :fksetor"),
    @NamedQuery(name = "Produto.findByFkgrupo", query = "SELECT p FROM Produto p WHERE p.fkgrupo = :fkgrupo"),
    @NamedQuery(name = "Produto.findByFksubgrupo", query = "SELECT p FROM Produto p WHERE p.fksubgrupo = :fksubgrupo"),
    @NamedQuery(name = "Produto.findByFkmarca", query = "SELECT p FROM Produto p WHERE p.fkmarca = :fkmarca"),
    @NamedQuery(name = "Produto.findByStatus", query = "SELECT p FROM Produto p WHERE p.status = :status")})
public class Produto implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduto")
    private Integer idproduto;
    @Column(name = "descricaoproduto")
    private String descricaoproduto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorvenda")
    private BigDecimal valorvenda;
    @Column(name = "valorcustounitario")
    private BigDecimal valorcustounitario;
    @Column(name = "fksetor")
    private Integer fksetor;
    @Column(name = "fkgrupo")
    private Integer fkgrupo;
    @Column(name = "fksubgrupo")
    private Integer fksubgrupo;
    @Column(name = "fkmarca")
    private Integer fkmarca;
    @Column(name = "status")
    private Boolean status;

    public Produto() {
    }

    public Produto(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public Integer getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        Integer oldIdproduto = this.idproduto;
        this.idproduto = idproduto;
        changeSupport.firePropertyChange("idproduto", oldIdproduto, idproduto);
    }

    public String getDescricaoproduto() {
        return descricaoproduto;
    }

    public void setDescricaoproduto(String descricaoproduto) {
        String oldDescricaoproduto = this.descricaoproduto;
        this.descricaoproduto = descricaoproduto;
        changeSupport.firePropertyChange("descricaoproduto", oldDescricaoproduto, descricaoproduto);
    }

    public BigDecimal getValorvenda() {
        return valorvenda;
    }

    public void setValorvenda(BigDecimal valorvenda) {
        BigDecimal oldValorvenda = this.valorvenda;
        this.valorvenda = valorvenda;
        changeSupport.firePropertyChange("valorvenda", oldValorvenda, valorvenda);
    }

    public BigDecimal getValorcustounitario() {
        return valorcustounitario;
    }

    public void setValorcustounitario(BigDecimal valorcustounitario) {
        BigDecimal oldValorcustounitario = this.valorcustounitario;
        this.valorcustounitario = valorcustounitario;
        changeSupport.firePropertyChange("valorcustounitario", oldValorcustounitario, valorcustounitario);
    }

    public Integer getFksetor() {
        return fksetor;
    }

    public void setFksetor(Integer fksetor) {
        Integer oldFksetor = this.fksetor;
        this.fksetor = fksetor;
        changeSupport.firePropertyChange("fksetor", oldFksetor, fksetor);
    }

    public Integer getFkgrupo() {
        return fkgrupo;
    }

    public void setFkgrupo(Integer fkgrupo) {
        Integer oldFkgrupo = this.fkgrupo;
        this.fkgrupo = fkgrupo;
        changeSupport.firePropertyChange("fkgrupo", oldFkgrupo, fkgrupo);
    }

    public Integer getFksubgrupo() {
        return fksubgrupo;
    }

    public void setFksubgrupo(Integer fksubgrupo) {
        Integer oldFksubgrupo = this.fksubgrupo;
        this.fksubgrupo = fksubgrupo;
        changeSupport.firePropertyChange("fksubgrupo", oldFksubgrupo, fksubgrupo);
    }

    public Integer getFkmarca() {
        return fkmarca;
    }

    public void setFkmarca(Integer fkmarca) {
        Integer oldFkmarca = this.fkmarca;
        this.fkmarca = fkmarca;
        changeSupport.firePropertyChange("fkmarca", oldFkmarca, fkmarca);
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
        hash += (idproduto != null ? idproduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idproduto == null && other.idproduto != null) || (this.idproduto != null && !this.idproduto.equals(other.idproduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescricaoproduto();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
