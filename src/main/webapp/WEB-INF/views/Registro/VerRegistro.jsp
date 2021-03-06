<%--
  Created by IntelliJ IDEA.
  User: ICS
  Date: 17/08/2020
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />

    <spring:url value="/resources/css/jquery-ui.css" var="uiCss" />
    <link href="${uiCss}" rel="stylesheet" type="text/css"/>

    <style>

       input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff; font-family: Roboto }
       input[type="text"]{color: #000000; font-family: Roboto
       }

        #myUL li:hover:not(.header) {
            background-color: #eee; /* Add a hover effect to all links, except for headers */
        }
        .card-body { background-color: #ffffff /* rgba(21, 20, 22, 0.1)*/ !important; }
        .card {
            background-color: #ffffff;
            border: 1px solid rgba(0, 34, 51, 0.1);
            box-shadow: 2px 4px 10px 0 rgba(0, 34, 51, 0.05), 2px 4px 10px 0 rgba(0, 34, 51, 0.05);
            border-radius: 0.15rem;
        }

        /* Tabs Card */

        .tab-card {
            border:1px solid #eee;
        }

        .tab-card-header {
            background:none;
        }
        /* Default mode */
        .tab-card-header > .nav-tabs {
            border: none;
            margin: 0px;
        }
        .tab-card-header > .nav-tabs > li {
            margin-right: 2px;
        }
        .tab-card-header > .nav-tabs > li > a {
            border: 0;
            border-bottom:2px solid transparent;
            margin-right: 0;
            color: #737373;
            padding: 2px 15px;
        }

        .tab-card-header > .nav-tabs > li > a.show {
            border-bottom:2px solid #007bff;
            color: #007bff;
        }
        .tab-card-header > .nav-tabs > li > a:hover {
            color: #007bff;
        }

        .tab-card-header > .tab-content {
            padding-bottom: 0;
        }


        /*inicio mi css */
        .main-box.no-header {
            padding-top: 20px;
        }
        .main-box {
            background: #FFFFFF;
            -webkit-box-shadow: 1px 1px 2px 0 #CCCCCC;
            -moz-box-shadow: 1px 1px 2px 0 #CCCCCC;
            -o-box-shadow: 1px 1px 2px 0 #CCCCCC;
            -ms-box-shadow: 1px 1px 2px 0 #CCCCCC;
            box-shadow: 1px 1px 2px 0 #CCCCCC;
            margin-bottom: 16px;
            -webikt-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
        }
        .table a.table-link.danger {
            color: #e74c3c;
        }
        .label {
            border-radius: 3px;
            font-size: 0.875em;
            font-weight: 600;
        }
        .user-list tbody td .user-subhead {
            font-size: 0.875em;
            font-style: italic;
        }
        .user-list tbody td .user-link {
            display: block;
            font-size: 1.25em;
            padding-top: 3px;
            margin-left: 60px;
        }
        a {
            color: #3498db;
            outline: none!important;
        }
        .user-list tbody td>img {
            position: relative;
            max-width: 50px;
            float: left;
            margin-right: 15px;
        }

        .table thead tr th {
            text-transform: uppercase;
            font-size: 0.875em;
        }
        .table thead tr th {
            border-bottom: 2px solid #e7ebee;
        }
        .table tbody tr td:first-child {
            font-size: 1.125em;
            font-weight: 300;
        }
        .table tbody tr td {
            font-size: 0.875em;
            vertical-align: middle;
            border-top: 1px solid #e7ebee;
            padding: 12px 8px;
        }
        a:hover{
            text-decoration:none;
        }
        /* fin  EFFECT 1 ========================================== */
        .effect-1 {
            position: relative;
        }
        .effect-1::before, .effect-1::after {
            z-index: -1;
            position: absolute;
            content: "";
            bottom: 15px;
            left: 10px;
            width: 50%;
            top: 80%;
            max-width: 300px;
            background: #aaa;
            box-shadow: 0 15px 10px #aaa;
            transform: rotate(-3deg);
        }
        .effect-1::after {
            transform: rotate(3deg);
            right: 10px;
            left: auto;
        }

        /* search*/
       .search-result-box .tab-content {
           padding: 30px 30px 10px 30px;
           -webkit-box-shadow: none;
           box-shadow: none;
           -moz-box-shadow: none
       }

       .search-result-box .search-item {
           padding-bottom: 20px;
           border-bottom: 1px solid #e3eaef;
           margin-bottom: 20px
       }
       .text-success {
           color: #0acf97!important;
       }
       a {
           color: #007bff;
           text-decoration: none;
           background-color: transparent;
       }
       .btn-custom {
           background-color: #02c0ce;
           border-color: #02c0ce;
       }

       .btn-custom, .btn-danger, .btn-info, .btn-inverse, .btn-pink, .btn-primary, .btn-purple, .btn-success, .btn-warning {
           color: #fff!important;
       }
        /*inicio tab 1*/
       .main-body {
           padding: 15px;
       }

       .card {
           box-shadow: 0 1px 3px 0 rgba(0,0,0,.1), 0 1px 2px 0 rgba(0,0,0,.06);
       }

       .card {
           position: relative;
           display: flex;
           flex-direction: column;
           min-width: 0;
           word-wrap: break-word;
           background-color: #fff;
           background-clip: border-box;
           border: 0 solid rgba(0,0,0,.125);
           border-radius: .25rem;
       }
       .card1:hover{
           transform: scale(1.05);
           box-shadow: 0 10px 20px rgba(0,0,0,.12), 0 4px 8px rgba(0,0,0,.06);
       }

       .card-body {
           flex: 1 1 auto;
           min-height: 1px;
           padding: 1rem;
       }

       .gutters-sm {
           margin-right: -8px;
           margin-left: -8px;
       }

       .gutters-sm>.col, .gutters-sm>[class*=col-] {
           padding-right: 8px;
           padding-left: 8px;
       }
       .mb-3, .my-3 {
           margin-bottom: 1rem!important;
       }

       .bg-gray-300 {
           background-color: #e2e8f0;
       }
       .h-100 {
           height: 100%!important;
       }
       .shadow-none {
           box-shadow: none!important;
       }

        /*fin*/

       .btn-circle {
           width: 45px;
           height: 45px;
           line-height: 45px;
           text-align: center;
           padding: 0;
           border-radius: 50%;
       }

       .btn-circle i {
           position: relative;
           top: -1px;
       }

       .btn-circle-sm {
           width: 35px;
           height: 35px;
           line-height: 35px;
           font-size: 0.9rem;
       }

       .btn-circle-lg {
           width: 55px;
           height: 55px;
           line-height: 55px;
           font-size: 1.1rem;
       }

       .btn-circle-xl {
           width: 70px;
           height: 70px;
           line-height: 70px;
           font-size: 1.3rem;
       }
       .badge-cyan {
           color: #fff;
           background-color: #007bff;
       } .btn-primary {
             background-color: #3f51b5 !important;
             color: #fff
         }
       .rounded-pill {
           border-radius: 50rem !important;
       }
       .anchos{
           width: 15px;
       }
       .modal-lg {
           max-width: 1000px !important;
       }
        .error{
            color: red;
        }
       #tblParticipantes_filter {
           visibility: hidden;
       }

    /*
    table.dataTable.dataTable_width_auto {
          width: auto;
        }
    */
    </style>
   <!-- <spring:url value="/resources/css/animate.css" var="anime" />
    <link rel="stylesheet" href="${anime}" type="text/css"/> -->

    <spring:url value="/resources/css/sweetalert.css" var="swalcss" />
    <link href="${swalcss}" rel="stylesheet" type="text/css"/>

