package impressions;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
 
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
 
import br.com.adilson.util.PrinterMatrix;
import model.beans.Comandas.ComandaDetBeans;
import model.beans.Emitente.EmitenteBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class printComandaInvoiceOrder.
 * @author alephe
 */
public class printComandaInvoiceOrder {
	
	/** The printer matrix. */
	private PrinterMatrix printerMatrix = new PrinterMatrix();
	
	/** The line. */
	private int line = 1;
	
	/** The path. */
	private String path;
	
	/** The Constant FORMAT_DATA. */
	private static final SimpleDateFormat FORMAT_DATA = new SimpleDateFormat("dd/MM/yyyy");
	
	/** The Constant BRL. */
	@SuppressWarnings("deprecation")
	private static final NumberFormat BRL = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	
	/** The Constant QTY. */
	private static final DecimalFormat QTY = new DecimalFormat("#,##0.##");
	
	/** The Constant VLR. */
	private static final DecimalFormat VLR = new DecimalFormat("#,##0.00");

	/**
	 * Generate odt.
	 *
	 * @param idBlocoComanda the id bloco comanda
	 */
	public void generateOdt(int idBlocoComanda) {
		String OS = System.getProperty("os.name").toLowerCase();
		String nameFileOdt = "comanda" + idBlocoComanda + ".odt";
		this.path = "";

		if (OS.contains("win")) {
			path = "C:/home/";
		} else {
			path = System.getProperty("user.home") + "/";
		}

		System.out.println("Path: " + path + nameFileOdt);

		printerMatrix.setOutSize(25, 80);
		printerMatrix.printTextLinCol(line, 4, "=============DADOS DA EMPRESA==============");

		path = path + nameFileOdt;
	}

	/**
	 * Insert and print.
	 *
	 * @param comandaDetalheBeans the comanda detalhe beans
	 * @param emitente the emitente
	 */
	public void insertAndPrint(ArrayList<ComandaDetBeans> comandaDetalheBeans, EmitenteBeans emitente) {
		ComandaDetBeans comandaDetalhe = comandaDetalheBeans.get(0);

		// Data from enterprise
		printerMatrix.printTextLinCol(line, 52, "RAZAO.: " + emitente.getNome());
		printerMatrix.printTextLinCol(++line, 4, "CNPJ.: " + emitente.getCnpj());
		printerMatrix.printTextLinCol(line, 52, "IE.: " + emitente.getIe());
		printerMatrix.printTextLinCol(++line, 4, "Endereco.: " + emitente.getEndereco());
		printerMatrix.printTextLinCol(line, 52, "Numero.: " + emitente.getNumero());
		printerMatrix.printTextLinCol(++line, 4, "Bairro.: " + emitente.getBairro());
		printerMatrix.printTextLinCol(line, 52, "Telefone.: " + emitente.getTelefone());

		// Data from comand
		printerMatrix.printTextLinCol(++line, 4, "=============DADOS DA COMANDA==============");
		printerMatrix.printTextLinCol(line, 52, "COMANDA .: " + comandaDetalhe.getIdBlocoComanda());
		printerMatrix.printTextLinCol(++line, 4, "Comanda N.: " + comandaDetalhe.getIdBlocoComanda());
		String dtComanda = comandaDetalhe.getDataComanda() != null ? FORMAT_DATA.format(comandaDetalhe.getDataComanda()) : "";
		printerMatrix.printTextLinCol(line, 52, "Data Comanda.: " + dtComanda);

		// Header from itens
		printerMatrix.printTextLinCol(++line, 4, "============== ITENS ======================");
		printerMatrix.printTextLinCol(line, 52, "Codigo      Produto");
		printerMatrix.printTextLinCol(++line, 4, "Qtde  Valor");
		printerMatrix.printTextLinCol(line, 52, "Total");
		printerMatrix.printTextLinCol(++line, 4, "---------------------------------------------------------------------------");

		// Itens from comand
		double totalGeral = 0.0;
		for (ComandaDetBeans item : comandaDetalheBeans) {
			String cod = item.getIdProduto() != null ? item.getIdProduto() : "---";
			String nome = item.getDescricaoProduto() != null ? item.getDescricaoProduto() : "---";
			String qtde = QTY.format(item.getQtdeComandaDetalhe());
			String vlrUnit = VLR.format(item.getVlrUnitarioComandaDetalhe());
			String vlrTotalItem = BRL.format(item.getVlrTotFinalComandaDetalhe());

			String linhaItem = "  " + padRight(cod, 10) + padRight(nome, 38) + padLeft(qtde + " X  " + vlrUnit, 20);
			printerMatrix.printTextLinCol(++line, 4, linhaItem);
			printerMatrix.printTextLinCol(++line, 1, "= " + vlrTotalItem);

			totalGeral += item.getVlrTotFinalComandaDetalhe();
		}

		// Footer
		printerMatrix.printTextLinCol(++line, 4, "---------------------------------------------------------------------------");
		String func = comandaDetalhe.getNomeFuncionario() != null ? comandaDetalhe.getNomeFuncionario() : "";
		printerMatrix.printTextLinCol(++line, 4, "  Funcionário .: " + func);
		printerMatrix.printTextLinCol(line, 52, "CONSUMIDOR");
		printerMatrix.printTextLinCol(++line, 4, "  Total.: COMANDA EM ABERTO. " + BRL.format(totalGeral));
		printerMatrix.printTextLinCol(++line, 4, "  Obs .:");

		String cidade = emitente.getCidade() != null ? emitente.getCidade().toUpperCase() : "";
		String dataExt = "";
		if (comandaDetalhe.getDataComanda() != null) {
			@SuppressWarnings("deprecation")
			SimpleDateFormat formatoExtenso = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
			dataExt = formatoExtenso.format(comandaDetalhe.getDataComanda());
		}
		printerMatrix.printTextLinCol(++line, 4, "  " + cidade + ", " + dataExt);

		printerMatrix.toFile(path);

		print();
		
		line = 1;
	}
	
