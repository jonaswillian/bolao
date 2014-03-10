<%@ page import="bolao.Pessoa" %>



<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="pessoa.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${pessoaInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="pessoa.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${pessoaInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'usuario', 'error')} ">
	<label for="usuario">
		<g:message code="pessoa.usuario.label" default="Usuario" />
		
	</label>
	<g:select id="usuario" name="usuario.id" from="${restrito.Usuario.list()}" optionKey="id" value="${pessoaInstance?.usuario?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'dataCadastro', 'error')} required">
	<label for="dataCadastro">
		<g:message code="pessoa.dataCadastro.label" default="Data Cadastro" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dataCadastro" precision="day"  value="${pessoaInstance?.dataCadastro}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'telefone', 'error')} ">
	<label for="telefone">
		<g:message code="pessoa.telefone.label" default="Telefone" />
		
	</label>
	<g:textField name="telefone" value="${pessoaInstance?.telefone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'tipo', 'error')} required">
	<label for="tipo">
		<g:message code="pessoa.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tipo" type="number" value="${pessoaInstance.tipo}" required=""/>
</div>

