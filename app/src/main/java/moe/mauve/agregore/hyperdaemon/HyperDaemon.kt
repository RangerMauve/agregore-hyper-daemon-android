package moe.mauve.agregore.mobile.lite

import java.io.InputStream
import to.holepunch.bare.kit.Worklet

class HyperDaemon() {
  private var worklet: Worklet? = null

  public fun start(storagePath: String, bundleStream: InputStream) {
    if (worklet !== null) {
      throw RuntimeException("Already started")
    }
    worklet = Worklet(null)

    try {
      val args = arrayOf(storagePath)
      worklet!!.start("/app.bundle", bundleStream, args)
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }

  public fun pause() {
    worklet!!.suspend()
  }

  public fun resume() {
    worklet!!.resume()
  }

  public fun destroy() {
    worklet!!.terminate()
    worklet = null
  }
}
