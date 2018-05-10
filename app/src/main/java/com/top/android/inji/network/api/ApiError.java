/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package com.top.android.inji.network.api;

import android.util.SparseIntArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import com.top.android.inji.R;
import com.top.android.inji.network.AuthenticationException;
import com.top.android.inji.network.ResponseConversionException;
import com.top.android.inji.network.api.ApiContract.Response.Error;
import com.top.android.inji.network.api.ApiContract.Response.Error.Codes.*;
import com.top.android.inji.util.StringUtils;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ApiError extends Throwable {

    private static final SparseIntArray ERROR_CODE_STRING_RES_MAP;
    static {

        ERROR_CODE_STRING_RES_MAP = new SparseIntArray();

        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Custom.INVALID_ERROR_RESPONSE,
                R.string.api_error_invalid_error_response);

        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.INVALID_REQUEST_997,
                R.string.api_error_invalid_request_997);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.UNKNOWN_V2_ERROR, R.string.api_error_unknown_v2_error);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.NEED_PERMISSION, R.string.api_error_need_permission);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.URI_NOT_FOUND, R.string.api_error_uri_not_found);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.MISSING_ARGS, R.string.api_error_missing_args);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.IMAGE_TOO_LARGE, R.string.api_error_image_too_large);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.HAS_BAN_WORD, R.string.api_error_has_ban_word);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.INPUT_TOO_SHORT, R.string.api_error_input_too_short);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.TARGET_NOT_FOUND, R.string.api_error_target_not_found);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.NEED_CAPTCHA, R.string.api_error_need_captcha);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.IMAGE_UNKNOWN, R.string.api_error_image_unknown);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.IMAGE_WRONG_FORMAT,
                R.string.api_error_image_wrong_format);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.IMAGE_WRONG_CK, R.string.api_error_image_wrong_ck);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.IMAGE_CK_EXPIRED, R.string.api_error_image_ck_expired);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.TITLE_MISSING, R.string.api_error_title_missing);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Base.DESC_MISSING, R.string.api_error_desc_missing);

        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_REQUEST_SCHEME,
                R.string.api_error_token_invalid_request_scheme);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_REQUEST_METHOD,
                R.string.api_error_token_invalid_request_method);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.ACCESS_TOKEN_IS_MISSING,
                R.string.api_error_token_access_token_is_missing);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_ACCESS_TOKEN,
                R.string.api_error_token_invalid_access_token);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_APIKEY,
                R.string.api_error_token_invalid_apikey);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.APIKEY_IS_BLOCKED,
                R.string.api_error_token_apikey_is_blocked);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.ACCESS_TOKEN_HAS_EXPIRED,
                R.string.api_error_token_access_token_has_expired);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_REQUEST_URI,
                R.string.api_error_token_invalid_request_uri);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_CREDENCIAL1,
                R.string.api_error_token_invalid_credencial1);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_CREDENCIAL2,
                R.string.api_error_token_invalid_credencial2);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.NOT_TRIAL_USER,
                R.string.api_error_token_not_trial_user);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.RATE_LIMIT_EXCEEDED1,
                R.string.api_error_token_rate_limit_exceeded1);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.RATE_LIMIT_EXCEEDED2,
                R.string.api_error_token_rate_limit_exceeded2);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.REQUIRED_PARAMETER_IS_MISSING,
                R.string.api_error_token_required_parameter_is_missing);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.UNSUPPORTED_GRANT_TYPE,
                R.string.api_error_token_unsupported_grant_type);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.UNSUPPORTED_RESPONSE_TYPE,
                R.string.api_error_token_unsupported_response_type);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.CLIENT_SECRET_MISMATCH,
                R.string.api_error_token_client_secret_mismatch);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.REDIRECT_URI_MISMATCH,
                R.string.api_error_token_redirect_uri_mismatch);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_AUTHORIZATION_CODE,
                R.string.api_error_token_invalid_authorization_code);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_REFRESH_TOKEN,
                R.string.api_error_token_invalid_refresh_token);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.USERNAME_PASSWORD_MISMATCH,
                R.string.api_error_token_username_password_mismatch);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_USER, R.string.api_error_token_invalid_user);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.USER_HAS_BLOCKED,
                R.string.api_error_token_user_has_blocked);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.ACCESS_TOKEN_HAS_EXPIRED_SINCE_PASSWORD_CHANGED,
                R.string.api_error_token_access_token_has_expired_since_password_changed);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.ACCESS_TOKEN_HAS_NOT_EXPIRED,
                R.string.api_error_token_access_token_has_not_expired);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_REQUEST_SCOPE,
                R.string.api_error_token_invalid_request_scope);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.INVALID_REQUEST_SOURCE,
                R.string.api_error_token_invalid_request_source);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.THIRDPARTY_LOGIN_AUTH_FAILED,
                R.string.api_error_token_thirdparty_login_auth_failed);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Token.USER_LOCKED, R.string.api_error_token_user_locked);

        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Followship.ALREADY_FOLLOWED,
                R.string.api_error_followship_already_followed);
        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Followship.NOT_FOLLOWED_YET,
                R.string.api_error_followship_not_followed_yet);

        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.Broadcast.NOT_FOUND, R.string.api_error_broadcast_not_found);

        ERROR_CODE_STRING_RES_MAP.put(ApiContract.Response.Error.Codes.RebroadcastBroadcast.REBROADCASTED_BROADCAST_DELETED,
                R.string.api_error_rebroadcast_broadcast_deleted);
    }

    public Object response;
    public String bodyString;
    public JSONObject bodyJson;
    public int code;
    public String localizedMessage;
    public String message;
    public String request;
    private String errMsg;

    public ApiError(Throwable throwable) {
        super(throwable);
    }

    public ApiError(Object response, ResponseBody responseBody) {
        this.response = response;
        if (responseBody != null) {
            // Don't throw an exception from here, just do the best we can.
            try {
                bodyString = responseBody.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            responseBody.close();
            if (bodyString != null) {
                parseResponse();
            }
        }
    }

    public ApiError(Response response) {
        this(response, response.body());
    }

    public ApiError(retrofit2.Response<?> response) {
        this(response, response.errorBody());
    }

    public ApiError(String err) {
        this.errMsg = err;
    }

    private void parseResponse() {
        try {
            bodyJson = new JSONObject(bodyString);
            code = bodyJson.optInt(ApiContract.Response.Error.CODE, 0);
            message = bodyJson.optString(ApiContract.Response.Error.MSG, null);
            request = bodyJson.optString(ApiContract.Response.Error.REQUEST, null);
            localizedMessage = bodyJson.optString(ApiContract.Response.Error.LOCALIZED_MESSAGE, null);
        } catch (JSONException e) {
            e.printStackTrace();
            code = ApiContract.Response.Error.Codes.Custom.INVALID_ERROR_RESPONSE;
        }
    }

    public String getErrorString() {

        if(!StringUtils.isBlank(errMsg)){
            return errMsg;
        }

        if (response == null) {
            // Return as the wrapped error.
            // We only have two constructors, so this cast is safe.
            return getErrorString(this.getCause());
        }

        return "未知错误";
    }

    public static String getErrorString(Throwable error) {
        if (error instanceof ResponseConversionException) {
            return "解析错误";
        } else if (error instanceof AuthenticationException) {
            return "授权错误";
        } else if (error instanceof SocketTimeoutException) {
            return "请求超时";
        } else if (error instanceof UnknownHostException) {
            return "无网络连接";
        } else if (error instanceof IOException) {
            return "网络错误";
        } else if (error instanceof ApiError) {
            return ((ApiError) error).getErrorString();
        } else {
            return "未知错误";
        }
    }


    @Override
    public String toString() {
        return "ApiError{" +
                "response=" + response +
                ", bodyString='" + bodyString + '\'' +
                ", bodyJson=" + bodyJson +
                ", code=" + code +
                ", localizedMessage='" + localizedMessage + '\'' +
                ", message='" + message + '\'' +
                ", request='" + request + '\'' +
                '}';
    }
}