package impressions;

import java.awt.Desktop;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import model.beans.ComandaDetBeans;

public class printComandaInvoiceOrder {

	private File fileOdt;
	private static final NumberFormat BRL = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	private static final DecimalFormat QTY = new DecimalFormat("#.##");
	private static final SimpleDateFormat FMT_DATA = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat FMT_HORA = new SimpleDateFormat("HH:mm");

	public void generateOdt(int idBlocoComanda) throws IOException {
		File pasta = new File(System.getProperty("java.io.tmpdir"), "bestcheff");
		pasta.mkdirs();

		fileOdt = new File(pasta, "comanda" + idBlocoComanda + ".odt");

		try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(fileOdt))) {
			writeMimetype(zip);
			writeEntry(zip, "META-INF/manifest.xml", manifest());
			writeEntry(zip, "styles.xml", styles());
			writeEntry(zip, "content.xml", contentVazio());
		}
	}

	public void insertAndOpen(ArrayList<ComandaDetBeans> itens) throws IOException {
		File temp = File.createTempFile("comanda_tmp", ".odt", fileOdt.getParentFile());

		try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(temp))) {
			writeMimetype(zip);
			writeEntry(zip, "META-INF/manifest.xml", manifest());
			writeEntry(zip, "styles.xml", styles());
			writeEntry(zip, "content.xml", contentComDados(itens));
		}

		Files.move(temp.toPath(), fileOdt.toPath(), StandardCopyOption.REPLACE_EXISTING);

		openFile(fileOdt);
		agendarDelete(fileOdt);
	}

	private void agendarDelete(File arquivo) {
		Thread t = new Thread(() -> {
			while (arquivo.exists()) {
				try {
					Thread.sleep(3000);
					if (arquivo.renameTo(arquivo)) {
						arquivo.delete();
						break;
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}
			}
		});
		t.setDaemon(true);
		t.start();
	}

	public void openFile(File file) {
		try {
			if (Desktop.isDesktopSupported())
				Desktop.getDesktop().open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeMimetype(ZipOutputStream zip) throws IOException {
		byte[] mtype = "application/vnd.oasis.opendocument.text".getBytes(StandardCharsets.UTF_8);
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
	}

	private void writeEntry(ZipOutputStream zip, String nome, String conteudo) throws IOException {
		zip.putNextEntry(new ZipEntry(nome));
		zip.write(conteudo.getBytes(StandardCharsets.UTF_8));
		zip.closeEntry();
	}

	private String contentVazio() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<office:document-content"
				+ "  xmlns:office=\"urn:oasis:names:tc:opendocument:xmlns:office:1.0\""
				+ "  xmlns:text=\"urn:oasis:names:tc:opendocument:xmlns:text:1.0\""
				+ "  xmlns:style=\"urn:oasis:names:tc:opendocument:xmlns:style:1.0\""
				+ "  xmlns:fo=\"urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0\""
				+ "  xmlns:table=\"urn:oasis:names:tc:opendocument:xmlns:table:1.0\">"
				+ "<office:automatic-styles/>"
				+ "<office:body><office:text/></office:body>"
				+ "</office:document-content>";
	}

	private String contentComDados(ArrayList<ComandaDetBeans> itens) {
		StringBuilder sb = new StringBuilder();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<office:document-content");
		sb.append("  xmlns:office=\"urn:oasis:names:tc:opendocument:xmlns:office:1.0\"");
		sb.append("  xmlns:text=\"urn:oasis:names:tc:opendocument:xmlns:text:1.0\"");
		sb.append("  xmlns:style=\"urn:oasis:names:tc:opendocument:xmlns:style:1.0\"");
		sb.append("  xmlns:fo=\"urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0\"");
		sb.append("  xmlns:table=\"urn:oasis:names:tc:opendocument:xmlns:table:1.0\">");

		sb.append("<office:automatic-styles>");
		estilo(sb, "Titulo", "paragraph", "Courier New", "11pt", "bold", "center");
		estilo(sb, "Negrito", "paragraph", "Courier New", "10pt", "bold", "start");
		estilo(sb, "Normal", "paragraph", "Courier New", "10pt", "normal", "start");
		estilo(sb, "Total", "paragraph", "Courier New", "11pt", "bold", "start");
		colStyle(sb, "ColCod", "1.2cm");
		colStyle(sb, "ColProd", "5.5cm");
		colStyle(sb, "ColNum", "1.2cm");
		colStyle(sb, "ColVlr", "2.3cm");
		sb.append("</office:automatic-styles>");

		sb.append("<office:body>");
		sb.append("<office:text>");
		insertInformationOdt(sb, itens);
		sb.append("</office:text>");
		sb.append("</office:body>");
		sb.append("</office:document-content>");

		return sb.toString();
	}

	private void insertInformationOdt(StringBuilder sb, ArrayList<ComandaDetBeans> itens) {
		ComandaDetBeans primeiro = itens.get(0);

		p(sb, "====  COMANDA EM VERIFICACAO  ====", "Titulo");
		p(sb, "Comanda N.: " + primeiro.getIdBlocoComanda(), "Negrito");

		if (primeiro.getDataComanda() != null) {
			p(sb, "Data: " + FMT_DATA.format(primeiro.getDataComanda()) + "   Hora: "
					+ FMT_HORA.format(primeiro.getHoraComanda()), "Normal");
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
			String cod = item.getIdProduto() != null ? item.getIdProduto() : "---";
			String nome = item.getDescricaoProduto() != null ? item.getDescricaoProduto() : "---";

			tabelaLinha(sb, cod, nome,
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
	}

	private String manifest() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<manifest:manifest xmlns:manifest=\"urn:oasis:names:tc:opendocument:xmlns:manifest:1.0\">"
				+ "<manifest:file-entry manifest:full-path=\"/\" manifest:media-type=\"application/vnd.oasis.opendocument.text\"/>"
				+ "<manifest:file-entry manifest:full-path=\"content.xml\" manifest:media-type=\"text/xml\"/>"
				+ "<manifest:file-entry manifest:full-path=\"styles.xml\" manifest:media-type=\"text/xml\"/>"
				+ "</manifest:manifest>";
	}

	private String styles() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<office:document-styles"
				+ "  xmlns:office=\"urn:oasis:names:tc:opendocument:xmlns:office:1.0\""
				+ "  xmlns:style=\"urn:oasis:names:tc:opendocument:xmlns:style:1.0\""
				+ "  xmlns:fo=\"urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0\">"
				+ "<office:styles>"
				+ "<style:default-style style:family=\"paragraph\">"
				+ "<style:text-properties fo:font-family=\"Courier New\" fo:font-size=\"10pt\"/>"
				+ "</style:default-style>"
				+ "</office:styles>"
				+ "</office:document-styles>";
	}

	private void estilo(StringBuilder sb, String nome, String family, String font, String size, String weight, String align) {
		sb.append("<style:style style:name=\"").append(nome).append("\" style:family=\"").append(family).append("\">");
		sb.append("<style:paragraph-properties fo:text-align=\"").append(align).append("\"/>");
		sb.append("<style:text-properties fo:font-family=\"").append(font).append("\"")
				.append(" fo:font-size=\"").append(size).append("\"")
				.append(" fo:font-weight=\"").append(weight).append("\"/>");
		sb.append("</style:style>");
	}

	private void colStyle(StringBuilder sb, String nome, String largura) {
		sb.append("<style:style style:name=\"").append(nome).append("\" style:family=\"table-column\">")
				.append("<style:table-column-properties style:column-width=\"").append(largura).append("\"/>")
				.append("</style:style>");
	}

	private void tabelaLinha(StringBuilder sb, String c1, String c2, String c3, String c4, String c5, String style) {
		sb.append("<table:table-row>");
		celula(sb, c1, style);
		celula(sb, c2, style);
		celula(sb, c3, style);
		celula(sb, c4, style);
		celula(sb, c5, style);
		sb.append("</table:table-row>");
	}

	private void celula(StringBuilder sb, String texto, String style) {
		sb.append("<table:table-cell>")
				.append("<text:p text:style-name=\"").append(style).append("\">")
				.append(escapeXml(texto))
				.append("</text:p>")
				.append("</table:table-cell>");
	}

	private void p(StringBuilder sb, String texto, String style) {
		sb.append("<text:p text:style-name=\"").append(style).append("\">")
				.append(escapeXml(texto))
				.append("</text:p>");
	}

	private static String escapeXml(String s) {
		if (s == null)
			return "";
		return s.replace("&", "&amp;")
				.replace("<", "&lt;")
				.replace(">", "&gt;")
				.replace("\"", "&quot;")
				.replace("'", "&apos;");
	}
}