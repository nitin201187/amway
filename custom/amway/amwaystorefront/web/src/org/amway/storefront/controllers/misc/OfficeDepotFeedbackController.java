/**
 *
 */
package org.amway.storefront.controllers.misc;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.amway.notification.engine.dto.ActionTypeDto;
import org.amway.notification.engine.dto.CustomerDeviceDto;
import org.amway.notification.engine.services.NotificationService;
import org.amway.notification.engine.sns.SNSMobilePush;
import org.amway.storefront.forms.OfficeDepotFeedbackForm;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author ruby
 *
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/feedbackPage")
public class OfficeDepotFeedbackController extends AbstractPageController
{
	protected static final Logger LOG = Logger.getLogger(OfficeDepotFeedbackController.class);

	@Inject
	@Named("notificationService")
	private NotificationService notificationService;


	@Inject
	@Named("snsMobilePush")
	private SNSMobilePush snsMobilePush;



	@RequestMapping(method = RequestMethod.GET)
	public String showCart(final Model model) throws CMSItemNotFoundException, CommerceCartModificationException
	{
		final Collection<ActionTypeDto> actionTypes = new ArrayList<>();
		final ActionTypeDto actionTypeDto1 = new ActionTypeDto();
		actionTypeDto1.setCode("information");
		actionTypeDto1.setName("information");
		final ActionTypeDto actionTypeDto2 = new ActionTypeDto();
		actionTypeDto2.setCode("action");
		actionTypeDto2.setName("action");
		actionTypes.add(actionTypeDto1);
		actionTypes.add(actionTypeDto2);
		final OfficeDepotFeedbackForm feedbackForm = new OfficeDepotFeedbackForm();
		model.addAttribute("actionTypes", actionTypes);
		model.addAttribute("feedbackForm", feedbackForm);
		return getRendererInformation(model);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveFeedback(final Model model, @ModelAttribute("feedbackForm") final OfficeDepotFeedbackForm feedbackForm,
			final BindingResult bindingResult) throws CMSItemNotFoundException
	{

		try
		{
			final List<CustomerDeviceDto> userDetailsDtos = notificationService.getUserDetails(feedbackForm.getUserId());
			snsMobilePush.pushMessages(userDetailsDtos, feedbackForm.getMessage(), feedbackForm.getAction(), feedbackForm.getUrl());
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		catch (final Exception e1)
		{
			e1.printStackTrace();
		}

		final Collection<ActionTypeDto> actionTypes = new ArrayList<>();
		final ActionTypeDto actionTypeDto1 = new ActionTypeDto();
		actionTypeDto1.setCode("information");
		actionTypeDto1.setName("information");
		final ActionTypeDto actionTypeDto2 = new ActionTypeDto();
		actionTypeDto2.setCode("action");
		actionTypeDto2.setName("action");
		actionTypes.add(actionTypeDto1);
		actionTypes.add(actionTypeDto2);
		model.addAttribute("actionTypes", actionTypes);
		return getRendererInformation(model);
	}



	private String getRendererInformation(final Model model) throws CMSItemNotFoundException
	{

		storeCmsPageInModel(model, getContentPageForLabelOrId("feedbackPage"));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId("feedbackPage"));
		//		return ControllerConstants.Views.Pages.Misc.FeedbackPage;
		return getViewForPage(model);
	}


	//
	//
	//	@RequestMapping(value = "/addNotification", method = RequestMethod.GET)
	//	public String addPushNotification()
	//	{
	//		return REDIRECT_PREFIX + "/pushNotification";
	//	}
	//
	//
	//	@RequestMapping(value = "/pushNotification", method = RequestMethod.POST)
	//	@ResponseStatus(value = HttpStatus.ACCEPTED)
	//	public void pushNotification(@RequestParam(value = "id", required = true) final String userId,
	//			@RequestParam(value = "message", required = true) final String message,
	//			@RequestParam(value = "type", required = true) final String type,
	//			@RequestParam(value = "url", required = false) final String url)
	//	{
	//		final List<CustomerDeviceDto> userDetailsDtos = notificationService.getUserDetails(userId);
	//		try
	//		{
	//			snsMobilePush.pushMessages(userDetailsDtos, message, type, url);
	//		}
	//		catch (final IOException e)
	//		{
	//			e.printStackTrace();
	//			return;
	//		}
	//		return;
	//	}
}
