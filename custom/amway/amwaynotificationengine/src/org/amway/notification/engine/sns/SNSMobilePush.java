package org.amway.notification.engine.sns;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.amway.core.enums.DeviceTypeEnum;
import org.amway.notification.engine.dto.CustomerDeviceDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.cognitosync.model.Platform;
import com.amazonaws.services.sns.model.MessageAttributeValue;


public class SNSMobilePush
{

	@Inject
	@Qualifier("snsClientWrapper")
	private AmazonSNSClientWrapper snsClientWrapper;

	@Value("#{corePropertyConfigurer['google.server.api.key']}")
	private String serverAPIKey;

	@Value("#{corePropertyConfigurer['application.name']}")
	private String applicationName;


	public static final Map<Platform, Map<String, MessageAttributeValue>> attributesMap = new HashMap<Platform, Map<String, MessageAttributeValue>>();
	static
	{
		attributesMap.put(Platform.ADM, null);
		attributesMap.put(Platform.GCM, null);
		attributesMap.put(Platform.APNS, null);
		attributesMap.put(Platform.APNS_SANDBOX, null);
	}


	public void pushMessages(final List<CustomerDeviceDto> customerDeviceDto, final String message, final String type,
			final String url) throws IOException
	{
		try
		{
			for (final CustomerDeviceDto userDetailsDto : customerDeviceDto)
			{
				if (DeviceTypeEnum.GCM.equals(userDetailsDto.getRegistrationType()))
				{
					demoAndroidAppNotification(userDetailsDto.getDeviceEndPointArn(), userDetailsDto.getRegistrationId(), message,
							type, url);
				}
				else if (DeviceTypeEnum.APNS.equals(userDetailsDto.getRegistrationType()))
				{
					demoAppleAppNotification(userDetailsDto.getDeviceEndPointArn(), userDetailsDto.getRegistrationId(), message, type,
							url);
				}
			}
		}
		catch (final AmazonServiceException ase)
		{
			System.out.println(ase);
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon SNS, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		}
		catch (final AmazonClientException ace)
		{
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with SNS, such as not "
					+ "being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

	}

	public void demoAndroidAppNotification(final String deviceEndpointArn, final String registrationId, final String message,
			final String type, final String url)
	{
		snsClientWrapper.demoNotification(Platform.GCM, registrationId, attributesMap, deviceEndpointArn, message, type, url);

	}

	public void demoAppleAppNotification(final String deviceEndpointArn, final String registrationId, final String message,
			final String type, final String url)
	{
		snsClientWrapper.demoNotification(Platform.APNS, registrationId, attributesMap, deviceEndpointArn, message, type, url);

	}

	public String getPlatformEndPoint(final Platform platform, final String registrationType, final String registrationId,
			final String serverAPIKey, final String applicationName)
	{
		return snsClientWrapper.getPlatformEndPoint(platform, registrationType, serverAPIKey, registrationId, applicationName);
	}



}
