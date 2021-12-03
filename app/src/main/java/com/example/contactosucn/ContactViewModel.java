package com.example.contactosucn;

import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
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


public class ContactViewModel extends AndroidViewModel {

  /**
   * The {@link List} of {@link Contact}
   */
  private MutableLiveData<List<Contact>> contactList;

  /**
   * The constructor of the class
   * @param application the application to use
   */
  public ContactViewModel(@NonNull Application application){
    super(application);
  }

  public MutableLiveData<List<Contact>> getContacts(){
    // Lazy load
    if (this.contactList == null){
      this.contactList = new MutableLiveData<>();
      this.loadContacts();
    }
    return this.contactList;
  }

  /**
   * Read the Contacts from contacts.json
   */
  private void loadContacts() {

    AsyncTask.execute(() ->{
      List<Contact> theContacts;

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

        // Google Gson black magic
        theContacts = gson.fromJson(reader, contactListType);

        // Sort by name
        theContacts.sort(Comparator.comparing(Contact::getName));

        // Remove the Contact without email
        theContacts.removeIf(c -> c.getEmail() == null);

      } catch (IOException e){
        e.printStackTrace();
        return;
      }

      this.contactList.postValue(theContacts);
    });
  }
}
