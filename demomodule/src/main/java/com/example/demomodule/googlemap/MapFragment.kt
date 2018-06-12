package com.rosia.googlemap


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demomodule.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_map.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class MapFragment : Fragment(), OnMapReadyCallback {

    override fun onMapReady(googleMap: GoogleMap?) {
        val outletLocation = arguments?.getParcelable<LatLng>(_LATLNG)
        googleMap?.uiSettings?.isMapToolbarEnabled = false
        googleMap?.uiSettings?.setAllGesturesEnabled(false)
        println("on map ready $outletLocation")

        outletLocation?.let {
            googleMap?.addMarker(MarkerOptions().position(outletLocation))
        }
        // For zooming automatically to the location of the marker
        val cameraPosition = CameraPosition.Builder().target(outletLocation).zoom(18f).build()
        googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)

        var view=inflater.inflate(R.layout.fragment_map,container,false)
        view.mapView.onCreate(savedInstanceState)
        view.mapView.getMapAsync(this)
        return view
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    companion object {
        private const val _LATLNG ="latlng"
        fun newInstance(latLng: LatLng) : MapFragment{
            val mapFragment = MapFragment()
            val args = Bundle()
            args.putParcelable(_LATLNG, latLng)
            mapFragment.arguments = args
            return mapFragment
        }
    }


}
