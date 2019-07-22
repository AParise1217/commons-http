package com.parisesoftware.commons.http.notification

/**
 * <h1>Notification</h1>
 * <p>Captures errors in the domain layer</p>
 *
 * @since   1.0
 * @version 1.0
 */
class Notification {

    /**
     * Collection of Errors
     */
    List<NotificationError> errors = []

    /**
     * Check if there are Errors Present in this Notification
     * @return {@code boolean}
     */
    boolean hasErrors() {
        return errors.size() > 0
    }

    /**
     * Checks if there are Errors Present in the Notification of the given type
     * @param type the type to check for
     * @return {@code boolean} true if there is an error of that type present, false if not
     */
    boolean hasErrorsFor(NotificationType type) {
        return errors.any { it.type == type }
    }

    /**
     * Checks if there is the given Error Present in the Errors Collection
     * @param error the error to check for
     * @return {@code boolean} true if the error is present, false if not
     */
    boolean hasError(NotificationError error) {
        return errors.any { it == error }
    }

    /**
     * Replaces the given existingError, with the given replacementError
     * @param existingError the Error to be replaced
     * @param replacementError the Error to be added, if the given existingError is present
     */
    void replaceErrorIfPresent(NotificationError existingError, NotificationError replacementError) {
        if(hasError(existingError)) {
            errors.remove(existingError)
            errors.add(replacementError)
        }
    }

}
