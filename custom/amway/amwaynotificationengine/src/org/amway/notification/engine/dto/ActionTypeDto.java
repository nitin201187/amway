/**
 *
 */
package org.amway.notification.engine.dto;

/**
 * @author nitin1786
 *
 */
public class ActionTypeDto implements java.io.Serializable
{

	/**
	 * <i>Generated property</i> for <code>TitleData.code</code> property defined at extension
	 * <code>commercefacades</code>.
	 */
	private String code;
	/**
	 * <i>Generated property</i> for <code>TitleData.name</code> property defined at extension
	 * <code>commercefacades</code>.
	 */
	private String name;

	public ActionTypeDto()
	{
		// default constructor
	}


	public void setCode(final String code)
	{
		this.code = code;
	}


	public String getCode()
	{
		return code;
	}


	public void setName(final String name)
	{
		this.name = name;
	}


	public String getName()
	{
		return name;
	}

}