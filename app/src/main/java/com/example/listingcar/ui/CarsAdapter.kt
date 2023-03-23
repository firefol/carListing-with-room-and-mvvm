package com.example.listingcar.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listingcar.dao.Car
import com.example.listingcar.databinding.ItemCarsBinding

class CarsAdapter(): RecyclerView.Adapter<CarsAdapter.CarsViewHolder>() {

    private var cars: List<Car> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setNewsList(cars: List<Car>){
        this.cars = cars
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCarsBinding.inflate(inflater, parent, false)
        return CarsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val cars = cars[position]
        with(holder.binding){
            holder.itemView.tag = cars
            markTextView.text = cars.mark
            typeCarTextView.text = cars.type_car
            horsePowerTextView.text = cars.horsePower.toString()
            colorTextView.text = cars.color
            Glide.with(image.context)
                .load(cars.image)
                .into(image)
        }
    }

    override fun getItemCount(): Int = cars.size

    class CarsViewHolder(
        val binding: ItemCarsBinding
    ):RecyclerView.ViewHolder(binding.root)
}