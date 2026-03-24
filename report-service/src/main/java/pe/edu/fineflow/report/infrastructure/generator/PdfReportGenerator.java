package pe.edu.fineflow.report.infrastructure.generator;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.report.domain.model.ReportJob;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Component
public class PdfReportGenerator {

    public byte[] generate(ReportJob job) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, out);
        doc.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Color.DARK_GRAY);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.BLUE);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK);

        Paragraph title = new Paragraph("FINE FLOW — Reporte Académico", titleFont);
        title.setAlignment(Element.ALIGN_CENTER); doc.add(title);
        doc.add(new Paragraph(" "));

        doc.add(new Paragraph("Tipo: " + job.getReportType(), headerFont));
        doc.add(new Paragraph("Colegio: " + job.getSchoolId(), bodyFont));
        doc.add(new Paragraph("Solicitado por: " + job.getRequestedBy(), bodyFont));
        doc.add(new Paragraph("Generado: " + (job.getCompletedAt() != null ?
                job.getCompletedAt().toString() : "N/A"), bodyFont));
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Parámetros: " + job.getParametersJson(), bodyFont));

        doc.close();
        return out.toByteArray();
    }
}
