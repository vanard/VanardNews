package career.gits.vanard.vanardnews

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_view_news.*

class ViewNewsActivity : AppCompatActivity() {

    var webUrl:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_news)

        if (intent != null)
        {
            view_title.setText(intent.getStringExtra("title"))
            view_desc.setText(intent.getStringExtra("desc"))
            Picasso.with(this)
                    .load(intent.getStringExtra("image"))
                    .into(view_image)
            webUrl = intent.getStringExtra("webUrl")
        }

        view_readmore.setOnClickListener {
            val i = Intent(baseContext, NewsDetailActivity::class.java)
            i.putExtra("webUrl", webUrl)
            startActivity(i)
        }
    }
}
