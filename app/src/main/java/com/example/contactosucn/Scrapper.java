package com.example.contactosucn;



import com.example.contactosucn.model.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scrapper {

  private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


  public ArrayList<Contact> getContactsFromUrl() throws IOException {

    ArrayList<Contact> list;

    // create a reader
    Reader reader = Files.newBufferedReader(Paths.get("contacts.json"));

    list = GSON.fromJson(reader, new TypeToken<ArrayList<Contact>>() {
    }.getType());

    int start = list.get(list.size() - 1).getId();
    int end = 30000;

    for (int id = start; id < end; id++) {
      // Connect and get the Document
      String urlBase = "https://admision01.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=";
      Document doc = Jsoup
          .connect(urlBase + id)
          .get();

      if (doc.getElementById("lblNombre").text().length() < 1) {
        continue;
      }

      // Wait for ...
      try {
        Thread.sleep(250);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      // Scraping
      String name = doc.getElementById("lblNombre").text();
      String charge = doc.getElementById("lblCargo").text();
      String unit = doc.getElementById("lblUnidad").text();
      String email = doc.getElementById("lblEmail").text();
      String phone = doc.getElementById("lblTelefono").text();
      String office = doc.getElementById("lblOficina").text();
      String address = doc.getElementById("lblDireccion").text();

      // Save by 15.
      if (list.size() % 15 == 0) {

        GSON.toJson(list, new FileWriter(String.valueOf(Paths.get("contacts.json"))));

        list.add(
            new Contact(id, name, charge, unit, email, phone, office, address));

      }
    }

    return list;

  }
}