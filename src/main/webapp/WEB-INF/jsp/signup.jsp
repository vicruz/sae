<%@include file="includes/header.jsp" %>
<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Please sign up</h3>
	</div>
	
	<div class="panel-body">
	
		<form:form modelAttribute="signupForm" role="form">
		
			<form:errors/>
		
			<div class="form-group">
				<form:label path="email">Email: </form:label>
				<form:input path="email" type="email" class="form-control" placeholder="Ingresar Email"/>
				<form:errors cssClass="error" path="email"></form:errors>
				<p class="help-block">Ingresa un email, este sera tu usuario</p>
			</div>
		
			<div class="form-group">
				<form:label path="name">Nombre: </form:label>
				<form:input path="name" type="name" class="form-control" placeholder="Ingresar un nombre"/>
				<form:errors cssClass="error" path="name"></form:errors>
				<p class="help-block">Display name</p>
			</div>
			
			<div class="form-group">
				<form:label path="password">Password: </form:label>
				<form:input path="password" type="password" class="form-control" placeholder="Password"/>
				<form:errors cssClass="error" path="password"></form:errors>
			</div>
		
			<button type="submit" class="btn btn-default">Submit</button>
		
		</form:form>
	
	</div>

</div>
<%@include file="includes/footer.jsp" %>