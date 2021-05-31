/*
 * AdministratorUserAccountController.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manager.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Manager;
import acme.entities.workplan.Workplan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/manager/workplan/")
public class ManagerWorkplanController extends AbstractController<Manager, Workplan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanListService	listService;

	@Autowired
	protected ManagerWorkplanUpdateService	updateService;

	@Autowired
	protected ManagerWorkplanCreateService	createService;

	@Autowired
	protected ManagerWorkplanShowService	showService;

	@Autowired
	protected ManagerWorkplanDeleteService	deleteService;
	
	@Autowired
	protected ManagerWorkplanDeleteTaskService	deleteTaskService;
	
	@Autowired
	protected ManagerWorkplanChangePrivacyService	changePrivacyService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST,   this.listService);
		super.addBasicCommand(BasicCommand.SHOW,   this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addCustomCommand(CustomCommand.DELETE_TASK_WORKPLAN, BasicCommand.DELETE, this.deleteTaskService);
		super.addCustomCommand(CustomCommand.CHANGE_PRIVACY, BasicCommand.DELETE, this.changePrivacyService);
	}

}
