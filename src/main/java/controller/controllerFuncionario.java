package controller;

import java.io.IOException;
import java.util.ArrayList;

import accessLevels.accessLevels;
import impressions.printComandaInvoiceOrder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.beans.Comandas.ComandaBeans;
import model.beans.Comandas.ComandaDetBeans;
import model.beans.Emitente.EmitenteBeans;
import model.beans.Funcionario.FuncionarioBeans;
import model.beans.Produto.ProdutoBeans;
import model.dao.Comanda.ComandaDao;
import model.dao.Emitente.EmitenteDao;
import model.dao.Funcionario.FuncionarioDao;
import model.dao.Produto.ProdutoDao;
import model.dao.accessLevelsDB.accessLevelsDao;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class controllerFuncionario.
 *
 * @author alephe
 */
@WebServlet(name = "controllerFuncionario", urlPatterns = { "/controllerFuncionario", "/login-funcionario",
		"/comandas/buscar-status-comanda", "/comandas/abrir-comanda", "/comandas/buscar-prod",
		"/comandas/insert-comanda", "/comandas/send-invoice" })
public class controllerFuncionario extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6300049712855924535L;
	
	/** The fun. */
	private FuncionarioBeans fun = new FuncionarioBeans();
	
	/** The com. */
	private ComandaBeans com = new ComandaBeans();
	
	/** The c det. */
	private ComandaDetBeans cDet = new ComandaDetBeans();
	
	/** The emitente. */
	private EmitenteBeans emitente = new EmitenteBeans();
	
	/** The dao funcionario. */
	private FuncionarioDao daoFuncionario = new FuncionarioDao();
	
	/** The dao produto. */
	private ProdutoDao daoProduto = new ProdutoDao();
	
	/** The dao comanda. */
	private ComandaDao daoComanda = new ComandaDao();
	
	/** The emitente dao. */
	private EmitenteDao emitenteDao = new EmitenteDao();
	
	/** The comand invoice order. */
	private printComandaInvoiceOrder comandInvoiceOrder = new printComandaInvoiceOrder();
	
	/** The access dao. */
	private accessLevelsDao accessDao = new accessLevelsDao();
	
	/** The action. */
	private String action;
	
	/** The have access to create. */
	private String haveAccessToCreate = null;
	
	/** The have access to read. */
	private String haveAccessToRead = null;
	
	/** The have access to delete. */
	private String haveAccessToDelete = null;

	/**
	 * Instantiates a new controller funcionario.
	 */
	public controllerFuncionario() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("ISO8859_1");
		response.setCharacterEncoding("ISO8859_1");
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("ISO8859_1");
		response.setCharacterEncoding("ISO8859_1");
		action = request.getServletPath();
		System.out.println("Controller in: " + action);
		switch (action) {
		case "/login-funcionario": {
			logar(request, response);
			break;
		}
		case "/comandas/buscar-status-comanda": {
			buscarStatusComanda(request, response);
			break;
		}
		case "/comandas/abrir-comanda": {
			abrirComanda(request, response);
			break;
		}
		case "/comandas/buscar-prod": {
			buscarProduto(request, response);
			break;
		}
		case "/comandas/insert-comanda": {
			inserirComanda(request, response);
			break;
		}
		case "/comandas/send-invoice": {
			printComandInvoiceOrder(request, response);
			break;
		}
		default:
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * Logar.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void logar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<accessLevels> levels = new ArrayList<>();
		fun.setLoginFuncionario(request.getParameter("usuario"));
		fun.setSenhaFuncionario(request.getParameter("senha"));
		daoFuncionario.logarFuncionario(fun);
		if (fun.getNomeFuncionario() == null || fun.getNomeFuncionario().equals("")) {
			request.getSession().setAttribute("MSG-ERRO-LOGIN", "usuário ou senha errado!");
			response.sendRedirect("index.jsp");
		} else {
			levels = accessDao.getTheAccessLevelsFunc(fun.getCodFuncionario());
			if(levels != null && !levels.isEmpty()) {
				for (int i = 0; i < levels.size(); i++) {					
					haveAccessToCreate = levels.get(i).getC();
					haveAccessToRead = levels.get(i).getR();
					haveAccessToDelete = levels.get(i).getD();
	
					System.out.println("Tem permissão para criar: "+haveAccessToCreate);
					System.out.println("Tem permissão para ler: "+haveAccessToRead);
					System.out.println("Tem permissão para excluir: "+haveAccessToDelete);
					
					if (haveAccessToCreate.equals("Sim.") && haveAccessToRead.equals("Sim.") && haveAccessToDelete.equals("Sim.")) {
						request.getSession().setAttribute("INFO-FUNCIONARIO-NAME", fun.getNomeFuncionario());
						request.getSession().setAttribute("INFO-FUNCIONARIO-COD", fun.getCodFuncionario());
						System.out.println("Cod: " + fun.getCodFuncionario());
						System.out.println("Nome: " + fun.getNomeFuncionario());
	
						ArrayList<ProdutoBeans> produtos = new ArrayList<>();
						produtos = daoProduto.listarProdutos();
						request.getSession().setAttribute("mostrar-produtos", produtos);
	
						response.sendRedirect("comandas/comanda.jsp");
						break;
					} else if((haveAccessToCreate == null && haveAccessToRead == null && haveAccessToDelete == null) || (haveAccessToCreate.equals("Nao.") && haveAccessToRead.equals("Nao.") && haveAccessToDelete.equals("Nao."))) {
						request.getSession().setAttribute("doNotHaveAccess", "Funcionário: "+fun.getNomeFuncionario()+" não tem acesso para esse sistema!");
						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
						break;
					}
				}
			} else {
				request.getSession().setAttribute("doNotHaveAccess", "Funcionário: "+fun.getNomeFuncionario()+" não tem acesso para esse sistema!");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
		}
		emitenteDao.getEmitente(emitente);
	}

	/**
	 * Buscar status comanda.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void buscarStatusComanda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		daoComanda.buscarComandaStatus(com, request.getParameter("codComanda").toString(),
				request.getParameter("nomeComanda").toString(), fun.getCodFuncionario());
		comandInvoiceOrder.generateOdt(com.getIdBlocoComanda());
		request.setAttribute("status-comanda", com.getStatusComanda().trim());
		System.out.println("Id comanda: " + com.getIdComanda());
		System.out.println("Status retornado: " + com.getStatusComanda().trim());
		RequestDispatcher rd = request.getRequestDispatcher("/comandas/comanda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Abrir comanda.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void abrirComanda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (com.getStatusComanda().equals("Fechado")) {
			System.out.println("Abrindo comanda: " + com.getIdComanda());
			daoComanda.abrirComanda(com);
			request.setAttribute("status-comanda", com.getStatusComanda());
			request.setAttribute("focar-pesq-produto", "pesq-nome-produto");
			RequestDispatcher rd = request.getRequestDispatcher("/comandas/comanda.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("focar-pesq-produto", "pesq-nome-produto");
			RequestDispatcher rd = request.getRequestDispatcher("/comandas/comanda.jsp");
			rd.forward(request, response);

		}
	}

	/**
	 * Buscar produto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void buscarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<ProdutoBeans> produtos = new ArrayList<ProdutoBeans>();
		produtos = daoProduto.buscarProdutoNome(request.getParameter("nomeProduto"));
		request.getSession().setAttribute("mostrar-produtos", produtos);
		RequestDispatcher rd = request.getRequestDispatcher("/comandas/comanda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Inserir comanda.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void inserirComanda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(
				"Qtde: " + request.getParameter("qtde").replaceAll("[^0-9.,]", "").replace(".", "").replace(",", "."));
		cDet.setQtdeComandaDetalhe(Double.parseDouble(
				request.getParameter("qtde").replaceAll("[^0-9.,]", "").replace(".", "").replace(",", ".")));
		cDet.setVlrTotalComandaDetalhe(Double.parseDouble(
				request.getParameter("total").replaceAll("[^0-9.,]", "").replace(".", "").replace(",", ".")));
		cDet.setVlrUnitarioComandaDetalhe(Double.parseDouble(
				request.getParameter("vlr").replaceAll("[^0-9.,]", "").replace(".", "").replace(",", ".")));
		cDet.setVlrTotFinalComandaDetalhe(Double.parseDouble(
				request.getParameter("total").replaceAll("[^0-9.,]", "").replace(".", "").replace(",", ".")));
		daoComanda.inserirComanda(com, cDet, fun, request.getParameter("cod"));
		ArrayList<ProdutoBeans> produtos = new ArrayList<>();
		produtos = daoProduto.listarProdutos();
		request.getSession().setAttribute("mostrar-produtos", produtos);
		request.getSession().setAttribute("gravou", "Comanda gravada com sucesso");
		response.sendRedirect("./comanda.jsp");
	}

	/**
	 * Prints the comand invoice order.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void printComandInvoiceOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<ComandaDetBeans> comandasDetalhes = new ArrayList<>();
		comandasDetalhes = daoComanda.buscarTodasComandasDet(com);
		comandInvoiceOrder.insertAndOpen(comandasDetalhes, emitente);
		ArrayList<ProdutoBeans> produtos = new ArrayList<>();
		produtos = daoProduto.listarProdutos();
		request.getSession().setAttribute("mostrar-produtos", produtos);
		request.getSession().setAttribute("get-invoice", "Impressão de nota fiscal enviada com sucesso!");
		response.sendRedirect("./comanda.jsp");
	}
}