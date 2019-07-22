package com.parisesoftware.commons.http.response.error.impl

import com.parisesoftware.commons.http.dto.IDataTransferObject
import com.parisesoftware.commons.http.response.error.ErrorResponse


/**
 * {@inheritDoc}
 *
 * <h1>Not Found Response</h1>
 * <p>HTTP 404</p>
 * <p>Error Response for when the resource was not found</p>
 *
 * @since 1.0
 * @version 1.0
 */
class NotFoundErrorResponse extends ErrorResponse {

    private static final int HTTP_CODE_NOT_FOUND = 404

    /**
     * Private Constructor; Use {@link NotFoundErrorResponse#from} factory method instead
     * @param status the HTTP Status Code associated with this Error Response
     * @param errors the Array of Errors to include in the response
     */
    private NotFoundErrorResponse(int status, List errors) {
        super(status, errors)
    }

    /**
     * Factory Method to execute object creation
     * @param data the {@link IDataTransferObject} to construct this based off of
     * @return {@code NotFoundErrorResponse} the fully constructed NotFoundErrorResponse object
     */
    static NotFoundErrorResponse from(IDataTransferObject data) {
        return new NotFoundErrorResponse(HTTP_CODE_NOT_FOUND, data.notification.errors)
    }

}
