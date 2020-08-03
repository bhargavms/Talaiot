package com.cdsap.talaiot.plugin

import com.cdsap.talaiot.utils.TemporaryFolder
import io.kotlintest.specs.BehaviorSpec
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome

class OutputPublisherBuildTest : BehaviorSpec({
    given("Build Gradle File") {
        val testProjectDir = TemporaryFolder()
        `when`("Talaiot is included with OutputPublisher") {
            testProjectDir.create()
            val buildFile = testProjectDir.newFile("build.gradle")
            buildFile.appendText(
                """
                   plugins {
                      id 'java'
                      id 'com.cdsap.talaiot.plugin.base'
                   }

                  talaiot{
                    logger = com.cdsap.talaiot.base.logger.LogTracker.Mode.INFO
                     publishers {
                      outputPublisher {}
                  }
               }
            """
            )
            val result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("assemble")
                .withPluginClasspath()
                .build()
            then("logs are shown in the output with the shrugged") {
                assert(result.output.contains("OutputPublisher"))
                assert(result.output.contains("¯\\_(ツ)_/¯"))
                assert(result.task(":assemble")?.outcome == TaskOutcome.SUCCESS)

            }
            testProjectDir.delete()
        }
    }
})
