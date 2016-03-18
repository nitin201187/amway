package org.amway.notification.engine.sns;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitosync.model.Platform;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import com.amazonaws.services.sns.model.CreatePlatformApplicationResult;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointResult;
import com.amazonaws.services.sns.model.GetEndpointAttributesRequest;
import com.amazonaws.services.sns.model.GetEndpointAttributesResult;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SetEndpointAttributesRequest;


public class AmazonSNSClientWrapper
{

	private AmazonSNS snsClient;

	@PostConstruct
	public void initializeSnsClient() throws IOException
	{
		final Properties props = new Properties();
		props.load(getClass().getResourceAsStream("/configuration.properties"));
		final String accessKey = props.getProperty("accessKey");
		final String secretKey = props.getProperty("secretKey");
		final AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		final AmazonSNS sns = new AmazonSNSClient(awsCredentials);
		sns.setEndpoint("https://sns.us-west-2.amazonaws.com");
		this.snsClient = sns;
	}

	private CreatePlatformApplicationResult createPlatformApplication(final String applicationName, final Platform platform,
			final String principal, final String credential)
	{
		final CreatePlatformApplicationRequest platformApplicationRequest = new CreatePlatformApplicationRequest();
		final Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("PlatformPrincipal", principal);
		attributes.put("PlatformCredential", credential);
		platformApplicationRequest.setAttributes(attributes);
		platformApplicationRequest.setName(applicationName);
		platformApplicationRequest.setPlatform(platform.name());
		return snsClient.createPlatformApplication(platformApplicationRequest);
	}

	private CreatePlatformEndpointResult createPlatformEndpoint(final String customData, final String platformToken,
			final String applicationArn)
	{
		final CreatePlatformEndpointRequest platformEndpointRequest = new CreatePlatformEndpointRequest();
		platformEndpointRequest.setCustomUserData(customData);
		final String token = platformToken;
		platformEndpointRequest.setToken(token);
		platformEndpointRequest.setPlatformApplicationArn(applicationArn);
		return snsClient.createPlatformEndpoint(platformEndpointRequest);
	}


	private PublishResult publish(final String endpointArn, final Platform platform,
			final Map<Platform, Map<String, MessageAttributeValue>> attributesMap, final String message, final String type,
			final String url)
	{
		final PublishRequest publishRequest = new PublishRequest();
		final Map<String, MessageAttributeValue> notificationAttributes = getValidNotificationAttributes(attributesMap
				.get(platform));
		if (notificationAttributes != null && !notificationAttributes.isEmpty())
		{
			publishRequest.setMessageAttributes(notificationAttributes);
		}
		publishRequest.setMessageStructure("json");
		// If the message attributes are not set in the requisite method,
		// notification is sent with default attributes
		String completeMessage = getPlatformSampleMessage(platform, message, type, url);
		final Map<String, String> messageMap = new HashMap<String, String>();
		messageMap.put(platform.name(), completeMessage);
		completeMessage = SampleMessageGenerator.jsonify(messageMap);
		// For direct publish to mobile end points, topicArn is not relevant.
		publishRequest.setTargetArn(endpointArn);

		// Display the message that will be sent to the endpoint/
		System.out.println("{Message Body: " + completeMessage + "}");
		final StringBuilder builder = new StringBuilder();
		builder.append("{Message Attributes: ");
		for (final Map.Entry<String, MessageAttributeValue> entry : notificationAttributes.entrySet())
		{
			builder.append("(\"" + entry.getKey() + "\": \"" + entry.getValue().getStringValue() + "\"),");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append("}");
		System.out.println(builder.toString());

		publishRequest.setMessage(completeMessage);
		return snsClient.publish(publishRequest);
	}

	protected String getPlatformEndPoint(final Platform platform, final String principal, final String credential,
			final String platformToken, final String applicationName)
	{
		// Create Platform Application. This corresponds to an app on a
		// platform.
		final CreatePlatformApplicationResult platformApplicationResult = createPlatformApplication(applicationName, platform,
				principal, credential);
		System.out.println(platformApplicationResult);

		// The Platform Application Arn can be used to uniquely identify the
		// Platform Application.
		final String platformApplicationArn = platformApplicationResult.getPlatformApplicationArn();

		// Create an Endpoint. This corresponds to an app on a device.
		final CreatePlatformEndpointResult platformEndpointResult = createPlatformEndpoint(
				"CustomData - Useful to store endpoint specific data", platformToken, platformApplicationArn);
		return platformEndpointResult.getEndpointArn();
	}

	public void demoNotification(final Platform platform, final String registrationId,
			final Map<Platform, Map<String, MessageAttributeValue>> attrsMap, final String deviceEndpointArn, final String message,
			final String type, final String url)
	{
		final GetEndpointAttributesRequest geaReq = new GetEndpointAttributesRequest().withEndpointArn(deviceEndpointArn);
		final GetEndpointAttributesResult geaRes = snsClient.getEndpointAttributes(geaReq);
		final boolean updateNeeded = !geaRes.getAttributes().get("Token").equals(registrationId)
				|| !geaRes.getAttributes().get("Enabled").equalsIgnoreCase("true");

		if (updateNeeded)
		{
			final Map attribs = new HashMap();
			attribs.put("Token", registrationId);
			attribs.put("Enabled", "true");
			final SetEndpointAttributesRequest saeReq = new SetEndpointAttributesRequest().withEndpointArn(deviceEndpointArn)
					.withAttributes(attribs);
			snsClient.setEndpointAttributes(saeReq);
		}

		final PublishResult publishResult = publish(deviceEndpointArn, platform, attrsMap, message, type, url);
		System.out.println("Published! \n{MessageId=" + publishResult.getMessageId() + "}");
	}

	private String getPlatformSampleMessage(final Platform platform, final String message, final String type, final String url)
	{
		switch (platform)
		{
			case GCM:
				return SampleMessageGenerator.getSampleAndroidMessage(message, type, url);
			case APNS:
				return SampleMessageGenerator.getSampleAppleMessage(message, type, url);
			default:
				throw new IllegalArgumentException("Platform not supported : " + platform.name());
		}
	}

	public static Map<String, MessageAttributeValue> getValidNotificationAttributes(
			final Map<String, MessageAttributeValue> notificationAttributes)
	{
		final Map<String, MessageAttributeValue> validAttributes = new HashMap<String, MessageAttributeValue>();

		if (notificationAttributes == null)
		{
			return validAttributes;
		}

		for (final Map.Entry<String, MessageAttributeValue> entry : notificationAttributes.entrySet())
		{
			if (!SnsStringUtil.isBlank(entry.getValue().getStringValue()))
			{
				validAttributes.put(entry.getKey(), entry.getValue());
			}
		}
		return validAttributes;
	}

}
