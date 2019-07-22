package com.parisesoftware.commons.http.dto

import com.parisesoftware.commons.http.notification.Notification

/**
 * <h1>Data Transfer Object</h1>
 * <p>
 *     <b>DTO Supertype</b> -
 *          provides general functionality to create and access the Notification
 * </p>
 *
 * @since 1.0
 * @version 1.0
 */
interface IDataTransferObject {

    /**
     * Fetch the Notification Instance
     * @return {@code Notification}
     */
    Notification getNotification()

}
