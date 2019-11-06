<%@page import="controller.PersonaController"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#">Examen</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item active">
				<a class="nav-link" href="personas">
					Listado<span class="sr-only">(current)</span>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="personas?op=<%=PersonaController.OP_NUEVO%>">Nueva Persona</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">Migrar</a>
			</li>
		</ul>
	</div>
</nav>
<main class="container">