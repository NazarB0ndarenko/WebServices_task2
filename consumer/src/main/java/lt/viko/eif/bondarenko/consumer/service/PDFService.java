package lt.viko.eif.bondarenko.consumer.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lt.viko.eif.bondarenko.consumer.wsdl.GetAllItemsResponse;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;
import org.springframework.stereotype.Service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Service class responsible for generating PDF documents from XML data using XSLT transformations.
 * This service uses Apache FOP (Formatting Objects Processor) to generate PDFs from the XML data provided.
 */
@Service
public class PDFService {

    /**
     * FOP factory used to create FOP instances for PDF generation.
     * It initializes FOP from the current directory (or another URI).
     */
    private final FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

    /**
     * Generates a PDF document containing all items from the provided {@link GetAllItemsResponse}.
     * The response is marshaled to XML, then transformed using an XSLT stylesheet and finally converted into a PDF.
     *
     * @param itemsResponse the {@link GetAllItemsResponse} containing item data
     * @return a byte array representing the generated PDF document
     * @throws Exception if any error occurs during the transformation or PDF generation
     */
    public byte[] generatePdfItems(GetAllItemsResponse itemsResponse) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(GetAllItemsResponse.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(itemsResponse, stringWriter);
        String xmlContent = stringWriter.toString();

        ByteArrayOutputStream foOutputStream = new ByteArrayOutputStream();
        InputStream xsltInputStream = getClass().getResourceAsStream("/items.xsl");
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltInputStream));
        StreamSource xmlSource = new StreamSource(new ByteArrayInputStream(xmlContent.getBytes("UTF-8")));
        transformer.transform(xmlSource, new StreamResult(foOutputStream));

        ByteArrayInputStream xslFoInputStream = new ByteArrayInputStream(foOutputStream.toByteArray());
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, pdfOutputStream);

        SAXResult saxResult = new SAXResult(fop.getDefaultHandler());
        Transformer foTransformer = transformerFactory.newTransformer();
        foTransformer.transform(new StreamSource(xslFoInputStream), saxResult);

        pdfOutputStream.flush();
        return pdfOutputStream.toByteArray();
    }
}


