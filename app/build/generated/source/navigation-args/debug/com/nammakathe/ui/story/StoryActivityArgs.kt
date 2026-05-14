package com.nammakathe.ui.story

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class StoryActivityArgs(
  public val heroId: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("heroId", this.heroId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("heroId", this.heroId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): StoryActivityArgs {
      bundle.setClassLoader(StoryActivityArgs::class.java.classLoader)
      val __heroId : String?
      if (bundle.containsKey("heroId")) {
        __heroId = bundle.getString("heroId")
        if (__heroId == null) {
          throw IllegalArgumentException("Argument \"heroId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"heroId\" is missing and does not have an android:defaultValue")
      }
      return StoryActivityArgs(__heroId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): StoryActivityArgs {
      val __heroId : String?
      if (savedStateHandle.contains("heroId")) {
        __heroId = savedStateHandle["heroId"]
        if (__heroId == null) {
          throw IllegalArgumentException("Argument \"heroId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"heroId\" is missing and does not have an android:defaultValue")
      }
      return StoryActivityArgs(__heroId)
    }
  }
}
