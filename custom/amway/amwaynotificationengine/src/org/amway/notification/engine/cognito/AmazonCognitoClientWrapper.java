package org.amway.notification.engine.cognito;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityResult;


public class AmazonCognitoClientWrapper
{

	private AmazonCognitoIdentityClient cognitoClient;

	@PostConstruct
	public void initializeSnsClient() throws IOException
	{
		final Properties props = new Properties();
		props.load(getClass().getResourceAsStream("/configuration.properties"));
		final String accessKey = props.getProperty("accessKey");
		final String secretKey = props.getProperty("secretKey");
		final AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
		final AmazonCognitoIdentityClient cognitoClient = new AmazonCognitoIdentityClient(awsCredentials);
		cognitoClient.setEndpoint("https://sns.us-west-2.amazonaws.com");
		cognitoClient.setRegion(Region.getRegion(Regions.AP_NORTHEAST_1));
		this.cognitoClient = cognitoClient;
	}

	public GetOpenIdTokenForDeveloperIdentityResult getOpenIdTokenForDeveloperIdentity(final String userId, final String identityId)
	{
		final Map<String, String> logins = new HashMap<String, String>();
		logins.put("sea.amway.notificationapp", userId);
		final GetOpenIdTokenForDeveloperIdentityRequest request = new GetOpenIdTokenForDeveloperIdentityRequest();
		request.setIdentityPoolId("ap-northeast-1:1564e429-b4bb-4a81-9dec-4fb66b3a7775");
		request.setTokenDuration(Long.parseLong("86399"));
		request.setLogins(logins);
		if (!StringUtils.isEmpty(identityId))
		{
			request.setIdentityId(identityId);
		}
		final GetOpenIdTokenForDeveloperIdentityResult result = cognitoClient.getOpenIdTokenForDeveloperIdentity(request);
		return result;
	}


}
