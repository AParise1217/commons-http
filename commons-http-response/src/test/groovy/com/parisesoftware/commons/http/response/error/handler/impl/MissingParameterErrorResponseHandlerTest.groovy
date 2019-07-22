package com.parisesoftware.commons.http.response.error.handler.impl

import com.parisesoftware.commons.http.dto.internal.DataTransferObject
import com.parisesoftware.commons.http.notification.NotificationType
import com.parisesoftware.commons.http.response.error.impl.BadRequestErrorResponse
import spock.lang.Specification

class MissingParameterErrorResponseHandlerTest extends Specification {

    def "getNotificationTypesHandled(): should return the an empty collection of Notification Type"() {

        when: 'a new MissingParameterErrorResponseHandler is created and the notification types handled is queried for'
        List resultant = new MissingParameterErrorResponseHandler(null).getNotificationTypesHandled()

        then: 'the resultant collection has three entries'
        resultant.size() == 3

        and: 'the resultant collection contains `NotificationType.MISSING_PATH_PARAMETER`'
        resultant.contains(NotificationType.MISSING_PATH_PARAMETER)

        and: 'the resultant collection contains `NotificationType.MISSING_QUERY_PARAMETER`'
        resultant.contains(NotificationType.MISSING_QUERY_PARAMETER)

        and: 'the resultant collection contains `NotificationType.MISSING_PATH_PARAMETER`'
        resultant.contains(NotificationType.MISSING_PATH_PARAMETER)
    }

    def "getResponse(): should delegate to BadRequestErrorResponse.from()"() {

        given: 'a sample Data Transfer Object'
        DataTransferObject testDTO = new DataTransferObject()

        and: 'a global BadRequestErrorResponse Mock'
        GroovyMock(BadRequestErrorResponse, global: true)

        when: 'a new MissingParameterErrorResponseHandler is created and a Response is generated from our sample DTO'
        new MissingParameterErrorResponseHandler(null).getResponse(testDTO)

        then: 'InternalServerErrorResponse.from() was invoked with our sample DTO'
        1 * BadRequestErrorResponse.from(testDTO)
    }

}
