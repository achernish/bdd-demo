package com.inatec.api.test.stories;


import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterConverters;

import java.util.Properties;

/**
 * @author Anatoly Chernysh
 */
public abstract class CoreStory extends JUnitStory {

    public CoreStory() {
        configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(false)
                .doIgnoreFailureInView(true).useThreads(1).useStoryTimeouts("60");
    }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");

        // Start from default ParameterConverters instance
        ParameterConverters parameterConverters = new ParameterConverters();

        // factory to allow parameter conversion and loading from external
        // resources (used by StoryParser too)
        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(),
                new LoadFromClasspath(embeddableClass), parameterConverters, new TableTransformers());


        return new MostUsefulConfiguration()
                .useStoryControls(new StoryControls().doDryRun(false).doSkipScenariosAfterFailure(false))
                        //.usePendingStepStrategy(new FailingUponPendingStep())
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryParser(new RegexStoryParser(examplesTableFactory))
                .useStoryPathResolver(new UnderscoredCamelCaseResolver())
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                                .withDefaultFormats().withPathResolver(new FilePrintStreamFactory.ResolveToPackagedName())
                                .withViewResources(viewResources).withFormats(Format.CONSOLE, Format.TXT, Format.HTML_TEMPLATE, Format.XML)
                                .withFailureTrace(true).withFailureTraceCompression(true))
                .useParameterConverters(parameterConverters)
                        // use '%' instead of '$' to identify parameters
                .useStepPatternParser(new RegexPrefixCapturingPatternParser("%"));
    }
}