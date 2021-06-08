<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.link" path="link" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.receipt.referenciaEx" path="receipt.referenciaEx"/>
	<acme:list-column code="anonymous.shout.list.label.receipt.deadlineEx" path="receipt.deadlineEx"/>
	<acme:list-column code="anonymous.shout.list.label.receipt.totalPriceEx" path="receipt.totalPriceEx"/>
	<acme:list-column code="anonymous.shout.list.label.receipt.paidEx" path="receipt.paidEx"/>
</acme:list>