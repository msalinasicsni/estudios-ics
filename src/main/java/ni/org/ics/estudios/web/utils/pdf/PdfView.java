package ni.org.ics.estudios.web.utils.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
//import com.sun.javafx.font.*;
import ni.org.ics.estudios.domain.SerologiaOct2020.SerologiaEnvio;
import ni.org.ics.estudios.domain.catalogs.Personal;
import ni.org.ics.estudios.domain.hemodinamica.DatosHemodinamica;
import ni.org.ics.estudios.domain.hemodinamica.HemoDetalle;
import ni.org.ics.estudios.domain.muestreoanual.ParticipanteProcesos;
import ni.org.ics.estudios.domain.scancarta.DetalleParte;
import ni.org.ics.estudios.domain.scancarta.ParticipanteCarta;
import ni.org.ics.estudios.language.MessageResource;
import ni.org.ics.estudios.web.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import sun.font.FontFamily;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.*;
import java.awt.Image;
import java.io.Writer;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.io.FileOutputStream;
import java.io.File;

/**
 * Created by Miguel Salinas on 19/10/2018.
 * V1.0
 */
public class PdfView extends AbstractPdfView {

    java.util.List<MessageResource> messageReports = new ArrayList<MessageResource>();
    java.util.List<MessageResource> messageExtremidades = new ArrayList<MessageResource>();
    java.util.List<MessageResource> messagerelFam = new ArrayList<MessageResource>();
    java.util.List<MessageResource> messagenivel = new ArrayList<MessageResource>();
    List<MessageResource> messagellenado = new ArrayList<MessageResource>();
    List<MessageResource> messagepulso = new ArrayList<MessageResource>();
    List<MessageResource> messagediuresis = new ArrayList<MessageResource>();
    /*ReporteCarta
    List<MessageResource> messagerelFam = new ArrayList<MessageResource>();
    List<MessageResource>messageproyecto = new ArrayList<MessageResource>();
    List<MessageResource>messagescancarta = new ArrayList<MessageResource>();*/
    @Override
    protected void buildPdfDocument(
            Map<String, Object> model,
            Document document,
            PdfWriter writer,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        messageReports = (java.util.List<MessageResource>) model.get("labels");
        if (model.get("TipoReporte").equals(Constants.TPR_DATOSGENERALES)){
            datosGenerales(model, document, writer);
        }
        if (model.get("TipoReporte").equals(Constants.TPR_HEMOREPORTE)){
            hemoReporte(model, document, writer);
        }
        if (model.get("TipoReporte").equals(Constants.TPR_REPORTECARTA)){
            ReporteCarta(model, document, writer);
        }
        if (model.get("TipoReporte").equals(Constants.TPR_ENVIOREPORTE)){
            ReporteEnvio(model, document, writer);
        }
    }

    private PdfPCell createCell(String text, Font f, int border){
        PdfPCell cell = new PdfPCell(new Phrase(text, f));
        cell.setPaddingBottom(5);
        cell.setBorder(border);
        return cell;
    }

    private PdfPCell createCell(String text, Font f, int border, int colspan){
        PdfPCell cell = new PdfPCell(new Phrase(text, f));
        cell.setColspan(colspan);
        cell.setPaddingBottom(5);
        cell.setBorder(border);
        return cell;
    }

    private PdfPCell createCellUnderline(String text, Font f, int border) {
        Chunk chunk2 = new Chunk(text, f);
        chunk2.setUnderline(1f, -2f);
        PdfPCell cell = new PdfPCell(new Phrase(chunk2));
        cell.setPaddingBottom(5);
        cell.setBorder(border);
        return cell;
    }
    private PdfPCell createCellUnderline(String text, Font f, int border, int colspan) {
        Chunk chunk2 = new Chunk(text, f);
        chunk2.setUnderline(1f, -2f);
        PdfPCell cell = new PdfPCell(new Phrase(chunk2));
        cell.setColspan(colspan);
        cell.setPaddingBottom(5);
        cell.setBorder(border);
        return cell;
    }


    private String getMessage(String messageKey, String languaje){
        for(MessageResource message : messageReports){
            if (message.getMessageKey().equalsIgnoreCase(messageKey)){
                if (languaje!=null && languaje.equalsIgnoreCase("en"))
                    return message.getEnglish();
                else return message.getSpanish();
            }
        }
        return "";
    }

