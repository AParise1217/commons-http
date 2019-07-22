package com.parisesoftware.commons.http.response.error.handler.impl

import com.parisesoftware.commons.http.dto.internal.DataTransferObject
import com.parisesoftware.commons.http.response.error.impl.InternalServerErrorResponse
import spock.lang.Specification

class DefaultErrorResponseHandlerTest extends Specification {

    def "getNotificationTypesHandled(): should return the an empty collection of Notification Type"() {

        when: 'a new DefaultErrorResponseHandler is created and the notification types handled is queried for'
        List resultant = new DefaultErrorResponseHandler(null).getNotificationTypesHandled()

        then: 'the resultant collection is empty'
        resultant.isEmpty()
    }

    def "getResponse(): should delegate to InternalServerErrorResponse.from()"() {

        given: 'a sample Data Transfer Object'
        DataTransferObject testDTO = new DataTransferObject()

        and: 'a global InternalServerErrorResponse Mock'
        GroovyMock(InternalServerErrorResponse, global: true)

        when: 'a new DefaultErrorResponseHandler is created and a Response is generated from our sample DTO'
        new DefaultErrorResponseHandler(null).getResponse(testDTO)

        then: 'InternalServerErrorResponse.from() was invoked with our sample DTO'
        1 * InternalServerErrorResponse.from(testDTO)
    }

    def "isDefaultHandler(): should be overridden to return true"() {

        when: 'isDefaultHandler() is invoked on our sample Response Handler'
        def resultant = new DefaultErrorResponseHandler(null).isDefaultHandler()

        then: 'the resultant boolean is true'
        resultant
    }

}
