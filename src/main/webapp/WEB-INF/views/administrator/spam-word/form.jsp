<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.spam-word.form.label.word" path="word" readonly="false"/> 
	<acme:form-submit code="administrator.spam-word.form.button.create" action="/administrator/spam-word/create"/>
  	<acme:form-return code="administrator.spam-word.form.button.return"/>
</acme:form>
