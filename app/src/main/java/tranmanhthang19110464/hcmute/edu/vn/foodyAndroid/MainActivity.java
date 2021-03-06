package tranmanhthang19110464.hcmute.edu.vn.foodyAndroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Database.Database;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment.homeFragment;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment.notificationFragment;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment.orderFragment;
import tranmanhthang19110464.hcmute.edu.vn.foodyAndroid.Fragment.userFragment;

public class MainActivity extends AppCompatActivity {
    GridView danhSachNhaHang;
    Database database;
    homeFragment fragmentHome;
    notificationFragment fragmentNotification;
    orderFragment fragmentOrder;
    userFragment fragmentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start Fragment
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        fragmentHome = new homeFragment();
        fragmentNotification = new notificationFragment();
        fragmentOrder = new orderFragment();
        fragmentUser = new userFragment();
        replaceFragment(fragmentHome);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        replaceFragment(fragmentHome);
                        break;
                    case R.id.notification:
                        replaceFragment(fragmentNotification);
                        break;
                    case R.id.order:
                        replaceFragment(fragmentOrder);
                        break;
                    case R.id.user:
                        replaceFragment(fragmentUser);
                        break;
                }
                return true;
            }
        });

        //Tao CSDL
        database = new Database(this);
        //createDatabase();
        //Reset CSDL
        database.QueryData("Delete from restaurant");
        database.QueryData("Delete from food");
        database.QueryData("Delete from ordertable");
        addRestaurantstoDB();
        addFoodstoDB();


    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

