package project.views.cleancoroutines.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import project.views.cleancoroutines.data.model.Users
import project.views.cleancoroutines.mvvm.Event

class ActivityViewModel : BaseViewModel() {

    val simpleLiveData = MutableLiveData<Event<Users>>()

    fun getUsers(page: Int) {
        requestWithLiveData(simpleLiveData) {
            api.getUsers(
                page = page
            )
        }
    }

    fun getUsersError(page: Int, callback: (data: Event<Users>) -> Unit) {
        requestWithCallback({
            api.getUsersError(
                page = page
            )
        }) {
            callback(it)
        }
    }
}