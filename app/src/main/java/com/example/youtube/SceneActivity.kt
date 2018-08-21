package com.example.youtube

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.youtube.common.checkCameraPermission
import com.example.youtube.common.requestCameraPermission
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class SceneActivity : AppCompatActivity() {

    var sceneRenderable: ViewRenderable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!checkCameraPermission()) {
            requestCameraPermission(CAMERA_PERMISSION_REQUEST_CODE)
            return
        }

        setContentView(R.layout.activity_scene)
        // Setting up the AR scene.
        setupScene()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode and grantResults[0]){

            CAMERA_PERMISSION_REQUEST_CODE and PackageManager.PERMISSION_GRANTED -> {
                setContentView(R.layout.activity_scene)
                // Setting up the AR scene.
                setupScene()
            }
        }
    }


    /**
     * Method to setup the AR scene.
     */
    private fun setupScene() {

        val arFragment = supportFragmentManager.findFragmentById(R.id.arSceneFragment) as ArFragment

        /**
         * Sets up the ViewRenderable.
         */
        fun setupViewRenderable() {
            ViewRenderable.builder()
                    .setView(this, R.layout.arscene)
                    .build()
                    .thenAccept { renderable -> sceneRenderable = renderable }
                    .exceptionally { throwable ->
                        Log.e(TAG, "This shit happened: ${throwable.localizedMessage}")
                        null
                    }

            arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
                sceneRenderable?.let {

                    val anchor = hitResult.createAnchor()
                    val node = AnchorNode(anchor)
                    node.setParent(arFragment.arSceneView.scene)

                    val youtube = TransformableNode(arFragment.transformationSystem)
                    youtube.setParent(node)
                    youtube.renderable = sceneRenderable
                    youtube.select()
                }
            }
        }

        // Setting up the required View in the scene.
        setupViewRenderable()

    }

    companion object {
        val TAG: String = SceneActivity::class.java.simpleName
        const val CAMERA_PERMISSION_REQUEST_CODE = 1001
    }
}
