package com.morezilla.materialfox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.mozilla.geckoview.GeckoRuntime
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoView

class MainActivity : AppCompatActivity() {

  companion object {
    // GeckoRuntime can only be initialized once per process
    private var sRuntime: GeckoRuntime? = null
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val view: GeckoView = findViewById(R.id.geckoView)
    val session = GeckoSession()

    // Workaround for Bug 1758212
    session.contentDelegate = object : GeckoSession.ContentDelegate {}

    if (sRuntime == null) {
      sRuntime = GeckoRuntime.create(this)
    }

    session.open(sRuntime!!)
    view.setSession(session)
    session.loadUri("https://www.firefox.com") // Or any other URL
  }
}