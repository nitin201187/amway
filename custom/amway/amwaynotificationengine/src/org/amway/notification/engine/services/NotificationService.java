package org.amway.notification.engine.services;

import java.util.List;

import org.amway.notification.engine.dto.CustomerDeviceDto;


public interface NotificationService
{

	public List<CustomerDeviceDto> getUserDetails(String userId);

	public void updateUserDetails(String userId, String registrationId, String registrationType);
}
