package com.inatec.api.test.steps;

import com.inatec.api.test.model.AuthorizeRequest;
import com.inatec.api.test.model.Response;
import com.inatec.api.test.service.InatecCreditCardAPI;
import com.inatec.api.test.service.InatecCreditCardAPIImpl;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anatoly Chernysh
 */
public class AuthorizeTransactionSteps extends Steps {

    private Response response;

    protected AuthorizeRequest authorizeRequest;

    @Given("a transaction: $transactions")
    public void givenTransaction(List<AuthorizeRequest> transactions) {
        this.authorizeRequest = transactions.get(0);
        //this.authorizeRequest.setCustom1("123456");
    }


    @When("authorize")
    public void whenAuthorize() {
        InatecCreditCardAPI inatecCreditCardAPI = new InatecCreditCardAPIImpl();
        this.response = inatecCreditCardAPI.authorize(this.authorizeRequest);
    }

    @Then("transaction has been authorized")
    public void thanTransactionHasBeenAuthorized () {
        assertThat(response.getStatus(), is(0));
    }
}