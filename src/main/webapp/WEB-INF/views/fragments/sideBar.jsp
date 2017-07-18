<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="sidebar">
    <nav class="sidebar-nav">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/" htmlEscape="true "/>"><i class="icon-speedometer"></i><spring:message code="dashboard" /></a>
            </li>
            <li class="nav-item nav-dropdown administracion">
	            <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-wrench"></i><spring:message code="admin" /></a>
	            <ul class="nav-dropdown-items">
	                <li class="nav-item users">
	                    <a class="nav-link" href="<spring:url value="/admin/users/" htmlEscape="true "/>"><i class="icon-people"></i><spring:message code="users" /></a>
	                </li>
                    <li class="nav-item versionLetters">
                        <a class="nav-link" href="<spring:url value="/admin/vcartas/" htmlEscape="true "/>"><i class="icon-docs"></i><spring:message code="versionLetters" /></a>
                    </li>
	            </ul>
	        </li>
	        <li class="nav-item nav-dropdown chfcasos">
                <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-notebook"></i><spring:message code="chfcasos" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item chfcasossearch">
                        <a class="nav-link" href="<spring:url value="/chf/editarcaso/" htmlEscape="true "/>"><i class="icon-home"></i><spring:message code="chfcasossearch" /></a>
                    </li>
                </ul>
            </li>
            <li class="nav-item nav-dropdown supervision">
                <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-eye"></i><spring:message code="superv" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item intensiveMonitoring">
                        <a class="nav-link" href="<spring:url value="/super/casacaso/" htmlEscape="true "/>"><i class="icon-location-pin"></i><spring:message code="intensiveMonitoring" /></a>
                    </li>
                </ul>
            </li>
            <li class="nav-item nav-dropdown laboratory">
                <a class="nav-link nav-dropdown-toggle" href="#"><i class="icon-chemistry"></i><spring:message code="laboratory" /></a>
                <ul class="nav-dropdown-items">
                    <li class="nav-item printcodes">
                        <a class="nav-link" href="<spring:url value="/lab/muestras/" htmlEscape="true "/>"><i class="icon-printer"></i><spring:message code="print.codes" /></a>
                    </li>
                </ul>
            </li>
	        <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/logout" htmlEscape="true" />"><i class="icon-lock"></i><spring:message code="logout" /></a>
            </li>
        </ul>
    </nav>
</div>