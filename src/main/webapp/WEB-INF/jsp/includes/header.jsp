<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>.: Gandhi :.</title>
	<!-- Boostrap 
	<link href="public/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">-->
	
	<!-- Css 
	<link href="public/css/styles.css" rel="stylesheet"> -->
	<link type="text/css" rel="stylesheet" href="/public/lib/kadmin/styles/jquery-ui-1.10.4.custom.min.css">
    <!-- <link type="text/css" rel="stylesheet" href="public/lib/kadmin/styles/font-awesome.min.css">-->
    <link type="text/css" rel="stylesheet" href="/public/lib/kadmin/styles/bootstrap.min.css">
    <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">

  	 <!-- Ionicons -->
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link type="text/css" rel="stylesheet" href="/public/lib/kadmin/styles/animate.css">
    <link type="text/css" rel="stylesheet" href="/public/lib/kadmin/styles/all.css">
    <link type="text/css" rel="stylesheet" href="/public/lib/kadmin/styles/main.css">
    <link type="text/css" rel="stylesheet" href="/public/lib/kadmin/styles/style-responsive.css">
    <link type="text/css" rel="stylesheet" href="/public/lib/kadmin/styles/zabuto_calendar.min.css">
    <link type="text/css" rel="stylesheet" href="/public/lib/kadmin/styles/pace.css">
    <link type="text/css" rel="stylesheet" href="/public/lib/kadmin/styles/jquery.news-ticker.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">

	<!-- Data tables -->  
    <!-- <link rel="stylesheet" type="text/css" href="public/lib/datatable/css/jquery.dataTables.css"> -->
    <link rel="stylesheet"  href="/public/lib/datatable/css/dataTables.bootstrap.css">
    <link rel="stylesheet"  href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
    <link rel="stylesheet"  href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.dataTables.min.css">
  
  <!-- Ionicons 
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
-->
</head>
<body>
	 <div>
        <!--BEGIN THEME SETTING-->
        <div id="theme-setting">
            <div class="content-theme-setting">
                <select id="list-style" class="form-control">
                    <option value="style1">Flat Squared style</option>
                    <option value="style2">Flat Rounded style</option>
                    <option value="style3" selected="selected">Flat Border style</option>
                </select>
            </div>
        </div>
        <!--END THEME SETTING-->
        <!--BEGIN BACK TO TOP-->
        <a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
        <!--END BACK TO TOP-->
        <!--BEGIN TOPBAR-->
        <div id="header-topbar-option-demo" class="page-header-topbar">
            <nav id="topbar" role="navigation" style="margin-bottom: 0;" data-step="3" class="navbar navbar-default navbar-static-top">
            <div class="navbar-header">
                <button type="button" data-toggle="collapse" data-target=".sidebar-collapse" class="navbar-toggle"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                <a id="logo" href="index.html" class="navbar-brand"><span class="fa fa-rocket"></span><span class="logo-text">Gandhi</span><span style="display: none" class="logo-text-icon">µ</span></a></div>
            <div class="topbar-main"><a id="menu-toggle" href="#" class="hidden-xs"><i class="fa fa-bars"></i></a>
                <ul class="nav navbar navbar-top-links navbar-right mbn">
                    <li class="dropdown topbar-user"><a data-hover="dropdown" href="#" class="dropdown-toggle">&nbsp;<span class="hidden-xs"><sec:authentication property='principal.user.usuario' /></span>&nbsp;<span class="caret"></span></a>
                        <ul class="dropdown-menu dropdown-user pull-right">
	                        <li>
	      						<c:url var="logoutUrl" value="/logout" />
	      							<form:form	id="logoutForm" action="${logoutUrl}" method="post"></form:form>
	      							<a href="#" onclick="document.getElementById('logoutForm').submit()"><span class="fa fa-key"></span> Log out</a>
	      					</li>
                        </ul>
                    </li>                   
                </ul>
            </div>
        </nav>
            
        
        <!--END TOPBAR-->
        <div id="wrapper">
            <!--BEGIN SIDEBAR MENU-->
            <nav id="sidebar" role="navigation" data-step="2" data-intro=""
                data-position="right" class="navbar-default navbar-static-side">
            <div class="sidebar-collapse menu-scroll">
                <ul id="side-menu" class="nav">
                    
                    <div class="clearfix"></div>
                    <li><a href="<c:url value='/alumnos' />"><i class="fa fa-desktop fa-fw">
                        <div class="icon-bg bg-orange"></div>
                    </i><span class="menu-title">Alumnos</span></a></li>
                </ul>
            </div>
        </nav>
          
          
            <div id="page-wrapper">

<c:if test="${not empty flashMessage}">
	<div class="alert alert-${flashKind} ">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true" >&times;</button>
		${flashMessage}
		
	</div>
</c:if>

