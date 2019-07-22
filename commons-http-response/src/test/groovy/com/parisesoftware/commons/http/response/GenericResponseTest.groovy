package com.parisesoftware.commons.http.response

import spock.lang.Specification

import java.sql.Timestamp

class GenericResponseTest extends Specification {

    def "getStatus(): should return the status code passed in the constructor"() {
        given: 'a sample Status Code'
        int testStatusCode = 9999

        when: 'a new GenericResponse is created using the sample Status Code, and the set status is immediately fetched'
        int resultant = new GenericResponseExtension(testStatusCode).getStatus()

        then: 'the passed in sample Status Code to be equal to our resultant status code'
        resultant == testStatusCode
    }

    def "timestamp: should be properly set"() {
        given: 'a sample Date'
        Date testDate = new Date(1492, 2, 44)

        and: 'a globally mocked Date constructor'
        Date anyDate = GroovySpy(global:true)
        1 * new Date() >> testDate

        and: 'a globally mocked Timestamp constructor'
        Timestamp anyTimestamp = GroovyMock(global:true)
        new Timestamp(testDate.time) >> new Timestamp(testDate.time)

        when: 'a new GenericResponse is created, and the Timestamp is immediately fetched'
        String resultant = new GenericResponseExtension(123).getTimestamp()

        then: 'the passed in sample Status Code to be equal to our resultant status code'
        resultant == new Timestamp(testDate.time).toString()
    }

    /**
     * Test Class to verify `GenericResponse` is working as expected at the unit level
     */
    class GenericResponseExtension extends GenericResponse {

        /**
         * Default Constructor
         * @param status the HTTP Status Code
         */
        GenericResponseExtension(int status) {
            super(status)
        }

        @Override
        boolean isSuccessful() {
            return false
        }
    }

}
