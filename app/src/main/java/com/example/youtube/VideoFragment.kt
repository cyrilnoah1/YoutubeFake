package com.example.youtube


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_video.*


/**
 * [Fragment] to display the desired video and comments related to it.
 */
class VideoFragment : Fragment() {

    /**
     * [OnTransitionListener] to provide continuous updates of progress values of
     * [mlRoot]'s transitions.
     */
    var progressListener: VideoFragment.OnTransitionListener? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Listening to the motion transition of the layout.
        clRoot.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionChange(
                    motionLayout: MotionLayout?,
                    startId: Int,
                    endId: Int,
                    progress: Float
            ) {
                // Sending the transition progress to the listening class.
                progressListener?.onProgress(progress)
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                // Nothing to do here. Scroll away.
            }
        })
    }

    /**
     * Listener to provide the transition progress value of [MotionLayout] transitions.
     */
    interface OnTransitionListener {
        fun onProgress(progress: Float)
    }

    companion object {

        /**
         * Method to return a new instance of the [VideoFragment] class.
         */
        fun newInstance(listener: VideoFragment.OnTransitionListener): VideoFragment {

            return VideoFragment().apply { progressListener = listener }
        }
    }
}
