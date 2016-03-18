/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 17 Mar, 2016 10:23:00 PM                    ---
 * ----------------------------------------------------------------
 */
package org.amway.core.jalo;

import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.amway.core.constants.AmwayCoreConstants;

/**
 * Generated class for type {@link org.amway.core.jalo.CustomerDevice CustomerDevice}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCustomerDevice extends GenericItem
{
	/** Qualifier of the <code>CustomerDevice.deviceId</code> attribute **/
	public static final String DEVICEID = "deviceId";
	/** Qualifier of the <code>CustomerDevice.deviceType</code> attribute **/
	public static final String DEVICETYPE = "deviceType";
	/** Qualifier of the <code>CustomerDevice.deviceEndPointArn</code> attribute **/
	public static final String DEVICEENDPOINTARN = "deviceEndPointArn";
	/** Qualifier of the <code>CustomerDevice.customerPOS</code> attribute **/
	public static final String CUSTOMERPOS = "customerPOS";
	/** Qualifier of the <code>CustomerDevice.customer</code> attribute **/
	public static final String CUSTOMER = "customer";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n CUSTOMER's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedCustomerDevice> CUSTOMERHANDLER = new BidirectionalOneToManyHandler<GeneratedCustomerDevice>(
	AmwayCoreConstants.TC.CUSTOMERDEVICE,
	false,
	"customer",
	"customerPOS",
	true,
	true,
	CollectionType.SET
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(DEVICEID, AttributeMode.INITIAL);
		tmp.put(DEVICETYPE, AttributeMode.INITIAL);
		tmp.put(DEVICEENDPOINTARN, AttributeMode.INITIAL);
		tmp.put(CUSTOMERPOS, AttributeMode.INITIAL);
		tmp.put(CUSTOMER, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		CUSTOMERHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.customer</code> attribute.
	 * @return the customer
	 */
	public Customer getCustomer(final SessionContext ctx)
	{
		return (Customer)getProperty( ctx, CUSTOMER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.customer</code> attribute.
	 * @return the customer
	 */
	public Customer getCustomer()
	{
		return getCustomer( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.customer</code> attribute. 
	 * @param value the customer
	 */
	public void setCustomer(final SessionContext ctx, final Customer value)
	{
		CUSTOMERHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.customer</code> attribute. 
	 * @param value the customer
	 */
	public void setCustomer(final Customer value)
	{
		setCustomer( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.customerPOS</code> attribute.
	 * @return the customerPOS
	 */
	 Integer getCustomerPOS(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, CUSTOMERPOS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.customerPOS</code> attribute.
	 * @return the customerPOS
	 */
	 Integer getCustomerPOS()
	{
		return getCustomerPOS( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.customerPOS</code> attribute. 
	 * @return the customerPOS
	 */
	 int getCustomerPOSAsPrimitive(final SessionContext ctx)
	{
		Integer value = getCustomerPOS( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.customerPOS</code> attribute. 
	 * @return the customerPOS
	 */
	 int getCustomerPOSAsPrimitive()
	{
		return getCustomerPOSAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.customerPOS</code> attribute. 
	 * @param value the customerPOS
	 */
	 void setCustomerPOS(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, CUSTOMERPOS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.customerPOS</code> attribute. 
	 * @param value the customerPOS
	 */
	 void setCustomerPOS(final Integer value)
	{
		setCustomerPOS( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.customerPOS</code> attribute. 
	 * @param value the customerPOS
	 */
	 void setCustomerPOS(final SessionContext ctx, final int value)
	{
		setCustomerPOS( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.customerPOS</code> attribute. 
	 * @param value the customerPOS
	 */
	 void setCustomerPOS(final int value)
	{
		setCustomerPOS( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.deviceEndPointArn</code> attribute.
	 * @return the deviceEndPointArn
	 */
	public String getDeviceEndPointArn(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEVICEENDPOINTARN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.deviceEndPointArn</code> attribute.
	 * @return the deviceEndPointArn
	 */
	public String getDeviceEndPointArn()
	{
		return getDeviceEndPointArn( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.deviceEndPointArn</code> attribute. 
	 * @param value the deviceEndPointArn
	 */
	public void setDeviceEndPointArn(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEVICEENDPOINTARN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.deviceEndPointArn</code> attribute. 
	 * @param value the deviceEndPointArn
	 */
	public void setDeviceEndPointArn(final String value)
	{
		setDeviceEndPointArn( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.deviceId</code> attribute.
	 * @return the deviceId
	 */
	public String getDeviceId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DEVICEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.deviceId</code> attribute.
	 * @return the deviceId
	 */
	public String getDeviceId()
	{
		return getDeviceId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.deviceId</code> attribute. 
	 * @param value the deviceId
	 */
	public void setDeviceId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DEVICEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.deviceId</code> attribute. 
	 * @param value the deviceId
	 */
	public void setDeviceId(final String value)
	{
		setDeviceId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.deviceType</code> attribute.
	 * @return the deviceType
	 */
	public EnumerationValue getDeviceType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, DEVICETYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerDevice.deviceType</code> attribute.
	 * @return the deviceType
	 */
	public EnumerationValue getDeviceType()
	{
		return getDeviceType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.deviceType</code> attribute. 
	 * @param value the deviceType
	 */
	public void setDeviceType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, DEVICETYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerDevice.deviceType</code> attribute. 
	 * @param value the deviceType
	 */
	public void setDeviceType(final EnumerationValue value)
	{
		setDeviceType( getSession().getSessionContext(), value );
	}
	
}
