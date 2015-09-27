package com.inatec.api.test.steps;

import com.inatec.api.test.model.AuthorizeRequest;
import com.inatec.api.test.model.CaptureRequest;
import com.inatec.api.test.model.Response;
import com.inatec.api.test.service.InatecCreditCardAPI;
import com.inatec.api.test.service.InatecCreditCardAPIImpl;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anatoly Chernysh
 */
public class CaptureTransactionSteps extends AbstractSteps {

    private Map<AuthorizeRequest, Response> preAuthorizeResponses;

    private List<Response> captureResponses;

    private InatecCreditCardAPI inatecCreditCardAPI;

    @BeforeStory
    public void init() {
        inatecCreditCardAPI = new InatecCreditCardAPIImpl();
    }

    @When("pre-authorize")
    public void whenPreAuthorize() {
        this.preAuthorizeResponses = new HashMap<>(this.authorizeRequests.size());

        for (AuthorizeRequest authorizeRequest : this.authorizeRequests) {
            this.preAuthorizeResponses.put(authorizeRequest, inatecCreditCardAPI.preAuthorize(authorizeRequest));
        }
    }

    @Then("transaction has been pre-authorized")
    public void thanTransactionHasBeenPreAuthorized () {
        for (Response response : this.preAuthorizeResponses.values()) {
            assertThat(response.getStatus(), is(0));
        }
    }

    @When("capture")
    public void whenCapture() {
        this.captureResponses = new ArrayList<Response>(this.authorizeRequests.size());

        for (Map.Entry<AuthorizeRequest, Response> entry : this.preAuthorizeResponses.entrySet()) {
            CaptureRequest captureRequest = new CaptureRequest(entry.getKey().getMerchantid(),
                    entry.getValue().getTransactionid(),
                    entry.getKey().getLanguage());
            captureResponses.add(inatecCreditCardAPI.capture(captureRequest));
        }
    }

    @Then("transaction has been captured")
    public void thanTransactionHasBeenCaptured() {
        for (Response response : this.captureResponses) {
            assertThat(response.getStatus(), is(0));
        }
    }
}