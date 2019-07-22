package com.parisesoftware.commons.http.notification

/**
 * <h1>Notification Error</h1>
 * <p>Simple Wrapper for an Error Message</p>
 *
 * @since   1.0
 * @version 1.0
 */
class NotificationError {

    private static final NotificationType DEFAULT_TYPE = NotificationType.APPLICATION_ERROR

    /**
     * the Error Message
     */
    String message

    /**
     * the Error Type
     */
    NotificationType type = DEFAULT_TYPE

}
