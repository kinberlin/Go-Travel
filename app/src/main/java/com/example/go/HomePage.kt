package com.example.go

import android.content.Intent
import kotlinx.android.synthetic.main.fragment_home_page.*

import android.net.Uri
import android.os.Bundle
import kotlinx.serialization.json.Json
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.example.go.Adapters.CompanyList
import com.example.go.Adapters.TownAdapter
import com.example.go.Models.Town
import com.example.go.Models.Transport_company
import com.example.go.Models.Travel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import kotlinx.serialization.encodeToString

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomePage.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomePage.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomePage : Fragment() {


    // TODO: Rename and change types of parameters
    lateinit var auth: FirebaseAuth
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit  var Cadapter : CompanyList
    lateinit var  Tadapter : TownAdapter

    var TownLists = mutableListOf<Town>(
        Town("Jardin Botannique", "Cameroun")
    , Town("Plage de Limbe", "Limbe")
       )
    var Companies = mutableListOf<CompagnieTransport>(
        CompagnieTransport(R.drawable.busa,"General Express",356,"AGence de voyage sure"),
        CompagnieTransport(R.drawable.busb,"Menoua Voyage",168,"L'ouest est keur domaine"),
        CompagnieTransport(R.drawable.busc,"Finexs",267,"Premier natianal en terme de transport interurbain"),
        CompagnieTransport(R.drawable.busd,"Global Voyage",73,"Nouveau sur le marchee")
    )
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }*/
override fun onOptionsItemSelected(item: MenuItem): Boolean {
    var Mains = MainActivity()
    when(item.itemId)
    {
        R.id.menu_home ->
        {
            Toast.makeText(
                context,
                "Opening Home Fragment",
                Toast.LENGTH_SHORT
            ).show()
        }
        R.id.menu_logout ->
        { //var currentUser=auth.currentUser

            Mains.signoout()

        }
        R.id.menu_contact ->{
            //startActivity(Intent(this,MyContactlist::class.java), bundleOf())
        }

        R.id.menu_myBooking ->{
            activity?.let{
                var intents = Intent(context,Booking_history::class.java)
                startActivity(intents, bundleOf())
            }}

    }
    return super.onOptionsItemSelected(item)
}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var daydate : String = "30-4-2022"
        date_input.setOnDateChangeListener(CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
            daydate = dayOfMonth.toString() +"-"+(month + 1).toString() + "-" +year.toString()
        })
        val TownArray = Town("Jardin Botannique", "Cameroun").getallTown()

        Tadapter = TownAdapter(TownArray)
        idTownList.adapter = Tadapter

        if(TownArray.size == 0)
        {

            idTownList.layoutManager = GridLayoutManager(context,10)

        }
        else{

            idTownList.layoutManager = GridLayoutManager(context,TownArray.size)

        }
        var voyages = mutableListOf<Travel>()

        var companydesc= Transport_company().getAllTranport_companysattributes()


        Cadapter = CompanyList(companydesc)
        idCompanyList.adapter = Cadapter

        if(companydesc.size == 0)
        {

            idCompanyList.layoutManager = GridLayoutManager(context,10)

        }
        else
        {

            idCompanyList.layoutManager = GridLayoutManager(context, companydesc.size)

        }
        /*var fil = File(context!!.filesDir, "travels")
        if(fil.exists()) {
            val fist: FileInputStream = FileInputStream(File(context!!.filesDir, "travels"))
            val `ist` = ObjectInputStream(fist)
            var restr = `ist`.readObject() as String
            voyages = Json.decodeFromString(restr) as MutableList<Travel>
            `ist`.close()
            fist.close()
        }*/
        searchtrip.setOnClickListener{
            activity?.let{
                //voyages = Travel().SearchTravels(from_input.text.toString(),To_input.text.toString(),daydate)
                print("")
                var intent = Intent(it,reservation_search::class.java)
                var vlList : String = Json.encodeToString(voyages)
                intent.putExtra("departure_town",from_input.text.toString() )
                intent.putExtra("arrival_town", To_input.text.toString())
                intent.putExtra("departure_date", daydate)
                intent.putExtra("vl", vlList);
                activity?.let{
                Toast.makeText(context, "Resultats en cours de chargement ...", Toast.LENGTH_LONG).show()}
                startActivity(intent)
            }
        }
        Tadapter.setOnClickListener(object : TownAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {
                //idVilleDescription.text ="Description : "+ TownLists[position].Desecription.toString()
                idVilleNom.text = "Nom : "+TownArray[position].town_name.toString()
                idVilleNomRegion.text = "Region : "+ TownArray[position].county_name
            }
        })
        Cadapter.setOnClickListener(object : CompanyList.OnItemClickListener {

            override fun onItemClick(position: Int) {

                idCompanyDescription.text = "Description : "+ companydesc[position].description
            }
        })
        Thread.sleep(1000L)
        super.onViewCreated(view, savedInstanceState)
    }
    // TODO: Rename method, update argument and hook method into UI event
   /* fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomePage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomePage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
