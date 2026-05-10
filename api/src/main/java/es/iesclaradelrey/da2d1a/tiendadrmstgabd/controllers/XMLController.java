package es.iesclaradelrey.da2d1a.tiendadrmstgabd.controllers;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.CategoriaReloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.services.RelojService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@RestController
public class XMLController {

    private final RelojService relojService;

    public XMLController(RelojService relojService) {
        this.relojService = relojService;
    }

    @GetMapping("/api/v1/xml")
    public ResponseEntity<byte[]> exportProductos() throws Exception {
        Collection<Reloj> relojes = relojService.buscarTodo();

        // =========================
        // Crear DOM
        // =========================

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder =
                factory.newDocumentBuilder();

        Document document = builder.newDocument();

        // raíz
        Element root = document.createElement("products");

        document.appendChild(root);

        // =========================
        // Productos
        // =========================

        for (Reloj reloj : relojes) {

            Element productElement =
                    document.createElement("product");

            root.appendChild(productElement);

            // id
            Element id =
                    document.createElement("id");

            id.setTextContent(
                    reloj.getId().toString()
            );

            productElement.appendChild(id);

            // nombre
            Element nombre =
                    document.createElement("nombre");

            nombre.setTextContent(
                    reloj.getNombre()
            );

            productElement.appendChild(nombre);

            // precio
            Element precio =
                    document.createElement("precio");

            precio.setTextContent(
                    reloj.getPrecio().toString()
            );

            productElement.appendChild(precio);

            // stock
            Element stock =
                    document.createElement("stock");

            stock.setTextContent(
                    reloj.getStock().toString()
            );

            productElement.appendChild(stock);

            // =========================
            // Marca
            // =========================

            Element marca =
                    document.createElement("marca");

            marca.setAttribute(
                    "id",
                    reloj.getMarca().getId().toString()
            );

            marca.setTextContent(
                    reloj.getMarca().getNombre()
            );

            productElement.appendChild(marca);

            // =========================
            // Categorías
            // =========================

            Element categorias =
                    document.createElement("categorias");

            for (CategoriaReloj categoria
                    : reloj.getCategorias()) {

                Element categoriaElement =
                        document.createElement("categoria");

                categoriaElement.setAttribute(
                        "id",
                        categoria.getId().toString()
                );

                categoriaElement.setTextContent(
                        categoria.getNombre()
                );

                categorias.appendChild(categoriaElement);
            }

            productElement.appendChild(categorias);
        }

        // =========================
        // Convertir a bytes
        // =========================

        TransformerFactory transformerFactory =
                TransformerFactory.newInstance();

        Transformer transformer =
                transformerFactory.newTransformer();

        transformer.setOutputProperty(
                OutputKeys.INDENT,
                "yes"
        );

        transformer.setOutputProperty(
                OutputKeys.ENCODING,
                "UTF-8"
        );

        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();

        transformer.transform(
                new DOMSource(document),
                new StreamResult(outputStream)
        );

        byte[] xmlBytes =
                outputStream.toByteArray();

        // =========================
        // Nombre fichero
        // =========================

        String timestamp =
                LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyy-MM-dd.HH-mm"
                                )
                        );

        String filename =
                "products-export."
                        + timestamp
                        + ".xml";

        // =========================
        // Respuesta
        // =========================

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + filename + "\""
                )
                .contentType(
                        MediaType.APPLICATION_XML
                )
                .body(xmlBytes);
    }
}
