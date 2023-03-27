package model.vo.vacina;

import java.sql.Date;

public class Vacina {
	
	private Integer id;
	private String paisOrigem;
	private String estagioPesquisa;
	private Date dataInicioPesquisa;
	private String nomeResponsavel;
	
	
	public Vacina(Integer id, String paisOrigem, String estagioPesquisa, Date dataInicioPesquisa,
			String nomeResponsavel) {
		super();
		this.id = id;
		this.paisOrigem = paisOrigem;
		this.estagioPesquisa = estagioPesquisa;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.nomeResponsavel = nomeResponsavel;
	}
	public Vacina() {
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
	public String getEstagioPesquisa() {
		return estagioPesquisa;
	}
	public void setEstagioPesquisa(String estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}
	public Date getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}
	public void setDataInicioPesquisa(Date dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
	
	
	


}
