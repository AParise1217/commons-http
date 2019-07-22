package com.parisesoftware.commons.http.response.success

import spock.lang.Specification

class SuccessResponseTest extends Specification {

    def "getStatus(): should return the default HTTP Okay Status Code"() {

        when: 'a new SuccessResponse is created using the sample Status Code, and the set status is immediately fetched'
        int resultant = new SuccessResponse().getStatus()

        then: 'the resultant status code is equal to the set Okay status code'
        resultant == 200
    }

    def "isSuccessful(): should return true"() {

        when: 'a new SuccessResponse is created, and the success flag is immediately fetched'
        boolean resultant = new SuccessResponse().isSuccessful()

        then: 'the success flag to be false'
        resultant
    }

}
