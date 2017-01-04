package org.hch.jsonfast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hernan on 24/11/16.
 */
public class JsonArrayBuilder{
    private static final char[] NULL = {'n', 'u', 'l', 'l'};
    private static final char QUOTE = '"';
    private static final char COMMA = ',';
    private static final char[] COMMA_QUOTE = {COMMA, QUOTE};
    private StringBuilder stringBuilder;
    private NumberFormat numberFormat;
    private SimpleDateFormat simpleDateFormat;

    public JsonArrayBuilder(){
        this(255);
    }

    public JsonArrayBuilder(int initialCapacity){
        stringBuilder = new StringBuilder(initialCapacity).append('[');
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        numberFormat = new DecimalFormat(Properties.DECIMAL_FORMAT, otherSymbols);
        simpleDateFormat = new SimpleDateFormat(Properties.ISO_DATE_FORMAT);
    }

    /**
     * Permite agregar strings preformateadas a las que no es necesario agregar las comillas
     *
     * @param value valor de la propiedad
     */
    private void addWithoutQuotes(Object value){
        if(value == null){
            addNull();
        } else{
            stringBuilder
                .append(COMMA)
                .append(value);
        }
    }

    /**
     * Agrega un nuevo elemento al array json, agrega comillas al value
     *
     * @param value valor de la propiedad
     */
    public void add(String value){
        if(value == null){
            addNull();
        } else{
            stringBuilder
                .append(COMMA_QUOTE)
                .append(value)
                .append(QUOTE);
        }
    }

    /**
     * Castea el elemento y lo ingresa usando el metodo correcto.
     * las clases que busca son: {@link Integer}, {@link Number}, {@link String}, {@link Date}, {@link Enum}, {@link Boolean} y {@link Object}
     *
     * @param value valor de la propiedad
     */
    public void add(Object value){
        if(value instanceof Integer){
            add((Integer) value);
        } else if(value instanceof Number){
            add((Number) value);
        } else if(value instanceof String){
            add((String) value);
        } else if(value instanceof Date){
            add((Date) value);
        } else if(value instanceof Enum){
            add((Enum) value);
        } else if(value instanceof Boolean){
            add((Boolean) value);
        } else{
            addWithoutQuotes(value);
        }
    }

    /**
     * Agrega un nuevo elemento de tipo Integer al array json, no agrega comillas al value
     *
     * @param value valor de la propiedad
     */
    public void add(Integer value){
        addWithoutQuotes(value);
    }

    /**
     * Agrega un nuevo elemento de tipo Number al array json, usando el formateador por defecto, no agrega comillas al value
     *
     * @param value valor de la propiedad
     */
    public void add(Number value){
        addWithoutQuotes(numberFormat.format(value));
    }

    /**
     * Agrega un nuevo elemento de tipo Date al array json, usando el formateador por defecto
     *
     * @param value valor de la propiedad
     */
    public void add(Date value){
        add(simpleDateFormat.format(value));
    }

    /**
     * Método creado para los archivos .method las cuales no castean automaticamente las primitivas a object
     *
     * @param value valor de la propiedad
     */
    public void add(int value){
        addWithoutQuotes(value);
    }

    /**
     * Método creado para los archivos .method las cuales no castean automaticamente las primitivas a object
     *
     * @param value valor de la propiedad
     */
    public void add(float value){
        add((Number) value);
    }

    /**
     * Método creado para los archivos .method las cuales no castean automaticamente las primitivas a object
     *
     * @param value valor de la propiedad
     */
    public void add(double value){
        add((Number) value);
    }

    /**
     * Método creado para los archivos .method las cuales no castean automaticamente las primitivas a object
     *
     * @param value valor de la propiedad
     */
    public void add(boolean value){
        addWithoutQuotes(value);
    }

    /**
     * Método creado para los archivos .method las cuales no castean automaticamente las primitivas a object
     *
     * @param value valor de la propiedad
     */
    public void add(Enum value){
        add(value.toString());
    }

    /**
     * Ingresa un valor null de json en el array
     */
    public void addNull(){
        stringBuilder
            .append(COMMA)
            .append(NULL);
    }

    @Override
    public String toString(){
        if(stringBuilder.length() > 1 && stringBuilder.charAt(1) == COMMA){
            //Borro la primer coma sobrante
            stringBuilder.deleteCharAt(1);
        }
        //Agrego el corchete de cierre para que el array sea válido
        String result = stringBuilder.append(']').toString();
        //Luego lo quito para que se pueda seguir agregando elementos
        stringBuilder.setLength(stringBuilder.length() - 1);

        return result;
    }

    /************************
     GETTERS AND SETTERS
     ************************/

    public NumberFormat getNumberFormat(){
        return numberFormat;
    }

    public void setNumberFormat(NumberFormat numberFormat){
        this.numberFormat = numberFormat;
    }

    public SimpleDateFormat getSimpleDateFormat(){
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat){
        this.simpleDateFormat = simpleDateFormat;
    }

    /**
     * Devuelve el StringBuilder usado para crear el json. Se debe tener cuidado de no hacer una modificación del mismo
     *
     * @return stringBuilder
     */
    public StringBuilder getStringBuilder(){
        return this.stringBuilder;
    }
}
