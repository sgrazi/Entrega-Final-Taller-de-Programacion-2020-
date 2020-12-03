
package publicar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para colDtFuncion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="colDtFuncion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="funs" type="{http://publicar/}dtFuncion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "colDtFuncion", propOrder = {
    "funs"
})
public class ColDtFuncion {

    @XmlElement(nillable = true)
    protected List<DtFuncion> funs;

    /**
     * Gets the value of the funs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the funs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFuns().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtFuncion }
     * 
     * 
     */
    public List<DtFuncion> getFuns() {
        if (funs == null) {
            funs = new ArrayList<DtFuncion>();
        }
        return this.funs;
    }

}
