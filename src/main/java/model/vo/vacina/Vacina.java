package model.vo.vacina;

import java.time.LocalDateTime;

public class Vacina {
	
	private Integer id;
	private String paisOrigem;
	private int estagioPesquisa;
	private LocalDateTime dataInicioPesquisa;
	private String nomeResponsavel;
	
	public Vacina(Integer id, String paisOrigem, int estagioPesquisa, LocalDateTime dataInicioPesquisa,
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
	public int getEstagioPesquisa() {
		return estagioPesquisa;
	}
	public void setEstagioPesquisa(int estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}
	public LocalDateTime getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}
	public void setDataInicioPesquisa(LocalDateTime dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
	
	

}
