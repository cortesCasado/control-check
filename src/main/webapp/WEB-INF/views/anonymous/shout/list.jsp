<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.link" path="link" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.rareID" path="infoSheet.rareID"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.moment" path="infoSheet.moment"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.money" path="infoSheet.money"/>
	<acme:list-column code="anonymous.shout.list.label.infoSheet.flag" path="infoSheet.flag"/>


</acme:list>