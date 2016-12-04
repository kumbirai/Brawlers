package com.kumbirai.golf.samples;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.shared.JavaScriptExtensionState;

/**
 * Shared state class for {@link AttributeExtension} communication from server
 * to client.
 */
public class AttributeExtensionState extends JavaScriptExtensionState
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	public Map<String, String> attributes = new HashMap<>();
}