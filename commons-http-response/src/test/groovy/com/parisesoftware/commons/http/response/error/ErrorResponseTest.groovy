package com.parisesoftware.commons.http.response.error

import com.parisesoftware.commons.http.notification.NotificationError
import spock.lang.Specification

class ErrorResponseTest extends Specification {

    def "getStatus(): should return the status code passed in the constructor"() {
        given: 'a sample Status Code'
        int testStatusCode = 9999

        when: 'a new ErrorResponse is created using the sample Status Code, and the set status is immediately fetched'
        int resultant = new ErrorResponse(testStatusCode, []).getStatus()

        then: 'the passed in sample Status Code to be equal to our resultant status code'
        resultant == testStatusCode
    }

    def "isSuccessful(): should return false"() {

        when: 'a new ErrorResponse is created, and the success flag is immediately fetched'
        boolean resultant = new ErrorResponse(123, []).isSuccessful()

        then: 'the success flag to be false'
        !resultant
    }

    def "getErrors(): should return the error array passed in the constructor"() {
        given: 'a sample Status Code'
        int testStatusCode = 9999

        and: 'some sample Errors'
        List testErrorList = [new NotificationError(message: 'error1'), new NotificationError(message: 'error2')]

        when: 'a new ErrorResponse is created using the sample Error List, and the set errors are immediately fetched'
        List resultant = new ErrorResponse(testStatusCode, testErrorList).getErrors()

        then: 'the passed in sample Status Code to be equal to our resultant status code'
        resultant == testErrorList
    }

}
