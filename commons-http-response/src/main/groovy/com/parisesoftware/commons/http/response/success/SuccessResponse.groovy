package com.parisesoftware.commons.http.response.success

import com.parisesoftware.commons.http.response.GenericResponse

/**
 * {@inheritDoc}
 *
 *
 * @since   1.0
 * @version 1.0
 */
class SuccessResponse extends GenericResponse {

    /**
     * the HTTP 200 Okay Status Code to be included in HTTP Responses of this Type
     */
    private static final int HTTP_CODE_OKAY = 200

    /**
     * Default Constructor
     */
    SuccessResponse() {
        super(HTTP_CODE_OKAY)
    }

    @Override
    boolean isSuccessful() {
        return true
    }

}
