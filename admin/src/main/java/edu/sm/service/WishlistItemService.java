package edu.sm.service;

import edu.sm.dao.WishlistDao;
import edu.sm.dto.WishlistItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WishlistItemService {
    private final WishlistDao wishlistItemDao;

    public WishlistItemService() {
        this.wishlistItemDao = new WishlistDao();
    }

    // 위시리스트 아이템 추가 메서드
    public WishlistItem addWishlistItem(WishlistItem item, Connection con) throws Exception {
        // 필요에 따라 추가적인 유효성 검사를 수행할 수 있습니다.
        return wishlistItemDao.insert(item, con);
    }


    // 모든 위시리스트 아이템 조회 메서드
    public List<WishlistItem> getAllWishlistItems(Connection con) throws Exception {
        return wishlistItemDao.select(con);
    }
}
