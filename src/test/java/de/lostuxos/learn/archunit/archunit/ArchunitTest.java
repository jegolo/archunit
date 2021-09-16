package de.lostuxos.learn.archunit.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(importOptions = ImportOption.DoNotIncludeTests.class, packages = "de.lostuxos.learn.archunit.avatar")
public class ArchunitTest {

    @ArchTest
    public static final ArchRule useCasesAreOnlyCallableFromInAdapter = classes()
            .that().resideInAPackage("..ports.in..")
            .should().onlyBeAccessed().byAnyPackage("..adapter.in..")
            .andShould().beInterfaces();

    @ArchTest
    public static final ArchRule useCasesAreImplementedInServices = classes()
            .that().resideInAnyPackage("..domain..")
            .should().onlyHaveDependentClassesThat().resideInAPackage("..ports.in..");

    @ArchTest
    public static final ArchRule outPortsAreInterfacesAndOnlyCalledByServices = classes()
            .that().resideInAPackage("..ports.out..")
            .should().beInterfaces();

    @ArchTest
    public static final ArchRule LAYERED_ARCHITECTURE =
            layeredArchitecture()
    .layer("InBounds").definedBy("..adapter.in..")
    .layer("InPorts").definedBy("..ports.in..")
    .layer("Domain").definedBy("..domain..")
    .layer("OutPorts").definedBy("..ports.out..")
    .layer("OutBounds").definedBy("..adapter.out..")

    .whereLayer("InBounds").mayNotBeAccessedByAnyLayer()
    .whereLayer("InPorts").mayOnlyBeAccessedByLayers("InBounds", "Domain")
    .whereLayer("OutPorts").mayOnlyBeAccessedByLayers("Domain", "OutBounds")
    .whereLayer("Domain").mayNotBeAccessedByAnyLayer()
    .whereLayer("OutBounds").mayNotBeAccessedByAnyLayer();
}
