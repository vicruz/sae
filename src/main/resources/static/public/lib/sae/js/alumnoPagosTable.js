/*$(document).ready(function() {
	
	function getAlumnoPagos(id){
		alert(id)
		$.ajax({
			type: "POST",
			url: "/pagosRest/"+id,
			dataType: 'json',
			success: drawAlumnoPagos,
			//error: AjaxFetchDataFailed
			
		});
	}
		
	function drawAlumnoPagos(result){
		if (result != "[]") {
			$('#alumnoPagos').DataTable( {
				"processing": true,
				"data": result,
				"columns": [
				            { "data": "concepto" },
				            { "data": "monto" },
				            { "data": "pago" },
				            { "data": "fecha" },
				            { "data": "estatus" }
				            ]
			} );
		}
	}
});*/

$(document).ready(function() {
	var idAlumno= $("#id").val();
	var elPath = $("#elPath").val();
	var urlRest= elPath +"/pagosRest/"+$("#id").val();
	$('#alumnoPagos').DataTable( {
		"language": {
	        	"url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
	        },
	    "dom": "Bfrtip",
	    "buttons": [
	              "excel", "pdf", "print"
	             ],
		"processing": true,
		//"serverSide": true,
		"ajax": {
			"url": urlRest,
			"type": "POST"
		},
		"columns": [
		            { "data": "concepto" },
		            { "data": "monto", render: $.fn.dataTable.render.number( ',', '.', 2, '$' ) },
		            { "data": "pago", render: $.fn.dataTable.render.number( ',', '.', 2, '$' ) },
		            { "data": "fecha" },
		            { "data": "estatus" }
		            ]
	} );
	
	function cambiaMonto($value){
		alert($value);
	}
	
} );
