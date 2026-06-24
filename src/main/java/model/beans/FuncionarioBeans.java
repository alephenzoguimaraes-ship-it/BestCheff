/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class FuncionarioBeans.
 *
 * @author Aleph & Simon
 */

public class FuncionarioBeans implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The cod funcionario. */
    private Integer codFuncionario;
    
    /** The nome funcionario. */
    private String nomeFuncionario;
    
    /** The funcao funcionario. */
    private String funcaoFuncionario;
    
    /** The login funcionario. */
    private String loginFuncionario;
    
    /** The senha funcionario. */
    private String senhaFuncionario;
   
    /** The admissao funcionario. */
    private Date admissaoFuncionario;
    
    /** The acesso total. */
    private Integer acessoTotal;
    
    /** The telefone. */
    private String telefone;
   
    /** The celular. */
    private String celular;
    
    /** The email. */
    private String email;
    
    /** The cpf. */
    private String cpf;
    
    /** The rg. */
    private String rg;
    
    /** The cod departamento. */
    private int codDepartamento;
    
    /** The comissao. */
    private BigDecimal comissao;
    
    /** The departamento. */
    private String departamento;

    /**
     * Instantiates a new funcionario beans.
     */
    public FuncionarioBeans() {
        
    }

    /**
     * Gets the acesso total.
     *
     * @return the acesso total
     */
    public Integer getAcessoTotal() {
        return acessoTotal;
    }

    /**
     * Sets the acesso total.
     *
     * @param acessoTotal the new acesso total
     */
    public void setAcessoTotal(Integer acessoTotal) {
        this.acessoTotal = acessoTotal;
    }

    /**
     * Gets the cod departamento.
     *
     * @return the cod departamento
     */
    public int getCodDepartamento() {
        return codDepartamento;
    }

    /**
     * Sets the cod departamento.
     *
     * @param codDepartamento the new cod departamento
     */
    public void setCodDepartamento(int codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    /**
     * Instantiates a new funcionario beans.
     *
     * @param codFuncionario the cod funcionario
     */
    public FuncionarioBeans(Integer codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    /**
     * Instantiates a new funcionario beans.
     *
     * @param codFuncionario the cod funcionario
     * @param nomeFuncionario the nome funcionario
     */
    public FuncionarioBeans(Integer codFuncionario, String nomeFuncionario) {
        this.codFuncionario = codFuncionario;
        this.nomeFuncionario = nomeFuncionario;
    }

    /**
     * Gets the cod funcionario.
     *
     * @return the cod funcionario
     */
    public Integer getCodFuncionario() {
        return codFuncionario;
    }

    /**
     * Sets the cod funcionario.
     *
     * @param codFuncionario the new cod funcionario
     */
    public void setCodFuncionario(Integer codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    /**
     * Gets the nome funcionario.
     *
     * @return the nome funcionario
     */
    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    /**
     * Sets the nome funcionario.
     *
     * @param nomeFuncionario the new nome funcionario
     */
    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    /**
     * Gets the funcao funcionario.
     *
     * @return the funcao funcionario
     */
    public String getFuncaoFuncionario() {
        return funcaoFuncionario;
    }

    /**
     * Sets the funcao funcionario.
     *
     * @param funcaoFuncionario the new funcao funcionario
     */
    public void setFuncaoFuncionario(String funcaoFuncionario) {
        this.funcaoFuncionario = funcaoFuncionario;
    }

    /**
     * Gets the login funcionario.
     *
     * @return the login funcionario
     */
    public String getLoginFuncionario() {
        return loginFuncionario;
    }

    /**
     * Sets the login funcionario.
     *
     * @param loginFuncionario the new login funcionario
     */
    public void setLoginFuncionario(String loginFuncionario) {
        this.loginFuncionario = loginFuncionario;
    }

    /**
     * Gets the senha funcionario.
     *
     * @return the senha funcionario
     */
    public String getSenhaFuncionario() {
        return senhaFuncionario;
    }

    /**
     * Sets the senha funcionario.
     *
     * @param senhaFuncionario the new senha funcionario
     */
    public void setSenhaFuncionario(String senhaFuncionario) {
        this.senhaFuncionario = senhaFuncionario;
    }

    /**
     * Gets the admissao funcionario.
     *
     * @return the admissao funcionario
     */
    public Date getAdmissaoFuncionario() {
        return admissaoFuncionario;
    }

    /**
     * Sets the admissao funcionario.
     *
     * @param admissaoFuncionario the new admissao funcionario
     */
    public void setAdmissaoFuncionario(Date admissaoFuncionario) {
        this.admissaoFuncionario = admissaoFuncionario;
    }

  

    /**
     * Gets the telefone.
     *
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Sets the telefone.
     *
     * @param telefone the new telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Gets the celular.
     *
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * Sets the celular.
     *
     * @param celular the new celular
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the cpf.
     *
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Gets the rg.
     *
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * Sets the rg.
     *
     * @param rg the new rg
     */
    public void setRg(String rg) {
        this.rg = rg;
    }
    
    /**
     * Sets the cpf.
     *
     * @param cpf the new cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Gets the comissao.
     *
     * @return the comissao
     */
    public BigDecimal getComissao() {
        return comissao;
    }

    /**
     * Sets the comissao.
     *
     * @param comissao the new comissao
     */
    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    /**
     * Gets the departamento.
     *
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the departamento.
     *
     * @param departamento the new departamento
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
     
    
    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codFuncionario != null ? codFuncionario.hashCode() : 0);
        return hash;
    }

    /**
     * Equals.
     *
     * @param object the object
     * @return true, if successful
     */
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

	/**
	 * To string.
	 *
	 * @return the string
	 */
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
