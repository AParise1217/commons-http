package com.parisesoftware.commons.http.response.error.handler.impl

import com.parisesoftware.commons.http.dto.IDataTransferObject
import com.parisesoftware.commons.http.notification.NotificationType
import com.parisesoftware.commons.http.response.GenericResponse
import com.parisesoftware.commons.http.response.error.handler.AbstractErrorResponseHandler
import com.parisesoftware.commons.http.response.error.impl.InternalServerErrorResponse


/**
 * {@inheritDoc}
 *
 * Application Error Response Handler
 * <p>
 * Response Handler for Application Errors
 *
 * @version 1.0
 * @since 1.0
 */
class ApplicationErrorResponseHandler extends AbstractErrorResponseHandler {

    /**
     * Default Constructor
     * @param theNextHandler to delegate to, if this instance is not applicable
     */
    ApplicationErrorResponseHandler(final AbstractErrorResponseHandler theNextHandler) {
        super(theNextHandler)
    }

    /**
     * {@inheritDoc}
     * @version 1.0
     * @since 1.0
     */
    @Override
    protected List<NotificationType> getNotificationTypesHandled() {
        return [NotificationType.APPLICATION_ERROR]
    }

    /**
     * {@inheritDoc}
     * @version 1.0
     * @since 1.0
     */
    @Override
    protected GenericResponse getResponse(final IDataTransferObject aDTO) {
        return InternalServerErrorResponse.from(aDTO)
    }

}
