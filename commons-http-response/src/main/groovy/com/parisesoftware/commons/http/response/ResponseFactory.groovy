package com.parisesoftware.commons.http.response

import com.parisesoftware.commons.http.dto.IDataTransferObject
import com.parisesoftware.commons.http.response.error.handler.ErrorResponseHandlerChain


/**
 * Response Factory
 * <p>
 * Generic Factory to execute the creation of HTTP Responses
 *
 * @version 1.0
 * @since 1.0
 */
class ResponseFactory {

    /**
     * Factory Method to execute creating {@code GenericResponse} instances
     * @version 1.0
     *
     * @param aDTO to checked for errors, then delegated to the given Construction Strategy closure
     * @param aConstructionStrategy defining the construction of the response object
     *
     * @return {@code GenericResponse}
     * @since 1.0
     */
    GenericResponse createResponse(final IDataTransferObject aDTO,
                                   final Closure<GenericResponse> aConstructionStrategy) {

        if (aDTO.notification.hasErrors()) {
            return ErrorResponseHandlerChain.handlerChain.handle(aDTO)
        }

        return aConstructionStrategy(aDTO)
    }

}
