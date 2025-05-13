package com.example.smartcoffee;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] coffee_images = {R.drawable.espresso, R.drawable.cappuccino, R.drawable.drycappuccino, R.drawable.ristretto, R.drawable.lungo,
            R.drawable.americano, R.drawable.blackeye, R.drawable.doppio, R.drawable.macchiato, R.drawable.cortado, R.drawable.latte, R.drawable.doublelatte, R.drawable.flatwhite,
            R.drawable.mocha, R.drawable.breve, R.drawable.mochabreve, R.drawable.galao, R.drawable.cafecreme, R.drawable.cafebombon,
            R.drawable.cafenoisette, R.drawable.affogato, R.drawable.conpanna};
    private String[] coffee_names = {"Espresso", "Cappuccino", "Dry Cappuccino", "Ristretto", "Lungo", "Americano", "Black Eye", "Doppio", "Macchiato", "Cortado",
            "Latte", "Double Latte", "Flat White", "Mocha", "Breve", "Mocha Breve", "Galao", "Cafe Creme", "Cafe Bombon", "Cafe Noisette", "Affogato", "Con Panna"};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return coffee_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.coffee_pictures, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.espresso_picture);
        imageView.setImageResource(coffee_images[position]);
        TextView textView = (TextView) view.findViewById(R.id.coffees);
        textView.setText(coffee_names[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoffeeChoice.class);
                intent.putExtra("name", coffee_names[position]);
                intent.putExtra("image", coffee_images[position]);
                context.startActivity(intent);
            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

}
