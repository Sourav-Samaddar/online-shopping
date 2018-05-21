package net.sam.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.sam.shoppingbackend.dao.CategoryDao;
import net.sam.shoppingbackend.dto.Category;

@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> list() {
		String selectActiveCategory = "from Category where active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory,Category.class);
		query.setParameter("active", true);
		return query.getResultList();
	}

	@Override
	public Category get(int id) {

		try {
			return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override

	public boolean add(Category category) {
		try {
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
