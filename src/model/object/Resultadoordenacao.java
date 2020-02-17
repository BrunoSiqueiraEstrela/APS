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
@Table(name = "resultadoordenacao", catalog = "bancoaps", schema = "")
@NamedQueries({
    @NamedQuery(name = "Resultadoordenacao.findAll", query = "SELECT r FROM Resultadoordenacao r"),
    @NamedQuery(name = "Resultadoordenacao.findByCodigo", query = "SELECT r FROM Resultadoordenacao r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Resultadoordenacao.findByMetodoordenacao", query = "SELECT r FROM Resultadoordenacao r WHERE r.metodoordenacao = :metodoordenacao"),
    @NamedQuery(name = "Resultadoordenacao.findByTabela", query = "SELECT r FROM Resultadoordenacao r WHERE r.tabela = :tabela"),
    @NamedQuery(name = "Resultadoordenacao.findByColuna", query = "SELECT r FROM Resultadoordenacao r WHERE r.coluna = :coluna"),
    @NamedQuery(name = "Resultadoordenacao.findByRegistros", query = "SELECT r FROM Resultadoordenacao r WHERE r.registros = :registros"),
    @NamedQuery(name = "Resultadoordenacao.findByTempo", query = "SELECT r FROM Resultadoordenacao r WHERE r.tempo = :tempo")})
public class Resultadoordenacao implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "metodoordenacao")
    private String metodoordenacao;
    @Column(name = "tabela")
    private String tabela;
    @Column(name = "coluna")
    private String coluna;
    @Column(name = "registros")
    private Integer registros;
    @Column(name = "tempo")
    private Integer tempo;

    public Resultadoordenacao() {
    }

    public Resultadoordenacao(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        Integer oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public String getMetodoordenacao() {
        return metodoordenacao;
    }

    public void setMetodoordenacao(String metodoordenacao) {
        String oldMetodoordenacao = this.metodoordenacao;
        this.metodoordenacao = metodoordenacao;
        changeSupport.firePropertyChange("metodoordenacao", oldMetodoordenacao, metodoordenacao);
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        String oldTabela = this.tabela;
        this.tabela = tabela;
        changeSupport.firePropertyChange("tabela", oldTabela, tabela);
    }

    public String getColuna() {
        return coluna;
    }

    public void setColuna(String coluna) {
        String oldColuna = this.coluna;
        this.coluna = coluna;
        changeSupport.firePropertyChange("coluna", oldColuna, coluna);
    }

    public Integer getRegistros() {
        return registros;
    }

    public void setRegistros(Integer registros) {
        Integer oldRegistros = this.registros;
        this.registros = registros;
        changeSupport.firePropertyChange("registros", oldRegistros, registros);
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        Integer oldTempo = this.tempo;
        this.tempo = tempo;
        changeSupport.firePropertyChange("tempo", oldTempo, tempo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultadoordenacao)) {
            return false;
        }
        Resultadoordenacao other = (Resultadoordenacao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "views.Resultadoordenacao[ codigo=" + codigo + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
