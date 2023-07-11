package Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * Classe che formatta la visualizzazione delle date
 * @author Christofer Fanò
 */
@SuppressWarnings("serial")
public class DateFormatter extends AbstractFormatter {

    private final String datePattern = "dd-MM-yyyy";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    /**
     * Restituisce la stringa convertita in oggetto
     * @param text stringa da convertire
     * @return stringa convertita in oggetto
     * @throws ParseException genera l'eccezione nel caso in cui non si riesca a convertire
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    /**
     * Restituisce l'oggetto convertito in stringa
     * @param value oggetto da convertire
     * @return oggetto convertito in stringa
     */
    @Override
    public String valueToString(Object value) {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}