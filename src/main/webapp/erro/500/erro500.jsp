<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500 - Erro interno do servidor</title>
    <link rel="icon" href="<%= request.getContextPath() %>/Image/BestChefflogo.png">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/styleErro500.css">
</head>
<body>

    <div class="error-container">
        <div class="glow glow-purple"></div>
        <div class="glow glow-green"></div>

        <div class="error-card">
            <div class="icon-wrapper">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <path d="M10.29 3.86L1.82 18a2 2 0 0 0 1.71 3h16.94a2 2 0 0 0 1.71-3L13.71 3.86a2 2 0 0 0-3.42 0z"></path>
                    <line x1="12" y1="9" x2="12" y2="13"></line>
                    <line x1="12" y1="17" x2="12.01" y2="17"></line>
                </svg>
            </div>

            <div class="error-code">500</div>
            <h1>Erro interno do servidor</h1>
            <p class="subtitle">Ocorreu um problema inesperado ao processar sua solicitação. Tente novamente em instantes.</p>

            <a href="<%= request.getContextPath() %>/index.jsp" class="btn-voltar">Voltar para o login</a>

            <p class="suporte">Problemas? Chame o suporte -&gt; 77991386974</p>
        </div>
    </div>

</body>
</html>
