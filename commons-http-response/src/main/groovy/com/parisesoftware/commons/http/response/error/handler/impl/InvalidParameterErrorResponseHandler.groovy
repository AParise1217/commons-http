package com.parisesoftware.commons.http.response.error.handler.impl

import com.parisesoftware.commons.http.dto.IDataTransferObject
import com.parisesoftware.commons.http.notification.NotificationType
import com.parisesoftware.commons.http.response.GenericResponse
import com.parisesoftware.commons.http.response.error.handler.AbstractErrorResponseHandler
import com.parisesoftware.commons.http.response.error.impl.UnprocessableEntityErrorResponse


/**
 * {@inheritDoc}
 *
 * Invalid Parameter Error Response Handler
 * <p>
 * Response Handler for Invalid Parameter Errors
 *
 * @version 1.0
 * @since 1.0
 */
class InvalidParameterErrorResponseHandler extends AbstractErrorResponseHandler {

    /**
     * Default Constructor
     * @param theNextHandler to delegate to, if this instance is not applicable
     */
    InvalidParameterErrorResponseHandler(final AbstractErrorResponseHandler theNextHandler) {
        super(theNextHandler)
    }

    /**
     * {@inheritDoc}
     * @version 1.0
     * @since 1.0
     */
    @Override
    protected List<NotificationType> getNotificationTypesHandled() {
        return [
                NotificationType.INVALID_BODY_PARAMETER,
                NotificationType.INVALID_QUERY_PARAMETER,
                NotificationType.INVALID_PATH_PARAMETER
        ]
    }

    /**
     * {@inheritDoc}
     * @version 1.0
     * @since 1.0
     */
    @Override
    protected GenericResponse getResponse(final IDataTransferObject aDTO) {
        return UnprocessableEntityErrorResponse.from(aDTO)
    }

}
