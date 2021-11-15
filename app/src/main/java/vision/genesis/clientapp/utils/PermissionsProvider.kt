package vision.genesis.clientapp.utils

import android.content.Context
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Completable
import timber.log.Timber
import javax.inject.Inject

class PermissionsProvider @Inject constructor(context: Context) {

    private val rxPermissions = RxPermissions.getInstance(context)

    fun requestPermission(permission: String): Completable =
        rxPermissions.request(permission)
            .flatMapCompletable { permissionGranted ->
                if (permissionGranted) Completable.complete()
                else Completable.error(PermissionNotGrantedException())
            }
            .doOnError { Timber.e(it, it.message.orEmpty()) }

    fun requestPermissions(permissions: Array<String>): Completable =
        rxPermissions.request(*permissions).flatMapCompletable { permissionsGranted ->
            if (permissionsGranted) Completable.complete()
            else Completable.error(PermissionNotGrantedException())
        }
            .doOnError { Timber.e(it, it.message.orEmpty()) }

    fun isPermissionGranted(permission: String): Boolean {
        return rxPermissions.isGranted(permission)
    }

    fun isPermissionRevoked(permission: String): Boolean {
        return rxPermissions.isRevoked(permission)
    }

    class PermissionNotGrantedException : Exception()
}