package com.nammakathe.ui.district

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class HeroListFragmentArgs(
  public val districtId: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("districtId", this.districtId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("districtId", this.districtId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): HeroListFragmentArgs {
      bundle.setClassLoader(HeroListFragmentArgs::class.java.classLoader)
      val __districtId : String?
      if (bundle.containsKey("districtId")) {
        __districtId = bundle.getString("districtId")
        if (__districtId == null) {
          throw IllegalArgumentException("Argument \"districtId\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"districtId\" is missing and does not have an android:defaultValue")
      }
      return HeroListFragmentArgs(__districtId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): HeroListFragmentArgs {
      val __districtId : String?
      if (savedStateHandle.contains("districtId")) {
        __districtId = savedStateHandle["districtId"]
        if (__districtId == null) {
          throw IllegalArgumentException("Argument \"districtId\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"districtId\" is missing and does not have an android:defaultValue")
      }
      return HeroListFragmentArgs(__districtId)
    }
  }
}
