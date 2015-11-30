package com.inatec.bankproxy.api;

import com.inatec.bankproxy.dto.AuthorisationRequest;
import com.inatec.bankproxy.dto.AuthorisationResponse;

/**
 * @author Anatoly Chernysh
 */
public interface BankProxyService {

    public AuthorisationResponse authorize(AuthorisationRequest authorisationRequest);

}
