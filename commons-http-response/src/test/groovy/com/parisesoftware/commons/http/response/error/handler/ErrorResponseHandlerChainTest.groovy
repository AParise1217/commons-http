package com.parisesoftware.commons.http.response.error.handler

import com.parisesoftware.commons.http.response.error.handler.impl.ApplicationErrorResponseHandler
import com.parisesoftware.commons.http.response.error.handler.impl.DefaultErrorResponseHandler
import com.parisesoftware.commons.http.response.error.handler.impl.InvalidParameterErrorResponseHandler
import com.parisesoftware.commons.http.response.error.handler.impl.MissingParameterErrorResponseHandler
import spock.lang.Specification

class ErrorResponseHandlerChainTest extends Specification {

    def "getHandlerChain(): should have the InvalidParameterErrorResponseHandler first"() {

        given: 'the Error Response Handler Chain\'s first handler'
        AbstractErrorResponseHandler testErrorHandlerChain = ErrorResponseHandlerChain.getHandlerChain() as AbstractErrorResponseHandler

        expect: 'the first handler to be of type InvalidParameterErrorResponseHandler'
        testErrorHandlerChain.class == InvalidParameterErrorResponseHandler.class

        and: 'the next link is not null'
        testErrorHandlerChain.nextHandler != null
    }

    def "getHandlerChain(): should have the MissingParameterErrorResponseHandler second"() {

        given: 'the Error Response Handler Chain\'s second handler'
        AbstractErrorResponseHandler testErrorHandlerChain =
                (ErrorResponseHandlerChain.getHandlerChain() as AbstractErrorResponseHandler).nextHandler

        expect: 'the second handler to be of type MissingParameterErrorResponseHandler'
        testErrorHandlerChain.class == MissingParameterErrorResponseHandler.class

        and: 'the next link is not null'
        testErrorHandlerChain.nextHandler != null
    }

    def "getHandlerChain(): should have the ApplicationErrorResponseHandler third"() {

        given: 'the Error Response Handler Chain\'s third handler'
        AbstractErrorResponseHandler testErrorHandlerChain =
                (ErrorResponseHandlerChain.getHandlerChain() as AbstractErrorResponseHandler).nextHandler.nextHandler

        expect: 'the third handler to be of type ApplicationErrorResponseHandler'
        testErrorHandlerChain.class == ApplicationErrorResponseHandler.class

        and: 'the next link is not null'
        testErrorHandlerChain.nextHandler != null
    }

    def "getHandlerChain(): should have the DefaultErrorResponseHandler last"() {

        given: 'the Error Response Handler Chain\'s fourth handler'
        AbstractErrorResponseHandler testErrorHandlerChain =
                (ErrorResponseHandlerChain.getHandlerChain() as AbstractErrorResponseHandler)
                        .nextHandler.nextHandler.nextHandler

        expect: 'the third handler to be of type DefaultErrorResponseHandler'
        testErrorHandlerChain.class == DefaultErrorResponseHandler.class

        and: 'the next link is null'
        testErrorHandlerChain.nextHandler == null

        and: 'the link is the default'
        testErrorHandlerChain.isDefaultHandler()
    }

}
