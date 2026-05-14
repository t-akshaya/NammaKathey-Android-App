package com.nammakathe.ui.district

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.nammakathe.R
import com.nammakathe.databinding.FragmentStatueFinderBinding
import com.nammakathe.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StatueFinderFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentStatueFinderBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private var googleMap: GoogleMap? = null

    companion object {
        fun newInstance(heroId: String) = StatueFinderFragment().apply {
            arguments = Bundle().also { it.putString("heroId", heroId) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatueFinderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val heroId = arguments?.getString("heroId") ?: return
        val hero = viewModel.getHeroById(heroId) ?: return

        lifecycleScope.launch {
            val isKn = viewModel.isKannada.first()
            binding.tvMemorialName.text = hero.memorialName
            binding.tvHeroNameMap.text = if (isKn) hero.nameKn else hero.name
            binding.tvHeroEmojiMap.text = hero.emoji
        }

        // Setup map
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        binding.btnGetDirections.setOnClickListener {
            val uri = Uri.parse("google.navigation:q=${hero.memorialLat},${hero.memorialLng}&mode=d")
            startActivity(Intent(Intent.ACTION_VIEW, uri).apply {
                setPackage("com.google.android.apps.maps")
            })
        }
        binding.btnBack.setOnClickListener { parentFragmentManager.popBackStack() }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val heroId = arguments?.getString("heroId") ?: return
        val hero = viewModel.getHeroById(heroId) ?: return

        val memorialPos = LatLng(hero.memorialLat, hero.memorialLng)
        map.addMarker(
            MarkerOptions()
                .position(memorialPos)
                .title(hero.name)
                .snippet(hero.memorialName)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        )
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(memorialPos, 13f))
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isCompassEnabled = true

        // Try to show user location
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
