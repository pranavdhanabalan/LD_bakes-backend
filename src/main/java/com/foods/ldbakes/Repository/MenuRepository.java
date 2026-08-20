package com.foods.ldbakes.Repository;

import com.foods.ldbakes.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}
