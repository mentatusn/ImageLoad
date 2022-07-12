package com.gb.imageload.ui.coil

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.Coil
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.load
import coil.transform.CircleCropTransformation
import com.gb.imageload.R
import com.gb.imageload.databinding.FragmentCoilBinding

class CoilFragment : Fragment() {

    private var _binding: FragmentCoilBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageLoader = ImageLoader.Builder(requireContext())
            .components {
                //GIF
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
                //SVG
                add(SvgDecoder.Factory())
            }
            .build()
        Coil.setImageLoader(imageLoader)
        binding.image.load("https://www.google.ru/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png") {
            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.loadingfast)
            //transformations(CircleCropTransformation())
        }

        binding.imageSVG.load("https://yastatic.net/weather/i/icons/blueye/color/svg/bkn_n.svg") {}
        binding.imageGIF.load(R.drawable.loadingfast) {}

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}