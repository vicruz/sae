<%@include file="includes/header.jsp" %>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
	<div class="page-header pull-left">
		<div class="page-title">Cat&aacute;logo de pagos</div>
	</div>
	<div class="clearfix"></div>
</div>

<input type="hidden" name="elPath" id="elPath" value="${conPath}">

	<div class="page-content">
	
	<div id="addButton" >
		<div class="panel-body">
			<button type="button" id="addAlumno" class="btn btn-primary pull-right" data-toggle="modal" data-target="#myPago" >
				<span class="glyphicon glyphicon-plus"></span> Agregar Pago</button>
		</div>
	</div>
	
	<!-- Modal -->
<div class="modal fade" id="myPago" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Agregar Pago</h4>
      </div>
      <div class="modal-body">
        <form:form modelAttribute="catPagosForm" role="form">
		
			<form:errors/>
		
			<div class="form-group">
				<form:label path="concepto">Concepto </form:label>
				<form:input path="concepto" type="concepto" class="form-control" placeholder="Concepto"/>
				<form:errors cssClass="error" path="concepto"></form:errors>
			</div>
			
			<div class="form-group">
				<form:label path="monto">Monto </form:label>
				<form:input path="monto" type="monto" class="form-control" placeholder="Monto"/>
				<form:errors cssClass="error" path="monto"></form:errors>
			</div>
		
			<div class="form-group">
				<!-- <button type="submit" class="btn btn-default">Submit</button> -->
	        	<button type="submit" class="btn btn-primary pull-right">Guardar</button>
				<button type="button" class="btn btn-danger pull-right" data-dismiss="modal">Cerrar</button>

			</div>
		
		
		</form:form>
      </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>


<table id="pagos" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>#</th>
                  <th>Concepto</th>
                  <th>Monto</th>
                  <th>Fecha</th>
  				  <th>Acción</th>
                </tr>
                
                
                
                </thead>
               
                <tfoot>
                <tr>
                  <th>#</th>
                  <th>Concepto</th>
                  <th>Monto</th>
                  <th>Fecha</th>
  				  <th>Acción</th>
                </tr>
                </tfoot>
              </table>
		
	</div>
	
	

	<!-- <spring:message code="hello"/> -->
<%@include file="includes/footer.jsp" %>