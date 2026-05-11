package es.iesclaradelrey.da2d1a.tiendadrmstgabd.services;

import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Reloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.entities.CategoriaReloj;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.exceptions.ResourceNotFoundException;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.RelojRepository;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.MarcaRepository;
import es.iesclaradelrey.da2d1a.tiendadrmstgabd.repositories.CategoriaRelojRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;

@Service
public class XmlImportService {

    private final RelojRepository relojRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRelojRepository categoriaRepository;

    public XmlImportService(RelojRepository relojRepository, MarcaRepository marcaRepository, CategoriaRelojRepository categoriaRepository) {
        this.relojRepository = relojRepository;
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public void importarDesdeXml(MultipartFile file) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        RelojHandler handler = new RelojHandler(relojRepository, marcaRepository, categoriaRepository);
        saxParser.parse(file.getInputStream(), handler);
    }

    private class RelojHandler extends DefaultHandler {
        private final RelojRepository relojRepository;
        private final MarcaRepository marcaRepository;
        private final CategoriaRelojRepository categoriaRepository;

        private StringBuilder valorActual = new StringBuilder();
        private Reloj relojActual;

        public RelojHandler(RelojRepository relojRepository, MarcaRepository marcaRepository, CategoriaRelojRepository categoriaRepository) {
            this.relojRepository = relojRepository;
            this.marcaRepository = marcaRepository;
            this.categoriaRepository = categoriaRepository;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            valorActual.setLength(0);

            if (qName.equalsIgnoreCase("product")) {
                relojActual = new Reloj();
                relojActual.setCategorias(new ArrayList<>());
                relojActual.setCodigo("IMPORT-" + System.currentTimeMillis());
            }

            // LEER ID DE MARCA DESDE EL ATRIBUTO
            else if (qName.equalsIgnoreCase("marca") && relojActual != null) {
                String idStr = attributes.getValue("id");
                if (idStr != null) {
                    Long idMarca = Long.parseLong(idStr);
                    Marca marca = marcaRepository.findById(idMarca)
                            .orElseThrow(() -> new ResourceNotFoundException("Error XML: Marca ID " + idMarca + " no existe."));
                    relojActual.setMarca(marca);
                }
            }

            // LEER ID DE CATEGORIA DESDE EL ATRIBUTO
            else if (qName.equalsIgnoreCase("categoria") && relojActual != null) {
                String idStr = attributes.getValue("id");
                if (idStr != null) {
                    Long idCat = Long.parseLong(idStr);
                    CategoriaReloj cat = categoriaRepository.findById(idCat)
                            .orElseThrow(() -> new ResourceNotFoundException("Error XML: Categoría ID " + idCat + " no existe."));
                    relojActual.getCategorias().add(cat);
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            valorActual.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (relojActual == null) return;

            switch (qName.toLowerCase()) {
                case "nombre"      -> relojActual.setNombre(valorActual.toString());
                case "precio"      -> relojActual.setPrecio(Double.parseDouble(valorActual.toString()));
                case "stock"       -> relojActual.setStock(Integer.parseInt(valorActual.toString()));
                // AÑADE ESTOS TRES:
                case "codigo"      -> relojActual.setCodigo(valorActual.toString());
                case "descripcion" -> relojActual.setDescripcion(valorActual.toString());
                case "descuento"   -> relojActual.setDescuento(Integer.parseInt(valorActual.toString()));

                case "product" -> {
                    relojRepository.save(relojActual);
                    relojActual = null;
                }
            }
        }
    }
}