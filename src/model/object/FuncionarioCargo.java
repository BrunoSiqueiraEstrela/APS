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
@Table(name = "funcionario_cargo", catalog = "bancoaps", schema = "")
@NamedQueries({
    @NamedQuery(name = "FuncionarioCargo.findAll", query = "SELECT f FROM FuncionarioCargo f"),
    @NamedQuery(name = "FuncionarioCargo.findByIdcargo", query = "SELECT f FROM FuncionarioCargo f WHERE f.idcargo = :idcargo"),
    @NamedQuery(name = "FuncionarioCargo.findByDescricao", query = "SELECT f FROM FuncionarioCargo f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FuncionarioCargo.findByStatus", query = "SELECT f FROM FuncionarioCargo f WHERE f.status = :status")})
public class FuncionarioCargo implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcargo")
    private Integer idcargo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "status")
    private Boolean status;

    public FuncionarioCargo() {
    }

    public FuncionarioCargo(Integer idcargo) {
        this.idcargo = idcargo;
    }

    public Integer getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Integer idcargo) {
        Integer oldIdcargo = this.idcargo;
        this.idcargo = idcargo;
        changeSupport.firePropertyChange("idcargo", oldIdcargo, idcargo);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
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
        hash += (idcargo != null ? idcargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuncionarioCargo)) {
            return false;
        }
        FuncionarioCargo other = (FuncionarioCargo) object;
        if ((this.idcargo == null && other.idcargo != null) || (this.idcargo != null && !this.idcargo.equals(other.idcargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
