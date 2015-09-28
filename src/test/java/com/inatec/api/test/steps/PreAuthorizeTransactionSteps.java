package com.inatec.api.test.steps;

import com.inatec.api.test.model.Response;
import com.inatec.api.test.service.InatecCreditCardAPI;
import com.inatec.api.test.service.InatecCreditCardAPIImpl;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anatoly Chernysh
 */
public class PreAuthorizeTransactionSteps extends AbstractSteps {

    private List<Response> responses;

    @When("pre-authorize")
    public void whenPreAuthorize() {
        InatecCreditCardAPI inatecCreditCardAPI = new InatecCreditCardAPIImpl();
        this.responses = this.authorizeRequests.stream().map(inatecCreditCardAPI::preAuthorize).collect(toList());
    }

    @Then("transaction has been pre-authorized")
    public void thanTransactionHasBeenPreAuthorized() {
        for (Response response : this.responses) {
            assertThat(response.getStatus(), is(0));
        }
    }
}