package com.example.contactosucn;


import com.example.contactosucn.model.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public final class TheMain {

  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


  public static void main(String[] args) throws IOException {


    ArrayList<Contact> list;
    String filename="contacts.json";
    Path pathToFile = Paths.get(filename);
    // create a reader
    Reader reader = Files.newBufferedReader(pathToFile.toAbsolutePath());
    list = GSON.fromJson(reader, new TypeToken<ArrayList<Contact>>() {
    }.getType());

    int start = list.get(list.size() - 1).getId() + 1;
    int end = 30000;

    for (int id = start; id < end; id++) {
      // Connect and get the Document
      String urlBase = "https://admision01.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=";
      Document doc = Jsoup
          .connect(urlBase + id)
          .get();

      if (doc.getElementById("lblNombre").text().length() < 1) {

        System.out.println("id: "+ id);
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
      System.out.println(name + "added.");
      list.add(
          new Contact(id, name, charge, unit, email, phone, office, address));
      // Save by 15.
      if (list.size() % 15 == 0) {
        System.out.println("saved 15 contacts.");
        Writer writer = new FileWriter("contacts.json");
        GSON.toJson(list, writer);
        // close writer
        writer.close();
      }

    }

  }

}
