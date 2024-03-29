package com.example.go

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.go.R
/*

*/
/*

class CompanyAdapter(private val mList: List<CompagnieTransport>) : RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    private lateinit var mListener : OnItemClickListener

    fun setOnClickListener(listener : OnItemClickListener){mListener = listener }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.societe_items, parent, false)

        return ViewHolder(view,mListener)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, Position: Int) {

//for(x in mList){
        // sets the image to the imageview from our itemHolder class
        *//*

*/
/*var bmp : Bitmap = BitmapFactory.decodeByteArray(mList[Position].image,0,mList[Position].image!!.size)
        holder.imageView.setImageBitmap(bmp)*//*
*/
/*

        holder.imageView.setImageResource(mList[Position].logo)
        // sets the text to the textview from our itemHolder class
        //holder.textname.text = " Nom : "+mList[Position].name +"/n Nombre de Destinations : " +mList[Position].nombre_Destination
        holder.textname.text = mList[Position].name.toString()
        //holder.textDescrip.text = mList[Position].Description
    }





    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.idTownImage)
        //val textDescrip: TextView = itemView.findViewById(R.id.idDescription)
        val textname: TextView = itemView.findViewById(R.id.idCountryName)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition) }
        }


    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
*//*

*/
/*
class TownAdapter(private val mList: List<Ville>) : RecyclerView.Adapter<TownAdapter.ViewHolder>() {

    private lateinit var mListener : OnItemClickListener
    fun setOnClickListener(listener : OnItemClickListener){mListener = listener }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.town_items, parent, false)

        return ViewHolder(view,mListener)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, Position: Int) {

//for(x in mList){
        // sets the image to the imageview from our itemHolder class
        *//*
*/
/*

*//*

*/
/*var bmp : Bitmap = BitmapFactory.decodeByteArray(mList[Position].image,0,mList[Position].image!!.size)
        holder.imageView.setImageBitmap(bmp)*//*
*/
/*
*//*

*/
/*

        holder.imageView.setImageResource(mList[Position].image)
        // sets the text to the textview from our itemHolder class
        holder.textname.text = " Nom : "+mList[Position].image
        holder.textRegion.text = mList[Position].region}





    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.idTownImage)
        val textRegion: TextView = itemView.findViewById(R.id.idRegion)
        val textname: TextView = itemView.findViewById(R.id.idTownName)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition) }
        }


    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}*//*

*/
/*

*/
