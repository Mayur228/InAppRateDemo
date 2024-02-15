package com.example.inappratedemo

import android.app.Activity
import android.util.Log
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.testing.FakeReviewManager

interface InAppReview {
    fun checkConstraints(activity: Activity, isNotFromHomeActivity: Boolean)
}

object InAppReviewObj: InAppReview {
    override fun checkConstraints(activity: Activity, isNotFromHomeActivity: Boolean) {
        requestReview(activity)
    }

    fun requestReview(activity: Activity) {
        try {
//            val manager = ReviewManagerFactory.create(activity)
            val manager = FakeReviewManager(activity)

            val request = manager.requestReviewFlow()
            request
                .addOnSuccessListener {
                    val reviewInfo = it
                    val flow = manager.launchReviewFlow(activity, reviewInfo)
                    flow.addOnSuccessListener { it1 ->
                        Log.e("CHECK",reviewInfo.toString())
                    }
                }
        } catch (e: Exception) {
            Log.e("ERROR",e.message.toString())
        }
    }

}
