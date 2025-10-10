package controlador;

import modelo.Plataforma;
import modelo.Videojuego;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DOMController {
    private final String uri;
    private static DOMController controlador;
    private Document data;

    private DOMController(String uri) {
        this.uri = uri;
    }

    public static DOMController getInstance(String uri) {
        if (controlador == null) controlador = new DOMController(uri);
        return controlador;
    }

    public void initData() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder  = dbFactory.newDocumentBuilder();
        this.data = dBuilder.newDocument();
        Element rootElement = this.data.createElement(this.uri);
        this.data.appendChild(rootElement);
    }

    public void loadData() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(this.uri);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        this.data = dBuilder.parse(xmlFile);
        this.data.getDocumentElement().normalize();
    }

    private String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName).item(0).getChildNodes();
        Node node = nodeList.item(0);
        if (node != null) return node.getNodeValue();
        else return null;
    }

    private Videojuego getVideojuego(Node node) {
        Videojuego videojuego = new Videojuego();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            videojuego.setNombre(element.getAttribute("nombre"));
            videojuego.setGenero(Videojuego.Genero.valueOf(getTagValue(element,"genero")));
            videojuego.setClasificacion(Videojuego.Clasificacion.valueOf(getTagValue(element,"clasificacion")));
            videojuego.setPrecio(Integer.parseInt(Objects.requireNonNull(getTagValue(element, "precio"))));
            videojuego.setFechaLanzamiento(LocalDate.parse(Objects.requireNonNull(getTagValue(element, "fechaLanzamiento")), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        return videojuego;
    }

    private Set<Videojuego> getVideojuegos(String plataforma) {
        Set<Videojuego> videojuegos = new HashSet<Videojuego>();
        NodeList plataformas = this.data.getElementsByTagName("plataforma");
        for (int i = 0; i < plataformas.getLength(); i++) {
            Node plataformaNode = plataformas.item(i);
            if (plataformaNode.getNodeType() == Node.ELEMENT_NODE) {
                Element plataformaElement = (Element) plataformaNode;
                if (plataformaElement.getAttribute("nombre").equals(plataforma)) {
                    NodeList videojuegosNodes = plataformaElement.getElementsByTagName("videojuego");
                    for (int j = 0; j < videojuegosNodes.getLength(); j++) videojuegos.add(getVideojuego(videojuegosNodes.item(j)));
                    break;
                }
            }
        }
        return videojuegos;
    }

    private Plataforma getPlataforma(Node node) {
        Plataforma plataforma = new Plataforma();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            plataforma.setNombre(element.getAttribute("nombre"));
            plataforma.setPrecio(Integer.parseInt(Objects.requireNonNull(getTagValue(element, "precio"))));
            plataforma.setRAM(Integer.parseInt(Objects.requireNonNull(getTagValue(element, "ram"))));
            plataforma.setCatalogo(getVideojuegos(plataforma.getNombre()));
        }
        return plataforma;
    }

    public List<Plataforma> getPlataformas() {
        List<Plataforma> plataformas = new ArrayList<Plataforma>();
        NodeList nodelist = this.data.getElementsByTagName("plataforma");
        for (int i = 0; i < nodelist.getLength(); i++) plataformas.add(getPlataforma(nodelist.item(i)));
        return plataformas;
    }

    private static Transformer preProcess() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        return transformer;
    }

    public void printXML() throws TransformerException {
        Transformer transformer = preProcess();
        DOMSource source = new DOMSource(this.data);
        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);
    }

}