	/**
	 * Prints the.
	 */
	private void print() {
		PrintService defaultPrint = PrintServiceLookup.lookupDefaultPrintService();
 
		if (defaultPrint == null) {
			System.out.println("Nenhuma impressora padrão configurada no sistema.\n"
		    + "Configure uma impressora padrão no Windows/linux e tente novamente.");
			return;
		}
		
		try (FileInputStream content = new FileInputStream(this.path)) {
			DocFlavor flavor = DocFlavor.INPUT_STREAM.TEXT_PLAIN_HOST;
			Doc document = new SimpleDoc(content, flavor, null);
 
			PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
			attributes.add(new Copies(1));
 
			DocPrintJob job = defaultPrint.createPrintJob();
			job.print(document, attributes);
 
			System.out.println("Comanda enviada para impressão em: " + defaultPrint.getName());
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo da comanda: " + e.getMessage());
		} catch (PrintException e) {
			System.out.println("Erro ao enviar para a impressora: " + e.getMessage());
		} finally {
			deleteAfterClose();
			if(new File(this.path).exists()) {
				new File(this.path).delete();
			}
		}
	}
	
	/**
	 * Delete after close.
	 */
	private void deleteAfterClose() {
		File fileToDelete = new File(this.path);
		if (fileToDelete.exists()) {
			fileToDelete.deleteOnExit();
			System.out.println("The file was deleted!");
		}
	}
	
	/**
	 * Pad right.
	 *
	 * @param texto the texto
	 * @param tamanho the tamanho
	 * @return the string
	 */
	private String padRight(String texto, int tamanho) {
		if (texto == null) {
			texto = "";
		}
		if (texto.length() >= tamanho) {
			return texto.substring(0, tamanho);
		}
		StringBuilder sb = new StringBuilder(texto);
		while (sb.length() < tamanho) {
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * Pad left.
	 *
	 * @param texto the texto
	 * @param tamanho the tamanho
	 * @return the string
	 */
	private String padLeft(String texto, int tamanho) {
		if (texto == null) {
			texto = "";
		}
		if (texto.length() >= tamanho) {
			return texto.substring(texto.length() - tamanho);
		}
		StringBuilder sb = new StringBuilder();
		while (sb.length() < tamanho - texto.length()) {
			sb.append(" ");
		}
		sb.append(texto);
		return sb.toString();
	}
}