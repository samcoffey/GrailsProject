package interns

import grails.transaction.Transactional
import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
import org.codehaus.groovy.grails.commons.GrailsApplication

@Transactional
class CommsService {

    GrailsApplication grailsApplication

    /**
     * Make a GET request to the Simplify API platform
     *
     *
     * @param query
     * @param path
     * @return
     */
    public Map getFromSimplify(Map query, String path) {
        log.info(">> getFromSimplify(query=$query, path=$path")

        Map responseMap = [:]

        createRESTClient().request(Method.GET, ContentType.JSON) {
            req ->
                // The expand params are restricted, cat and desc are not available
                uri.query = query
                uri.path = path

                // response handler for a success response code
                response.success = { HttpResponseDecorator resp, json ->
                    log.info("getFromDemandware(), successful response $resp.statusLine ")
                    responseMap.httpResponse = resp
                    responseMap.json = json

                    resp.getHeaders('Set-Cookie').each {
                        String cookie = it.value.split(';')[0]
                        log.debug("Adding cookie to collection: $cookie")
                    }

                }

                // handler for any failure status code
                response.failure = { resp, json ->
                    log.error "getFromDemandware(), Unexpected error: " +
                            "${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
                    responseMap.httpResponse = resp
                    responseMap.json = json
                }
        }

        log.info("<< getFromDemandware(), responseMap=$responseMap")
        return responseMap
    }

    /**
     * Make a POST request to the Simplify API platform
     *
     *
     * @param query
     * @param path
     * @return
     */
    public Map postToFromSimplify(Map query, String path, Map postParams = [:]) {
        log.info(">> postToFromSimplify(query=$query, path=$path, postParams=$postParams)")

        Map responseMap = [:]

        createRESTClient().request(Method.POST, ContentType.JSON) {
            req ->
                uri.query = query
                uri.path = path

                if (!postParams.isEmpty()) {
                    body = postParams
                }

                response.success = { resp, json ->
                    log.info("postToFromSimplify(), successful response $resp.statusLine in ")
                    responseMap.httpResponse = resp
                    responseMap.json = json

                    log.debug("postToFromSimplify(), json=$json")

                }

                response.failure = { resp, json ->
                    log.error "postToFromSimplify(), Unexpected error: " +
                            "${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
                }
        }

        log.info("<< postToFromSimplify(), responseMap=$responseMap")
        return responseMap
    }

    public RESTClient createRESTClient() {
        log.info(">> createRESTClient(), $grailsApplication.config.simplify.host")
        RESTClient restClient = new RESTClient(grailsApplication.config.simplify.host);
        return restClient
    }
}
