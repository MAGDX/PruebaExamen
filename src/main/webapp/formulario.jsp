<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>

<h1>Formulario Detallado</h1>
<hr>

<c:if test="${mensaje != null}">
	<div class="alert alert-info" role="alert">${mensaje }</div>
</c:if>

<form action="personas" method="post">
	<input type="hidden" class="form-control" name="op"
		value="<%=PersonaController.OP_GUARDAR%>">
	<div class="form-group">
		<label for="id">ID</label> <input type="text" class="form-control"
			name="id" id="id" value="${persona.id}" readonly>
	</div>
	<div class="form-group">
		<label for="nombre">Nombre</label> <input type="text"
			class="form-control" name="nombre" id="nombre"
			value="${persona.nombre}" placeholder="Inserte nombre...">
	</div>
	<div class="form-group">
		<label for="apellido1">Primer Apellido</label> <input type="text"
			class="form-control" name="apellido1" id="apellido1"
			value="${persona.apellido1}" placeholder="Inserte primer apellido...">
	</div>
	<div class="form-group">
		<label for="apellido2">Segundo Apellido</label> <input type="text"
			class="form-control" name="apellido2" id="apellido2"
			value="${persona.apellido2}"
			placeholder="Inserte segundo apellido...">
	</div>
	<div class="form-group">
		<label for="email">Email</label> <input type="text"
			class="form-control" name="email" id="email" value="${persona.email}"
			placeholder="Inserte email...">
	</div>
	<div class="form-group">
		<label for="dni">DNI</label> <input type="text" class="form-control"
			name="dni" id="dni" value="${persona.dni}"
			placeholder="Inserte DNI...">
	</div>
	<button type="submit" class="btn btn-primary">${(persona.id > 0)?"Modificar":"Crear"}</button>
</form>

<%@include file="includes/footer.jsp"%>