package model.seletor;

public abstract class BaseSeletor {
	
	private int pagina;
	private int limite;
	
	public BaseSeletor() {
		
		this.pagina = 0;
		this.limite = 0;
	}

	public boolean temPaginacao() {
		return this.limite > 0 && this.pagina > 0;
	
	}
	// Vai fazer a 
	public int getOffset() {
		return (this.limite * (this.pagina - 1));
	}
	
	
	//MÉTODO ABSTRATO para o herdeiro implementar
	public abstract boolean temFiltro();
}
