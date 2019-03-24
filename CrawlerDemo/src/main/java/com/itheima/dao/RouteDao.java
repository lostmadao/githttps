package com.itheima.dao;

import com.itheima.domain.Route;
import com.itheima.utils.JDBCUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    public void updateRoutetTwo(Route route){
        String sql = "insert into tab_route2 values(null,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            int update = template.update(sql, route.getRname(), route.getPrice(), route.getRouteIntroduce(), route.getRflag(), route.getRdate(), route.getIsThemeTour(), route.getCount(), route.getCid(), route.getRimage(), route.getSid(), route.getSourceId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }
}
