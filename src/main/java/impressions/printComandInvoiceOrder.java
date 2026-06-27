package impressions;

import java.awt.Desktop;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.*;

import javax.swing.JOptionPane;

import model.beans.ComandaDetBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class printComandInvoiceOrder.
 */
public class printComandInvoiceOrder {

    /** The Constant BRL. */
    private static final NumberFormat    BRL      = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    
    /** The Constant QTY. */
    private static final DecimalFormat   QTY      = new DecimalFormat("#.##");
    
    /** The Constant FMT_DATA. */
    private static final SimpleDateFormat FMT_DATA = new SimpleDateFormat("dd/MM/yyyy");
    
    /** The Constant FMT_HORA. */
    private static final SimpleDateFormat FMT_HORA = new SimpleDateFormat("HH:mm");

    /**
     * Prints the invoice.
     *
     * @param comandDet the comand det
     */
    public void printInvoice(ArrayList<ComandaDetBeans> comandDet) {
        if (comandDet == null || comandDet.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum item para imprimir.");
            return;
        }
        try {
            File arquivo = gerarOdt(comandDet);
            abrirArquivo(arquivo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar ODT: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gerar odt.
     *
     * @param itens the itens
     * @return the file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private File gerarOdt(ArrayList<ComandaDetBeans> itens) throws IOException {
        File arquivo = new File("comanda_" + itens.get(0).getIdComanda() + ".odt");

        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(arquivo))) {

            byte[] mtype = "application/vnd.oasis.opendocument.text".getBytes("ISO8859_1");
            ZipEntry me = new ZipEntry("mimetype");
            me.setMethod(ZipEntry.STORED);
            me.setSize(mtype.length);
            me.setCompressedSize(mtype.length);
            CRC32 crc = new CRC32();
            crc.update(mtype);
            me.setCrc(crc.getValue());
            zip.putNextEntry(me);
            zip.write(mtype);
            zip.closeEntry();

            zip.putNextEntry(new ZipEntry("META-INF/manifest.xml"));
            zip.write(manifest().getBytes("ISO8859_1"));
            zip.closeEntry();

            zip.putNextEntry(new ZipEntry("styles.xml"));
            zip.write(styles().getBytes("ISO8859_1"));
            zip.closeEntry();

            zip.putNextEntry(new ZipEntry("content.xml"));
            zip.write(content(itens).getBytes("ISO8859_1"));
            zip.closeEntry();
        }

        return arquivo;
    }

    /**
     * Manifest.
     *
     * @return the string
     */
    private String manifest() {
        return "<?xml version=\"1.0\" encoding=\"ISO8859_1\"?>"
             + "<manifest:manifest xmlns:manifest=\"urn:oasis:names:tc:opendocument:xmlns:manifest:1.0\">"
             + "<manifest:file-entry manifest:full-path=\"/\"           manifest:media-type=\"application/vnd.oasis.opendocument.text\"/>"
             + "<manifest:file-entry manifest:full-path=\"content.xml\" manifest:media-type=\"text/xml\"/>"
             + "<manifest:file-entry manifest:full-path=\"styles.xml\"  manifest:media-type=\"text/xml\"/>"
             + "</manifest:manifest>";
    }

    /**
     * Styles.
     *
     * @return the string
     */
    private String styles() {
        return "<?xml version=\"1.0\" encoding=\"ISO8859_1\"?>"
             + "<office:document-styles"
             + "  xmlns:office=\"urn:oasis:names:tc:opendocument:xmlns:office:1.0\""
             + "  xmlns:style=\"urn:oasis:names:tc:opendocument:xmlns:style:1.0\""
             + "  xmlns:fo=\"urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0\">"
             + "<office:styles>"
             + "<style:default-style style:family=\"paragraph\">"
             + "  <style:text-properties fo:font-family=\"Courier New\" fo:font-size=\"10pt\"/>"
             + "</style:default-style>"
             + "</office:styles>"
             + "</office:document-styles>";
    }

    /**
     * Content.
     *
     * @param itens the itens
     * @return the string
     */
    private String content(ArrayList<ComandaDetBeans> itens) {
        ComandaDetBeans primeiro = itens.get(0);
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\" encoding=\"ISO8859_1\"?>");
        sb.append("<office:document-content");
        sb.append("  xmlns:office=\"urn:oasis:names:tc:opendocument:xmlns:office:1.0\"");
        sb.append("  xmlns:text=\"urn:oasis:names:tc:opendocument:xmlns:text:1.0\"");
        sb.append("  xmlns:style=\"urn:oasis:names:tc:opendocument:xmlns:style:1.0\"");
        sb.append("  xmlns:fo=\"urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0\"");
        sb.append("  xmlns:table=\"urn:oasis:names:tc:opendocument:xmlns:table:1.0\">");

        sb.append("<office:automatic-styles>");
        estilo(sb, "Titulo",  "paragraph", "Courier New", "12pt", "bold",   "center");
        estilo(sb, "Negrito", "paragraph", "Courier New", "10pt", "bold",   "left");
        estilo(sb, "Normal",  "paragraph", "Courier New", "10pt", "normal", "left");
        estilo(sb, "Total",   "paragraph", "Courier New", "12pt", "bold",   "left");

        colStyle(sb, "ColCod",  "2.5cm");
        colStyle(sb, "ColProd", "6cm");
        colStyle(sb, "ColNum",  "1.8cm");
        colStyle(sb, "ColVlr",  "3cm");

        sb.append("</office:automatic-styles>");
        sb.append("<office:body><office:text>");

        p(sb, "====  COMANDA EM VERIFICACAO  ====", "Titulo");
        p(sb, "Comanda N.: " + primeiro.getIdComanda(), "Negrito");

        if (primeiro.getDataComanda() != null) {
            p(sb, "Data: " + FMT_DATA.format(primeiro.getDataComanda())
                + "   Hora: " + FMT_HORA.format(primeiro.getHoraComanda()), "Normal");
        }

        p(sb, "-------------------------------------------", "Normal");

        sb.append("<table:table table:name=\"Itens\">");
        sb.append("<table:table-column table:style-name=\"ColCod\"/>");
        sb.append("<table:table-column table:style-name=\"ColProd\"/>");
        sb.append("<table:table-column table:style-name=\"ColNum\"/>");
        sb.append("<table:table-column table:style-name=\"ColVlr\"/>");
        sb.append("<table:table-column table:style-name=\"ColVlr\"/>");

        tabelaLinha(sb, "COD.", "PRODUTO", "QTD", "UNIT.", "TOTAL", "Negrito");

        double totalGeral = 0.0;
        for (ComandaDetBeans item : itens) {
            String cod  = item.getIdProduto()       != null ? item.getIdProduto()       : "---";
            String nome = item.getDescricaoProduto() != null ? item.getDescricaoProduto() : "---";

            tabelaLinha(sb,
                cod,
                nome,
                QTY.format(item.getQtdeComandaDetalhe()),
                BRL.format(item.getVlrUnitarioComandaDetalhe()),
                BRL.format(item.getVlrTotFinalComandaDetalhe()),
                "Normal");

            totalGeral += item.getVlrTotFinalComandaDetalhe();
        }
        sb.append("</table:table>");

        p(sb, "-------------------------------------------", "Normal");
        p(sb, "TOTAL: " + BRL.format(totalGeral), "Total");
        p(sb, " ", "Normal");
        p(sb, "** CONTA EM ABERTO **", "Titulo");

        sb.append("</office:text></office:body>");
        sb.append("</office:document-content>");
        return sb.toString();
    }

