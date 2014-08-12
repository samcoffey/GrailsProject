grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
		mavenRepo 'http://mvnrepository.com'
    }

    dependencies {
		compile 'com.simplify:payments-sdk-java:1.1.2'
		runtime 'com.javadocmd:simplelatlng:1.0.0'
		runtime 'com.nimbusds:nimbus-jose-jwt:2.10.0'
		runtime 'net.minidev:json-smart:1.0.9'
    }

    plugins {
        compile ':heroku:1.0.1'
        compile ':cloud-support:1.0.8'

        // plugins for the build system only
        build ":tomcat:7.0.53"

        // plugins for the compile step
        compile ":scaffolding:2.0.3"
        compile ':cache:1.1.2'
        compile ':rest:0.8'
		compile ":geolocation:0.4.1"

        // plugins needed at runtime but not for compilation
        runtime ":hibernate:3.6.10.15" // or ":hibernate4:4.3.5.2"
        runtime ":database-migration:1.4.0"
        runtime ":jquery:1.11.1"
        runtime ":resources:1.2.7"

        compile ":codenarc:0.21"
    }
}
