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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author DELTA
 */
@Entity
@Table(name = "venda", catalog = "bancoaps", schema = "")
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findByIdvenda", query = "SELECT v FROM Venda v WHERE v.idvenda = :idvenda"),
    @NamedQuery(name = "Venda.findByFkfuncionario", query = "SELECT v FROM Venda v WHERE v.fkfuncionario = :fkfuncionario"),
    @NamedQuery(name = "Venda.findByFkcliente", query = "SELECT v FROM Venda v WHERE v.fkcliente = :fkcliente"),
    @NamedQuery(name = "Venda.findByData", query = "SELECT v FROM Venda v WHERE v.data = :data"),
    @NamedQuery(name = "Venda.findByHora", query = "SELECT v FROM Venda v WHERE v.hora = :hora"),
    @NamedQuery(name = "Venda.findByValor", query = "SELECT v FROM Venda v WHERE v.valor = :valor"),
    @NamedQuery(name = "Venda.findByDesconto", query = "SELECT v FROM Venda v WHERE v.desconto = :desconto"),
    @NamedQuery(name = "Venda.findByDescontors", query = "SELECT v FROM Venda v WHERE v.descontors = :descontors"),
    @NamedQuery(name = "Venda.findByValorfinal", query = "SELECT v FROM Venda v WHERE v.valorfinal = :valorfinal"),
    @NamedQuery(name = "Venda.findByTroco", query = "SELECT v FROM Venda v WHERE v.troco = :troco"),
    @NamedQuery(name = "Venda.findByFkformapagamento", query = "SELECT v FROM Venda v WHERE v.fkformapagamento = :fkformapagamento"),
    @NamedQuery(name = "Venda.findByObservacao", query = "SELECT v FROM Venda v WHERE v.observacao = :observacao"),
    @NamedQuery(name = "Venda.findByObservacaocancelamento", query = "SELECT v FROM Venda v WHERE v.observacaocancelamento = :observacaocancelamento"),
    @NamedQuery(name = "Venda.findByStatus", query = "SELECT v FROM Venda v WHERE v.status = :status")})
public class Venda implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvenda")
    private Integer idvenda;
    @Column(name = "fkfuncionario")
    private Integer fkfuncionario;
    @Column(name = "fkcliente")
    private Integer fkcliente;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "desconto")
    private BigDecimal desconto;
    @Column(name = "descontors")
    private BigDecimal descontors;
    @Column(name = "valorfinal")
    private BigDecimal valorfinal;
    @Column(name = "troco")
    private BigDecimal troco;
    @Column(name = "fkformapagamento")
    private Integer fkformapagamento;
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "observacaocancelamento")
    private String observacaocancelamento;
    @Column(name = "status")
    private Boolean status;

    public Venda() {
    }

    public Venda(Integer idvenda) {
        this.idvenda = idvenda;
    }

    public Integer getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(Integer idvenda) {
        Integer oldIdvenda = this.idvenda;
        this.idvenda = idvenda;
        changeSupport.firePropertyChange("idvenda", oldIdvenda, idvenda);
    }

    public Integer getFkfuncionario() {
        return fkfuncionario;
    }

    public void setFkfuncionario(Integer fkfuncionario) {
        Integer oldFkfuncionario = this.fkfuncionario;
        this.fkfuncionario = fkfuncionario;
        changeSupport.firePropertyChange("fkfuncionario", oldFkfuncionario, fkfuncionario);
    }

    public Integer getFkcliente() {
        return fkcliente;
    }

    public void setFkcliente(Integer fkcliente) {
        Integer oldFkcliente = this.fkcliente;
        this.fkcliente = fkcliente;
        changeSupport.firePropertyChange("fkcliente", oldFkcliente, fkcliente);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        Date oldData = this.data;
        this.data = data;
        changeSupport.firePropertyChange("data", oldData, data);
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        Date oldHora = this.hora;
        this.hora = hora;
        changeSupport.firePropertyChange("hora", oldHora, hora);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        BigDecimal oldValor = this.valor;
        this.valor = valor;
        changeSupport.firePropertyChange("valor", oldValor, valor);
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        BigDecimal oldDesconto = this.desconto;
        this.desconto = desconto;
        changeSupport.firePropertyChange("desconto", oldDesconto, desconto);
    }

    public BigDecimal getDescontors() {
        return descontors;
    }

    public void setDescontors(BigDecimal descontors) {
        BigDecimal oldDescontors = this.descontors;
        this.descontors = descontors;
        changeSupport.firePropertyChange("descontors", oldDescontors, descontors);
    }

    public BigDecimal getValorfinal() {
        return valorfinal;
    }

    public void setValorfinal(BigDecimal valorfinal) {
        BigDecimal oldValorfinal = this.valorfinal;
        this.valorfinal = valorfinal;
        changeSupport.firePropertyChange("valorfinal", oldValorfinal, valorfinal);
    }

    public BigDecimal getTroco() {
        return troco;
    }

    public void setTroco(BigDecimal troco) {
        BigDecimal oldTroco = this.troco;
        this.troco = troco;
        changeSupport.firePropertyChange("troco", oldTroco, troco);
    }

    public Integer getFkformapagamento() {
        return fkformapagamento;
    }

    public void setFkformapagamento(Integer fkformapagamento) {
        Integer oldFkformapagamento = this.fkformapagamento;
        this.fkformapagamento = fkformapagamento;
        changeSupport.firePropertyChange("fkformapagamento", oldFkformapagamento, fkformapagamento);
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        String oldObservacao = this.observacao;
        this.observacao = observacao;
        changeSupport.firePropertyChange("observacao", oldObservacao, observacao);
    }

    public String getObservacaocancelamento() {
        return observacaocancelamento;
    }

    public void setObservacaocancelamento(String observacaocancelamento) {
        String oldObservacaocancelamento = this.observacaocancelamento;
        this.observacaocancelamento = observacaocancelamento;
        changeSupport.firePropertyChange("observacaocancelamento", oldObservacaocancelamento, observacaocancelamento);
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
        hash += (idvenda != null ? idvenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.idvenda == null && other.idvenda != null) || (this.idvenda != null && !this.idvenda.equals(other.idvenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "views.Venda[ idvenda=" + idvenda + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
