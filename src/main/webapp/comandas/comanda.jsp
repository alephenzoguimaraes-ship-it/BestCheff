<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.beans.ProdutoBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.DecimalFormatSymbols"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<ProdutoBeans> listaProd = (ArrayList<ProdutoBeans>) session.getAttribute("mostrar-produtos");
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="../Image/BestChefflogo.png">
    <link rel="stylesheet" href="../CSS/styleComanda.css">
    <title>BestCheff-Comandas</title>
</head>
<body>
<!-- Modal de Aviso -->
<div id="modal-aviso" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%;
     background:rgba(0,0,0,0.65); z-index:9999; justify-content:center; align-items:center;
     backdrop-filter:blur(4px);">
    <div style="background:#1e1e3a; border:1px solid #4f46e5; border-radius:14px;
                width:360px; overflow:hidden; box-shadow:0 24px 60px rgba(0,0,0,0.6);
                animation:popIn .2s ease;">
        <!-- Header -->
        <div style="background:linear-gradient(135deg,#6d28d9,#4f46e5); padding:16px 20px;
                    display:flex; align-items:center; gap:10px;">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="white" stroke-width="2.5">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M12 9v4m0 4h.01M10.29 3.86L1.82 18a2 2 0 001.71 3h16.94a2 2 0 001.71-3L13.71 3.86a2 2 0 00-3.42 0z"/>
            </svg>
            <span style="color:white; font-weight:600; font-size:14px; letter-spacing:.3px">Atenção</span>
        </div>
        <!-- Body -->
        <div style="padding:28px 24px; text-align:center;">
            <p id="modal-msg" style="color:#e2e8f0; font-size:14px; line-height:1.7; margin:0 0 22px 0;"></p>
            <button onclick="fecharModal()" style="background:linear-gradient(135deg,#6d28d9,#4f46e5);
                    color:white; border:none; border-radius:8px; padding:10px 36px;
                    font-size:14px; font-weight:600; cursor:pointer; letter-spacing:.3px;">OK</button>
        </div>
    </div>
