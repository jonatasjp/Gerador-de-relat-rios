package geradorRelatorio;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import util.ArquivoUtil;

public abstract class GeradorDeRelatorio {

	private Map<String, Object> parametros;
	private String noArquivoJasper;
	private Collection<?> colecaoDataSource;
	private String diretorioJasper;
	private JasperPrint print;
	private String nomeDeSaidaDoArquivo;

	public GeradorDeRelatorio(Map<String, Object> parametros, String noArquivoJasper, Collection<?> colecaoDataSource, String nomeDeSaidaDoArquivo) {
		this.parametros = parametros;
		this.noArquivoJasper = noArquivoJasper;
		this.colecaoDataSource = colecaoDataSource;
		this.nomeDeSaidaDoArquivo = nomeDeSaidaDoArquivo;
		this.diretorioJasper = ArquivoUtil.obterCaminhoCompleto("jasper");
	}

	public StreamedContent gerarArquivo() throws Exception {
		StreamedContent streamedContent = null;
		try {
			JasperReport relatorioJasper =
					(JasperReport) JRLoader.loadObjectFromFile(diretorioJasper+File.separator+noArquivoJasper);
			obterJasperPrint(relatorioJasper);
			streamedContent = finalizarGeracaoArquivo();
		} catch (JRException | IOException e) {
			e.printStackTrace();
			throw new Exception("Erro ao gerar arquivo");
		}
		return streamedContent;
	}

	protected abstract StreamedContent finalizarGeracaoArquivo() throws JRException, IOException;

	private void obterJasperPrint(JasperReport relatorioJasper) throws JRException {
		if(colecaoDataSource == null || colecaoDataSource.isEmpty()) {
			print = JasperFillManager.fillReport(relatorioJasper, parametros);
		} else {
			print = JasperFillManager.fillReport(relatorioJasper, parametros, new JRBeanCollectionDataSource(colecaoDataSource));
		}
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public String getNoArquivoJasper() {
		return noArquivoJasper;
	}

	public Collection<?> getColecaoDataSource() {
		return colecaoDataSource;
	}

	public JasperPrint getPrint() {
		return print;
	}

	public String getDiretorioJasper() {
		return diretorioJasper;
	}

	public String getNomeDeSaidaDoArquivo() {
		return nomeDeSaidaDoArquivo;
	}

}