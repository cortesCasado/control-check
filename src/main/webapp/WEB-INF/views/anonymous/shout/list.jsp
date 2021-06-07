<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.link" path="link" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.reference" path="receipt.reference"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.deadline" path="receipt.deadline"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.totalPrice" path="receipt.totalPrice"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.paid" path="receipt.paid"/>
</acme:list>