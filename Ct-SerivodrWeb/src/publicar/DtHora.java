
package publicar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtHora complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtHora">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="horas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minutos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtHora", propOrder = {
    "horas",
    "minutos"
})
public class DtHora {

    protected int horas;
    protected int minutos;

    /**
     * Obtiene el valor de la propiedad horas.
     * 
     */
    public int getHoras() {
        return horas;
    }

    /**
     * Define el valor de la propiedad horas.
     * 
     */
    public void setHoras(int value) {
        this.horas = value;
    }

    /**
     * Obtiene el valor de la propiedad minutos.
     * 
     */
    public int getMinutos() {
        return minutos;
    }

    /**
     * Define el valor de la propiedad minutos.
     * 
     */
    public void setMinutos(int value) {
        this.minutos = value;
    }

}
