package mary.klinger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    ImageView menuIcon;
    LinearLayout contentView;




    //El menú irá aquí
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);




        //Functions will be executed automatically when this activity will be created
        featuredRecycler();
        mostViewedRecycler(); 
        categoriesRecycler();

        //Acá se capturan los menus
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        
        navigationDrawer();








    }

    //Acá se ve la selección del menú
    private void navigationDrawer() {
        //Acá se captura la navegación
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        //Acá se llama al menu, con el menuIcon que capturamos arriba.
        menuIcon.setOnClickListener(view -> {
            if (drawerLayout.isDrawerVisible(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START);
            else drawerLayout.openDrawer(GravityCompat.START);
        });

        animateNavigationDrawer();
        
    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorSecondary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }


    public void onBackPressed(){
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                startActivity(intent);
                break;

            case  R.id.nav_nosotros:
                Intent intentd = new Intent(getApplicationContext(), AllCategories.class);
                startActivity(intentd);
                break;

            case  R.id.nav_contacto:
                Intent intentg = new Intent(getApplicationContext(), Contactanos.class);
                startActivity(intentg);
                break;

            case  R.id.nav_login:
                Intent intentA = new Intent(getApplicationContext(), Login.class);
                startActivity(intentA);
                break;

            case  R.id.nav_delivery:
                Intent intentB = new Intent(getApplicationContext(), EntregaDelivery.class);
                startActivity(intentB);
                break;



        }


        return true;
    }




    private void categoriesRecycler() {

        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();

        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.opciondos, "Fritas"));
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.opcionuno, "Horneadas"));
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.opciontres, "Vegano"));



        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);



    }

    public void callRetailerScreen(View view){
        startActivity(new Intent(getApplicationContext(),RetailerStartUpScreen.class));
    }

    private void mostViewedRecycler() {

        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.gelatinauno, "Gelatina de fresa","holaaa descripcion"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.gelatina, "Gelatina en trozos","holaaa descripcion"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.flan, "Flan","holaaa descripcion"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.arroz, "Arroz con leche","holaaa descripcion"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.magdalena, "Magdalena","holaaa descripcion"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.mote, "Mote con huesillo","holaaa descripcion"));


        adapter = new MostViewedAdpater(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);



    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.latacoca, "Lata Coca-Cola", "Bebiba en lata de cocacola."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.fanta, "Lata Fanta", "Bebiba en lata de fanta."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.sprite, "Lata Sprite", "Bebiba en lata de sprite."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.pepsi, "Lata Pepsi", "Bebiba en lata de pepsi."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.bottlecoca, "Botella 1/2 Coca-Cola", "Bebiba de medio cocacola."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.bottlefanta, "Botella 1/2 Fanta", "Bebiba de medio fanta."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.bottlesprite, "Botella 1/2 Sprite", "Bebiba de medio sprite."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.manzana, "Jugo Manzana", "Jugo de manzana 200ml."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.jugo, "Jugo Naranja", "Jugo de naranja 200ml."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.agua, "Agua con gas", "Agua vital con gas."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.coffee, "Café de máquina", "Café en máquina de variados sabores."));




        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400,0xffaff600});

    }



}