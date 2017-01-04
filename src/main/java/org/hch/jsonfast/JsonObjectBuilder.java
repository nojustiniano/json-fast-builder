package org.hch.jsonfast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hernan on 24/11/16.
 */
public class JsonObjectBuilder{

    private static final char[] NULL = {'n', 'u', 'l', 'l'};
    private static final char QUOTE = '"';
    private static final char COMMA = ',';
    private static final char COLON = ':';
    private static final char[] COMMA_QUOTE = {COMMA, QUOTE};
    private static final char[] QUOTE_COLON = {QUOTE, COLON};
    private static final char[] QUOTE_COLON_QUOTE = {QUOTE, COLON, QUOTE};
    private StringBuilder stringBuilder;
    private NumberFormat numberFormat;
    private SimpleDateFormat simpleDateFormat;

    public JsonObjectBuilder() {
        this(255);
    }

    public JsonObjectBuilder(int initialCapacity) {
        stringBuilder = new StringBuilder(initialCapacity).append('{');
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        numberFormat = new DecimalFormat(Properties.DECIMAL_FORMAT, otherSymbols);
        simpleDateFormat = new SimpleDateFormat(Properties.ISO_DATE_FORMAT);
    }

    public JsonObjectBuilder(JsonObjectBuilder jsonObjectBuilder){
        stringBuilder = new StringBuilder(jsonObjectBuilder.getStringBuilder());
        numberFormat = jsonObjectBuilder.numberFormat;
        simpleDateFormat = jsonObjectBuilder.simpleDateFormat;
    }

    /**
     * Agrega una nueva propiedad al objeto json, no agrega comillas al value
     *
     * @param key   nombre de la propiedad
     * @param value valor de la propiedad
     */
    private void addPropertyWithoutQuotes(String key, Object value) {
        if (value == null) {
            addPropertyNull(key);
        } else {
            stringBuilder
                .append(COMMA_QUOTE)
                .append(key)
                .append(QUOTE_COLON)
                .append(value);
        }
    }

    /**
     * Agrega una nueva propiedad al objeto json, agrega comillas al value
     *
     * @param key   nombre de la propiedad
     * @param value valor de la propiedad
     */
    public void addProperty(String key, String value) {
        if (value == null) {
            addPropertyNull(key);
        } else {
            stringBuilder
                .append(COMMA_QUOTE)
                .append(key)
                .append(QUOTE_COLON_QUOTE)
                .append(value)
                .append(QUOTE);
        }
    }

    /**
     * Castea el valor de la propiedad y la ingresa usando el metodo correcto.
     * las clases que busca son: {@link Integer}, {@link Number}, {@link String}, {@link Date}, {@link Enum}, {@link Boolean} y {@link Object}
     *
     * @param key   nombre de la propiedad
     * @param value valor de la propiedad
     */
    public void addProperty(String key, Object value) {
        if (value instanceof Integer) {
            addProperty(key, (Integer)value);
        } else if (value instanceof Number) {
            addProperty(key, (Number)value);
        } else if (value instanceof String) {
            addProperty(key, (String)value);
        } else if (value instanceof Date) {
            addProperty(key, (Date)value);
        } else if (value instanceof Enum) {
            addProperty(key, (Enum)value);
        } else if (value instanceof Boolean) {
            addProperty(key, (Boolean)value);
        } else {
            addPropertyWithoutQuotes(key, value);
        }
    }

    /**
     * Agrega una nueva propiedad de tipo integer al objeto json, no agrega comillas al value
     *
     * @param key   nombre de la propiedad
     * @param value valor de la propiedad
     */
    public void addProperty(String key, Integer value) {
        addPropertyWithoutQuotes(key, value);
    }

    /**
     * Agrega una nueva propiedad al objeto json, no agrega comillas al value
     *
     * @param key   nombre de la propiedad
     * @param value valor de la propiedad
     */
    public void addProperty(String key, Number value) {
        addPropertyWithoutQuotes(key, numberFormat.format(value));
    }

    /**
     * Agrega una nueva propiedad al objeto json, no agrega comillas al value
     *
     * @param key   nombre de la propiedad
     * @param value valor de la propiedad
     */
    public void addProperty(String key, Date value) {
        addProperty(key, simpleDateFormat.format(value));
    }

    /**
     * Método creado para los archivos .method las cuales no castean automaticamente las primitivas a object
     *
     * @param key   nombre de la propiedad
     * @param value valor de la propiedad
     */
    public void addProperty(String key, int value) {
        this.addPropertyWithoutQuotes(key, value);
    }

    /**
     * Método creado para los archivos .method las cuales no castean automaticamente las primitivas a object
     *
     * @param key   nombre de la propiedad
     * @param value valor de la propiedad
     */
    public void addProperty(String key, double value) {
        this.addProperty(key, (Number) value);
    }

    /**
     * Método creado para los archivos .method las cuales no castean automaticamente las primitivas a object
     *
     * @param key   nombre de la propiedad
     * @param value valor de la propiedad
     */
    public void addProperty(String key, float value) {
        this.addProperty(key, (Number) value);
    }

    /**
     * Método creado para los archivos .method las cuales no castean automaticamente las primitivas a object
     *
     * @param key   nombre de la propiedad
     * @param value valor de la propiedad
     */
    public void addProperty(String key, boolean value) {
        this.addPropertyWithoutQuotes(key, value);
    }

    /**
     * Ingresa una propiedad enum como string
     *
     * @param key   nombre de la propiedad
     * @param value valor de la propiedad
     */
    public void addProperty(String key, Enum value) {
        this.addProperty(key, value.toString());
    }

    /**
     * Ingresa un valor null de json en la clave especificada
     *
     * @param key nombre de la propiedad
     */
    public void addPropertyNull(String key) {
        stringBuilder
            .append(COMMA_QUOTE)
            .append(key)
            .append(QUOTE_COLON)
            .append(NULL);
    }

    @Override
    public String toString() {
        if (stringBuilder.length() > 1 && stringBuilder.charAt(1) == COMMA) {
            //Borro la primer coma sobrante
            stringBuilder.deleteCharAt(1);
        }
        //Agrego la llave de cierre para que el json sea valido
        String result = stringBuilder.append('}').toString();
        //Luego la quito para que se pueda seguir agregando propiedades
        stringBuilder.setLength(stringBuilder.length() - 1);

        return result;
    }

    /************************
     GETTERS AND SETTERS
     ************************/

    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    /**
     * Devuelve el StringBuilder usado para crear el json. Se debe tener cuidado de no hacer una modificación del mismo
     *
     * @return stringBuilder
     */
    public StringBuilder getStringBuilder() {
        return this.stringBuilder;
    }
}
