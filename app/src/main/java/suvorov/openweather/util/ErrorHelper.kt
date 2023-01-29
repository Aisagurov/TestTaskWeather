package suvorov.openweather.util

import android.content.Context
import suvorov.openweather.R
import suvorov.openweather.util.ShowHelper.showToast
import java.io.EOFException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorHelper {
    fun errorHandle(context: Context, throwable: Throwable) {
        val message = when(throwable) {
            is UnknownHostException -> context.getString(R.string.unable_to_connect_to_server)
            is SocketTimeoutException -> context.getString(R.string.link_timeout)
            is ConnectException -> context.getString(R.string.link_failed)
            is SocketException, is EOFException -> context.getString(R.string.link_closed)
            else -> throwable.message.emptyIfNull("")
        }
        showToast(context, message)
    }
}