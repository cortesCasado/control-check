<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author" placeholder="John Davies"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text" placeholder="This is an example text"/>
	<acme:form-textbox code="anonymous.shout.form.label.link" path="link" placeholder="http://wwww.ebiblioteca.org"/>
	
	<acme:form-textbox code="anonymous.shout.form.label.infoSheet.rareID" path="infoSheet.rareID" placeholder="${rareIdPlaceholder}" />
	<acme:form-moment code="anonymous.shout.form.label.infoSheet.moment" path="infoSheet.moment"/>
	<acme:form-money code="anonymous.shout.form.label.infoSheet.money" path="infoSheet.money"/>
	<acme:form-checkbox code="anonymous.shout.form.label.infoSheet.flag" path="infoSheet.flag"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>