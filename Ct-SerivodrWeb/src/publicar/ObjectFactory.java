
package publicar;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publicar package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PaqueteRepetidoException_QNAME = new QName("http://publicar/", "PaqueteRepetidoException");
    private final static QName _PuntajeInvalidoException_QNAME = new QName("http://publicar/", "PuntajeInvalidoException");
    private final static QName _UsuarioAgregarYaExisteException_QNAME = new QName("http://publicar/", "UsuarioAgregarYaExisteException");
    private final static QName _CanjeInvalidoException_QNAME = new QName("http://publicar/", "CanjeInvalidoException");
    private final static QName _ParseException_QNAME = new QName("http://publicar/", "ParseException");
    private final static QName _TicketsAgotadosParaFuncionException_QNAME = new QName("http://publicar/", "TicketsAgotadosParaFuncionException");
    private final static QName _UsuarioNoExisteException_QNAME = new QName("http://publicar/", "UsuarioNoExisteException");
    private final static QName _EspectaculoAgregadoYaExisteExcepcion_QNAME = new QName("http://publicar/", "EspectaculoAgregadoYaExisteExcepcion");
    private final static QName _EspectaculoNoExistenteException_QNAME = new QName("http://publicar/", "EspectaculoNoExistenteException");
    private final static QName _PlataformaNoExisteException_QNAME = new QName("http://publicar/", "PlataformaNoExisteException");
    private final static QName _EspectadorYaRegistradoException_QNAME = new QName("http://publicar/", "EspectadorYaRegistradoException");
    private final static QName _EspectaculoEnPaqueteErrorException_QNAME = new QName("http://publicar/", "EspectaculoEnPaqueteErrorException");
    private final static QName _UsuarioNoEsEspectadorException_QNAME = new QName("http://publicar/", "UsuarioNoEsEspectadorException");
    private final static QName _UsuarioAgregarDatosInvalidosException_QNAME = new QName("http://publicar/", "UsuarioAgregarDatosInvalidosException");
    private final static QName _NombreFuncionRepetidoException_QNAME = new QName("http://publicar/", "NombreFuncionRepetidoException");
    private final static QName _PaqueteNoExisteException_QNAME = new QName("http://publicar/", "PaqueteNoExisteException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtEspectaculo }
     * 
     */
    public DtEspectaculo createDtEspectaculo() {
        return new DtEspectaculo();
    }

    /**
     * Create an instance of {@link DtEspectaculo.Valoraciones }
     * 
     */
    public DtEspectaculo.Valoraciones createDtEspectaculoValoraciones() {
        return new DtEspectaculo.Valoraciones();
    }

    /**
     * Create an instance of {@link UsuarioNoExisteException }
     * 
     */
    public UsuarioNoExisteException createUsuarioNoExisteException() {
        return new UsuarioNoExisteException();
    }

    /**
     * Create an instance of {@link EspectadorYaRegistradoException }
     * 
     */
    public EspectadorYaRegistradoException createEspectadorYaRegistradoException() {
        return new EspectadorYaRegistradoException();
    }

    /**
     * Create an instance of {@link EspectaculoAgregadoYaExisteExcepcion }
     * 
     */
    public EspectaculoAgregadoYaExisteExcepcion createEspectaculoAgregadoYaExisteExcepcion() {
        return new EspectaculoAgregadoYaExisteExcepcion();
    }

    /**
     * Create an instance of {@link EspectaculoNoExistenteException }
     * 
     */
    public EspectaculoNoExistenteException createEspectaculoNoExistenteException() {
        return new EspectaculoNoExistenteException();
    }

    /**
     * Create an instance of {@link PlataformaNoExisteException }
     * 
     */
    public PlataformaNoExisteException createPlataformaNoExisteException() {
        return new PlataformaNoExisteException();
    }

    /**
     * Create an instance of {@link NombreFuncionRepetidoException }
     * 
     */
    public NombreFuncionRepetidoException createNombreFuncionRepetidoException() {
        return new NombreFuncionRepetidoException();
    }

    /**
     * Create an instance of {@link PaqueteNoExisteException }
     * 
     */
    public PaqueteNoExisteException createPaqueteNoExisteException() {
        return new PaqueteNoExisteException();
    }

    /**
     * Create an instance of {@link UsuarioAgregarDatosInvalidosException }
     * 
     */
    public UsuarioAgregarDatosInvalidosException createUsuarioAgregarDatosInvalidosException() {
        return new UsuarioAgregarDatosInvalidosException();
    }

    /**
     * Create an instance of {@link EspectaculoEnPaqueteErrorException }
     * 
     */
    public EspectaculoEnPaqueteErrorException createEspectaculoEnPaqueteErrorException() {
        return new EspectaculoEnPaqueteErrorException();
    }

    /**
     * Create an instance of {@link UsuarioNoEsEspectadorException }
     * 
     */
    public UsuarioNoEsEspectadorException createUsuarioNoEsEspectadorException() {
        return new UsuarioNoEsEspectadorException();
    }

    /**
     * Create an instance of {@link UsuarioAgregarYaExisteException }
     * 
     */
    public UsuarioAgregarYaExisteException createUsuarioAgregarYaExisteException() {
        return new UsuarioAgregarYaExisteException();
    }

    /**
     * Create an instance of {@link PuntajeInvalidoException }
     * 
     */
    public PuntajeInvalidoException createPuntajeInvalidoException() {
        return new PuntajeInvalidoException();
    }

    /**
     * Create an instance of {@link PaqueteRepetidoException }
     * 
     */
    public PaqueteRepetidoException createPaqueteRepetidoException() {
        return new PaqueteRepetidoException();
    }

    /**
     * Create an instance of {@link TicketsAgotadosParaFuncionException }
     * 
     */
    public TicketsAgotadosParaFuncionException createTicketsAgotadosParaFuncionException() {
        return new TicketsAgotadosParaFuncionException();
    }

    /**
     * Create an instance of {@link CanjeInvalidoException }
     * 
     */
    public CanjeInvalidoException createCanjeInvalidoException() {
        return new CanjeInvalidoException();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link ColDtUsuarios }
     * 
     */
    public ColDtUsuarios createColDtUsuarios() {
        return new ColDtUsuarios();
    }

    /**
     * Create an instance of {@link DtPremio }
     * 
     */
    public DtPremio createDtPremio() {
        return new DtPremio();
    }

    /**
     * Create an instance of {@link DtColCategorias }
     * 
     */
    public DtColCategorias createDtColCategorias() {
        return new DtColCategorias();
    }

    /**
     * Create an instance of {@link ListString }
     * 
     */
    public ListString createListString() {
        return new ListString();
    }

    /**
     * Create an instance of {@link DtFuncion }
     * 
     */
    public DtFuncion createDtFuncion() {
        return new DtFuncion();
    }

    /**
     * Create an instance of {@link DtHora }
     * 
     */
    public DtHora createDtHora() {
        return new DtHora();
    }

    /**
     * Create an instance of {@link ColDtFuncion }
     * 
     */
    public ColDtFuncion createColDtFuncion() {
        return new ColDtFuncion();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link ColDtEspectaculos }
     * 
     */
    public ColDtEspectaculos createColDtEspectaculos() {
        return new ColDtEspectaculos();
    }

    /**
     * Create an instance of {@link DtArtista }
     * 
     */
    public DtArtista createDtArtista() {
        return new DtArtista();
    }

    /**
     * Create an instance of {@link DtColPlataformas }
     * 
     */
    public DtColPlataformas createDtColPlataformas() {
        return new DtColPlataformas();
    }

    /**
     * Create an instance of {@link ColDtPaquete }
     * 
     */
    public ColDtPaquete createColDtPaquete() {
        return new ColDtPaquete();
    }

    /**
     * Create an instance of {@link DtEspectador }
     * 
     */
    public DtEspectador createDtEspectador() {
        return new DtEspectador();
    }

    /**
     * Create an instance of {@link ColDtsPerfil }
     * 
     */
    public ColDtsPerfil createColDtsPerfil() {
        return new ColDtsPerfil();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtPlataforma }
     * 
     */
    public DtPlataforma createDtPlataforma() {
        return new DtPlataforma();
    }

    /**
     * Create an instance of {@link SetString }
     * 
     */
    public SetString createSetString() {
        return new SetString();
    }

    /**
     * Create an instance of {@link ArrayString }
     * 
     */
    public ArrayString createArrayString() {
        return new ArrayString();
    }

    /**
     * Create an instance of {@link DtEspectaculo.Valoraciones.Entry }
     * 
     */
    public DtEspectaculo.Valoraciones.Entry createDtEspectaculoValoracionesEntry() {
        return new DtEspectaculo.Valoraciones.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaqueteRepetidoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "PaqueteRepetidoException")
    public JAXBElement<PaqueteRepetidoException> createPaqueteRepetidoException(PaqueteRepetidoException value) {
        return new JAXBElement<PaqueteRepetidoException>(_PaqueteRepetidoException_QNAME, PaqueteRepetidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PuntajeInvalidoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "PuntajeInvalidoException")
    public JAXBElement<PuntajeInvalidoException> createPuntajeInvalidoException(PuntajeInvalidoException value) {
        return new JAXBElement<PuntajeInvalidoException>(_PuntajeInvalidoException_QNAME, PuntajeInvalidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioAgregarYaExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "UsuarioAgregarYaExisteException")
    public JAXBElement<UsuarioAgregarYaExisteException> createUsuarioAgregarYaExisteException(UsuarioAgregarYaExisteException value) {
        return new JAXBElement<UsuarioAgregarYaExisteException>(_UsuarioAgregarYaExisteException_QNAME, UsuarioAgregarYaExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanjeInvalidoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "CanjeInvalidoException")
    public JAXBElement<CanjeInvalidoException> createCanjeInvalidoException(CanjeInvalidoException value) {
        return new JAXBElement<CanjeInvalidoException>(_CanjeInvalidoException_QNAME, CanjeInvalidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TicketsAgotadosParaFuncionException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "TicketsAgotadosParaFuncionException")
    public JAXBElement<TicketsAgotadosParaFuncionException> createTicketsAgotadosParaFuncionException(TicketsAgotadosParaFuncionException value) {
        return new JAXBElement<TicketsAgotadosParaFuncionException>(_TicketsAgotadosParaFuncionException_QNAME, TicketsAgotadosParaFuncionException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "UsuarioNoExisteException")
    public JAXBElement<UsuarioNoExisteException> createUsuarioNoExisteException(UsuarioNoExisteException value) {
        return new JAXBElement<UsuarioNoExisteException>(_UsuarioNoExisteException_QNAME, UsuarioNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspectaculoAgregadoYaExisteExcepcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "EspectaculoAgregadoYaExisteExcepcion")
    public JAXBElement<EspectaculoAgregadoYaExisteExcepcion> createEspectaculoAgregadoYaExisteExcepcion(EspectaculoAgregadoYaExisteExcepcion value) {
        return new JAXBElement<EspectaculoAgregadoYaExisteExcepcion>(_EspectaculoAgregadoYaExisteExcepcion_QNAME, EspectaculoAgregadoYaExisteExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspectaculoNoExistenteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "EspectaculoNoExistenteException")
    public JAXBElement<EspectaculoNoExistenteException> createEspectaculoNoExistenteException(EspectaculoNoExistenteException value) {
        return new JAXBElement<EspectaculoNoExistenteException>(_EspectaculoNoExistenteException_QNAME, EspectaculoNoExistenteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlataformaNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "PlataformaNoExisteException")
    public JAXBElement<PlataformaNoExisteException> createPlataformaNoExisteException(PlataformaNoExisteException value) {
        return new JAXBElement<PlataformaNoExisteException>(_PlataformaNoExisteException_QNAME, PlataformaNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspectadorYaRegistradoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "EspectadorYaRegistradoException")
    public JAXBElement<EspectadorYaRegistradoException> createEspectadorYaRegistradoException(EspectadorYaRegistradoException value) {
        return new JAXBElement<EspectadorYaRegistradoException>(_EspectadorYaRegistradoException_QNAME, EspectadorYaRegistradoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspectaculoEnPaqueteErrorException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "EspectaculoEnPaqueteErrorException")
    public JAXBElement<EspectaculoEnPaqueteErrorException> createEspectaculoEnPaqueteErrorException(EspectaculoEnPaqueteErrorException value) {
        return new JAXBElement<EspectaculoEnPaqueteErrorException>(_EspectaculoEnPaqueteErrorException_QNAME, EspectaculoEnPaqueteErrorException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoEsEspectadorException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "UsuarioNoEsEspectadorException")
    public JAXBElement<UsuarioNoEsEspectadorException> createUsuarioNoEsEspectadorException(UsuarioNoEsEspectadorException value) {
        return new JAXBElement<UsuarioNoEsEspectadorException>(_UsuarioNoEsEspectadorException_QNAME, UsuarioNoEsEspectadorException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioAgregarDatosInvalidosException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "UsuarioAgregarDatosInvalidosException")
    public JAXBElement<UsuarioAgregarDatosInvalidosException> createUsuarioAgregarDatosInvalidosException(UsuarioAgregarDatosInvalidosException value) {
        return new JAXBElement<UsuarioAgregarDatosInvalidosException>(_UsuarioAgregarDatosInvalidosException_QNAME, UsuarioAgregarDatosInvalidosException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NombreFuncionRepetidoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "NombreFuncionRepetidoException")
    public JAXBElement<NombreFuncionRepetidoException> createNombreFuncionRepetidoException(NombreFuncionRepetidoException value) {
        return new JAXBElement<NombreFuncionRepetidoException>(_NombreFuncionRepetidoException_QNAME, NombreFuncionRepetidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaqueteNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "PaqueteNoExisteException")
    public JAXBElement<PaqueteNoExisteException> createPaqueteNoExisteException(PaqueteNoExisteException value) {
        return new JAXBElement<PaqueteNoExisteException>(_PaqueteNoExisteException_QNAME, PaqueteNoExisteException.class, null, value);
    }

}
