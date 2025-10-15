package controlador;

import modelo.Plataforma;
import modelo.Videojuego;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class XMLPlataforma {

    public static void guardarPlataformaXML(List<Plataforma> plataformas, String archivoXML) {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("plataformas");
            doc.appendChild(root);

            for (Plataforma p : plataformas) {
                Element plataformaElement = doc.createElement("plataforma");
                plataformaElement.setAttribute("nombre", p.getNombre());

                Element precio = doc.createElement("precio");
                precio.appendChild(doc.createTextNode((String.valueOf(p.getPrecio()))));
                plataformaElement.appendChild(precio);

                Element ram = doc.createElement("ram");
                ram.appendChild(doc.createTextNode((String.valueOf(p.getRAM()))));
                plataformaElement.appendChild(ram);

                Element catalogo = doc.createElement("catalogo");

                for (Videojuego v : p.getCatalogo()) {
                    Element j =  doc.createElement("juego");
                    j.setAttribute("nombre", v.getNombre());

                    Element genero =  doc.createElement("genero");
                    genero.appendChild(doc.createTextNode(v.getGenero().toString()));
                    j.appendChild(genero);

                    Element clasificacion =  doc.createElement("clasificacion");
                    clasificacion.appendChild(doc.createTextNode(v.getClasificacion().toString()));
                    j.appendChild(clasificacion);

                    Element precioJ = doc.createElement("precio");
                    precioJ.appendChild(doc.createTextNode((String.valueOf(v.getPrecio()))));
                    j.appendChild(precioJ);

                    Element fechaLanzamiento =  doc.createElement("fechaLanzamiento");
                    fechaLanzamiento.appendChild( doc.createTextNode(String.valueOf(v.getFechaLanzamiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));
                    j.appendChild(fechaLanzamiento);

                    catalogo.appendChild(j);

                }

                plataformaElement.appendChild(catalogo);
                root.appendChild(plataformaElement);

            }

            // Guardar archivo XML

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(archivoXML));

            transformer.transform(source, result);

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

}
