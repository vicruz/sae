$(document).ready(function() {
	var elPath = $("#elPath").val();
    	    $('#pagos').DataTable( {
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
    	        	"url": elPath + "/catpagos/addConcepto",
    	        	"type": "POST"
    	        },
    	        "columns": [
    	                    { "data": "id" },
    	                    { "data": "concepto" },
    	                    { "data": "monto" },
    	                    { "data": "fecha" },
    	                    { "data": "url",
    	                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
    	                            $(nTd).html("<a href='"+elPath+oData.url+"'><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span>&nbsp;Borrar</a>");
    	                        }
    	                    }
    	                ]
    	    } );
    	} );