package com.inatec.api.test.steps;

import com.inatec.api.test.service.InatecCreditCardAPI;
import com.inatec.api.test.service.InatecCreditCardAPIImpl;
import com.inatec.api.test.model.Response;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anatoly Chernysh
 */
public class AuthorizeTransactionSteps extends AbstractSteps {

    private Response response;

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