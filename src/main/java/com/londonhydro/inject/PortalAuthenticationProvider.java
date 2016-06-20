/*****************************************************************************
 *                                                                           *
 *                       Copyright (c) 2012-2013 London Hydro                *
 *                            ALL RIGHTS RESERVED                            *
 *                                                                           *
 *****************************************************************************
 *
 *  File Name:  PortalAuthenticationProvider.java
 *
 *  Facility:   London Hydro Broker
 *
 *  Author:     Daniel Isaac Khan Ramiro, Affinity Systems
 *
 *  Revision History
 *
 *  Date        Author              Description
 *  -------     ------------------  -----------------------------------------
 *  18/09/13    Daniel Khan         Original version
 */
package com.londonhydro.inject;

import javax.inject.Provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.londonhydro.model.LoginResult;

/**
 * @author dikhan
 *
 */
public class PortalAuthenticationProvider implements Provider<LoginResult>
{
    private static final Log logger = LogFactory.getLog(PortalAuthenticationProvider.class);
    
    private LoginResult loginResult;
    private String username;
    private String password;   
    private String serverUrl;
    private String apiLoginEndPoint;
    
    @Inject @Named("londonhydro.cloud2cis.api.authorization.enabled")
    private boolean authorizationEnabled; 
    
    @Inject
    public PortalAuthenticationProvider(@Named("londonhydro.cloud2cis.api.login.username") String username, @Named("londonhydro.cloud2cis.api.login.password") String password, 
            @Named("com.londonhydro.lh.server.url") String serverUrl, @Named("londonhydro.cloud2cis.api.public.login.endPoint") String apiLoginEndPoint,
            @Named("londonhydro.cloud2cis.api.public.login.delay") boolean delay)
    {
        this.username = username;
        this.password = password;
        this.serverUrl = serverUrl;
        this.apiLoginEndPoint = apiLoginEndPoint;
        if (!delay)
            login();
    }

    //@Override
    public LoginResult get()
    {
        if (null == loginResult)
            login();
        return loginResult;
    }
    
    @SuppressWarnings("unchecked")
    public boolean login()
    {
        if (loginResult != null)
          return true;
        
        ClientRequest request = new ClientRequest(serverUrl + apiLoginEndPoint);
       
        //request.queryParameter("", "");       
        request.getFormParameters().add("username", username);
        request.getFormParameters().add("password", password);
        
        try
        {
        	/*
            ClientResponse<UserAccount> response = request.post();
            if(response.getStatus() == 200)
            {
                loginResult = (LoginResult) response.getEntity(LoginResult.class);
                logger.info("Broker has been logged in the Portal.");
            }
            else
                logger.error("Failed logging into the Portal.");
                */
            
        }
        catch (Exception e)
        {
            logger.error("Failed logging into the Portal.");
            e.printStackTrace();
        }
        
        return true;
    }

    public void addAuthorizationHeader(ClientRequest request)
    {
        if (!authorizationEnabled)
        {
            request.header("authorization", "Bearer -dt01");
            logger.debug("Authorization is not enabled");
            return;
        }
        if (loginResult == null)
            login();
        if (loginResult == null)
            throw new RuntimeException("Login to the portal has failed");
         request.header("authorization", "Bearer " + loginResult.getTimeKey());
    }
    
}
