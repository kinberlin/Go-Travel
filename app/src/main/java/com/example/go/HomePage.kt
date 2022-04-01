package com.example.go

import android.content.Context

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home_page.*

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
    lateinit  var Cadapter : CompanyAdapter
    lateinit var  Tadapter : TownAdapter
    var TownLists = mutableListOf<Ville>(
        Ville(R.drawable.f13,"Jardin Botannique", "Le jardin botannique, creer en 1986 est un des sites touristiques les plus belles du Cameroun.","Sud Ouest")
    , Ville(R.drawable.f19,"Plage de Limbe", "Besoin de vacance, d'eau, de sable, de nature .... /n Bienvenue a la plage de Limbe.","Sud Ouest"),
        Ville(R.drawable.f20,"Plage de Kribi", "Vous aimer vous bronzer sur le sable, les noix de coco et les courant d'air frais ? /n Passer des vacances pacifiques","Sud Ouest")
    , Ville(R.drawable.f21,"Plage de Kribi", "Vous aimer vous bronzer sur le sable, les noix de coco et les courant d'air frais ? /n Passer des vacances pacifiques","Sud Ouest")
    ,Ville(R.drawable.f22,"Montagnes de Bangou", "Vous aimer l'altitude et les vus en hauteur, /n Bienvenu a Bangou et profiter de l'altitude","Ouest")
    ,Ville(R.drawable.f23,"Baie de Bertoua", "Aventurer vous aux coeurs des forets et montagnes et poser les yeux sur le  crystale bleu de bertoua","Est")
        ,Ville(R.drawable.f24,"Montagnes de Bangou", "Vous aimer l'altitude et les vus en hauteur, /n Bienvenu a Bangou et profiter de l'altitude","Ouest")
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
        {
            auth= FirebaseAuth.getInstance()
            var currentUser=auth.currentUser
            auth.signOut()
            var Mains = MainActivity()
            Mains.LoginStart()

        }

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

        Tadapter = TownAdapter(TownLists)
        idTownList.adapter = Tadapter
        idTownList.layoutManager = GridLayoutManager(context,TownLists.size)

        Cadapter = CompanyAdapter(Companies)
        idCompanyList.adapter = Cadapter
        idCompanyList.layoutManager = GridLayoutManager(context,Companies.size)

        Tadapter.setOnClickListener(object : TownAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {
                idVilleDescription.text ="Description : "+ TownLists[position].Desecription.toString()
                idVilleNom.text = "Nom : "+TownLists[position].name.toString()
                idVilleNomRegion.text = "Region : "+ TownLists[position].region
            }
        })
        Cadapter.setOnClickListener(object : CompanyAdapter.OnItemClickListener {

            override fun onItemClick(position: Int) {
                idCompanyDescription.text = "Description : "+ Companies[position].Description
            }
        })
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
