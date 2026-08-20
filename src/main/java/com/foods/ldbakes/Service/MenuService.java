package com.foods.ldbakes.Service;

import com.foods.ldbakes.Model.Menu;
import com.foods.ldbakes.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenuItems(){
        return menuRepository.findAll();
    }

    public Menu createMenuItem(Menu menu){
        return menuRepository.save(menu);
    }

    public boolean deleteMenuItem(Long itemId){
        menuRepository.deleteById(itemId);
        return true;
    }
}
