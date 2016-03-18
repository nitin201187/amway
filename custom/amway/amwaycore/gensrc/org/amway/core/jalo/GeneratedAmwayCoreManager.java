/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 17 Mar, 2016 10:23:00 PM                    ---
 * ----------------------------------------------------------------
 */
package org.amway.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.util.OneToManyHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.amway.core.constants.AmwayCoreConstants;
import org.amway.core.jalo.ApparelProduct;
import org.amway.core.jalo.ApparelSizeVariantProduct;
import org.amway.core.jalo.ApparelStyleVariantProduct;
import org.amway.core.jalo.CustomerDevice;
import org.amway.core.jalo.ElectronicsColorVariantProduct;

/**
 * Generated class for type <code>AmwayCoreManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedAmwayCoreManager extends Extension
{
	/**
	* {@link OneToManyHandler} for handling 1:n CUSTOMERDEVICES's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<CustomerDevice> CUSTOMERDEVICERELATIONCUSTOMERDEVICESHANDLER = new OneToManyHandler<CustomerDevice>(
	AmwayCoreConstants.TC.CUSTOMERDEVICE,
	false,
	"customer",
	"customerPOS",
	true,
	true,
	CollectionType.SET
	);
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public ApparelProduct createApparelProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmwayCoreConstants.TC.APPARELPRODUCT );
			return (ApparelProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelProduct createApparelProduct(final Map attributeValues)
	{
		return createApparelProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public ApparelSizeVariantProduct createApparelSizeVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmwayCoreConstants.TC.APPARELSIZEVARIANTPRODUCT );
			return (ApparelSizeVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelSizeVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelSizeVariantProduct createApparelSizeVariantProduct(final Map attributeValues)
	{
		return createApparelSizeVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public ApparelStyleVariantProduct createApparelStyleVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmwayCoreConstants.TC.APPARELSTYLEVARIANTPRODUCT );
			return (ApparelStyleVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelStyleVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelStyleVariantProduct createApparelStyleVariantProduct(final Map attributeValues)
	{
		return createApparelStyleVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public CustomerDevice createCustomerDevice(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmwayCoreConstants.TC.CUSTOMERDEVICE );
			return (CustomerDevice)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CustomerDevice : "+e.getMessage(), 0 );
		}
	}
	
	public CustomerDevice createCustomerDevice(final Map attributeValues)
	{
		return createCustomerDevice( getSession().getSessionContext(), attributeValues );
	}
	
	public ElectronicsColorVariantProduct createElectronicsColorVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AmwayCoreConstants.TC.ELECTRONICSCOLORVARIANTPRODUCT );
			return (ElectronicsColorVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ElectronicsColorVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ElectronicsColorVariantProduct createElectronicsColorVariantProduct(final Map attributeValues)
	{
		return createElectronicsColorVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.customerDevices</code> attribute.
	 * @return the customerDevices
	 */
	public Set<CustomerDevice> getCustomerDevices(final SessionContext ctx, final Customer item)
	{
		return (Set<CustomerDevice>)CUSTOMERDEVICERELATIONCUSTOMERDEVICESHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.customerDevices</code> attribute.
	 * @return the customerDevices
	 */
	public Set<CustomerDevice> getCustomerDevices(final Customer item)
	{
		return getCustomerDevices( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.customerDevices</code> attribute. 
	 * @param value the customerDevices
	 */
	public void setCustomerDevices(final SessionContext ctx, final Customer item, final Set<CustomerDevice> value)
	{
		CUSTOMERDEVICERELATIONCUSTOMERDEVICESHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.customerDevices</code> attribute. 
	 * @param value the customerDevices
	 */
	public void setCustomerDevices(final Customer item, final Set<CustomerDevice> value)
	{
		setCustomerDevices( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to customerDevices. 
	 * @param value the item to add to customerDevices
	 */
	public void addToCustomerDevices(final SessionContext ctx, final Customer item, final CustomerDevice value)
	{
		CUSTOMERDEVICERELATIONCUSTOMERDEVICESHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to customerDevices. 
	 * @param value the item to add to customerDevices
	 */
	public void addToCustomerDevices(final Customer item, final CustomerDevice value)
	{
		addToCustomerDevices( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from customerDevices. 
	 * @param value the item to remove from customerDevices
	 */
	public void removeFromCustomerDevices(final SessionContext ctx, final Customer item, final CustomerDevice value)
	{
		CUSTOMERDEVICERELATIONCUSTOMERDEVICESHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from customerDevices. 
	 * @param value the item to remove from customerDevices
	 */
	public void removeFromCustomerDevices(final Customer item, final CustomerDevice value)
	{
		removeFromCustomerDevices( getSession().getSessionContext(), item, value );
	}
	
	@Override
	public String getName()
	{
		return AmwayCoreConstants.EXTENSIONNAME;
	}
	
}
