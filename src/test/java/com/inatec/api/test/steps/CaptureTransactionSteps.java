package com.inatec.api.test.steps;

import com.inatec.api.test.service.InatecCreditCardAPI;
import com.inatec.api.test.service.InatecCreditCardAPIImpl;
import com.inatec.api.test.model.CaptureRequest;
import com.inatec.api.test.model.Response;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anatoly Chernysh
 */
public class CaptureTransactionSteps extends AbstractSteps {

    private Response authorizeResponse;

    private CaptureRequest captureRequest;

    private Response captureResponse;

    private InatecCreditCardAPI inatecCreditCardAPI;

    @BeforeStory
    public void init() {
        inatecCreditCardAPI = new InatecCreditCardAPIImpl();
    }

    @When("pre-authorize")
    public void whenPreAuthorize() {
        this.authorizeResponse = inatecCreditCardAPI.authorize(this.authorizeRequest);
    }

    @Then("transaction has been pre-authorized")
    public void thanTransactionHasBeenPreAuthorized () {
        assertThat(this.authorizeResponse.getStatus(), is(0));
    }

    @When("capture")
    public void whenCapture() {
        this.captureRequest = new CaptureRequest(this.authorizeRequest.getMerchantid(),
                this.authorizeResponse.getTransactionid(),
                this.authorizeRequest.getLanguage());
        this.captureResponse = inatecCreditCardAPI.capture(this.captureRequest);
    }

    @Then("transaction has been captured")
    public void thanTransactionHasBeenCaptured() {
        assertThat(this.captureResponse.getStatus(), is(0));
    }
}