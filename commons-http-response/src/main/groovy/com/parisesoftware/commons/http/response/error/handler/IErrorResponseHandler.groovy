package com.parisesoftware.commons.http.response.error.handler

import com.parisesoftware.commons.http.dto.IDataTransferObject
import com.parisesoftware.commons.http.response.GenericResponse


/**
 * Error Response Handler Interface
 * <p>
 * Explicitly defined interface to statically type the implementing Error Response Handlers
 *
 * @version 1.0
 * @since 1.0
 */
interface IErrorResponseHandler {

    /**
     * Returns an instance of {@link GenericResponse} based on the data
     *      contained in the given {@link IDataTransferObject}
     * @version 1.0
     * @param aDTO {@code IDataTransferObject} containing the errors collected within a single request scope
     * @return {@code GenericResponse}
     * @since 1.0
     */
    GenericResponse handle(IDataTransferObject aDTO)

}
