package com.parisesoftware.commons.http.notification

/**
 * <h1>Notification Type</h1>
 *
 * @since   1.0
 * @version 1.0
 */
enum NotificationType {

    /**
     * an Internal Application Error occurred
     */
    APPLICATION_ERROR,

    /**
     * the Parameter was Provided in the Request Body, but invalid
     */
    INVALID_BODY_PARAMETER,

    /**
     * the Parameter was Missing from the Request Body
     */
    MISSING_BODY_PARAMETER,

    /**
     * the Parameter was Provided in the Path, but invalid
     */
    INVALID_PATH_PARAMETER,

    /**
     * the Parameter was Missing from the Path
     */
    MISSING_PATH_PARAMETER,

    /**
     * the Parameter was Provided in the URI, but invalid
     */
    INVALID_QUERY_PARAMETER,

    /**
     * the Parameter was Missing from the URI
     */
    MISSING_QUERY_PARAMETER

}
