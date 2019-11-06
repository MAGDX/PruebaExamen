<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>

<h1>Listado Persona</h1>adsafasfsf
<hr>

<c:if test="${mensaje != null}">
	<div class="alert alert-info" role="alert">${mensaje }</div>
</c:if>

<ul class="list-group">
	<c:forEach items="${listaPersonas}" var="p">
		<li class="list-group-item">
			${p.nombre}
			
			<!-- Eliminar -->
			<i class="fas fa-trash-alt float-right text-danger" data-toggle="modal" data-target="#exampleModal${p.id }"></i>
			<!-- Modal -->
			<div class="modal fade" id="exampleModal${p.id }" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel${p.id }" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel${p.id }">Confirmación de eliminar</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">¿Estas seguro que quieres eliminar esta personas?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
							<form action="personas" method="post">
								<input type="hidden" name="op" value="<%=PersonaController.OP_ELIMINAR%>">
								<input type="hidden" name="id" value="${p.id }" readonly>
								<input type="submit" class="btn btn-danger" value="Eliminar">
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- End Eliminar -->
			
			<!-- Editar -->
			<a href="personas?op=<%=PersonaController.OP_DETALLE%>&id=${p.id}">
				<i class="fas fa-edit float-right mr-1 text-primary"></i>
			</a>
			<!-- End Editar -->
		</li>
	</c:forEach>
</ul>

<%@include file="includes/footer.jsp"%>