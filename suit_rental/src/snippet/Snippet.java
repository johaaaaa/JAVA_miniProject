package snippet;

public class Snippet {
	CREATE TABLE rental
	(PHONE_NUM number(8) primary key,
	rental_date DATE DEFAULT SYSDATE,
	rental_end DATE DEFAULT SYSDATE+3,
	SUIT_SIZE VARCHAR(2) constraint suit_size_nn NOT NULL ,
	CATEGORY VARCHAR2(10) constraint suit_cate_nn NOT NULL);
	
}

