import org.gradle.api.tasks.testing.logging.TestExceptionFormat

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

tasks.register("cs101Test", Test::class.java) {
    testClassesDirs = files(layout.projectDirectory.dir("app/src/test/java/com/example/cs101_test"))
    classpath = files(layout.projectDirectory.dir("app/src/test/java/com/example/cs101_test"))
    binaryResultsDirectory.set(layout.buildDirectory.dir("test-results/binary/cs101Test"))
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = TestExceptionFormat.FULL
    }
    reports {
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("reports/tests/test"))
        junitXml.required.set(true)
        junitXml.outputLocation.set(layout.buildDirectory.dir("test-results/test"))
    }
}

afterEvaluate {
    tasks.getByPath(":app:preBuild").dependsOn(":cs101Test")
}
