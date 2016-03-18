package org.amway.notification.engine.services;


import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.amway.core.enums.DeviceTypeEnum;
import org.amway.core.model.CustomerDeviceModel;
import org.amway.notification.engine.constants.NotificationConstant;
import org.amway.notification.engine.dto.CustomerDeviceDto;
import org.amway.notification.engine.sns.SNSMobilePush;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.services.cognitosync.model.Platform;


@Named("notificationService")
public class NotificationServiceImpl implements NotificationService
{

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "modelService")
	private ModelService modelService;

	@Inject
	@Qualifier("snsMobilePush")
	private SNSMobilePush snsMobilePush;

	@Value("#{corePropertyConfigurer['google.server.api.key']}")
	private String serverAPIKey;

	@Value("#{corePropertyConfigurer['application.name']}")
	private String applicationName;

	@Value("#{corePropertyConfigurer['apple.certificate.key']}")
	private String appleCertificateKey;

	@Value("#{corePropertyConfigurer['apple.private.key']}")
	private String applePrivateKey;


	@Override
	public List<CustomerDeviceDto> getUserDetails(final String userId)
	{
		final CustomerModel customerModel = (CustomerModel) userService.getUserForUID(userId);
		List<CustomerDeviceDto> userDetailsDtos = null;
		if (customerModel != null)
		{
			for (final CustomerDeviceModel customerDevice : customerModel.getCustomerDevices())
			{
				final CustomerDeviceDto dto = new CustomerDeviceDto();
				if (userDetailsDtos == null)
				{
					userDetailsDtos = new ArrayList<CustomerDeviceDto>();
				}
				dto.setRegistrationId(customerDevice.getDeviceId());
				dto.setRegistrationType(customerDevice.getDeviceType());
				dto.setDeviceEndPointArn(customerDevice.getDeviceEndPointArn());
				userDetailsDtos.add(dto);
			}
		}

		return userDetailsDtos;

	}

	@Override
	public void updateUserDetails(final String userId, final String registrationId, final String registrationType)
	{
		final CustomerModel customerModel = (CustomerModel) userService.getUserForUID(userId);
		final Set<CustomerDeviceModel> customerDevices = customerModel.getCustomerDevices();
		final Set<CustomerDeviceModel> tempCustomerDevices = new HashSet<>(customerDevices);
		boolean alreadyPresent = false;
		for (final CustomerDeviceModel customerDeviceModel : customerDevices)
		{
			if (customerDeviceModel.getDeviceId().equals(registrationId))
			{
				alreadyPresent = true;
				break;
			}
		}

		if (!alreadyPresent)
		{
			final CustomerDeviceModel newRecord = new CustomerDeviceModel();
			newRecord.setDeviceId(registrationId);
			newRecord.setDeviceType(DeviceTypeEnum.valueOf(registrationType));
			String deviceEndPointArn = null;
			if (NotificationConstant.GCM.equalsIgnoreCase(registrationType))
			{
				deviceEndPointArn = snsMobilePush.getPlatformEndPoint(Platform.GCM, NotificationConstant.GCM, registrationId,
						serverAPIKey, applicationName);
			}
			else if (NotificationConstant.APNS.equalsIgnoreCase(registrationType))
			{
				deviceEndPointArn = snsMobilePush.getPlatformEndPoint(Platform.APNS, appleCertificateKey, registrationId,
						applePrivateKey, applicationName);
			}

			newRecord.setDeviceEndPointArn(deviceEndPointArn);
			try
			{
				tempCustomerDevices.add(newRecord);

			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}
			customerModel.setCustomerDevices(tempCustomerDevices);
			modelService.save(customerModel);
		}

	}

}
