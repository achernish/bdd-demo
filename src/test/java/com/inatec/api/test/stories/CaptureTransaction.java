package com.inatec.api.test.stories;

import com.inatec.api.test.steps.CaptureTransactionSteps;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

/**
 * @author Anatoly Chernysh
 */
public class CaptureTransaction extends CoreStory {

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new CaptureTransactionSteps());
    }
}