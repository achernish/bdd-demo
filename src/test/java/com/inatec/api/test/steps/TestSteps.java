package com.inatec.api.test.steps;

import com.inatec.api.test.model.TestRequest;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import java.util.List;

/**
 * @author Anatoly Chernysh
 */
public class TestSteps extends Steps {

    @Given("a table: $table")
    public void given(List<TestRequest> table) {
        System.out.println("Given table: " + table);
    }

    @When("do something")
    public void when() {
        System.out.println("Test when");
    }

    @Then("life is good")
    public void than() {
        System.out.println("Test than");
    }
}
