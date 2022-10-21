package pl.emunowak.letterboxed.solver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.KieResources;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.springframework.stereotype.Component;
import pl.emunowak.letterboxed.solver.config.ResourcesConfiguration;
import pl.emunowak.letterboxed.solver.config.RulesConfiguration;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class RulesLoader {

    private final RulesConfiguration rulesResources;
    private final ResourcesConfiguration textResources;

    public KieBase initializeSolveBase() {
        var kieServices = KieServices.Factory.get();
        var rules = rulesResources.getRulesFromResources();
        var kieContainer = addRulesToKnowledgeBase( kieServices, rules );
        return kieContainer.getKieBase();
    }

    private KieContainer addRulesToKnowledgeBase( KieServices kieServices, Collection<String> rules ) {
        var kieFileSystem = kieServices.newKieFileSystem();
        fillKnowledgeBuilderWithRules( kieFileSystem, kieServices.getResources(), rules );
        var kieBuilder = kieServices.newKieBuilder( kieFileSystem ).buildAll();
        handleRuleErrors( kieBuilder.getResults() );
        return kieServices.newKieContainer( kieBuilder.getKieModule().getReleaseId() );
    }

    private void fillKnowledgeBuilderWithRules( KieFileSystem kieFileSystem, KieResources kieResources, Collection<String> rules ) {
        rules.forEach( elem -> {
            var resource = kieResources.newByteArrayResource( elem.getBytes() );
            resource.setSourcePath( String.valueOf( elem.hashCode() ) );
            resource.setResourceType( ResourceType.DRL );
            kieFileSystem.write( resource );
        } );
    }

    private void handleRuleErrors( Results results ) {
        if ( results.hasMessages( Message.Level.ERROR ) ) {
            throw new RuntimeException( textResources.getRulesParseError() +
                    results.getMessages().stream().map( Object::toString ).collect( Collectors.joining( "\n" ) ) );
        }
    }
}
