package impressions;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import model.beans.Comandas.ComandaDetBeans;
import model.beans.Emitente.EmitenteBeans;


// TODO: Auto-generated Javadoc
/**
 * The Class printComandaInvoiceOrder.
 * @author alephe
 * The Class was made by AI with help of one programmer.
 */
public class printComandaInvoiceOrder {
	
	/** The file odt. */
	private File fileOdt;
	
	/** The Constant BRL. */
	@SuppressWarnings("deprecation")
	private static final NumberFormat BRL = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	
	/** The Constant QTY. */
	private static final DecimalFormat QTY = new DecimalFormat("#.##");
	
	/** The Constant FMT_DATA. */
	private static final SimpleDateFormat FMT_DATA = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Generate odt.
	 *
	 * @param idBlocoComanda the id bloco comanda
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void generateOdt(int idBlocoComanda) throws IOException {
		String os = System.getProperty("os.name").toLowerCase();
		File pasta;

		if (os.contains("win")) {
			pasta = new File("C:/home/");
		} else {
			pasta = new File(System.getProperty("user.dir"));
		}

		pasta.mkdirs();

		fileOdt = new File(pasta, "comanda" + idBlocoComanda + ".odt");

		try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(fileOdt))) {
			writeMimetype(zip);
			writeEntry(zip, "META-INF/manifest.xml", manifest());
			writeEntry(zip, "styles.xml", styles());
			writeEntry(zip, "content.xml", contentVazio());
		}
	}

	/**
	 * Insert and open.
	 *
	 * @param itens the itens
	 * @param emitente the emitente
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void insertAndOpen(ArrayList<ComandaDetBeans> itens, EmitenteBeans emitente) throws IOException {
		File temp = File.createTempFile("comanda_tmp", ".odt", fileOdt.getParentFile());

		try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(temp))) {
			writeMimetype(zip);
			writeEntry(zip, "META-INF/manifest.xml", manifest());
			writeEntry(zip, "styles.xml", styles());
			writeEntry(zip, "content.xml", contentComDados(itens, emitente));
		}

		Files.move(temp.toPath(), fileOdt.toPath(), StandardCopyOption.REPLACE_EXISTING);

		openFile(fileOdt);

		try {
			imprimirNaImpressoraDisponivel(itens, emitente);
		} catch (PrintException e) {
			e.printStackTrace();
		}

		agendarDelete(fileOdt);
	}

	/**
	 * Insert and print.
	 *
	 * @param itens the itens
	 * @param emitente the emitente
	 * @throws PrintException the print exception
	 */
	public void insertAndPrint(ArrayList<ComandaDetBeans> itens, EmitenteBeans emitente) throws PrintException {
		PrintService padrao = PrintServiceLookup.lookupDefaultPrintService();
		if (padrao == null)
			throw new PrintException("Nenhuma impressora padrao configurada.");
		insertAndPrint(itens, emitente, padrao);
	}

	/**
	 * Insert and print.
	 *
	 * @param itens the itens
	 * @param emitente the emitente
	 * @param printService the print service
	 * @throws PrintException the print exception
	 */
	public void insertAndPrint(ArrayList<ComandaDetBeans> itens, EmitenteBeans emitente, PrintService printService)
			throws PrintException {
		String texto = montaTextoRecibo(itens, emitente);
		byte[] bytes = texto.getBytes(StandardCharsets.UTF_8);

		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		Doc doc = new SimpleDoc(bytes, flavor, null);
		PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();

		DocPrintJob job = printService.createPrintJob();
		job.print(doc, attrs);
	}

	/**
	 * Listar impressoras.
	 *
	 * @return the prints the service[]
	 */
	public PrintService[] listarImpressoras() {
		return PrintServiceLookup.lookupPrintServices(DocFlavor.BYTE_ARRAY.AUTOSENSE, null);
	}

	/**
	 * Buscar impressora.
	 *
	 * @param nome the nome
	 * @return the prints the service
	 */
	public PrintService buscarImpressora(String nome) {
		for (PrintService ps : listarImpressoras()) {
			if (ps.getName().equalsIgnoreCase(nome))
				return ps;
		}
		return null;
	}

	/**
	 * Buscar impressora para impressao.
	 *
	 * @return the prints the service
	 */
	private PrintService buscarImpressoraParaImpressao() {
		PrintService[] impressoras = listarImpressoras();

		if (impressoras == null || impressoras.length == 0) {
			return null;
		}

		String[] palavrasChave = { "fiscal", "nota", "nfce", "nfe", "sat", "ecf", "cupom" };

		for (PrintService ps : impressoras) {
			String nome = ps.getName().toLowerCase();
			for (String chave : palavrasChave) {
				if (nome.contains(chave)) {
					return ps;
				}
			}
		}

		PrintService padrao = PrintServiceLookup.lookupDefaultPrintService();
		if (padrao != null) {
			return padrao;
		}

		return impressoras[0];
	}

	/**
	 * Imprimir na impressora disponivel.
	 *
	 * @param itens the itens
	 * @param emitente the emitente
	 * @throws PrintException the print exception
	 */
	private void imprimirNaImpressoraDisponivel(ArrayList<ComandaDetBeans> itens, EmitenteBeans emitente)
			throws PrintException {
		PrintService impressora = buscarImpressoraParaImpressao();

		if (impressora == null) {
			throw new PrintException("Nenhuma impressora encontrada no sistema.");
		}

		insertAndPrint(itens, emitente, impressora);
	}

	/**
	 * Monta texto recibo.
	 *
	 * @param itens the itens
	 * @param emitente the emitente
	 * @return the string
	 */
	private String montaTextoRecibo(ArrayList<ComandaDetBeans> itens, EmitenteBeans emitente) {
		StringBuilder sb = new StringBuilder();
		ComandaDetBeans primeiro = itens.get(0);

		sb.append(padRight("==============DADOS DA EMPRESA=============", 49) + "RAZAO.: " + emitente.getNome())
				.append("\n");
		sb.append(padRight("CNPJ.: " + emitente.getCnpj(), 49) + "IE.: " + emitente.getIe()).append("\n");
		sb.append(padRight("Endereco.: " + emitente.getEndereco(), 49) + "Numero.: " + emitente.getNumero())
				.append("\n");
		sb.append(padRight("Bairro.: " + emitente.getBairro(), 49) + "Telefone.: " + emitente.getTelefone())
				.append("\n");

		sb.append(padRight("==============DADOS DA COMANDA=============", 49) + "COMANDA .: "
				+ primeiro.getIdBlocoComanda()).append("\n");
		String dtCmd = primeiro.getDataComanda() != null ? FMT_DATA.format(primeiro.getDataComanda()) : "";
		sb.append(padRight("Comanda N.: " + primeiro.getIdBlocoComanda(), 49) + "Data Comanda.: " + dtCmd).append("\n");

		sb.append(padRight("============== ITENS =====================", 49) + "Codigo      Produto").append("\n");
		sb.append(padRight("Qtde  Valor", 49) + "Total").append("\n");
		sb.append("---------------------------------------------------------------------------").append("\n");

		double totalGeral = 0.0;
		for (ComandaDetBeans item : itens) {
			String cod = item.getIdProduto() != null ? item.getIdProduto() : "---";
			String nome = item.getDescricaoProduto() != null ? item.getDescricaoProduto() : "---";
			String qty = QTY.format(item.getQtdeComandaDetalhe());
			
			String vlrU = new DecimalFormat("#,##0.00").format(item.getVlrUnitarioComandaDetalhe());
			String vlrT = BRL.format(item.getVlrTotFinalComandaDetalhe());

			sb.append("  " + padRight(cod, 10) + padRight(nome, 38) + padLeft(qty + " X  " + vlrU, 20)).append("\n");
			sb.append("= " + vlrT).append("\n");

			totalGeral += item.getVlrTotFinalComandaDetalhe();
		}

		sb.append("---------------------------------------------------------------------------").append("\n");

		String func = primeiro.getNomeFuncionario() != null ? primeiro.getNomeFuncionario() : "";
		sb.append(padRight("  Funcionário .: " + func, 49) + "CONSUMIDOR").append("\n");
		sb.append("  Total.: COMANDA EM ABERTO. " + BRL.format(totalGeral)).append("\n");
		sb.append("  Obs .:").append("\n");

		String cidade = emitente.getCidade() != null ? emitente.getCidade().toUpperCase() : "";
		@SuppressWarnings("deprecation")
		String dataExt = primeiro.getDataComanda() != null
				? new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new Locale("pt", "BR")).format(
						primeiro.getDataComanda())
				: "";
		sb.append("  " + cidade + ", " + dataExt).append("\n");

		sb.append("\f");

		return sb.toString();
	}

	/**
	 * Agendar delete.
	 *
	 * @param arquivo the arquivo
	 */
	private void agendarDelete(File arquivo) {
		arquivo.deleteOnExit();
	}

	/**
	 * Open file.
	 *
	 * @param file the file
	 */
	public void openFile(File file) {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write mimetype.
	 *
	 * @param zip the zip
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	/**
	 * Write entry.
	 *
	 * @param zip the zip
	 * @param nome the nome
	 * @param conteudo the conteudo
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void writeEntry(ZipOutputStream zip, String nome, String conteudo) throws IOException {
		zip.putNextEntry(new ZipEntry(nome));
		zip.write(conteudo.getBytes(StandardCharsets.UTF_8));
		zip.closeEntry();
	}

	/**
	 * Content vazio.
	 *
	 * @return the string
	 */
	private String contentVazio() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<office:document-content"
				+ "  xmlns:office=\"urn:oasis:names:tc:opendocument:xmlns:office:1.0\""
				+ "  xmlns:text=\"urn:oasis:names:tc:opendocument:xmlns:text:1.0\""
				+ "  xmlns:style=\"urn:oasis:names:tc:opendocument:xmlns:style:1.0\""
				+ "  xmlns:fo=\"urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0\">"
				+ "<office:automatic-styles/>" + "<office:body><office:text/></office:body>"
				+ "</office:document-content>";
	}

	/**
	 * Content com dados.
	 *
	 * @param itens the itens
	 * @param emitente the emitente
	 * @return the string
	 */
	private String contentComDados(ArrayList<ComandaDetBeans> itens, EmitenteBeans emitente) {
		StringBuilder sb = new StringBuilder();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<office:document-content");
		sb.append("  xmlns:office=\"urn:oasis:names:tc:opendocument:xmlns:office:1.0\"");
		sb.append("  xmlns:text=\"urn:oasis:names:tc:opendocument:xmlns:text:1.0\"");
		sb.append("  xmlns:style=\"urn:oasis:names:tc:opendocument:xmlns:style:1.0\"");
		sb.append("  xmlns:fo=\"urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0\">");

		sb.append("<office:automatic-styles>");
		estilo(sb, "Normal", "paragraph", "Courier New", "10pt", "normal", "start");
		sb.append("</office:automatic-styles>");

		sb.append("<office:body>");
		sb.append("<office:text>");
		insertInformationOdt(sb, itens, emitente);
		sb.append("</office:text>");
		sb.append("</office:body>");
		sb.append("</office:document-content>");

		return sb.toString();
	}

	/**
	 * Insert information odt.
	 *
	 * @param sb the sb
	 * @param itens the itens
	 * @param emitente the emitente
	 */
	private void insertInformationOdt(StringBuilder sb, ArrayList<ComandaDetBeans> itens, EmitenteBeans emitente) {
		ComandaDetBeans primeiro = itens.get(0);

		p(sb, padRight("==============DADOS DA EMPRESA=============", 49) + "RAZAO.: " + emitente.getNome(), "Normal");
		p(sb, padRight("CNPJ.: " + emitente.getCnpj(), 49) + "IE.: " + emitente.getIe(), "Normal");
		p(sb, padRight("Endereco.: " + emitente.getEndereco(), 49) + "Numero.: " + emitente.getNumero(), "Normal");
		p(sb, padRight("Bairro.: " + emitente.getBairro(), 49) + "Telefone.: " + emitente.getTelefone(), "Normal");

		p(sb, padRight("==============DADOS DA COMANDA=============", 49) + "COMANDA .: "
				+ primeiro.getIdBlocoComanda(), "Normal");
		String dtCmd = primeiro.getDataComanda() != null ? FMT_DATA.format(primeiro.getDataComanda()) : "";
		p(sb, padRight("Comanda N.: " + primeiro.getIdBlocoComanda(), 49) + "Data Comanda.: " + dtCmd, "Normal");

		p(sb, padRight("============== ITENS =====================", 49) + "Codigo      Produto", "Normal");
		p(sb, padRight("Qtde  Valor", 49) + "Total", "Normal");
		p(sb, "---------------------------------------------------------------------------", "Normal");

		double totalGeral = 0.0;
		for (ComandaDetBeans item : itens) {
			String cod = item.getIdProduto() != null ? item.getIdProduto() : "---";
			String nome = item.getDescricaoProduto() != null ? item.getDescricaoProduto() : "---";
			String qty = QTY.format(item.getQtdeComandaDetalhe());
			
			String vlrU = new DecimalFormat("#,##0.00").format(item.getVlrUnitarioComandaDetalhe());
			String vlrT = BRL.format(item.getVlrTotFinalComandaDetalhe());

			String strLinha1 = "  " + padRight(cod, 10) + padRight(nome, 38) + padLeft(qty + " X  " + vlrU, 20);

			p(sb, strLinha1, "Normal");
			p(sb, "= " + vlrT, "Normal");

			totalGeral += item.getVlrTotFinalComandaDetalhe();
		}

		p(sb, "---------------------------------------------------------------------------", "Normal");

		String func = primeiro.getNomeFuncionario() != null ? primeiro.getNomeFuncionario() : "";
		p(sb, padRight("  Funcionário .: " + func, 49) + "CONSUMIDOR", "Normal");
		p(sb, "  Total.: COMANDA EM ABERTO. " + BRL.format(totalGeral), "Normal");
		p(sb, "  Obs .:", "Normal");

		String cidade = emitente.getCidade() != null ? emitente.getCidade().toUpperCase() : "";
		@SuppressWarnings("deprecation")
		String dataExt = primeiro.getDataComanda() != null
				? new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new Locale("pt", "BR")).format(
						primeiro.getDataComanda())
				: "";
		p(sb, "  " + cidade + ", " + dataExt, "Normal");
	}

	/**
	 * Pad right.
	 *
	 * @param s the s
	 * @param n the n
	 * @return the string
	 */
	private String padRight(String s, int n) {
		if (s == null)
			s = "";
		if (s.length() > n)
			return s.substring(0, n);
		StringBuilder sb = new StringBuilder(s);
		while (sb.length() < n)
			sb.append(" ");
		return sb.toString();
	}

	/**
	 * Pad left.
	 *
	 * @param s the s
	 * @param n the n
	 * @return the string
	 */
	private String padLeft(String s, int n) {
		if (s == null)
			s = "";
		if (s.length() > n)
			return s.substring(0, n);
		StringBuilder sb = new StringBuilder();
		while (sb.length() < n - s.length())
			sb.append(" ");
		sb.append(s);
		return sb.toString();
	}

	/**
	 * Manifest.
	 *
	 * @return the string
	 */
	private String manifest() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<manifest:manifest xmlns:manifest=\"urn:oasis:names:tc:opendocument:xmlns:manifest:1.0\">"
				+ "<manifest:file-entry manifest:full-path=\"/\" manifest:media-type=\"application/vnd.oasis.opendocument.text\"/>"
				+ "<manifest:file-entry manifest:full-path=\"content.xml\" manifest:media-type=\"text/xml\"/>"
				+ "<manifest:file-entry manifest:full-path=\"styles.xml\" manifest:media-type=\"text/xml\"/>"
				+ "</manifest:manifest>";
	}

	/**
	 * Styles.
	 *
	 * @return the string
	 */
	private String styles() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<office:document-styles"
				+ "  xmlns:office=\"urn:oasis:names:tc:opendocument:xmlns:office:1.0\""
				+ "  xmlns:style=\"urn:oasis:names:tc:opendocument:xmlns:style:1.0\""
				+ "  xmlns:fo=\"urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0\">" + "<office:styles>"
				+ "<style:default-style style:family=\"paragraph\">"
				+ "<style:text-properties fo:font-family=\"Courier New\" fo:font-size=\"10pt\"/>"
				+ "</style:default-style>" + "</office:styles>" + "</office:document-styles>";
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
	private void estilo(StringBuilder sb, String nome, String family, String font, String size, String weight,
			String align) {
		sb.append("<style:style style:name=\"").append(nome).append("\" style:family=\"").append(family).append("\">");
		sb.append("<style:paragraph-properties fo:text-align=\"").append(align).append("\"/>");
		sb.append("<style:text-properties fo:font-family=\"").append(font).append("\"").append(" fo:font-size=\"")
				.append(size).append("\"").append(" fo:font-weight=\"").append(weight).append("\"/>");
		sb.append("</style:style>");
	}

	/**
	 * P.
	 *
	 * @param sb the sb
	 * @param texto the texto
	 * @param style the style
	 */
	private void p(StringBuilder sb, String texto, String style) {
		sb.append("<text:p text:style-name=\"").append(style).append("\">");
		String escaped = escapeXml(texto);
		StringBuilder lineContent = new StringBuilder();
		int spaces = 0;
		for (char c : escaped.toCharArray()) {
			if (c == ' ') {
				spaces++;
			} else {
				if (spaces == 1) {
					lineContent.append(" ");
				} else if (spaces > 1) {
					lineContent.append("<text:s text:c=\"").append(spaces).append("\"/>");
				}
				spaces = 0;
				lineContent.append(c);
			}
		}
		if (spaces == 1) {
			lineContent.append(" ");
		} else if (spaces > 1) {
			lineContent.append("<text:s text:c=\"").append(spaces).append("\"/>");
		}
		sb.append(lineContent.toString());
		sb.append("</text:p>");
	}

	/**
	 * Escape xml.
	 *
	 * @param s the s
	 * @return the string
	 */
	private static String escapeXml(String s) {
		if (s == null)
			return "";
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'",
				"&apos;");
	}
}