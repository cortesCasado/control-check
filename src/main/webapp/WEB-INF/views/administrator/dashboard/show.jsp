<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<style>
	.bar {
	  fill: #aaa;
	  height: 21px;
	  transition: fill .3s ease;
	  cursor: pointer;
	  font-family: Helvetica, sans-serif;
	}
	
	.bar text {
	    fill: #000000;
	  }
	
	.chart:hover,
	.chart:focus {
	  .bar {
	    fill: #aaa;
	  }
	}
	
	.bar:hover,
	.bar:focus {
	  fill: red !important;
	}
	
	.bar:hover text,
	.bar:focus text {
	    fill: red;
	  }
	
	figcaption {
	  font-weight: bold;
	  color: #000;
	  margin-bottom: 20px;
	}
</style>

<h3>
	<acme:message code="administrator.dashboard.entities.title.shouts-indicators"/>
</h3>

<table class="table table-sm">
		
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.flaggedAs"/>
		</th>
		<td>
			<acme:print value="${flaggedAs}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.ratioOfShouts"/>
		</th>
		<td>
			<acme:print value="${ratioOfShouts}%"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.avgMoneyCurrency1"/>
		</th>
		<td>
			<acme:print value="${avgMoneyCurrency1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.dvtMoneyCurrency1"/>
		</th>
		<td>
			<acme:print value="${dvtMoneyCurrency1}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.avgMoneyCurrency2"/>
		</th>
		<td>
			<acme:print value="${avgMoneyCurrency2}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.dvtMoneyCurrency2"/>
		</th>
		<td>
			<acme:print value="${dvtMoneyCurrency2}"/>
		</td>
	</tr>
</table>



<h3>
		<acme:message code="administrator.dashboard.entities.title.task-indicators"/>
</h3>

<table class="table table-sm">
		
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPublicTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfPublicTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPrivateTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfPrivateTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfFinishedTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfNonFinishedTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfNonFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${avgExecutionPeriodTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.deviationOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${dvtExecutionPeriodTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${minExecutionPeriodTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.maxOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${maxExecutionPeriodTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${avgWorkloadTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.deviationOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${dvtWorkloadTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${minWorkloadTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.maxOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${maxWorkloadTasks}"/>
		</td>
	</tr>
</table>

<h3>
	<acme:message code="administrator.dashboard.entities.title.workplan-indicators"/>
</h3>

<table class="table table-sm">
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPublicWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfPublicWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPrivateWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfPrivateWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfFinishedWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfFinishedWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfNonFinishedWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfNonFinishedWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfWorkPlanPeriods"/>
		</th>
		<td>
			<acme:print value="${avgExecutionPeriodWorkplans}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.deviationOfWorkPlanPeriods"/>
		</th>
		<td>
			<acme:print value="${dvtExecutionPeriodWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfWorkPlanPeriods"/>
		</th>
		<td>
			<acme:print value="${minExecutionPeriodWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.maxOfWorkPlanPeriods"/>
		</th>
		<td>
			<acme:print value="${maxExecutionPeriodWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${avgWorkloadWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.deviationOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${dvtWorkloadWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${minWorkloadWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.maxOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${maxWorkloadWorkplans}"/>
		</td>
	</tr>
	</table>
<h2>
	<acme:message code="administrator.dashboard.entities.title.application-statuses"/>
</h2>

<div>
	<figcaption><acme:message code="administrator.dashboard.entities.title.figcaption"/></figcaption>
	<svg class="chart" width="1300" height="150" aria-labelledby="title desc" role="img">
	  <g class="bar">
	    <text x="0" y="20" dy=".35em"><acme:message code="administrator.dashboard.entities.label.numberOfWorkPlans"/>: ${totalNumberOfWorkplans}</text>
   	    <rect width="1000" height="40" x="300"></rect>
	  </g>
	  <g class="bar">
  	    <text x="0" y="70" dy=".35em"><acme:message code="administrator.dashboard.entities.label.numberOfPublishedWorkPlans"/>: ${totalNumberOfPublishedWorkplans}</text>
	    <rect width="${1000 * totalNumberOfPublishedWorkplans/totalNumberOfWorkplans}" height="40" x="300" y="50"></rect>
	  </g>
	  <g class="bar">
  	    <text x="0" y="120" dy=".35em"><acme:message code="administrator.dashboard.entities.label.numberOfNonPublishedWorkPlans"/>: ${totalNumberOfNonPublishedWorkplans}</text>
	    <rect width="${1000 * totalNumberOfNonPublishedWorkplans/totalNumberOfWorkplans}" height="40" x="300" y="100"></rect>
	  </g>
	</svg>
</div>