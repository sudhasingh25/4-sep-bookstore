package com.bookstore.entity;
// Generated Aug 5, 2020 8:24:04 AM by Hibernate Tools 5.2.3.Final


import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Book generated by hbm2java
 */
@Entity

@NamedQueries({
	@NamedQuery(name="Book.findAll",query="Select b from Book b"),
	@NamedQuery(name="Book.count",query="Select Count(*) from Book b"),
	@NamedQuery(name="Book.countByCategory",query="Select Count(b) from Book b where b.category.categoryId= :catId"),
	@NamedQuery(name="Book.findByTitle",query="Select b from Book b where b.title = :title"),
	@NamedQuery(name="Book.listbook",query="Select b from Book b ORDER BY b.publishDate DESC"),
	@NamedQuery(name="Book.search",query="Select b from Book b where b.title LIKE '%' || :keyword || '%' or b.author LIKE '%' || :keyword || '%' or b.description LIKE '%' || :keyword || '%'"),
	@NamedQuery(name="Book.bookByCategory",query="Select b from Book b Join Category c on b.category.categoryId=c.categoryId and c.categoryId= :catID")
})
@Table(name = "book", catalog = "book_store", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Book implements java.io.Serializable {

//	private static final GenerationType IDENTITY = null;
	private Integer bookId;
	private Category category;
	private String title;
	private String author;
	private String description;
	private String isbn;
	private byte[] image;
	private String base64Image;
	private float price;
	private Date publishDate;
	private Date lastUpdateTime;
	private Set<Review> reviews = new HashSet<Review>(0);
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

	public Book() {
	}

	
	public Book(Integer bookId) {
		super();
		this.bookId = bookId;
	}


	public Book(int bookId, Category category, String title, String author, String description, String isbn,
			byte[] image, float price, Date publishDate, Date lastUpdateTime) {
		this.bookId = bookId;
		this.category = category;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdateTime = lastUpdateTime;
	}

	public Book(int bookId, Category category, String title, String author, String description, String isbn,
			byte[] image, float price, Date publishDate, Date lastUpdateTime, Set<Review> reviews,
			Set<OrderDetail> orderDetails) {
		this.bookId = bookId;
		this.category = category;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdateTime = lastUpdateTime;
		this.reviews = reviews;
		this.orderDetails = orderDetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "book_id", unique = true, nullable = false)
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "title", unique = true, nullable = false, length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "author", nullable = false, length = 64)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "description", nullable = false, length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "isbn", nullable = false, length = 15)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "image", nullable = false)
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	@Transient
	public String getBase64Image(){
		this.base64Image= Base64.getEncoder().encodeToString(this.image);
		return this.base64Image;
	}
	
	@Transient
	public void setBase64Image(String base64){
		this.base64Image=base64;
	}	
		
	@Column(name = "price", nullable = false, precision = 12, scale = 0)
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "publish_date", nullable = false, length = 10)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update_time", nullable = false, length = 19)
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	

	@Transient
	public float getAverageRating(){
		float sum=0.0f,averageRating=0.0f;
		
		if(reviews.isEmpty()){
			return 0.0f;
		}
		
		for(Review review:reviews){
			sum+= review.getRating();
		}
		
		averageRating=sum/reviews.size();
		
		return averageRating;
	}
	
	@Transient
	public String getRatingStars(){
		float averageRating=getAverageRating();
		return getRatingString(averageRating);
	}
	
	@Transient
	public String getRatingString(float averageRating){
		String result="";
		
		int numberOfStarsOn=(int)averageRating;
		
		for(int i=1;i<=numberOfStarsOn;i++){
			result +="on,";
		}
		
		int next=numberOfStarsOn+1;
		if(averageRating>numberOfStarsOn){
			result +="half,";
			next++;
		}
		
		for(int i=next;i<=5;i++){
			result +="off,";
		}
		return result.substring(0,result.length()-1);
	}

	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		return true;
	}



}
