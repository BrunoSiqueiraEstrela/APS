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
@Table(name = "produto_grupo", catalog = "bancoaps", schema = "")
@NamedQueries({
    @NamedQuery(name = "ProdutoGrupo.findAll", query = "SELECT p FROM ProdutoGrupo p"),
    @NamedQuery(name = "ProdutoGrupo.findByIdgrupo", query = "SELECT p FROM ProdutoGrupo p WHERE p.idgrupo = :idgrupo"),
    @NamedQuery(name = "ProdutoGrupo.findByFksetor", query = "SELECT p FROM ProdutoGrupo p WHERE p.fksetor = :fksetor"),
    @NamedQuery(name = "ProdutoGrupo.findByNome", query = "SELECT p FROM ProdutoGrupo p WHERE p.nome = :nome"),
    @NamedQuery(name = "ProdutoGrupo.findByStatus", query = "SELECT p FROM ProdutoGrupo p WHERE p.status = :status")})
public class ProdutoGrupo implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgrupo")
    private Integer idgrupo;
    @Basic(optional = false)
    @Column(name = "fksetor")
    private int fksetor;
    @Column(name = "nome")
    private String nome;
    @Column(name = "status")
    private Boolean status;

    public ProdutoGrupo() {
    }

    public ProdutoGrupo(Integer idgrupo) {
        this.idgrupo = idgrupo;
    }

    public ProdutoGrupo(Integer idgrupo, int fksetor) {
        this.idgrupo = idgrupo;
        this.fksetor = fksetor;
    }

    public Integer getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Integer idgrupo) {
        Integer oldIdgrupo = this.idgrupo;
        this.idgrupo = idgrupo;
        changeSupport.firePropertyChange("idgrupo", oldIdgrupo, idgrupo);
    }

    public int getFksetor() {
        return fksetor;
    }

    public void setFksetor(int fksetor) {
        int oldFksetor = this.fksetor;
        this.fksetor = fksetor;
        changeSupport.firePropertyChange("fksetor", oldFksetor, fksetor);
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
        hash += (idgrupo != null ? idgrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoGrupo)) {
            return false;
        }
        ProdutoGrupo other = (ProdutoGrupo) object;
        if ((this.idgrupo == null && other.idgrupo != null) || (this.idgrupo != null && !this.idgrupo.equals(other.idgrupo))) {
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
