package com.example.contactosucn;

import android.app.Application;
import android.content.Context;
import org.acra.ACRA;

/**
 * @author LixoAcer
 */
public class App extends Application {

  /**
   *
   * @param base context to use
   *
   */
  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);

    ACRA.init(this);
  }

}
