<%@include file="includes/header.jsp"%>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
	<div class="page-header pull-left">
		<div class="page-title">Alumno</div>
	</div>
	<div class="clearfix"></div>
</div>

<input type="hidden" name="elPath" id="elPath" value="${conPath}">

<div class="page-content">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Alumno</h3>
		</div>
		<div class="panel-body">
			<div>
				<input type="hidden" name="id" id="id"
					value='<c:out value="${alumno.id}" />' />
				<dl class="dl-horizontal">
					<dt>Nombre:</dt>
					<dd>
						<c:out value="${alumno.nombre}" />
						&nbsp;
						<c:out value="${alumno.apPaterno}" />
						&nbsp;
						<c:out value="${alumno.apMaterno}" />
					</dd>

					<dt>Grado:</dt>
					<dd>
						<c:out value="${alumno.grado.name}" />
					</dd>
				</dl>
			</div>
			<div align="right">
				<button type="button" id="addAlumno"
					class="btn btn-primary pull-right" data-toggle="modal"
					data-target="#myModal">
					<span class="glyphicon glyphicon-plus"></span> Agregar Pago
				</button>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Agregar Pago</h4>
				</div>
				<div class="modal-body">

					<form:form modelAttribute="alumnoPagoForm" role="form">

						<form:errors />

						<div class="row">
							<div class="col-md-6">
								<spring:bind path="idPagoGrado">
									<div class="form-group">
										<form:label path="idPagoGrado">Concepto: </form:label>
										<form:select id="selectConcepto" path="idPagoGrado"
											class="form-control" onchange="cambiaMonto(value)">
											<form:option value="0" label="--- Selecciona ---" />
											<form:options items="${conceptos}" />
										</form:select>
									</div>
								</spring:bind>
								<div class="form-group">
									<form:label path="monto">Monto: </form:label>
									<form:input path="monto" type="monto" class="form-control"
										readonly="true" />
								</div>
								<div class="form-group">
									<form:label path="pago">Pago: </form:label>
									<form:input path="pago" type="pago" class="form-control" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<button type="submit" class="btn btn-primary pull-right">Guardar</button>
							<button type="button" class="btn btn-danger pull-right"
								data-dismiss="modal">Cerrar</button>
						</div>


					</form:form>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>

	<!-- TABLE -->
	<table id="alumnoPagos" class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>id</th>
				<th>idAlumno</th>
				<th>Concepto</th>
				<th>Monto a pagar</th>
				<th>Pago</th>
				<th>Fecha de pago</th>
				<th>Estatus</th>
				<th>Editar</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>id</th>
				<th>idAlumno</th>
				<th>Concepto</th>
				<th>Monto a pagar</th>
				<th>Pago</th>
				<th>Fecha de pago</th>
				<th>Estatus</th>
				<th>Editar</th>
			</tr>
		</tfoot>
	</table>
</div>




<%@include file="includes/footer.jsp"%>