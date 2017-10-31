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
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterContext;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.PdfReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

public class GeradorDeRelatorioPdf extends GeradorDeRelatorio {

	private static final String EXTENSAO = ".pdf";

	public GeradorDeRelatorioPdf(Map<String, Object> parametros, String noArquivoJasper,
			Collection<?> colecaoDataSource, String nomeDeSaidaDoArquivo) {
		super(parametros, noArquivoJasper, colecaoDataSource, nomeDeSaidaDoArquivo);
	}

	@Override
	protected StreamedContent finalizarGeracaoArquivo() throws JRException, IOException{
		InputStream inputStream = null;

		File arquivoGerado = File.createTempFile(getNoArquivoJasper(), EXTENSAO);

		JRAbstractExporter<PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput, JRPdfExporterContext> exportpdf = new JRPdfExporter();
		exportpdf.setExporterInput(new SimpleExporterInput(getPrint()));
		exportpdf.setExporterOutput(new SimpleOutputStreamExporterOutput(arquivoGerado));

		SimplePdfReportConfiguration configpdf = new SimplePdfReportConfiguration();
		exportpdf.setConfiguration(configpdf);

		exportpdf.exportReport();

		arquivoGerado.deleteOnExit();
		inputStream = new FileInputStream(arquivoGerado);

		return new DefaultStreamedContent(inputStream, "application/pdf", getNomeDeSaidaDoArquivo()+EXTENSAO);
	}

}