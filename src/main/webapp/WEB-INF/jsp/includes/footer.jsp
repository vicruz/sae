<!--BEGIN FOOTER-->
                <div id="footer">
                    <div class="copyright">
                        <!-- <a href="http://themifycloud.com">2014 © KAdmin Responsive Multi-Purpose Template</a></div> -->
                </div>
                <!--END FOOTER-->
            </div>
            <!--END PAGE WRAPPER-->
        </div>
    </div>
    <!-- jQuery 2.2.3 
    <script src="public/lib/jquery-3.1.1.min.js"></script>-->
    <!-- <script src="public/lib/kadmin/script/jquery-1.10.2.min.js"></script> -->
    <script src="/public/lib/kadmin/script/jquery-3.1.1.min.js"></script>
    <script src="/public/lib/kadmin/script/jquery-migrate-1.2.1.min.js"></script>
    <script src="/public/lib/kadmin/script/jquery-ui.js"></script>
    <script src="/public/lib/kadmin/script/bootstrap.min.js"></script> 
    <!-- Bootstrap 3.3.6
    <script src="public/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> -->
    
    <script src="/public/lib/kadmin/script/bootstrap-hover-dropdown.js"></script>
    <script src="/public/lib/kadmin/script/html5shiv.js"></script>
    <script src="/public/lib/kadmin/script/respond.min.js"></script>
    <script src="/public/lib/kadmin/script/jquery.metisMenu.js"></script>
    <script src="/public/lib/kadmin/script/jquery.slimscroll.js"></script>
    <script src="/public/lib/kadmin/script/jquery.cookie.js"></script>
    <script src="/public/lib/kadmin/script/icheck.min.js"></script>
    <script src="/public/lib/kadmin/script/custom.min.js"></script>
    <script src="/public/lib/kadmin/script/jquery.news-ticker.js"></script>
    <script src="/public/lib/kadmin/script/jquery.menu.js"></script>
    <script src="/public/lib/kadmin/script/pace.min.js"></script>
    <script src="/public/lib/kadmin/script/holder.js"></script>
    <script src="/public/lib/kadmin/script/responsive-tabs.js"></script>
    <script src="/public/lib/kadmin/script/jquery.flot.js"></script> 
    <script src="/public/lib/kadmin/script/jquery.flot.categories.js"></script>
    <script src="/public/lib/kadmin/script/jquery.flot.pie.js"></script>
    <script src="/public/lib/kadmin/script/jquery.flot.tooltip.js"></script>
    <script src="/public/lib/kadmin/script/jquery.flot.resize.js"></script>
    <script src="/public/lib/kadmin/script/jquery.flot.fillbetween.js"></script>
    <script src="/public/lib/kadmin/script/jquery.flot.stack.js"></script>
    <script src="/public/lib/kadmin/script/jquery.flot.spline.js"></script>
    <script src="/public/lib/kadmin/script/zabuto_calendar.min.js"></script>
    <script src="/public/lib/kadmin/script/index.js"></script>
    <!--LOADING SCRIPTS FOR CHARTS-->
    <!-- <script src="public/lib/kadmin/script/highcharts.js"></script> 
    <script src="public/lib/kadmin/script/data.js"></script>
    <script src="public/lib/kadmin/script/drilldown.js"></script>
    <script src="public/lib/kadmin/script/exporting.js"></script>
    <script src="public/lib/kadmin/script/highcharts-more.js"></script>
    <script src="public/lib/kadmin/script/charts-highchart-pie.js"></script>
    <script src="public/lib/kadmin/script/charts-highchart-more.js"></script> -->
    <!-- DATA TABLES
    <script type="text/javascript" charset="utf8" src="public/lib/datatable/js/jquery.dataTables.js"></script> -->    
    <script src="/public/lib/datatable/js/jquery.dataTables.min.js"></script>
    <script src="/public/lib/datatable/js/dataTables.bootstrap.min.js"></script>

	<!-- DATA TABLES Botones para exportar -->
	<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" src="//cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"></script>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
	<script type="text/javascript" src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
	<script type="text/javascript" src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
	<script type="text/javascript" src="//cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
	<script type="text/javascript" src="//cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>









	
    <!--CORE JAVASCRIPT-->
    <script src="/public/lib/kadmin/script/main.js"></script>
    <script src="/public/lib/sae/js/alumnoTable.js"></script>
    <script src="/public/lib/sae/js/alumnoPagosTable.js"></script>
    <script type="text/javascript" src="/public/lib/sae/js/alumnoPagosTableJS.js"></script>
    
    
    <script>      
    
    (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function () {
                (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date();
            a = s.createElement(o),
            m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
        })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
        ga('create', 'UA-145464-12', 'auto');
        ga('send', 'pageview');

        /*$(document).ready(function() {
            $('#example').DataTable({
            	"paging": true,
                "lengthChange": false,
                "searching": false,
                "ordering": true,
                "info": true,
                "autoWidth": false
            });
        } );
        */
        
/*
        $(document).ready( function () {
    	    $('#alumnos').DataTable();
    	} );*/

/*    	$(document).ready(function() {
    	    $('#alumnos').DataTable( {
    	        "processing": true,
    	        //"serverSide": true,
    	        "ajax": {
    	        	"url": "../alumnoRest",
    	        	"type": "POST"
    	        },
    	        "columns": [
    	                    { "data": "id" },
    	                    { "data": "apPaterno" },
    	                    { "data": "apMaterno" },
    	                    { "data": "nombre" },
    	                    { "data": "grado" },
    	                    { "data": "semaforo" }
    	                ]
    	    } );
    	} );*/
        
        

</script>
</body>
</html>