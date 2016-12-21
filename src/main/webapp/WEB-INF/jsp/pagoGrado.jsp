<%@include file="includes/header.jsp"%>

<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
	<div class="page-header pull-left">
		<div class="page-title">Relaci&oacute;n pagos con grado</div>
	</div>
	<div class="clearfix"></div>
</div>

<input type="hidden" name="elPath" id="elPath" value="${conPath}">

<div class="page-content">
		
	<div id="addButton">
		<div class="panel-body">
			<button type="button" id="addAlumno" class="btn btn-primary pull-right" data-toggle="modal" data-target="#myModal" >
			<span class="glyphicon glyphicon-plus"></span> Agregar Pago</button>		
		</div>
	</div>
		


<!-- Modal --> 
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
     <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Agregar Relacion</h4>
      </div>
      <div class="modal-body">
      	
        <form:form modelAttribute="pagoGradoRelForm" role="form" class="form-inline">
		
			<form:errors/>
			
			
				<spring:bind path="idPago">
					<div class="form-group">
						<form:label path="idPago">Concepto: </form:label>
						<form:select id="selectConcepto" path="idPago" class="form-control" onchange="cambiaMonto(value)">
							<form:option value="0" label="--- Selecciona ---" />
							<form:options items="${conceptos}" />
						</form:select>
			         </div>		
				</spring:bind>

				<div class="form-group">
					<p style='text-indent: 1em'>
						<form:label path="monto">Monto: </form:label>
						<form:input path="monto" type="monto" class="form-control" readonly="true"/>
					</p>
				</div>
				<br>
				<br>
				<br>
				
				<spring:bind path="idGrado">
					<div class="form-group">
						<form:label path="idGrado">Grados: </form:label>
						<form:select id="selectGrado" path="idGrado" class="form-control">
							<form:option value="0" label="--- Selecciona ---" />
							<form:options items="${grados}" />
						</form:select>
			        </div>		
				</spring:bind>
				
				<!--  <div class="form-group">
					<p style='text-indent: 1em'>
						<form:label path="mes">Mes: </form:label>
						<form:input path="mes" type="mes" class="form-control" readonly="true"/>
					</p>
			    </div>-->	
				<spring:bind path="mes">
					<div class="form-group">
					<p style='text-indent: 1em'>
						<form:label path="mes">Mes: </form:label>
						<form:select id="selectMes" path="mes" class="form-control" onchange="cambiaAnio()">
							<form:option value="0" label="--- Selecciona ---" />
							<form:options items="${meses}" />
						</form:select>
						</p>
			        </div>		
				</spring:bind>
				
				<br>
				<br>
				<br>
				
				<div class="form-group">
					<form:label path="anio">Año: </form:label>
					<form:select id="selectAnio" path="anio" class="form-control">
						<form:option value="2016" label="2016" />
						<form:option value="2017" label="2017" />
						<form:option value="2018" label="2018" />
						<form:option value="2019" label="2019" />
						<form:option value="2020" label="2020" />
						<form:option value="2021" label="2021" />
					</form:select>
			    </div>
				
				
					
				<div class="form-group">
					<form:label path="fechaLimite">fecha Limite: </form:label>
					<form:input id="datepicker" path="fechaLimite" type="fechaLimite" class="form-control" placeholder="AAAA-MM-DD"/>
					<span class="glyphicon glyphicon-calendar" id="calendario"></span>
			    </div>
			
				<br>
				<br>
				<br>
				
				<div class="form-group">
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

<table id="pagogrado" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>#</th>
                  <th>Concepto</th>
                  <th>Monto</th>
  				  <th>Grado</th>
  				  <th>Mes</th>
  				  <th>A&ntilde;o</th>
  				  <th>Fecha L&iacute;mite</th>
                </tr>
                
                </thead>
               
                <tfoot>
                <tr>
                  <th>#</th>
                  <th>Concepto</th>
                  <th>Monto</th>
  				  <th>Grado</th>
  				  <th>Mes</th>
  				  <th>A&ntilde;o</th>
  				  <th>Fecha L&iacute;mite</th>
                </tr>
                </tfoot>
              </table>
		
	</div>
	
	

<%@include file="includes/footer.jsp"%>