</div>
<div class="tela">

    <!-- HEADER -->
    <div class="header">
        <div class="header-icon">
            <svg viewBox="0 0 24 24" stroke-width="2">
                <path stroke-linecap="round" stroke-linejoin="round"
                      d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2"/>
            </svg>
        </div>
        <div>
            <h1>Comandas</h1>
            <p>Gerenciamento de pedidos</p>
        </div>
    </div>

    <!-- PAINÉIS -->
    <div class="paineis">

        <!-- ── PAINEL ESQUERDO ── -->
        <div class="painel-esq">

            <!-- Busca por código da comanda -->
            <div>
                <p class="secao-titulo">Buscar Comanda</p>
                <div class="busca-grid">
                <form id="formBuscaComandaStatus" name="form-busca-comanda" action="buscar-status-comanda" method="post">
                    <div class="busca-row">
                        <span class="label-inline">Cód.</span>
                        <input class="inp inp-sm" type="text" name="codComanda" placeholder="0000">
                        <span class="label-inline">Nome</span>
                        <input class="inp" type="text" name="nomeComanda" placeholder="Barra da comanda...">
                        <button type="submit" class="btn-ok">OK</button>
                    </div>
                </form>
                <form id="formBuscaProd" name="form-busca-prod" action="buscar-prod" method="post">
                    <div class="busca-row">
                        <span class="label-inline">Produto</span>
                        <input id="pesq-nome-produto" class="inp" type="text" name="nomeProduto" placeholder="Pesquise pelo nome do produto...">
                        <button type="submit" class="btn btn-primary">OK</button>
                    </div>
                </form>
                </div>
            </div>

            <!-- Tabela de itens -->
            <div class="tabela-wrap">
                <table>
                    <thead>
                        <tr>
                            <th>Cód.</th>
                            <th>Nome</th>
                            <th>Preço</th>
                            <th style="text-align:right">Qtde</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%for(ProdutoBeans p : listaProd) {%>
                        <tr class="sel">
                            <td class="cod"><%= p.getCodProduto() %></td>
                            <td class="nome"><%= p.getDescricaoProduto() %></td>
                            <% 
							    DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
							    simbolos.setGroupingSeparator('.');
							    simbolos.setDecimalSeparator(',');
							    DecimalFormat df = new DecimalFormat("#,##0.00", simbolos);
							%>							
							<td class="preco"><%= df.format(p.getPrecoVendaProduto()) %></td>
                            <td class="qtde"><%= p.getQtde() %></td>
                        </tr>
                    <%}%>
                        <tr><td colspan="3" class="vazio">— Adicione itens à comanda —</td></tr>
                    </tbody>
                </table>
            </div>

        </div>
        <!-- ── PAINEL DIREITO ── -->
        <div class="painel-dir">
		<form id="form-insert-comanda" name="insert-comanda-form" action="insert-comanda" method="post">
            <p class="secao-titulo">Item da Comanda</p>

            <!-- Código + Nome -->
            <div class="campo-duplo">
                <div class="campo">
                    <label>Cód.</label>
                    <input class="inp" type="text" name="cod" placeholder="000">
                </div>
                <div class="campo">
                    <label>Nome</label>
                    <input class="inp" type="text" name="nome" placeholder="Nome do produto">
                </div>
            </div>

            <!-- Qtde -->
            <div class="campo">
                <label>Qtde.</label>
                <input class="inp" id="Quantidade" type="text" name="qtde" placeholder="1" min="1">
            </div>

            <!-- Valor unit -->
            <div class="campo">
                <label>Vlr. Unit.</label>
                <input class="inp" type="text" name="vlr" placeholder="0,00">
            </div>

            <hr class="divider">

            <!-- Total -->
            <div class="campo">
                <label>Total</label>
                <input class="inp inp-total" type="text" name="total" placeholder="0,00" readonly>
            </div>

            <!-- Botões de ação -->
            <div class="btns-acao">
	                <button id="btn-enviar" class="btn btn-success" type="submit" style="justify-content:center">
	                    <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
	                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 4v16m8-8H4"/>
	                    </svg>
	                    Enviar
	                </button>
                <form id="formAbrirComanda" name="form-abrir-comanda" action="abrir-comanda" method="post" style="width:100%">
	                <button class="btn btn-primary" style="justify-content:center; width:100%" type="submit">
	                    <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
	                        <path stroke-linecap="round" stroke-linejoin="round" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2"/>
	                    </svg>
	                    Abrir Comanda
	                </button>
                </form>                
            </div>
		</form>
            <!-- Status comanda -->
            <div class="status-comanda">
                <div class="status-item">
                    <span id="ball-open" class="status-dot dot-aberta"></span>
                    <span>Comanda Aberta</span>
                </div>
                <div class="status-item">
                    <span id="ball-close" class="status-dot dot-fechada"></span>
                    <span>Comanda Fechada</span>
                </div>
            </div>

        </div>
        <!-- /painel dir -->

    </div><!-- /paineis -->

    <!-- FOOTER -->
    <div class="footer">
        <span>Comanda Digital</span>
        <em id="info-funcionario"></em>
    </div>
	
	<script>
	<% String verify = (String) request.getAttribute("status-comanda"); %>
	    let status = "<%= verify %>";
	
	    function mostrarModal(msg) {
	        document.getElementById('modal-msg').innerHTML = msg;
	        document.getElementById('modal-aviso').style.display = 'flex';
	    }
	    function fecharModal() {
	        document.getElementById('modal-aviso').style.display = 'none';
	    }
	
	    // Fecha clicando fora do card
	    document.getElementById('modal-aviso').addEventListener('click', function(e) {
	        if (e.target === this) fecharModal();
	    });
	
	    // Clique nas linhas da tabela — só se Aberto
	    if (status === "Aberto") {
	        document.querySelectorAll('tbody tr').forEach(function(tr) {
	            tr.addEventListener('click', function() {
	                let cod   = tr.querySelector('td.cod')   ? tr.querySelector('td.cod').innerText   : '';
	                let nome  = tr.querySelector('td.nome')  ? tr.querySelector('td.nome').innerText  : '';
	                let preco = tr.querySelector('td.preco') ? tr.querySelector('td.preco').innerText : '';
	                document.querySelector('[name="cod"]').value  = cod;
	                document.querySelector('[name="nome"]').value = nome;
	                document.querySelector('[name="vlr"]').value  = preco;
	                document.querySelectorAll('tbody tr').forEach(r => r.classList.remove('sel'));
	                tr.classList.add('sel');
	                document.getElementById('Quantidade').focus();
	            });
	        });
	    }
	
	    // Botão Enviar — valida antes de qualquer coisa
	    document.getElementById('btn-enviar').addEventListener('click', function() {
	        if (status === "Fechado") {
	            mostrarModal("Não é possível inserir o produto.<br><strong>A comanda está fechada!</strong>");
	        } else if (status === "null" || status === "") {
	            mostrarModal("Nenhuma comanda selecionada.<br><strong>Busque uma comanda primeiro!</strong>");
	        } else {
	            // Aqui vai a lógica de envio do produto
	        }
	    });
	</script>
	
	<script>
	    function calcularTotal() {
	        var vlrRaw  = document.querySelector('[name="vlr"]').value.replace('.', '').replace(',', '.');
	        var qtdeRaw = document.querySelector('[name="qtde"]').value.replace(',', '.');
	
	        var vlr  = parseFloat(vlrRaw)  || 0;
	        var qtde = parseFloat(qtdeRaw) || 0;
	
	        var total = vlr * qtde;
	
	        // formata de volta pra moeda brasileira
	        document.querySelector('[name="total"]').value = total.toLocaleString('pt-BR', {
	            minimumFractionDigits: 2,
	            maximumFractionDigits: 2
	        });
	    }
	
	    document.querySelector('[name="qtde"]').addEventListener('blur',  calcularTotal);
	    document.querySelector('[name="vlr"]').addEventListener('blur',   calcularTotal);
	    document.querySelector('[name="qtde"]').addEventListener('input', calcularTotal);
	    document.querySelector('[name="vlr"]').addEventListener('input',  calcularTotal);
	</script>
	
	<%
		String cod = String.valueOf(session.getAttribute("INFO-FUNCIONARIO-COD"));
		String nome = (String) session.getAttribute("INFO-FUNCIONARIO-NAME");
		if(cod != null && nome != null) {
	%>
		<script>
			document.getElementById('info-funcionario').innerHTML = "Powered by BestCheff ® | Cod: "+"<%= cod %>"+" Nome: "+"<%= nome %>";
		</script>
	<%
		}
	%>
	
	<%
	    String statusComanda = (String) request.getAttribute("status-comanda");
	    if(statusComanda != null) {
	%>
	    <script>
		    let statusVindoDoJava = "<%= statusComanda %>";
		    if (statusVindoDoJava !== "null" && statusVindoDoJava !== "") {
		        localStorage.setItem("statusComandaSalvo", statusVindoDoJava);
		    }
		    let statusValor = localStorage.getItem("statusComandaSalvo");
		    let alertOpen = document.getElementById('ball-open');
		    let alertClose = document.getElementById('ball-close');
		    if(statusValor === "Aberto") {
		        alertOpen.style.background = "#34d399";
		        alertOpen.style.boxShadow = "0 0 8px 2px #34d399";
		        let campoPesquisa = document.getElementById("pesq-nome-produto");
		        if(campoPesquisa) campoPesquisa.focus();
		    } else if(statusValor === "Fechado") {
		        alertClose.style.background = "#f87171";
		        alertClose.style.boxShadow = "0 0 8px 2px #f87171";
		    }
	    </script>
	<%
	    }
	%>
	
	<%
		String focusPesquisa = (String) request.getAttribute("focar-pesq-produto");
		if(focusPesquisa != null) {
	%>
		<script>
			document.getElementById("pesq-nome-produto").focus();
		</script>
	<%
		}
	%>
	<script>
	    document.addEventListener('keydown', function(e) {
	        if (e.key !== 'Enter') return;
	
	        var focusable = Array.from(document.querySelectorAll(
	            'input, select, textarea, button, a[href]'
	        )).filter(function(el) {
	            return !el.disabled && el.offsetParent !== null;
	        });
	
	        var atual = document.activeElement;
	        var index = focusable.indexOf(atual);
	
	        if (atual.tagName === 'BUTTON') {
	            atual.click();
	            return;
	        }
	
	        e.preventDefault();
	
	        var proximo = focusable[index + 1];
	        if (proximo) {
	            proximo.focus();
	        }
	    });
	</script>
	<script>
		<% String msgGravou = (String) session.getAttribute("gravou"); 
			if(msgGravou != null) {
		%>
			mostrarModal("<%= msgGravou %>");
		<%
			}
		%>
	</script>
</div>

</body>
</html>