package com.test.flutter_bug_opengl_native

import android.content.Context
import android.opengl.GLSurfaceView
import android.opengl.GLU
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


/**
 * OpenGL Custom renderer used with GLSurfaceView
 */
class MyGLRenderer(val context: Context) : GLSurfaceView.Renderer {
    private val pyramid = Pyramid()
    private var anglePyramid = 0f


    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        gl.glClearDepthf(1.0f)
        gl.glEnable(GL10.GL_DEPTH_TEST)
        gl.glDepthFunc(GL10.GL_LEQUAL)
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST)
        gl.glShadeModel(GL10.GL_SMOOTH)
        gl.glDisable(GL10.GL_DITHER)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        var height = height
        if (height == 0) height = 1
        val aspect = width.toFloat() / height

        gl.glViewport(0, 0, width, height)

        gl.glMatrixMode(GL10.GL_PROJECTION)
        gl.glLoadIdentity()
        GLU.gluPerspective(gl, 45f, aspect, 0.1f, 100f)
        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()
    }

    override fun onDrawFrame(gl: GL10) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)

        gl.glLoadIdentity()
        gl.glTranslatef(0f, 0.0f, -6.0f)
        gl.glRotatef(anglePyramid, 0.1f, 1.0f, -0.1f)
        pyramid.draw(gl)

        anglePyramid += 2.0f
    }
}