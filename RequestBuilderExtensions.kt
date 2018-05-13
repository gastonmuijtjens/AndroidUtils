package com.gastonmuijtjens.androidutils.extensions

import okhttp3.Request

/**
 * Convenience extension methods for OkHttp Request.Builder
 * @author Gaston Muijtjens
 * @since 18-03-2018
 */

/**
 * Adds the authorization header for a given authorization string
 *
 * @param authorization The authorization string to add to the header
 * @return Builder with the authorization header added
 */
fun Request.Builder.addAuthorization(authorization: String): Request.Builder {
    return addHeader("Authorization", authorization)
}

/**
 * Adds the Bearer authorization header for a given token
 *
 * @param token The token to add to the Bearer authorization
 * @return Builder with the Bearer authorization header added
 */
fun Request.Builder.addBearerAuthorization(token: String): Request.Builder {
    return addAuthorization("Bearer $token")
}

/**
 * Adds the content type header for a given content type
 *
 * @param contentType The content type to add to the header
 * @return Builder with the content type header added
 */
fun Request.Builder.addContentType(contentType: String): Request.Builder {
    return addHeader("Content-Type", contentType)
}