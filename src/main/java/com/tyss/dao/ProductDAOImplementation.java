package com.tyss.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tyss.dto.ProductBean;
import com.tyss.exception.ProductException;

@Repository
public class ProductDAOImplementation implements ProductDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;
	
	@Override
	public boolean createProduct(ProductBean bean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		boolean isAdded;
		try {
			transaction.begin();
			manager.persist(bean);
			transaction.commit();
			isAdded = true;
		} catch (Exception e) {
			e.printStackTrace();
			isAdded = false;
			throw new ProductException(e.getMessage());
		} finally {
			manager.close();
		}
		return isAdded;
	}

	@Override
	public boolean update(ProductBean bean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		boolean isUpdated = false;
		try {
			transaction.begin();
			String jpql = "update ProductBean p set p.productName = :name where p.price = :price";
			Query query = manager.createQuery(jpql);
			query.setParameter("name", bean.getProductName());
			query.setParameter("price", bean.getPrice());
			query.executeUpdate();
			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {
			e.printStackTrace();
			isUpdated = false;
			throw new ProductException(e.getMessage());
		} finally {
			manager.close();
		}
		return isUpdated;
	}

	@Override
	public boolean delete(String productName) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		boolean isdeleted = false;
		try {
			transaction.begin();
			ProductBean bean = manager.find(ProductBean.class, productName);
			if(bean != null) {
				manager.remove(bean);
				transaction.commit();
				isdeleted = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isdeleted = false;
			throw new ProductException(e.getMessage());
		} finally {
			manager.close();
		}
		return isdeleted;
	}

	@Override
	public ProductBean getProduct(String productName) {
		EntityManager manager = factory.createEntityManager();
		try {
		/*	String jpql = "select p from ProductBean p where p.productName = :productName";
			Query query = manager.createQuery(jpql, ProductBean.class);
			query.setParameter("productName", productName);
			ProductBean bean = (ProductBean) query.getSingleResult();*/
			ProductBean bean = manager.find(ProductBean.class, productName);
			if(bean != null) {
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		} finally {
			manager.close();
		}
		return null;
	}

	@Override
	public List<ProductBean> getAllProduct() {
		EntityManager manager = factory.createEntityManager();
		try {
			String jpql = "select p from ProductBean p";
			TypedQuery<ProductBean> query = manager.createQuery(jpql, ProductBean.class);
			List<ProductBean> productList = query.getResultList();
			return productList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException(e.getMessage());
		} finally {
			manager.close();
		}
	}

}
