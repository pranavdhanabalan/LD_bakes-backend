package com.foods.ldbakes.Controller;

import com.foods.ldbakes.Model.Menu;
import com.foods.ldbakes.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/get")
    public List<Menu> getAllMenuItems(){
        return  menuService.getAllMenuItems();
    }

    @PostMapping("/add")
    public Menu createMenuItem(@RequestBody Menu menu) {
        return menuService.createMenuItem(menu);
    }

    @DeleteMapping("/delete/{itemId}")
    public boolean deleteMenuItem(@PathVariable Long itemId){
        return menuService.deleteMenuItem(itemId);
    }
}
