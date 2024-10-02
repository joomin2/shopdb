package edu.sm.wishlist;


import edu.sm.dto.Wishlist;

import edu.sm.service.WishlistService;

import java.util.List;

public class WishlistSelect {
    public static void main(String[] args) {
        WishlistService wishlistService = new WishlistService();

        List <Wishlist> wishlist = null;

        try {
            wishlist = wishlistService.get();
            System.out.println(wishlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
