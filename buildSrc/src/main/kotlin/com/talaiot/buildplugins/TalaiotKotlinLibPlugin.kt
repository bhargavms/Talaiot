package com.talaiot.buildplugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import java.net.URI

/**
 * TalaiotKotlinLib Plugin represents a build configuration
 * of java-libraries]/kotlin modules.
 * These modules will require common build logic for unit tests and jacoco
 */
class TalaiotKotlinLibPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target
            .extensions
            .create<BaseConfiguration>("talaiotLib")

        target.plugins.apply("kotlin")
        target.plugins.apply("maven-publish")
        target.plugins.apply("jacoco")
        target.plugins.apply("java-library")
        target.plugins.apply("com.jfrog.bintray")

        target.repositories {
            jcenter()
            mavenCentral()
            maven { url = URI("https://plugins.gradle.org/m2/") }
        }

        target.setUpJacoco()
        target.setUpJunitPlatform()
        target.afterEvaluate {
            val extension = extensions.getByType<BaseConfiguration>()
            setProjectVersion(extension.version)
            setProjectGroup(extension.group, Type.LIBRARY)
            collectUnitTest()
            setUpPublishing(Type.LIBRARY)
            setUpJfrog()
        }

        target.dependencies {
            add("testImplementation", "com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")
            add("testImplementation", "io.kotlintest:kotlintest-runner-junit5:3.3.2")
        }
    }
}
