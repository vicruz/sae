$(document).ready(function() {
	var elPath = $("#elPath").val();
    	    $('#pagogrado').DataTable( {
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
    	        	"url": elPath + "/pagoGradoRest",
    	        	"type": "POST"
    	        },
    	        "columns": [
    	                    { "data": "idPagoGrado" },
    	                    { "data": "concepto" },
    	                    { "data": "monto" },
    	                    { "data": "grado" },
    	                    { "data": "mes" },
    	                    { "data": "anio" },
    	                    { "data": "fechaLimite" }
    	                ]
    	    } );
    	} );