</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
    <!-- Main content -->
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i>
                <a href="<spring:url value="/Registro/BuscarInfor" htmlEscape="true "/>">
                    <spring:message code="Datos Generales" />
                </a>
            </li>
        </ol>
        <div class="container-fluid">
            <div class="animated fadeIn">
                <div class="card shadow w-100 bg-white rounded">
                    <spring:url value="/Registro/searchParticipant" var="searchPartUrl"/>
                    <div class="card-header">
                        <h5 class="text-gray-dark" style="font-family: Roboto">
                            <i class="fa fa-list" aria-hidden="true"></i>
                            <spring:message code="Datos Generales del Participante" /></h5>
                    </div>
                    <div class="card-body text-dark p-2 effect-1">
                        <div class="container">

                            <spring:url value="/Registro/searchParticipant" var="searchPartUrl"/>
                            <spring:url value="/Registro/participantsByCasa" var="participantsByCodeCasaUrl"/>
                            <spring:url value="/Registro/allParticipants" var="allParticipantsUrl"/>
                            <spring:url value="/Registro/getNombre1" var="getNombre1Url"/>
                            <spring:url value="/Registro/getApellido1" var="getApellido1Url"/>
                            <spring:url value="/Registro/getPartNombreApellido" var="getPartNombreApellidoUrl"/>

                            <form action="#" autocomplete="off" id="select-participante-form" name="select-participante-form" class="form-horizontal">
                                <div class="form-group row">
                                    <label class="form-control-label col-md-2" for="username"><spring:message code="participant.code" />
                                        <span class="required">*</span>
                                    </label>
                                    <div class="input-group col-md-10">
                                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                        <input id="parametro" name="parametro" type="text" value="" class="form-control"/>
                                        <button id="buscar" type="submit" class="btn btn-success btn-ladda" data-style="expand-right">
                                            <i class="fa fa-search" aria-hidden="true"></i>
                                            <spring:message code="search" />
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <!-- Tabs -->
                        <div class="">
                            <div class="">
                                <div class="">
                                    <div class="card mt-3 tab-card">
                                        <div class="card-header tab-card-header">
                                            <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                                                <li class="nav-item">
                                                    <a class="nav-link" id="one-tab" data-toggle="tab" href="#one" role="tab" aria-controls="One" aria-selected="true">
                                                        <i class="fa fa-users" aria-hidden="true"></i> Datos del Participante</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="two-tab" data-toggle="tab" href="#two" role="tab" aria-controls="Two" aria-selected="false">
                                                        <i class="fa fa-user-times" aria-hidden="true"></i> Información del Retiro</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" id="three-tab" data-toggle="tab" href="#three" role="tab" aria-controls="Three" aria-selected="false">
                                                        <i class="fa fa-search" aria-hidden="true"></i> Buscar Nombre</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="tab-content" id="myTabContent">
                                            <div class="tab-pane fade show active p-3 animate__headShake" id="one" role="tabpanel" aria-labelledby="one-tab">
                                                <!-- 1er tabs -->
                                                <!-- Fin 1er tabs-->
                                                <div class="container">
                                                    <div class="main-body">
                                                        <div class="row gutters-sm">
                                                            <div class="col-md-5 mb-3">
                                                                <div class="card card1">
                                                                    <div class="card-body">
                                                                        <div class="d-flex flex-column align-items-center text-center">
                                                                            <spring:url value="/resources/img/default-profile.jpg" var="userDefault" />
                                                                            <img class="rounded-circle" width="150" src="${userDefault}" alt="<spring:message code="Admin" />" />
                                                                            <div class="mt-3">
                                                                                <span class="badge badge-pill text-primary" id="nombreComplete" style="font-size: 15px"></span>
                                                                                <div class="table-responsive">

                                                                                    <table class="table table-hover" style="width:100%">


                                                                                        <tr>
                                                                                            <td class="text-left">Código Participante:</td>
                                                                                            <td class="text-right"><span class="badge badge-pill text-primary" id="idParticipante" style="font-size: 15px"></span> </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="text-left">Estudios</td>
                                                                                            <td class="text-right"><span class="badge badge-pill text-primary" id="estudios" style="font-size: 15px"></span> </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td class="text-left">Fecha Nacimiento</td>
                                                                                            <td class="text-right">	<span id="fnac"></span> </td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td class="text-left">PBMC</td>
                                                                                            <td class="text-right"><span class="badge badge-pill text-white pull-rigth" style="background-color: #d562da; font-size: 15px" id="pbmc2"></span></td>
                                                                                        </tr>

                                                                                        <tr>
                                                                                            <td class="text-left">PaxGene</td>
                                                                                            <td class="text-right"><span class="badge badge-pill text-white" style="background-color: #00dd00; font-size: 15px" id="paxgene2"></span></td>
                                                                                        </tr>
                                                                                            <!--
                                                                                        <tr>

                                                                                            <td><button id="btnCodeLineal" class="btn btn-primary float-left" data-toggle="tooltip" data-placement="top" title="Imprimir Lineal">
                                                                                                <i class="fa fa-barcode" aria-hidden="true"></i>
                                                                                            </button>
                                                                                            </td>

                                                                                            <td>
                                                                                                <button id="btnCodeBidi" class="btn btn-warning float-right" data-toggle="tooltip" data-placement="bottom" title="Imprimir QRCode">
                                                                                                    <i class="fa fa-print"></i>
                                                                                                </button>
                                                                                            </td>

                                                                                        </tr>-->
                                                                                    </table>

                                                                                </div>

                                                                            </div>

                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="card card1 mb-3">
                                                                   <div class="card-body">
                                                                       <h5 class="d-flex align-items-center mb-3"><i class="material-icons text-warning mr-2"> <strong>Procesos Pendientes </strong></i> <span id="contador" class="badge badge-primary badge-pill"></span></h5>

                                                                       <ul id="myUL" class="list-group mb-3">
                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Realizar encuesta</small>
                                                                                   <h6 class="my-0">Peso y Talla</h6>
                                                                               </div>
                                                                               <h2><span id="pyt" class="badge badge-primary badge-pill text-dark"></span></h2>
                                                                           </li>
                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Realizar encuesta</small>
                                                                                   <h6 class="my-0">Lactancia Materna</h6>
                                                                               </div>
                                                                               <h2><span id="lact" class="badge badge-primary badge-pill text-dark"></span></h2>
                                                                           </li>
                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Realizar encuesta</small>
                                                                                   <h6 class="my-0">Participante</h6>
                                                                               </div>
                                                                               <h2><span id="enc_part" class="badge badge-primary badge-pill text-dark"></span></h2>
                                                                           </li>
                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small  class="text-muted">Realizar</small>
                                                                                   <h6 class="my-0">Consentimiento Flu</h6>
                                                                               </div>
                                                                               <h2> <span id="cons_flu" class="badge badge-primary badge-pill text-dark"></span> </h2>

                                                                           </li>

                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Realizar </small>
                                                                                   <h6 class="my-0">Consentimiento Dengue</h6>
                                                                               </div>
                                                                               <h2><span id="cons_Den" class="badge badge-primary badge-pill text-dark"></span> </h2>
                                                                           </li>

                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Tiene</small>
                                                                                   <h6 class="my-0">Vacunas</h6>
                                                                               </div>
                                                                               <h2><span id="vacuna" class="badge badge-primary badge-pill text-white"></span> </h2>
                                                                           </li>

                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted"> Realizar Encuesta </small>
                                                                                   <h6 class="my-0"> Casa Cohorte</h6>
                                                                               </div>
                                                                               <h2><span id="enc_casa_cohorte" class="badge badge-primary badge-pill text-dark"></span> </h2>
                                                                           </li>

                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Realizar Encuesta</small>
                                                                                   <h6 class="my-0"> Casa Familia</h6>
                                                                               </div>
                                                                               <h2> <span id="enc_casa_Fam" class="badge badge-primary badge-pill text-dark"></span></h2>
                                                                           </li>

                                                                           <li class="list-group-item d-flex justify-content-between lh-condensed">
                                                                               <div>
                                                                                   <small class="text-muted">Realizar Encuesta</small>
                                                                                   <h6 class="my-0">Casa Seroprevalencia</h6>
                                                                               </div>
                                                                               <h2> <span id="enc_casa_sa" class="badge badge-primary badge-pill text-dark"></span></h2>
                                                                           </li>
                                                                       </ul>
                                                                   </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-7">
                                                                <div class="card card1 mb-3">
                                                                    <div class="card-body">
                                                                        <h6 class="d-flex align-items-center mb-3"><i class="material-icons text-info mr-2"> <strong> Información Del Participante </strong></i></h6>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Casa Pediátrica</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                                <span id="idCasa"></span>
                                                                                <button id="VerAll" class="btn btn-info rounded-pill float-right" data-toggle="tooltip" data-placement="bottom" title="Participantes en Casa">
                                                                                <i class="fa fa-users" aria-hidden="true"></i>
                                                                                </button>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Casa Familia</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                                <span id="CodigoCasaFamilia"></span>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Sexo</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                                <span id="sexo"></span>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Edad</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                               <span id="edad"></span>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Dirección</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                               <span id="direc"></span>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">Barrio</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                                <span id="barrio"></span>
                                                                            </div>
                                                                        </div>
                                                                        <hr>
                                                                        <div class="row">
                                                                            <div class="col-sm-3">
                                                                                <h6 class="mb-0">N° Manzana</h6>
                                                                            </div>
                                                                            <div class="col-sm-9 text-secondary">
                                                                                <span id="manzana"></span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-md-8">
                                                                    <div class="card card1 mb-3">
                                                                        <div class="card-body">
                                                                            <h6 class="d-flex align-items-center mb-3"><i class="material-icons text-info mr-2"> <strong> Datos Familiares</strong> </i></h6>
                                                                            <div class="row">
                                                                                <div class="col-sm-3">
                                                                                    <h6 class="mb-0">Jefe de Familia</h6>
                                                                                </div>
                                                                                <div class="col-sm-9 text-secondary">
                                                                                    <span id="jefe"></span>
                                                                                </div>
                                                                            </div>
                                                                            <hr>
                                                                            <div class="row">
                                                                                <div class="col-sm-3">
                                                                                    <h6 class="mb-0">Padre</h6>
                                                                                </div>
                                                                                <div class="col-sm-9 text-secondary">
                                                                                    <span id="padre"></span>
                                                                                </div>
                                                                            </div>
                                                                            <hr>
                                                                            <div class="row">
                                                                                <div class="col-sm-3">
                                                                                    <h6 class="mb-0">Madre</h6>
                                                                                </div>
                                                                                <div class="col-sm-9 text-secondary">
                                                                                    <span id="madre"></span>
                                                                                </div>
                                                                            </div>
                                                                            <hr>
                                                                            <div class="row">
                                                                                <div class="col-sm-3">
                                                                                    <h6 class="mb-0">Tutor</h6>
                                                                                </div>
                                                                                <div class="col-sm-9 text-secondary">
                                                                                    <span id="tutor"></span>
                                                                                </div>
                                                                            </div>
                                                                            <hr>
                                                                            <div class="row">
                                                                                <div class="col-sm-3">
                                                                                    <h6 class="mb-0">Relación</h6>
                                                                                </div>
                                                                                <div class="col-sm-9 text-secondary">
                                                                                    <span id="relfam"></span>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade p-3 animate__headShake" id="two" role="tabpanel" aria-labelledby="two-tab">
                                                <hr>
                                                        <div class="row">
                                                            <div class="col-sm-12 col-xl-12">
                                                                        <div class="table-responsive">
                                                                            <table id="tblVerRetiro" class="table table-hover table-bordered dt-responsive nowrap" style="width:100%">
                                                                                <thead>
                                                                                <tr>
                                                                                    <th class="text-center"><spring:message code="CÓDIGO" /></th>
                                                                                    <th class="text-center"><spring:message code="FECHA RETIRO" /></th>
                                                                                    <th class="text-center"><spring:message code="COMUNICADO POR" /></th>
                                                                                    <th class="text-center"><spring:message code="RELACIÓN" /></th>
                                                                                    <th><spring:message code="MOTIVO" /></th>
                                                                                    <th class="text-center"><spring:message code="OBSERVACIÓN" /></th>
                                                                                    <th class="text-center"><spring:message code="ESTUDIO" /></th>
                                                                                    <th class="text-center"><spring:message code="FECHA FALLECIDO" /></th>
                                                                                </tr>
                                                                                </thead>
                                                                                <tbody></tbody>
                                                                                <tfoot>
                                                                                <tr>
                                                                                    <th class="text-center"><spring:message code="CÓDIGO" /></th>
                                                                                    <th class="text-center"><spring:message code="FECHA RETIRO" /></th>
                                                                                    <th class="text-center"><spring:message code="COMUNICADO POR" /></th>
                                                                                    <th class="text-center"><spring:message code="RELACIÓN" /></th>
                                                                                    <th><spring:message code="MOTIVO" /></th>
                                                                                    <th class="text-center"><spring:message code="OBSERVACIÓN" /></th>
                                                                                    <th class="text-center"><spring:message code="ESTUDIO" /></th>
                                                                                    <th class="text-center"><spring:message code="FECHA FALLECIDO" /></th>
                                                                                </tr>
                                                                                </tfoot>
                                                                            </table>
                                                                        </div>
                                                            </div>
                                                        </div>

                                            </div>
                                            <div class="tab-pane fade p-3 animate__headShake" id="three" role="tabpanel" aria-labelledby="three-tab">

                                                <div class="row">

                                                    <div class="col-md-12">

                                                        <form name="form_by_nombre" action="#" id="form_by_nombre">

                                                            <div class="row">
                                                                <div class="col-md-1"></div>

                                                                <div class="col-md-4">
                                                                    <div class="form-group">
                                                                        <label for="nombre1">Nombre</label>
                                                                        <input type="text" class="form-control focusNext" placeholder="Primer Nombre" name="nombre1" id="nombre1" tabindex="1">
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-4">
                                                                    <div class="form-group">
                                                                        <label for="apellido1">Apellido</label>
                                                                        <input type="text" class="form-control focusNext" placeholder="Primer Apellido" name="apellido1" id="apellido1" tabindex="2">
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-2" style="margin-top: 10px; padding-top: 17px; padding-left: 0px; padding-bottom: 10px">
                                                                    <button id="cargarParticipantes"  type="submit" class="btn btn-success btn-ladda btn-block" data-style="expand-right"><i class="fa fa-search"></i></button>
                                                                </div>
                                                                <div class="col-md-1"></div>

                                                            </div>
                                                           <!-- <div class="row">
                                                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label for="nombre1">Nombre</label>
                                                                        <input type="text" class="form-control" placeholder="Primer Nombre" name="nombre1" id="nombre1">
                                                                    </div>
                                                                </div>
                                                                <div class="col">
                                                                        <label for="nombre1">Apellido</label>
                                                                    <div class="input-group mb-3">
                                                                        <input type="text" class="form-control" placeholder="Primer Apellido" name="apellido1" id="apellido1" aria-describedby="basic-addon2">
                                                                        <div class="input-group-append">
                                                                            <button id="cargarParticipantes"  type="submit" class="btn btn-success btn-ladda" data-style="expand-right"><i class="fa fa-search"></i></button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>-->
                                                        </form>
                                                        <div class="table-responsive">
                                                        <table class="table table-hover table-bordered dt-responsive nowrap" id="tblParticipantes" style="width:100%">
                                                            <thead>
                                                            <tr>
                                                                <th scope="col"><spring:message code="code" /></th>
                                                                <th scope="col"><spring:message code="1er.Nombre" /></th>
                                                                <th scope="col"><spring:message code="2do.Nombre" /></th>
                                                                <th scope="col"><spring:message code="1er Apellido" /></th>
                                                                <th scope="col"><spring:message code="2do.Apellido" /></th>
                                                                <th scope="col"><spring:message code="Fecha Nac." /></th>
                                                                <th scope="col"><spring:message code="Sexo" /></th>
                                                                <th scope="col"><spring:message code="actions" /></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            </tbody>
                                                            <tfoot>
                                                            <tr>
                                                                <th scope="col"><spring:message code="code" /></th>
                                                                <th scope="col"><spring:message code="1er.Nombre" /></th>
                                                                <th scope="col"><spring:message code="2do.Nombre" /></th>
                                                                <th scope="col"><spring:message code="1er Apellido" /></th>
                                                                <th scope="col"><spring:message code="2do.Apellido" /></th>
                                                                <th scope="col"><spring:message code="Fecha Nac." /></th>
                                                                <th scope="col"><spring:message code="Sexo" /></th>
                                                                <th scope="col"><spring:message code="actions" /></th>
                                                            </tr>
                                                            </tfoot>
                                                        </table>
                                                    </div>
                                                </div>

                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- ./Tabs -->
                        <div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- /.conainer-fluid -->
    </div>
    <!-- Modal -->
    <div id="exampleModal" class="modal fade bd-example-modal-lg"  tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">
                        <i class="fa fa-users"></i> Participantes.</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="table-responsive" style="width: 100%">
                         <table id="tblCasa_Participants" class="table table-dark table-hover dt-responsive nowrap" style="width:100%">
                        <thead>
                        <tr>
                            <th>C.Pediátrica</th>
                            <th>Casa CHF</th>
                            <th>Código</th>
                            <th>Nombre Completo</th>
                            <th>Edad</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        <i class="fa fa-times-circle" aria-hidden="true"></i>
                        Cerrar</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal -->

    <!-- /.conainer-fluid -->
