/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Aleph & Simon
 */

public class FuncionarioBeans implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer codFuncionario;
    
    private String nomeFuncionario;
    
    private String funcaoFuncionario;
    
    private String loginFuncionario;
    
    private String senhaFuncionario;
   
    private Date admissaoFuncionario;
    
    private Integer acessoTotal;
    
    private String telefone;
   
    private String celular;
    
    private String email;
    
    private String cpf;
    
    private String rg;
    
    private int codDepartamento;
    
    private BigDecimal comissao;
    
    private String departamento;

    public FuncionarioBeans() {
        
    }

    public Integer getAcessoTotal() {
        return acessoTotal;
    }

    public void setAcessoTotal(Integer acessoTotal) {
        this.acessoTotal = acessoTotal;
    }

    public int getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(int codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public FuncionarioBeans(Integer codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public FuncionarioBeans(Integer codFuncionario, String nomeFuncionario) {
        this.codFuncionario = codFuncionario;
        this.nomeFuncionario = nomeFuncionario;
    }

    public Integer getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(Integer codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getFuncaoFuncionario() {
        return funcaoFuncionario;
    }

    public void setFuncaoFuncionario(String funcaoFuncionario) {
        this.funcaoFuncionario = funcaoFuncionario;
    }

    public String getLoginFuncionario() {
        return loginFuncionario;
    }

    public void setLoginFuncionario(String loginFuncionario) {
        this.loginFuncionario = loginFuncionario;
    }

    public String getSenhaFuncionario() {
        return senhaFuncionario;
    }

    public void setSenhaFuncionario(String senhaFuncionario) {
        this.senhaFuncionario = senhaFuncionario;
    }

    public Date getAdmissaoFuncionario() {
        return admissaoFuncionario;
    }

    public void setAdmissaoFuncionario(Date admissaoFuncionario) {
        this.admissaoFuncionario = admissaoFuncionario;
    }

  

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
     
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codFuncionario != null ? codFuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuncionarioBeans)) {
            return false;
        }
        FuncionarioBeans other = (FuncionarioBeans) object;
        if ((this.codFuncionario == null && other.codFuncionario != null) || (this.codFuncionario != null && !this.codFuncionario.equals(other.codFuncionario))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "UsuarioBeans [codFuncionario=" + codFuncionario + ", nomeFuncionario=" + nomeFuncionario
				+ ", funcaoFuncionario=" + funcaoFuncionario + ", loginFuncionario=" + loginFuncionario
				+ ", senhaFuncionario=" + senhaFuncionario + ", admissaoFuncionario=" + admissaoFuncionario
				+ ", acessoTotal=" + acessoTotal + ", telefone=" + telefone + ", celular=" + celular + ", email="
				+ email + ", cpf=" + cpf + ", rg=" + rg + ", codDepartamento=" + codDepartamento + ", comissao="
				+ comissao + ", departamento=" + departamento + "]";
	}

}
