package project.views.cleancoroutines.data.model


import com.google.gson.annotations.SerializedName


data class Error(
        @SerializedName("error_code")
        val errorCode: Int?,
        @SerializedName("error_msg")
        val errorMsg: String?,
        @SerializedName("request_params")
        val requestParams: List<RequestParam?>?,
        @SerializedName("captcha_sid")
        val captchaSid: Long?,
        @SerializedName("captcha_img")
        val captchaImg: String?
) {
    data class RequestParam(
            @SerializedName("key")
            val key: String?,
            @SerializedName("value")
            val value: String?
    )
}
