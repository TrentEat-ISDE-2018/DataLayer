package datalayer.dataCentric_Agritur.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import datalayer.dataCentric_Agritur.dao.AgriturDao;

@Entity
@NamedQuery(name = "AgriturEntity.findAll",query = "SELECT a FROM AgriturEntity a")
@Table(name = "agriturs")
public class AgriturEntity {
	private String phone; //0+prefisso+ " " + cell
	private String email;
	private String website;
	private Integer altitude;
	private String address; // indirizzo + ", " + comune
	private Double lat;
	private Double lon;
	
	@Id
	private String name; //ID	
	
	private Integer num_for_eat;
	private Integer num_for_sleep;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Integer getAltitude() {
		return altitude;
	}
	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNum_for_eat() {
		return num_for_eat;
	}
	public void setNum_for_eat(Integer num_for_eat) {
		this.num_for_eat = num_for_eat;
	}
	public Integer getNum_for_sleep() {
		return num_for_sleep;
	}
	public void setNum_for_sleep(Integer num_for_sleep) {
		this.num_for_sleep = num_for_sleep;
	}
	
    public static AgriturEntity getById(String id){
        EntityManager em= AgriturDao.instance.createEntityManager();
        AgriturEntity a=em.find(AgriturEntity.class,id);
        AgriturDao.instance.closeConnections(em);

        return a;
    }
    
    public static List<AgriturEntity> getAll(){
        EntityManager em = AgriturDao.instance.createEntityManager();
        List<AgriturEntity> userEntities = em.createNamedQuery("AgriturEntity.findAll").getResultList();
        AgriturDao.instance.closeConnections(em);
        return userEntities;
    }

    public static AgriturEntity saveAgritur(AgriturEntity a){
        EntityManager em= AgriturDao.instance.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        a=em.merge(a);
        tx.commit();
        AgriturDao.instance.closeConnections(em);

        return a;
    }
    
    public static void deleteAgritur(AgriturEntity a){
        EntityManager em= AgriturDao.instance.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        a=em.merge(a);
        em.remove(a);
        tx.commit();
        AgriturDao.instance.closeConnections(em);
    }
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
}
