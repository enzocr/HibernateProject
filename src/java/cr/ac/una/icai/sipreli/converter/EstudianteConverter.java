package cr.ac.una.icai.sipreli.converter;

import cr.ac.una.icai.sipreli.clases.Estudiante;
import cr.ac.una.icai.sipreli.clases.Libro;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter("estudianteConverter")
public class EstudianteConverter implements Converter {

	/** vea @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)*/
	public Object getAsObject(FacesContext context, UIComponent arg1, String valor) {
		try {			
			Estudiante estudiante = new Estudiante();
			String[] valores = valor.split("~");
			if(valores.length == 2){
                                estudiante.setCarnet(Integer.parseInt(valores[0].trim()));
                                estudiante.setNombre((valores[1].trim()));
				return estudiante;				
			} else{		
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error 1 de conversion de Estudiante");
				throw new ConverterException(msg);				
			}
		} catch (NumberFormatException e){
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("Error 2 de conversion de Estudiante");
			throw new ConverterException(msg);
		}
	}

	/** vea @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)*/
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		String valor = "";
		if(object!=null){
			if(object instanceof Estudiante){ 		
                                Estudiante estudiante = (Estudiante) object;
                                valor = estudiante.getCarnet()+ "~" + estudiante.getNombre();
                        }else{			
				FacesMessage msg = new FacesMessage();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				msg.setSummary("Error de conversion, el objeto no corresponde a un Estudiante");
				throw new ConverterException(msg);			
			}
		}else{
			valor = null;
		}
		return valor;
	}
}
