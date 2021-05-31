<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.spam.form.title"/>
</h2>

<acme:form>
	<acme:form-textbox code="administrator.spam.form.label.threshold" path="threshold"/>
	<acme:form-submit code="administrator.spam.form.button.update" action="/administrator/spam/update"/>
	
	<h4>
		<acme:message code="administrator.spam.form.label.spamWords"/>
	</h4>	
	<table class="table table-sm">
		<jstl:forEach items = "${spamWords}" var = "sw">
		<tr>
			<td><acme:print value="${sw.word}"/></td>
			<td>
				<acme:form-submit code="administrator.spamword.form.button.delete" action="/administrator/spam-word/delete?id=${sw.id}"/>
			</td> 
		</tr>
		</jstl:forEach>
	</table>
	
	<acme:form-return code="administrator.spamword.form.button.create" action="/administrator/spam-word/create"/>
  	<acme:form-return code="administrator.spam.form.button.return"/>
</acme:form>
