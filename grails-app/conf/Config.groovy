import com.dublininterns.hack.logging.MaskingLayout
import org.apache.log4j.RollingFileAppender

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

grails.gorm.failOnError=true

String basedir = System.properties["base.dir"]
String log4jFileLocation = System.properties["jboss.server.log.dir"] ? "${System.properties["jboss.server.log.dir"]}/" : "${basedir}/"

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']
grails.resources.adhoc.includes = ['/images/**', '/css/**', '/js/**', '/plugins/**']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// Simplify API
merchant.id = '123456'
simplify.host = 'https://raw.githubusercontent.com/longieirl/GrailsProject/develop/'
simplify.processpayment = 'status.json'
simplify.checkout = 'checkout.json'
simplify.private.key = 'YOUR_PRIVATE_API_KEY'
simplify.public.key = 'YOUR_PUBLIC_API_KEY'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"

        simplify.host = 'http://www.producrl.com'
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    appenders {
        'null' name: 'stacktrace'

        console name: 'mainAppender', layout: {
            // Using a MaskingLayout to mask sensitive information.
            def layout = new MaskingLayout(conversionPattern: '%d{dd,MM HH:mm:ss:SSS} [%X{REQUEST_URL_PATH}|%X{REQUEST_ID}|%X{SDK_THREAD_NAME}|%X{SDK_THREAD_ID}] [%t] %-5p %c{2} - %m\n')
            layout.activateOptions()
            layout
        }.call()

        appender name: 'grailsProjectAppender', new RollingFileAppender(file: "${log4jFileLocation}GrailsProject.log", layout: {
            // Using a MaskingLayout to mask sensitive information.
            def layout = new MaskingLayout(conversionPattern: '%d{dd,MM HH:mm:ss:SSS} [%X{REQUEST_URL_PATH}|%X{REQUEST_ID}|%X{SDK_THREAD_NAME}|%X{SDK_THREAD_ID}] [%t] %-5p %c{2} - %m\n')
            layout.activateOptions()
            layout
        }.call())

        appender name: 'sqlAppender', new RollingFileAppender(file: "${log4jFileLocation}sql.log", layout:pattern(conversionPattern:
                '%d{yyyy-MM-dd HH:mm:ss:SSS} [%X{REQUEST_URL_PATH}|%X{REQUEST_ID}|%X{ACCESS_TOKEN}|%X{SESSION_ID}|%X{QKR_USER_ID}] [%t] %-5p %c{2} - %m\n'))
    }

    error sqlAppender: 'org.hibernate.SQL', additivity: true

    error 'org.codehaus.groovy.grails.web.servlet',        // controllers
            'org.codehaus.groovy.grails.web.pages',          // GSP
            'org.codehaus.groovy.grails.web.sitemesh',       // layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping',        // URL mapping
            'org.codehaus.groovy.grails.commons',            // core / classloading
            'org.codehaus.groovy.grails.plugins',            // plugins
            'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
            'grails.app.service.org.grails.plugin.resource',
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate',
            'errorAppender',
            'grails.app.services.org.grails.plugin.resource',
            'grails.app.taglib.org.grails.plugin.resource', // Only log error
            'grails.app.resourceMappers.org.grails.plugin.resource' // Only log error

    debug   'grails.app.controllers.com.dublininterns.hack',
            'grails.app.services.com.dublininterns.hack',
            'grails.app.domain.com.dublininterns.hack',
            'grails.app.jobs.com.dublininterns.hack',
            'com.dublininterns.hack',
            'grails.app.conf.BootStrap',
            'grails.app.filters',
            'grails.app.task',

    root {
        error 'mainAppender', 'grailsProjectAppender'
        additivity = true
    }

    environments {

        development {

            appenders {
                file name: 'mainAppender', file: 'grailsProject.log'
                appender name: 'mainAppenderLogFile', new RollingFileAppender(file: "grailsProject.log", threshold: org.apache.log4j.Level.DEBUG, layout: pattern(conversionPattern:
                        '%d{yyyy-MM-DD HH:mm:ss:SSS} [%X{REQUEST_URL_PATH}|%X{REQUEST_ID}|%X{REQUEST_USER}] [%t] %-5p %c{2} - %m\n'))
            }
        }

        production {

            warn 'grails.app.conf',
                    'grails.app.filters',
                    'grails.app.services',
                    'grails.app.controllers',
                    'grails.app.domain',
                    'com.dublininterns.hack.dexter'

            off 'org.apache.http.headers'
            off 'org.apache.http.wire'
        }
    }
}
