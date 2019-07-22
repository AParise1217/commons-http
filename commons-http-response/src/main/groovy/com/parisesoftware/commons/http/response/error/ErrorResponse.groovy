package com.parisesoftware.commons.http.response.error

import com.parisesoftware.commons.http.response.GenericResponse

/**
 * {@inheritDoc}
 *
 * <h1>Error Response</h1>
 * <p>HTTP 4xx or 5xx</p>
 *
 * @since   1.0
 * @version 1.0
 */
class ErrorResponse extends GenericResponse {

    private final List errors

    /**
     * Default Constructor
     *
     * @param status the HTTP Status Code
     * @param errors the Error List to include in the HTTP Response
     */
    ErrorResponse(final int status, final List errors) {
        super(status)
        this.errors = errors
    }

    /**
     * Accessor for the Serializer to include it in the HTTP Response
     * @return {@code List}
     */
    List getErrors() {
        return errors
    }

    @Override
    boolean isSuccessful() {
        return false
    }
}
