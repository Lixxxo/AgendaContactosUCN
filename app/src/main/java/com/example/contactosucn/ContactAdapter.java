package com.example.contactosucn;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contactosucn.model.Contact;
import java.util.ArrayList;
import java.util.List;


public final class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

  private List<Contact> contactList = new ArrayList<>();

  /**
   * The Constructor
   */
  public ContactAdapter(){
    // Nothing here
  }

  /**
   * Populate contactList
   * @param contactList
   */
  public void setContactList(final List<Contact> contactList) {
    this.contactList = contactList;
  }

  /**
   * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent an
   * item.
   * <p>
   * This new ViewHolder should be constructed with a new View that can represent the items of the
   * given type. You can either create a new View manually or inflate it from an XML layout file.
   * <p>
   * The new ViewHolder will be used to display items of the adapter using {@link
   * #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display different items
   * in the data set, it is a good idea to cache references to sub views of the View to avoid
   * unnecessary {@link View#findViewById(int)} calls.
   *
   * @param parent   The ViewGroup into which the new View will be added after it is bound to an
   *                 adapter position.
   * @param viewType The view type of the new View.
   * @return A new ViewHolder that holds a View of the given view type.
   * @see #getItemViewType(int)
   * @see #onBindViewHolder(ViewHolder, int)
   */
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    // Step 1: Get the inflater
    final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    // Step 2: Inflate the row of Contact
    final View contactView = layoutInflater.inflate(R.layout.row_contact, parent, false);
    // Step 3: Build the ViewHolder
    return new ViewHolder(contactView);
  }

  /**
   * Called by RecyclerView to display the data at the specified position. This method should update
   * the contents of the {@link ViewHolder#itemView} to reflect the item at the given position.
   * <p>
   * Note that unlike {@link ListView}, RecyclerView will not call this method again if the position
   * of the item changes in the data set unless the item itself is invalidated or the new position
   * cannot be determined. For this reason, you should only use the <code>position</code> parameter
   * while acquiring the related data item inside this method and should not keep a copy of it. If
   * you need the position of an item later on (e.g. in a click listener), use {@link
   * ViewHolder#getAdapterPosition()} which will have the updated adapter position.
   * <p>
   * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can handle
   * efficient partial bind.
   *
   * @param holder   The ViewHolder which should be updated to represent the contents of the item at
   *                 the given position in the data set.
   * @param position The position of the item within the adapter's data set.
   */
  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    // Get Contact at position
    final Contact contact = this.contactList.get(position);

    holder.name.setText(contact.getName());
    holder.id.setText(String.valueOf(contact.getId()));
    holder.charge.setText(contact.getCharge());
    holder.unit.setText(contact.getUnit());
    holder.email.setText(contact.getEmail());
    holder.phone.setText(contact.getPhone());
    holder.address.setText(contact.getAddress());
  }

  /**
   * Returns the total number of items in the data set held by the adapter.
   *
   * @return The total number of items in this adapter.
   */
  @Override
  public int getItemCount() {
    return this.contactList.size();
  }

  /**
   * The ViewHolder
   */
  protected static class ViewHolder extends RecyclerView.ViewHolder {

    // for debugging
    private TextView id;

    private TextView name;
    private TextView charge;
    private TextView unit;
    private TextView office;
    private TextView email;
    private TextView phone;
    private TextView address;

    public ViewHolder(View view){
      super(view);
      this.id = view.findViewById(R.id.rc_tv_id);
      this.name = view.findViewById(R.id.rc_tv_name);
      this.charge = view.findViewById(R.id.rc_tv_charge);
      this.unit = view.findViewById(R.id.rc_tv_unit);
      this.office = view.findViewById(R.id.rc_tv_office);
      this.email = view.findViewById(R.id.rc_tv_email);
      this.phone = view.findViewById(R.id.rc_tv_phone);
      this.address = view.findViewById(R.id.rc_tv_address);
    }
  }
}
