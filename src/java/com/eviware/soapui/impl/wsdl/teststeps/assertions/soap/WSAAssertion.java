/*
 *  soapUI, copyright (C) 2004-2008 eviware.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */

package com.eviware.soapui.impl.wsdl.teststeps.assertions.soap;

import com.eviware.soapui.config.TestAssertionConfig;
import com.eviware.soapui.impl.wsdl.WsdlRequest;
import com.eviware.soapui.impl.wsdl.submit.WsdlMessageExchange;
import com.eviware.soapui.impl.wsdl.support.wsa.WsaValidator;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlMessageAssertion;
import com.eviware.soapui.impl.wsdl.teststeps.assertions.AbstractTestAssertionFactory;
import com.eviware.soapui.model.iface.MessageExchange;
import com.eviware.soapui.model.iface.SubmitContext;
import com.eviware.soapui.model.testsuite.*;
import com.eviware.soapui.model.testsuite.AssertionError;

/**
 * Assertion for verifying that WS-Addressing processing was ok
 * 
 * @author dragica.soldo
 */

public class WSAAssertion extends WsdlMessageAssertion implements ResponseAssertion, RequestAssertion
{
	public static final String ID = "WS Addressing Assertion";
	public static final String LABEL = "WS-Addressing";
	/**
	 * Constructor for our assertion.
	 * 
	 * @param assertionConfig
	 * @param modelItem
	 */
	public WSAAssertion( TestAssertionConfig assertionConfig, Assertable modelItem )
	{
		super( assertionConfig, modelItem, false, false, false, true );
	}

	public static class Factory extends AbstractTestAssertionFactory
	{
		public Factory()
		{
			super(WSAAssertion.ID, WSAAssertion.LABEL, WSAAssertion.class, WsdlRequest.class);
		}
	}
	@Override
	protected String internalAssertRequest(MessageExchange messageExchange, SubmitContext context)
			throws AssertionException
	{
	    try
	    {
	    	new WsaValidator( (WsdlMessageExchange) messageExchange ).validateWsAddressingRequest();
	    }
	    catch (Exception e)
	    {
	       throw new AssertionException( new AssertionError(e.getMessage()) );
	    }
	    
	    return "Request WS-Addressing is valid";
	}

	@Override
	protected String internalAssertResponse(MessageExchange messageExchange, SubmitContext context)
			throws AssertionException
	{
    try
    {
    	new WsaValidator( (WsdlMessageExchange) messageExchange ).validateWsAddressingResponse();
    }
    catch (Exception e)
    {
       throw new AssertionException( new AssertionError(e.getMessage()) );
    }
    
    return "Response WS-Addressing is valid";
	}

}
