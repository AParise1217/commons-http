package com.parisesoftware.commons.http.response

import com.parisesoftware.commons.http.dto.internal.DataTransferObject
import com.parisesoftware.commons.http.notification.Notification
import com.parisesoftware.commons.http.notification.NotificationError
import com.parisesoftware.commons.http.response.error.handler.ErrorResponseHandlerChain
import com.parisesoftware.commons.http.response.error.handler.IErrorResponseHandler
import spock.lang.Specification

class ResponseFactoryTest extends Specification {

    def "newInstance(): should invoke ErrorResponseHandlerChain.getHandlerChain() when there are Notification Errors"() {

        given: 'a mocked IErrorResponseHandler'
        IErrorResponseHandler mockedErrorResponseHandler = Mock(IErrorResponseHandler)

        and: 'a mocked ErrorResponseHandlerChain'
        GroovyMock(ErrorResponseHandlerChain, global: true)
        1 * ErrorResponseHandlerChain.getHandlerChain() >> mockedErrorResponseHandler

        and: 'a sample DTO, with Notification Errors present'
        DataTransferObject testDTO = new DataTransferObject(
                notification: new Notification(errors: [new NotificationError(), new NotificationError()])
        )

        and: 'a mocked Closure'
        Closure mockClosure = Mock(Closure)

        when: 'a createInstance() is queried for in the ResponseFactory'
        new ResponseFactory().createResponse(testDTO, mockClosure)

        then: 'the execute() method of our mocked IErrorResponseHandler was invoked once'
        1 * mockedErrorResponseHandler.handle(testDTO)

        and: 'the mocked Closure was never invoked'
        0 * mockClosure(_ as DataTransferObject)
    }

    def "newInstance(): should invoke the Closure Factory Method when there are no Notification Errors"() {

        given: 'a mocked IErrorResponseHandler'
        IErrorResponseHandler mockedErrorResponseHandler = Mock(IErrorResponseHandler)

        and: 'a mocked ErrorResponseHandlerChain'
        GroovyMock(ErrorResponseHandlerChain, global: true)
        0 * ErrorResponseHandlerChain.getHandlerChain() >> mockedErrorResponseHandler

        and: 'a sample DTO, with no Notification Errors'
        DataTransferObject testDTO = new DataTransferObject()

        and: 'a mocked Closure'
        Closure mockClosure = Mock(Closure)

        when: 'a createInstance() is queried for in the ResponseFactory'
        new ResponseFactory().createResponse(testDTO, mockClosure)

        then: 'the sample DTO has no errors (sanity check)'
        !testDTO.notification.hasErrors()

        then: 'the execute() method of our mocked IErrorResponseHandler was never invoked'
        0 * mockedErrorResponseHandler.handle(_ as DataTransferObject)

        and: 'the mocked Closure was invoked once'
        1 * mockClosure(testDTO)
    }

}
