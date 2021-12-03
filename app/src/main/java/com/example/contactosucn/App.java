package com.example.contactosucn;

import android.app.Application;
import android.content.Context;
import org.acra.ACRA;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.DialogConfigurationBuilder;
import org.acra.data.StringFormat;

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


    CoreConfigurationBuilder builder = new CoreConfigurationBuilder(this);
    builder
        .withBuildConfigClass(BuildConfig.class)
        .withReportFormat(StringFormat.JSON)
        .withEnabled(true);

    // ACRA Dialog Configuration
    builder.getPluginConfigurationBuilder(DialogConfigurationBuilder.class)
        .withResText(R.string.acra_dialog_tittle)
        .withResCommentPrompt(R.string.acra_dialog_comment)
        .withEnabled(true);


    ACRA.init(this, builder);

  }

}
