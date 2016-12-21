<%@include file="includes/header.jsp"%>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
	<div class="page-header pull-left">
		<div class="page-title">Estad&iacute;sticas</div>
	</div>
	<div class="clearfix"></div>
</div>


<input type="hidden" name="elPath" id="elPath" value="${conPath}">

<div class="page-content">
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Periodo</h3>
		</div>
		
		<div class="panel-body">
			<div class="input-daterange input-group" id="datepicker">
    			<input type="text" id="datepickerInicio" class="form-control" name="start" placeholder="AAAA-MM-DD" readonly/>
    			<span class="input-group-addon">A</span>
    			<input type="text" id="datepickerFin" class="form-control" name="end" placeholder="AAAA-MM-DD" readonly/>
			</div>
			<button type="button" onclick="actualizaCharts()" class="btn btn-info pull-right">Actualizar</button>
		</div>
		
	</div>

	<div class="row mbl">
		<div class="col-lg-12">

			<div class="col-lg-6" >
			
				<div class="portlet box">
					<div class="portlet-header">
						<div class="caption"><label id="lbTotalMount">Monto total: $0.00</label></div>
						<div class="tools">
							<i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
						</div>
					</div>
					<div class="portlet-body">
						<div id="totalchart" style="height: 350px;"></div>
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="portlet box">
					<div class="portlet-header">
						<div class="caption"><label id="lbGruposMount">Pagos total: $0.00</label></div>
						<div class="tools">
							<i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
						</div>
					</div>
					<div class="portlet-body">
						<div id="gruposchart" style="height: 350px;"></div>
					</div>
				</div>
			</div>

		</div>
	</div>

</div>




<!-- <spring:message code="hello"/> -->
<%@include file="includes/footer.jsp"%>