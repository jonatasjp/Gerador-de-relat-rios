package util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class ArquivoUtil {

	private ArquivoUtil(){
	}

	public static String obterCaminhoCompleto(String path){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext contextoExterno = context.getExternalContext();
		ServletContext servletContext = (ServletContext) contextoExterno.getContext();
		return servletContext.getRealPath(path);
	}

}
