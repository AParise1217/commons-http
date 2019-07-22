package com.parisesoftware.commons.http.response.error.impl

import com.parisesoftware.commons.http.dto.internal.DataTransferObject
import com.parisesoftware.commons.http.notification.NotificationError
import spock.lang.Specification

class InternalServerErrorResponseTest extends Specification {

    def 'from(): should properly set the error list'() {

        given: 'a sample NotificationError with the sample message'
        NotificationError testNotificationError = new NotificationError(message: 'I\'m a doctor, not a brick layer')

        and: 'a sample DataTransferObject, with an error list containing our sample NotificationError'
        DataTransferObject testDTO = new DataTransferObject()
        testDTO.notification.errors.add(testNotificationError)

        when: 'the factory method is invoked with our sample Data Transfer Object'
        InternalServerErrorResponse resultant = InternalServerErrorResponse.from(testDTO)

        then: 'the resultant response contains our sample error'
        resultant.errors.contains(testNotificationError)

    }

    def 'from(): should set the status code to 500'() {

        when: 'the factory method is invoked with our sample Data Transfer Object'
        InternalServerErrorResponse resultant = InternalServerErrorResponse.from(new DataTransferObject())

        then: 'the resultant response status code is 500'
        resultant.status == 500

    }

}
