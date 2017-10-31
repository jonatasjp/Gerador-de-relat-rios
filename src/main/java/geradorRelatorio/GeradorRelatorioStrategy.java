package geradorRelatorio;

import java.util.Collection;
import java.util.Map;

public interface GeradorRelatorioStrategy {

	public GeradorDeRelatorio getInstanceGerador(
			Map<String, Object> parametros, String noArquivoJasper,
			Collection<?> colecaoDataSource, String nomeDeSaidaDoArquivo);
}
