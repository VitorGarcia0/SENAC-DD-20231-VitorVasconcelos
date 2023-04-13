package model.vo.telefonia;

import java.util.ArrayList;
import java.util.List;

public class ClienteVO {
	
	private Integer id;
	private String nome;
	private String cpf;
	private List<TelefoneVO> telefones;
	private boolean ativo;
	private EnderecoVO endereco;
	
	public void Cliente() {
		this.telefones = new ArrayList<>();
	}
	
	public ClienteVO(Integer id, String nome, String cpf, List<TelefoneVO> telefones, boolean ativo, EnderecoVO endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefones = telefones;
		this.ativo = ativo;
		this.endereco = endereco;
	}
	public ClienteVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<TelefoneVO> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<TelefoneVO> telefones) {
		this.telefones = telefones;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public EnderecoVO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoVO endereco) {
		this.endereco = endereco;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefones=" + telefones + ", ativo=" + ativo
				+ ", endereco=" + endereco + "]";
	}

	
	

}
