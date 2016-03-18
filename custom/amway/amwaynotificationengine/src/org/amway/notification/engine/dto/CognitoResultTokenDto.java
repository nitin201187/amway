/**
 *
 */
package org.amway.notification.engine.dto;


/**
 * @author nitin1786
 *
 */
public class CognitoResultTokenDto implements java.io.Serializable
{
	private String identityId;

	private String token;

	/**
	 *
	 */
	public CognitoResultTokenDto()
	{
		// YTODO Auto-generated constructor stub
	}

	/**
	 * @return the identityId
	 */
	public String getIdentityId()
	{
		return identityId;
	}

	/**
	 * @param identityId
	 *           the identityId to set
	 */
	public void setIdentityId(final String identityId)
	{
		this.identityId = identityId;
	}

	/**
	 * @return the token
	 */
	public String getToken()
	{
		return token;
	}

	/**
	 * @param token
	 *           the token to set
	 */
	public void setToken(final String token)
	{
		this.token = token;
	}
}
