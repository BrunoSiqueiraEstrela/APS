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
@Table(name = "produto_setor", catalog = "bancoaps", schema = "")
@NamedQueries({
    @NamedQuery(name = "ProdutoSetor.findAll", query = "SELECT p FROM ProdutoSetor p"),
    @NamedQuery(name = "ProdutoSetor.findByIdsetor", query = "SELECT p FROM ProdutoSetor p WHERE p.idsetor = :idsetor"),
    @NamedQuery(name = "ProdutoSetor.findByNome", query = "SELECT p FROM ProdutoSetor p WHERE p.nome = :nome"),
    @NamedQuery(name = "ProdutoSetor.findByStatus", query = "SELECT p FROM ProdutoSetor p WHERE p.status = :status")})
public class ProdutoSetor implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsetor")
    private Integer idsetor;
    @Column(name = "nome")
    private String nome;
    @Column(name = "status")
    private Boolean status;

    public ProdutoSetor() {
    }

    public ProdutoSetor(Integer idsetor) {
        this.idsetor = idsetor;
    }

    public Integer getIdsetor() {
        return idsetor;
    }

    public void setIdsetor(Integer idsetor) {
        Integer oldIdsetor = this.idsetor;
        this.idsetor = idsetor;
        changeSupport.firePropertyChange("idsetor", oldIdsetor, idsetor);
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
        hash += (idsetor != null ? idsetor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoSetor)) {
            return false;
        }
        ProdutoSetor other = (ProdutoSetor) object;
        if ((this.idsetor == null && other.idsetor != null) || (this.idsetor != null && !this.idsetor.equals(other.idsetor))) {
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
