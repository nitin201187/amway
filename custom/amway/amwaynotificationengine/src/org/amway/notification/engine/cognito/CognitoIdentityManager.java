/**
 *
 */
package org.amway.notification.engine.cognito;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;

import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityResult;


/**
 * @author nitin1786
 *
 */
public class CognitoIdentityManager
{
	@Inject
	@Qualifier("amazonCognitoClientWrapper")
	private AmazonCognitoClientWrapper amazonCognitoClientWrapper;

	public GetOpenIdTokenForDeveloperIdentityResult getOpenIdTokenForDeveloperIdentity(final String userId, final String identityId)
	{
		return amazonCognitoClientWrapper.getOpenIdTokenForDeveloperIdentity(userId, identityId);
	}
}