//    private void createDatabase() {
//        database.QueryData("Create table if not exists user(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255), birthdate VARCHAR(255), address VARCHAR(255), image INTEGER, isBuyer INTEGER)");
//        database.QueryData("Create table if not exists restaurant(idRestaurant INTEGER PRIMARY KEY, user_id INTEGER, name VARCHAR(255), address VARCHAR(255), time VARCHAR(255), rate DOUBLE, image INTEGER)");
//        database.QueryData("Create table if not exists food(idFood INTEGER PRIMARY KEY AUTOINCREMENT, restaurant_id INTEGER, name VARCHAR(255), describe VARCHAR(255), image INTEGER, price INTEGER)");
//        database.QueryData("Create table if not exists comment(restaurant_id INTEGER, user_id INTEGER, user_name VARCHAR(255), content VARCHAR(255), rate DOUBLE)");
//        database.QueryData("Create table if not exists ordertable(idOrder INTEGER PRIMARY KEY AUTOINCREMENT,user_id INTEGER, restaurant_id INTEGER, food_id INTEGER, quantity INTEGER, price INTEGER, isConfirm INTEGER)");
//    }
    public boolean addFood(Food food){
        if(food == null){
            return false;
        }else if(1==1){
            //Kiem tra trung id => return false
        }
        int id = food.getId();
        int restaurantId = food.getRestaurantId();
        String name = food.getName();
        String descrt = food.getDescibe();
        int image = food.getImage();
        int price = food.getPrice();
        database.QueryData("Insert into food Values("+ id +","+ restaurantId +",'"+ name +"','"+ descrt +"',"+ image +", "+ price +")");
        return true;
    }
    public void addFoodstoDB(){
        addFood(new Food(0,0,"G?? r??n","mi???ng g?? r??n ngon",R.drawable.food_burgerking_garan,40000));
        addFood(new Food(1,0,"Coca","n?????c gi???i kh??t coca",R.drawable.food_burgerking_cocajpg,20000));
        addFood(new Food(2,2,"G?? r??n","n?????c gi???i kh??t coca",R.drawable.food_burgerking_garan,20000));
        addFood(new Food(3,2,"Coca","n?????c gi???i kh??t coca",R.drawable.food_burgerking_cocajpg,20000));
        addFood(new Food(5,2,"G?? l???c khoai t??y","H???n h???p",R.drawable.food_kfc_chickenmixpotato,120000));
        addFood(new Food(4,1,"Voucher","n?????c gi???i kh??t coca",R.drawable.food_dookki_voucher,139000));
        addFood(new Food(6,4,"Tr?? s???a truy???n th???ng","n?????c gi???i kh??t coca",R.drawable.food_tron94_tranchauduongden,39000));
        addFood(new Food(7,4,"Tr?? s???a ho??ng kim","n?????c gi???i kh??t coca",R.drawable.food_tron94_trasuahoangkim,49000));
        addFood(new Food(8,4,"Tr?? s???a s??m d???a","n?????c gi???i kh??t coca",R.drawable.food_tron94_samdua,39000));
    }
    public boolean addRestaurant(NhaHang nhaHang){
        if(nhaHang==null){
            return false;
        }else if(1==1){
            //Kiem tra trung id => return false
        }
        int id = nhaHang.getId();
        int user_id = nhaHang.getUser_id();
        String name = nhaHang.getName();
        String address = nhaHang.getAddress();
        String time = nhaHang.getTime();
        double rate = nhaHang.getRate();
        int icon = nhaHang.getImgID();
        database.QueryData("Insert into restaurant Values("+ id +","+ user_id +",'"+ name +"','"+ address +"','"+ time +"', "+ rate +", "+icon+")");
        return true;
    }

    private void addRestaurantstoDB() {
        addRestaurant(new NhaHang(0,0,"Burger King","277 Ph???m Ng?? L??o, Ph?????ng Ph???m Ng?? L??o, Qu???n 1, Th??nh ph??? H??? Ch?? Minh 700000, Vi???t Nam","5:00 22:00", 4.6, R.drawable.restaurant_burgerking_icon));
        addRestaurant(new NhaHang(1,1,"Dookki","141 Nguy???n Gia Tr??, Ph?????ng 25, B??nh Th???nh, Th??nh ph??? H??? Ch?? Minh 70000, Vi???t Nam","5:00 22:00", 4.5, R.drawable.restaurant_dookki_icon));
        addRestaurant(new NhaHang(2,2,"KFC","193 ??. L?? V??n Vi???t, Hi???p Ph??, Qu???n 9, Th??nh ph??? H??? Ch?? Minh, Vi???t Nam","10:00 22:00", 4.6, R.drawable.restaurant_kfc_icon));
        addRestaurant(new NhaHang(3,3,"MC Donald","2-6Bis ??i???n Bi??n Ph???, ??a Kao, Qu???n 1, Th??nh ph??? H??? Ch?? Minh 70000, Vi???t Nam","5:00 22:00", 4.6, R.drawable.restaurant_mcdonald_icon));
        addRestaurant(new NhaHang(4,4,"Toco Toco","827 ??. Kha V???n C??n, Linh Chi???u, Th??? ?????c, Th??nh ph??? H??? Ch?? Minh, Vi???t Nam","5:00 22:00", 4.6, R.drawable.restaurant_tron94_icon));
        addRestaurant(new NhaHang(5,5,"Miutea","29 ???????ng Nguy???n V??n Nghi, Ph?????ng 4, G?? V???p, Th??nh ph??? H??? Ch?? Minh 700000, Vi???t Nam","5:00 22:00", 4.6, R.drawable.restaurant_tron94_icon));
        addRestaurant(new NhaHang(6,6,"Koi","372 ??. V?? V??n Ng??n, B??nh Th???, Th??? ?????c, Th??nh ph??? H??? Ch?? Minh 70000, Vi???t Nam","5:00 22:00", 4.6, R.drawable.restaurant_tron94_icon));
        addRestaurant(new NhaHang(7,7,"Tr?? s???a m??o","95H Quang Trung, P, Tp. Th??? ?????c, Th??nh ph??? H??? Ch?? Minh 700000, Vi???t Nam","5:00 22:00", 4.6, R.drawable.restaurant_tron94_icon));
        addRestaurant(new NhaHang(8,8,"Highlands Coffe","216 ??. V?? V??n Ng??n, B??nh Th???, Th??? ?????c, Th??nh ph??? H??? Ch?? Minh 700000, Vi???t Nam","5:00 22:00", 4.6, R.drawable.restaurant_tron94_icon));
    }


}