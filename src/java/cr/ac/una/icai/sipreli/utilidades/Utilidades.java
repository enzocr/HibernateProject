package cr.ac.una.icai.sipreli.utilidades;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 *
 * @author Enzo Quartino Zamora
 * <github.com/enzocr || email: enzoquartino@gmail.com>
 */
public class Utilidades {

    public static String obtenerMsj(String key) {

        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        String messagesBaseName = "cr.ac.una.icai.sipreli.propiedades.etiquetas";

        if (locale == null || context == null) {
            locale = Locale.getDefault();
        } else {
            if (context.getViewRoot() != null) {
                locale = context.getViewRoot().getLocale();
            }
        }
        String resourceString;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(messagesBaseName,
                    locale);
            resourceString = bundle.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }

        return resourceString;
    }

    public static Date addDays(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 5);
        return cal.getTime();
    }
}
