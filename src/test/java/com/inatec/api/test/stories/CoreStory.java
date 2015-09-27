package com.inatec.api.test.stories;


import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.junit.JUnitStory;

/**
 * @author Anatoly Chernysh
 */
public abstract class CoreStory extends JUnitStory {

    public CoreStory() {
        configuredEmbedder().embedderControls().doGenerateViewAfterStories(false).doIgnoreFailureInStories(false)
                .doIgnoreFailureInView(true).useThreads(1).useStoryTimeouts("60");
    }

    @Override
    public Configuration configuration() {
        Configuration configuration = new MostUsefulConfiguration().usePendingStepStrategy(new FailingUponPendingStep());
        return configuration;
    }
}