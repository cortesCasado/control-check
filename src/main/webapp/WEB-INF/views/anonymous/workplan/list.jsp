<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list >
		
	<acme:list-column code="anonymous.workplan.list.label.title" path="title" width="20%" sortable="false" />
	<acme:list-column code="anonymous.workplan.list.label.execution_period_final_date" path="executionPeriod.finalDate" width="20%"/>
	<acme:list-column code="anonymous.workplan.list.label.execution_period_initial_date" path="executionPeriod.initialDate" width="20%"/>
	<acme:list-column code="anonymous.workplan.list.label.workload" path="workload" width="20%"/>	

</acme:list>