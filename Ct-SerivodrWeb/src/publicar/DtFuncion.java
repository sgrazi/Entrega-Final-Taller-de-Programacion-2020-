
package publicar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para dtFuncion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtFuncion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="hora" type="{http://publicar/}dtHora" minOccurs="0"/>
 *         &lt;element name="fechaDeRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cantidadRegistros" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreEsp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaFinalizacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="sorteada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtFuncion", propOrder = {
    "nombre",
    "fecha",
    "hora",
    "fechaDeRegistro",
    "cantidadRegistros",
    "imagen",
    "costo",
    "nombreEsp",
    "fechaFinalizacion",
    "sorteada"
})
public class DtFuncion {

    protected String nombre;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    protected DtHora hora;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaDeRegistro;
    protected int cantidadRegistros;
    protected String imagen;
    protected int costo;
    protected String nombreEsp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFinalizacion;
    protected boolean sorteada;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad hora.
     * 
     * @return
     *     possible object is
     *     {@link DtHora }
     *     
     */
    public DtHora getHora() {
        return hora;
    }

    /**
     * Define el valor de la propiedad hora.
     * 
     * @param value
     *     allowed object is
     *     {@link DtHora }
     *     
     */
    public void setHora(DtHora value) {
        this.hora = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaDeRegistro.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    /**
     * Define el valor de la propiedad fechaDeRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaDeRegistro(XMLGregorianCalendar value) {
        this.fechaDeRegistro = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadRegistros.
     * 
     */
    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    /**
     * Define el valor de la propiedad cantidadRegistros.
     * 
     */
    public void setCantidadRegistros(int value) {
        this.cantidadRegistros = value;
    }

    /**
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public int getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(int value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreEsp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEsp() {
        return nombreEsp;
    }

    /**
     * Define el valor de la propiedad nombreEsp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEsp(String value) {
        this.nombreEsp = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFinalizacion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    /**
     * Define el valor de la propiedad fechaFinalizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFinalizacion(XMLGregorianCalendar value) {
        this.fechaFinalizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad sorteada.
     * 
     */
    public boolean isSorteada() {
        return sorteada;
    }

    /**
     * Define el valor de la propiedad sorteada.
     * 
     */
    public void setSorteada(boolean value) {
        this.sorteada = value;
    }

}
