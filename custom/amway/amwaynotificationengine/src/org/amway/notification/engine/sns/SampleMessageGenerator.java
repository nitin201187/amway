package org.amway.notification.engine.sns;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


public class SampleMessageGenerator
{

	/*
	 * This message is delivered if a platform specific message is not specified for the end point. It must be set. It is
	 * received by the device as the value of the key "default".
	 */
	public static final String defaultMessage = "This is the default message";

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static enum Platform
	{
		// Apple Push Notification Service
		APNS,
		// Sandbox version of Apple Push Notification Service
		APNS_SANDBOX,
		// Amazon Device Messaging
		ADM,
		// Google Cloud Messaging
		GCM,
		// Baidu CloudMessaging Service
		BAIDU,
		// Windows Notification Service
		WNS,
		// Microsoft Push Notificaion Service
		MPNS;
	}

	public static String jsonify(final Object message)
	{
		try
		{
			return objectMapper.writeValueAsString(message);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			throw (RuntimeException) e;
		}
	}

	private static Map<String, String> getData(final String message, final String type, final String url)
	{
		final Map<String, String> payload = new HashMap<String, String>();
		payload.put("message", message);
		payload.put("type", type);
		payload.put("url", url);
		return payload;
	}


	public static String getSampleAndroidMessage(final String message, final String type, final String url)
	{
		final Map<String, Object> androidMessageMap = new HashMap<String, Object>();
		//androidMessageMap.put("collapse_key", "Welcome");
		androidMessageMap.put("data", getData(message, type, url));
		androidMessageMap.put("delay_while_idle", true);
		androidMessageMap.put("time_to_live", 125);
		//androidMessageMap.put("dry_run", false);
		return jsonify(androidMessageMap);
	}

	public static String getSampleAppleMessage(final String message, final String type, final String url)
	{
		final Map<String, Object> appleMessageMap = new HashMap<String, Object>();
		final Map<String, Object> appMessageMap = new HashMap<String, Object>();
		appMessageMap.put("alert", message);
		appMessageMap.put("type", type);
		appMessageMap.put("url", url);
		appMessageMap.put("badge", 9);
		appMessageMap.put("sound", "default");
		appleMessageMap.put("aps", appMessageMap);
		return jsonify(appleMessageMap);
	}

	public static String getSampleBaiduMessage()
	{
		final Map<String, Object> baiduMessageMap = new HashMap<String, Object>();
		baiduMessageMap.put("title", "New Notification Received from SNS");
		baiduMessageMap.put("description", "Hello World!");
		return jsonify(baiduMessageMap);
	}

	public static String getSampleWNSMessage()
	{
		final Map<String, Object> wnsMessageMap = new HashMap<String, Object>();
		wnsMessageMap.put("version", "1");
		wnsMessageMap.put("value", "23");
		return "<badge version=\"" + wnsMessageMap.get("version") + "\" value=\"" + wnsMessageMap.get("value") + "\"/>";
	}

	public static String getSampleMPNSMessage()
	{
		final Map<String, String> mpnsMessageMap = new HashMap<String, String>();
		mpnsMessageMap.put("count", "23");
		mpnsMessageMap.put("payload", "This is a tile notification");
		return "<?xml version=\"1.0\" encoding=\"utf-8\"?><wp:Notification xmlns:wp=\"WPNotification\"><wp:Tile><wp:Count>"
				+ mpnsMessageMap.get("count") + "</wp:Count><wp:Title>" + mpnsMessageMap.get("payload")
				+ "</wp:Title></wp:Tile></wp:Notification>";
	}
}