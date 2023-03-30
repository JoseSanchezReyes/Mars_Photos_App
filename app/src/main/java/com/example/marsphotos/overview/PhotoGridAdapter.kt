package com.example.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marsphotos.databinding.GridViewItemBinding
import com.example.marsphotos.service.network.MarsPhoto

//Diffutil de DiffCallback => Cada que se agrega, quita o cambia algun item de RecyclerView no se actualiza la lista completa
class PhotoGridAdapter : ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {

    // Representan cada vista de mi lista
    class MarsPhotoViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind( marsPhoto: MarsPhoto) {
            binding.photo = marsPhoto
            binding.executePendingBindings() //Actualizacion ejecutada de inmediato
        }
    }

    //Crear nuevas interfaces de vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        return MarsPhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    //Reemplaza el contenido de una vista de elementos de lista, con una vista y su posicion
    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

    //Objeto complementario para DiffCallback => para comparar dos objetos de tipo MarsPhoto
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {

        //Diffutil llama a este metodo para decidir si dos objetos representan el mismo valor
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        //DiffUtil llama a este metodo cuando desea verificar si dos elementos tienen los mismos datos.
        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }
}