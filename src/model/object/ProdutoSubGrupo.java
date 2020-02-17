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
@Table(name = "produto_subgrupo", catalog = "bancoaps", schema = "")
@NamedQueries({
    @NamedQuery(name = "ProdutoSubgrupo.findAll", query = "SELECT p FROM ProdutoSubgrupo p"),
    @NamedQuery(name = "ProdutoSubgrupo.findByIdsubgrupo", query = "SELECT p FROM ProdutoSubgrupo p WHERE p.idsubgrupo = :idsubgrupo"),
    @NamedQuery(name = "ProdutoSubgrupo.findByFkgrupo", query = "SELECT p FROM ProdutoSubgrupo p WHERE p.fkgrupo = :fkgrupo"),
    @NamedQuery(name = "ProdutoSubgrupo.findByNome", query = "SELECT p FROM ProdutoSubgrupo p WHERE p.nome = :nome"),
    @NamedQuery(name = "ProdutoSubgrupo.findByStatus", query = "SELECT p FROM ProdutoSubgrupo p WHERE p.status = :status")})
public class ProdutoSubGrupo implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubgrupo")
    private Integer idsubgrupo;
    @Basic(optional = false)
    @Column(name = "fkgrupo")
    private int fkgrupo;
    @Column(name = "nome")
    private String nome;
    @Column(name = "status")
    private Boolean status;

    public ProdutoSubGrupo() {
    }

    public ProdutoSubGrupo(Integer idsubgrupo) {
        this.idsubgrupo = idsubgrupo;
    }

    public ProdutoSubGrupo(Integer idsubgrupo, int fkgrupo) {
        this.idsubgrupo = idsubgrupo;
        this.fkgrupo = fkgrupo;
    }

    public Integer getIdsubgrupo() {
        return idsubgrupo;
    }

    public void setIdsubgrupo(Integer idsubgrupo) {
        Integer oldIdsubgrupo = this.idsubgrupo;
        this.idsubgrupo = idsubgrupo;
        changeSupport.firePropertyChange("idsubgrupo", oldIdsubgrupo, idsubgrupo);
    }

    public int getFkgrupo() {
        return fkgrupo;
    }

    public void setFkgrupo(int fkgrupo) {
        int oldFkgrupo = this.fkgrupo;
        this.fkgrupo = fkgrupo;
        changeSupport.firePropertyChange("fkgrupo", oldFkgrupo, fkgrupo);
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
        hash += (idsubgrupo != null ? idsubgrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoSubGrupo)) {
            return false;
        }
        ProdutoSubGrupo other = (ProdutoSubGrupo) object;
        if ((this.idsubgrupo == null && other.idsubgrupo != null) || (this.idsubgrupo != null && !this.idsubgrupo.equals(other.idsubgrupo))) {
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
