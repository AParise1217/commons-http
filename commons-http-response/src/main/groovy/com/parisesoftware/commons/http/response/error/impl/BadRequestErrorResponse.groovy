package com.parisesoftware.commons.http.response.error.impl

import com.parisesoftware.commons.http.dto.IDataTransferObject
import com.parisesoftware.commons.http.response.error.ErrorResponse


/**
 * {@inheritDoc}
 *
 * <h1>Bad Request Response</h1>
 * <p>HTTP 400</p>
 * <p>Error Response for when the request body could not be parsed</p>
 *
 * @since 1.0
 * @version 1.0
 */
class BadRequestErrorResponse extends ErrorResponse {

    private static final int HTTP_CODE_BAD_REQUEST = 400

    /**
     * Private Constructor; Use {@link BadRequestErrorResponse#from} factory method instead
     * @param status the HTTP Status Code associated with this Error Response
     * @param errors the Array of Errors to include in the response
     */
    private BadRequestErrorResponse(int status, List errors) {
        super(status, errors)
    }

    /**
     * Factory Method to execute object creation
     * @param data the {@link IDataTransferObject} to construct this based off of
     * @return {@code BadRequestErrorResponse} the fully constructed BadRequestErrorResponse object
     */
    static BadRequestErrorResponse from(IDataTransferObject data) {
        return new BadRequestErrorResponse(HTTP_CODE_BAD_REQUEST, data.notification.errors)
    }

}
