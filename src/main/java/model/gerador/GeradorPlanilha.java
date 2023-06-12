package model.gerador;

import java.util.List;

import model.vo.telefonia.ClienteVO;

public class GeradorPlanilha {
	
	public byte[] gerarPlanilhaClientes(List<ClienteVO> clientes) {
		byte[] arquivoXls = null;
		
		// HSSFWorkbook arquivoExcel = new HSSFWorkbook(); // Criando a pasta de trabalho
		// HSSFSheet abaPlanilha = arquivoExcel.createSheet("Clientes");  // Criando a aba da planilha
		return arquivoXls;
		
		// Colocar a biblioteca do Apache no POM
		// FileOutputStream - vai pegar os dados e escrever no disco (arquivo)
		// Fazer a validação no cliente Controller, lançar as exceções
		//
	}
}
