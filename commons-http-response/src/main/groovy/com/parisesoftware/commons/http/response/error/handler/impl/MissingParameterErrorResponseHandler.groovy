package com.parisesoftware.commons.http.response.error.handler.impl

import com.parisesoftware.commons.http.dto.IDataTransferObject
import com.parisesoftware.commons.http.notification.NotificationType
import com.parisesoftware.commons.http.response.GenericResponse
import com.parisesoftware.commons.http.response.error.handler.AbstractErrorResponseHandler
import com.parisesoftware.commons.http.response.error.impl.BadRequestErrorResponse

/**
 * {@inheritDoc}
 *
 * Missing Parameter Error Response Handler
 * <p>
 * Response Handler for Missing Parameter Errors
 *
 * @version 1.0
 * @since 1.0
 */
class MissingParameterErrorResponseHandler extends AbstractErrorResponseHandler {

    /**
     * Default Constructor
     * @param theNextHandler to delegate to, if this instance is not applicable
     */
    MissingParameterErrorResponseHandler(final AbstractErrorResponseHandler theNextHandler) {
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
                NotificationType.MISSING_PATH_PARAMETER,
                NotificationType.MISSING_BODY_PARAMETER,
                NotificationType.MISSING_QUERY_PARAMETER
        ]
    }

    /**
     * {@inheritDoc}
     * @version 1.0
     * @since 1.0
     */
    @Override
    protected GenericResponse getResponse(final IDataTransferObject aDTO) {
        return BadRequestErrorResponse.from(aDTO)
    }

}
