<%--
  Created by IntelliJ IDEA.
  User: ICS_Inspiron3
  Date: 22/05/2019
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/js/libs/data-tables/TableTools/css/dataTables.tableTools.css" var="dtttcss" />
    <link rel="stylesheet" href="${dtttcss}"/>
    <!-- DATE PICKER -->
    <spring:url value="/resources/css/datepicker.css" var="datepickerCss" />
    <link href="${datepickerCss}" rel="stylesheet" type="text/css"/>
    <!-- END DATE PICKER -->
    <spring:url value="/resources/css/responsive.bootstrap4.min.css" var="bdrespat4" />
    <link rel="stylesheet" href="${bdrespat4}" type="text/css"/>

    <spring:url value="/resources/css/animate.css" var="anime" />
    <link rel="stylesheet" href="${anime}" type="text/css"/>

    <style>
         input[type="text"]:read-only:not([read-only="false"]) { color: #000000; background-color: #ffffff; font-family: Roboto }
         input[type="text"]{color: #000000; font-family: Roboto}
         input[type="select"]{color: #000000; font-family: Roboto}

         span.error {
             display:block;
             visibility:hidden;
             color:red;
             font-size:90%;
         }
    </style>
    <jsp:include page="../fragments/headTag.jsp" />
    <spring:url value="/resources/css/bootstrap.min.css" var="boot" />
    <link href="${boot}" rel="stylesheet" type="text/css"/>

    <spring:url value="/resources/css/smartWizardCss/smarthWizardCss.css" var="smw" />
    <link href="${smw}" rel="stylesheet" type="text/css"/>
    <spring:url value="/resources/css/smartWizardCss/smart_wizard_theme_arrows.min.css" var="smwtheme" />
    <link href="${smwtheme}" rel="stylesheet" type="text/css"/>

    <title>Hemodinámica</title>
</head>
<body class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
<jsp:include page="../fragments/bodyHeader.jsp" />
<div class="app-body">
    <jsp:include page="../fragments/sideBar.jsp" />
    <div class="main">
        <!-- Breadcrumb -->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<spring:url value="/" htmlEscape="true "/>"><spring:message code="home" /></a>
                <i class="fa fa-angle-right"></i> <a href="<spring:url value="/hemo/listado2/" htmlEscape="true "/>">LISTADO</a>
                <i class="fa fa-angle-right"></i><a href="<spring:url value="/hemo/create/" htmlEscape="true "/>">
                    <spring:message code="Hemodinámica" />
                </a>
            </li>
        </ol>
        <spring:url value="/hemo/addHemodinamica" var="saveHemoUrl"/>
        <spring:url value="/hemo/listado2" var="ListadoUrl"/>
        <spring:url value="/hemo/GetRange" var="GetRangeUrl"/>
        <c:set var="successmessage"><spring:message code="process.success" /></c:set>
        <c:set var="errormessage"><spring:message code="process.errors" /></c:set>
        <div class="container-fluid">
                <div class="animated fadeIn">
                    <div class="card text-black-50 animate__animated animate__zoomIn">
                        <div class="card-header">
                            <h5 class="text-gray-dark" style="font-family: Roboto">
                                <i class="fa fa-search" aria-hidden="true"></i>   <spring:message code="Búscar Participante" /></h5>
                        </div>
                        <div class="card-block">
                        <div class="container">
                        <!-- SmartWizard html -->
                        <div id="smartwizard">

                            <ul class="nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="#step-1">
                                        <i class="fa fa-search" aria-hidden="false"></i> <strong>Búscar</strong> <br>Participante.
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#step-2">
                                        <i class="fa fa-calculator" aria-hidden="true"></i>
                                        <strong>Cálculos</strong> <br/>Peso y Talla.
                                    </a>
                                </li>
                            </ul>

                            <div class="tab-content">
                                <div id="step-1" class="tab-pane" role="tabpanel" aria-labelledby="step-1">
                                    <br/>
                                    <br/>
                                    <div class="container">
                                        <spring:url value="/hemo/searchParticipant" var="searchPartUrl"/>
                                        <form action="#" id="select-participante-form" name="select-participante-form" autocomplete="off" class="form-horizontal">
                                            <div class="row">
                                                <div class="form-group col-sm-12 col-md-6 col-lg-12">
                                                    <label>Código del Participante</label>
                                                    <input type="text" class="form-control" placeholder="Ingrese el código" id="parametro" name="parametro">
                                                    <div id="gendererror" class="text-danger"></div>
                                                </div>
                                            </div>
                                        </form>
                                        <form action="#" class="form-horizontal" id="save-hemo-form" method="post" autocomplete="off" name="save-hemo-form" role="form">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <h4 class="text-capitalize" style="font-family: Roboto">Datos Personales.</h4>
                                            </div>
                                                <div hidden="hidden" class="form-group col-sm-12">
                                                    <label for="idParticipante">Participante:</label>
                                                    <input type="text" name="idParticipante" id="idParticipante" placeholder="Código del Participante" readonly class="form-control"/>
                                                </div>

                                            <div class="form-group col-sm-6">
                                                <label for="nombre">Nombre:</label>
                                                <input type="text" class="form-control" placeholder="Nombre del Participante" id="nombre" required readonly>
                                            </div>


                                            <div class="form-group col-sm-3">
                                                <label for="fecha">Fecha de Nacimiento:</label>
                                                <input type="text" id="fecha" name="fecha" readonly class="form-control" />
                                            </div>

                                            <div class="form-group col-sm-3">
                                                <label for="expediente">Expediente:</label>
                                                <span class="required text-danger"> * </span>
                                                <input type="text" class="form-control" id="expediente" name="expediente" readonly  placeholder="Número de Expediente">
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="sector">Sector:</label>
                                                <span class="required text-danger"> * </span>
                                                <select name="sector" id="sector" name="sector" class="form-control" required="required">
                                                    <option selected value=""><spring:message code="select" />...</option>
                                                    <c:forEach items="${barrios}" var="barrio">
                                                        <option value="${barrio.codigo}">${barrio.nombre}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-sm-8">
                                                <label for="direccion">Dirección de Participante :</label>
                                                <input type="text" name="direccion" id="direccion" class="form-control" placeholder="Ingrese la dirección"/>
                                            </div>

                                            <div id="bar" class="form-group col-sm-12" style="display: none">
                                                <label for="barrioF">Barrio :</label>
                                                <input type="text" class="form-control" id="barrioF" name="barrioF" style="text-transform:uppercase" />
                                            </div>

                                            <div class="form-group col-sm-12">
                                                <label for="telefono">Teléfono:</label>
                                                <input type="text" class="form-control focusNext" id="telefono" name="telefono" placeholder="Número de telefono" tabindex="1">
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div id="step-2" class="tab-pane" role="tabpanel" aria-labelledby="step-2">
                                <br/>
                                    <div class="container">
                                    <div class="row">

                                    <div class="col-sm-12">
                                        <h4 class="text-capitalize" style="font-family: Roboto">Cálculos</h4>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="peso">Peso(kg):</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext"  name="peso" id="peso" placeholder="Peso" tabindex="2">
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label for="talla">Talla(cm):</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext" name="talla"  id="talla" placeholder="Talla" tabindex="3">
                                    </div>

                                    <div class="form-group col-sm-4">
                                        <label for="numParametro">Parametros:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext pos-params" name="numParametro"  id="numParametro" placeholder="Cantidad de Parámetros" tabindex="4">
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="fconsulta">Fecha Consulta:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext" id="fconsulta" name="fconsulta" data-date-end-date="+0d" required tabindex="5"/>
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="fie">Fecha Inicio de Enfermedad:</label>
                                        <span class="required text-danger"> * </span>
                                        <input type="text" class="form-control focusNext" id="fie" name="fie" required tabindex="6" data-date-end-date="+0d"/>
                                    </div>

                                    <div class="col-md-6">
                                        <div class="row">
                                            <div class="form-group col-sm-12">
                                                <div id="positivoerror" class="text-danger"></div>
                                                <div class="custom-control custom-radio custom-control-inline">
                                                    <p class="text-center">
                                                        <br/>
                                                        <input type="radio" id="chkpositivo0" value="0" name="chkpositivo" class="custom-control-input">
                                                        <label class="custom-control-label" for="chkpositivo0">Negativo.</label>
                                                    </p>
                                                </div>
                                                <div class="custom-control custom-radio custom-control-inline">
                                                    <p class="text-center">
                                                        <br/>
                                                        <input type="radio" id="chkpositivo1" value="1" name="chkpositivo" class="custom-control-input">
                                                        <label class="custom-control-label" for="chkpositivo1">Positivo.</label>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div hidden="hidden">
                                            <div class="form-group col-sm-6">
                                                <label for="edad">Edad:</label>
                                                <input type="text" class="form-control" id="edad" placeholder="Edad" name="edad" readonly>
                                            </div>
                                            <div class="form-group col-sm-4">
                                                <label for="IMCdetallado">Detalle Imc:</label>
                                                <input type="text" class="form-control" id="IMCdetallado" name="IMCdetallado" readonly>
                                            </div>

                                            <div class="form-group col-sm-3">
                                                <input type="text" name="anios" id="anios" />
                                            </div>
                                            <div class="form-group col-sm-3">
                                                <input type="text" name="mes" id="mes"/>
                                            </div>
                                            <div class="form-group col-sm-3">
                                                <input type="text" id="dias" name="dias"/>
                                            </div>
                                            <div class="form-group col-sm-3">
                                                <input type="text" id="sexo" name="sexo"/>
                                            </div>
                                            <div class="custom-control custom-checkbox my-1 mr-sm-2">
                                                <p class="text-center">
                                                    <br/>
                                                <div class="custom-control custom-switch">
                                                    <input type="checkbox" class="custom-control-input" id="chkRange2" name="chkRange2">
                                                    <label class="custom-control-label" for="chkRange2">Frecuencias Respiratorias.</label>
                                                </div>
                                                </p>
                                            </div>

                                            <div class="form-group col-sm-6">
                                                <label for="asc">A.S.C(m2):</label>
                                                <input type="text" class="form-control" id="asc" name="asc" readonly>
                                            </div>

                                            <div class="form-group col-sm-6">
                                                <label for="imc">IMC:</label>
                                                <input type="text" class="form-control" id="imc" name="imc" readonly>
                                            </div>

                                            <div class="form-group col-md-6">
                                                <label for="diasenf">Días de Enfermedad:</label>
                                                <input type="text" class="form-control" id="diasenf" name="diasenf" value="${obj.diasenf}"  readonly/>
                                            </div>

                                            <div class="custom-control custom-radio custom-control-inline">
                                                <p class="text-center">
                                                    <br/>
                                                    <input type="radio" id="customRadio2" name="positivo" class="custom-control-input" value="0">
                                                    <label class="custom-control-label" for="customRadio2"> Negativo</label>
                                                </p>

                                            </div>

                                            <div class="row">
                                                <div class="form-group col-sm-4">
                                                    <button class="btn btn-dark btn-block btn-lg" type="button" tabindex="5" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                                        <i class="fa fa-stethoscope" aria-hidden="true"></i>  Rangos
                                                    </button>
                                                </div>
                                                <div class="form-group col-sm-4">
                                                </div>
                                                <div class="form-group col-sm-4">
                                                    <button id="btnObtenerRango" class="btn btn-primary btn-block btn-lg" type="button"> Obtener</button>
                                                </div>
                                            </div>
                                        </div>



                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="collapse" id="collapseExample">
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <label style="font-family: Roboto">Rango de Presión PS/PD:</label>
                                                        </div>

                                                        <div class="form-group col-sm-4">
                                                            <input type="text" class="form-control font-weight-bold focusNext" id="sdMin" name="sdMin" placeholder="Mínima" readonly tabindex="6">
                                                        </div>
                                                        <div class="form-group col-sm-4">
                                                            <input type="text" class="form-control font-weight-bold focusNext" id="sdMed" name="sdMed" placeholder="Media" readonly tabindex="7">
                                                        </div>
                                                        <div class="form-group col-sm-4">
                                                            <input type="text" class="form-control font-weight-bold focusNext" id="sdMax" name="sdMax" placeholder="Máxima" readonly tabindex="8">
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <label class="text-capitalize"  style="font-family: Roboto">Presión Arterial Media (PAM): </label>
                                                        </div>
                                                        <div class="form-group col-sm-4">
                                                            <input type="text" class="form-control font-weight-bold focusNext" id="pamMin" name="pamMin" placeholder="Minima" readonly tabindex="9">
                                                        </div>
                                                        <div class="form-group col-sm-4">
                                                            <input type="text" class="form-control font-weight-bold focusNext" id="pamMed" name="pamMed" placeholder="Media" readonly tabindex="10">
                                                        </div>
                                                        <div class="form-group col-sm-4">
                                                            <input type="text" class="form-control font-weight-bold focusNext" id="pamMax" name="pamMax" placeholder="Máxima" readonly tabindex="11">
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <label style="font-family: Roboto">Rango de Frecuencias Cárdiacas: </label>
                                                        </div>
                                                        <div class="form-group col-sm-4">
                                                            <input type="text" class="form-control font-weight-bold focusNext" id="fcMin" name="fcMin" placeholder="Minima" readonly tabindex="12">
                                                        </div>

                                                        <div class="form-group col-sm-4">
                                                            <input type="text" class="form-control font-weight-bold focusNext" id="fcMed" name="fcMed" placeholder="Máxima" readonly tabindex="13">
                                                        </div>
                                                        <div class="form-group col-sm-4">
                                                            <input type="text" class="form-control font-weight-bold focusNext" id="fcProm" name="fcProm" placeholder="Promedio" readonly tabindex="14">
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <label class="text-capitalize"  style="font-family: Roboto">Rango Frecuencias Respiratorias </label>
                                                        </div>
                                                        <div class="form-group col-sm-6">
                                                            <input type="text" class="form-control font-weight-bold focusNext" id="frMin" name="frMin" placeholder="Minima" readonly tabindex="15">
                                                        </div>
                                                        <div class="form-group col-sm-6">
                                                            <input type="text" class="form-control font-weight-bold focusNext" id="frMax" name="frMax" placeholder="Máxima" readonly tabindex="16">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div> <!-- Fin Hidden  -->
                                    <hr/>
                                    <div class="col-md-12">
                                         <div class="row">
                                             <div class="form-group col-md-4">
                                                 <button type="submit" id="btnSave" class="btn btn-info btn-block btn-lg" tabindex="17">
                                                     <i class="fa fa-save"></i> Guardar</button>
                                             </div>
                                             <div class="form-group col-md-4">
                                             </div>
                                             <div class="form-group col-md-4">
                                                 <a class="btn btn-warning btn-block btn-lg" tabindex="18" href="<spring:url value="/hemo/listado2" htmlEscape="true "/>">
                                                     <i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
                                                     <spring:message code="Cancelar" /></a>
                                             </div>
                                         </div>
                                    </div>
                                    </form>
                                    </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <br /> &nbsp;
                        <!-- FIN SmartWizard html -->


                          </div>
                      </div>
                  </div>
              </div>
        </div>
    </div>
</div>

<jsp:include page="../fragments/corePlugins.jsp" />
<c:choose>
    <c:when test="${cookie.eIcsLang.value == null}">
        <c:set var="lenguaje" value="es"/>
    </c:when>
    <c:otherwise>
        <c:set var="lenguaje" value="${cookie.eIcsLang.value}"/>
    </c:otherwise>
</c:choose>
<!-- GenesisUI main scripts -->
<spring:url value="/resources/js/libs/jquery.validate.js" var="validateJs" />
<script src="${validateJs}" type="text/javascript"></script>
<spring:url value="/resources/js/libs/jquery-validation/additional-methods.js" var="validateAMJs" />
<script src="${validateAMJs}" type="text/javascript"></script>

<spring:url value="/resources/js/libs/jquery-validation/localization/messages_{language}.js" var="jQValidationLoc">
    <spring:param name="language" value="${lenguaje}" />
</spring:url>
<script src="${jQValidationLoc}"></script>

<!-- bootstrap datepicker -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/bootstrap-datepicker.js" var="datepickerPlugin" />
<script src="${datepickerPlugin}"></script>

<spring:url value="/resources/js/libs/mySelect2/select2.min.js" var="selectJs" />
<script type="text/javascript" src="${selectJs}"></script>
<!-- <spring:url value="/resources/js/libs/mySelect2/select2_locale_es.min.js" var="select_esJs" />
<script type="text/javascript" src="${select_esJs}"></script> -->
<spring:url value="/resources/js/libs/bootstrap-datepicker/locales/bootstrap-datepicker.{languagedt}.js" var="datePickerLoc">
    <spring:param name="languagedt" value="${lenguaje}" /></spring:url>
<script src="${datePickerLoc}"></script>
<spring:url value="/resources/js/views/handleDatePickers.js" var="handleDatePickers" />
<script src="${handleDatePickers}"></script>
<c:set var="notFound"><spring:message code="noResults" /></c:set>
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

<spring:url value="/resources/js/libs/notify.min.js" var="noty" />
<script type="text/javascript" src="${noty}"></script>

<spring:url value="/resources/js/libs/moment.js" var="moment" />
<script type="text/javascript" src="${moment}"></script>

<spring:url value="/resources/js/libs/sweetalert.min.js" var="sw" />
<script type="text/javascript" src="${sw}"></script>

<spring:url value="/resources/js/views/hemodinamica/datos.js" var="datosHemo" />
<script type="text/javascript" src="${datosHemo}"></script>

<spring:url value="/resources/js/libs/smartWizard/jquery.smartWizard.js" var="jqsw"/>
<script type="application/javascript" src="${jqsw}"></script>

<spring:url value="/resources/js/views/hemodinamica/dinamicatools.js" var="tools"/>
<script type="application/javascript" src="${tools}"></script>


<script type="text/javascript">
    $(document).ready(function(){
        $('#smartwizard').smartWizard({
            selected: 0,
            theme: 'arrows',
            justified: true,
            darkMode: true,
            lang: {  // Language variables
                next: 'Sig',
                previous: 'Prev'
            }
        });
        var endPoint = {
            saveHemoUrl: "${saveHemoUrl}",
            ListadoUrl:"${ListadoUrl}",
            GetRangeUrl:"${GetRangeUrl}",
            searchPartUrl: "${searchPartUrl}",
            notFound: "${notFound}"
        };
        datosHemo.init(endPoint);
        GuardarDinamica.init(endPoint);
        $("#parametro").focus();
        $('#peso').on('change', function() {
            if(isNaN(this.value)){
                this.value = "";
            }else{
                this.value = parseFloat(this.value).toFixed(2);
            }
        });
        $('#talla').on('change', function() {
            if(isNaN(this.value)){
                this.value = "";
            }else{
                this.value = parseFloat(this.value).toFixed(2);
            }
        });
        $("#sector").select2();
        $("#sector").on("change", function(){
           if(this.value == 18 ){
                $("#bar").fadeIn("slow");
               $("#barrioF").attr("required", "true");
            }else{
                $("#bar").fadeOut("slow");
               $("#barrioF").val("").attr("required", "false");
            }
        });

        $("#fie").prop("disabled", true);

        $('#numParametro').keyup(function (){
            this.value = (this.value + '').replace(/[^0-9]/g, '');
        });


        var getDate = function(input){
            return new Date(input.date.valueOf());
        }
        $("#fconsulta, #fie").datepicker({
            format: "dd/mm/yyyy",
            todayBtn:true,
            todayHighlight: true,
            autoclose: true,
            endDate: '-0d'
        });

        $("#fconsulta").on("changeDate", function(selected){
            $("#fie").prop("disabled", false);
            $("#fie").val("");
            $("#diasenf").val(0);
            $('#fie').datepicker('setEndDate', getDate(selected));
            var fConsulta = this.value;
            var fnac = $('#fecha').val();
            daysMonthsYearsInDates(fnac,fConsulta);
        });
        $("#fie").on("changeDate", function(){
            var f1 = $("#fconsulta").val();
            var f2= $("#fie").val();
            var diasEnfermo = restaFechas(f1,f2);
            if(diasEnfermo > 31){
                $.notify("Días Enfermo mayor a: "+ diasEnfermo, "warning");
                $("#diasenf").val(0);
                return false;
            }else{
                $("#diasenf").val(diasEnfermo);
                $.notify("Días Enfermo: "+ diasEnfermo, "info");
            }
        })
        restaFechas = function(f1,f2){
            var aFecha1 = f1.split("/");
            var aFecha2 = f2.split("/");
            var fFecha1 = Date.UTC(aFecha1[2],aFecha1[1]-1,aFecha1[0]);
            var fFecha2 = Date.UTC(aFecha2[2],aFecha2[1]-1,aFecha2[0]);
            var dif =  fFecha1 - fFecha2;
            var dias = Math.floor(dif / (1000 * 60 * 60 * 24));
            if(dias === NaN){
                dias = 0;
            }
            return dias + 1;
        }

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

    })
</script>
<jsp:include page="../fragments/bodyFooter.jsp" />
</body>
</html>
