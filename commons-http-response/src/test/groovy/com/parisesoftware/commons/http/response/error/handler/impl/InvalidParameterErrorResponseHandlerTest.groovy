package com.parisesoftware.commons.http.response.error.handler.impl

import com.parisesoftware.commons.http.dto.internal.DataTransferObject
import com.parisesoftware.commons.http.notification.NotificationType
import com.parisesoftware.commons.http.response.error.impl.UnprocessableEntityErrorResponse
import spock.lang.Specification

class InvalidParameterErrorResponseHandlerTest extends Specification {

    def "getNotificationTypesHandled(): should return the an empty collection of Notification Type"() {

        when: 'a new InvalidParameterErrorResponseHandler is created and the notification types handled is queried for'
        List resultant = new InvalidParameterErrorResponseHandler(null).getNotificationTypesHandled()

        then: 'the resultant collection has three entries'
        resultant.size() == 3

        and: 'the resultant collection contains `NotificationType.INVALID_BODY_PARAMETER`'
        resultant.contains(NotificationType.INVALID_BODY_PARAMETER)

        and: 'the resultant collection contains `NotificationType.INVALID_QUERY_PARAMETER`'
        resultant.contains(NotificationType.INVALID_QUERY_PARAMETER)

        and: 'the resultant collection contains `NotificationType.INVALID_PATH_PARAMETER`'
        resultant.contains(NotificationType.INVALID_PATH_PARAMETER)
    }

    def "getResponse(): should delegate to UnprocessableEntityErrorResponse.from()"() {

        given: 'a sample Data Transfer Object'
        DataTransferObject testDTO = new DataTransferObject()

        and: 'a global UnprocessableEntityErrorResponse Mock'
        GroovyMock(UnprocessableEntityErrorResponse, global: true)

        when: 'a new InvalidParameterErrorResponseHandler is created and a Response is generated from our sample DTO'
        new InvalidParameterErrorResponseHandler(null).getResponse(testDTO)

        then: 'UnprocessableEntityErrorResponse.from() was invoked with our sample DTO'
        1 * UnprocessableEntityErrorResponse.from(testDTO)
    }

}
