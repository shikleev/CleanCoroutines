package project.views.cleancoroutines.data

import com.google.gson.annotations.SerializedName
import project.views.cleancoroutines.data.model.Error
import java.io.Serializable

class ResponseWrapper<T> : Serializable {

    @SerializedName("response")
    val data: T? = null
    @SerializedName("error")
    val error: Error? = null
}