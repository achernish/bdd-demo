package com.inatec.api.test.steps;

import com.inatec.api.test.model.Response;
import com.inatec.api.test.service.InatecCreditCardAPI;
import com.inatec.api.test.service.InatecCreditCardAPIImpl;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anatoly Chernysh
 */
public class PreAuthorizeTransactionSteps extends AbstractSteps {

    private Response response;

    @When("pre-authorize")
    public void whenPreAuthorize() {
        InatecCreditCardAPI inatecCreditCardAPI = new InatecCreditCardAPIImpl();
        this.response = inatecCreditCardAPI.preAuthorize(authorizeRequest);
    }

    @Then("transaction has been pre-authorized")
    public void thanTransactionHasBeenPreAuthorized() {
        assertThat(this.response.getStatus(), is(0));
    }
}