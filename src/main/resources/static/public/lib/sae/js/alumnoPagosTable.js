$(document).ready(function() {
	var idAlumno= $("#id").val();
	var elPath = $("#elPath").val();
	var urlRest= elPath +"/pagosRest/"+$("#id").val();
	var montoPago;
	var descripcionPago;
	
	var alumnoPagosTable = $('#alumnoPagos').DataTable( {
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
		            { "data": "id", visible: false, searchable: false },
		            { "data": "idAlumno", visible: false, searchable: false },
		            { "data": "concepto" },
		            { "data": "monto", render: $.fn.dataTable.render.number( ',', '.', 2, '$' ) },
		            { "data": "pago", render: $.fn.dataTable.render.number( ',', '.', 2, '$' ) },
		            { "data": "fecha" },
		            { "data": "estatus" },
		            { "data": "editar" }
		            ]
	} );
	
	function cambiaMonto($value){
		alert($value);
	}
	
	$('#alumnoPagos tbody').on( 'click', 'button', function () {
		var objTabla = $(this).parents('tr');
        var values = alumnoPagosTable.row( objTabla ).data();
        var idx = alumnoPagosTable.row( objTabla ).index();
        
        //Si ya esta pagado ya no muestra el pop up
        if(values.estatus.includes("Pagado")){
        	return true;
        }
        
        console.log(idx);
        montoPago = values.monto;
        descripcionPago = values.concepto;
        console.log("xx: "+montoPago+","+descripcionPago);
        montoAPagar = montoPago - values.pago; 

        var updateMontoBox =  {
    			state0: {
    				title: 'Pago',
    				html:'<p><b>'+descripcionPago+'</b></p>'+
    				'<p>Monto total: $'+montoPago+'</br>'+
    				'Monto por pagar: $'+montoAPagar+'</p>'+
    				'<p>Monto a pagar: '+
    				'<input type="text" onkeypress=\"return ((event.charCode >= 48 && event.charCode <= 57)||(event.charCode == 46))\" name="monto_pago" id="monto_pago" value="" placeholder="Monto a pagar" />' +
    				'</p>',
    				buttons: { Cancelar: 0, Pagar: 1 },
    				focus: 1,
    				submit:function(e,v,m,f){
    					//boton cancelar
    					if(v==0)
    						$.prompt.close()
    					else{
    						//Valida que el monto de pago no sea mayor al monto a pagar
    						if(montoAPagar < f.monto_pago){
    							$.prompt("El monto de pago no puede ser mayor al monto a pagar",{
    								title: "ERROR!"
    							});
    						}else{
    							//Actualiza el monto de pago
    							$.ajax({
    								type: "POST",
    								url: elPath +"/pagosRest/update/"+values.id,
    								data: {
    									pago: f.monto_pago,
    									userId: 1
    								},
    								success: function( data, textStatus, jQxhr ){
    									console.log("ajax.data: "+data);
    									values.pago = data.pago;
    									values.fecha = data.fecha;
    									values.estatus = data.estatus;
    									values.editar = data.editar;
    									alumnoPagosTable.row( objTabla ).data(values).draw();
    									$.prompt("El monto de pago se ha actualizado",{
    										title: "Actualizado!"
    									});
    								}
    							});					
    						}
    						
    					} 
    				}				
    			}
    		}
        
        $.prompt(updateMontoBox,{
        	close: function(e,v,m,f){
				if(v !== undefined ){
					var str;
					$.each(f,function(i,obj){
						str = obj;
					});	
				}
			},
			classes: {
				box: '',
				fade: '',
				prompt: '',
				close: '',
				title: 'lead',
				message: '',
				buttons: '',
				button: 'btn',
				defaultButton: 'btn-primary'
			}
        }); 
    } );
} );
