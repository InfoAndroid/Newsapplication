package infoandroid.com.newsapplication.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class HeadlinePagerAdapter (manager: FragmentManager/*,fragments : ArrayList<Fragment>,titles :ArrayList<String>*/) : FragmentPagerAdapter(manager) {
    val fragments = ArrayList<Fragment>()
    val titles = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)

    }

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles.get(position)

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }
}