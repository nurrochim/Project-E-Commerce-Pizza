package com.ecommerce.ecommercepizza.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ecommerce.ecommercepizza.model.MenuPizza;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import com.ecommerce.ecommercepizza.model.User;

/**
 * Created by Asus on 24/10/2016.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "ECommercePizza.db";
    private static final int    DATABASE_VERSION = 4;
    private Dao<User, String> userDao = null;
    private Dao<MenuPizza, String> menuPizzaDao = null;
    private RuntimeExceptionDao<User, ?> mUser;
    private RuntimeExceptionDao<MenuPizza, ?> mMenuPizza;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, MenuPizza.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, MenuPizza.class, true);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        super.close();
    }


    public Dao<User, String> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        mUser = getRuntimeExceptionDao(User.class);
        return userDao;
    }

    public Dao<MenuPizza, String> getMenuPizzaDao() throws SQLException {
        if (menuPizzaDao == null) {
            menuPizzaDao = getDao(MenuPizza.class);
        }
        mMenuPizza = getRuntimeExceptionDao(MenuPizza.class);
        return menuPizzaDao;
    }

    public String AddDataMenuPizza() {
        String message = "";
        try {
            // add sample data
            for (int i = 1; i < 10; i++) {
                MenuPizza item = new MenuPizza();
                item.setIdMenu(String.valueOf(i));
                switch (i) {
                    case 1:
                        item.setMenuName("BEEF RENDANG");
                        item.setDetail("Rendang Sauce, Shredded Mozzarella Cheese, Onion, Green Pepper");
                        item.setHarga("Rp.105.000");
                        item.setSize("10 Inch");
                    case 2:
                        item.setMenuName("tAYAM BAKAR");
                        item.setDetail("Rendang Sauce, Shredded Mozzarella Cheese, Onion, AyamBakar, Dry Parsley Flake");
                        item.setHarga("Rp.105.000");
                        item.setSize("10 Inch");
                    case 3:
                        item.setMenuName("EXTRAVAGANZZA");
                        item.setDetail("Domino's Pizza Sauce, Green Peppers, Beef Crumble, Beef Pepperoni, Onions, Mushrooms, Black Olives, Mozzarella Cheese");
                        item.setHarga("Rp.85.000");
                        item.setSize("10 Inch");
                    case 4:
                        item.setMenuName("FIRE BREATHER");
                        item.setDetail("Domino's Pizza Sauce, Sambal Sauce, Jalapeno, Tomato, Beef Pepperoni, Dry Chili Flakes, Mozzarella Cheese");
                        item.setHarga("Rp.72.000");
                        item.setSize("10 Inch");
                    case 5:
                        item.setMenuName("SPICY GARLIC PRAWN");
                        item.setDetail("Rustic Sauce, Shredded Mozzarella Cheese, Prawn, Garlic Bread Seasoning, Onion, Local Tomato, Dry Parsley Flakes");
                        item.setHarga("Rp.105.000");
                        item.setSize("10 Inch");
                    case 6:
                        item.setMenuName("TANDOORI CHICKEN");
                        item.setDetail("Curry Sauce, Onion, Green Pepper, Tandoori Chicken, Rahitta Sauce");
                        item.setHarga("Rp.105.000");
                        item.setSize("10 Inch");
                    case 7:
                        item.setMenuName("VEGGIE SUPREME");
                        item.setDetail("Domino's Pizza Sauce, Green Pepper, Red Pepper, Onion, Feta Cheese, Parsley, Mozzarella Cheese");
                        item.setHarga("Rp.72.000");
                        item.setSize("10 Inch");
                    case 8:
                        item.setMenuName("VEGGIE MANIA");
                        item.setDetail("Domino's Pizza Sauce, Onions, Green Peppers, Corns, Mushrooms, Black Olives, Tomato Dice, Dried Parsley, Mozarella Cheese");
                        item.setHarga("Rp.85.000");
                        item.setSize("10 Inch");
                    case 9:
                        item.setMenuName("SAMBAL BEEF");
                        item.setDetail("Pizza Dough, Dom Pizza Sauce, Mozarella Cheese, Beef Topping / Cramble, Sambal Sauce");
                        item.setHarga("Rp.45.000");
                        item.setSize("10 Inch");
                    case 10:
                        item.setMenuName("BEEF PEPPERONI FEAST");
                        item.setDetail("Domino's Pizza Sauce, Beef Pepperoni, Mozarella Cheese");
                        item.setHarga("Rp.72.000");
                        item.setSize("10 Inch");
                }
            menuPizzaDao.create(item);
        }
            message = "Succes Add Data Menu Pizza";
    } catch (SQLException e) {
        e.printStackTrace();
        message = e.getMessage();
    }
    return message;
}

}
