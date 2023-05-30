package com.love.lovelive.ui.fragments.goLive

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.os.Handler
import android.os.HandlerThread
import android.view.Surface
import android.view.TextureView.SurfaceTextureListener
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.love.lovelive.databinding.FragmentGoLiveBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class GoLiveVm @Inject constructor() : ViewModel() {

    private lateinit var binding: FragmentGoLiveBinding
    private lateinit var context:GoLiveFragment

    private lateinit var handler: Handler
    private lateinit var handlerThread: HandlerThread
    private lateinit var cameraManager:CameraManager
    private lateinit var cameraDevice: CameraDevice
    private var requestPermissionLauncher:ActivityResultLauncher<String>? = null


    fun initContext(context:GoLiveFragment){
        this.context = context
        binding = context.getViewDataBinding()
        cameraManager = context.requireActivity().getSystemService(Context.CAMERA_SERVICE) as CameraManager
        handlerThread = HandlerThread("VideoThread")
        handlerThread.start()
        handler = Handler(handlerThread.looper)

        binding.textureView.surfaceTextureListener = object :SurfaceTextureListener{
            override fun onSurfaceTextureAvailable(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
                openCamera()
            }

            override fun onSurfaceTextureSizeChanged(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                return false
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {

            }

        }

        requestPermissionLauncher = context.registerForActivityResult(RequestPermission()) { isGranted ->
                if (isGranted) {
                    showCamera()
                } else {
                    requestPermissionLauncher?.launch(Manifest.permission.CAMERA)
                }
            }
    }

    private fun openCamera() {
        if (ActivityCompat.checkSelfPermission(
                context.requireActivity(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher?.launch(Manifest.permission.CAMERA)
            return
        }
        else{
            showCamera()
        }


    }

    @SuppressLint("MissingPermission")
    private fun showCamera() {
        cameraManager.openCamera(cameraManager.cameraIdList[1],object :CameraDevice.StateCallback(){
            override fun onOpened(camera: CameraDevice) {
                cameraDevice = camera
                val capRequest = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                var surface = Surface(binding.textureView.surfaceTexture)
                capRequest.addTarget(surface)

                cameraDevice.createCaptureSession(listOf(surface),object :CameraCaptureSession.StateCallback(){
                    override fun onConfigured(session: CameraCaptureSession) {
                        session.setRepeatingRequest(capRequest.build(), null, null)
                    }

                    override fun onConfigureFailed(session: CameraCaptureSession) {

                    }

                },handler)
            }

            override fun onDisconnected(camera: CameraDevice) {
            }

            override fun onError(camera: CameraDevice, error: Int) {
            }

        },handler)
    }


}