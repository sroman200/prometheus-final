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
        branchSpec = """
        +:refs/heads/*
        +:refs/pull/*/head
    """.trimIndent()
    }

    steps {
        script {
            name = "Print MR Test Message"
            scriptContent = """echo "HELLO I'M TEST MR-2""""
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
