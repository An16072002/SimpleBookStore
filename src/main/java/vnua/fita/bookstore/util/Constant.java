package vnua.fita.bookstore.util;

public interface Constant {
	public static final String BOOK_ID_INVALID_VALIDATE_MSG="Book id xác thực không hợp lệ"; 
	public static final String BOOK_QUANTITY_IN_STOCK_INVALID_VALIDATE_MSG="Số lượng trong kho xác thực không hợp lệ"; 
	public static final String ADD_TO_CART_ACTION="addToCart"; 
	public static final String CART_OF_CUSTOMER="cartOfCustomer"; 
	public static final String REMOVE_FROM_CART_ACTION="removeFromCart"; 
	public static final String GET_BOOK_FAIL="Lỗi lấy danh sách sách"; 
	public static final String INCORRECT_ACCOUNT_VALIDATE_MSG="Sai thông tin tài khoản"; 
	public static final int ADMIN_ROLE=1; 
	public static final int CUSTOMER_ROLE=2; 
	public static final String USERNAME_EMPTY_VALIDATE_MSG="Nhap username"; 
	public static final String PASSWORD_EMPTY_VALIDATE_MSG="Nhap password"; 
	public static final String LOGINED_USER="loginedUser";
	public static final String USERNAME_STORE_IN_COOKIE_OF_BOOKSTORE="username";
	public static final String TOKEN_STORE_IN_COOKIE_OF_BOOKSTORE="token";
	public static final String SECRET_STRING="SECRET_STRING";
}
