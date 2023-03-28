package model.vo.vacina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PessoaVO {

	private int id;
	private String nome;
	private LocalDate dt_Nascimento;
	private String sexo;
	private String cpf;
	private int tipoPessoaVO;

	public PessoaVO(int id, String nome, LocalDate dt_Nascimento, String sexo, String cpf, int tipoPessoaVO) {
		super();
		this.id = id;
		this.nome = nome;
		this.dt_Nascimento = dt_Nascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipoPessoaVO = tipoPessoaVO;
	}
	
	public PessoaVO(String nome, LocalDate dt_Nascimento, String sexo, String cpf, int tipoPessoaVO) {
		super();
		this.nome = nome;
		this.dt_Nascimento = dt_Nascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipoPessoaVO = tipoPessoaVO;
	}

	public PessoaVO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDt_Nascimento() {
		return dt_Nascimento;
	}

	public void setDt_Nascimento(LocalDate dt_Nascimento) {
		this.dt_Nascimento = dt_Nascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getTipoPessoaVO() {
		return tipoPessoaVO;
	}

	public void setTipoPessoaVO(int tipoPessoaVO) {
		this.tipoPessoaVO = tipoPessoaVO;
	}

	@SuppressWarnings("unused")
	private String validarData(LocalDate dt_Nascimento) {
		String resultado = "";
		if (dt_Nascimento != null) {
			resultado = dt_Nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		return resultado;
	}

	@Override
	public String toString() {
		return "PessoaVO: id:" + id + ", nome:" + nome + ", dt_Nascimento:" + dt_Nascimento + ", sexo:" + sexo
				+ ", cpf:" + cpf + ", tipoPessoaVO:" + tipoPessoaVO;
	}
	
}
