/**
 * @author: Aleph Enzo Guimarães da Silva
 */

function validar() {
    let user = document.getElementById('usuario');
    let password = document.getElementById('senha');
    let msg = document.getElementById('erro-log-msg');

    if (user.value.trim() === '') {
        msg.innerHTML = "Você precisa preencher o campo Usuário!";
        user.focus();
        return false;
    }

    if (password.value.trim() === '') {
        msg.innerHTML = "Você precisa preencher o campo Senha!";
        password.focus();
        return false;
    }

    document.forms['form-log-user'].submit();
}