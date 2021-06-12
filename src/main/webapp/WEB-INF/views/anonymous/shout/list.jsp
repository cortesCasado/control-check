<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.link" path="link" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.somp.code" path="somp.code"/>
	<acme:list-column code="anonymous.shout.list.label.somp.deadline" path="somp.deadline"/>
	<acme:list-column code="anonymous.shout.list.label.somp.budget" path="somp.budget"/>
	<acme:list-column code="anonymous.shout.list.label.somp.important" path="somp.important"/>
</acme:list>