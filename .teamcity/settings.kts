
/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.project
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.triggers.vcs

version = "2025.03"

project {

    name = "Prometheus Final"

    buildType(BuildMR)

}

object BuildMR : BuildType({
    id("BuildMR")
    name = "TEST MR"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "Print MR Test Message"
            scriptContent = """echo "HELLO I'M TEST MR""""
        }
    }

    triggers {
        vcs {
            branchFilter = "+:refs/pull/*/head"
        }
    }

    features {
        perfmon {}
    }
})
