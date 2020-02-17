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
@Table(name = "forma_pagamento", catalog = "bancoaps", schema = "")
@NamedQueries({
    @NamedQuery(name = "FormaPagamento.findAll", query = "SELECT f FROM FormaPagamento f"),
    @NamedQuery(name = "FormaPagamento.findByIdformapagamento", query = "SELECT f FROM FormaPagamento f WHERE f.idformapagamento = :idformapagamento"),
    @NamedQuery(name = "FormaPagamento.findByDescricao", query = "SELECT f FROM FormaPagamento f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FormaPagamento.findByStatus", query = "SELECT f FROM FormaPagamento f WHERE f.status = :status")})
public class FormaPagamento implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idformapagamento")
    private Integer idformapagamento;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "status")
    private Boolean status;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer idformapagamento) {
        this.idformapagamento = idformapagamento;
    }

    public Integer getIdformapagamento() {
        return idformapagamento;
    }

    public void setIdformapagamento(Integer idformapagamento) {
        Integer oldIdformapagamento = this.idformapagamento;
        this.idformapagamento = idformapagamento;
        changeSupport.firePropertyChange("idformapagamento", oldIdformapagamento, idformapagamento);
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
        hash += (idformapagamento != null ? idformapagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPagamento)) {
            return false;
        }
        FormaPagamento other = (FormaPagamento) object;
        if ((this.idformapagamento == null && other.idformapagamento != null) || (this.idformapagamento != null && !this.idformapagamento.equals(other.idformapagamento))) {
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
