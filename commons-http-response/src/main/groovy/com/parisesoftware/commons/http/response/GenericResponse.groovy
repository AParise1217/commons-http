package com.parisesoftware.commons.http.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.commons.lang3.builder.ToStringBuilder

import java.sql.Timestamp

/**
 * Generic Response
 * <p>
 *     <b>HTTP Response Supertype</b> -
 *          provides general functionality common amongst all HTTP Responses
 *
 * @version 1.0
 * @since 1.0
 */
abstract class GenericResponse implements Serializable {

    /**
     * Allows for Seamless Compatibility during Serialization/Deserialization
     * @version 1.0
     * @since 1.0
     */
    private static final long serialVersionUID = 6776279384815632436L

    /**
     * HTTP Status Code
     */
    private final int status

    /**
     * Timestamp
     */
    private final Timestamp timestamp

    /**
     * Default Constructor
     * @param aHTTPStatusCode
     */
    protected GenericResponse(final int aHTTPStatusCode) {
        this.status = aHTTPStatusCode
        this.timestamp = new Timestamp(new Date().time)
    }

    /**
     * Public Accessor for the HTTP Status Code
     *  For the JSON Serializer to include it in the HTTP Response
     * @version 1.0
     * @return {@code int} the HTTP Status Code
     * @since 1.0
     */
    @JsonProperty('status')
    int getStatus() {
        return status
    }

    /**
     * Public Accessor for the Timestamp
     *  For the JSON Serializer to include it in the HTTP Response
     * @version 1.0
     * @return {@code int} the HTTP Status Code
     * @since 1.0
     */
    @JsonProperty('timestamp')
    String getTimestamp() {
        return timestamp.toString()
    }

    /**
     * Success Flag
     * @version 1.0
     * @since 1.0
     */
    @JsonProperty('success')
    abstract boolean isSuccessful()

    @Override
    String toString() {
        return new ToStringBuilder(this)
                .append('status', status)
                .append('timestamp', timestamp)
                .append('success', isSuccessful())
                .toString()
    }

}
