package com.treecio.crowdio.permission

import android.content.Intent
import android.support.v4.app.Fragment

/**
 * This fragment serves as the creator and callback of permission requests. The fragment does not
 * contain any visible UI and is attached to an activity only to mediate the permission flow.
 */
class PermissionHelperFragment : Fragment() {

    private var permissionFlow: PermissionFlow? = null

    fun bindPermissionFlow(permissionFlow: PermissionFlow) {
        this.permissionFlow = permissionFlow
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionFlow!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!permissionFlow!!.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
