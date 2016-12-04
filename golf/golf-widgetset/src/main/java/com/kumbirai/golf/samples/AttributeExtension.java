package com.kumbirai.golf.samples;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.ui.TextField;

/**
 * A JavaScript extension for adding arbitrary HTML attributes for components.
 */
@JavaScript("attribute_extension_connector.js")
public class AttributeExtension extends AbstractJavaScriptExtension
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose:
	 * <br>
	 * extend<br>
	 * <br>
	 * @param target<br>
	 */
	public void extend(TextField target)
	{
		super.extend(target);
	}

	/** (non-Javadoc)
	 * @see com.vaadin.server.AbstractJavaScriptExtension#getState()
	 */
	@Override
	protected AttributeExtensionState getState()
	{
		return (AttributeExtensionState) super.getState();
	}

	/**
	 * Purpose:
	 * <br>
	 * setAttribute<br>
	 * <br>
	 * @param attribute
	 * @param value<br>
	 */
	public void setAttribute(String attribute, String value)
	{
		getState().attributes.put(attribute, value);
	}
}