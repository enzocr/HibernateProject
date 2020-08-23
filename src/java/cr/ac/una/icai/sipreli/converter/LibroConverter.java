package cr.ac.una.icai.sipreli.converter;

import cr.ac.una.icai.sipreli.clases.Libro;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter("libroConverter")
public class LibroConverter implements Converter {

	/** vea @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)*/
	public Object getAsObject(FacesContext context, UIComponent arg1, String valor) {
		try {			
			Libro libro = new Libro();
			String[] valores = valor.split("~");
			if(valores.length == 2){
                                libro.setIsbn(Integer.parseInt(valores[0].trim()));
                                libro.setNombre((valores[1].trim()));
				return libro;				
			} else{		
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error 1 de conversion de Libro");
				throw new ConverterException(msg);				
			}
		} catch (NumberFormatException e){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error 2 de conversion de Libro");
			throw new ConverterException(msg);
		}
	}

	/** vea @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)*/
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		String valor = "";
		if(object!=null){
			if(object instanceof Libro){ 		
                                Libro libro = (Libro) object;
                                valor = libro.getIsbn()+ "~" + libro.getNombre();
                        }else{			
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error de conversion, el objeto no corresponde a un Libro");
				throw new ConverterException(msg);			
			}
		}else{
			valor = null;
		}
		return valor;
	}
}
