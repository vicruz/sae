function cambiaMonto(){
	var selectedValue= $("#selectConcepto").val();
	var elPath = $("#elPath").val();
	var url = elPath + "/catpagos/getMonto/"+selectedValue;
	$.get(url,function(respuesta){
		$("#monto").val(respuesta);
	})
}

$( function() {
    $( "#datepicker" ).datepicker({format: "yyyy-mm-dd", todayHighlight: true, autoclose: true});
  } );

function cambiaAnio(){
	var elPath = $("#elPath").val();
	var url = elPath + "/catpagos/getAnio";
	$.get(url,function(respuesta){
		$("#anio").val(respuesta);
	})
}

