package model.vo.vacina;

public enum TipoPessoaVO {
	
	SOMENTE_PESQUISADORES (1),
	VOLUNTARIOS (2),
	PUBLICO_GERAL (3);
	
	private int valor;

	private TipoPessoaVO(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public static TipoPessoaVO getTipoPessoaVOPorValor(int valor) {
		TipoPessoaVO tipoPessoaVO = null;
		for(TipoPessoaVO elemento : TipoPessoaVO.values()) {
			if(elemento.getValor() == valor) {
				tipoPessoaVO = elemento;
			}
		}
		return tipoPessoaVO;
	}
	
}
