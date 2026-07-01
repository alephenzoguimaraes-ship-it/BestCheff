<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage=""%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="CSS/style.css">
    <link rel="icon" href="Image/BestChefflogo.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BestCheff-Login</title>
</head>
<body>
    <div class="card">
        <div class="logo">
            <div class="logo-icon">
                <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <path d="M12 2C9.243 2 7 4.243 7 7v2H6a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2v-9a2 2 0 0 0-2-2h-1V7c0-2.757-2.243-5-5-5zm0 2c1.654 0 3 1.346 3 3v2H9V7c0-1.654 1.346-3 3-3zm0 10a2 2 0 1 1 0 4 2 2 0 0 1 0-4z"/>
                </svg>
            </div>
        </div>
        <h1>Bem-vindo de volta</h1>
        <p class="subtitle">Acesse sua conta para continuar</p>
        <form name="form-log-user" id="formLogUser" action="login-funcionario" method="post">
            <div class="field">
                <label for="usuario">Usuário</label>
                <div class="input-wrapper">
                    <input
                        type="text"
                        id="usuario"
                        name="usuario"
                        placeholder="Digite seu usuário"
                        autocomplete="username"
                        required
                    >
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.8">
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z"/>
                    </svg>
                </div>
            </div>
            <div class="field">
                <label for="senha">Senha</label>
                <div class="input-wrapper">
                    <input
                        type="password"
                        id="senha"
                        name="senha"
                        placeholder="Digite sua senha"
                        autocomplete="current-password"
                        required
                    >
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.8">
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="M16.5 10.5V6.75a4.5 4.5 0 1 0-9 0v3.75m-.75 11.25h10.5a2.25 2.25 0 0 0 2.25-2.25v-6.75a2.25 2.25 0 0 0-2.25-2.25H6.75a2.25 2.25 0 0 0-2.25 2.25v6.75a2.25 2.25 0 0 0 2.25 2.25Z"/>
                    </svg>
                    <button type="button" class="toggle-senha" id="toggleSenha" onclick="toggleVerSenha()">
                        <svg id="iconeOlhoAberto" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.8">
                            <path stroke-linecap="round" stroke-linejoin="round"
                                  d="M2.036 12.322a1.012 1.012 0 0 1 0-.639C3.423 7.51 7.36 4.5 12 4.5c4.638 0 8.573 3.007 9.963 7.178.07.207.07.431 0 .639C20.577 16.49 16.64 19.5 12 19.5c-4.638 0-8.573-3.007-9.963-7.178Z"/>
                            <path stroke-linecap="round" stroke-linejoin="round"
                                  d="M15 12a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"/>
                        </svg>
                        <svg id="iconeOlhoFechado" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.8" style="display:none;">
                            <path stroke-linecap="round" stroke-linejoin="round"
                                  d="M3.98 8.223A10.477 10.477 0 0 0 1.934 12C3.226 16.338 7.244 19.5 12 19.5c.993 0 1.953-.138 2.863-.395M6.228 6.228A10.451 10.451 0 0 1 12 4.5c4.756 0 8.773 3.162 10.065 7.498a10.522 10.522 0 0 1-4.293 5.774M6.228 6.228 3 3m3.228 3.228 3.65 3.65m7.894 7.894L21 21m-3.228-3.228-3.65-3.65m0 0a3 3 0 1 0-4.243-4.243m4.242 4.242L9.88 9.88"/>
                        </svg>
                    </button>
                </div>
            </div>
            <div id="erro-log">
            	<label id="erro-log-msg"></label>
            </div>
            <button type="button" class="btn-login" onclick="validar()">Logar</button>
        </form>
        <p class="divider">Problemas? Chame o suporte -> 77991386974</p>
    </div>
	<script>
	    function toggleVerSenha() {
	        var input = document.getElementById('senha');
	        var olhoAberto = document.getElementById('iconeOlhoAberto');
	        var olhoFechado = document.getElementById('iconeOlhoFechado');
	
	        if (input.type === 'password') {
	            input.type = 'text';
	            olhoAberto.style.display = 'none';
	            olhoFechado.style.display = 'block';
	        } else {
	            input.type = 'password';
	            olhoAberto.style.display = 'block';
	            olhoFechado.style.display = 'none';
	        }
	    }
	
	    document.addEventListener('keydown', function(e) {
	        if (e.key !== 'Enter') return;
	
	        var focusable = Array.from(document.querySelectorAll(
	            'input, select, textarea, button:not(.toggle-senha), a[href]'
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
	<script src="JavaScript/validarIndex.js"></script>
	<%
		String msgErro = (String) session.getAttribute("MSG-ERRO-LOGIN");
		if(msgErro != null) {
	%>
		<script>
			document.getElementById('erro-log-msg').style.color = "#FF0055";
			document.getElementById("erro-log-msg").innerHTML = "<%= msgErro %>";
		</script>
	<%
			session.removeAttribute("MSG-ERRO-LOGIN");
		}
	%>
	<script>
		<%String accessLevels = (String) session.getAttribute("doNotHaveAccess");
		  if(accessLevels != null) {%>
			document.getElementById('erro-log-msg').style.color = "#FF0055";
			document.getElementById('erro-log-msg').innerHTML = "<%= accessLevels %>";
		<%  session.removeAttribute("doNotHaveAccess");
		  }%>
	</script>
</body>
</html>
