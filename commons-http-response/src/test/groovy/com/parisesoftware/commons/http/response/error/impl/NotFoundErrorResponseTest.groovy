package com.parisesoftware.commons.http.response.error.impl

import com.parisesoftware.commons.http.dto.internal.DataTransferObject
import com.parisesoftware.commons.http.notification.NotificationError
import spock.lang.Specification

class NotFoundErrorResponseTest extends Specification {

    def 'from(): should properly set the error list'() {

        given: 'a sample NotificationError with the sample message'
        NotificationError testNotificationError = new NotificationError(message: 'KHAAANNN!')

        and: 'a sample DataTransferObject, with an error list containing our sample NotificationError'
        DataTransferObject testDTO = new DataTransferObject()
        testDTO.notification.errors.add(testNotificationError)

        when: 'the factory method is invoked with our sample Data Transfer Object'
        NotFoundErrorResponse resultant = NotFoundErrorResponse.from(testDTO)

        then: 'the resultant response contains our sample error'
        resultant.errors.contains(testNotificationError)

    }

    def 'from(): should set the status code to 404'() {

        when: 'the factory method is invoked with our sample Data Transfer Object'
        NotFoundErrorResponse resultant = NotFoundErrorResponse.from(new DataTransferObject())

        then: 'the resultant response status code is 404'
        resultant.status == 404

    }

}
