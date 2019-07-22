package com.parisesoftware.commons.http.response.error.impl

import com.parisesoftware.commons.http.dto.IDataTransferObject
import com.parisesoftware.commons.http.response.error.ErrorResponse


/**
 * {@inheritDoc}
 *
 * <h1>Internal Server Error Response</h1>
 * <p>HTTP 500</p>
 * <p>
 *     Error Response for when the server encountered an unexpected condition
 *      which prevented it from fulfilling the request
 * </p>
 *
 * @since   1.0
 * @version 1.0
 */
class InternalServerErrorResponse extends ErrorResponse {

    private static final int HTTP_CODE_INTERNAL_SERVER_ERROR = 500

    /**
     * Private Constructor; Use {@link InternalServerErrorResponse#from} factory method instead
     * @param status the HTTP Status Code associated with this Error Response
     * @param errors the Array of Errors to include in the response
     */
    private InternalServerErrorResponse(int status, List errors) {
        super(status, errors)
    }

    /**
     * Factory Method to execute object creation
     * @param data the {@link IDataTransferObject} to construct this based off of
     * @return {@code NotFoundErrorResponse} the fully constructed NotFoundErrorResponse object
     */
    static InternalServerErrorResponse from(IDataTransferObject data) {
        return new InternalServerErrorResponse(HTTP_CODE_INTERNAL_SERVER_ERROR, data.notification.errors)
    }

}
