
package publicar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para colDtsPerfil complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="colDtsPerfil">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dtSeguidores" type="{http://publicar/}dtUsuario" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dtSeguidos" type="{http://publicar/}dtUsuario" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dtEspectaculos" type="{http://publicar/}dtEspectaculo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dtFunciones" type="{http://publicar/}dtFuncion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dtPaquetes" type="{http://publicar/}dtPaquete" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="favoritos" type="{http://publicar/}dtEspectaculo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="premios" type="{http://publicar/}dtPremio" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "colDtsPerfil", propOrder = {
    "dtSeguidores",
    "dtSeguidos",
    "dtEspectaculos",
    "dtFunciones",
    "dtPaquetes",
    "favoritos",
    "premios"
})
public class ColDtsPerfil {

    @XmlElement(nillable = true)
    protected List<DtUsuario> dtSeguidores;
    @XmlElement(nillable = true)
    protected List<DtUsuario> dtSeguidos;
    @XmlElement(nillable = true)
    protected List<DtEspectaculo> dtEspectaculos;
    @XmlElement(nillable = true)
    protected List<DtFuncion> dtFunciones;
    @XmlElement(nillable = true)
    protected List<DtPaquete> dtPaquetes;
    @XmlElement(nillable = true)
    protected List<DtEspectaculo> favoritos;
    @XmlElement(nillable = true)
    protected List<DtPremio> premios;

    /**
     * Gets the value of the dtSeguidores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtSeguidores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtSeguidores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtUsuario }
     * 
     * 
     */
    public List<DtUsuario> getDtSeguidores() {
        if (dtSeguidores == null) {
            dtSeguidores = new ArrayList<DtUsuario>();
        }
        return this.dtSeguidores;
    }

    /**
     * Gets the value of the dtSeguidos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtSeguidos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtSeguidos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtUsuario }
     * 
     * 
     */
    public List<DtUsuario> getDtSeguidos() {
        if (dtSeguidos == null) {
            dtSeguidos = new ArrayList<DtUsuario>();
        }
        return this.dtSeguidos;
    }

    /**
     * Gets the value of the dtEspectaculos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtEspectaculos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtEspectaculos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtEspectaculo }
     * 
     * 
     */
    public List<DtEspectaculo> getDtEspectaculos() {
        if (dtEspectaculos == null) {
            dtEspectaculos = new ArrayList<DtEspectaculo>();
        }
        return this.dtEspectaculos;
    }

    /**
     * Gets the value of the dtFunciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtFunciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtFunciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtFuncion }
     * 
     * 
     */
    public List<DtFuncion> getDtFunciones() {
        if (dtFunciones == null) {
            dtFunciones = new ArrayList<DtFuncion>();
        }
        return this.dtFunciones;
    }

    /**
     * Gets the value of the dtPaquetes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtPaquetes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtPaquetes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPaquete }
     * 
     * 
     */
    public List<DtPaquete> getDtPaquetes() {
        if (dtPaquetes == null) {
            dtPaquetes = new ArrayList<DtPaquete>();
        }
        return this.dtPaquetes;
    }

    /**
     * Gets the value of the favoritos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the favoritos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFavoritos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtEspectaculo }
     * 
     * 
     */
    public List<DtEspectaculo> getFavoritos() {
        if (favoritos == null) {
            favoritos = new ArrayList<DtEspectaculo>();
        }
        return this.favoritos;
    }

    /**
     * Gets the value of the premios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the premios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPremios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPremio }
     * 
     * 
     */
    public List<DtPremio> getPremios() {
        if (premios == null) {
            premios = new ArrayList<DtPremio>();
        }
        return this.premios;
    }

}
