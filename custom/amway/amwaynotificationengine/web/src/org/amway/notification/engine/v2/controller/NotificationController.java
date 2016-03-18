/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package org.amway.notification.engine.v2.controller;

import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionService;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.amway.notification.engine.cognito.CognitoIdentityManager;
import org.amway.notification.engine.dto.CognitoResultTokenDto;
import org.amway.notification.engine.dto.CustomerDeviceDto;
import org.amway.notification.engine.services.NotificationService;
import org.amway.notification.engine.sns.SNSMobilePush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityResult;


/**
 *
 */
@Controller
@RequestMapping(value = "/{baseSiteId}/app")
public class NotificationController extends BaseController
{


	@Inject
	@Named("notificationService")
	private NotificationService notificationService;


	@Inject
	@Named("snsMobilePush")
	private SNSMobilePush snsMobilePush;

	@Inject
	@Named("cognitoIdentityManager")
	private CognitoIdentityManager cognitoIdentityManager;

	@Autowired
	SessionService sessionService;



	@RequestMapping(value = "/pushNotification", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void pushNotification(@RequestParam(value = "id", required = true) final String userId,
			@RequestParam(value = "message", required = true) final String message,
			@RequestParam(value = "type", required = true) final String type,
			@RequestParam(value = "url", required = false) final String url)
	{
		final List<CustomerDeviceDto> userDetailsDtos = notificationService.getUserDetails(userId);
		try
		{
			snsMobilePush.pushMessages(userDetailsDtos, message, type, url);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
			return;
		}
		return;
	}


	@RequestMapping(value = "/getDeviceCognitoToken", method = RequestMethod.GET)
	@ResponseBody
	public CognitoResultTokenDto getOpenIdToken(@RequestParam(value = "id", required = true) final String userId,
			@RequestParam(value = "identityId", required = false) final String identityId)
	{

		final GetOpenIdTokenForDeveloperIdentityResult result = cognitoIdentityManager.getOpenIdTokenForDeveloperIdentity(userId,
				identityId);
		final CognitoResultTokenDto cognitoResultTokenDto = new CognitoResultTokenDto();
		cognitoResultTokenDto.setToken(result.getToken());
		cognitoResultTokenDto.setIdentityId(result.getIdentityId());
		return cognitoResultTokenDto;
	}

	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/addUserDevice", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void addUserDevice(final HttpServletRequest httpRequest)
	{
		final String userId = httpRequest.getParameter("id");
		final String registrationId = httpRequest.getParameter("registrationId");
		final String registrationType = httpRequest.getParameter("type");

		final Session session = sessionService.getCurrentSession();
		session.getSessionId();

		try
		{
			notificationService.updateUserDetails(userId, registrationId, registrationType);
		}
		catch (final Exception e)
		{
			return;
		}

		return;
	}

}
