package com.inatec.api.test.stories;

import com.inatec.api.test.steps.PreAuthorizeTransactionSteps;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

/**
 * @author Anatoly Chernysh
 */
public class PreAuthorizeTransaction extends CoreStory {

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new PreAuthorizeTransactionSteps());
    }
}