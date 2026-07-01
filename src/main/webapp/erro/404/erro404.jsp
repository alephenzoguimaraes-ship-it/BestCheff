<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/styleErro404.css">
    <link rel="icon" href="<%= request.getContextPath() %>/Image/BestChefflogo.png">
    <title>404 - Página não encontrada</title>
</head>
<body>

    <div class="error-container">
        <div class="glow glow-purple"></div>
        <div class="glow glow-green"></div>

        <div class="error-card">
            <div class="icon-wrapper">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                    <circle cx="11" cy="11" r="8"></circle>
                    <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
                    <line x1="8" y1="11" x2="14" y2="11"></line>
                </svg>
            </div>

            <div class="error-code">404</div>
            <h1>Página não encontrada</h1>
            <p class="subtitle">A página que você está procurando não existe, foi removida ou o endereço está incorreto.</p>

            <a href="<%= request.getContextPath() %>/index.jsp" class="btn-voltar">Voltar para o login</a>

            <p class="suporte">Problemas? Chame o suporte -&gt; 77991386974</p>
        </div>
    </div>

</body>
</html>
