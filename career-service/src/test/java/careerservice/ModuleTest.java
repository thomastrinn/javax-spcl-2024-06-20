package careerservice;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModuleTest {

    @Test
    void architecture() {
        var modules = ApplicationModules.of(CareerServiceApplication.class);
        modules.verify();

        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml()
                .writeDocumentation()
                .writeModuleCanvases()
        ;
    }
}
