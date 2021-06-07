<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author" placeholder="John Davies"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text" placeholder="This is an example text"/>
	<acme:form-textbox code="anonymous.shout.form.label.link" path="link" placeholder="http://wwww.ebiblioteca.org"/>
	
	<acme:form-textbox code="anonymous.shout.form.label.infoSheet.reference" path="receipt.reference" placeholder="${referencePlaceholder}" />
<%-- 	<acme:form-moment code="anonymous.shout.form.label.infoSheet.deadline" path="receipt.deadline"/> --%>
	<acme:form-money code="anonymous.shout.form.label.infoSheet.totalPrice" path="receipt.totalPrice"/>
	<acme:form-checkbox code="anonymous.shout.form.label.infoSheet.paid" path="receipt.paid"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>