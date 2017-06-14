package services;

import com.google.inject.Inject;
import models.Restaurant;
import play.Logger;
import repository.MenuRepository;

import java.awt.*;
import java.util.List;

/**
 * Created by lapa on 6/14/17.
 */
public class MenuService implements BaseService {

    private MenuRepository menuRespository;

    @Inject
    public void setMenuRespository(MenuRepository menuRespository) {
        this.menuRespository = menuRespository;
    }

    public List getMenuForSelectedRestaurant(Long restaurantId, String type){
        return menuRespository.getMenuForSelectedRestaurant(restaurantId, type);
    }

}
