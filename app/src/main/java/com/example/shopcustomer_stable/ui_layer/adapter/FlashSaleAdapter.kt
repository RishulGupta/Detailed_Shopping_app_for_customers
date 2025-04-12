package com.example.shopcustomer_stable.ui_layer.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shopcustomer_stable.databinding.EachFlashSaleItemRvBinding
import com.example.shopcustomer_stable.ui_layer.model.ProductsModel
import com.example.shopcustomer_stable.ui_layer.sheets.activity_viewmodel_ui.product_activity.ProductActivity

class FlashSaleAdapter(var context: Context, var productList: List<ProductsModel>) : Adapter<FlashSaleAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: EachFlashSaleItemRvBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(EachFlashSaleItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = productList[position]

        product.pImage?.let { holder.binding.flashSaleProductIv.setImageResource(it) }
        holder.binding.flashSaleProductNameTv.text = product.pName
        holder.binding.flashSaleProductCodeTv.text = product.pCode
        holder.binding.flashSaleProductDiscountedPriceTv.text = product.pDiscountedPrice.toString()
        holder.binding.flashSaleProductPriceTv.text = product.pPrice.toString()
        holder.binding.flashSaleProductPriceTv.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

//        val priceDifference:Float = product.pPrice - product.pDiscountedPrice!!.toFloat()
        val priceDifference:Float = product.pPrice?.minus(product.pDiscountedPrice!!)!!.toFloat()
//        val ogPrice:Float = product.pPrice.toFloat()
        val ogPrice:Float = product.pPrice!!.toFloat()
        val discountPercent:Float = (priceDifference/ogPrice)*100
        val displayPercent = discountPercent.toString().split(".")

//        holder.binding.flashSaleProductDiscountPercentageTv.text = discountPercent.toString().plus("% off")
        holder.binding.flashSaleProductDiscountPercentageTv.text = displayPercent[0].toString().plus("% off")

        holder.itemView.setOnClickListener{
            context.startActivity(Intent(context, ProductActivity::class.java).putExtra("DATA",product))
        }

    }
}