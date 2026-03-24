package pe.edu.fineflow.report.infrastructure.generator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import pe.edu.fineflow.report.domain.model.ReportJob;
import java.io.ByteArrayOutputStream;

@Component
public class ExcelReportGenerator {

    public byte[] generate(ReportJob job) throws Exception {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Reporte");

            CellStyle headerStyle = wb.createCellStyle();
            Font font = wb.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerStyle.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Header row
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Tipo de Reporte", "Colegio", "Solicitado por", "Parámetros"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }

            // Data row
            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue(job.getReportType());
            dataRow.createCell(1).setCellValue(job.getSchoolId());
            dataRow.createCell(2).setCellValue(job.getRequestedBy());
            dataRow.createCell(3).setCellValue(job.getParametersJson() != null ? job.getParametersJson() : "");

            for (int i = 0; i < columns.length; i++) sheet.autoSizeColumn(i);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            wb.write(out);
            return out.toByteArray();
        }
    }
}
