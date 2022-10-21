package pl.emunowak.letterboxed.solver

import org.kie.api.KieBase
import org.kie.api.KieServices
import org.kie.api.io.ResourceType

class RulesTestUtils {

    static KieBase getKieBaseWithPackages( String rule ) {
        def kieServices = KieServices.Factory.get()
        def kieFileSystem = kieServices.newKieFileSystem()
        def kieResources = kieServices.getResources();

        def resource = kieResources.newByteArrayResource( rule.getBytes() )
        resource.setSourcePath( "1" )
        resource.setResourceType( ResourceType.DRL )
        kieFileSystem.write( resource )

        def kieBuilder = kieServices.newKieBuilder( kieFileSystem ).buildAll();
        def container = kieServices.newKieContainer( kieBuilder.getKieModule().getReleaseId() )
        container.getKieBase()
    }
}
