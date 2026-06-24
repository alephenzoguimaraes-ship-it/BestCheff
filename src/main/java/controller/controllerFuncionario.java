package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.beans.ComandaBeans;
import model.beans.ComandaDetBeans;
import model.beans.FuncionarioBeans;
import model.beans.ProdutoBeans;
import model.dao.ComandaDao;
import model.dao.FuncionarioDao;
import model.dao.ProdutoDao;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class controllerFuncionario
 * @author alephe
 */
@WebServlet(name = "controllerFuncionario", urlPatterns = {"/controllerFuncionario", "/login-funcionario", "/comandas/buscar-status-comanda", "/comandas/abrir-comanda", "/comandas/buscar-prod", "/comandas/insert-comanda"})
public class controllerFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FuncionarioBeans fun = new FuncionarioBeans();
	private ComandaBeans com = new ComandaBeans();
	private ComandaDetBeans cDet = new ComandaDetBeans();
	private FuncionarioDao daoFuncionario = new FuncionarioDao();
	private ProdutoDao daoProduto = new ProdutoDao();
	private ComandaDao daoComanda = new ComandaDao();
	private String action;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public controllerFuncionario() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		action = request.getServletPath();
		System.out.println("Controller in: "+action);
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
		default:
			response.sendRedirect("index.jsp");
		}
	}

	protected void logar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fun.setLoginFuncionario(request.getParameter("usuario"));
		fun.setSenhaFuncionario(request.getParameter("senha"));
		daoFuncionario.logarFuncionario(fun);
		if(fun.getNomeFuncionario() == null || fun.getNomeFuncionario().equals("")) {
			request.getSession().setAttribute("MSG-ERRO-LOGIN", "usuário ou senha errado!");
			response.sendRedirect("index.jsp");
		} else {
			// Manda informações de quem logou
			request.getSession().setAttribute("INFO-FUNCIONARIO-NAME", fun.getNomeFuncionario());
			request.getSession().setAttribute("INFO-FUNCIONARIO-COD", fun.getCodFuncionario());
			System.out.println("Cod: "+fun.getCodFuncionario());
			System.out.println("Nome: "+fun.getNomeFuncionario());
			
			// Lista todos os produtos
			ArrayList<ProdutoBeans> produtos = new ArrayList<>();
			produtos = daoProduto.listarProdutos();
			request.getSession().setAttribute("mostrar-produtos", produtos);
			
			// manda para a tela onde o garçom irá preencher as informações
			response.sendRedirect("comandas/comanda.jsp");
		}
	}
	
	protected void buscarStatusComanda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		daoComanda.buscarComandaStatus(com, request.getParameter("codComanda").toString(), request.getParameter("nomeComanda").toString(), fun.getCodFuncionario());
		request.setAttribute("status-comanda", com.getStatusComanda().trim());
		System.out.println("Status retornado: "+com.getStatusComanda().trim());
		RequestDispatcher rd = request.getRequestDispatcher("/comandas/comanda.jsp");
		rd.forward(request, response);
	}
	
	protected void abrirComanda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(com.getStatusComanda().equals("Fechado")) {
			System.out.println("Abrindo comanda: "+com.getIdComanda());
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
	
	protected void buscarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProdutoBeans> produtos = new ArrayList<ProdutoBeans>();
		produtos = daoProduto.buscarProdutoNome(request.getParameter("nomeProduto"));
		request.getSession().setAttribute("mostrar-produtos", produtos);
		RequestDispatcher rd = request.getRequestDispatcher("/comandas/comanda.jsp");
		rd.forward(request, response);
	}
	
	protected void inserirComanda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Qtde: "+request.getParameter("qtde").replaceAll("[^0-9.,]", "").replace(".", "").replace(",", "."));
		cDet.setQtdeComandaDetalhe(Double.parseDouble(request.getParameter("qtde").replaceAll("[^0-9.,]", "").replace(".", "").replace(",", ".")));
		cDet.setVlrTotalComandaDetalhe(Double.parseDouble(request.getParameter("total").replaceAll("[^0-9.,]", "").replace(".", "").replace(",", ".")));
		cDet.setVlrUnitarioComandaDetalhe(Double.parseDouble(request.getParameter("vlr").replaceAll("[^0-9.,]", "").replace(".", "").replace(",", ".")));
		cDet.setVlrTotFinalComandaDetalhe(Double.parseDouble(request.getParameter("total").replaceAll("[^0-9.,]", "").replace(".", "").replace(",", ".")));
		daoComanda.inserirComanda(com, cDet, fun, request.getParameter("cod"));
		ArrayList<ProdutoBeans> produtos = new ArrayList<>();
		produtos = daoProduto.listarProdutos();
		request.getSession().setAttribute("mostrar-produtos", produtos);
		request.getSession().setAttribute("gravou", "Comanda gravada com sucesso");
		response.sendRedirect("./comanda.jsp");
	}
}
