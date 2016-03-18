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
package org.amway.notification.engine.conv;

import com.thoughtworks.xstream.converters.Converter;


/**
 * Abstract implementation of {@link RedirectableConverter} interface. Contains implementation of methods common to all
 * {@link RedirectableConverter} interface implementations.
 */
public abstract class AbstractRedirectableConverter implements RedirectableConverter
{
	private Converter targetConverter;

	@Override
	public void setTargetConverter(final Converter converter)
	{
		this.targetConverter = converter;

	}

	protected Converter getTargetConverter()
	{
		return targetConverter;
	}


}