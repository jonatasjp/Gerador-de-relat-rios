package geradorRelatorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterContext;
import net.sf.jasperreports.export.DocxExporterConfiguration;
import net.sf.jasperreports.export.DocxReportConfiguration;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class GeradorDeRelatorioDocx extends GeradorDeRelatorio {

	private static final String EXTENSAO = ".docx";

	public GeradorDeRelatorioDocx(Map<String, Object> parametros, String noArquivoJasper,
			Collection<?> colecaoDataSource, String nomeDeSaidaDoArquivo) {
		super(parametros, noArquivoJasper, colecaoDataSource, nomeDeSaidaDoArquivo);
	}

	@Override
	protected StreamedContent finalizarGeracaoArquivo() throws JRException, IOException {
		InputStream inputStream = null;

		File arquivoGerado = File.createTempFile(getNoArquivoJasper(), EXTENSAO);

		JRAbstractExporter<DocxReportConfiguration, DocxExporterConfiguration, OutputStreamExporterOutput, JRDocxExporterContext> export = new JRDocxExporter();
		export.setExporterInput(new SimpleExporterInput(getPrint()));
		export.setExporterOutput(new SimpleOutputStreamExporterOutput(arquivoGerado));

		SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
		export.setConfiguration(config);

		export.exportReport();

		arquivoGerado.deleteOnExit();
		inputStream = new FileInputStream(arquivoGerado);

		return new DefaultStreamedContent(inputStream, "application/msword", getNomeDeSaidaDoArquivo()+EXTENSAO);
	}

}