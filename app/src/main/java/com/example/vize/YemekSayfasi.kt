package com.example.vize
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class Yemek(
    val ad: String,
    val tarif: String,
    val yapilis: String
)

class YemekSayfasi : AppCompatActivity() {

    private lateinit var adapter: YemekAdapter // Adapter değişkeni global
    private val yemekListesi = mutableListOf( // Mutable yani değiştirilebilir bir liste
        Yemek("Kuymak", "100 gr tereyağı, 2 yemek kaşığı mısır unu, 1 su bardağı su, 200 gr kolot peyniri", "İlk olarak kolot peynirimizi küp küp doğrayarak bir kenara alalım. Muhlama peyniri genel olarak kolot peyniri kullanılmaktadır. Ancak dilerseniz çeçil peyniri ya da kaşar peyniri gibi eriyen diğer peynirlerle de yapabilirsiniz.\n" +
                "Ardından ocağa aldığımız tavaya tereyağını ekleyelim ve erimesini bekleyelim." +
                "Yağımız eridikten sonra mısır ununu ekleyelim ve karıştırarak kavuralım. Bu aşamada ocağın yüksek ateşte olmamasına dikkat etmelisiniz." +
                "Unumuz 1-2 dakika kavrulduktan sonra suyumuzu ekleyelim." +
                "Karışımımız krema kıvamına geldikten sonra doğradığımız peynirleri ekleyelim ve peynirlerimiz eriyene kadar kısık ateşte pişirmeye devam edelim." +
                "Peynirlerimiz eridikten sonra muhlamamız servise hazır. Yapımı oldukça kolay ve son derece lezzetli muhlama tarifimi deneyecek herkese şimdiden afiyet olsun!"),


        Yemek("Kalaco", "1 mısır ekmeği (bayat), 1 adet soğan, 4 çorba kaşığı tereyağı,4 su bardağı ayran,Tuz", "Kalaco tarifi için, ilk olarak mısır ekmeğini lokma lokma doğrayarak bir kenarda bekletin. Soğanı ince bir şekilde kıyın. Tereyağını tavada eritip altın sarısı rengi alana kadar soğanı kavurun. Ardından ayranı ekleyin ve sürekli karıştırarak bir taşım kaynatın. Kaynadıktan sonra doğranmış mısır ekmeğini ekleyin ve tuzunu damak tadınıza göre ayarlayın. Sonra tüm malzemeleri bir arada 10 dakika kadar daha pişirin.\n" +
                "Pişirme işlemi tamamlandıktan sonra ocaktan alın ve yaklaşık 10 dakika kadar dinlendirin. Ardından tabaklara paylaştırarak sıcak olarak servis yapın. Afiyet olsun!"),


        Yemek("Kaygana", "4 yumurta, 1 su bardağı un, 1 su bardağı süt,maydanoz,dereotu,yeşil soğan (yeşillikleri isteğinize bağlı ekleyin),tuz", "Öncelikle unu, sütü ve tuzu karıştırın." +
                "Sonra yumurtaları ekleyin." +
                "En son yeşillikleri ekleyip çırpın." +
                "Yanmaz tavaya 2 yemek kaşığı sıvı yağ alıp kızdırın." +
                "Ardından hamuru döküp arkalı önlü kızartın." +
                "Hamur bitene kadar aynı işlemi yapın." +
                "Hamuru ister ince isterseniz kalın döküp pişirebilirsiniz (ben ince döktüm hamuru)." +
                "Afiyet olsun.")
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yemek_sayfasi)

        val listView = findViewById<ListView>(R.id.yemekListView)//xmldeki listview
        // yemekListesi listviewini adapterle bağlıyoruz
        adapter = YemekAdapter(this, yemekListesi)
        listView.adapter = adapter

        // ListView öğesine tıklanınca detay sayfasına geçiş
        listView.setOnItemClickListener { _, _, position, _ ->
            //basılan yemek
            val secilenYemek = yemekListesi[position]
            //Detay sayfasına geçiş
            val intent = Intent(this, YemekDetayActivity::class.java)
            //YemekDetayActivity sayfasındaki textlere seçilen yemeğin bilgilerini yemekListesi nden seçip ata
            intent.putExtra("yemekAdi", secilenYemek.ad)
            intent.putExtra("yemekTarifi", secilenYemek.tarif)
            intent.putExtra("yemekYapilisi", secilenYemek.yapilis)
            startActivity(intent)
        }
    }

    // Menüyü bağlama
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    // Menüdeki Yemek Ekle seçeneğini işleme
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Yemek_Ekle -> {
                showYemekEkleDialog()
                return true
            }
            R.id.Oyun -> { // Menüdeki "Oyun" seçeneği tıklanırsa
                val intent = Intent(this, OyunActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Yemek ekleme dialogu
    private fun showYemekEkleDialog() {
        //viewleri tanıtma
        val dialogView = layoutInflater.inflate(R.layout.dialog_yemek_ekle, null) //görünüm nesnesi
        val yemekAdiInput = dialogView.findViewById<EditText>(R.id.etYemekAdi)
        val yemekTarifiInput = dialogView.findViewById<EditText>(R.id.etYemekTarifi)
        val yemekYapilisiInput = dialogView.findViewById<EditText>(R.id.etYemekYapilisi)
        //alert dialog oluşturmak için
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Yeni Yemek Ekle") // alert dialog başlığı
        builder.setView(dialogView)
        //Pozitif bir Ekle butonu oluştur
        builder.setPositiveButton("Ekle") { dialog, _ ->
            //girilen değerleri değişkenlere ata
            val yemekAdi = yemekAdiInput.text.toString()
            val yemekTarifi = yemekTarifiInput.text.toString()
            val yemekYapilisi = yemekYapilisiInput.text.toString()

            //üçü de boş değilse
            if (yemekAdi.isNotEmpty() && yemekTarifi.isNotEmpty() && yemekYapilisi.isNotEmpty()) {
                //yeni bir yemek örneği oluştur
                val yeniYemek = Yemek(yemekAdi, yemekTarifi, yemekYapilisi)
                yemekListesi.add(yeniYemek) // Listeye yeni yemeği ekle
                adapter.notifyDataSetChanged() // Adapteri güncelle

                Toast.makeText(this, "Yemek eklendi", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Alanları doldurun.", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss() //dialogu kapat
        }
        //negatif iptal butonu
        builder.setNegativeButton("İptal") { dialog, _ ->

            dialog.dismiss()
        }
        //alertdialogu oluştur ve göster
        builder.create().show()
    }



}
