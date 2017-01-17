var elPath = $("#elPath").val();
var becaDataTable;


$(document).ready(function() {
	var urlRest= elPath +"/beca/alumno/"+$("#id").val();
	
	becaDataTable = $('#becasTable').DataTable( {
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
        	"type": "GET"
        },
        "columns": [
                    { "data": "id" },
                    { "data": "porcentaje" },
                    { "data": "inicio" },
                    { "data": "fin" },
                    { "data": "urlBorrar",
                    	"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                            //$(nTd).html("<a href='"+elPath+oData.urlBorrar+"'><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a>&nbsp;&nbsp;");
                    		$(nTd).html("<a href='#'><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a>&nbsp;&nbsp;");
                        } 
                    }
                ]
    } );
	
	
	
} );


$( function() {
    $( "#datepicker" ).datepicker({format: "yyyy-mm-dd", todayHighlight: true, language: "es", autoclose: true});
  } );