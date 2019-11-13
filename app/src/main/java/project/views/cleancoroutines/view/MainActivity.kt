package project.views.cleancoroutines.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import project.views.cleancoroutines.R
import project.views.cleancoroutines.data.model.Error
import project.views.cleancoroutines.data.model.User
import project.views.cleancoroutines.mvvm.Status
import project.views.cleancoroutines.mvvm.viewModel.ActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var activityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        observeGetPosts()

        buttonOneClickListener()
        buttonTwoClickListener()
    }

    private fun observeGetPosts() {
        activityViewModel.simpleLiveData.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> viewOneLoading()
                Status.SUCCESS -> viewOneSuccess(it.data)
                Status.ERROR -> viewOneError(it.error)
            }
        })
    }


    private fun buttonOneClickListener() {
        btn_test_one.setOnClickListener {
            activityViewModel.getUsers(page = 1)
        }
    }

    private fun buttonTwoClickListener() {
        btn_test_two.setOnClickListener {
            activityViewModel.getUsersError(page = 2) {
                when (it.status) {
                    Status.LOADING -> viewTwoLoading()
                    Status.SUCCESS -> viewTwoSuccess(it.data)
                    Status.ERROR -> viewTwoError(it.error)
                }
            }
        }
    }

    private fun viewOneLoading() {

    }

    private fun viewOneSuccess(data: User?) {
        val userList: MutableList<User.Item>? = data?.items as MutableList<User.Item>?
        userList?.shuffle()
        userList?.let {
            Toast.makeText(applicationContext, "${it}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewOneError(error: Error?) {

    }


    private fun viewTwoLoading() {

    }

    private fun viewTwoSuccess(data: User?) {

    }

    private fun viewTwoError(error: Error?) {
        error?.let {
            Toast.makeText(applicationContext, error.errorMsg, Toast.LENGTH_SHORT).show()
        }
    }


}
