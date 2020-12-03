
package publicar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para dtEspectaculo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtEspectaculo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duracion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantMinEspectadores" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cantMaxEspectadores" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="costo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fechaDeRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="categorias" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="artista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="funciones" type="{http://publicar/}dtFuncion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="paquetes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="plataforma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantSorteosPorFuncion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sorteoDescripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="videoUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valoraciones">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="promedioVal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cantfavoritos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtEspectaculo", propOrder = {
    "nombre",
    "descripcion",
    "duracion",
    "url",
    "cantMinEspectadores",
    "cantMaxEspectadores",
    "costo",
    "fechaDeRegistro",
    "categorias",
    "imagen",
    "artista",
    "estado",
    "funciones",
    "paquetes",
    "plataforma",
    "cantSorteosPorFuncion",
    "sorteoDescripcion",
    "videoUrl",
    "valoraciones",
    "promedioVal",
    "cantfavoritos"
})
public class DtEspectaculo {

    protected String nombre;
    protected String descripcion;
    protected int duracion;
    protected String url;
    protected int cantMinEspectadores;
    protected int cantMaxEspectadores;
    protected int costo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaDeRegistro;
    @XmlElement(nillable = true)
    protected List<String> categorias;
    protected String imagen;
    protected String artista;
    protected String estado;
    @XmlElement(nillable = true)
    protected List<DtFuncion> funciones;
    @XmlElement(nillable = true)
    protected List<String> paquetes;
    protected String plataforma;
    protected int cantSorteosPorFuncion;
    protected String sorteoDescripcion;
    protected String videoUrl;
    @XmlElement(required = true)
    protected DtEspectaculo.Valoraciones valoraciones;
    protected int promedioVal;
    protected int cantfavoritos;

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
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad duracion.
     * 
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Define el valor de la propiedad duracion.
     * 
     */
    public void setDuracion(int value) {
        this.duracion = value;
    }

    /**
     * Obtiene el valor de la propiedad url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Define el valor de la propiedad url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Obtiene el valor de la propiedad cantMinEspectadores.
     * 
     */
    public int getCantMinEspectadores() {
        return cantMinEspectadores;
    }

    /**
     * Define el valor de la propiedad cantMinEspectadores.
     * 
     */
    public void setCantMinEspectadores(int value) {
        this.cantMinEspectadores = value;
    }

    /**
     * Obtiene el valor de la propiedad cantMaxEspectadores.
     * 
     */
    public int getCantMaxEspectadores() {
        return cantMaxEspectadores;
    }

    /**
     * Define el valor de la propiedad cantMaxEspectadores.
     * 
     */
    public void setCantMaxEspectadores(int value) {
        this.cantMaxEspectadores = value;
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
     * Gets the value of the categorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<String>();
        }
        return this.categorias;
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
     * Obtiene el valor de la propiedad artista.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Define el valor de la propiedad artista.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtista(String value) {
        this.artista = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the funciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the funciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFunciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtFuncion }
     * 
     * 
     */
    public List<DtFuncion> getFunciones() {
        if (funciones == null) {
            funciones = new ArrayList<DtFuncion>();
        }
        return this.funciones;
    }

    /**
     * Gets the value of the paquetes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paquetes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaquetes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPaquetes() {
        if (paquetes == null) {
            paquetes = new ArrayList<String>();
        }
        return this.paquetes;
    }

    /**
     * Obtiene el valor de la propiedad plataforma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * Define el valor de la propiedad plataforma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlataforma(String value) {
        this.plataforma = value;
    }

    /**
     * Obtiene el valor de la propiedad cantSorteosPorFuncion.
     * 
     */
    public int getCantSorteosPorFuncion() {
        return cantSorteosPorFuncion;
    }

    /**
     * Define el valor de la propiedad cantSorteosPorFuncion.
     * 
     */
    public void setCantSorteosPorFuncion(int value) {
        this.cantSorteosPorFuncion = value;
    }

    /**
     * Obtiene el valor de la propiedad sorteoDescripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSorteoDescripcion() {
        return sorteoDescripcion;
    }

    /**
     * Define el valor de la propiedad sorteoDescripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSorteoDescripcion(String value) {
        this.sorteoDescripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad videoUrl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * Define el valor de la propiedad videoUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoUrl(String value) {
        this.videoUrl = value;
    }

    /**
     * Obtiene el valor de la propiedad valoraciones.
     * 
     * @return
     *     possible object is
     *     {@link DtEspectaculo.Valoraciones }
     *     
     */
    public DtEspectaculo.Valoraciones getValoraciones() {
        return valoraciones;
    }

    /**
     * Define el valor de la propiedad valoraciones.
     * 
     * @param value
     *     allowed object is
     *     {@link DtEspectaculo.Valoraciones }
     *     
     */
    public void setValoraciones(DtEspectaculo.Valoraciones value) {
        this.valoraciones = value;
    }

    /**
     * Obtiene el valor de la propiedad promedioVal.
     * 
     */
    public int getPromedioVal() {
        return promedioVal;
    }

    /**
     * Define el valor de la propiedad promedioVal.
     * 
     */
    public void setPromedioVal(int value) {
        this.promedioVal = value;
    }

    /**
     * Obtiene el valor de la propiedad cantfavoritos.
     * 
     */
    public int getCantfavoritos() {
        return cantfavoritos;
    }

    /**
     * Define el valor de la propiedad cantfavoritos.
     * 
     */
    public void setCantfavoritos(int value) {
        this.cantfavoritos = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class Valoraciones {

        protected List<DtEspectaculo.Valoraciones.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DtEspectaculo.Valoraciones.Entry }
         * 
         * 
         */
        public List<DtEspectaculo.Valoraciones.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<DtEspectaculo.Valoraciones.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected Integer value;

            /**
             * Obtiene el valor de la propiedad key.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Define el valor de la propiedad key.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setValue(Integer value) {
                this.value = value;
            }

        }

    }

}
