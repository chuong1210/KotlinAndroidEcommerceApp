
package com.example.ecommerceshop.view
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceshop.model.BrandModel
import com.example.ecommerceshop.model.ItemsModel
import com.example.ecommerceshop.model.SliderModel
import com.google.firebase.database.*

class MainViewModel : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _brand=MutableLiveData<MutableList<BrandModel>>()
    private val _inform=MutableLiveData<MutableList<ItemsModel>>()

    val banner: LiveData<List<SliderModel>> = _banner
    val brand: LiveData<MutableList<BrandModel>> = _brand
    val inform: LiveData<MutableList<ItemsModel>> = _inform





    fun loadBanners() {
        val bannerRef = firebaseDatabase.getReference("Banner") // Thay thế "Banner" bằng path của bạn

        bannerRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Sử dụng map để chuyển đổi DataSnapshot thành List<SliderModel>
                val sliderModels = snapshot.children.mapNotNull {
                    it.getValue(SliderModel::class.java)
                }
                _banner.value = sliderModels
            //    var lists= mutableListOf<SliderModel>()
//                for (childSs in snapshot.children)
//                {
//                    val list=childSs.getValue(SliderModel::class.java)
//                    if(list!=null)
//                    {
//                        lists.add(list)
//                    }
//                }
//                _banner.value=lists
//            }
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý lỗi nếu cần
                Log.w( "Failed to read value.", error.toException())
                print("Lỗi")
            }
        })
    }

    fun loadBrands()
    {
        val Ref=firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<BrandModel>()
                for (childSs in snapshot.children)
                {
                    val list=childSs.getValue(BrandModel::class.java)
                    if(list!=null)
                    {
                        print(list)
                        lists.add(list)
                    }
                }
                _brand.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }




    fun loadInform()
    {
        val Ref=firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ItemsModel>()
                for (childSs in snapshot.children)
                {
                    val list=childSs.getValue(ItemsModel::class.java)
                    if(list!=null)
                    {
                        print(list)
                        lists.add(list)
                    }
                }
                _inform.value=lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
            })
}
}