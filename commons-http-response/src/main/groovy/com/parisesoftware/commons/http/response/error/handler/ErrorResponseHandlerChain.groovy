package com.parisesoftware.commons.http.response.error.handler

import com.parisesoftware.commons.http.response.error.handler.impl.ApplicationErrorResponseHandler
import com.parisesoftware.commons.http.response.error.handler.impl.DefaultErrorResponseHandler
import com.parisesoftware.commons.http.response.error.handler.impl.InvalidParameterErrorResponseHandler
import com.parisesoftware.commons.http.response.error.handler.impl.MissingParameterErrorResponseHandler


/**
 * <h1>Error Response Handler Chain</h1>
 * <p>Entry-point to the Error Response Handler Chain</p>
 *
 * @since 1.0
 * @version 1.0
 */
class ErrorResponseHandlerChain {

    /**
     * Returns the Chain of Error Response Handlers
     * @return {@code IErrorResponseHandler}
     */
    static IErrorResponseHandler getHandlerChain() {
        return new InvalidParameterErrorResponseHandler(
                new MissingParameterErrorResponseHandler(
                        new ApplicationErrorResponseHandler(
                                new DefaultErrorResponseHandler()
                        )
                )
        )
    }

}
