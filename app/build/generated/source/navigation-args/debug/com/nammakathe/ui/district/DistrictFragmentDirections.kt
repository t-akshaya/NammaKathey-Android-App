package com.nammakathe.ui.district

import android.os.Bundle
import androidx.navigation.NavDirections
import com.nammakathe.R
import kotlin.Int
import kotlin.String

public class DistrictFragmentDirections private constructor() {
  private data class ActionDistrictToHeroList(
    public val districtId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_district_to_heroList

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("districtId", this.districtId)
        return result
      }
  }

  public companion object {
    public fun actionDistrictToHeroList(districtId: String): NavDirections =
        ActionDistrictToHeroList(districtId)
  }
}
