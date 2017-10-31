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
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporterContext;
import net.sf.jasperreports.export.RtfExporterConfiguration;
import net.sf.jasperreports.export.RtfReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleRtfReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.WriterExporterOutput;

public class GeradorDeRelatorioRtf extends GeradorDeRelatorio {

	private static final String EXTENSAO = ".rtf";

	public GeradorDeRelatorioRtf(Map<String, Object> parametros, String noArquivoJasper,
			Collection<?> colecaoDataSource, String nomeDeSaidaDoArquivo) {
		super(parametros, noArquivoJasper, colecaoDataSource, nomeDeSaidaDoArquivo);
	}

	@Override
	protected StreamedContent finalizarGeracaoArquivo() throws JRException, IOException{
		InputStream inputStream = null;

		File arquivoGerado = File.createTempFile(getNoArquivoJasper(), EXTENSAO);

		JRAbstractExporter<RtfReportConfiguration, RtfExporterConfiguration, WriterExporterOutput, JRRtfExporterContext> exportrtf = new JRRtfExporter();
		exportrtf.setExporterInput(new SimpleExporterInput(getPrint()));
		exportrtf.setExporterOutput(new SimpleWriterExporterOutput(arquivoGerado));

		SimpleRtfReportConfiguration configpdf = new SimpleRtfReportConfiguration();
		exportrtf.setConfiguration(configpdf);

		exportrtf.exportReport();

		arquivoGerado.deleteOnExit();
		inputStream = new FileInputStream(arquivoGerado);

		return new DefaultStreamedContent(inputStream, "application/rtf", getNomeDeSaidaDoArquivo()+EXTENSAO);
	}

}
