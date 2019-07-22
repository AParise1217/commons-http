package com.parisesoftware.commons.http.response.error.handler

import com.parisesoftware.commons.http.dto.IDataTransferObject
import com.parisesoftware.commons.http.notification.NotificationType
import com.parisesoftware.commons.http.response.GenericResponse
import groovy.transform.PackageScope

/**
 * {@inheritDoc}
 *
 * Abstract Error Response Handler
 * <p>
 * Default Implementation of {@link IErrorResponseHandler}
 *
 * @version 1.0
 * @since 1.0
 */
abstract class AbstractErrorResponseHandler implements IErrorResponseHandler {

    protected AbstractErrorResponseHandler nextHandler

    /**
     * Default Constructor
     * @param theNextHandler to delegate to, if this instance is not applicable
     */
    protected AbstractErrorResponseHandler(final AbstractErrorResponseHandler theNextHandler) {
        this.nextHandler = theNextHandler
    }

    /**
     * {@inheritDoc}
     * @version 1.0
     * @since 1.0
     */
    GenericResponse handle(final IDataTransferObject aDTO) {
        // if this is the correct handler, then return the response
        if (isApplicableHandler(aDTO)) {
            return getResponse(aDTO)
        }

        // otherwise, delegate to the next handler
        this.nextHandler?.handle(aDTO)
    }

    /**
     * Checks to see if this implementing Error Response Handler can handle the request
     * @version 1.0
     * @param aDTO containing errors to check for
     * @return {@code boolean}
     * @since 1.0
     */
    @PackageScope
    boolean isApplicableHandler(final IDataTransferObject aDTO) {
        return isDefaultHandler() || isHandlerFor(aDTO)
    }

    /**
     * Checks to see if this implementation handles any of the Notification Types present in the given DTO
     * @version 1.0
     * @param aDTO to check if this is the proper handler of that error type
     * @return {@code boolean}
     * @since 1.0
     */
    @PackageScope
    boolean isHandlerFor(final IDataTransferObject aDTO) {
        return aDTO.notification.errors.any {
            this.notificationTypesHandled.contains(it.type)
        }
    }

    /**
     * Flag to skip checking/delegation based on error types, and skip to execution
     *  Note: only one implementation of this per chain should have this flag overridden to true
     * @version 1.0
     *
     * @return {@code boolean} true to skip checking/delegation; false if not
     *
     * @since 1.0
     */
    protected boolean isDefaultHandler() {
        return false
    }

    /**
     * Returns a collection of the {@code NotificationType}s supported by this implementing Error Response Handler
     * @version 1.0
     * @return {@code List}
     * @since 1.0
     */
    protected abstract List<NotificationType> getNotificationTypesHandled()

    /**
     * Template Hook Method for subclasses to define the Response building
     * @version 1.0
     * @param aDTO containing Contextual Data used to build the response with
     * @return {@code GenericResponse}
     * @since 1.0
     */
    protected abstract GenericResponse getResponse(IDataTransferObject aDTO)

}
