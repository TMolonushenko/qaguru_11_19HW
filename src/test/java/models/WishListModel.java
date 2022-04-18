package models;

public class WishListModel {
    /*
    {
    "success": true,
    "message": "The product has been added to your <a href=\"/wishlist\">wishlist</a>",
    "updatetopwishlistsectionhtml": "(1)"
}

     */

    public Boolean success;
    public String message;
    public String updatetopwishlistsectionhtml;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUpdateTopWishlistsectionhtml() {
        return updatetopwishlistsectionhtml;
    }

    public void setWishlistsection(String wishlistsection) {
        this.updatetopwishlistsectionhtml = wishlistsection;
    }

}
