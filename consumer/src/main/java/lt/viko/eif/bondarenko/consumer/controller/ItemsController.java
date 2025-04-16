package lt.viko.eif.bondarenko.consumer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.viko.eif.bondarenko.consumer.proxy.ItemProxy;
import lt.viko.eif.bondarenko.consumer.wsdl.GetAllItemsResponse;
import lt.viko.eif.bondarenko.consumer.service.PDFService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller responsible for handling requests related to items.
 * Provides endpoints for generating PDFs of all items.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemsController {

    /**
     * Proxy service for interacting with the remote SOAP web service to fetch item data.
     */
    private final ItemProxy itemProxy;

    /**
     * Service for generating PDF documents based on item data.
     */
    private final PDFService pdfService;

    /**
     * Endpoint for generating a PDF document containing all items.
     * The PDF is created by fetching all items via the {@link ItemProxy}, then transforming them into a PDF using {@link PDFService}.
     *
     * @return a {@link ResponseEntity} containing the generated PDF file as a byte array, with proper headers for download
     * @throws Exception if there is an error during the PDF generation process
     */
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf() throws Exception {
        GetAllItemsResponse items = itemProxy.getAllItems();

        log.info("Generating PDF");

        byte[] pdfBytes = pdfService.generatePdfItems(items);

        log.info("PDF generated successfully");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=items.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}

