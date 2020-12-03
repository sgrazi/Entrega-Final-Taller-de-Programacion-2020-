
package publicar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtEspectador complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtEspectador">
 *   &lt;complexContent>
 *     &lt;extension base="{http://publicar/}dtUsuario">
 *       &lt;sequence>
 *         &lt;element name="funciones" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="premios" type="{http://publicar/}dtPremio" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtEspectador", propOrder = {
    "funciones",
    "premios"
})
public class DtEspectador
    extends DtUsuario
{

    @XmlElement(nillable = true)
    protected List<String> funciones;
    @XmlElement(nillable = true)
    protected List<DtPremio> premios;

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
     * {@link String }
     * 
     * 
     */
    public List<String> getFunciones() {
        if (funciones == null) {
            funciones = new ArrayList<String>();
        }
        return this.funciones;
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
