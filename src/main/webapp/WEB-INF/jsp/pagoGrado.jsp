<%@include file="includes/header.jsp"%>

<input type="hidden" name="elPath" id="elPath" value="${conPath}">

<div class="page-content">
<!-- 
<div align="right">
				<button type="button" id="addAlumno" class="btn btn-primary pull-right" data-toggle="modal" data-target="#myModal" >
					<span class="glyphicon glyphicon-plus"></span> Agregar Pago</button>
			</div>
		</div>
-->

<!-- Modal 
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
  <!--   <div class="modal-content"> -->
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Agregar Relacion</h4>
      </div>
      <div class="modal-body">
      	
        <form:form modelAttribute="pagoGradoRelForm" role="form">
		
			<form:errors/>
		
			<div class="row">
				<div class="col-md-6">
					<spring:bind path="idPago">
						<div class="form-group">
							<form:label path="idPago">Concepto: </form:label>
							<form:select id="selectConcepto" path="idPago" class="form-control" onchange="cambiaMonto(value)">
								<form:option value="0" label="--- Selecciona ---" />
								<form:options items="${conceptos}" />
							</form:select>
			            </div>		
					</spring:bind>
					
					<spring:bind path="idGrado">
						<div class="form-group">
							<form:label path="idGrado">Grados: </form:label>
							<form:select id="selectGrado" path="idGrado" class="form-control">
								<form:option value="0" label="--- Selecciona ---" />
								<form:options items="${grados}" />
							</form:select>
			            </div>		
					</spring:bind>
					
					
					<div class="form-group">
						<form:label path="monto">Monto: </form:label>
						<form:input path="monto" type="monto" class="form-control" readonly="true"/>
					</div>
				<div class="form-group">
					<form:label path="anio">Año: </form:label>
					<form:input path="anio" type="anio" class="form-control" placeholder="Año"/>
			    </div>
				
				<div class="form-group">
					<form:label path="mes">Mes: </form:label>
					<form:input path="mes" type="mes" class="form-control" placeholder="Mes"/>
			    </div>
					
					<div class="form-group">
					<form:label path="fechaLimite">fecha Limite: </form:label>
					<form:input path="fechaLimite" type="fechaLimite" class="form-control" placeholder="AAAA-MM-DD"/>
			    </div>
					
		        </div>
	        </div>
			
			<div class="form-group">
	        	<button type="submit" class="btn btn-primary pull-right">Guardar</button>
				<button type="button" class="btn btn-danger pull-right" data-dismiss="modal">Cerrar</button>
			</div>
		
		
		</form:form>
      </div>
      <div class="modal-footer">
      </div>
    <!--  </div>
  </div>
</div>-->

<%@include file="includes/footer.jsp"%>