    /**
     * Estilo.
     *
     * @param sb the sb
     * @param nome the nome
     * @param family the family
     * @param font the font
     * @param size the size
     * @param weight the weight
     * @param align the align
     */
    private void estilo(StringBuilder sb, String nome, String family,
                        String font, String size, String weight, String align) {
        sb.append("<style:style style:name=\"").append(nome).append("\" style:family=\"").append(family).append("\">");
        sb.append("<style:paragraph-properties fo:text-align=\"").append(align).append("\"/>");
        sb.append("<style:text-properties fo:font-family=\"").append(font).append("\"")
          .append(" fo:font-size=\"").append(size).append("\"")
          .append(" fo:font-weight=\"").append(weight).append("\"/>");
        sb.append("</style:style>");
    }

    /**
     * Col style.
     *
     * @param sb the sb
     * @param nome the nome
     * @param largura the largura
     */
    private void colStyle(StringBuilder sb, String nome, String largura) {
        sb.append("<style:style style:name=\"").append(nome).append("\" style:family=\"table-column\">")
          .append("<style:table-column-properties style:column-width=\"").append(largura).append("\"/>")
          .append("</style:style>");
    }

    /**
     * Tabela linha.
     *
     * @param sb the sb
     * @param c1 the c 1
     * @param c2 the c 2
     * @param c3 the c 3
     * @param c4 the c 4
     * @param c5 the c 5
     * @param style the style
     */
    private void tabelaLinha(StringBuilder sb,
                              String c1, String c2, String c3, String c4, String c5,
                              String style) {
        sb.append("<table:table-row>");
        celula(sb, c1, style);
        celula(sb, c2, style);
        celula(sb, c3, style);
        celula(sb, c4, style);
        celula(sb, c5, style);
        sb.append("</table:table-row>");
    }

    /**
     * Celula.
     *
     * @param sb the sb
     * @param texto the texto
     * @param style the style
     */
    private void celula(StringBuilder sb, String texto, String style) {
        sb.append("<table:table-cell>")
          .append("<text:p text:style-name=\"").append(style).append("\">")
          .append(escapeXml(texto))
          .append("</text:p>")
          .append("</table:table-cell>");
    }

    /**
     * P.
     *
     * @param sb the sb
     * @param texto the texto
     * @param style the style
     */
    private void p(StringBuilder sb, String texto, String style) {
        sb.append("<text:p text:style-name=\"").append(style).append("\">")
          .append(escapeXml(texto))
          .append("</text:p>");
    }

    /**
     * Escape xml.
     *
     * @param s the s
     * @return the string
     */
    private static String escapeXml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }

    /**
     * Abrir arquivo.
     *
     * @param arquivo the arquivo
     */
    private static void abrirArquivo(File arquivo) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(arquivo);
            } else {
                JOptionPane.showMessageDialog(null,
                    "Arquivo gerado em:\n" + arquivo.getAbsolutePath());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}