</div>
</div>
<jsp:include page="../fragments/bodyFooter.jsp" />
<jsp:include page="../fragments/corePlugins.jsp" />
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/app.js" var="App" />
<script src="${App}" type="text/javascript"></script>
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>
<spring:url value="/resources/js/views/loading-buttons.js" var="loadingButtonsJs" />
<script src="${loadingButtonsJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/jquery.dataTables.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.bootstrap4.min.js" var="Tablesb4" />
<script type="text/javascript" src="${Tablesb4}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/dataTables.responsive.min.js" var="TablesResponsive" />
<script type="text/javascript" src="${TablesResponsive}"></script>

<spring:url value="/resources/js/libs/dataTableResponsive/responsive.bootstrap4.min.js" var="TResponsiveb4" />
<script type="text/javascript" src="${TResponsiveb4}"></script>

<spring:url value="/resources/js/libs/data-tables/i18n/label_{language}.json" var="dataTablesLang">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/libs/jquery-ui.js" var="uiJs" />
<script src="${uiJs}" type="text/javascript"></script>


<script type="text/javascript">
        $(function () {
            $('[data-toggle="tooltip"]').tooltip()
        });
    $(document).ready(function(){
        var table  = $('#tblVerRetiro').DataTable({
            "autoWidth": true,
            searching: true,
            paging: true,
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },"columnDefs": [{
                "className": "text-center",
                "targets": "_all"
                }],
            initComplete: function () {
                this.api().columns().every( function () {
                    var that = this;
                    $('input', this.footer() ).on( 'keyup change clear', function () {
                        if ( that.search() !== this.value ) {
                            that.search( this.value ).draw();
                        }
                    });
                });
            }
        });
        $('#tblVerRetiro tfoot th').each( function () {
            var title = $(this).text();
            $(this).html( '<input type="text" placeholder="Búscar '+title+'" />' );
        });
        $("#parametro").focus();
        var parametros = {searchPartUrl: "${searchPartUrl}",
            participantsByCodeCasaUrl: "${participantsByCodeCasaUrl}",
            allParticipantsUrl: "${allParticipantsUrl}",
            getNombre1Url: "${getNombre1Url}",
            getApellido1Url: "${getApellido1Url}",
            getPartNombreApellido:"${getPartNombreApellidoUrl}"
        };
        $("#select-participante-form").validate({
            errorElement: 'span', //default input error message container
            focusInvalid: false,
            rules:{
                parametro: {required: true}
            },
            //errorElement: 'em',
            errorPlacement: function ( error, element ) {
                // Add the `help-block` class to the error element
                error.addClass( 'form-control-feedback' );
                if ( element.prop( 'type' ) === 'checkbox' ) {
                    error.insertAfter( element.parent( 'label' ) );
                } else {
                    //error.insertAfter( element ); //cuando no es input-group
                    error.insertAfter(element.parent('.input-group'));
                }
            },
            highlight: function ( element, errorClass, validClass ) {
                $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
            },
            unhighlight: function (element, errorClass, validClass) {
                $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
            },
            submitHandler: function (form) {
                var id = $('#parametro').val();
                searchParticipante(id);
            }
        })
        function searchParticipante(id){
            $.getJSON(parametros.searchPartUrl, { parametro : id,   ajax : 'true'  }, function(data) {
                console.log(data);
                LimpiarCtrls();
                var len = data.length;
                if(len==0){
                    swal("Error!","Código no encontrado","error");
                    $("#parametro").focus();
                }
                else{
                    if(data.estPart == "0"){
                        toastr.warning("Participante está retirado!");
                    }else{
                        toastr.success("Participante está Activo!");
                    }
                        var elemento = data.edad;
                        var fecha = elemento.split('/');
                        var datestring = ( fecha[0] + " Años " + fecha[1] + " Meses " + fecha[2] + " Dias");
                        $("#anios").val(fecha[0]);
                        $("#mes").val(fecha[1]);
                        $("#dias").val(fecha[2]);
                        $("#idParticipante").text(data.codigo_participante);
                        $("#idCasa").text(data.codigoCasa);
                        $("#CodigoCasaFamilia").text(data.CodigoCasaFamilia);
                        $("#manzana").text(data.manzana);
                        $("#nombreComplete").text(data.nombreCompletoParticipante);
                        $("#sexo").text(data.sexo);
                        $("#fnac").text(data.fechaNac);
                        $("#edad").text(datestring);
                        $("#jefe").text(data.jefe);
                        $("#tutor").text(data.tutor);
                        var relacion;
                        switch(data.relacionFamTutor){
                            case '1':
                                relacion = "Madre";
                                break;
                            case '2':
                                relacion = "Padre";
                                break;
                            case '3':
                                relacion = "Abuelo(a)";
                                break;
                            case '4':
                                relacion = "Tío(a)";
                                break;
                            case '5':
                                relacion = "Hermano(a)";
                                break;
                            case '8':
                                relacion = "Participante";
                                break;
                            case '998':
                                relacion = "Otra relación familiar";
                        }
                        $("#relfam").text(relacion);
                        $("#madre").text(data.nombreCompletoMadre);
                        $("#padre").text(data.nombreCompletoPadre);
                        $("#direc").text(data.direccion);
                        $("#barrio").text(data.nombreBarrio);
                        $("#estudios").text(data.estudios);
                        $("#pbmc").val(data.isPbmc);
                        $("#pbmc2").text(data.isPbmc);
                        $("#paxgene").val(data.isPaxgene);
                        $("#paxgene2").text(data.isPaxgene);

                        if(data.pesotalla =='Si'){//11979- codigo
                            $("#pyt").text(data.pesotalla).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#pyt").text(data.pesotalla).removeClass('badge-danger');
                        }

                        if(data.lactMat =='Si'){
                            $("#lact").text(data.lactMat).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#lact").text(data.lactMat).removeClass('badge-danger');
                        }

                        if(data.encPart =='Si'){
                            $("#enc_part").text(data.encPart).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#enc_part").text(data.encPart).removeClass('badge-danger');
                        }

                        if(data.consFlu =='Si'){
                            $("#cons_flu").text(data.consFlu).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#cons_flu").text(data.consFlu).removeClass('badge-danger');
                        }

                        if(data.consDeng =='Si'){
                            $("#cons_Den").text(data.consDeng).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#cons_Den").text(data.consDeng).removeClass('badge-danger');
                        }

                        if(data.vacunas =='Si'){
                            $("#vacuna").text(data.vacunas).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#vacuna").text(data.vacunas).removeClass('badge-danger');
                        }

                        if(data.encCasaCoh =='Si'){
                            $("#enc_casa_cohorte").text(data.encCasaCoh).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#enc_casa_cohorte").text(data.encCasaCoh).removeClass('badge-danger');
                        }

                        if(data.encCHF =='Si'){
                            $("#enc_casa_Fam").text(data.encCHF).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#enc_casa_Fam").text(data.encCHF).removeClass('badge-danger');
                        }

                        if(data.encCasaSa =='Si'){
                            $("#enc_casa_sa").text(data.encCasaSa).addClass('badge badge-danger badge-pill text-dark');
                        }else{
                            $("#enc_casa_sa").text(data.encCasaSa).removeClass('badge-danger');
                        }
                        recorrerUL();
                        table.clear().draw( false );
                        var len = data.retiroList.length;
                        for( var i = 0; i < len; i++ ) {
                            var relacion;
                            switch(data.retiroList[i].relfam){
                                case 1:
                                    relacion = "Madre";
                                    break;
                                case 2:
                                    relacion = "Padre";
                                    break;
                                case 3:
                                    relacion = "Abuelo(a)";
                                    break;
                                case 4:
                                    relacion = "Tío(a)";
                                    break;
                                case 5:
                                    relacion = "Hermano(a)";
                                    break;
                                case 8:
                                    relacion = "Participante";
                                    break;
                                case 998:
                                    relacion = "Otra relación familiar";
                            }
                            var getCode = data.codigo_participante;
                            var fr = new Date(data.retiroList[i].fecharetiro);
                            var ffallecido ="";
                            if(data.retiroList[i].fechafallecido === undefined){
                                ffallecido = '<h3> <span class="badge badge-success"> NO</span> </h3>';
                            }else{
                                var objfecha = new Date(data.retiroList[i].fechafallecido);
                                var datefallecido =  ("0" + objfecha.getDate()).slice(-2) + "/" + ("0"+(objfecha.getMonth()+1)).slice(-2) + "/" + objfecha.getFullYear();
                                ffallecido = '<h3> <span class="badge badge-danger"> '+ datefallecido.toUpperCase() +'</span> </h3>';
                            }
                            var datestring =  ("0" + fr.getDate()).slice(-2) + "/" + ("0"+(fr.getMonth()+1)).slice(-2) + "/" + fr.getFullYear();
                            var comunicador = data.retiroList[i].quiencomunica == "" ? "-" : data.retiroList[i].quiencomunica;
                            var observacion = ( data.retiroList[i].observaciones == "") ? "-":  data.retiroList[i].observaciones;
                            table.row.add([
                                getCode,
                                datestring,
                                comunicador.toUpperCase(),
                                relacion.toUpperCase(),
                                data.retiroList[i].descripcionretiro.toUpperCase(),
                               observacion.toUpperCase(),
                                data.retiroList[i].estudioretirado.toUpperCase(),
                                ffallecido
                            ]).draw( false );
                        }
                }
            }).fail(function() {
                toastr.error("Código no existe!");
                $("#parametro").focus();
            });
        }
        function recorrerUL(){
            var cont=0;
            $("#myUL li span").each(function(indice, elemento) {
                if($(elemento).text() == 'Si'){
                    cont++;
                }
                $('#contador').text(""+cont);
                $("#pendientes").text(""+cont);
            });
        }

        $("#btnCodeLineal").on("click", function(){
            var id = $("#idParticipante").text();
            swal({
                title: "Imprimir!",
                text: "cuantas copias del código: "+100,
                type: "input",
                showCancelButton: true,
                closeOnConfirm: false,
                inputPlaceholder: "copias"
            }, function (inputValue) {
                if (inputValue === false) return false;
                if (inputValue === "") {
                    swal.showInputError("debes ingresar algo!");
                    return false
                }
                if( isNaN( inputValue ) ){
                    return false;
                }
                if(id != ""){
                    imprimir(id,inputValue)
                    swal("Stickers!", "impresos: " + inputValue, "success");
                }
            });
        });
        $("#btnCodeBidi").on("click", function(){
            var id = $("#idParticipante").text();
            swal({
                title: "Imprimir!",
                text: "Número de copias: "+100,
                type: "input",
                showCancelButton: true,
                closeOnConfirm: false,
                inputPlaceholder: "copias"
            }, function (inputValue) {
                if (inputValue === false) return false;
                if (inputValue === "") {
                    swal.showInputError("debes ingresar algo!");
                    return false
                }
                if( isNaN( inputValue ) ){
                    return false;
                }
                if(id != ""){
                    imprimir(id,inputValue)
                    swal("Stickers!", "impresos: " + inputValue, "success");
                }
            });
        });

        function imprimir(strBarCodes,inputValue){
            var copia = parseInt(inputValue);
            $.getJSON("http://localhost:13001/print", { barcodes: strBarCodes, copias: copia, ajax:'false' }, function (data) {
                //console.log(data);
                toastr.success("etiquetas impresas");
            }).fail(function (jqXHR) {
                //console.log(jqXHR);
                if (jqXHR.status!=200 && jqXHR.status!=0) {
                    toastr.error("error al imprimir etiquetas","Error",{timeOut: 0});
                }
            });
        }

        var tableParticipantCasa = $("#tblCasa_Participants").DataTable({
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            autoWidth: true
        });

        /* Metodo para vizualizar participantes por casa pediatrica*/
        $('#VerAll').on('click', function(){
            const codCasa = $('#idCasa').text();
            const idParticipante = $('#idParticipante').text();
            if( !isNaN(codCasa) || codCasa != null ){
                $.getJSON(parametros.participantsByCodeCasaUrl,{casaCode: codCasa, codParticipante: idParticipante, ajax:'true'}, function(data){
                    console.log(data);
                    tableParticipantCasa.clear().draw( false );
                    $.each(data, function(i, item) {
                        var cPediatrica = (data[i].codCasaPediatrica);
                        var casaFam = (data[i].codCasaFamilia);
                        var cParticipante = (data[i].idParticipante);
                        var nombre = (data[i].nombreParticipante);
                        var edad = data[i].anios+" años "+ data[i].meses + " meses " + data[i].dias +" dias";
                        tableParticipantCasa.row.add([
                            cPediatrica,
                            casaFam,
                            cParticipante,
                            nombre,
                            edad
                        ]).draw(false);

                    });
                }).fail(function(){
                    toastr.error("Error, Intenta de nuevo!","Error",{timeOut: 0});
                });
            }

            $("#exampleModal").modal("show");
        })

        $('#tblParticipantes tfoot th').each( function () {
            var title = $(this).text();
            $(this).html( '<input type="text" placeholder="Búscar ' + title + ' " />' );
        });

        var tablaParticipantes = $("#tblParticipantes").DataTable({
            searching: true,
            lengthChange: false,
            "oLanguage": {
                "sUrl": "${dataTablesLang}"
            },
            columnDefs: [
                {
                    targets: -1,
                    className: 'text-center'
                }, {
                targets: 5,
                className: 'text-center'
            }, {
                targets: 6,
                className: 'text-center'
            },{
                targets: 7,
                className: 'text-center'
            }
        ],
            initComplete: function () {
                this.api().columns().every( function () {
                    var that = this;
                    $('input', this.footer() ).on( 'keyup change clear', function () {
                        if ( that.search() !== this.value ) {
                            that.search( this.value ).draw();
                        }
                    });
                });
            }
        });


        $("#cargarParticipantes").on("click", function(){
            $("form[name='form_by_nombre']").validate({
                rules: {
                    nombre1: {required: true},
                    apellido1: {required: true}
                },messages: {
                    nombre1: "Ingrese el Nombre.",
                    apellido1:"Ingrese el Apellido."
                }, errorElement: 'em',
                errorPlacement: function ( error, element ) {
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    } else {
                        error.insertAfter( element );
                    }
                    if (element.attr("name") == "signo") {
                        error.insertAfter("#gendererror");
                    } else {
                        error.insertAfter(element);
                    }
                },
                highlight: function ( element, errorClass, validClass ) {
                    $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                    $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
                },
                unhighlight: function (element, errorClass, validClass) {
                    $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                    $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
                },
                submitHandler: function (form) {
                    getParticipantes()
                }
            });

        });

        function getParticipantes(){
            tablaParticipantes.clear().draw( false );
            var form1 = $('#form_by_nombre');
            $.getJSON(parametros.getPartNombreApellido,{nombre1: $("#nombre1").val().trim(), apellido1: $("#apellido1").val().trim()}, function(data){
                $.each(data, function(key, item){
                    var codigo = (data[key].codigo);
                    var nombre1 = (data[key].nombre1);
                    var nombre2 = (data[key].nombre2);
                    var apellido1 = (data[key].apellido1);
                    var apellido2 = (data[key].apellido2);
                    var d =new Date(data[key].fechaNac);
                    var datestring =  ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" + d.getFullYear();
                    var sexo = data[key].sexo;

                    tablaParticipantes.row.add([
                        codigo,
                        nombre1,
                        nombre2,
                        apellido1,
                        apellido2,
                        datestring,
                        sexo,
                        valor = '<a class="btn btn-info btn-sm btnSendId" data-id="' + data[key].codigo + '"><i class="fa fa-search"></i></a>'
                    ]).draw(false);
                });
            }).fail(function(){
                toastr.error("Error, Intenta de nuevo!","Error",{timeOut: 0});
            });
        }

        $("#tblParticipantes tbody").on("click", ".btnSendId",function(){
            var id = $(this).data('id');
            searchParticipante(id);
            debugger;
            $("#one").addClass("active show");
            $("#one-tab").addClass("active");
            $("#three-tab").removeClass("active");
            $("#three").removeClass("active");
        });
        $( "#nombre1" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parametros.getNombre1Url, {nombre1: $('#nombre1').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });

        $( "#apellido1" ).autocomplete({
            delay:100,
            source: function(request, response){
                $.getJSON(parametros.getApellido1Url, {apellido1: $('#apellido1').val().trim(), ajax: 'true'},function(data){
                    response($.map(data, function (value, key) {
                        return {
                            label: value
                        };
                    }));
                });
            },minLength: 3,
            scroll: true,
            highlight: true
        });

        document.addEventListener('keypress', function(evt) {
            // Si el evento NO es una tecla Enter
            if (evt.key !== 'Enter') {
                return;
            }
            let element = evt.target;
            // Si el evento NO fue lanzado por un elemento con class "focusNext"
            if (!element.classList.contains('focusNext')) {
                return;
            }
            // AQUI logica para encontrar el siguiente
            let tabIndex = element.tabIndex + 1;
            var next = document.querySelector('[tabindex="'+tabIndex+'"]');
            // Si encontramos un elemento
            if (next) {
                next.focus();
                event.preventDefault();
            }
        });


        function LimpiarCtrls(){
            $("#idParticipante").text("");

            $("#estudios").text("");
            $("#fnac").text("");
            $("#pbmc2").text("");
            $("#paxgene2").text("");
            $("#idCasa").text("");
            $("#CodigoCasaFamilia").text("");
            $("#sexo").text("");
            $("#edad").text("");
            $("#direc").text("");
            $("#barrio").text("");
            $("#manzana").text("");
            $("#contador").text("");
            $("#pyt").text("");
            $("#lact").text("");
            $("#enc_part").text("");
            $("#cons_flu").text("");
            $("#cons_Den").text("");
            $("#vacuna").text("");
            $("#enc_casa_cohorte").text("");
            $("#enc_casa_sa").text("");
            $("#jefe").text("");
            $("#padre").text("");
            $("#madre").text("");
            $("#tutor").text("");
            $("#relfam").text("");
            $("#nombreComplete").text("");


        }

    });

</script>
</body>
</html>
