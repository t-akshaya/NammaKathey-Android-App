package com.nammakathe.ui.district

import android.os.Bundle
import androidx.navigation.NavDirections
import com.nammakathe.R
import kotlin.Int
import kotlin.String

public class HeroListFragmentDirections private constructor() {
  private data class ActionHeroListToStory(
    public val heroId: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_heroList_to_story

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("heroId", this.heroId)
        return result
      }
  }

  public companion object {
    public fun actionHeroListToStory(heroId: String): NavDirections = ActionHeroListToStory(heroId)
  }
}
