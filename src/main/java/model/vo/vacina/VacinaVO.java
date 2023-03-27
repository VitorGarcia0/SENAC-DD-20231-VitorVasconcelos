package model.vo.vacina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VacinaVO {

	private Integer id;
	private String paisOrigem;
	private int estagioPesquisa;
	private LocalDate dataInicioPesquisa;
	private PessoaVO responsavelPesquisa;

	public VacinaVO(Integer id, String paisOrigem, int estagioPesquisa, LocalDate dataInicioPesquisa,
			PessoaVO responsavelPesquisa) {
		super();
		this.id = id;
		this.paisOrigem = paisOrigem;
		this.estagioPesquisa = estagioPesquisa;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.responsavelPesquisa = responsavelPesquisa;
	}

	public VacinaVO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public int getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(int estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public PessoaVO getResponsavelPesquisa() {
		return responsavelPesquisa;
	}

	public void setResponsavelPesquisa(PessoaVO responsavelPesquisa) {
		this.responsavelPesquisa = responsavelPesquisa;
	}

	@SuppressWarnings("unused")
	private String validarData(LocalDate dt_Nascimento) {
		String resultado = "";
		if (dt_Nascimento != null) {
			resultado = dt_Nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		return resultado;
	}

}
