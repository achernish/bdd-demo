package com.inatec.api.test.steps;

import com.inatec.api.test.model.AuthorizeRequest;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.steps.Steps;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Anatoly Chernysh
 */
public abstract class AbstractSteps extends Steps {

    protected List<AuthorizeRequest> authorizeRequests;

    @Given("a transaction $requests")
    public void givenTransaction(List<AuthorizeRequest> requests) {
        this.authorizeRequests = requests;
    }
}
