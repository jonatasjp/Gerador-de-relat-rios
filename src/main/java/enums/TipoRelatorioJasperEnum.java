package enums;

import java.util.Collection;
import java.util.Map;

import geradorRelatorio.GeradorDeRelatorio;
import geradorRelatorio.GeradorDeRelatorioDocx;
import geradorRelatorio.GeradorDeRelatorioPdf;
import geradorRelatorio.GeradorDeRelatorioRtf;
import geradorRelatorio.GeradorDeRelatorioXlsx;
import geradorRelatorio.GeradorRelatorioStrategy;

public enum TipoRelatorioJasperEnum implements GeradorRelatorioStrategy{

	DOCX("DOCX") {
		public GeradorDeRelatorio getInstanceGerador(
				Map<String, Object> parametros, String noArquivoJasper, Collection<?> colecaoDataSource,
				String nomeDeSaidaDoArquivo) {
			return new GeradorDeRelatorioDocx(parametros, noArquivoJasper, colecaoDataSource, nomeDeSaidaDoArquivo);
		}
	},

	PDF("PDF") {
		public GeradorDeRelatorio getInstanceGerador(
				Map<String, Object> parametros, String noArquivoJasper, Collection<?> colecaoDataSource,
				String nomeDeSaidaDoArquivo) {
			return new GeradorDeRelatorioPdf(parametros, noArquivoJasper, colecaoDataSource, nomeDeSaidaDoArquivo);
		}
	},

	XLSX("XLSX") {
		public GeradorDeRelatorio getInstanceGerador(
				Map<String, Object> parametros, String noArquivoJasper, Collection<?> colecaoDataSource,
				String nomeDeSaidaDoArquivo) {
			return new GeradorDeRelatorioXlsx(parametros, noArquivoJasper, colecaoDataSource, nomeDeSaidaDoArquivo);
		}
	},

	RTF("RTF") {
		public GeradorDeRelatorio getInstanceGerador(
				Map<String, Object> parametros, String noArquivoJasper, Collection<?> colecaoDataSource,
				String nomeDeSaidaDoArquivo) {
			return new GeradorDeRelatorioRtf(parametros, noArquivoJasper, colecaoDataSource, nomeDeSaidaDoArquivo);
		}
	};


	private String tpRelatorio;

	private TipoRelatorioJasperEnum(String tpRelatorio) {
		this.tpRelatorio = tpRelatorio;
	}

	public TipoRelatorioJasperEnum obterEnumPeloValor(String tpRelatorio) {
		for(TipoRelatorioJasperEnum tpRelatorioJasper : TipoRelatorioJasperEnum.values()) {
			if(tpRelatorioJasper.getTpRelatorio().equals(tpRelatorio)) {
				return tpRelatorioJasper;
			}
		}
		return null;
	}

	public String getTpRelatorio() {
		return tpRelatorio;
	}

}