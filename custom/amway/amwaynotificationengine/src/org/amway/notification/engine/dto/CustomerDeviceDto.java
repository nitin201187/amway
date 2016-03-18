/**
 *
 */
package org.amway.notification.engine.dto;

import org.amway.core.enums.DeviceTypeEnum;


/**
 * @author nitin1786
 *
 */
public class CustomerDeviceDto
{
	private String registrationId;

	private DeviceTypeEnum registrationType;

	private String deviceEndPointArn;

	/**
	 * @return the registrationId
	 */
	public String getRegistrationId()
	{
		return registrationId;
	}

	/**
	 * @param registrationId
	 *           the registrationId to set
	 */
	public void setRegistrationId(final String registrationId)
	{
		this.registrationId = registrationId;
	}

	/**
	 * @return the registrationType
	 */
	public DeviceTypeEnum getRegistrationType()
	{
		return registrationType;
	}

	/**
	 * @param registrationType
	 *           the registrationType to set
	 */
	public void setRegistrationType(final DeviceTypeEnum registrationType)
	{
		this.registrationType = registrationType;
	}

	/**
	 * @return the deviceEndPointArn
	 */
	public String getDeviceEndPointArn()
	{
		return deviceEndPointArn;
	}

	/**
	 * @param deviceEndPointArn
	 *           the deviceEndPointArn to set
	 */
	public void setDeviceEndPointArn(final String deviceEndPointArn)
	{
		this.deviceEndPointArn = deviceEndPointArn;
	}
}
