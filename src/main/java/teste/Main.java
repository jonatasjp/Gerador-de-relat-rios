package teste;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.StreamedContent;

import enums.TipoRelatorioJasperEnum;
import geradorRelatorio.GeradorDeRelatorio;

public class Main {

	public Main() throws Exception {
		
		//Substituir pela coleção desejada
		List<Object> colecaoExtrato = new ArrayList<Object>();
		
		String noArquivoJasper = "NOMEDORELATORIO.jasper";
		String noArquidoDeSaida = "NOMEDESAIDADORELATORIO";
		Map<String, Object> parametros = new HashMap<>();

		GeradorDeRelatorio geradorRelatorio = TipoRelatorioJasperEnum.RTF.getInstanceGerador(parametros, noArquivoJasper, colecaoExtrato, noArquidoDeSaida);
		parametros.put("SUBREPORT_DIR", geradorRelatorio.getDiretorioJasper()+File.separator);

		//Objeto StreamedContent do primefaces que é responsável por gerar um download.
		StreamedContent file = geradorRelatorio.gerarArquivo();
	
	}
}
