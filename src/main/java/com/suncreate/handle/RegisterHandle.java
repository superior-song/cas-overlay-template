package com.suncreate.handle;

import org.apereo.cas.authentication.*;
import org.apereo.cas.authentication.handler.support.HttpBasedServiceCredentialsAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.apereo.cas.util.http.HttpClient;

import javax.security.auth.login.FailedLoginException;
import java.net.URL;
import java.security.GeneralSecurityException;

/**
 * Created by Administrator on 2020/12/30 0030.
 */
public class RegisterHandle extends HttpBasedServiceCredentialsAuthenticationHandler {
    public RegisterHandle(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order, HttpClient httpClient) {
        super(name, servicesManager, principalFactory, order, httpClient);
    }

    @Override
    public AuthenticationHandlerExecutionResult authenticate(Credential credential) throws GeneralSecurityException {
        HttpBasedServiceCredential httpCredential = (HttpBasedServiceCredential)credential;
        if(!httpCredential.getService().getProxyPolicy().isAllowedProxyCallbackUrl(httpCredential.getCallbackUrl())) {
         //   LOGGER.warn("Proxy policy for service [{}] cannot authorize the requested callback url [{}].", httpCredential.getService().getServiceId(), httpCredential.getCallbackUrl());
            throw new FailedLoginException(httpCredential.getCallbackUrl() + " cannot be authorized");
        } else {
        /*    LOGGER.debug("Attempting to authenticate [{}]", httpCredential);*/
            URL callbackUrl = httpCredential.getCallbackUrl();
          /*  if(!this.httpClient.isValidEndPoint(callbackUrl)) {
                throw new FailedLoginException(callbackUrl.toExternalForm() + " sent an unacceptable response status code");
            } else {
                return new DefaultAuthenticationHandlerExecutionResult(this, httpCredential, this.principalFactory.createPrincipal(httpCredential.getId()));
            }*/
        }
        return null;
    }
}
