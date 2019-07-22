package com.parisesoftware.commons.http.response.error.handler.impl

import com.parisesoftware.commons.http.dto.IDataTransferObject
import com.parisesoftware.commons.http.notification.NotificationType
import com.parisesoftware.commons.http.response.GenericResponse
import com.parisesoftware.commons.http.response.error.handler.AbstractErrorResponseHandler
import com.parisesoftware.commons.http.response.error.impl.InternalServerErrorResponse


/**
 * {@inheritDoc}
 *
 * Default Error Response Handler
 * <p>
 * Catch-All Error Response Handler
 *
 * @version 1.0
 * @since 1.0
 */
class DefaultErrorResponseHandler extends AbstractErrorResponseHandler {

    /**
     * Default Constructor
     * @param theNextHandler to delegate to, if this instance is not applicable
     */
    DefaultErrorResponseHandler(final AbstractErrorResponseHandler theNextHandler) {
        super(theNextHandler)
    }

    /**
     * {@inheritDoc}
     * This always returns true
     * @version 1.0
     * @since 1.0
     */
    @Override
    protected boolean isDefaultHandler() {
        return true
    }

    /**
     * {@inheritDoc}
     * @version 1.0
     * @since 1.0
     */
    @Override
    protected List<NotificationType> getNotificationTypesHandled() {
        return []
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
