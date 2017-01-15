$(document).ready(function() {
	var idAlumno= $("#id").val();
	var elPath = $("#elPath").val();
	var urlRest= elPath +"/pagosRest/"+$("#id").val();
	/////
	var saldo= parseFloat($("#saldo").val());
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
        var htmlText;
        var ejecuta = 1;
        
        //Si ya esta pagado ya no muestra el pop up
        if(values.estatus.includes("Pagado")){
        	return true;
        }
        
        console.log(idx);
        montoPago = parseFloat(values.monto);
        descripcionPago = values.concepto;
        console.log("xx: "+montoPago+","+descripcionPago);
        montoAPagar = montoPago - values.pago;
        
        ///
        htmlText = '<p><b>'+descripcionPago+'</b></p>'+
		'<p>Monto total: $'+montoPago+'</br>'+
		'Monto por pagar: $'+montoAPagar+'</p>';
        
        if(saldo>0){
        	htmlText = htmlText + '<p>'+
        	'<input type="checkbox" name="check_saldo" id="check_saldo" value="1"> &nbsp;'+
        	'Aplicar saldo a favor ($' + saldo +')'+
        	'</p>';
        }
        
        htmlText = htmlText + 
		'<p>Monto a pagar: '+
		'<input autofocus type="text" onkeypress=\"return ((event.charCode >= 48 && event.charCode <= 57)||(event.charCode == 46))\" name="monto_pago" id="monto_pago" value="" placeholder="Monto a pagar" />' +
		'</p>';
        

        var updateMontoBox =  {
    			state0: {
    				title: 'Pago',
    				html: htmlText,
    				buttons: { Cancelar: 0, Pagar: 1 },
    				//focus: 0,
    				submit:function(e,v,m,f){
    					//boton cancelar
    					if(v==0)
    						$.prompt.close()
    					else{
    						//Valida que el monto de pago no sea mayor al monto a pagar
    						if(montoAPagar < f.monto_pago){
    							ejecuta = 0;
    							$.prompt("El monto de pago no puede ser mayor al monto a pagar",{
    								title: "ERROR!"
    							});
    						}
    						else{
    							//Si el checkbox de saldo esta marcado y se ha puesto un monto, 
    							//se valida que la suma de estos no sea mayor al monto por pagar
    							if((f.monto_pago!=null && f.monto_pago>0) 
    									&& f.check_saldo==1){
    								var total = parseFloat(f.monto_pago) + parseFloat(saldo);
    								if(montoAPagar < total){
    									ejecuta = 0;
    	    							$.prompt("El monto de pago no puede ser mayor al monto a pagar. \nSi el saldo cubre el monto total, no se debe poner un monto a pagar",{
    	    								title: "ERROR!"
    	    							});
    	    						}
    							}
    							
    							if(ejecuta==1){
    								//Actualiza el monto de pago
        							$.ajax({
        								type: "POST",
        								url: elPath +"/pagosRest/update/"+values.id,
        								data: {
        									pago: f.monto_pago,
        									userId: 1, //no sirve de nada
        									checked: f.check_saldo//,
        									//saldo: saldo
        								},
        								success: function( data, textStatus, jQxhr ){
        									console.log("ajax.data: "+data);
        									values.pago = data.pago;
        									values.fecha = data.fecha;
        									values.estatus = data.estatus;
        									values.editar = data.editar;
        									alumnoPagosTable.row( objTabla ).data(values).draw();
        									document.getElementById("divSaldo").textContent= data.saldo;
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
