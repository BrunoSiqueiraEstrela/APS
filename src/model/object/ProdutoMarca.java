/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.object;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
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
@Table(name = "produto_marca", catalog = "bancoaps", schema = "")
@NamedQueries({
    @NamedQuery(name = "ProdutoMarca.findAll", query = "SELECT p FROM ProdutoMarca p"),
    @NamedQuery(name = "ProdutoMarca.findByIdmarca", query = "SELECT p FROM ProdutoMarca p WHERE p.idmarca = :idmarca"),
    @NamedQuery(name = "ProdutoMarca.findByNome", query = "SELECT p FROM ProdutoMarca p WHERE p.nome = :nome"),
    @NamedQuery(name = "ProdutoMarca.findByStatus", query = "SELECT p FROM ProdutoMarca p WHERE p.status = :status")})
public class ProdutoMarca implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmarca")
    private Integer idmarca;
    @Column(name = "nome")
    private String nome;
    @Column(name = "status")
    private Boolean status;

    public ProdutoMarca() {
    }

    public ProdutoMarca(Integer idmarca) {
        this.idmarca = idmarca;
    }

    public Integer getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Integer idmarca) {
        Integer oldIdmarca = this.idmarca;
        this.idmarca = idmarca;
        changeSupport.firePropertyChange("idmarca", oldIdmarca, idmarca);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
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
        hash += (idmarca != null ? idmarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoMarca)) {
            return false;
        }
        ProdutoMarca other = (ProdutoMarca) object;
        if ((this.idmarca == null && other.idmarca != null) || (this.idmarca != null && !this.idmarca.equals(other.idmarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
