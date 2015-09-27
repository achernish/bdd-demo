package com.inatec.api.test.steps;

import com.inatec.api.test.model.AuthorizeRequest;
import com.inatec.api.test.model.Response;
import com.inatec.api.test.service.InatecCreditCardAPI;
import com.inatec.api.test.service.InatecCreditCardAPIImpl;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anatoly Chernysh
 */
public class AuthorizeTransactionSteps extends AbstractSteps {

    private List<Response> responses;

    @When("authorize")
    public void whenAuthorize() {
        InatecCreditCardAPI inatecCreditCardAPI = new InatecCreditCardAPIImpl();

        this.responses = new ArrayList<>(this.authorizeRequests.size());
        for (AuthorizeRequest authorizeRequest : this.authorizeRequests) {
            this.responses.add(inatecCreditCardAPI.authorize(authorizeRequest));
        }
    }

    @Then("transaction has been authorized")
    public void thanTransactionHasBeenAuthorized () {
        for (Response response : this.responses) {
            assertThat(response.getStatus(), is(0));
        }
    }
}