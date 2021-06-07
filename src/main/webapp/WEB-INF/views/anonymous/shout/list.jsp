<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.link" path="link" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.reference" path="infoSheet.reference"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.deadline" path="infoSheet.deadline"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.totalPrice" path="infoSheet.totalPrice"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.paid" path="infoSheet.paid"/>


</acme:list>