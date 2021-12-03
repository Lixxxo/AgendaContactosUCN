package com.example.contactosucn;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contactosucn.model.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

  /**
   * The Contact Adapter
   */
  protected ContactAdapter contactAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_list);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    // get the list (RecycleView)
    final RecyclerView recyclerView = findViewById(R.id.am_rv_contacts);

    // The type of layout of RecycleView
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    // Build the adapter
    this.contactAdapter = new ContactAdapter();
    // Union of Adapter + RecycleView
    recyclerView.setAdapter(this.contactAdapter);

  }

  /**
   * Load the contacts.json
   */
  @Override
  protected void onStart(){
    super.onStart();

    AsyncTask.execute(() ->{

      List<Contact> theContacts;

      // Read the contacts.json
      try (final InputStream is = super
          .getApplication()
          .getAssets()
          .open("contacts.json")){

        // Get the type of List<Contact> with reflection
        final Type contactListType = new TypeToken<List<Contact>>(){}.getType();

        // the reader
        final Reader reader = new InputStreamReader(is);

        // The json object converter*
        final Gson gson = new GsonBuilder().create();

        theContacts = gson.fromJson(reader, contactListType);
        reader.close();

      } catch (IOException e){
        e.printStackTrace();
        return;
      }
      // Sort by name
      theContacts.sort(Comparator.comparing(Contact::getName));

      // Remove the Contact without email
      theContacts.removeIf(c -> c.getEmail() == null);

      // populate the Adapter
      this.contactAdapter.setContactList(theContacts);

      runOnUiThread(() -> {
        // Notify / Update the GUI
        this.contactAdapter.notifyDataSetChanged();
      });
    });
  }


}