    private void datosGenerales(Map<String, Object> model,
                                 Document document,
                                 PdfWriter writer) throws Exception{

        List<DatosGeneralesParticipante> datosParticipantes = (List<DatosGeneralesParticipante>) model.get("datos");
        if (datosParticipantes.size()<=0){
            Paragraph h1 = new Paragraph(this.getMessage("noResults", null), new Font(Font.TIMES_ROMAN, 13, Font.BOLD));
            document.add(h1);
        }
        for (DatosGeneralesParticipante datosParticipante : datosParticipantes) {
            document.newPage();
            MyFooter footer = new MyFooter();
            footer.setMensajes(datosParticipante.getMensajes());
            writer.setPageEvent(footer);
            Font timesRomanNormal12 = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
            Font timesRomanBold13 = new Font(Font.TIMES_ROMAN, 13, Font.BOLD);
            Font timesRomanBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
            Font timesRomanBoldItalic12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLDITALIC);

            Paragraph h1 = new Paragraph(this.getMessage("title.report.file", null), timesRomanBold13);
            document.add(h1);
            LineSeparator ls = new LineSeparator(0.5f, 100, null, 0, -5);
            ls.setLineWidth(0.5f);
            document.add(new Chunk(ls));

            PdfPTable table = new PdfPTable(new float[]{10, 13, 10, 67});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.code", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getCodigo(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.date", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getFechaIngreso(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            Paragraph t1 = new Paragraph(this.getMessage("lbl.general.data", null), timesRomanBoldItalic12);
            t1.setSpacingAfter(5f);
            document.add(t1);

            table = new PdfPTable(new float[]{23, 77});
            table.setWidthPercentage(96);
            table.addCell(createCellUnderline(this.getMessage("lbl.family", null), timesRomanBoldItalic12, Rectangle.NO_BORDER, 2));
            table.addCell(createCell(this.getMessage("lbl.family.boss", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getJefeFamilia(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.tutor", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTutor(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.father", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getPadre(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.mother", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getMadre(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{23, 10, 25, 15, 27});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.family.relationship", null), timesRomanBold12, Rectangle.NO_BORDER, 2));
            table.addCell(createCell(datosParticipante.getRelFamTutor(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.housing.type", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTipoVivienda(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.neighborhood", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getBarrio(), timesRomanNormal12, Rectangle.NO_BORDER, 2));
            table.addCell(createCell(this.getMessage("lbl.block", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getManzana(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{23, 77});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.address", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getDireccion(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.phone", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTelefonos(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCellUnderline(this.getMessage("lbl.contact", null), timesRomanBoldItalic12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.contact.explanation", null), timesRomanBoldItalic12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.name", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getNombreContacto(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.neighborhood", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getBarrioContacto(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.phone", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTelefonosContacto(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.address", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getDireccionContacto(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            Paragraph t2 = new Paragraph(this.getMessage("lbl.child.data", null), timesRomanBoldItalic12);
            t2.setSpacingAfter(5f);
            document.add(t2);

            table = new PdfPTable(new float[]{29, 71});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.names.surnames", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getNombreCompleto(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{8, 8, 23, 17, 7, 8, 15, 14});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.age", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getEdad(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.birthdate", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getFechaNacimiento(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.gender", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getSexo(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.student", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getEstudiante(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{10, 19, 10, 61});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.turn", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTurno(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.school", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getEscuela(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{29, 71});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.residence.time", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTiempoResidencia(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{65, 35});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.stay.area", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getPermanecerTresAnios(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{56, 44});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.where.child.attends", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getDondeAsiste(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{56, 44});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.health.unit.attends", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getUnidadAsiste(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            table = new PdfPTable(new float[]{85, 15});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.willing.attends.cssf", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getAsistirCSSF(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            Paragraph t3 = new Paragraph(this.getMessage("lbl.clinical.epi.data", null), timesRomanBoldItalic12);
            t3.setSpacingAfter(5f);
            document.add(t3);

            table = new PdfPTable(new float[]{53, 5, 10, 32});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.chronic disease", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getEnfermedadCronica(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.wich", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getCualEnfermedadCronica(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.take.treatment", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTomaTratamiento(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.wich", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getCualTratamiento(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.diagnosed.dengue", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getTieneDxDengue(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.when", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getFechaDxDengue(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.hospitalized.dengue", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getHospitalizadoDengue(), timesRomanNormal12, Rectangle.NO_BORDER));
            table.addCell(createCell(this.getMessage("lbl.when", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getFechaHospitalizadoDengue(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);

            LineSeparator ls2 = new LineSeparator();
            ls2.setLineWidth(0.5f);
            document.add(new Chunk(ls2));

            table = new PdfPTable(new float[]{20, 80});
            table.setWidthPercentage(96);
            table.addCell(createCell(this.getMessage("lbl.digitador", null), timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(datosParticipante.getDigitador(), timesRomanNormal12, Rectangle.NO_BORDER));
            document.add(table);
        }
    }


    // create TableNull
    private static PdfPTable createTableNull(String text){
        Font etiquetas = FontFactory.getFont(FontFactory.HELVETICA,9);
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100f);
        PdfPCell cel = new PdfPCell();
        cel.addElement(new Phrase(text,etiquetas));
        table.addCell(cel);
        table.completeRow();
        return table;
    }
//region Reporte Hemodinamica
    private void hemoReporte( Map<String, Object> model,
                              Document document, PdfWriter writer )throws Exception {
        DatosHemodinamica obj = (DatosHemodinamica) model.get("obj");
        List<HemoDetalle> ListDetail = (List<HemoDetalle>) model.get("detalle");
        messageReports = (java.util.List<MessageResource>) model.get("labels");
        messageExtremidades = (java.util.List<MessageResource>) model.get("extremidades");
        messagenivel = (java.util.List<MessageResource>) model.get("nivel");
        messagellenado = (List<MessageResource>) model.get("llenado");
        messagepulso = (List<MessageResource>) model.get("pulso");
        messagediuresis = (List<MessageResource>) model.get("diuresis");
        document.setPageSize(PageSize.A4.rotate());
        document.open();
        Font bf12 = new Font(Font.TIMES_ROMAN, 10);
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font font2 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD);
        Font etiquetas = FontFactory.getFont(FontFactory.HELVETICA, 9);
        Font timesRomanNormal12 = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
        Font timesRomanBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
//region
        PdfPTable table = new PdfPTable(new float[]{18, 30, 10, 40});
        table.setWidthPercentage(100f);
        PdfPCell cell;
        int maximoCol = 16;
        if (ListDetail.size()<=0){
            Font largeBold = new Font(Font.COURIER, 32,Font.BOLD, Color.red);
            Paragraph h1 = new Paragraph("Hoja Hemodinámica.", largeBold);
            h1.setAlignment(Element.ALIGN_CENTER);
            document.add(h1);
            Chunk chunk = new Chunk("");
            document.add(chunk);
            // text watermark
            Font f = new Font(Font.HELVETICA, 16, Font.BOLD, new GrayColor(0.85f));
            table = new PdfPTable(1);
            table.setWidthPercentage(100f);
            String estado = "PARA VIZUALIZAR EL REPORTE DEBES COMPLETAR TODA LA INFORMACIÓN.";
            Phrase p = new Phrase(estado, f);
            cell = new PdfPCell(new Phrase(p));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            document.add(table);
        }
        Double tablas = Math.ceil((double)ListDetail.size()/maximoCol);
        int conDetalle = 0;
        for (int t = 1; t <= tablas.intValue() ; t++) {
            if (conDetalle > 0 && conDetalle % 16 == 0) {
                document.newPage();
            }
            table = new PdfPTable(new float[]{10, 10, 10, 10, 10, 10, 10});
            table.setWidthPercentage(100f);
            cell = new PdfPCell(new Phrase(""));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("MINISTERIO DE SALUD."));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Código"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            document.add(table);

            /******************************************/
            table = new PdfPTable(new float[]{10, 10, 10, 10, 10, 10, 10});
            table.setWidthPercentage(100f);
            cell = new PdfPCell(new Phrase(""));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("HOJA DE EVALUACIÓN HEMODINÁMICA."));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            cell.setColspan(3);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(obj.getParticipante().getCodigo().toString()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            document.add(table);
            /******************************************/

            /******************************************/
            table = new PdfPTable(new float[]{10, 10, 10, 10, 10, 10, 10});
            table.setWidthPercentage(100f);
            cell = new PdfPCell(new Phrase(""));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("PACIENTES HOSPITALIZADOS CON DENGUE."));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            cell.setColspan(3);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            document.add(table);

            Paragraph p = new Paragraph();
            p.setLeading(0, 1.2f);
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            p.setSpacingBefore(10);
            document.add(p);

            /****************************** 1er Fila *********************************/

            table = new PdfPTable(new float[]{3, 6, 8, 12, 10, 10, 10, 11});
            table.setWidthPercentage(100f);
            cell = new PdfPCell(new Phrase("Silais:", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getSilais(), bf12));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Unidad de Salud:", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getuSalud(), bf12));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Municipio:", etiquetas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getMunicipio(), bf12));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Sector:", etiquetas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getParticipante().getCasa().getBarrio().getNombre(), bf12));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            document.add(table);
            /*******************************Fin 1ra Fila****************************/
            /****************************** 2da Fila *********************************/
            table = new PdfPTable(new float[]{5, 15, 10, 10, 9, 10, 11});
            table.setWidthPercentage(100f);
            cell = new PdfPCell(new Phrase("Dirección:", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getDireccion(), bf12));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha:", etiquetas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(DateUtil.DateToString(obj.getFecha(), "dd/MM/yyyy"), bf12));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            document.add(table);
/****************************** Fin 2da Fila *********************************/
/****************************** 3ra Fila *********************************/
            table = new PdfPTable(new float[]{13, 37, 13, 37});
            table.setWidthPercentage(100f);
            cell = new PdfPCell(new Phrase("Número de expediente:", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getnExpediente(), font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("No. Teléfonico:", etiquetas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);
            String tel;
            if (obj.getTelefono() != null) {
                tel = obj.getTelefono();
            } else {
                tel = "--";
            }
            cell = new PdfPCell(new Phrase(tel, font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);
            document.add(table);
            /****************************** Fin 3ra Fila *********************************/
            /****************************** Fin 4ta Fila *********************************/
            table = new PdfPTable(new float[]{10, 18, 4, 13, 5, 5, 5, 5, 5, 10, 5, 5});
            table.setWidthPercentage(100f);
            cell = new PdfPCell(new Phrase("Nombre y Apellidos:", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getParticipante().getNombreCompleto(), font));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Edad:", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getEdad(), font));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Peso:", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);
            Double pes = Double.valueOf(obj.getPeso().toString());
            cell = new PdfPCell(new Phrase(pes + " Kg", font));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Talla:", etiquetas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getTalla().toString() + " cm", font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("ASC:", etiquetas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            double x = (double) Math.round(obj.getAsc() * 100d) / 100d;
            cell = new PdfPCell(new Phrase(Double.valueOf(x).toString(), font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("IMC:", etiquetas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getImc().toString(), font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            document.add(table);
            /****************************** Fin 4ta Fila *********************************/
            Paragraph t3 = new Paragraph("Valoración del Estado nutricional en los niños/niñas", bf12);
            t3.setSpacingAfter(5f);
            document.add(t3);
            /********************************* 5ta Fila **************************************************/
            table = new PdfPTable(new float[]{10, 15, 5, 10, 8, 10, 5, 8});
            table.setWidthPercentage(100f);
            cell = new PdfPCell(new Phrase("Rango de Presión PS/SD.", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);
            String psdmin, psdmed, psdmax;
            if (obj.getSdMin() != null || obj.getSdMin() == "") {
                psdmin = obj.getSdMin();
                psdmed = obj.getSdMed();
                psdmax = obj.getSdMax();
            } else {
                psdmin = "--";
                psdmed = "--";
                psdmax = "--";
            }
            cell = new PdfPCell(new Phrase(psdmin + " /, " + psdmed + " /, " + psdmax, font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("PAM", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getPamMin() + " / " + obj.getPamMed() + " / " + obj.getPamMax(), font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Rangos de FC:", etiquetas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);
            String min, med, max, frmin, frmax;
            if (obj.getFcMin() != null) {
                min = obj.getFcMin().toString();
                med = obj.getFcMed().toString();
                max = obj.getFcProm().toString();
                frmin = obj.getFrMin().toString();
                frmax = obj.getFrMax().toString();
            } else {
                min = "--";
                med = "--";
                max = "--";
                frmin = "--";
                frmax = "--";
            }
            cell = new PdfPCell(new Phrase(min + " / " + med + " / " + max, font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Rango FR: ", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(frmin + " / " + frmax, font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            document.add(table);
            /************************************* Fin 5ta Fila **********************************************/
            /************************************* Fin 6ta Fila **********************************************/
            table = new PdfPTable(new float[]{10, 15, 5, 10, 8, 10, 5, 8});
            table.setWidthPercentage(100f);
            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("mínima media máxima", font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("mínima media máxima", font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("mínima/máximo promedio", font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(""));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("mínimo/máximo", font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            document.add(table);
            //endregion
            /************************************* Fin 6ta Fila **********************************************/

            /************************************* Fin 6ta Fila **********************************************/
            table = new PdfPTable(new float[]{10, 10, 7, 13, 10, 10});
            table.setWidthPercentage(100f);
            cell = new PdfPCell(new Phrase("Fecha inicio de enfermedad:", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(DateUtil.DateToString(obj.getFie(), "dd/MM/yyyy"), font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Días de enfermedad:", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getDiasenf().toString(), font));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Días de hospitalización:", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("--", etiquetas));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);
            document.add(table);

            table = new PdfPTable(new float[]{18,82});
            table.setWidthPercentage(100f);
            cell = new PdfPCell(new Phrase("Clasificacion clínica del Dengue:", etiquetas));
            cell.setBorder(0);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(ListDetail.size() <= 0? "" : ListDetail.get(0).getSigno(), font));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            document.add(table);
//endregion
            /************************************* Fin 6ta Fila **********************************************/

            table = new PdfPTable(17);
            table.setWidthPercentage(100f);
            table.addCell(new Phrase("Fecha ", font2));
            table.completeRow();
            table.addCell(new Phrase("Hora ", font2));
            table.completeRow();
            table.addCell(new Phrase("Nivel de Consciencia ", font2));
            table.completeRow();
            table.addCell(new Phrase("P/A mmHg", font2));
            table.completeRow();
            table.addCell(new Phrase("PP mmHg", font2));
            table.completeRow();
            table.addCell(new Phrase("PAM mmHg", font2));
            table.completeRow();
            table.addCell(new Phrase("FC por Minuto", font2));
            table.completeRow();
            table.addCell(new Phrase("Fr por Minuto", font2));
            table.completeRow();
            table.addCell(new Phrase("T°C", font2));
            table.completeRow();
            table.addCell(new Phrase("SA02%", font2));
            table.completeRow();
            table.addCell(new Phrase("Extremidades", font2));
            table.completeRow();
            table.addCell(new Phrase("Llenado Capilar (seg)", font2));
            table.completeRow();
            table.addCell(new Phrase("Pulso (Calidad)", font2));
            table.completeRow();
            table.addCell(new Phrase("Diuresis/ml/Kg/Hr", font2));
            table.completeRow();
            table.addCell(new Phrase("Densidad Urinaria", font2));
            table.completeRow();
            table.addCell(new Phrase("Validado por", font2));
            table.completeRow();


            for (int i = 0; i <= maximoCol; i++) {
                if (i == 0) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(DateUtil.DateToString(l.getFecha(), "dd/MM/yyyy"), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 1) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getHora(), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 2) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(this.getDescripcionCatalogo(l.getNivelConciencia(), "NIVELCONCIENCIA"), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 3) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    String part1="", part2=""; Integer alerta;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            for (int indice = 0; indice < l.getPa().length(); indice++){
                                String string = l.getPa();
                                String[] parts = string.split("/");
                                alerta = parts.length;
                                part1 = parts[0];
                                part2 = (alerta > 1)?parts[1]:l.getPd();
                            }
                            cell1[celda].setPhrase(new Phrase(Integer.parseInt(part1)+"/"+Integer.parseInt(part2), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 4) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getPp(), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 5) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getPam(), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 6) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getFc(), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 7) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getFr(), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 8) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getTc(), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 9) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getSa(), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 10) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(this.getDescripcionCatalogo(l.getExtremidades(), "EXTREMIDADES"), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 11) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(this.getDescripcionCatalogo(l.getLlenadoCapilar(), "LLENADOCAPILAR"), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 12) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {

                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(this.getDescripcionCatalogo(l.getPulsoCalidad(), "PULSOCALIDAD"), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 13) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(this.getDescripcionCatalogo(l.getDiuresis(), "DIURESIS"), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 14) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {

                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getDensidadUrinaria(), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
                if (i == 15) {
                    PdfPRow row = table.getRow(i);
                    PdfPCell[] cell1 = row.getCells();
                    int celda = 1;
                    for (int conDet = conDetalle; conDet < maximoCol * t; conDet++) {
                        if (conDet < ListDetail.size()) {
                            HemoDetalle l = ListDetail.get(conDet);
                            cell1[celda].setPhrase(new Phrase(l.getPersonaValida(), font));
                            cell1[celda].setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda++;
                        } else break;
                    }
                }
            }
            conDetalle = maximoCol * t;
            document.add(table);
            Date objDate = new Date();
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat hourFormat = new SimpleDateFormat("HH:mm");
            table = new PdfPTable(new float[]{10,10,10,5,5});
            table.setWidthPercentage(96);
            table.addCell(createCell("Creado por: ", timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(obj.getRecordUser(), font, Rectangle.NO_BORDER));
            table.addCell(createCell("Impreso : ", timesRomanBold12, Rectangle.NO_BORDER));
            table.addCell(createCell(dateformat.format(objDate) + " " + hourFormat.format(objDate), font, Rectangle.NO_BORDER));
            int pageN = writer.getPageNumber();
            table.addCell(createCell("Página: "+ writer.getCurrentPageNumber() +" de "+ " " , font, Rectangle.NO_BORDER));
            document.add(table);
        }
    }
//endregion

    private void ReporteCarta(Map<String, Object> model, Document document, PdfWriter writer)throws Exception{
        ParticipanteCarta obj= (ParticipanteCarta) model.get("obj");
        ParticipanteProcesos procesos = (ParticipanteProcesos) model.get("procesos");
        List<DetalleParte> ListDetailPart = (List<DetalleParte>) model.get("dp");
        messagerelFam = (java.util.List<MessageResource>) model.get("relFam");
        document.newPage();
        document.open();
        //region Encabezados
        if (ListDetailPart.size()<=0){
            Paragraph encabezado = new Paragraph("COMPLETE LA INFORMACIÓN, PARA VIZUALIZAR EL REPORTE.",FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black));
            document.add(encabezado);
        }
        Font timesRomanBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
        Font bf12 = new Font(Font.TIMES_ROMAN, 10);
        Font mia = new Font(Font.COURIER,12, Font.BOLDITALIC);
        Font miaNormal = new Font(Font.COURIER,12, Font.NORMAL);
        Font timesRomanBoldItalic12 = new Font(Font.COURIER, 12, Font.BOLD);
        Font timesRomanNormal12 = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);


        Date objDate = new Date();
        DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Paragraph fecha = new Paragraph(dateformat.format(objDate) + " " + hourFormat.format(objDate), mia);
        fecha.setAlignment(Element.ALIGN_RIGHT);
        document.add(fecha);
        Paragraph encabezado = new Paragraph("INSTITUTO DE CIENCIAS SOSTENIBLES.",FontFactory.getFont("COURIER", 20, java.awt.Font.BOLD, Color.black));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        document.add(encabezado);
        Paragraph encabezado2 = new Paragraph("REPORTE PARTICIPANTE CARTA.", FontFactory.getFont("COURIER",16, java.awt.Font.ITALIC));
        encabezado2.setAlignment(Element.ALIGN_CENTER);
        document.add(encabezado2);

        LineSeparator ls1 = new LineSeparator();
        ls1.setLineWidth(0.5f);
        document.add(new Chunk(ls1));


        PdfPTable table = new PdfPTable(new float[]{18,18});
        table.setWidthPercentage(96f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Código: ",miaNormal));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(obj.getParticipante().getCodigo().toString(),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCell("1. Datos Generales", timesRomanBold12, Rectangle.NO_BORDER));
        document.add(table);

        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCellUnderline("Familia.", timesRomanBoldItalic12, Rectangle.NO_BORDER, 2));
        document.add(table);

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Nombre de la Madre: ", miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        String Madre = obj.getParticipante().getNombre1Madre()+" "+obj.getParticipante().getNombre2Madre()+" "+obj.getParticipante().getApellido1Madre()+" "+obj.getParticipante().getApellido2Madre();
        cell = new PdfPCell(new Phrase(Madre,mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);




        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Nombre del Padre: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        String Padre = obj.getParticipante().getNombre1Padre()+" "+ obj.getParticipante().getApellido2Padre() + " "+ obj.getParticipante().getApellido1Padre() +" "+ obj.getParticipante().getApellido2Padre();
        cell = new PdfPCell(new Phrase(Padre,mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);



        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Nombre del Tutor :",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(obj.getParticipante().getTutor(),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);


        table = new PdfPTable(new float[]{56,40});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Relación del tutor con el participante: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(this.getDescripcionCatalogoScan(obj.getParticipante().getRelacionFamiliarTutor().toUpperCase(),"CP_CAT_RFTUTOR"),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCellUnderline("Datos del Participante.", timesRomanBoldItalic12, Rectangle.NO_BORDER, 2));
        document.add(table);


        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Nombres y Apellidos: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(obj.getParticipante().getNombreCompleto().toString(),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Sexo: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(obj.getParticipante().getSexo().toString(),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Fecha de Nacimiento: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(DateUtil.DateToString(obj.getParticipante().getFechaNac(),"dd/MM/yyyy"),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);


        // text watermark
        Font f = new Font(Font.HELVETICA, 50, Font.BOLD, new GrayColor(0.85f));
        table = new PdfPTable(1);
        table.setWidthPercentage(100f);
        String estado = (obj.getRetirado() == '0') ? "ACTIVO" : "RETIRADO";
        Phrase p = new Phrase(estado, f);
        cell = new PdfPCell(new Phrase(p));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //cell.setRotation(90);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
        document.add(table);

//endregion

        table = new PdfPTable(new float[]{48});
        table.setWidthPercentage(96f);
        table.addCell(createCellUnderline("Carta, Versión, Partes aceptadas y no aceptadas.", timesRomanBoldItalic12, Rectangle.NO_BORDER, 2));
        document.add(table);
        document.add(new Phrase("\n"));

//region foreach
        if (ListDetailPart.size() > 0){
            table = new PdfPTable(new float[]{18,18,18});
            cell = new PdfPCell(new Phrase(obj.getVersion().getCarta().getCarta(), mia));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            Integer numRowSpan = ListDetailPart.size()+2;
            cell.setRowspan(numRowSpan);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(obj.getVersion().getVersion(), mia));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("PARTE",miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("ACEPTA",miaNormal));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            for (DetalleParte objParte : ListDetailPart){
                cell = new PdfPCell(new Phrase(objParte.getParte().getParte().toString(),mia));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase( (objParte.getAcepta().toString() == "true" )? "Si":"No" , mia));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            document.add(table);
        }
        document.add(new Phrase("\n"));
        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Carta Firmada Por: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        String QuienFirma = obj.getQuienfirma()+" "+obj.getNombre2Firma()+" "+obj.getApellido1Firma()+" "+obj.getApellido2Firma();
        cell = new PdfPCell(new Phrase(QuienFirma,mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Relación familiar: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(this.getDescripcionCatalogoScan(obj.getRelfam().toString(),"CP_CAT_RFTUTOR"),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);


        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Proyecto: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        //AQUI VALIDAR QUE NO VAYA VACIO
        String pro = (this.getDescripcionCatalogoScan(obj.getProyecto().toString(),"PROYECTO") == "")?"No Aplica":this.getDescripcionCatalogoScan(obj.getProyecto().toString(),"PROYECTO");
        cell = new PdfPCell(new Phrase(pro,mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Asentimiento: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(this.getDescripcionCatalogoScan(obj.getAsentimiento().toString(),"SCANCARTA"),mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);
        //AQUI TAMBIEN VALIDAR

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Tipo Asentimiento: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        String tAsentimiento;
        if (this.getDescripcionCatalogoScan(obj.getTipoasentimiento().toString(), "TIPOASENTIMIENTO") == null){
            tAsentimiento = "No Aplica";
        }
        else {
            tAsentimiento = this.getDescripcionCatalogoScan(obj.getTipoasentimiento().toString(), "TIPOASENTIMIENTO");
        }
        cell = new PdfPCell(new Phrase(tAsentimiento, mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Contacto Futuro: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        Character contact = obj.getContactoFuturo();
        Character c1 = new Character('1');
        Character c2 = new Character(obj.getContactoFuturo());
        boolean res = c1.equals(c2);
        if (res){
            cell = new PdfPCell(new Phrase("Acepto",mia));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            document.add(table);
        }else {
            cell = new PdfPCell(new Phrase("No Acepto",mia));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            document.add(table);
        }
        table = new PdfPTable(new float[]{38,58});
        table.setWidthPercentage(96f);
        cell = new PdfPCell(new Phrase("Observación: ",miaNormal));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        String observa = obj.getObservacion().toString();
        if (observa==null || observa == ""){observa = "Ninguna";}
        cell = new PdfPCell(new Phrase(observa,mia));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        document.add(table);
        document.close();

    }// fin del reporte carta Scam

    private String getDescripcionCatalogo(String codigo,String catroot){
        for(MessageResource rnv : messageExtremidades){
            if (rnv.getCatKey().equals(codigo)) {
                if (catroot != "" && rnv.getCatRoot().equals(catroot))
                    return rnv.getSpanish();
            }
        }
        return "-";
    }

//region Reporte Envio de Muestras
        private void ReporteEnvio(Map<String, Object> model, Document document, PdfWriter writer)throws Exception{
            List<SerologiaEnvio> SerologiasEnviadas = (List<SerologiaEnvio>)  model.get("SerologiasEnviadas");
            Integer numeroEnvios = (Integer) model.get("nEnvios");
            String f1 = (String) model.get("fechaInicio");
            String f2 = (String) model.get("fechaFin");

            Date fecha_inicio = DateUtil.StringToDate(f1,"dd/MM/yyyy");
            Date fecha_fin = DateUtil.StringToDate(f2,"dd/MM/yyyy");

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(fecha_inicio);
            int yearsNow  = calendar2.get(Calendar.YEAR);

            document.newPage();
            document.open();

            HeaderFooter objHeaderFooter;

            Font miaNormal = new Font(Font.COURIER,12, Font.NORMAL);
            Font mia = new Font(Font.COURIER,12, Font.BOLDITALIC);
            Font miaEstudio = new Font(Font.COURIER,9);
            Date objDate = new Date();
            DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat hourFormat = new SimpleDateFormat("HH:mm");
            Paragraph fecha = new Paragraph(dateformat.format(objDate) + " " + hourFormat.format(objDate));
            fecha.setAlignment(Element.ALIGN_RIGHT);
            //document.add(fecha);

            PdfPTable table = new PdfPTable(new float[]{10,10,10,10,20});
            PdfPCell cell;

            table = new PdfPTable(new float[]{70,30});

            cell = new PdfPCell(new Phrase("CENTRO DE SALUD 'SÓCRATES FLORES.'" +"\n"+ "RECEPCIÓN DE MUESTRAS TUBOS ROJOS.",FontFactory.getFont("COURIER", 14, java.awt.Font.BOLD, Color.black)));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_BOTTOM);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("MUESTREO ANUAL CHF OCT " + yearsNow + "."+ "\n" + "Fecha:" + dateformat.format(objDate)+"\n" + "Viaje: "+numeroEnvios +" \n ",FontFactory.getFont("COURIER", 14, java.awt.Font.BOLD, Color.black)));
            cell.setBorder(0);
            table.addCell(cell);

            document.add(table);
/*
            Paragraph encabezado = new Paragraph("CENTRO DE SALUD 'SÓCRATES FLORES'\n" +
                    " RECEPCIÓN DE MUESTRAS TUBOS ROJOS.",FontFactory.getFont("COURIER", 16, java.awt.Font.BOLD, Color.black));
            encabezado.setAlignment(Element.ALIGN_CENTER);

            Paragraph encabezado2 = new Paragraph("REPORTE ENVÍO DE MUESTRAS CH FAMILIA "+ yearsNow+".", FontFactory.getFont("COURIER",14, java.awt.Font.ITALIC));
            encabezado2.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            document.add(encabezado2);

                        table = new PdfPTable(1);
            cell = new PdfPCell(new Phrase("Número de Envio: "+numeroEnvios,miaEstudio));
            cell.setBorder(0);
            table.addCell(cell);
            document.add(table);


*/


            Paragraph paragraph0 = new Paragraph();
            addEmptyLine(paragraph0, 1);
            document.add(paragraph0);

            table = new PdfPTable(new float[]{50,50});
            cell = new PdfPCell(new Phrase("Fecha Inicio: "+f1,miaEstudio));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha Fin: "+f2,miaEstudio));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            document.add(table);

            LineSeparator ls1 = new LineSeparator();
            ls1.setLineWidth(0.5f);
            document.add(new Chunk(ls1));


            Paragraph paragraph1 = new Paragraph();
            addEmptyLine(paragraph1, 1);
            document.add(paragraph1);

            table = new PdfPTable(new float[]{10,10,6,6,10,18});
            table.setHeaderRows(1);
            cell = new PdfPCell(new Phrase("CÓDIGO",mia));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("VOLUMEN",mia));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("AÑOS",mia));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("MESES",mia));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("ESTUDIOS",mia));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("OBSERVACIONES",mia));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            document.add(table);

           /* if (SerologiasEnviadas.size()<=0){

                table = new PdfPTable(1);
                cell = new PdfPCell(new Phrase("No hay información!",mia));
                cell.setBorder(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                document.add(table);

            }*/

            for (SerologiaEnvio obj : SerologiasEnviadas) {

                //codigo
                if (obj.getSerologia().getParticipante() == null){
                    cell = new PdfPCell(new Phrase(obj.getSerologia().getCodigonuevoparticipante().toString(),miaNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }else{
                    cell = new PdfPCell(new Phrase(obj.getSerologia().getParticipante().getCodigo().toString(),miaNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }

                //volumen
                cell = new PdfPCell(new Phrase(""+obj.getSerologia().getVolumen(),miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);


                //edad

                if (obj.getSerologia().getParticipante() == null){
                    cell = new PdfPCell(new Phrase("-",mia));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                }else{

                    String s = obj.getSerologia().getParticipante().getEdad().toString();
                    String[] result = s.split("/", 3);
                    cell = new PdfPCell(new Phrase(result[0],miaNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(result[1],miaNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }

                //estudios
                if (obj.getSerologia().getParticipante()==null){
                    cell = new PdfPCell(new Phrase("-",miaNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                }else{
                    cell = new PdfPCell(new Phrase(obj.getSerologia().getEstudio(),miaEstudio));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                }


                //estudios
                cell = new PdfPCell(new Phrase(obj.getSerologia().getObservacion(),miaNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);


            }
            document.add(table);


            table = new PdfPTable(new float[]{100});
            cell = new PdfPCell(new Phrase("Total: "+SerologiasEnviadas.size(),miaEstudio));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(1);
            table.addCell(cell);
            document.add(table);

            Paragraph paragraph2 = new Paragraph();
            addEmptyLine(paragraph2, 2);
            document.add(paragraph2);

            table = new PdfPTable(new float[]{40,20,40});
            cell = new PdfPCell(new Phrase("Entregado Por: ",miaEstudio));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(1);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("",miaEstudio));
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Recibido Por",miaEstudio));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(1);
            table.addCell(cell);
            document.add(table);


            table = new PdfPTable(1);
            table.addCell(createCell("Página: "+ writer.getCurrentPageNumber(), miaNormal, Rectangle.NO_BORDER));
            document.add(table);
            document.close();
        }

        private static void addEmptyLine(Paragraph paragraph, int number) {
            for (int i = 0; i < number; i++) {
                paragraph.add(new Paragraph(" "));
            }
        }
 //endregion






    private String getDescripcionCatalogoScan(String codigo,String catroot){

        for (MessageResource rnv : messagerelFam){
            if (rnv.getCatKey().equals(codigo)) {
                if (catroot != "" && rnv.getCatRoot().equals(catroot))
                    return rnv.getSpanish();
            }
        }
        return "-";
    }


}

class MyFooter extends PdfPageEventHelper {
    Font ffont = new Font(Font.COURIER, 8, Font.ITALIC);

    private List<String> mensajes = new ArrayList<String>();
    private int numberPage;

    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        int posicion = 5;
        for(String mensaje : mensajes) {
            Phrase footer = new Phrase("* "+mensaje, ffont);

            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                    footer,
                    (document.left()) / 2 + document.leftMargin(),//(document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - posicion, 0);
            posicion+=10;
        }

        if (numberPage > 0) {
            Phrase footer = new Phrase("* " + String.valueOf(numberPage), ffont);
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
                    footer,
                    (document.left()) / 2 + document.leftMargin(),//(document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - posicion, 0);
        }

    }
    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);

    }


}
