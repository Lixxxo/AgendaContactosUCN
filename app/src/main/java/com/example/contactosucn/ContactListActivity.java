package com.example.contactosucn;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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

    // Instance ContactViewModel
    ContactViewModel contactViewModel = ViewModelProvider
        .AndroidViewModelFactory  // The Factory
        .getInstance(this.getApplication()) // Singleton instance of Factory
        .create(ContactViewModel.class); // Call the Constructor of ContactViewModel

    // Observe List of Contacts
    contactViewModel.getContacts().observe(this, contactList -> {
      // Set the contactList (from view model)
      contactAdapter.setContactList(contactList);
      // Refresh Recycler (ListView)
      contactAdapter.notifyDataSetChanged();
    });

  }

}