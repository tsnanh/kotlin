
plugins {
    kotlin("jvm")
}

val testsCompilationClasspath by configurations.creating {
    resolutionStrategy {
        disableDependencyVerification()
    }
}

dependencies {
    testCompile(commonDep("junit"))
    testCompileOnly(project(":compiler:cli"))

    testRuntimeOnly(project(":kotlin-compiler"))

    testsCompilationClasspath("org.scala-lang:scala-library:2.12.11")
    testsCompilationClasspath(project(":kotlin-stdlib"))
}

sourceSets {
    "main" {}
    "test" { projectDefault() }
}

projectTest(parallel = true) {
    workingDir = projectDir
    val testsCompilationClasspathProvider = project.provider { testsCompilationClasspath.asPath }
    doFirst {
        systemProperty("testsCompilationClasspath", testsCompilationClasspathProvider.get())
    }
}
