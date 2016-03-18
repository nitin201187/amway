/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package org.amway.notification.engine.v1.controller;

import de.hybris.platform.commercewebservicescommons.utils.YSanitizer;
import org.amway.notification.engine.exceptions.UnknownResourceException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DefaultController
{
	@RequestMapping
	public void defaultRequest(final HttpServletRequest request)
	{
		throw new UnknownResourceException("There is no resource for path " + YSanitizer.sanitize(request.getRequestURI()));
	}
}
