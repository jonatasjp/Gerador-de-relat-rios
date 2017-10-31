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
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporterContext;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.export.XlsxExporterConfiguration;
import net.sf.jasperreports.export.XlsxReportConfiguration;

public class GeradorDeRelatorioXlsx extends GeradorDeRelatorio{

	private static final String EXTENSAO = ".xlsx";

	public GeradorDeRelatorioXlsx(Map<String, Object> parametros, String noArquivoJasper,
			Collection<?> colecaoDataSource, String nomeDeSaidaDoArquivo) {
		super(parametros, noArquivoJasper, colecaoDataSource, nomeDeSaidaDoArquivo);
	}

	@Override
	protected StreamedContent finalizarGeracaoArquivo() throws JRException, IOException{
		InputStream inputStream = null;

		File arquivoGerado = File.createTempFile(getNoArquivoJasper(), EXTENSAO);

		JRAbstractExporter<XlsxReportConfiguration, XlsxExporterConfiguration, OutputStreamExporterOutput, JRXlsxExporterContext> exportxlsx = new JRXlsxExporter();
		exportxlsx.setExporterInput(new SimpleExporterInput(getPrint()));
		exportxlsx.setExporterOutput(new SimpleOutputStreamExporterOutput(arquivoGerado));

		SimpleXlsxReportConfiguration configxlsx = new SimpleXlsxReportConfiguration();
		exportxlsx.setConfiguration(configxlsx);

		exportxlsx.exportReport();

		arquivoGerado.deleteOnExit();
		inputStream = new FileInputStream(arquivoGerado);

		return new DefaultStreamedContent(inputStream, "application/vnd.ms-excel", getNomeDeSaidaDoArquivo()+EXTENSAO);
	}

}
