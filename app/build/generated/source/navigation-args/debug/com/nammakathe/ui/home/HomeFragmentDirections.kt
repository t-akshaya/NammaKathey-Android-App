package com.nammakathe.ui.home

import android.os.Bundle
import androidx.navigation.NavDirections
import com.nammakathe.R
import kotlin.Int
import kotlin.String

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeToStory(
    public val heroId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_home_to_story

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("heroId", this.heroId)
        return result
      }
  }

  public companion object {
    public fun actionHomeToStory(heroId: String): NavDirections = ActionHomeToStory(heroId)
  }
}
