package com.treecio.crowdio.ui.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.treecio.crowdio.CrowdioApp
import com.treecio.crowdio.R
import com.treecio.crowdio.permission.PermissionCallback
import com.treecio.crowdio.permission.PermissionFlow
import com.treecio.crowdio.permission.PermissionFlowResult
import com.treecio.crowdio.ui.fragment.MapFragment

class MainActivity : BaseActivity(), PermissionCallback {

    private var permissionsChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (!permissionsChecked) {
            permissionsChecked = true
            PermissionFlow.builder(this, CrowdioApp.PERMISSIONS_BASIC)
                    .interactive(R.string.rationale_crucial_permissions)
                    .callback(this)
                    .flow()
        }
    }

    override fun onPermissionGranted(context: Context, requestId: Int, args: Bundle, result: PermissionFlowResult) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, MapFragment())
                .commit()

    }

    override fun onPermissionDenied(context: Context, requestId: Int, args: Bundle, result: PermissionFlowResult) {
        Toast.makeText(this, R.string.app_cant_continue_without_permissions, Toast.LENGTH_LONG).show()
        finish()
    }

}
