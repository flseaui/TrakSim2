package engine

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL

class TrakEngine {

    companion object {
        val WINDOW_SIZE = Pair(800, 600)
    }

    private var errorCallback: GLFWErrorCallback? = null
    private var keyCallback: GLFWKeyCallback? = null

    private var window: Long? = null

    private fun init() {

        errorCallback = glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err))

        if (!glfwInit()) {
            throw IllegalStateException("Unable to initialize GLFW")
        }
        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

        window = glfwCreateWindow(WINDOW_SIZE.first, WINDOW_SIZE.second, "T r a k s i m", NULL, NULL)
        if (window == NULL) {
            throw RuntimeException("Failed to create the GLFW window")
        }

        keyCallback = glfwSetKeyCallback(window!!, object : GLFWKeyCallback() {
            override fun invoke(
                window: Long,
                key: Int,
                scancode: Int,
                action: Int,
                mods: Int
            ) {

                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                    glfwSetWindowShouldClose(window, true)
                }

            }
        })

        val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())

        glfwSetWindowPos(
            window!!,
            (vidmode!!.width() - WINDOW_SIZE.first) / 2,
            (vidmode.height() - WINDOW_SIZE.second) / 2
        )

        glfwMakeContextCurrent(window!!)
        glfwSwapInterval(1)
        glfwShowWindow(window!!)

    }

    private fun loop() {

        GL.createCapabilities()

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f)

        while (!glfwWindowShouldClose(window!!)) {
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
            glfwSwapBuffers(window!!)
            glfwPollEvents()
        }

    }

    fun run() {

        try {

            init()
            loop()

            glfwDestroyWindow(window!!)
            keyCallback?.free()

        } finally {

            glfwTerminate()
            errorCallback?.free()

        }
    }

}
