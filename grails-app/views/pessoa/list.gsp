
<%@ page import="bolao.Pessoa" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'pessoa.label', default: 'Pessoa')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-pessoa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-pessoa" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nome" title="${message(code: 'pessoa.nome.label', default: 'Nome')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'pessoa.email.label', default: 'Email')}" />
					
						<th><g:message code="pessoa.usuario.label" default="Usuario" /></th>
					
						<g:sortableColumn property="dataCadastro" title="${message(code: 'pessoa.dataCadastro.label', default: 'Data Cadastro')}" />
					
						<g:sortableColumn property="telefone" title="${message(code: 'pessoa.telefone.label', default: 'Telefone')}" />
					
						<g:sortableColumn property="tipo" title="${message(code: 'pessoa.tipo.label', default: 'Tipo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${pessoaInstanceList}" status="i" var="pessoaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${pessoaInstance.id}">${fieldValue(bean: pessoaInstance, field: "nome")}</g:link></td>
					
						<td>${fieldValue(bean: pessoaInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: pessoaInstance, field: "usuario")}</td>
					
						<td><g:formatDate date="${pessoaInstance.dataCadastro}" /></td>
					
						<td>${fieldValue(bean: pessoaInstance, field: "telefone")}</td>
					
						<td>${fieldValue(bean: pessoaInstance, field: "tipo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pessoaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
