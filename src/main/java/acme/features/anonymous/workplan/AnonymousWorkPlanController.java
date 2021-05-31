package acme.features.anonymous.workplan;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.workplan.Workplan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/workplan")
public class AnonymousWorkPlanController extends AbstractController<Anonymous, Workplan>{
	
	@Autowired
	protected AnonymousWorkPlanListServiceExecutionPeriod workPlanService;
	
	@Autowired
	protected AnonymousWorkPlanListServiceWorkLoad workPlanWorkloadService;
	
	@Autowired
	protected AnonymousWorkPlanShowService showService;
	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.workPlanService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_ALL, BasicCommand.LIST, this.workPlanWorkloadService);

	}

}