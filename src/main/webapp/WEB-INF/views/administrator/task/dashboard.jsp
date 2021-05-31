<%--
- dashboard.jsp
-
- Copyright (C) 2012-2021.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
<%-- 	<acme:list-column code="administrator.task.dasboard.task" path=""/> --%>
	<acme:list-column code="administrator.task.dashboard.private" path="isPrivate"/>
	<acme:list-column code="administrator.task.dashboard.public" path="isPublic"/>
</acme:list>

<acme:list>
<%-- 	<acme:list-column code="administrator.task.dasboard.task" path=""/> --%>
	<acme:list-column code="administrator.task.dashboard.finished" path="finished"/>
	<acme:list-column code="administrator.task.dashboard.nonfinished" path="nonFinished"/>
</acme:list>

<acme:list>
<%-- 	<acme:list-column code="administrator.task.dasboard.workload" path=""/> --%>
	<acme:list-column code="administrator.task.dashboard.average" path="avgWorkload"/>
	<acme:list-column code="administrator.task.dashboard.deviation" path="dvtWorkload"/>
	<acme:list-column code="administrator.task.dashboard.minimum" path="minWorkload"/>
	<acme:list-column code="administrator.task.dashboard.maximum" path="maxWorkload"/>
</acme:list>

<acme:list>
<%-- 	<acme:list-column code="administrator.task.dasboard.executionperiod" path=""/> --%>
	<acme:list-column code="administrator.task.dashboard.average" path="avgExecutionPeriod"/>
	<acme:list-column code="administrator.task.dashboard.deviation" path="dvtExecutionPeriod"/>
	<acme:list-column code="administrator.task.dashboard.minimum" path="minExecutionPeriod"/>
	<acme:list-column code="administrator.task.dashboard.maximum" path="maxExecutionPeriod"/>
</acme:list>








