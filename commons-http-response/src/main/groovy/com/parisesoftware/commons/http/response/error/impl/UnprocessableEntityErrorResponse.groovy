package com.parisesoftware.commons.http.response.error.impl

import com.parisesoftware.commons.http.dto.IDataTransferObject
import com.parisesoftware.commons.http.response.error.ErrorResponse


/**
 * {@inheritDoc}
 *
 * <h1>Not Found Response</h1>
 * <p>HTTP 422</p>
 * <p>Error Response for when the server understands the request body, but the content is invalid</p>
 *
 * @since   1.0
 * @version 1.0
 */
class UnprocessableEntityErrorResponse extends ErrorResponse {

    private static final int HTTP_CODE_UNPROCESSABLE_ENTITY = 422

    /**
     * Private Constructor; Use {@link UnprocessableEntityErrorResponse#from} factory method instead
     * @param status the HTTP Status Code associated with this Error Response
     * @param errors the Array of Errors to include in the response
     */
    private UnprocessableEntityErrorResponse(int status, List errors) {
        super(status, errors)
    }

    /**
     * Factory Method to execute object creation
     * @param data the {@link IDataTransferObject} to construct this based off of
     * @return {@code UnprocessableEntityErrorResponse} the fully constructed UnprocessableEntityErrorResponse object
     */
    static UnprocessableEntityErrorResponse from(IDataTransferObject data) {
        return new UnprocessableEntityErrorResponse(HTTP_CODE_UNPROCESSABLE_ENTITY, data.notification.errors)
    }

}
