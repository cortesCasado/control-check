<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>

	<acme:form-textbox code="manager.workplan.form.label.title"
		path="title" readonly="false" />

	<acme:message
		code="manager.workplan.form.label.suggested_execution_period_initial_date" />
	<acme:print value="${suggestedExecutionPeriod.initialDate}" />
	<acme:form-textbox
		code="manager.workplan.form.label.execution_period_initial_date"
		path="executionPeriod.initialDate" readonly="false" />

	<acme:message
		code="manager.workplan.form.label.suggested_execution_period_final_date" />
	<acme:print value="${suggestedExecutionPeriod.finalDate}" />
	<acme:form-textbox
		code="manager.workplan.form.label.execution_period_final_date"
		path="executionPeriod.finalDate" readonly="false" />

	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="manager.workplan.form.label.isPrivate"
			path="isPrivate" readonly="false" />
	</jstl:if>

	<jstl:if test="${command != 'create'}">
		<jstl:choose>
			<jstl:when test="${isPrivate}">
				<acme:form-submit
					code="manager.workplan.form.button.changePrivacytoPublic"
					action="/manager/workplan/change_privacy?id=${id}" />
				<br>
				<br>
			</jstl:when>

			<jstl:otherwise>
				<acme:form-submit code="manager.workplan.form.button.changePrivacytoPrivate" action="/manager/workplan/change_privacy?id=${id}" />
				<br><br>
			</jstl:otherwise>
		</jstl:choose>
		
		<acme:form-errors path="isPrivate"/><br>
	
		<table class="table table-sm">
			<tr>
				<th>
					<acme:message code="manager.workplan.form.label.tareasseleccionadas" />
				 	<jstl:out value="${workload}" />
				 	<acme:message code="manager.workplan.form.label.horas" />
				</th>
				<th>
					<acme:message code="manager.workplan.list.label.execution_period_initial_date" />
				</th>
				<th>
					<acme:message code="manager.workplan.list.label.execution_period_final_date" />
				</th>
				<th>
					<acme:message code="manager.workplan.form.label.privacy" />
				</th>
				<th>
					<acme:message code="manager.workplan.form.button.delete.actions" />
				</th>
			</tr>
			
			<jstl:forEach items="${allTasksAlreadySelected}" var="tsk">
				<tr>
					<td>
						<acme:print value="${tsk.title}" />
					</td>
					<td>
						<acme:print value="${tsk.executionPeriod.initialDate}"/>
					</td>
					<td>
						<acme:print value="${tsk.executionPeriod.finalDate}"/>
					</td>
					<td>
						<acme:message code="manager.workplan.form.label.${tsk.isPrivate ? 'private' : 'public'}" />
					</td>
					<td><acme:form-submit code="manager.workplan.form.button.delete.task"
							action="/manager/workplan/delete_task_workplan?workplanId=${id}&taskId=${tsk.id}" />
					</td>
				</tr>
			</jstl:forEach>
		</table>

		<acme:form-select code="manager.workplan.form.label.taskSelect"
			path="task">
			<acme:form-option code="" value="" />
			<jstl:forEach items="${allTasksAvailable}" var="tsk">
				<acme:form-option code="${tsk.title}" value="${tsk.id}" />
			</jstl:forEach>
		</acme:form-select>

	</jstl:if>

	<acme:form-submit test="${command != 'create'}"
		code="manager.workplan.form.button.update"
		action="/manager/workplan/update" />

	<acme:form-submit test="${command != 'create'}"
		code="manager.workplan.form.button.delete.workplan"
		action="/manager/workplan/delete" />

	<acme:form-submit test="${command == 'create'}"
		code="manager.workplan.form.button.create"
		action="/manager/workplan/create" />

	<acme:form-return code="manager.workplan.form.button.return" />
</acme:form>