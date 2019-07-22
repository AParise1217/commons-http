package com.parisesoftware.commons.http.dto.internal

import com.parisesoftware.commons.http.dto.IDataTransferObject
import spock.lang.Specification

class DataTransferObjectTest extends Specification {

    def "DataTransferObject: should be of type IDataTransferObject"() {

        when: 'a new DataTransferObject is instantiated'
        DataTransferObject resultant = Mock(DataTransferObject)

        then: 'the DataTransferObject is of type IDataTransferObject'
        resultant instanceof IDataTransferObject
    }

    def 'constructor: should initialize a new notification instance, with an empty errors list'() {

        when: 'a new DataTransferObject is created'
        DataTransferObject resultant = new DataTransferObject()

        then: 'the DataTransferObject should not be null'
        resultant != null

        and: 'the Notification is not null'
        resultant.notification != null

        and: 'the errors list is not null, and empty'
        with(resultant.notification) {
            errors != null
            errors.size() == 0
        }

        and: 'no exceptions are thrown'
        noExceptionThrown()
    }

}
